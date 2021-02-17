package com.team254.lib.trajectory;

import java.util.ArrayList;
import java.util.List;

import com.team319.trajectory.RobotConfig;

public class TrajectoryGenerator {

	public static Trajectory generate(double startVelocity, double startPosition, double finalPosition, double finalVelocity, double maxVelocity) {
		double dt = RobotConfig.dt;
		
		List<Segment> segments = new ArrayList<>();
		double currentVelocity = startVelocity;
		double currentPosition = startPosition;
		
		double nextVelocity = startVelocity + RobotConfig.maxAcceleration * dt;
		double nextPosition = startPosition + (startVelocity + nextVelocity) / 2 * dt;
		
		// Add ramp up points
		while(isEnoughDistanceRemainingToRampDown(nextVelocity, finalVelocity, finalPosition - nextPosition)) {
			currentVelocity = nextVelocity;
			currentPosition = nextPosition;
	
			Segment current = new Segment();
			current.pos = currentPosition;
			current.vel = currentVelocity;
			current.acc = RobotConfig.maxAcceleration;
			current.dt = dt;

			segments.add(current);

			nextVelocity = currentVelocity + RobotConfig.maxAcceleration * dt;
			nextVelocity = Math.min(maxVelocity, nextVelocity);
			nextPosition = currentPosition +  (currentVelocity + nextVelocity) / 2 * dt;
		} 

		double remainingDistance = finalPosition - currentPosition;
		double rampDownAcceleration = (finalVelocity * finalVelocity - currentVelocity * currentVelocity) / 2.0 / remainingDistance;

		nextVelocity = currentVelocity + rampDownAcceleration * dt;
		nextPosition = currentPosition +  (currentVelocity + nextVelocity) / 2 * dt;

				// add ramp down points
		while(rampDownAcceleration != 0 && nextVelocity >= finalVelocity) {
			currentVelocity = nextVelocity;
			currentPosition = nextPosition;
	
			Segment current = new Segment();
			current.pos = currentPosition;
			current.vel = currentVelocity;
			current.acc = rampDownAcceleration;
			current.dt = dt;

			segments.add(current);

			nextVelocity = currentVelocity + rampDownAcceleration * dt;
			nextPosition = currentPosition + (currentVelocity + nextVelocity) / 2 * dt;
		} 
		
		// Segment finalSegment = new Segment();
		// finalSegment.pos = finalPosition;
		// finalSegment.vel = finalVelocity;
		// finalSegment.acc = -RobotConfig.maxAcceleration;
		// finalSegment.dt = dt;
		// segments.add(finalSegment);

		System.out.println("Path distance - " + currentPosition);
		System.out.println("Spline distance - " + finalPosition);
		Trajectory trajectory = new Trajectory();
		trajectory.getSegments().addAll(segments);
		// System.out.println("Path error = " + (finalDistance - nextDistance) * 12);
		// outputSegments(trajectory);
		return trajectory;
	}

	private static boolean isEnoughDistanceRemainingToRampDown(double currentVelocity, double finalVelocity, double distanceRemaining) {
		double distanceToDecellerate = getRampDownDistanceNeeded(currentVelocity, finalVelocity);
		return distanceToDecellerate < distanceRemaining;
	}

	private static double getRampDownDistanceNeeded(double currentVelocity, double finalVelocity) {
		double timeToDecellerate = (currentVelocity - finalVelocity) / RobotConfig.maxAcceleration;
		return (currentVelocity + finalVelocity) / 2.0 * timeToDecellerate;
	}

	public static void outputSegments(Trajectory trajectory) {
		for (Segment segment : trajectory.getSegments()) {
			StringBuilder segmentOutput = new StringBuilder();
			segmentOutput.append(segment.x).append(",");
			segmentOutput.append(segment.y).append(",");
			segmentOutput.append(segment.pos).append(",");
			segmentOutput.append(segment.vel).append(",");
			segmentOutput.append(segment.acc);
			System.out.println(segmentOutput.toString());
		}
	}
}
