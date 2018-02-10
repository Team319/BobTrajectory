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
		standardConfig.max_acc = 8;
		standardConfig.max_jerk = 60.0;
		standardConfig.max_vel = 8.0; 
		standardConfig.wheelbase_width_feet = inFeet(27);
		standardConfig.wheel_dia_inches = 5;
		standardConfig.scale_factor = 1.43; 
		standardConfig.encoder_ticks_per_rev = 480;
		
		SrxTranslatorConfig slowConfig = new SrxTranslatorConfig(standardConfig);
		slowConfig.max_vel = 3.0;
		
		SrxTranslatorConfig oppositeSideScaleConfig = new SrxTranslatorConfig(standardConfig);
		oppositeSideScaleConfig.max_vel = 6.0;
		
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
		sameSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(195), 0, 0));
		sameSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(285), inFeet(-40), Math.toRadians(-45)));
		
		BobPath sameSideScalePart2 = new BobPath(slowConfig, "SameSideScalePart2", -1);
		sameSideScalePart2.addWaypoint(new WaypointSequence.Waypoint(inFeet(285), inFeet(-40), Math.toRadians(-45)));
		sameSideScalePart2.addWaypoint(new WaypointSequence.Waypoint(inFeet(285), inFeet(0), Math.toRadians(-134.99)));
		
		BobPath sameSideScalePart3 = new BobPath(standardConfig, "SameSideScalePart3", 1);
		sameSideScalePart3.addWaypoint(new WaypointSequence.Waypoint(inFeet(285), inFeet(0), Math.toRadians(-134.99)));
		sameSideScalePart3.addWaypoint(new WaypointSequence.Waypoint(inFeet(240), inFeet(-40), Math.toRadians(-140)));
		
		BobPath sameSideScalePart4 = new BobPath(standardConfig, "SameSideScalePart4", -1);
		sameSideScalePart4.addWaypoint(new WaypointSequence.Waypoint(inFeet(240), inFeet(-40), Math.toRadians(-140)));
		sameSideScalePart4.addWaypoint(new WaypointSequence.Waypoint(inFeet(265), inFeet(0), Math.toRadians(-80.01)));
		
		BobPath sameSideScalePart5 = new BobPath(standardConfig, "SameSideScalePart5", 1);
		sameSideScalePart5.addWaypoint(new WaypointSequence.Waypoint(inFeet(265), inFeet(0), Math.toRadians(-80.01)));
		sameSideScalePart5.addWaypoint(new WaypointSequence.Waypoint(inFeet(285), inFeet(-40), Math.toRadians(-45)));
		
		BobPath oppositeSideScale = new BobPath(oppositeSideScaleConfig, "OppositeSideScale", 1);
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(ROBOT_LENGTH), 0, 0));
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(160), inFeet(24), 0));
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(245), inFeet(-65), Math.toRadians(-89.99)));
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(238), inFeet(-130), Math.toRadians(-89.99)));
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inFeet(295), inFeet(-205), Math.toRadians(0.00)));
		
		BobPath oppositeSideScalePart2 = new BobPath(oppositeSideScaleConfig, "OppositeSideScalePart2", -1);
		oppositeSideScalePart2.addWaypoint(new WaypointSequence.Waypoint(inFeet(295), inFeet(-205), Math.toRadians(0.00)));
		oppositeSideScalePart2.addWaypoint(new WaypointSequence.Waypoint(inFeet(235), inFeet(-270), Math.toRadians(89.99)));
	

		BobPathGenerator.exportPath("Paths", scalingCalibration);
		BobPathGenerator.exportPath("Paths", turningCalibration);
		BobPathGenerator.exportPath("Paths", centerSwitch);
		BobPathGenerator.exportPath("Paths", sameSideSwitch);
		BobPathGenerator.exportPath("Paths", sameSideScale);
		BobPathGenerator.exportPath("Paths", sameSideScalePart2);
		BobPathGenerator.exportPath("Paths", sameSideScalePart3);
		BobPathGenerator.exportPath("Paths", sameSideScalePart4);
		BobPathGenerator.exportPath("Paths", sameSideScalePart5);
		BobPathGenerator.exportPath("Paths", oppositeSideScale);
		BobPathGenerator.exportPath("Paths", oppositeSideScalePart2);
			
		
		//BobPathGenerator.appendAndExportPaths("Paths", "appendedPath", false, blueHopperThenShootAutoLeftSidePt2, toAppend);
		//BobPathGenerator.appendAndExportPaths("Paths", "appendedAndFlippedPath", true, blueHopperThenShootAutoLeftSidePt2, toAppend); 
		//redGear.exportPathWithSerializer(new VelocityOnlyFileSerializer(), "Paths");
	}
	
	private static double inFeet(double inches) {
		return ((double) inches) / 12.0;
	}
}
