package com.team254.lib.trajectory;

/**
 * Base class for an autonomous path.
 * 
 * @author Jared341
 */
public class Path {
	protected Trajectory trajectory;
	protected String name_;

	public Path(String name, Trajectory newTraj) {
		name_ = name;
		trajectory = newTraj;
	}

	public Path() {

	}

	public String getName() {
		return name_;
	}

	public Trajectory getTrajectory() {
		return trajectory;
	}

	public double getEndHeading() {
		int numSegments = trajectory.getNumSegments();
		Trajectory.Segment lastSegment = trajectory.getSegment(numSegments - 1);
		return lastSegment.heading;
	}
	
	public void offsetHeading(double theta_rad) {
		trajectory.offsetHeading(theta_rad);
	}
}
