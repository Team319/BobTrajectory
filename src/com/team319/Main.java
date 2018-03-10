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
	
	public static double robotTotalWidthFeet = 33.0/12.0;
	public static double robotTotalLengthFeet = 39.0/12.0;
	
	public static Waypoint rightSideStartingWaypoint = new Waypoint(robotTotalLengthFeet/2, 45.5/12.0, 0);
	public static Waypoint leftSideStartingWaypoint = new Waypoint(robotTotalLengthFeet/2, 277.8/12.0, 0);
	public static Waypoint centerStartingWaypoint = new Waypoint(robotTotalLengthFeet/2, 157.0/12.0, 0);

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
		powerUpHighGearConfig.max_vel = 8.0;
		powerUpHighGearConfig.max_acc = 6.0;
		powerUpHighGearConfig.scale_factor = 7.83; // 10.46
		powerUpHighGearConfig.wheelbase_width_feet = 23.5/12.0;

		generateMisc(powerUpHighGearConfig);
		generateCenterSwitch(powerUpHighGearConfig);
		generateRightSide(powerUpHighGearConfig);
		generateLeftSide(powerUpHighGearConfig);
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

		BobPath BackwardsThreeFeet = new BobPath(config, "BackwardsThreeFeet", -1);
		BackwardsThreeFeet.addWaypoint(0.0, 0.0, 0.0);
		BackwardsThreeFeet.addWaypoint(3.0, 0.0, 0.0);

		BobPathGenerator.exportArcToJavaFile(ThreeFeet);
		BobPathGenerator.exportArcToJavaFile(OneFoot);
		BobPathGenerator.exportArcToJavaFile(FiveFeetAndTurn);
		BobPathGenerator.exportArcToJavaFile(CrossTheLine);
		BobPathGenerator.exportArcToJavaFile(TestSTurnAuto);
		BobPathGenerator.exportArcToJavaFile(FifteenFeet);
		BobPathGenerator.exportArcToJavaFile(BackwardsThreeFeet);
	}

	private static void generateCenterSwitch(SrxTranslatorConfig config) {

		// --------------------RIGHT SIDE AUTOS----------//
		BobPath CenterToRightSwitch = new BobPath(config, "CenterToRightSwitch", 1); // 3 seconds
		CenterToRightSwitch.addWaypoint(centerStartingWaypoint);
		CenterToRightSwitch.addWaypointRelative(8.375, -5.0, 0.0);

		BobPath CenterToRightSwitchPt2 = new BobPath(config, "CenterToRightSwitchPt2", -1); // 4 seconds
		CenterToRightSwitchPt2.addWaypoint(CenterToRightSwitch.getLastWaypoint());
		CenterToRightSwitchPt2.addWaypointRelative(-6.375, 5.0, 0.0);// 45

		BobPath CenterToRightSwitchPt3 = new BobPath(config, "CenterToRightSwitchPt3", 1);
		CenterToRightSwitchPt3.addWaypoint(CenterToRightSwitchPt2.getLastWaypoint());
		CenterToRightSwitchPt3.addWaypointRelative(4.0, 0.0, 0.0);

		BobPath CenterToRightSwitchPt4 = new BobPath(config, "CenterToRightSwitchPt4", -1);
		CenterToRightSwitchPt4.addWaypoint(CenterToRightSwitchPt3.getLastWaypoint());
		CenterToRightSwitchPt4.addWaypointRelative(-3.19, 0.0, 0.0);

		BobPath CenterToRightSwitchPt5 = new BobPath(config, "CenterToRightSwitchPt5", 1);
		CenterToRightSwitchPt5.addWaypoint(CenterToRightSwitchPt4.getLastWaypoint());
		CenterToRightSwitchPt5.addWaypointRelative(6.375, -5.0, 0.0);

		// --------------------LEFT SIDE AUTOS----------//

		BobPath CenterToLeftSwitch = new BobPath(config, "CenterToLeftSwitch", 1);
		CenterToLeftSwitch.addWaypoint(centerStartingWaypoint);
		CenterToLeftSwitch.addWaypointRelative(8.375, 6.0, 0.0);

		BobPath CenterToLeftSwitchPt2 = new BobPath(config, "CenterToLeftSwitchPt2", -1);
		CenterToLeftSwitchPt2.addWaypoint(CenterToLeftSwitch.getLastWaypoint());
		CenterToLeftSwitchPt2.addWaypointRelative(-6.375, -6.0, 0.0);// -45

		BobPath CenterToLeftSwitchPt3 = new BobPath(config, "CenterToLeftSwitchPt3", 1);
		CenterToLeftSwitchPt3.addWaypoint(CenterToLeftSwitchPt2.getLastWaypoint());
		CenterToLeftSwitchPt3.addWaypointRelative(4.0, 0.0, 0.0);

		BobPath CenterToLeftSwitchPt4 = new BobPath(config, "CenterToLeftSwitchPt4", -1);
		CenterToLeftSwitchPt4.addWaypoint(CenterToLeftSwitchPt3.getLastWaypoint());
		CenterToLeftSwitchPt4.addWaypointRelative(-3.10, 0.0, 0.0);

		BobPath CenterToLeftSwitchPt5 = new BobPath(config, "CenterToLeftSwitchPt5", 1);
		CenterToLeftSwitchPt5.addWaypoint(CenterToLeftSwitchPt4.getLastWaypoint());
		CenterToLeftSwitchPt5.addWaypointRelative(6.375, 5.0, 0.0);

		BobPathGenerator.exportArcToJavaFile(CenterToRightSwitch);
		BobPathGenerator.exportArcToJavaFile(CenterToRightSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(CenterToRightSwitchPt3);
		BobPathGenerator.exportArcToJavaFile(CenterToRightSwitchPt4);
		BobPathGenerator.exportArcToJavaFile(CenterToRightSwitchPt5);

		BobPathGenerator.exportArcToJavaFile(CenterToLeftSwitch);
		BobPathGenerator.exportArcToJavaFile(CenterToLeftSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(CenterToLeftSwitchPt3);
		BobPathGenerator.exportArcToJavaFile(CenterToLeftSwitchPt4);
		BobPathGenerator.exportArcToJavaFile(CenterToLeftSwitchPt5);

	}

	private static void generateRightSide(SrxTranslatorConfig config) {
		
		BobPath RightWallToRightScale = new BobPath(config, "RightWallToRightScale", -1);
		RightWallToRightScale.addWaypoint(rightSideStartingWaypoint);
		RightWallToRightScale.addWaypointRelative(12.0, 0.0, 0.0);
		RightWallToRightScale.addWaypointRelative(10.5, 4.0, 15.0);

		BobPath ScaleToSwitchCubeRightSide = new BobPath(config, "ScaleToSwitchCubeRightSide", 1);
		ScaleToSwitchCubeRightSide.addWaypoint(RightWallToRightScale.getLastWaypoint());
		ScaleToSwitchCubeRightSide.addWaypointRelative(-5.0, -1.0, -20.0);

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

		BobPathGenerator.exportArcToJavaFile(RightWallToRightScale);
		BobPathGenerator.exportArcToJavaFile(ScaleToSwitchCubeRightSide);
		BobPathGenerator.exportArcToJavaFile(RightWallToRightSwitch);
		BobPathGenerator.exportArcToJavaFile(RightWallToRightSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(RightWallToLeftSide);
	}

	private static void generateLeftSide(SrxTranslatorConfig config) {
		
		BobPath LeftWallToLeftScale = new BobPath(config, "LeftWallToLeftScale", -1);
		LeftWallToLeftScale.addWaypoint(leftSideStartingWaypoint);
		LeftWallToLeftScale.addWaypointRelative(12.0, 0.0, 0.0);
		LeftWallToLeftScale.addWaypointRelative(10.5, -4.0, -15.0);

		BobPath ScaleToSwitchCubeLeftSide = new BobPath(config, "ScaleToSwitchCubeLeftSide", 1);
		ScaleToSwitchCubeLeftSide.addWaypoint(LeftWallToLeftScale.getLastWaypoint());
		ScaleToSwitchCubeLeftSide.addWaypointRelative(-5.0, 1.0, 20.0);

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

		BobPathGenerator.exportArcToJavaFile(LeftWallToLeftScale);
		BobPathGenerator.exportArcToJavaFile(ScaleToSwitchCubeLeftSide);
		BobPathGenerator.exportArcToJavaFile(LeftWallToLeftSwitch);
		BobPathGenerator.exportArcToJavaFile(LeftWallToLeftSwitchPt2);
		BobPathGenerator.exportArcToJavaFile(LeftWallToRightSide);
	}
}
