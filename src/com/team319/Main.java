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
	public static SrxTranslatorConfig tripleSwitchConfig;
	public static SrxTranslatorConfig longDistanceConfig;

	public static void main(String[] args) {

		powerUpConfig = new SrxTranslatorConfig();
		powerUpConfig.name = "powerUpConfig";
		powerUpConfig.dt = .01;
		powerUpConfig.max_acc = 30.0;
		powerUpConfig.max_jerk = 60.0;
		powerUpConfig.max_vel = 12.0; // 8.0
		powerUpConfig.wheelbase_width_feet = 23.5 / 12.0;// 23.5, then 29.5, 35.5
		powerUpConfig.wheel_dia_inches = 6.0;
		powerUpConfig.scale_factor = 7.57; //UNH value is 5.75, practice bot value is 7.42 // retune value is 1.53
		powerUpConfig.encoder_ticks_per_rev = 4096;
		powerUpConfig.robotLength = 39;
		powerUpConfig.robotWidth = 33;
		powerUpConfig.highGear = true;

		switchConfig = new SrxTranslatorConfig(powerUpConfig);
		switchConfig.max_vel = 6.0;
		switchConfig.max_acc = 10.0;
		
		tripleSwitchConfig = new SrxTranslatorConfig(powerUpConfig);
		tripleSwitchConfig.max_vel = 7.5;
		
		longDistanceConfig = new SrxTranslatorConfig(powerUpConfig);
		longDistanceConfig.max_vel = 10.0;
		longDistanceConfig.max_acc = 8.0;
		

		//generateMisc(switchConfig);
		//generateCenterSwitch(switchConfig);
		//generateTripleSwitch(tripleSwitchConfig);
		generateRightSide(powerUpConfig);
		generateLeftSide(powerUpConfig);
		
		copyArcsToRobotCode();
		//copyPathsToRobotCode();
	}

	private static void generateMisc(SrxTranslatorConfig config) {
		// --------------------MISC. AUTOS----------//

		BobPath ThreeFeet = new BobPath(config, "ThreeFeet", 1);
		ThreeFeet.addWaypoint(0.0, 0.0, 0.0);
		ThreeFeet.addWaypoint(4.0, 0.0, 0.0);

		BobPath OneFoot = new BobPath(config, "OneFoot", 1);
		OneFoot.addWaypoint(0.0, 0.0, 0.0);
		OneFoot.addWaypoint(1.0, 0.0, 0.0);

		BobPath FiveFeetAndTurn = new BobPath(config, "FiveFeetAndTurn", 1);
		FiveFeetAndTurn.addWaypoint(0.0, 0.0, 0.0);
		FiveFeetAndTurn.addWaypoint(5.0, 5.0, 89.99);

		BobPath CrossTheLine = new BobPath(config, "CrossTheLine", 1);
		CrossTheLine.addWaypoint(0.0, 0.0, 0.0);
		CrossTheLine.addWaypoint(10.0, 0.0, 0.0);
		
		BobPath CrossTheLineReverse = new BobPath(config, "CrossTheLineReverse", -1);
		CrossTheLineReverse.addWaypoint(0.0, 0.0, 0.0);
		CrossTheLineReverse.addWaypoint(10.0, 0.0, 0.0);

		BobPath TestSTurnAuto = new BobPath(config, "TestSTurnAuto", 1);
		TestSTurnAuto.addWaypoint(0.0, 0.0, 0.0);
		TestSTurnAuto.addWaypoint(8.375, 5.5, 0.0);

		BobPath FifteenFeet = new BobPath(config, "FifteenFeet", 1);
		FifteenFeet.addWaypoint(0.0, 0.0, 0.0);
		FifteenFeet.addWaypoint(15.0, 0.0, 0.0);
		
		BobPath TwentyFiveFeet = new BobPath(config, "TwentyFiveFeet", 1);
		TwentyFiveFeet.addWaypoint(0.0, 0.0, 0.0);
		TwentyFiveFeet.addWaypoint(25.0, 0.0, 0.0);

		BobPath FifteenFeetReverse = new BobPath(config, "FifteenFeetReverse", -1);
		FifteenFeetReverse.addWaypoint(0.0, 0.0, 0.0);
		FifteenFeetReverse.addWaypoint(15.0, 0.0, 0.0);

		BobPath BackwardsThreeFeet = new BobPath(config, "BackwardsThreeFeet", -1);
		BackwardsThreeFeet.addWaypoint(0.0, 0.0, 0.0);
		BackwardsThreeFeet.addWaypoint(-3.5, 0.0, 0.0);
		
		BobPath BackwardsTwoFeet = new BobPath(config, "BackwardsTwoFeet", -1);
		BackwardsTwoFeet.addWaypoint(0.0, 0.0, 0.0);
		BackwardsTwoFeet.addWaypoint(-2.0, 0.0, 0.0);
		
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
		
		BobPath TestPath = new BobPath(config, "TestPath", 1);
		TestPath.addWaypoint(centerStartingWaypoint);
		TestPath.addWaypointRelative(5, 2, 45);
		
		BobPath TestPath2 = new BobPath(config, "TestPath2", -1);
		TestPath2.addWaypoint(TestPath.getLastWaypoint());
		TestPath2.addWaypointRelative(-3, -3, 0);
		
		BobPathGenerator.exportArcToJavaFile(ThreeFeet);
		BobPathGenerator.exportArcToJavaFile(OneFoot);
		BobPathGenerator.exportArcToJavaFile(FiveFeetAndTurn);
		BobPathGenerator.exportArcToJavaFile(CrossTheLine);
		BobPathGenerator.exportArcToJavaFile(CrossTheLineReverse);
		BobPathGenerator.exportArcToJavaFile(TestSTurnAuto);
		BobPathGenerator.exportArcToJavaFile(FifteenFeet);
		BobPathGenerator.exportArcToJavaFile(TwentyFiveFeet);
		BobPathGenerator.exportArcToJavaFile(FifteenFeetReverse);
		BobPathGenerator.exportArcToJavaFile(BackwardsThreeFeet);
		BobPathGenerator.exportArcToJavaFile(BackwardsTwoFeet);
		BobPathGenerator.exportArcToJavaFile(TuningTestAuto);
		BobPathGenerator.exportArcToJavaFile(TuningTestAutoBackward);
		BobPathGenerator.exportArcToJavaFile(StraightForwardTuning);
		BobPathGenerator.exportArcToJavaFile(TestPath);
		BobPathGenerator.exportArcToJavaFile(TestPath2);
		BobPathGenerator.exportArcToJavaFile(BackwardsThreeFeet);
		
		
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
		CenterToRightSwitchPt3.addWaypointRelative(2.8, 0.0, 0.0);

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
		CenterToRightScalePt2.addWaypointRelative(14.0, -8.0, 45.0);
		
		BobPath CenterToRightScaleReverse = new BobPath(config, "CenterToRightScaleReverse", 1);
		CenterToRightScaleReverse.addWaypoint(CenterToRightSwitchPt3.getLastWaypoint());
		CenterToRightScaleReverse.addWaypointRelative(-3.0, -4.5, -89.99);
		CenterToRightScaleReverse.addWaypointRelative(3.0, -4.5, -89.99);
		CenterToRightScaleReverse.addWaypointRelative(5.0, 0, 0.0);

		// --------------------LEFT SIDE AUTOS----------//

		BobPath CenterToLeftSwitch = new BobPath(config, "CenterToLeftSwitch", 1);
		CenterToLeftSwitch.addWaypoint(centerStartingWaypoint);
		CenterToLeftSwitch.addWaypointRelative(8.375, 5.0, 0.0);

		BobPath CenterToLeftSwitchPt2 = new BobPath(config, "CenterToLeftSwitchPt2", -1);
		CenterToLeftSwitchPt2.addWaypoint(CenterToLeftSwitch.getLastWaypoint());
		CenterToLeftSwitchPt2.addWaypointRelative(-6.375, -5.0, 0.0);// -45

		BobPath CenterToLeftSwitchPt3 = new BobPath(config, "CenterToLeftSwitchPt3", 1);
		CenterToLeftSwitchPt3.addWaypoint(CenterToLeftSwitchPt2.getLastWaypoint());
		CenterToLeftSwitchPt3.addWaypointRelative(2.8, 0.0, 0.0);

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
		CenterToLeftScalePt2.addWaypointRelative(14.0, 8.0, -45.0);
		
		
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
	
	private static void generateTripleSwitch(SrxTranslatorConfig config) {
		
		BobPath TripleCubeCenterToLeftSwitch = new BobPath(config, "TripleCubeCenterToLeftSwitch", 1);
		TripleCubeCenterToLeftSwitch.addWaypoint(centerStartingWaypoint);
		TripleCubeCenterToLeftSwitch.addWaypointRelative(8.675, 4.5, 0.0);
		
		BobPath LeftSwitchToFirstCubePt1 = new BobPath(config, "LeftSwitchToFirstCubePt1", -1);
		LeftSwitchToFirstCubePt1.addWaypoint(TripleCubeCenterToLeftSwitch.getLastWaypoint());
		LeftSwitchToFirstCubePt1.addWaypointRelative(-1.0, 1.0, -60.0);
		
		BobPath LeftSwitchToFirstCubePt2 = new BobPath(config, "LeftSwitchToFirstCubePt2", 1);
		LeftSwitchToFirstCubePt2.addWaypoint(LeftSwitchToFirstCubePt1.getLastWaypoint());
		LeftSwitchToFirstCubePt2.addWaypointRelative(1.2, -2.05, 0.0);
		
		BobPath FirstCubeToLeftSwitchPt1 = new BobPath(config, "FirstCubeToLeftSwitchPt1", -1);
		FirstCubeToLeftSwitchPt1.addWaypoint(LeftSwitchToFirstCubePt2.getLastWaypoint());
		FirstCubeToLeftSwitchPt1.addWaypointRelative(-2.0, 0.5, 60.0);
		
		BobPath FirstCubeToLeftSwitchPt2 = new BobPath(config, "FirstCubeToLeftSwitchPt2", 1);
		FirstCubeToLeftSwitchPt2.addWaypoint(FirstCubeToLeftSwitchPt1.getLastWaypoint());
		FirstCubeToLeftSwitchPt2.addWaypointRelative(2.0, 0.0, 0.0);
		
		BobPath LeftSwitchToSecondCubePt1 = new BobPath(config, "LeftSwitchToSecondCubePt1", -1);
		LeftSwitchToSecondCubePt1.addWaypoint(FirstCubeToLeftSwitchPt2.getLastWaypoint());
		LeftSwitchToSecondCubePt1.addWaypointRelative(-1.5, 1.0, -89.99);
		
		BobPath LeftSwitchToSecondCubePt2 = new BobPath(config, "LeftSwitchToSecondCubePt2", 1);
		LeftSwitchToSecondCubePt2.addWaypoint(LeftSwitchToSecondCubePt1.getLastWaypoint());
		LeftSwitchToSecondCubePt2.addWaypointRelative(0.0, -4.5, 0.0);

		BobPath LeftSecondCubeToLeftScale = new BobPath(config, "LeftSecondCubeToLeftScale", -1);
		LeftSecondCubeToLeftScale.addWaypoint(LeftSwitchToSecondCubePt2.getLastWaypoint());
		LeftSecondCubeToLeftScale.addWaypointRelative(0.0, 6.0, 0.0);
		
		BobPath TripleCubeLeftSwitchToFirstCubePt1 = new BobPath(config, "TripleCubeLeftSwitchToFirstCubePt1", -1);
		TripleCubeLeftSwitchToFirstCubePt1.addWaypoint(TripleCubeCenterToLeftSwitch.getLastWaypoint());
		TripleCubeLeftSwitchToFirstCubePt1.addWaypointRelative(-6.375, -5.0, 0.0);
		
		BobPath TripleCubeLeftSwitchToFirstCubePt2 = new BobPath(config, "TripleCubeLeftSwitchToFirstCubePt2", 1);
		TripleCubeLeftSwitchToFirstCubePt2.addWaypoint(TripleCubeLeftSwitchToFirstCubePt1.getLastWaypoint());
		TripleCubeLeftSwitchToFirstCubePt2.addWaypointRelative(2.8, 0.0, 0.0);
		
		BobPath TripleCubeFirstCubeToLeftSwitchPt1 = new BobPath(config, "TripleCubeFirstCubeToLeftSwitchPt1", -1);
		TripleCubeFirstCubeToLeftSwitchPt1.addWaypoint(TripleCubeLeftSwitchToFirstCubePt2.getLastWaypoint());
		TripleCubeFirstCubeToLeftSwitchPt1.addWaypointRelative(-4.10, 0.0, 0.0);
		
		BobPath TripleCubeFirstCubeToLeftSwitchPt2 = new BobPath(config, "TripleCubeFirstCubeToLeftSwitchPt2", 1);
		TripleCubeFirstCubeToLeftSwitchPt2.addWaypoint(TripleCubeFirstCubeToLeftSwitchPt1.getLastWaypoint());
		TripleCubeFirstCubeToLeftSwitchPt2.addWaypointRelative(8.0, 4.0, 0.0);
		
		BobPath LeftSwitchToRightScale = new BobPath(config, "LeftSwitchToRightScale", 1);
		LeftSwitchToRightScale.addWaypoint(LeftSwitchToSecondCubePt1.getLastWaypoint());
		LeftSwitchToRightScale.addWaypointRelative(0.0, -5.0, 0.0);
		LeftSwitchToRightScale.addWaypointRelative(0.0, -4.0, 0.0);
		LeftSwitchToRightScale.addWaypointRelative(4.0, -4.0, 89.99);
		LeftSwitchToRightScale.addWaypointRelative(4.0, 0.0, 0.0);
		
		// right side
		
		BobPath TripleCubeCenterToRightSwitch = new BobPath(config, "TripleCubeCenterToRightSwitch", 1); // 3 seconds
		TripleCubeCenterToRightSwitch.addWaypoint(centerStartingWaypoint);
		TripleCubeCenterToRightSwitch.addWaypointRelative(8.375, -4.0, 0.0);
		
		BobPath RightSwitchToFirstCubePt1 = new BobPath(config, "RightSwitchToFirstCubePt1", -1);
		RightSwitchToFirstCubePt1.addWaypoint(TripleCubeCenterToRightSwitch.getLastWaypoint());
		RightSwitchToFirstCubePt1.addWaypointRelative(-1.0, -1.0, 60.0);
		
		BobPath RightSwitchToFirstCubePt2 = new BobPath(config, "RightSwitchToFirstCubePt2", 1);
		RightSwitchToFirstCubePt2.addWaypoint(RightSwitchToFirstCubePt1.getLastWaypoint());
		RightSwitchToFirstCubePt2.addWaypointRelative(1.2, 2.05, 0.0);
		
		BobPath FirstCubeToRightSwitchPt1 = new BobPath(config, "FirstCubeToRightSwitchPt1", -1);
		FirstCubeToRightSwitchPt1.addWaypoint(RightSwitchToFirstCubePt2.getLastWaypoint());
		FirstCubeToRightSwitchPt1.addWaypointRelative(-2.0, -0.5, -60.0);
		
		BobPath FirstCubeToRightSwitchPt2 = new BobPath(config, "FirstCubeToRightSwitchPt2", 1);
		FirstCubeToRightSwitchPt2.addWaypoint(FirstCubeToRightSwitchPt1.getLastWaypoint());
		FirstCubeToRightSwitchPt2.addWaypointRelative(2.0, 0.0, 0.0);
		
		BobPath RightSwitchToSecondCubePt1 = new BobPath(config, "RightSwitchToSecondCubePt1", -1);
		RightSwitchToSecondCubePt1.addWaypoint(FirstCubeToRightSwitchPt2.getLastWaypoint());
		RightSwitchToSecondCubePt1.addWaypointRelative(-1.5, -1.0, 89.99);
		
		BobPath RightSwitchToSecondCubePt2 = new BobPath(config, "RightSwitchToSecondCubePt2", 1);
		RightSwitchToSecondCubePt2.addWaypoint(RightSwitchToSecondCubePt1.getLastWaypoint());
		RightSwitchToSecondCubePt2.addWaypointRelative(0.0, 4.5, 0.0);

		BobPath RightSecondCubeToRightScale = new BobPath(config, "RightSecondCubeToRightScale", -1);
		RightSecondCubeToRightScale.addWaypoint(RightSwitchToSecondCubePt2.getLastWaypoint());
		RightSecondCubeToRightScale.addWaypointRelative(1.0, -8.0, -89.99);
		RightSecondCubeToRightScale.addWaypointRelative(5.0, 0.0, 30.0);
		
		BobPath RightSecondCubeToLeftScalePt1 = new BobPath(config, "RightSecondCubeToLeftScalePt1", -1);
		RightSecondCubeToLeftScalePt1.addWaypoint(RightSwitchToSecondCubePt2.getLastWaypoint());
		RightSecondCubeToLeftScalePt1.addWaypointRelative(-2.0, -4.0, 30.0);
		
		BobPath RightSecondCubeToLeftScalePt2 = new BobPath(config, "RightSecondCubeToLeftScalePt2", 1);
		RightSecondCubeToLeftScalePt2.addWaypoint(RightSecondCubeToLeftScalePt1.getLastWaypoint());
		RightSecondCubeToLeftScalePt2.addWaypointRelative(0.0, 6.0, 0.0);
		RightSecondCubeToLeftScalePt2.addWaypointRelative(15.0, 8.0, -89.99);
		
		BobPath TripleCubeRightSwitchToFirstCubePt1 = new BobPath(config, "TripleCubeRightSwitchToFirstCubePt1", -1); // 4 seconds
		TripleCubeRightSwitchToFirstCubePt1.addWaypoint(TripleCubeCenterToRightSwitch.getLastWaypoint());
		TripleCubeRightSwitchToFirstCubePt1.addWaypointRelative(-6.375, 4.0, 0.0);// 45

		BobPath TripleCubeRightSwitchToFirstCubePt2 = new BobPath(config, "TripleCubeRightSwitchToFirstCubePt2", 1);
		TripleCubeRightSwitchToFirstCubePt2.addWaypoint(TripleCubeRightSwitchToFirstCubePt1.getLastWaypoint());
		TripleCubeRightSwitchToFirstCubePt2.addWaypointRelative(2.8, 0.0, 0.0);

		BobPath TripleCubeFirstCubeToRightSwitchPt1 = new BobPath(config, "TripleCubeFirstCubeToRightSwitchPt1", -1);
		TripleCubeFirstCubeToRightSwitchPt1.addWaypoint(TripleCubeRightSwitchToFirstCubePt2.getLastWaypoint());
		TripleCubeFirstCubeToRightSwitchPt1.addWaypointRelative(-4.19, 0.0, 0.0);

		BobPath TripleCubeFirstCubeToRightSwitchPt2 = new BobPath(config, "TripleCubeFirstCubeToRightSwitchPt2", 1);
		TripleCubeFirstCubeToRightSwitchPt2.addWaypoint(TripleCubeFirstCubeToRightSwitchPt1.getLastWaypoint());
		TripleCubeFirstCubeToRightSwitchPt2.addWaypointRelative(8.0, -4.0, 0.0);
		
		BobPath RightSwitchToLeftScale = new BobPath(config, "RightSwitchToLeftScale", 1);
		RightSwitchToLeftScale.addWaypoint(RightSwitchToSecondCubePt2.getLastWaypoint());
		RightSwitchToLeftScale.addWaypointRelative(0.0, 5.0, 0.0);
		RightSwitchToLeftScale.addWaypointRelative(0.0, 4.0, 0.0);
		RightSwitchToLeftScale.addWaypointRelative(4.0, 4.0, -89.99);
		RightSwitchToLeftScale.addWaypointRelative(4.0, 0.0, 0.0);
		
		BobPathGenerator.exportArcToJavaFile(TripleCubeCenterToLeftSwitch);
		BobPathGenerator.exportArcToJavaFile(LeftSwitchToFirstCubePt1);
		BobPathGenerator.exportArcToJavaFile(LeftSwitchToFirstCubePt2);
		BobPathGenerator.exportArcToJavaFile(FirstCubeToLeftSwitchPt1);
		BobPathGenerator.exportArcToJavaFile(FirstCubeToLeftSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(LeftSwitchToSecondCubePt1);
		BobPathGenerator.exportArcToJavaFile(LeftSwitchToSecondCubePt2);
		BobPathGenerator.exportArcToJavaFile(LeftSecondCubeToLeftScale);
		BobPathGenerator.exportArcToJavaFile(TripleCubeLeftSwitchToFirstCubePt1);
		BobPathGenerator.exportArcToJavaFile(TripleCubeLeftSwitchToFirstCubePt2);
		BobPathGenerator.exportArcToJavaFile(TripleCubeFirstCubeToLeftSwitchPt1);
		BobPathGenerator.exportArcToJavaFile(TripleCubeFirstCubeToLeftSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(LeftSwitchToRightScale);

		
		BobPathGenerator.exportArcToJavaFile(TripleCubeCenterToRightSwitch);
		BobPathGenerator.exportArcToJavaFile(RightSwitchToFirstCubePt1);
		BobPathGenerator.exportArcToJavaFile(RightSwitchToFirstCubePt2);
		BobPathGenerator.exportArcToJavaFile(FirstCubeToRightSwitchPt1);
		BobPathGenerator.exportArcToJavaFile(FirstCubeToRightSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(RightSwitchToSecondCubePt1);
		BobPathGenerator.exportArcToJavaFile(RightSwitchToSecondCubePt2);
		BobPathGenerator.exportArcToJavaFile(TripleCubeRightSwitchToFirstCubePt1);
		BobPathGenerator.exportArcToJavaFile(TripleCubeRightSwitchToFirstCubePt2);
		BobPathGenerator.exportArcToJavaFile(TripleCubeFirstCubeToRightSwitchPt1);
		BobPathGenerator.exportArcToJavaFile(TripleCubeFirstCubeToRightSwitchPt2);
		//BobPathGenerator.exportArcToJavaFile(RightSecondCubeToRightScale);
		//BobPathGenerator.exportArcToJavaFile(RightSecondCubeToLeftScalePt1);
		//BobPathGenerator.exportArcToJavaFile(RightSecondCubeToLeftScalePt2);
		BobPathGenerator.exportArcToJavaFile(RightSwitchToLeftScale);
		
	}

	private static void generateRightSide(SrxTranslatorConfig config) {

		BobPath RightWallToRightScale = new BobPath(config, "RightWallToRightScale", -1);
		RightWallToRightScale.addWaypoint(rightSideStartingWaypoint);
		RightWallToRightScale.addWaypointRelative(12.0, 1.0, 10.0);
		RightWallToRightScale.addWaypointRelative(9.75, 3.75, 0.0);
		//RightWallToRightScale.addWaypointRelative(22.5, 0.0, 0.0);

		BobPath ScaleToSwitchCubeRightSide = new BobPath(config, "ScaleToSwitchCubeRightSide", 1);
		ScaleToSwitchCubeRightSide.addWaypoint(RightWallToRightScale.getLastWaypoint());
		ScaleToSwitchCubeRightSide.addWaypointRelative(-4.6, 0.0, 0.0);

		BobPath SwitchCubeToScaleRightSide = new BobPath(config, "SwitchCubeToScaleRightSide", -1);
		SwitchCubeToScaleRightSide.addWaypoint(ScaleToSwitchCubeRightSide.getLastWaypoint());
		SwitchCubeToScaleRightSide.addWaypointRelative(4.6, 0.0, 15.0);
		
		BobPath RightScaleToSecondSwitchCube = new BobPath(config, "RightScaleToSecondSwitchCube", 1);
		RightScaleToSecondSwitchCube.addWaypoint(SwitchCubeToScaleRightSide.getLastWaypoint());
		RightScaleToSecondSwitchCube.addWaypointRelative(-4.8, 3.1, -70.0);
		
		BobPath RightWallToRightSwitchPt1 = new BobPath(config, "RightWallToRightSwitchPt1", -1);
		RightWallToRightSwitchPt1.addWaypoint(rightSideStartingWaypoint);
		RightWallToRightSwitchPt1.addWaypointRelative(20.0, 0.6, -5.0);

		BobPath RightWallToRightSwitchPt2 = new BobPath(config, "RightWallToRightSwitchPt2", 1);
		RightWallToRightSwitchPt2.addWaypoint(RightWallToRightSwitchPt1.getLastWaypoint());
		RightWallToRightSwitchPt2.addWaypointRelative(-3.55, 3.55, -35.0);
		
		BobPath RightSwitchToRightSwitchCube = new BobPath(config, "RightSwitchToRightSwitchCube", -1);
		RightSwitchToRightSwitchCube.addWaypoint(RightWallToRightSwitchPt2.getLastWaypoint());
		RightSwitchToRightSwitchCube.addWaypointRelative(1.25, -1.25, 0.0);

		BobPath RightWallToLeftSide = new BobPath(config, "RightWallToLeftSide", -1);
		RightWallToLeftSide.addWaypoint(rightSideStartingWaypoint);
		RightWallToLeftSide.addWaypointRelative(10.25, 0.5, 0.0);
		RightWallToLeftSide.addWaypointRelative(6.0, 7.0, 89.99);
		//RightWallToLeftSide.addWaypointRelative(0.0, 2.0, 0.0);

		BobPath RightWallToLeftScale = new BobPath(config, "RightWallToLeftScale", -1);
		RightWallToLeftScale.addWaypoint(rightSideStartingWaypoint);
		RightWallToLeftScale.addWaypointRelative(10.25, 0.5, 0.0);
		RightWallToLeftScale.addWaypointRelative(6.0, 7.0, 89.99);
		RightWallToLeftScale.addWaypointRelative(0.0, 6.3, 0.0);//-7.0 ->-9.0 derrick 3/27
		RightWallToLeftScale.addWaypointRelative(5.5, 4.7, -89.99);//6.0 -> 5.0 derrick 3/27
		
		BobPath ScaleToSwitchCubeLeftSide = new BobPath(config, "ScaleToSwitchCubeLeftSide", 1);
		ScaleToSwitchCubeLeftSide.addWaypoint(RightWallToLeftScale.getLastWaypoint());
		ScaleToSwitchCubeLeftSide.addWaypointRelative(-5.5, -1.5, 10.0);

		BobPath SwitchCubeToScaleLeftSide = new BobPath(config, "SwitchCubeToScaleLeftSide", -1);
		SwitchCubeToScaleLeftSide.addWaypoint(ScaleToSwitchCubeLeftSide.getLastWaypoint());
		SwitchCubeToScaleLeftSide.addWaypointRelative(5.5, 1.5, -10.0);
		
		BobPath RightWallToRightScaleNullZone = new BobPath(config, "RightWallToRightScaleNullZone", -1);
		RightWallToRightScaleNullZone.addWaypoint(rightSideStartingWaypoint);
		RightWallToRightScaleNullZone.addWaypointRelative(10.0, -1.0, 0.0);
		RightWallToRightScaleNullZone.addWaypointRelative(10.5, 0.0, 0.0);
		RightWallToRightScaleNullZone.addWaypointRelative(3.0, 3.0, 89.99);
		
		BobPath RightNullZoneBackUpToScale = new BobPath(switchConfig, "RightNullZoneBackUpToScale", -1);
		RightNullZoneBackUpToScale.addWaypoint(RightWallToRightScaleNullZone.getLastWaypoint());
		RightNullZoneBackUpToScale.addWaypointRelative(0.0, 1.5, 0.0);
		
		BobPath RightNullZoneDriveAwayFromScale = new BobPath(switchConfig, "RightNullZoneDriveAwayFromScale", 1);
		RightNullZoneDriveAwayFromScale.addWaypoint(RightNullZoneBackUpToScale.getLastWaypoint());
		RightNullZoneDriveAwayFromScale.addWaypointRelative(0.0, -3.0, 0.0);

		BobPathGenerator.exportArcToJavaFile(RightWallToRightScale);
		BobPathGenerator.exportArcToJavaFile(ScaleToSwitchCubeRightSide);
		BobPathGenerator.exportArcToJavaFile(SwitchCubeToScaleRightSide);
		BobPathGenerator.exportArcToJavaFile(RightScaleToSecondSwitchCube);
		BobPathGenerator.exportArcToJavaFile(RightWallToRightSwitchPt1);
		BobPathGenerator.exportArcToJavaFile(RightWallToRightSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(RightSwitchToRightSwitchCube);
		BobPathGenerator.exportArcToJavaFile(RightWallToLeftSide);
		BobPathGenerator.exportArcToJavaFile(RightWallToLeftScale);
		BobPathGenerator.exportArcToJavaFile(ScaleToSwitchCubeLeftSide);
		BobPathGenerator.exportArcToJavaFile(SwitchCubeToScaleLeftSide);
		BobPathGenerator.exportArcToJavaFile(RightWallToRightScaleNullZone);
		BobPathGenerator.exportArcToJavaFile(RightNullZoneBackUpToScale);
		BobPathGenerator.exportArcToJavaFile(RightNullZoneDriveAwayFromScale);
		
	}

	private static void generateLeftSide(SrxTranslatorConfig config) {

		BobPath LeftWallToLeftScale = new BobPath(config, "LeftWallToLeftScale", -1);
		LeftWallToLeftScale.addWaypoint(leftSideStartingWaypoint);
		LeftWallToLeftScale.addWaypointRelative(12.91, -1.0, -5.0);
		LeftWallToLeftScale.addWaypointRelative(9.75, -2.25, 5.0); // y was -4.25, changed becuase robot consistently went too far right

		BobPath ScaleToSwitchCubeLeftSide = new BobPath(config, "ScaleToSwitchCubeLeftSide", 1);
		ScaleToSwitchCubeLeftSide.addWaypoint(LeftWallToLeftScale.getLastWaypoint());
		ScaleToSwitchCubeLeftSide.addWaypointRelative(-5.0, -0.5, 0.0);//-5.0 to -6.0 derrick 3/27
		
		BobPath SwitchCubeToScaleLeftSide = new BobPath(config, "SwitchCubeToScaleLeftSide", -1);
		SwitchCubeToScaleLeftSide.addWaypoint(ScaleToSwitchCubeLeftSide.getLastWaypoint());
		SwitchCubeToScaleLeftSide.addWaypointRelative(5.0, 0.5, -15.0);//5.0 to 6.0 derrick 3/27
		
		BobPath LeftScaleToSecondSwitchCube = new BobPath(config, "LeftScaleToSecondSwitchCube", 1);
		LeftScaleToSecondSwitchCube.addWaypoint(SwitchCubeToScaleLeftSide.getLastWaypoint());
		LeftScaleToSecondSwitchCube.addWaypointRelative(-3.75, -3.5, 75.0);
		
		BobPath SecondSwitchCubeToLeftScale = new BobPath(config, "SecondSwitchCubeToLeftScale", -1);
		SecondSwitchCubeToLeftScale.addWaypoint(LeftScaleToSecondSwitchCube.getLastWaypoint());
		SecondSwitchCubeToLeftScale.addWaypointRelative(4.25, 3.5, -75.0);

		BobPath LeftWallToLeftSwitchPt1 = new BobPath(config, "LeftWallToLeftSwitchPt1", -1);
		LeftWallToLeftSwitchPt1.addWaypoint(leftSideStartingWaypoint);
		LeftWallToLeftSwitchPt1.addWaypointRelative(20.0, -0.6, 5.0);

		BobPath LeftWallToLeftSwitchPt2 = new BobPath(config, "LeftWallToLeftSwitchPt2", 1);
		LeftWallToLeftSwitchPt2.addWaypoint(LeftWallToLeftSwitchPt1.getLastWaypoint());
		LeftWallToLeftSwitchPt2.addWaypointRelative(-3.55, -3.55, 35.0);
		
		BobPath LeftSwitchToLeftSwitchCube = new BobPath(config, "LeftSwitchToLeftSwitchCube", -1);
		LeftSwitchToLeftSwitchCube.addWaypoint(LeftWallToLeftSwitchPt2.getLastWaypoint());
		LeftSwitchToLeftSwitchCube.addWaypointRelative(1.25, 1.25, 0.0);

		BobPath LeftWallToRightSide = new BobPath(longDistanceConfig, "LeftWallToRightSide", -1);
		LeftWallToRightSide.addWaypoint(leftSideStartingWaypoint);
		LeftWallToRightSide.addWaypointRelative(10.25, -0.5, 0.0);
		LeftWallToRightSide.addWaypointRelative(6.0, -7.0, -89.99);
		//LeftWallToRightSide.addWaypointRelative(0.0, -2.0, 0.0);
		
		BobPath LeftWallToRightScale = new BobPath(longDistanceConfig, "LeftWallToRightScale", -1);
		LeftWallToRightScale.addWaypoint(leftSideStartingWaypoint);
		LeftWallToRightScale.addWaypointRelative(10.5, -0.5, 0.0);
		LeftWallToRightScale.addWaypointRelative(6.0, -7.0, -89.99);
		LeftWallToRightScale.addWaypointRelative(0.0, -3.3, 0.0);//-7.0 ->-9.0 derrick 3/27
		LeftWallToRightScale.addWaypointRelative(6.0, -4.7, 89.99);//6.0 -> 5.0 derrick 3/27		
		
		BobPath LeftWallToLeftScaleNullZone = new BobPath(config, "LeftWallToLeftScaleNullZone", -1);
		LeftWallToLeftScaleNullZone.addWaypoint(leftSideStartingWaypoint);
		LeftWallToLeftScaleNullZone.addWaypointRelative(10.0, 1.0, 0.0); // had a y of 0.5
		LeftWallToLeftScaleNullZone.addWaypointRelative(10.5, 0.0, 0.0);
		LeftWallToLeftScaleNullZone.addWaypointRelative(3.0, -3.0, -89.99);
		
		BobPath LeftNullZoneToLeftNullZoneWall = new BobPath(switchConfig, "LeftNullZoneToLeftNullZoneWall", 1);
		LeftNullZoneToLeftNullZoneWall.addWaypoint(LeftWallToLeftScaleNullZone.getLastWaypoint());
		LeftNullZoneToLeftNullZoneWall.addWaypointRelative(0.0, 1.0, 0.0);
		
		BobPath LeftNullZoneWallToLeftScale = new BobPath(switchConfig, "LeftNullZoneWallToLeftScale", -1);
		LeftNullZoneWallToLeftScale.addWaypoint(LeftWallToLeftScaleNullZone.getLastWaypoint());
		LeftNullZoneWallToLeftScale.addWaypointRelative(0.0, -2.0, 0.0);
		
		BobPath LeftWallToLeftScaleCorner = new BobPath(config, "LeftWallToLeftScaleCorner", -1);
		LeftWallToLeftScaleCorner.addWaypoint(leftSideStartingWaypoint);
		LeftWallToLeftScaleCorner.addWaypointRelative(10.0, 1.0, 0.0); // had a y of 0.5
		LeftWallToLeftScaleCorner.addWaypointRelative(12.0, -2.5, -45.0);
		
		BobPath GetOutTheWay = new BobPath(config, "GetOutTheWay", 1);
		GetOutTheWay.addWaypoint(LeftWallToLeftScaleCorner.getLastWaypoint());
		GetOutTheWay.addWaypointRelative(-5.5, 2.5, 45.0);
		
		BobPath LeftNullZoneDriveAwayFromScale = new BobPath(switchConfig, "LeftNullZoneDriveAwayFromScale", 1);
		LeftNullZoneDriveAwayFromScale.addWaypoint(LeftNullZoneWallToLeftScale.getLastWaypoint());
		LeftNullZoneDriveAwayFromScale.addWaypointRelative(0.0, 3.0, 0.0);
		

		BobPathGenerator.exportArcToJavaFile(LeftWallToLeftScale);
		BobPathGenerator.exportArcToJavaFile(ScaleToSwitchCubeLeftSide);
		BobPathGenerator.exportArcToJavaFile(SwitchCubeToScaleLeftSide);
		BobPathGenerator.exportArcToJavaFile(LeftScaleToSecondSwitchCube);
		BobPathGenerator.exportArcToJavaFile(SecondSwitchCubeToLeftScale);
		BobPathGenerator.exportArcToJavaFile(LeftWallToLeftSwitchPt1);
		BobPathGenerator.exportArcToJavaFile(LeftWallToLeftSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(LeftSwitchToLeftSwitchCube);
		BobPathGenerator.exportArcToJavaFile(LeftWallToRightSide);
		BobPathGenerator.exportArcToJavaFile(LeftWallToRightScale);
		BobPathGenerator.exportArcToJavaFile(LeftWallToLeftScaleNullZone);
		BobPathGenerator.exportArcToJavaFile(LeftNullZoneToLeftNullZoneWall);
		BobPathGenerator.exportArcToJavaFile(LeftNullZoneWallToLeftScale);
		BobPathGenerator.exportArcToJavaFile(LeftWallToLeftScaleCorner);
		BobPathGenerator.exportArcToJavaFile(GetOutTheWay);
		BobPathGenerator.exportArcToJavaFile(LeftNullZoneDriveAwayFromScale);
	}
	
	private static void copyArcsToRobotCode() {
		BobPathGenerator.copyFilesToRelativeDirectory("Arcs", "..\\frc319-2018\\src\\org\\usfirst\\frc\\team319\\arcs");
	}
	
	private static void copyPathsToRobotCode() {
		BobPathGenerator.copyFilesToRelativeDirectory("Paths", "..\\frc319-2018\\src\\org\\usfirst\\frc\\team319\\paths");
	}
	
}
