package com.team319;

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SrxTranslatorConfig gearBobConfig = new SrxTranslatorConfig();
		SrxTranslatorConfig powerUpConfig = new SrxTranslatorConfig();
		// length of robot is 38.5

		// Standard configs between all trajectories
		gearBobConfig.name = "gearBobConfig";
		gearBobConfig.dt = .01;
		gearBobConfig.max_acc = 10.0;
		gearBobConfig.max_jerk = 60.0;
		gearBobConfig.max_vel = 4.0; // gearbob was 6.0
		gearBobConfig.wheelbase_width_feet = 32.5 / 12.0;
		gearBobConfig.wheel_dia_inches = 3.5;
		gearBobConfig.scale_factor = 2.35; // 0.899 // gearbob is 2.35
		gearBobConfig.encoder_ticks_per_rev = 1024;

		powerUpConfig.name = "powerUpConfig";
		powerUpConfig.dt = .01;
		powerUpConfig.max_acc = 10.0;
		powerUpConfig.max_jerk = 60.0;
		powerUpConfig.max_vel = 4.0; // 8.0
		powerUpConfig.wheelbase_width_feet = 33.5 / 12.0;// 23.5, then 29.5, 35.5
		powerUpConfig.wheel_dia_inches = 6.0;
		powerUpConfig.scale_factor = 6.54; // 3.08
		powerUpConfig.encoder_ticks_per_rev = 1024;

		SrxTranslatorConfig slowConfig = new SrxTranslatorConfig(gearBobConfig);
		slowConfig.max_vel = 4.0;

		SrxTranslatorConfig powerUpHighGearConfig = new SrxTranslatorConfig(powerUpConfig);
		powerUpHighGearConfig.max_vel = 6.0;
		powerUpHighGearConfig.max_acc = 4.0;
		powerUpHighGearConfig.scale_factor = 7.61; // 10.46
		powerUpHighGearConfig.wheelbase_width_feet = 30.5 / 12.0;

		// generateMiscPaths(powerUpHighGearConfig);
		// generateCenterSwitchPaths(powerUpHighGearConfig);
		generateRightSidePaths(powerUpHighGearConfig);
		// generateLeftSidePaths(powerUpHighGearConfig);
	}

	private static void generateMiscPaths(SrxTranslatorConfig config) {
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

		BobPath BackwardsThreeFeet = new BobPath(config, "BackwardsThreeFeet", -1);
		BackwardsThreeFeet.addWaypoint(0.0, 0.0, 0.0);
		BackwardsThreeFeet.addWaypoint(3.0, 0.0, 0.0);

		BobPathGenerator.exportPathToJavaFile("Paths", ThreeFeet);
		BobPathGenerator.exportPathToJavaFile("Paths", OneFoot);
		BobPathGenerator.exportPathToJavaFile("Paths", FiveFeetAndTurn);
		BobPathGenerator.exportPathToJavaFile("Paths", CrossTheLine);
		BobPathGenerator.exportPathToJavaFile("Paths", TestSTurnAuto);
		BobPathGenerator.exportPathToJavaFile("Paths", FifteenFeet);
		BobPathGenerator.exportPathToJavaFile("Paths", BackwardsThreeFeet);
	}

	private static void generateCenterSwitchPaths(SrxTranslatorConfig config) {

		// Right Side
		BobPath CenterToRightSwitch = new BobPath(config, "CenterToRightSwitch", 1); // 3 seconds
		CenterToRightSwitch.addWaypoint(0.0, 0.0, 0.0);
		CenterToRightSwitch.addWaypoint(8.375, -6.0, 0.0);

		BobPath CenterToRightSwitchPt2 = new BobPath(config, "CenterToRightSwitchPt2", -1); // 4 seconds
		CenterToRightSwitchPt2.addWaypoint(0.0, 0.0, 0.0);
		CenterToRightSwitchPt2.addWaypoint(-6.5, 0.0, 0.0);// 45

		BobPath CenterToRightSwitchPt3 = new BobPath(config, "CenterToRightSwitchPt3", 1);
		CenterToRightSwitchPt3.addWaypoint(0.0, 0.0, 0.0);
		CenterToRightSwitchPt3.addWaypoint(3.25, 5.25, 45.0);

		BobPath CenterToRightSwitchPt4 = new BobPath(config, "CenterToRightSwitchPt4", -1);
		CenterToRightSwitchPt4.addWaypoint(0.0, 0.0, 0.0);
		CenterToRightSwitchPt4.addWaypoint(-3.25, -5.25, 45.0);

		BobPath CenterToRightSwitchPt5 = new BobPath(config, "CenterToRightSwitchPt5", 1);
		CenterToRightSwitchPt5.addWaypoint(0.0, 0.0, 0.0);
		CenterToRightSwitchPt5.addWaypoint(6.5, 0.0, 0.0);

		// Left Side

		BobPath CenterToLeftSwitch = new BobPath(config, "CenterToLeftSwitch", 1);
		CenterToLeftSwitch.addWaypoint(0.0, 0.0, 0.0);
		CenterToLeftSwitch.addWaypoint(8.375, 6.0, 0.0);

		BobPath CenterToLeftSwitchPt2 = new BobPath(config, "CenterToLeftSwitchPt2", -1);
		CenterToLeftSwitchPt2.addWaypoint(0.0, 0.0, 0.0);
		CenterToLeftSwitchPt2.addWaypoint(-6.5, 0.0, 0.0);// -45

		BobPath CenterToLeftSwitchPt3 = new BobPath(config, "CenterToLeftSwitchPt3", 1);
		CenterToLeftSwitchPt3.addWaypoint(0.0, 0.0, 0.0);
		CenterToLeftSwitchPt3.addWaypoint(3.25, -5.25, 45.0);

		BobPath CenterToLeftSwitchPt4 = new BobPath(config, "CenterToLeftSwitchPt4", -1);
		CenterToLeftSwitchPt4.addWaypoint(0.0, 0.0, 0.0);
		CenterToLeftSwitchPt4.addWaypoint(-3.25, 5.25, 45.0);

		BobPath CenterToLeftSwitchPt5 = new BobPath(config, "CenterToLeftSwitchPt5", 1);
		CenterToLeftSwitchPt5.addWaypoint(0.0, 0.0, 0.0);
		CenterToLeftSwitchPt5.addWaypoint(6.5, 0.0, 0.0);

		BobPathGenerator.exportPathToJavaFile("Paths", CenterToRightSwitch);
		BobPathGenerator.exportPathToJavaFile("Paths", CenterToRightSwitchPt2);
		BobPathGenerator.exportPathToJavaFile("Paths", CenterToRightSwitchPt3);
		BobPathGenerator.exportPathToJavaFile("Paths", CenterToRightSwitchPt4);
		BobPathGenerator.exportPathToJavaFile("Paths", CenterToRightSwitchPt5);

		BobPathGenerator.exportPathToJavaFile("Paths", CenterToLeftSwitch);
		BobPathGenerator.exportPathToJavaFile("Paths", CenterToLeftSwitchPt2);
		BobPathGenerator.exportPathToJavaFile("Paths", CenterToLeftSwitchPt3);
		BobPathGenerator.exportPathToJavaFile("Paths", CenterToLeftSwitchPt4);
		BobPathGenerator.exportPathToJavaFile("Paths", CenterToLeftSwitchPt5);

	}

	private static void generateRightSidePaths(SrxTranslatorConfig config) {
		BobPath RightWallToRightScale = new BobPath(config, "RightWallToRightScale", -1);
		RightWallToRightScale.addWaypoint(0.0, 0.0, 0.0);
		RightWallToRightScale.addWaypoint(12.0, 0.0, 0.0);
		RightWallToRightScale.addWaypoint(22.5, 4.0, 15.0);

		BobPath ScaleToSwitchCubeRightSide = new BobPath(config, "ScaleToSwitchCubeRightSide", 1);
		ScaleToSwitchCubeRightSide.addWaypoint(0.0, 0.0, 0.0);
		ScaleToSwitchCubeRightSide.addWaypoint(5.0, -1.0, -20.0);

		BobPath RightWallToRightSwitch = new BobPath(config, "RightWallToRightSwitch", -1);
		RightWallToRightSwitch.addWaypoint(0.0, 0.0, 0.0);
		RightWallToRightSwitch.addWaypoint(17.0, -1.0, -30.0);

		BobPath RightWallToRightSwitchPt2 = new BobPath(config, "RightWallToRightSwitchPt2", 1);
		RightWallToRightSwitchPt2.addWaypoint(0.0, 0.0, 0.0);
		RightWallToRightSwitchPt2.addWaypoint(2.0, -1.0, -30.0);

		BobPath RightWallToLeftSide = new BobPath(config, "RightWallToLeftSide", -1);
		RightWallToLeftSide.addWaypoint(0.0, 0.0, 0.0);
		RightWallToLeftSide.addWaypoint(13.0, 0.0, 0.0);
		RightWallToLeftSide.addWaypoint(16.0, 3.5, 89.99);

		BobPathGenerator.exportArcToJavaFile("Paths", RightWallToRightScale);
		BobPathGenerator.exportArcToJavaFile("Paths", ScaleToSwitchCubeRightSide);
		BobPathGenerator.exportArcToJavaFile("Paths", RightWallToRightSwitch);
		BobPathGenerator.exportArcToJavaFile("Paths", RightWallToRightSwitchPt2);
		BobPathGenerator.exportArcToJavaFile("Paths", RightWallToLeftSide);
	}

	private static void generateLeftSidePaths(SrxTranslatorConfig config) {
		BobPath LeftWallToLeftScale = new BobPath(config, "LeftWallToLeftScale", -1);
		LeftWallToLeftScale.addWaypoint(0.0, 0.0, 0.0);
		LeftWallToLeftScale.addWaypoint(12.0, 0.0, 0.0);
		LeftWallToLeftScale.addWaypoint(22.5, -4.0, -15.0);

		BobPath ScaleToSwitchCubeLeftSide = new BobPath(config, "ScaleToSwitchCubeLeftSide", 1);
		ScaleToSwitchCubeLeftSide.addWaypoint(0.0, 0.0, 0.0);
		ScaleToSwitchCubeLeftSide.addWaypoint(5.0, 1.0, 20.0);

		BobPath LeftWallToLeftSwitch = new BobPath(config, "LeftWallToLeftSwitch", -1);
		LeftWallToLeftSwitch.addWaypoint(0.0, 0.0, 0.0);
		LeftWallToLeftSwitch.addWaypoint(17.0, 1.0, 30.0);

		BobPath LeftWallToLeftSwitchPt2 = new BobPath(config, "LeftWallToLeftSwitchPt2", 1);
		LeftWallToLeftSwitchPt2.addWaypoint(0.0, 0.0, 0.0);
		LeftWallToLeftSwitchPt2.addWaypoint(2.0, 1.0, 30.0);

		BobPath LeftWallToRightSide = new BobPath(config, "LeftWallToRightSide", -1);
		LeftWallToRightSide.addWaypoint(0.0, 0.0, 0.0);
		LeftWallToRightSide.addWaypoint(13.0, 0.0, 0.0);
		LeftWallToRightSide.addWaypoint(16.0, -3.5, -89.99);

		BobPathGenerator.exportPathToJavaFile("Paths", LeftWallToLeftScale);
		BobPathGenerator.exportPathToJavaFile("Paths", ScaleToSwitchCubeLeftSide);
		BobPathGenerator.exportPathToJavaFile("Paths", LeftWallToLeftSwitch);
		BobPathGenerator.exportPathToJavaFile("Paths", LeftWallToLeftSwitchPt2);
		BobPathGenerator.exportPathToJavaFile("Paths", LeftWallToRightSide);
	}
}
