package com.team254.lib.trajectory;

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

	public static Trajectory generate(Config config, double start_vel, double start_pos,
			double goal_pos, double goal_vel, double maxVelocity) {
		Trajectory traj;
//		double adjusted_max_vel = maxVelocity;
		double distanceAtMaxVelocity = ((goal_vel * goal_vel) - (start_vel * start_vel)) / 
				(2 * config.max_acc * config.max_acc)  + (goal_pos - start_pos) / 2;
//		double maxPossibleVelocity = config.max_acc / 2 * Math.sqrt(distanceAtMaxVelocity + (start_vel * start_vel) / (config.max_acc * config.max_acc));
		double maxPossibleVelocity = start_vel + Math.sqrt(2 * config.max_acc * distanceAtMaxVelocity);
		double adjusted_max_vel = Math.min(maxVelocity, maxPossibleVelocity);
		//		}
		double t_rampup = (adjusted_max_vel - start_vel) / config.max_acc;
		double x_rampup = (adjusted_max_vel + start_vel) / 2.0 * t_rampup;
		if (x_rampup > goal_pos - start_pos) {
			System.out.println("CAN'T REACH FINAL VELOCITY");
		}
		double t_rampdown = (adjusted_max_vel - goal_vel) / config.max_acc;
		double x_rampdown = (adjusted_max_vel + goal_vel) / 2.0 * t_rampdown;
		double x_cruise = goal_pos - start_pos - x_rampdown - x_rampup;
		double t_cruise = x_cruise / adjusted_max_vel;

		int time = (int) ((t_rampup + t_rampdown + t_cruise) / config.dt);
		double impulse = (t_rampup + t_cruise) / config.dt;
		traj = generateValues(config.dt, start_vel, start_pos, adjusted_max_vel, config.max_acc, impulse, time, goal_pos);

		return traj;
	}

	private static Trajectory generateValues(double dt, double start_vel, double start_pos,
			double max_vel, double max_accel, double total_impulse, int time, double goalPosition) {
		if (time <= 0) {
			return null;
		}

		Trajectory traj = new Trajectory(time);
		double currentPosition = start_pos;
		double currentVelocity = start_vel;
		double currentAcceleration = 0;
		for (int i = 0; i < time; i++) {
			Trajectory.Segment current = new Trajectory.Segment();
			current.pos = currentPosition;
			current.vel = currentVelocity;
			current.acc = currentAcceleration;
			current.dt = dt;

			traj.setSegment(i, current);

			if (i >= total_impulse) {
				currentVelocity -= max_accel * dt;
				currentAcceleration = -max_accel;
			} else {
				currentVelocity += max_accel * dt;
				currentAcceleration = max_accel;
			}

			if (currentVelocity >= max_vel) {
				currentVelocity = max_vel;
				currentAcceleration = 0;
			}
			currentPosition += currentVelocity * dt;
		}
//		System.out.println("Missing distance: " + (goalPosition - currentPosition));
		return traj;
	}
}
