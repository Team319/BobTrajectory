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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SrxTranslatorConfig gearBobConfig = new SrxTranslatorConfig();
		SrxTranslatorConfig powerUpConfig = new SrxTranslatorConfig();
		//length of robot is 38.5
		
		//Standard configs between all trajectories
		gearBobConfig.name = "gearBobConfig";
		gearBobConfig.dt = .01;
		gearBobConfig.max_acc = 10.0;
		gearBobConfig.max_jerk = 60.0;
		gearBobConfig.max_vel = 4.0; // gearbob was 6.0
		gearBobConfig.wheelbase_width_feet = 32.5/12.0;
		gearBobConfig.wheel_dia_inches = 3.5;
		gearBobConfig.scale_factor = 2.35; //0.899 // gearbob is 2.35
		gearBobConfig.encoder_ticks_per_rev = 1024;
		
		powerUpConfig.name = "powerUpConfig";
		powerUpConfig.dt = .01;
		powerUpConfig.max_acc = 10.0;
		powerUpConfig.max_jerk = 60.0;
		powerUpConfig.max_vel = 4.0; //8.0
		powerUpConfig.wheelbase_width_feet = 33.5/12.0;//23.5, then 29.5, 35.5
		powerUpConfig.wheel_dia_inches = 6.0;
		powerUpConfig.scale_factor = 3.49; //3.08 
		powerUpConfig.encoder_ticks_per_rev = 1024;
		
		
		SrxTranslatorConfig slowConfig = new SrxTranslatorConfig(gearBobConfig);
		slowConfig.max_vel = 4.0;
		
		
		//--------------------MISC. AUTOS----------//
		
		BobPath ThreeFeet = new BobPath(powerUpConfig, "ThreeFeet", 1);
		ThreeFeet.addWaypoint(0.0, 0.0, 0.0);
		ThreeFeet.addWaypoint(3.0, 0.0, 0.0);
		
		BobPath OneFoot = new BobPath(powerUpConfig, "OneFoot", 1);
		OneFoot.addWaypoint(0.0, 0.0, 0.0);
		OneFoot.addWaypoint(1.0, 0.0, 0.0);
		
		BobPath FiveFeetAndTurn = new BobPath(powerUpConfig, "FiveFeetAndTurn", 1);
		FiveFeetAndTurn.addWaypoint(0.0, 0.0, 0.0);
		FiveFeetAndTurn.addWaypoint(5.0, 5.0, 89.99);

		BobPath CrossTheLine = new BobPath(powerUpConfig, "CrossTheLine", 1);
		CrossTheLine.addWaypoint(0.0, 0.0, 0.0);
		CrossTheLine.addWaypoint(10.0, 0.0, 0.0);
		
		BobPath TestSTurnAuto = new BobPath(powerUpConfig, "TestSTurnAuto", 1);
		TestSTurnAuto.addWaypoint(0.0, 0.0, 0.0);
		TestSTurnAuto.addWaypoint(8.375, 5.5, 0.0);
		
		
		//--------------------RIGHT SIDE AUTOS----------//
		
		BobPath CenterToRightSwitch = new BobPath(powerUpConfig, "CenterToRightSwitch", 1); //3 seconds
		CenterToRightSwitch.addWaypoint(0.0, 0.0, 0.0);
		CenterToRightSwitch.addWaypoint(8.375, -5.5, 0.0);
		
		BobPath CenterToRightSwitchPt2 = new BobPath(powerUpConfig, "CenterToRightSwitchPt2", -1); //4 seconds
		CenterToRightSwitchPt2.addWaypoint(0.0, 0.0, 0.0);
		CenterToRightSwitchPt2.addWaypoint(-6.5, 0.0, 0.0);//45
		
		BobPath CenterToRightSwitchPt3 = new BobPath(powerUpConfig, "CenterToRightSwitchPt3", 1);
		CenterToRightSwitchPt3.addWaypoint(0.0, 0.0, 0.0);
		CenterToRightSwitchPt3.addWaypoint(3.25, 5.25, 45.0);
		
		BobPath CenterToRightSwitchPt4 = new BobPath(powerUpConfig, "CenterToRightSwitchPt4", -1);
		CenterToRightSwitchPt4.addWaypoint(0.0, 0.0, 0.0);
		CenterToRightSwitchPt4.addWaypoint(-3.25, -5.25, 45.0);
		
		BobPath CenterToRightSwitchPt5 = new BobPath(powerUpConfig, "CenterToRightSwitchPt5", 1);
		CenterToRightSwitchPt5.addWaypoint(0.0, 0.0, 0.0);
		CenterToRightSwitchPt5.addWaypoint(6.5, 0.0, 0.0);
		//--------------------LEFT SIDE AUTOS----------//

		
		BobPath CenterToLeftSwitch =new BobPath(powerUpConfig, "CenterToLeftSwitch", 1);
		CenterToLeftSwitch.addWaypoint(0.0, 0.0, 0.0);
		CenterToLeftSwitch.addWaypoint(8.375, 5.5, 0.0);
		
		BobPath CenterToLeftSwitchPt2 = new BobPath(powerUpConfig, "CenterToLeftSwitchPt2", -1);
		CenterToLeftSwitchPt2.addWaypoint(0.0, 0.0, 0.0);
		CenterToLeftSwitchPt2.addWaypoint(-6.5, 0.0, 0.0);//-45

		
		BobPath CenterToLeftSwitchPt3 = new BobPath(powerUpConfig, "CenterToLeftSwitchPt3", 1);
		CenterToLeftSwitchPt3.addWaypoint(0.0, 0.0, 0.0);
		CenterToLeftSwitchPt3.addWaypoint(3.25, -5.25, 45.0);
		
		BobPath CenterToLeftSwitchPt4 = new BobPath(powerUpConfig, "CenterToLeftSwitchPt4", -1);
		CenterToLeftSwitchPt4.addWaypoint(0.0, 0.0, 0.0);
		CenterToLeftSwitchPt4.addWaypoint(-3.25, 5.25, 45.0);
		
		BobPath CenterToLeftSwitchPt5 = new BobPath(powerUpConfig, "CenterToLeftSwitchPt5", 1);
		CenterToLeftSwitchPt5.addWaypoint(0.0, 0.0, 0.0);
		CenterToLeftSwitchPt5.addWaypoint(6.5, 0.0, 0.0);

		
		//--------------------EXPORTERS----------//
				
				BobPathGenerator.exportPathToJavaFile("Paths", ThreeFeet);
				//BobPathGenerator.exportPath("Paths", OneFoot);
				BobPathGenerator.exportPathToJavaFile("Paths", FiveFeetAndTurn);
				//BobPathGenerator.exportPath("Paths", CrossTheLine);
				BobPathGenerator.exportPath("Paths", TestSTurnAuto);
				BobPathGenerator.exportPathToJavaFile("Paths", CrossTheLine);
				BobPathGenerator.exportPathToJavaFile("Paths", CenterToRightSwitch);
				//BobPathGenerator.exportPath("Paths", CenterToRightSwitchPt2);
				//BobPathGenerator.exportPath("Paths", CenterToRightSwitchPt3);
				//BobPathGenerator.exportPath("Paths", CenterToRightSwitchPt4);
				//BobPathGenerator.exportPath("Paths", CenterToRightSwitchPt5);
				BobPathGenerator.exportPathToJavaFile("Paths", CenterToLeftSwitch);
				//BobPathGenerator.exportPath("Paths", CenterToLeftSwitchPt2);
				//BobPathGenerator.exportPath("Paths", CenterToLeftSwitchPt3);
				//BobPathGenerator.exportPath("Paths", CenterToLeftSwitchPt4);
				//BobPathGenerator.exportPath("Paths", CenterToLeftSwitchPt5);
				
				
		//BobPathGenerator.appendAndExportPaths("Paths", "appendedPath", false, blueHopperThenShootAutoLeftSidePt2, toAppend);
		//BobPathGenerator.appendAndExportPaths("Paths", "appendedAndFlippedPath", true, blueHopperThenShootAutoLeftSidePt2, toAppend); 
		//redGear.exportPathWithSerializer(new VelocityOnlyFileSerializer(), "Paths");
	}
}
