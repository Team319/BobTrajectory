package com.team319;

import com.team254.lib.trajectory.WaypointSequence;
import com.team254.lib.trajectory.io.VelocityOnlyFileSerializer;
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
		//SrxTranslator translator = new SrxTranslator();
		SrxTranslatorConfig standardConfig = new SrxTranslatorConfig();
		
		//length of robot is 38.5
		
		//Standard configs between all trajectories
		standardConfig.name = "StandardConfig";
		standardConfig.dt = .01;
		standardConfig.max_acc = 10.0;
		standardConfig.max_jerk = 60.0;
		standardConfig.max_vel = 4.0; // gearbob was 6.0
		standardConfig.wheelbase_width_feet = 32.5/12.0;
		standardConfig.wheel_dia_inches = 3.5;
		standardConfig.scale_factor = 2.35; //0.899 // gearbob is 2.35
		standardConfig.encoder_ticks_per_rev = 1024;
		
		SrxTranslatorConfig slowConfig = new SrxTranslatorConfig(standardConfig);
		slowConfig.max_vel = 4.0;
		
		
		//--------------------MISC. AUTOS----------//
		
		BobPath ThreeFeet = new BobPath(standardConfig, "ThreeFeet", 1);
		ThreeFeet.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		ThreeFeet.addWaypoint(new WaypointSequence.Waypoint(3.0, 0.0, Math.toRadians(0.0)));
		
		BobPath OneFoot = new BobPath(standardConfig, "OneFoot", 1);
		OneFoot.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		OneFoot.addWaypoint(new WaypointSequence.Waypoint(1.0, 0.0, Math.toRadians(0.0)));
		
		BobPath FiveFeetAndTurn = new BobPath(standardConfig, "FiveFeetAndTurn", 1);
		FiveFeetAndTurn.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		FiveFeetAndTurn.addWaypoint(new WaypointSequence.Waypoint(5.0, 5.0, Math.toRadians(89.99)));

		BobPath CrossTheLine = new BobPath(standardConfig, "CrossTheLine", 1);
		CrossTheLine.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		CrossTheLine.addWaypoint(new WaypointSequence.Waypoint(10.0, 0.0, Math.toRadians(0.0)));
		
		
		//--------------------RIGHT SIDE AUTOS----------//
		
		BobPath CenterToRightSwitch = new BobPath(standardConfig, "CenterToRightSwitch", 1); //3 seconds
		CenterToRightSwitch.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		CenterToRightSwitch.addWaypoint(new WaypointSequence.Waypoint(8.375, -5.5, Math.toRadians(0.0)));
		
		BobPath CenterToRightSwitchPt2 = new BobPath(standardConfig, "CenterToRightSwitchPt2", -1); //4 seconds
		CenterToRightSwitchPt2.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		CenterToRightSwitchPt2.addWaypoint(new WaypointSequence.Waypoint(-6.5, 0.0, Math.toRadians(0.0)));//45
		
		BobPath CenterToRightSwitchPt3 = new BobPath(standardConfig, "CenterToRightSwitchPt3", 1);
		CenterToRightSwitchPt3.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		CenterToRightSwitchPt3.addWaypoint(new WaypointSequence.Waypoint(3.25, 5.25, Math.toRadians(45)));
		
		BobPath CenterToRightSwitchPt4 = new BobPath(standardConfig, "CenterToRightSwitchPt4", -1);
		CenterToRightSwitchPt4.addWaypoint(new WaypointSequence.Waypoint(0.0, 0.0, 0.0));
		CenterToRightSwitchPt4.addWaypoint(new WaypointSequence.Waypoint(-3.25, -5.25, Math.toRadians(45)));
		
		BobPath CenterToRightSwitchPt5 = new BobPath(standardConfig, "CenterToRightSwitchPt5", 1);
		CenterToRightSwitchPt5.addWaypoint(new WaypointSequence.Waypoint(0.0, 0.0, 0.0));
		CenterToRightSwitchPt5.addWaypoint(new WaypointSequence.Waypoint(6.5, 0.0, Math.toRadians(0.0)));
		//--------------------LEFT SIDE AUTOS----------//

		
		BobPath CenterToLeftSwitch =new BobPath(standardConfig, "CenterToLeftSwitch", 1);
		CenterToLeftSwitch.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		CenterToLeftSwitch.addWaypoint(new WaypointSequence.Waypoint(8.375, 5.5, Math.toRadians(0.0)));
		
		BobPath CenterToLeftSwitchPt2 = new BobPath(standardConfig, "CenterToLeftSwitchPt2", -1);
		CenterToLeftSwitchPt2.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		CenterToLeftSwitchPt2.addWaypoint(new WaypointSequence.Waypoint(-6.5, 0.0, Math.toRadians(0.0)));//-45

		
		BobPath CenterToLeftSwitchPt3 = new BobPath(standardConfig, "CenterToLeftSwitchPt3", 1);
		CenterToLeftSwitchPt3.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		CenterToLeftSwitchPt3.addWaypoint(new WaypointSequence.Waypoint(3.25, -5.25, Math.toRadians(-45)));
		
		BobPath CenterToLeftSwitchPt4 = new BobPath(standardConfig, "CenterToLeftSwitchPt4", -1);
		CenterToLeftSwitchPt4.addWaypoint(new WaypointSequence.Waypoint(0.0, 0.0, 0.0));
		CenterToLeftSwitchPt4.addWaypoint(new WaypointSequence.Waypoint(-3.25, 5.25, Math.toRadians(-45)));
		
		BobPath CenterToLeftSwitchPt5 = new BobPath(standardConfig, "CenterToLeftSwitchPt5", 1);
		CenterToLeftSwitchPt5.addWaypoint(new WaypointSequence.Waypoint(0.0, 0.0, 0.0));
		CenterToLeftSwitchPt5.addWaypoint(new WaypointSequence.Waypoint(6.5, 0.0, 0.0));
		
		//--------------------EXPORTERS----------//
				
				//BobPathGenerator.exportPath("Paths", ThreeFeet);
				//BobPathGenerator.exportPath("Paths", OneFoot);
				BobPathGenerator.exportPath("Paths", FiveFeetAndTurn);
				BobPathGenerator.exportPath("Paths", CrossTheLine);
				//BobPathGenerator.exportPath("Paths", CenterToRightSwitch);
				//BobPathGenerator.exportPath("Paths", CenterToRightSwitchPt2);
				//BobPathGenerator.exportPath("Paths", CenterToRightSwitchPt3);
				//BobPathGenerator.exportPath("Paths", CenterToRightSwitchPt4);
				//BobPathGenerator.exportPath("Paths", CenterToRightSwitchPt5);
				//BobPathGenerator.exportPath("Paths", CenterToLeftSwitch);
				//BobPathGenerator.exportPath("Paths", CenterToLeftSwitchPt2);
				//BobPathGenerator.exportPath("Paths", CenterToLeftSwitchPt3);
				//BobPathGenerator.exportPath("Paths", CenterToLeftSwitchPt4);
				//BobPathGenerator.exportPath("Paths", CenterToLeftSwitchPt5);
				
				
		//BobPathGenerator.appendAndExportPaths("Paths", "appendedPath", false, blueHopperThenShootAutoLeftSidePt2, toAppend);
		//BobPathGenerator.appendAndExportPaths("Paths", "appendedAndFlippedPath", true, blueHopperThenShootAutoLeftSidePt2, toAppend); 
		//redGear.exportPathWithSerializer(new VelocityOnlyFileSerializer(), "Paths");
	}
}
