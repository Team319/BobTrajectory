package com.team254.lib.trajectory;

import java.util.List;

import com.team2363.geometry.Pose2d;
import com.team2363.spline.Spline;
import com.team2363.spline.SplineGenerator;
import com.team319.trajectory.RobotConfig;
import com.team319.ui.DraggableWaypoint;

public class PathGenerator {

	public static Trajectory generateTrajectory(List<DraggableWaypoint> waypoints) {
		Spline[] splines = SplineGenerator.getSplines(waypoints);
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

	private static void assignHeadings(Trajectory traj, Spline[] splines) {
		// Assign headings based on the splines.
		int cur_spline = 0;
		double start_pos = 0;
		double splineLength = splines[0].calculateLength();
		for (Segment segment : traj.getSegments()) {

			if (segment.pos - start_pos > splineLength && cur_spline < splines.length - 1) {
				start_pos += splineLength;
				splineLength = splines[cur_spline + 1].calculateLength();
				cur_spline++;
			}

			double input = Math.min(1, splines[cur_spline].calculateInput(segment.pos - start_pos));
			Pose2d pose = splines[cur_spline].getPoint(input);

			segment.x = pose.x();
			segment.y = pose.y();
			segment.heading = pose.getRotation();
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

			currentSegment.heading = lastCorrectedHeading - headingDelta;
			lastUncorrectedHeading = uncorrectedHeading;
			lastCorrectedHeading = currentSegment.heading;
		}
	}

	public static TrajectorySet makeLeftAndRightTrajectories(Trajectory input) {
		Trajectory left = input.copy();
		Trajectory right = input.copy();

		for (int i = 0; i < input.getNumSegments(); ++i) {
			Segment currentCenter = input.getSegments().get(i);
			double cos_angle = Math.cos(currentCenter.heading);
			double sin_angle = Math.sin(currentCenter.heading);

			Segment currentLeft = right.getSegments().get(i);
			currentLeft.x = currentCenter.x + RobotConfig.wheelBase / 2 * sin_angle;
			currentLeft.y = currentCenter.y - RobotConfig.wheelBase / 2 * cos_angle;
			if (i > 0) calculateSegmentData(currentLeft, left.getSegments().get(i - 1));

			Segment currentRight = right.getSegments().get(i);
			currentRight.x = currentCenter.x + RobotConfig.wheelBase / 2 * sin_angle;
			currentRight.y = currentCenter.y - RobotConfig.wheelBase / 2 * cos_angle;
			if (i > 0) calculateSegmentData(currentRight, right.getSegments().get(i - 1));
		}

		return new TrajectorySet(left, input, right);
	}

	private static void calculateSegmentData(Segment current, Segment previous) {
		double dist = Math.sqrt((current.x - previous.x) 
		* (current.x - previous.x) 
		+ (current.y - previous.y) 
		* (current.y - previous.y));
		current.pos = previous.pos + dist;
		current.vel = dist / current.dt;
		current.acc = (current.vel - previous.vel) / current.dt;
		current.jerk = (current.acc - previous.acc) / current.dt;
	}
}