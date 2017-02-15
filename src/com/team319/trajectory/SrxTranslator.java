package com.team319.trajectory;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;

public class SrxTranslator {


	// Reads a Path object generated from 254's trajectory planning software and
	// creates a CombinedSrxMotionProfile from it
	public SrxTrajectory getSrxTrajectoryFromChezyPath(Path chezyPath, SrxTranslatorConfig config) {

		// create an array of points for the SRX
		double[][] leftPoints = extractSRXPointsFromChezyTrajectory(chezyPath.getPair().left, config.wheel_dia_inches,
				config.scale_factor, config.direction);

		// do it again for the right side
		double[][] rightPoints = extractSRXPointsFromChezyTrajectory(chezyPath.getPair().right, config.wheel_dia_inches,
				config.scale_factor, config.direction);

		// create the motion profile objects
		SrxMotionProfile left = new SrxMotionProfile(leftPoints.length, leftPoints);
		SrxMotionProfile right = new SrxMotionProfile(rightPoints.length, rightPoints);

		// Combine
		return new SrxTrajectory(left, right);
	}
	
	
	public SrxTrajectory combineSrxTrajectories(SrxTrajectory first, SrxTrajectory second){
		SrxTrajectory combinedTrajectory = new SrxTrajectory(first.leftProfile, first.rightProfile);
		
		return combinedTrajectory;
		
	}

	public double[][] extractSRXPointsFromChezyTrajectory(Trajectory traj, double wheelDiameterInches,
			double scaleFactor, int direction) {
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

	// convert 254's distance units of feet to SRX's distance units of encoder
	// rotations
	public double convertFeetToEncoderRotations(double feet, double wheelDiameterInches, double scaleFactor) {
		// convert feet to wheel rotations using the circumference of the wheel
		double wheelRotations = feet * 12 / (wheelDiameterInches * Math.PI);

		// convert wheel rotations to encoder rotations using the recuction
		// between the two
		double encoderRotations = wheelRotations * scaleFactor;
		return encoderRotations;
	}
	
}
