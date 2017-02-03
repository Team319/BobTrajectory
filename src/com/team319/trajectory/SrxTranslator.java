package com.team319.trajectory;


import org.json.simple.JSONObject;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectoryGenerator;

public class SrxTranslator {
	
	public static class Config extends TrajectoryGenerator.Config{
		public String name;
	    public double wheelbase_width_feet;
	    public double wheel_dia_inches;
	    public double scale_factor;  //used for reductions between encoder and wheel
	    public int direction = 1;  //1 = forward, -1 = backward
	    
	    public JSONObject toJson(){
	    	JSONObject obj = new JSONObject();
	    	obj.put("name", name);
	    	obj.put("wheelbase_width_feet", wheelbase_width_feet);
	    	obj.put("wheel_dia_inches", wheel_dia_inches);
	    	obj.put("scale_factor", scale_factor);
	    	obj.put("direction", direction);
	    	obj.put("dt", dt);
	    	obj.put("max_vel", max_vel);
	    	obj.put("max_acc", max_acc);
	    	obj.put("max_jerk", max_jerk);
	    	
	    	return obj;
	    }
	}

	// Reads a Path object generated from 254's trajectory planning software and
	// creates a CombinedSrxMotionProfile from it
	public SrxTrajectory getSrxTrajectoryFromChezyPath(Path chezyPath, SrxTranslator.Config config) {

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
