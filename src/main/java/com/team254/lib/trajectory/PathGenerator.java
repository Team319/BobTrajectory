package com.team254.lib.trajectory;

import com.team254.lib.trajectory.Trajectory.Segment;

/**
 * Generate a smooth Trajectory from a Path.
 *
 * @author Art Kalb
 * @author Stephen Pinkerton
 * @author Jared341
 */
public class PathGenerator {
	/**
	 * Generate a path for autonomous driving.
	 * 
	 * @param waypoints
	 *            The waypoints to drive to (FOR THE "GO LEFT" CASE!!!!)
	 * @param config
	 *            Trajectory config.
	 * @param wheelbase_width
	 *            Wheelbase separation; units must be consistent with config and
	 *            waypoints.
	 * @param name
	 *            The name of the new path. THIS MUST BE A VALID JAVA CLASS NAME
	 * @return The path.
	 */
	public static Path makePath(WaypointSequence waypoints, TrajectoryGenerator.Config config, double wheelbase_width,
			String name) {
		return new Path(name, generateFromPath(waypoints, config));
	}

	static Trajectory generateFromPath(WaypointSequence path, TrajectoryGenerator.Config config) {
		if (path.getNumWaypoints() < 2) {
			return null;
		}

		// Compute the total length of the path by creating splines for each pair
		// of waypoints.
		Spline[] splines = new Spline[path.getNumWaypoints() - 1];
		double[] spline_lengths = new double[splines.length];
		for (int i = 0; i < splines.length; ++i) {
			splines[i] = new Spline();
			if (!Spline.reticulateSplines(path.getWaypoint(i), path.getWaypoint(i + 1), splines[i],
					Spline.QuinticHermite)) {
				System.out.println("COULDN'T RETICULATE SPLINE!!");
				return null;
			}
			spline_lengths[i] = splines[i].calculateLength();
		}

		// Generate a smooth trajectory over the total distance.
		Trajectory traj = TrajectoryGenerator.generate(
				config, 
				0.0,
				0.0, 
				spline_lengths[0], 
				path.getWaypoint(1).endVelocity, 
				path.getWaypoint(1).maxVelocity);
		double distance = spline_lengths[0];
		for (int i = 2; i < path.num_waypoints_; ++i) {
			distance += spline_lengths[i - 1];
			traj.append(
					TrajectoryGenerator.generate(
							config,
							traj.getSegment(traj.getNumSegments() - 1).vel,
							traj.getSegment(traj.getNumSegments() - 1).pos, 
							distance, 
							path.getWaypoint(i).endVelocity, 
							path.getWaypoint(i).maxVelocity));
		}

		// Assign headings based on the splines.
		int cur_spline = 0;
		double cur_spline_start_pos = 0;
		double length_of_splines_finished = 0;
		for (int i = 0; i < traj.getNumSegments(); ++i) {
			double cur_pos = traj.getSegment(i).pos;

			boolean found_spline = false;
			while (!found_spline) {
				double cur_pos_relative = cur_pos - cur_spline_start_pos;
				if (cur_pos_relative <= spline_lengths[cur_spline]) {
					double percentage = splines[cur_spline].getPercentageForDistance(cur_pos_relative);
					traj.getSegment(i).heading = splines[cur_spline].angleAt(percentage);
					double[] coords = splines[cur_spline].getXandY(percentage);
					traj.getSegment(i).x = coords[0];
					traj.getSegment(i).y = coords[1];
					found_spline = true;
				} else if (cur_spline < splines.length - 1) {
					length_of_splines_finished += spline_lengths[cur_spline];
					cur_spline_start_pos = length_of_splines_finished;
					++cur_spline;
				} else {
					traj.getSegment(i).heading = splines[splines.length - 1].angleAt(1.0);
					double[] coords = splines[splines.length - 1].getXandY(1.0);
					traj.getSegment(i).x = coords[0];
					traj.getSegment(i).y = coords[1];
					found_spline = true;
				}
			}
		}

		// Fix headings so they are continuously additive 
		double lastUncorrectedHeading = traj.getSegment(0).heading;
		double lastCorrectedHeading = traj.getSegment(0).heading;
		for (int i = 1; i < traj.getNumSegments(); ++i) {
			Segment currentSegment = traj.getSegment(i);
			double uncorrectedHeading = currentSegment.heading;

			double headingDelta = 0;
			
			if (lastUncorrectedHeading < 0 && uncorrectedHeading > 0  && lastUncorrectedHeading < -Math.PI / 2) {
				headingDelta = -(2 * Math.PI - Math.abs(lastUncorrectedHeading) - Math.abs(uncorrectedHeading));
			} else if (lastUncorrectedHeading > 0 && uncorrectedHeading < 0 && lastUncorrectedHeading > Math.PI / 2) {
				headingDelta = 2 * Math.PI - Math.abs(lastUncorrectedHeading) - Math.abs(uncorrectedHeading);
			} else {
				headingDelta = lastUncorrectedHeading - uncorrectedHeading;
			}

			double correctedHeading = lastCorrectedHeading + headingDelta;
			currentSegment.heading = correctedHeading;
			lastUncorrectedHeading = uncorrectedHeading;
			lastCorrectedHeading = correctedHeading;
		}
		return traj;
	}	
}
