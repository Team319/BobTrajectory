package com.team319;

import com.team254.lib.trajectory.WaypointSequence;
import com.team319.trajectory.BobPath;
import com.team319.trajectory.BobPathGenerator;
import com.team319.trajectory.SrxTranslatorConfig;
/**
 * Forked from 254's 2014 Trajectory library just a comment to make a change
 * 
 * @author Jared341
 * @author ttremblay
 */
public class Main {

	public static void main(String[] args) {
		
		final double ROBOT_LENGTH = 33;
		//SrxTranslator translator = new SrxTranslator();
		SrxTranslatorConfig standardConfig = new SrxTranslatorConfig();
		
		//Standard configs between all trajectories
		standardConfig.name = "StandardConfig";
		standardConfig.dt = .01;
		standardConfig.max_acc = 3.5;
		standardConfig.max_jerk = 60.0;
		standardConfig.max_vel = 4.0; 
		standardConfig.wheelbase_width_feet = inFeet(27);
		standardConfig.wheel_dia_inches = 5;
		standardConfig.scale_factor = 1.43; 
		standardConfig.encoder_ticks_per_rev = 480;
		
		SrxTranslatorConfig slowConfig = new SrxTranslatorConfig(standardConfig);
		slowConfig.max_vel = 3.0;
		
		// X IS YOUR FORWARD MOVEMENT AND Y IS YOUR SIDEWAYS MOVEMENT
		
		BobPath scalingCalibration = new BobPath(standardConfig, "scaling_calibration", 1);
		scalingCalibration.addWaypoint(new WaypointSequence.Waypoint(0.0, 0.0, 0.0));
		scalingCalibration.addWaypoint(new WaypointSequence.Waypoint(5.0, 0.0, Math.toRadians(0.0)));
		
		BobPath turningCalibration = new BobPath(slowConfig, "turning_calibration", 1);
		turningCalibration.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		turningCalibration.addWaypoint(new WaypointSequence.Waypoint(4.0, 4.0, Math.toRadians(89.99)));
		
		BobPath centerSwitch = new BobPath(standardConfig, "CenterSwitch", 1);
		centerSwitch.addWaypoint(new WaypointSequence.Waypoint(inFeet(ROBOT_LENGTH), 0, 0));
		centerSwitch.addWaypoint(new WaypointSequence.Waypoint(inFeet(140), inFeet(60), 0));
		
		BobPath sameSideSwitch = new BobPath(standardConfig, "SameSideSwitch", 1);
		sameSideSwitch.addWaypoint(new WaypointSequence.Waypoint(inFeet(ROBOT_LENGTH), 0, 0));
		sameSideSwitch.addWaypoint(new WaypointSequence.Waypoint(inFeet(140), inFeet(-57), Math.toRadians(0.0)));
		
		BobPath sameSideScale = new BobPath(standardConfig, "SameSideScale", 1);
		sameSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(ROBOT_LENGTH), 0, 0));
		sameSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(300), inFeet(-40), 0));
		
		BobPath oppositeSideScale = new BobPath(slowConfig, "OppositeSideScale", 1);
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(ROBOT_LENGTH), 0, 0));
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(192), inFeet(-110), Math.toRadians(-89.99)));
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(192), inFeet(-140), Math.toRadians(-89.99)));
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(300), inFeet(-209), 0));
	

		BobPathGenerator.exportPath("Paths", scalingCalibration);
		BobPathGenerator.exportPath("Paths", turningCalibration);
		BobPathGenerator.exportPath("Paths", centerSwitch);
		BobPathGenerator.exportPath("Paths", sameSideSwitch);
		BobPathGenerator.exportPath("Paths", sameSideScale);
		BobPathGenerator.exportPath("Paths", oppositeSideScale);
			
		
		//BobPathGenerator.appendAndExportPaths("Paths", "appendedPath", false, blueHopperThenShootAutoLeftSidePt2, toAppend);
		//BobPathGenerator.appendAndExportPaths("Paths", "appendedAndFlippedPath", true, blueHopperThenShootAutoLeftSidePt2, toAppend); 
		//redGear.exportPathWithSerializer(new VelocityOnlyFileSerializer(), "Paths");
	}
	
	private static double inFeet(double inches) {
		return ((double) inches) / 12.0;
	}
}
