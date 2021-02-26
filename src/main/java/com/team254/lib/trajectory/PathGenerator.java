package com.team254.lib.trajectory;

import java.util.List;

import com.team2363.HelixSplineGenerator;
import com.team2363.QuinticHermiteSpline;
import com.team319.trajectory.RobotConfig;
import com.team319.ui.DraggableWaypoint;

public class PathGenerator {

	public static Trajectory generateTrajectory(List<DraggableWaypoint> waypoints) {
		QuinticHermiteSpline[] splines = HelixSplineGenerator.getSplines(waypoints);
		// Generate a smooth trajectory over the total distance.
		Trajectory traj = TrajectoryGenerator.generate(
				0.0,
				0.0, 
				splines[0].calculateLength(), 
				waypoints.get(1).getCurrentVelocity(), 
				waypoints.get(1).getMaxVelocity());
		double distance = splines[0].calculateLength();
		for (int i = 2; i < waypoints.size(); ++i) {
			distance += splines[i - 1].calculateLength();
			traj.append(
					TrajectoryGenerator.generate(
							traj.getSegments().get(traj.getNumSegments() - 1).vel,
							traj.getSegments().get(traj.getNumSegments() - 1).pos, 
							distance, 
							waypoints.get(i).getCurrentVelocity(), 
							waypoints.get(i).getMaxVelocity()));
		}

		assignHeadings(traj, splines);
		correctHeading(traj);
		return traj;
	}	

	private static void assignHeadings(Trajectory traj, QuinticHermiteSpline[] splines) {
		// Assign headings based on the splines.
		int cur_spline = 0;
		double cur_spline_start_pos = 0;
		double length_of_splines_finished = 0;
		for (int i = 0; i < traj.getNumSegments(); ++i) {
			double cur_pos = traj.getSegments().get(i).pos;

			boolean found_spline = false;
			while (!found_spline) {
				double cur_pos_relative = cur_pos - cur_spline_start_pos;
				if (cur_pos_relative <= splines[cur_spline].calculateLength()) {
					double t = splines[cur_spline].parametrizeSpline(cur_pos_relative);
					traj.getSegments().get(i).heading = splines[cur_spline].getRotation(t);
					traj.getSegments().get(i).x = splines[cur_spline].x(t);
					traj.getSegments().get(i).y = splines[cur_spline].y(t);
					found_spline = true;
				} else if (cur_spline < splines.length - 1) {
					length_of_splines_finished += splines[cur_spline].calculateLength();
					cur_spline_start_pos = length_of_splines_finished;
					++cur_spline;
				} else {
					traj.getSegments().get(i).heading = splines[splines.length - 1].getRotation(1.0);
					traj.getSegments().get(i).x = splines[splines.length - 1].x(1.0);
					traj.getSegments().get(i).y = splines[splines.length - 1].y(1.0);
					found_spline = true;
				}
			}
		}
	}

	private static void correctHeading(Trajectory trajectory) {
		// Fix headings so they are continuously additive 
		double lastUncorrectedHeading = trajectory.getSegments().get(0).heading;
		double lastCorrectedHeading = lastUncorrectedHeading;
		for (int i = 1; i < trajectory.getNumSegments(); ++i) {
			Segment currentSegment = trajectory.getSegments().get(i);
			double uncorrectedHeading = currentSegment.heading;
			double headingDelta = 0;
			
			if (lastUncorrectedHeading < 0 && uncorrectedHeading > 0  && lastUncorrectedHeading < -Math.PI / 2) {
				headingDelta = -(2 * Math.PI - Math.abs(lastUncorrectedHeading) - Math.abs(uncorrectedHeading));
			} else if (lastUncorrectedHeading > 0 && uncorrectedHeading < 0 && lastUncorrectedHeading > Math.PI / 2) {
				headingDelta = 2 * Math.PI - Math.abs(lastUncorrectedHeading) - Math.abs(uncorrectedHeading);
			} else {
				headingDelta = lastUncorrectedHeading - uncorrectedHeading;
			}

			double correctedHeading = lastCorrectedHeading - headingDelta;
			currentSegment.heading = correctedHeading;
			lastUncorrectedHeading = uncorrectedHeading;
			lastCorrectedHeading = correctedHeading;
		}
	}

	public static TrajectorySet makeLeftAndRightTrajectories(Trajectory input) {
		Trajectory[] output = new Trajectory[2];
		output[0] = input.copy();
		output[1] = input.copy();
		Trajectory left = output[0];
		Trajectory right = output[1];

		for (int i = 0; i < input.getNumSegments(); ++i) {
			Segment current = input.getSegments().get(i);
			double cos_angle = Math.cos(current.heading);
			double sin_angle = Math.sin(current.heading);

			Segment s_left = left.getSegments().get(i);
			s_left.x = current.x - RobotConfig.wheelBase / 2 * sin_angle;
			s_left.y = current.y + RobotConfig.wheelBase / 2 * cos_angle;
			if (i > 0) {
				// Get distance between current and last segment
				double dist = Math.sqrt((s_left.x - left.getSegments().get(i - 1).x)
						* (s_left.x - left.getSegments().get(i - 1).x)
						+ (s_left.y - left.getSegments().get(i - 1).y)
						* (s_left.y - left.getSegments().get(i - 1).y));
				s_left.pos = left.getSegments().get(i - 1).pos + dist;
				s_left.vel = dist / s_left.dt;
				s_left.acc = (s_left.vel - left.getSegments().get(i - 1).vel) / s_left.dt;
				s_left.jerk = (s_left.acc - left.getSegments().get(i - 1).acc) / s_left.dt;
			}

			Segment s_right = right.getSegments().get(i);
			s_right.x = current.x + RobotConfig.wheelBase / 2 * sin_angle;
			s_right.y = current.y - RobotConfig.wheelBase / 2 * cos_angle;
			if (i > 0) {
				// Get distance between current and last segment
				double dist = Math.sqrt((s_right.x - right.getSegments().get(i - 1).x)
						* (s_right.x - right.getSegments().get(i - 1).x)
						+ (s_right.y - right.getSegments().get(i - 1).y)
						* (s_right.y - right.getSegments().get(i - 1).y));
				s_right.pos = right.getSegments().get(i - 1).pos + dist;
				s_right.vel = dist / s_right.dt;
				s_right.acc = (s_right.vel - right.getSegments().get(i - 1).vel) / s_right.dt;
				s_right.jerk = (s_right.acc - right.getSegments().get(i - 1).acc) / s_right.dt;
			}
		}

		return new TrajectorySet(output[0], input, output[1]);
	}
}
