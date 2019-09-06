package com.team254.lib.trajectory;

import com.team319.trajectory.RobotConfig;

/**
 * Factory class for creating Trajectories.
 *
 * @author Jared341
 */
public class TrajectoryGenerator {

	///// INNER CLASSES /////
	public static class Config {

		public double dt;
		public double max_vel;
		public double max_acc;
		public double max_jerk;
	}

	public static Trajectory generate(double startVelocity, double startDistance, double finalDistance, double finalVelocity, double maxVelocity) {
		Trajectory trajectory = new Trajectory();
		double currentVelocity = startVelocity;
		double currentDistance = startDistance;
		double dt = RobotConfig.dt;

		// Add ramp up points
		while(isEnoughDistanceRemainingToRampDown(currentVelocity, finalVelocity, finalDistance - currentDistance)) {
			currentVelocity += RobotConfig.maxAcceleration * dt;
			currentVelocity = Math.min(maxVelocity, currentVelocity);
			currentDistance += currentVelocity * dt;

			Segment current = new Segment();
			current.pos = currentDistance;
			current.vel = currentVelocity;
			current.acc = RobotConfig.maxAcceleration;
			current.dt = dt;

			trajectory.getSegments().add(current);
		}

		// Add ramp down points
		while(currentVelocity > finalVelocity) {
			currentVelocity -= RobotConfig.maxAcceleration * dt;
			currentDistance += currentVelocity * dt;

			Segment current = new Segment();
			current.pos = Math.min(currentDistance, finalDistance);
			current.vel = Math.min(currentVelocity, finalVelocity);
			current.acc = -RobotConfig.maxAcceleration;
			current.dt = dt;

			trajectory.getSegments().add(current);
		}
		return trajectory;
	}

	private static boolean isEnoughDistanceRemainingToRampDown(double currentVelocity, double finalVelocity, double distanceRemaining) {
		double timeToDecellerate = (currentVelocity - finalVelocity) / RobotConfig.maxAcceleration;
		double distanceToDecellerate = (currentVelocity + finalVelocity) / 2.0 * timeToDecellerate;
		return distanceToDecellerate < distanceRemaining;
	}
}
