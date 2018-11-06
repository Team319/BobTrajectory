package com.team319.trajectory;

import java.math.BigDecimal;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;

public class SrxTranslator {

	// Reads a Path object generated from 254's trajectory planning software and
	// creates a CombinedSrxMotionProfile from it
	public SrxTrajectory getSrxTrajectoryFromChezyPath(Path chezyPath, SrxTranslatorConfig config) {

		// create an array of points for the SRX
		double[][] centerPoints = extractSRXPointsFromChezyTrajectory(chezyPath.getTrajectory(),
				config.wheel_dia_inches, config.scale_factor, config.encoder_ticks_per_rev);

		// create the motion profile object
		SrxMotionProfile center = new SrxMotionProfile(centerPoints.length, centerPoints);

		// Combine
		return new SrxTrajectory(center);

	}

	public double[][] extractSRXPointsFromChezyTrajectory(Trajectory traj, double wheelDiameterInches,
			double scaleFactor, int encoderTicksPerRev) {
		// create an array of points for the SRX
		double[][] points = new double[traj.getSegments().length][4];
		double lastHeading = 0;
		double continuousHeading = 0;
		// Fill that array
		for (int i = 0; i < traj.getSegments().length; i++) {
			// translate from feet to encoder ticks
			points[i][0] = convertFeetToEncoderTicks(traj.getSegment(i).pos, wheelDiameterInches, scaleFactor,
					encoderTicksPerRev);

			// translate from fps to rpm
			points[i][1] = convertFpsToEncoderTicksPer100ms(traj.getSegment(i).vel, wheelDiameterInches, scaleFactor,
					encoderTicksPerRev);

			// translate from seconds to milliseconds
			points[i][2] = traj.getSegment(i).dt * 1000;
			double nextHeading = new BigDecimal(Math.toDegrees(traj.getSegment(i).heading)).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
			if (nextHeading > 180) {
				nextHeading -= 360;
			}
			if (i != 0) {
				double headingDifference = nextHeading - lastHeading;
				if (headingDifference >= 360) {
					headingDifference -= 360;
				}
				continuousHeading += headingDifference;
			}
			else {
				if (nextHeading >= 360) {
					nextHeading -= 360;
				}
				continuousHeading = nextHeading;
			}
			points[i][3] = continuousHeading;
			lastHeading = continuousHeading;
		}
		return points;
	}

	public double[][] extractSRXPointsFromChezyTrajectory(Trajectory traj, double wheelDiameterInches,
			double scaleFactor) {
		// create an array of points for the SRX
		double[][] points = new double[traj.getSegments().length][3];

		// Fill that array
		for (int i = 0; i < traj.getSegments().length; i++) {

			// translate from feet to rotations
			points[i][0] = convertFeetToEncoderRotations(traj.getSegment(i).pos, wheelDiameterInches, scaleFactor);

			// translate from fps to rpm
			points[i][1] = convertFpsToEncoderRpm(traj.getSegment(i).vel, wheelDiameterInches, scaleFactor);

			// translate from seconds to milliseconds
			points[i][2] = traj.getSegment(i).dt * 1000;
		}

		return points;
	}

	public double convertFpsToEncoderRpm(double fps, double wheelDiameterInches, double scaleFactor) {
		// feet per minute
		double fpm = fps * 60;
		// wheel rpm
		double rpm = fpm * 12 / (wheelDiameterInches * Math.PI);
		// encoder rpm
		double encoderRpm = rpm * scaleFactor;

		return encoderRpm;
	}

	public double convertFpsToEncoderTicksPer100ms(double fps, double wheelDiameterInches, double scaleFactor,
			int encoderTicksPerRev) {
		// feet per minute
		double fpm = fps * 60;
		// wheel rpm
		double rpm = fpm * 12 / (wheelDiameterInches * Math.PI);
		// encoder rpm
		double encoderRpm = rpm * scaleFactor;

		double ticksPer100ms = encoderRpm * encoderTicksPerRev / 600;

		return ticksPer100ms;
	}

	// convert 254's distance units of feet to SRX's distance units of encoder
	// rotations
	public double convertFeetToEncoderRotations(double feet, double wheelDiameterInches, double scaleFactor) {
		// convert feet to wheel rotations using the circumference of the wheel
		double wheelRotations = feet * 12 / (wheelDiameterInches * Math.PI);

		// convert wheel rotations to encoder rotations using the reduction
		// between the two
		double encoderRotations = wheelRotations * scaleFactor;
		return encoderRotations;
	}

	// convert 254's distance units of feet to SRX's distance units of encoder
	// rotations
	public double convertFeetToEncoderTicks(double feet, double wheelDiameterInches, double scaleFactor,
			int encoderTicksPerRev) {
		// convert feet to wheel rotations using the circumference of the wheel
		double wheelRotations = feet * 12 / (wheelDiameterInches * Math.PI);

		// convert wheel rotations to encoder rotations using the reduction
		// between the two
		double encoderRotations = wheelRotations * scaleFactor;
		double encoderTicks = encoderRotations * encoderTicksPerRev;
		return encoderTicks;
	}
}
