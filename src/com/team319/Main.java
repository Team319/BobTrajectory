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
	
	private static SrxTranslatorConfig standardConfig = new SrxTranslatorConfig();
	private static final double ROBOT_LENGTH = 33;

	public static void main(String[] args) {
		//Standard configs between all trajectories
		standardConfig.name = "StandardConfig";
		standardConfig.dt = .01;
		standardConfig.max_acc = 5;
		standardConfig.max_jerk = 60.0;
		standardConfig.max_vel = 7.0; 
		standardConfig.wheelbase_width_feet = inInches(32);
		standardConfig.wheel_dia_inches = 5;
		standardConfig.scale_factor = 1.33; 
		standardConfig.encoder_ticks_per_rev = 480;

		generateCenterSwitch();
		generateSameSideSwitch();
		generateSameSideScale();
		generateOppositeSideScale();
		generateBaseline();
		generateConfig();
	}
	
	private static void generateCenterSwitch() {
		BobPath centerSwitch = new BobPath(standardConfig, "CenterSwitch", 1);
		centerSwitch.addWaypoint(new WaypointSequence.Waypoint(inInches(ROBOT_LENGTH), 0, 0));
		centerSwitch.addWaypoint(new WaypointSequence.Waypoint(inInches(140), inInches(60), 0));
		
		BobPathGenerator.exportPathToJavaFile("Paths", centerSwitch);
	}
	
	private static void generateSameSideSwitch() {
		SrxTranslatorConfig slowConfig = new SrxTranslatorConfig(standardConfig);
		slowConfig.max_vel = 4.0;
		slowConfig.max_acc = 2;
		
//		BobPath sameSideSwitch = new BobPath(standardConfig, "SameSideSwitch", 1);
//		sameSideSwitch.addWaypoint(new WaypointSequence.Waypoint(inInches(ROBOT_LENGTH), 0, 0));
//		sameSideSwitch.addWaypoint(new WaypointSequence.Waypoint(inInches(140), inInches(-57), Math.toRadians(0.0)));
		
		BobPath sameSideSwitch = new BobPath(slowConfig, "SameSideSwitch", 1);
		sameSideSwitch.addWaypoint(new WaypointSequence.Waypoint(inInches(ROBOT_LENGTH), 0, 0));
//		sameSideSwitch.addWaypoint(new WaypointSequence.Waypoint(inInches(145), inInches(10), 0));
//		sameSideSwitch.addWaypoint(new WaypointSequence.Waypoint(inInches(185), inInches(-22), Math.toRadians(-89)));
		sameSideSwitch.addWaypoint(new WaypointSequence.Waypoint(inInches(185), inInches(0), 0));
		
		SrxTranslatorConfig turnConfig = new SrxTranslatorConfig(standardConfig);
		turnConfig.max_vel = 4.0;
		turnConfig.max_acc = 3;
		
		BobPath sameSideSwitchPart1point3 = new BobPath(turnConfig, "SameSideSwitchPart1point3", 1);
		sameSideSwitchPart1point3.addWaypoint(new WaypointSequence.Waypoint(inInches(0), inInches(0), Math.toRadians(0)));
		sameSideSwitchPart1point3.addWaypoint(new WaypointSequence.Waypoint(inDegrees(90), 0, 0));
		
		SrxTranslatorConfig straightConfig = new SrxTranslatorConfig(standardConfig);
		straightConfig.max_vel = 5.0;
		straightConfig.max_acc = 8;
		
		BobPath sameSideSwitchPart1point6 = new BobPath(straightConfig, "SameSideSwitchPart1point6", 1);
		sameSideSwitchPart1point6.addWaypoint(new WaypointSequence.Waypoint(inInches(0), inInches(0), Math.toRadians(0)));
		sameSideSwitchPart1point6.addWaypoint(new WaypointSequence.Waypoint(inInches(22), inInches(0), 0));
		
		BobPath sameSideSwitchPart2 = new BobPath(turnConfig, "SameSideSwitchPart2", 1);
		sameSideSwitchPart2.addWaypoint(new WaypointSequence.Waypoint(inInches(0), inInches(0), Math.toRadians(0)));
		sameSideSwitchPart2.addWaypoint(new WaypointSequence.Waypoint(inDegrees(90), 0, 0));
		
		BobPath sameSideSwitchPart3 = new BobPath(straightConfig, "SameSideSwitchPart3", -1);
		sameSideSwitchPart3.addWaypoint(new WaypointSequence.Waypoint(inInches(0), inInches(0), Math.toRadians(0)));
		sameSideSwitchPart3.addWaypoint(new WaypointSequence.Waypoint(inInches(84), inInches(0), 0));
		
		BobPath sameSideSwitchPart4 = new BobPath(straightConfig, "SameSideSwitchPart4", 1);
		sameSideSwitchPart4.addWaypoint(new WaypointSequence.Waypoint(inInches(84), inInches(0), 0));
		sameSideSwitchPart4.addWaypoint(new WaypointSequence.Waypoint(inInches(41), inInches(-16), Math.toRadians(45)));
		
		
		BobPathGenerator.exportPathToJavaFile("Paths", sameSideSwitch);
		BobPathGenerator.exportPathToJavaFile("Paths", sameSideSwitchPart1point3, 1);
		BobPathGenerator.exportPathToJavaFile("Paths", sameSideSwitchPart1point6);
		BobPathGenerator.exportPathToJavaFile("Paths", sameSideSwitchPart2, 1);
		BobPathGenerator.exportPathToJavaFile("Paths", sameSideSwitchPart3);
		BobPathGenerator.exportPathToJavaFile("Paths", sameSideSwitchPart4);
	}
	
	private static void generateSameSideScale() {
		SrxTranslatorConfig sameSideScaleConfig = new SrxTranslatorConfig(standardConfig);
		sameSideScaleConfig.max_vel = 3.0;
		sameSideScaleConfig.max_acc = 4;
		
		SrxTranslatorConfig firstPathConfig = new SrxTranslatorConfig(standardConfig);
		firstPathConfig.max_vel = 10;
		firstPathConfig.max_acc = 5;
		
		SrxTranslatorConfig slowConfig = new SrxTranslatorConfig(standardConfig);
		slowConfig.max_vel = 2.0;
		slowConfig.max_acc = 3;
		
		SrxTranslatorConfig turnConfig = new SrxTranslatorConfig(standardConfig);
		turnConfig.max_vel = 4.0;
		turnConfig.max_acc = 3;
		
		SrxTranslatorConfig turnConfigFast = new SrxTranslatorConfig(standardConfig);
		turnConfig.max_vel = 5.0;
		turnConfig.max_acc = 4;
		
		BobPath sameSideScale = new BobPath(firstPathConfig, "SameSideScale", 1);
		sameSideScale.addWaypoint(new WaypointSequence.Waypoint(inInches(ROBOT_LENGTH), 0, 0));
		sameSideScale.addWaypoint(new WaypointSequence.Waypoint(inInches(200), inInches(0), 0));
		sameSideScale.addWaypoint(new WaypointSequence.Waypoint(inInches(280), inInches(-15), Math.toRadians(-25)));
		
		BobPath sameSideScalePart2 = new BobPath(turnConfig, "SameSideScalePart2", 1);
		sameSideScalePart2.addWaypoint(0, 0, 0);
		sameSideScalePart2.addWaypoint(inDegrees(148), 0, 0);
		
		BobPath sameSideScalePart3 = new BobPath(sameSideScaleConfig, "SameSideScalePart3", 1);
		sameSideScalePart3.addWaypoint(new WaypointSequence.Waypoint(inInches(0), inInches(0), Math.toRadians(0)));
		sameSideScalePart3.addWaypoint(new WaypointSequence.Waypoint(inInches(50), inInches(0), Math.toRadians(0)));
		
		BobPath sameSideScalePart4 = new BobPath(turnConfigFast, "SameSideScalePart4", 1);
		sameSideScalePart4.addWaypoint(0, 0, 0);
		sameSideScalePart4.addWaypoint(inDegrees(165), 0, 0);
		
		BobPath sameSideScalePart5 = new BobPath(sameSideScaleConfig, "SameSideScalePart5", 1);
		sameSideScalePart5.addWaypoint(new WaypointSequence.Waypoint(inInches(0), inInches(0), Math.toRadians(0)));
		sameSideScalePart5.addWaypoint(new WaypointSequence.Waypoint(inInches(47), inInches(0), Math.toRadians(0)));
		
		BobPathGenerator.exportPathToJavaFile("Paths", sameSideScale);
		BobPathGenerator.exportPathToJavaFile("Paths", sameSideScalePart2, 1);
		BobPathGenerator.exportPathToJavaFile("Paths", sameSideScalePart3);
		BobPathGenerator.exportPathToJavaFile("Paths", sameSideScalePart4, -1);
		BobPathGenerator.exportPathToJavaFile("Paths", sameSideScalePart5);
	}
	
	private static void generateOppositeSideScale() {
		SrxTranslatorConfig oppositeSideScaleConfig = new SrxTranslatorConfig(standardConfig);
		oppositeSideScaleConfig.max_vel = 5.0;
		oppositeSideScaleConfig.max_acc = 3;
		
		BobPath oppositeSideScale = new BobPath(oppositeSideScaleConfig, "OppositeSideScale", 1);
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inInches(ROBOT_LENGTH), 0, 0));
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inInches(160), inInches(18), 0));
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inInches(235), inInches(-65), Math.toRadians(-89.99)));
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inInches(235), inInches(-145), Math.toRadians(-89.99)));
		oppositeSideScale.addWaypoint(new WaypointSequence.Waypoint(inInches(275), inInches(-200), Math.toRadians(0.00)));
		
		SrxTranslatorConfig turnConfig = new SrxTranslatorConfig(standardConfig);
		turnConfig.max_vel = 4.0;
		turnConfig.max_acc = 3;
		
		BobPath oppositeSideScalePart2 = new BobPath(turnConfig, "OppositeSideScalePart2", 1);
		oppositeSideScalePart2.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		oppositeSideScalePart2.addWaypoint(new WaypointSequence.Waypoint(inDegrees(171), 0, 0));
		
		BobPath oppositeSideScalePart3 = new BobPath(turnConfig, "OppositeSideScalePart3", 1);
		oppositeSideScalePart3.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		oppositeSideScalePart3.addWaypoint(new WaypointSequence.Waypoint(inInches(38), 0, 0));
		
		SrxTranslatorConfig turnConfigFast = new SrxTranslatorConfig(standardConfig);
		turnConfig.max_vel = 5.0;
		turnConfig.max_acc = 4;
		
		BobPath oppositeSideScalePart4 = new BobPath(turnConfigFast, "OppositeSideScalePart4", 1);
		oppositeSideScalePart4.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		oppositeSideScalePart4.addWaypoint(new WaypointSequence.Waypoint(inDegrees(180), 0, 0));
		
		BobPathGenerator.exportPathToJavaFile("Paths", oppositeSideScale);
		BobPathGenerator.exportPathToJavaFile("Paths", oppositeSideScalePart2, -1);
		BobPathGenerator.exportPathToJavaFile("Paths", oppositeSideScalePart3);
		BobPathGenerator.exportPathToJavaFile("Paths", oppositeSideScalePart4, -1);
	}
	
	private static void generateBaseline() {
		SrxTranslatorConfig oppositeSideScaleConfig = new SrxTranslatorConfig(standardConfig);
		oppositeSideScaleConfig.max_vel = 5.0;
		oppositeSideScaleConfig.max_acc = 3;
		
		BobPath baseline = new BobPath(oppositeSideScaleConfig, "Baseline", 1);
		baseline.addWaypoint(new WaypointSequence.Waypoint(inInches(ROBOT_LENGTH), 0, 0));
		baseline.addWaypoint(new WaypointSequence.Waypoint(inInches(160), inInches(24), 0));
		baseline.addWaypoint(new WaypointSequence.Waypoint(inInches(252), inInches(-65), Math.toRadians(-89.99)));
		baseline.addWaypoint(new WaypointSequence.Waypoint(inInches(252), inInches(-100), Math.toRadians(-89.99)));

		BobPathGenerator.exportPathToJavaFile("Paths", baseline);
	}
	
	private static void generateConfig() {
		SrxTranslatorConfig slowConfig = new SrxTranslatorConfig(standardConfig);
		slowConfig.max_vel = 2.0;
		slowConfig.max_acc = 3;
		
		BobPath scalingCalibration = new BobPath(standardConfig, "scaling_calibration", 1);
		scalingCalibration.addWaypoint(new WaypointSequence.Waypoint(0.0, 0.0, 0.0));
		scalingCalibration.addWaypoint(new WaypointSequence.Waypoint(5.0, 0.0, Math.toRadians(0.0)));
		
		BobPath turningCalibration = new BobPath(slowConfig, "turning_calibration", 1);
		turningCalibration.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		turningCalibration.addWaypoint(new WaypointSequence.Waypoint(4.0, 4.0, Math.toRadians(89.99)));

		BobPathGenerator.exportPathToJavaFile("Paths", scalingCalibration);
		BobPathGenerator.exportPathToJavaFile("Paths", turningCalibration);
	}
	
	private static double inInches(double inches) {
		return ((double) inches) / 12.0;
	}
	
	private static double inDegrees(double degree) {
		return inInches(90) * degree / 360.0;
	}
}
