package com.team319;

import com.team254.lib.trajectory.WaypointSequence.Waypoint;
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

	public static double robotTotalWidthFeet = 33.0 / 12.0;
	public static double robotTotalLengthFeet = 39.0 / 12.0;

	public static Waypoint rightSideStartingWaypoint = new Waypoint(robotTotalLengthFeet / 2, 45.5 / 12.0, 0);
	public static Waypoint leftSideStartingWaypoint = new Waypoint(robotTotalLengthFeet / 2, 277.8 / 12.0, 0);
	public static Waypoint centerStartingWaypoint = new Waypoint(robotTotalLengthFeet / 2, 157.0 / 12.0, 0);
	
	public static SrxTranslatorConfig powerUpConfig;
	public static SrxTranslatorConfig switchConfig;
	public static SrxTranslatorConfig longDistanceConfig;

	public static void main(String[] args) {

		powerUpConfig = new SrxTranslatorConfig();
		powerUpConfig.name = "powerUpConfig";
		powerUpConfig.dt = .01;
		powerUpConfig.max_acc = 8.0;
		powerUpConfig.max_jerk = 60.0;
		powerUpConfig.max_vel = 10.0; // 8.0
		powerUpConfig.wheelbase_width_feet = 23.5 / 12.0;// 23.5, then 29.5, 35.5
		powerUpConfig.wheel_dia_inches = 6.0;
		powerUpConfig.scale_factor = 7.618; //north shore value is 5.685
		powerUpConfig.encoder_ticks_per_rev = 4096;
		powerUpConfig.robotLength = 39;
		powerUpConfig.robotWidth = 33;
		powerUpConfig.highGear = true;

		switchConfig = new SrxTranslatorConfig(powerUpConfig);
		switchConfig.max_vel = 6.0;
		
		longDistanceConfig = new SrxTranslatorConfig(switchConfig);
		longDistanceConfig.max_vel = 8.0;

		//generateMisc(powerUpConfig);
		//generateCenterSwitch(switchConfig);
		generateRightSide(powerUpConfig);
		generateLeftSide(powerUpConfig);
		
		copyArcsToRobotCode();
		//copyPathsToRobotCode();
	}

	private static void generateMisc(SrxTranslatorConfig config) {
		// --------------------MISC. AUTOS----------//

		BobPath ThreeFeet = new BobPath(config, "ThreeFeet", 1);
		ThreeFeet.addWaypoint(0.0, 0.0, 0.0);
		ThreeFeet.addWaypoint(3.0, 0.0, 0.0);

		BobPath OneFoot = new BobPath(config, "OneFoot", 1);
		OneFoot.addWaypoint(0.0, 0.0, 0.0);
		OneFoot.addWaypoint(1.0, 0.0, 0.0);

		BobPath FiveFeetAndTurn = new BobPath(config, "FiveFeetAndTurn", 1);
		FiveFeetAndTurn.addWaypoint(0.0, 0.0, 0.0);
		FiveFeetAndTurn.addWaypoint(5.0, 5.0, 89.99);

		BobPath CrossTheLine = new BobPath(config, "CrossTheLine", 1);
		CrossTheLine.addWaypoint(0.0, 0.0, 0.0);
		CrossTheLine.addWaypoint(10.0, 0.0, 0.0);

		BobPath TestSTurnAuto = new BobPath(config, "TestSTurnAuto", 1);
		TestSTurnAuto.addWaypoint(0.0, 0.0, 0.0);
		TestSTurnAuto.addWaypoint(8.375, 5.5, 0.0);

		BobPath FifteenFeet = new BobPath(config, "FifteenFeet", 1);
		FifteenFeet.addWaypoint(0.0, 0.0, 0.0);
		FifteenFeet.addWaypoint(15.0, 0.0, 0.0);

		BobPath FifteenFeetReverse = new BobPath(config, "FifteenFeetReverse", -1);
		FifteenFeetReverse.addWaypoint(0.0, 0.0, 0.0);
		FifteenFeetReverse.addWaypoint(15.0, 0.0, 0.0);

		BobPath BackwardsThreeFeet = new BobPath(config, "BackwardsThreeFeet", -1);
		BackwardsThreeFeet.addWaypoint(0.0, 0.0, 0.0);
		BackwardsThreeFeet.addWaypoint(3.0, 0.0, 0.0);
		
		BobPath TuningTestAuto = new BobPath(config, "TuningTestAuto", 1);
		TuningTestAuto.addWaypoint(centerStartingWaypoint);
		TuningTestAuto.addWaypointRelative(4.0, 5.0, 89.99);
		TuningTestAuto.addWaypointRelative(0.0, 10.0, 0.0);
		
		BobPath TuningTestAutoBackward = new BobPath(config, "TuningTestAutoBackward", 1);
		TuningTestAutoBackward.addWaypoint(TuningTestAuto.getLastWaypoint());
		TuningTestAutoBackward.addWaypointRelative(0.0, -10.0, 0.0);
		TuningTestAutoBackward.addWaypointRelative(-4.0, -5.0, -89.99);
		
		BobPath StraightForwardTuning = new BobPath(config, "StraightForwardTuning", -1);
		StraightForwardTuning.addWaypoint(rightSideStartingWaypoint);
		StraightForwardTuning.addWaypointRelative(223.0/12.0, 0.0, 0.0);
		
		BobPathGenerator.exportArcToJavaFile(ThreeFeet);
		BobPathGenerator.exportArcToJavaFile(OneFoot);
		BobPathGenerator.exportArcToJavaFile(FiveFeetAndTurn);
		BobPathGenerator.exportArcToJavaFile(CrossTheLine);
		BobPathGenerator.exportArcToJavaFile(TestSTurnAuto);
		BobPathGenerator.exportArcToJavaFile(FifteenFeet);
		BobPathGenerator.exportArcToJavaFile(FifteenFeetReverse);
		BobPathGenerator.exportArcToJavaFile(BackwardsThreeFeet);
		BobPathGenerator.exportArcToJavaFile(TuningTestAuto);
		BobPathGenerator.exportArcToJavaFile(TuningTestAutoBackward);
		BobPathGenerator.exportArcToJavaFile(StraightForwardTuning);
		
	}

	private static void generateCenterSwitch(SrxTranslatorConfig config) {

		// --------------------RIGHT SIDE AUTOS----------//
		BobPath CenterToRightSwitch = new BobPath(config, "CenterToRightSwitch", 1); // 3 seconds
		CenterToRightSwitch.addWaypoint(centerStartingWaypoint);
		CenterToRightSwitch.addWaypointRelative(8.375, -4.0, 0.0);

		BobPath CenterToRightSwitchPt2 = new BobPath(config, "CenterToRightSwitchPt2", -1); // 4 seconds
		CenterToRightSwitchPt2.addWaypoint(CenterToRightSwitch.getLastWaypoint());
		CenterToRightSwitchPt2.addWaypointRelative(-6.375, 4.0, 0.0);// 45

		BobPath CenterToRightSwitchPt3 = new BobPath(config, "CenterToRightSwitchPt3", 1);
		CenterToRightSwitchPt3.addWaypoint(CenterToRightSwitchPt2.getLastWaypoint());
		CenterToRightSwitchPt3.addWaypointRelative(2.5, 0.0, 0.0);

		BobPath CenterToRightSwitchPt4 = new BobPath(config, "CenterToRightSwitchPt4", -1);
		CenterToRightSwitchPt4.addWaypoint(CenterToRightSwitchPt3.getLastWaypoint());
		CenterToRightSwitchPt4.addWaypointRelative(-4.19, 0.0, 0.0);

		BobPath CenterToRightSwitchPt5 = new BobPath(config, "CenterToRightSwitchPt5", 1);
		CenterToRightSwitchPt5.addWaypoint(CenterToRightSwitchPt4.getLastWaypoint());
		CenterToRightSwitchPt5.addWaypointRelative(6.375, -4.0, 0.0);
		
		BobPath CenterToRightScalePt1 = new BobPath(config, "CenterToRightScalePt1", -1);
		CenterToRightScalePt1.addWaypoint(CenterToRightSwitchPt3.getLastWaypoint());
		CenterToRightScalePt1.addWaypointRelative(-4.0, 1.0, -30.0);
		
		BobPath CenterToRightScalePt2 = new BobPath(config, "CenterToRightScalePt2", 1);
		CenterToRightScalePt2.addWaypoint(CenterToRightScalePt1.getLastWaypoint());
		CenterToRightScalePt2.addWaypointRelative(14.0, -4.0, 45.0);
		
		BobPath CenterToRightScaleReverse = new BobPath(config, "CenterToRightScaleReverse", -1);
		CenterToRightScaleReverse.addWaypoint(CenterToRightSwitchPt3.getLastWaypoint());
		CenterToRightScaleReverse.addWaypointRelative(-3.0, -4.5, -85.0);
		CenterToRightScaleReverse.addWaypointRelative(3.0, -4.5, -85.0);
		CenterToRightScaleReverse.addWaypointRelative(5.0, 0.0, 0.0);

		// --------------------LEFT SIDE AUTOS----------//

		BobPath CenterToLeftSwitch = new BobPath(config, "CenterToLeftSwitch", 1);
		CenterToLeftSwitch.addWaypoint(centerStartingWaypoint);
		CenterToLeftSwitch.addWaypointRelative(8.375, 5.0, 0.0);

		BobPath CenterToLeftSwitchPt2 = new BobPath(config, "CenterToLeftSwitchPt2", -1);
		CenterToLeftSwitchPt2.addWaypoint(CenterToLeftSwitch.getLastWaypoint());
		CenterToLeftSwitchPt2.addWaypointRelative(-6.375, -5.0, 0.0);// -45

		BobPath CenterToLeftSwitchPt3 = new BobPath(config, "CenterToLeftSwitchPt3", 1);
		CenterToLeftSwitchPt3.addWaypoint(CenterToLeftSwitchPt2.getLastWaypoint());
		CenterToLeftSwitchPt3.addWaypointRelative(2.5, 0.0, 0.0);

		BobPath CenterToLeftSwitchPt4 = new BobPath(config, "CenterToLeftSwitchPt4", -1);
		CenterToLeftSwitchPt4.addWaypoint(CenterToLeftSwitchPt3.getLastWaypoint());
		CenterToLeftSwitchPt4.addWaypointRelative(-4.10, 0.0, 0.0);

		BobPath CenterToLeftSwitchPt5 = new BobPath(config, "CenterToLeftSwitchPt5", 1);
		CenterToLeftSwitchPt5.addWaypoint(CenterToLeftSwitchPt4.getLastWaypoint());
		CenterToLeftSwitchPt5.addWaypointRelative(6.375, 4.0, 0.0);
		
		BobPath CenterToLeftScalePt1 = new BobPath(config, "CenterToLeftScalePt1", -1);
		CenterToLeftScalePt1.addWaypoint(CenterToLeftSwitchPt3.getLastWaypoint());
		CenterToLeftScalePt1.addWaypointRelative(-4.0, -1.0, 30.0);
		
		BobPath CenterToLeftScalePt2 = new BobPath(config, "CenterToLeftScalePt2", 1);
		CenterToLeftScalePt2.addWaypoint(CenterToLeftScalePt1.getLastWaypoint());
		CenterToLeftScalePt2.addWaypointRelative(14.0, 4.0, -45.0);

		BobPathGenerator.exportArcToJavaFile(CenterToRightSwitch);
		BobPathGenerator.exportArcToJavaFile(CenterToRightSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(CenterToRightSwitchPt3);
		BobPathGenerator.exportArcToJavaFile(CenterToRightSwitchPt4);
		BobPathGenerator.exportArcToJavaFile(CenterToRightSwitchPt5);
		BobPathGenerator.exportArcToJavaFile(CenterToRightScalePt1);
		BobPathGenerator.exportArcToJavaFile(CenterToRightScalePt2);
		BobPathGenerator.exportArcToJavaFile(CenterToRightScaleReverse);
		
		BobPathGenerator.exportArcToJavaFile(CenterToLeftSwitch);
		BobPathGenerator.exportArcToJavaFile(CenterToLeftSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(CenterToLeftSwitchPt3);
		BobPathGenerator.exportArcToJavaFile(CenterToLeftSwitchPt4);
		BobPathGenerator.exportArcToJavaFile(CenterToLeftSwitchPt5);
		BobPathGenerator.exportArcToJavaFile(CenterToLeftScalePt1);
		BobPathGenerator.exportArcToJavaFile(CenterToLeftScalePt2);

	}

	private static void generateRightSide(SrxTranslatorConfig config) {

		BobPath RightWallToRightScale = new BobPath(config, "RightWallToRightScale", -1);
		RightWallToRightScale.addWaypoint(rightSideStartingWaypoint);
		RightWallToRightScale.addWaypointRelative(16.0, 1.0, 10.0);
		RightWallToRightScale.addWaypointRelative(6.5, 2.0, -10.0);
		//RightWallToRightScale.addWaypointRelative(22.5, 0.0, 0.0);

		BobPath ScaleToSwitchCubeRightSide = new BobPath(config, "ScaleToSwitchCubeRightSide", 1);
		//ScaleToSwitchCubeRightSide.addWaypoint(19.13,7.29,0.00);
		ScaleToSwitchCubeRightSide.addWaypoint(RightWallToRightScale.getLastWaypoint());
		ScaleToSwitchCubeRightSide.addWaypointRelative(-5.0, 0.5, 0.0);

		BobPath SwitchCubeToScaleRightSide = new BobPath(config, "SwitchCubeToScaleRightSide", -1);
		SwitchCubeToScaleRightSide.addWaypoint(ScaleToSwitchCubeRightSide.getLastWaypoint());
		SwitchCubeToScaleRightSide.addWaypointRelative(6.0, -1.5, 0.0);
		
		BobPath RightWallToRightSwitch = new BobPath(config, "RightWallToRightSwitch", -1);
		RightWallToRightSwitch.addWaypoint(rightSideStartingWaypoint);
		RightWallToRightSwitch.addWaypointRelative(17.0, -1.0, -30.0);

		BobPath RightWallToRightSwitchPt2 = new BobPath(config, "RightWallToRightSwitchPt2", 1);
		RightWallToRightSwitchPt2.addWaypoint(RightWallToRightSwitch.getLastWaypoint());
		RightWallToRightSwitchPt2.addWaypointRelative(2.0, -1.0, -30.0);

		BobPath RightWallToLeftSide = new BobPath(config, "RightWallToLeftSide", -1);
		RightWallToLeftSide.addWaypoint(rightSideStartingWaypoint);
		RightWallToLeftSide.addWaypointRelative(13.0, 0.0, 0.0);
		RightWallToLeftSide.addWaypointRelative(3.0, 3.5, 89.99);

		BobPath RightWallToLeftScale = new BobPath(config, "RightWallToLeftScale", -1);
		RightWallToLeftScale.addWaypoint(rightSideStartingWaypoint);
		RightWallToLeftScale.addWaypointRelative(11.5, 0.0, 0.0);
		RightWallToLeftScale.addWaypointRelative(4.0, 5.0, 89.99);
		RightWallToLeftScale.addWaypointRelative(0.0, 7.0, 0.0);
		RightWallToLeftScale.addWaypointRelative(6.0, 4.0, -89.99);
		
		BobPath ScaleToSwitchCubeLeftSide = new BobPath(config, "ScaleToSwitchCubeLeftSide", 1);
		ScaleToSwitchCubeLeftSide.addWaypoint(RightWallToLeftScale.getLastWaypoint());
		ScaleToSwitchCubeLeftSide.addWaypointRelative(-6.0, -1.5, 10.0);

		BobPath SwitchCubeToScaleLeftSide = new BobPath(config, "SwitchCubeToScaleLeftSide", -1);
		SwitchCubeToScaleLeftSide.addWaypoint(ScaleToSwitchCubeLeftSide.getLastWaypoint());
		SwitchCubeToScaleLeftSide.addWaypointRelative(6.0, 1.5, -10.0);
		
		BobPath RightWallToRightScaleNullZone = new BobPath(config, "RightWallToRightScaleNullZone", -1);
		RightWallToRightScaleNullZone.addWaypoint(rightSideStartingWaypoint);
		RightWallToRightScaleNullZone.addWaypointRelative(22.0, -2.0, 0.0);
		RightWallToRightScaleNullZone.addWaypointRelative(3.0, 3.0, 89.99);

		BobPathGenerator.exportArcToJavaFile(RightWallToRightScale);
		BobPathGenerator.exportArcToJavaFile(ScaleToSwitchCubeRightSide);
		BobPathGenerator.exportArcToJavaFile(SwitchCubeToScaleRightSide);
		BobPathGenerator.exportArcToJavaFile(RightWallToRightSwitch);
		BobPathGenerator.exportArcToJavaFile(RightWallToRightSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(RightWallToLeftSide);
		BobPathGenerator.exportArcToJavaFile(RightWallToLeftScale);
		BobPathGenerator.exportArcToJavaFile(ScaleToSwitchCubeLeftSide);
		BobPathGenerator.exportArcToJavaFile(SwitchCubeToScaleLeftSide);
		BobPathGenerator.exportArcToJavaFile(RightWallToRightScaleNullZone);
		
	}

	private static void generateLeftSide(SrxTranslatorConfig config) {

		BobPath LeftWallToLeftScale = new BobPath(config, "LeftWallToLeftScale", -1);
		LeftWallToLeftScale.addWaypoint(leftSideStartingWaypoint);
		LeftWallToLeftScale.addWaypointRelative(12.0, -1.0, -10.0);
		LeftWallToLeftScale.addWaypointRelative(10.5, -4.5, 0.0);

		BobPath ScaleToSwitchCubeLeftSide = new BobPath(config, "ScaleToSwitchCubeLeftSide", 1);
		ScaleToSwitchCubeLeftSide.addWaypoint(LeftWallToLeftScale.getLastWaypoint());
		ScaleToSwitchCubeLeftSide.addWaypointRelative(-5.0, -1.25, 10.0);//-5.0 to -6.0 derrick 3/27
		
		BobPath SwitchCubeToScaleLeftSide = new BobPath(config, "SwitchCubeToScaleLeftSide", -1);
		SwitchCubeToScaleLeftSide.addWaypoint(ScaleToSwitchCubeLeftSide.getLastWaypoint());
		SwitchCubeToScaleLeftSide.addWaypointRelative(5.0, 1.25, -10.0);//5.0 to 6.0 derrick 3/27

		BobPath LeftWallToLeftSwitch = new BobPath(config, "LeftWallToLeftSwitch", -1);
		LeftWallToLeftSwitch.addWaypoint(leftSideStartingWaypoint);
		LeftWallToLeftSwitch.addWaypointRelative(17.0, 1.0, 30.0);

		BobPath LeftWallToLeftSwitchPt2 = new BobPath(config, "LeftWallToLeftSwitchPt2", 1);
		LeftWallToLeftSwitchPt2.addWaypoint(LeftWallToLeftSwitch.getLastWaypoint());
		LeftWallToLeftSwitchPt2.addWaypointRelative(2.0, 1.0, 30.0);

		BobPath LeftWallToRightSide = new BobPath(config, "LeftWallToRightSide", -1);
		LeftWallToRightSide.addWaypoint(leftSideStartingWaypoint);
		LeftWallToRightSide.addWaypointRelative(13.0, 0.0, 0.0);
		LeftWallToRightSide.addWaypointRelative(3.0, -3.5, -89.99);
		
		BobPath LeftWallToRightScale = new BobPath(config, "LeftWallToRightScale", -1);
		LeftWallToRightScale.addWaypoint(leftSideStartingWaypoint);
		LeftWallToRightScale.addWaypointRelative(11.5, 0.0, 0.0);
		LeftWallToRightScale.addWaypointRelative(4.0, -5.0, -89.99);
		LeftWallToRightScale.addWaypointRelative(0.0, -8.0, 0.0);//-7.0 ->-9.0 derrick 3/27
		LeftWallToRightScale.addWaypointRelative(6.0, -4.0, 89.99);//6.0 -> 5.0 derrick 3/27

		BobPathGenerator.exportArcToJavaFile(LeftWallToLeftScale);
		BobPathGenerator.exportArcToJavaFile(ScaleToSwitchCubeLeftSide);
		BobPathGenerator.exportArcToJavaFile(SwitchCubeToScaleLeftSide);
		BobPathGenerator.exportArcToJavaFile(LeftWallToLeftSwitch);
		BobPathGenerator.exportArcToJavaFile(LeftWallToLeftSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(LeftWallToRightSide);
		BobPathGenerator.exportArcToJavaFile(LeftWallToRightScale);
	}
	
	private static void copyArcsToRobotCode() {
		BobPathGenerator.copyFilesToRelativeDirectory("Arcs", "..\\frc319-2018\\src\\org\\usfirst\\frc\\team319\\arcs");
	}
	
	private static void copyPathsToRobotCode() {
		BobPathGenerator.copyFilesToRelativeDirectory("Paths", "..\\frc319-2018\\src\\org\\usfirst\\frc\\team319\\paths");
	}
	
}
