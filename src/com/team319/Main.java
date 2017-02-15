package com.team319;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.WaypointSequence;
import com.team319.trajectory.BobPathGenerator;
import com.team319.trajectory.BobWaypointSequence;
import com.team319.trajectory.SrxTrajectory;
import com.team319.trajectory.SrxTrajectoryExporter;
import com.team319.trajectory.SrxTrajectoryImporter;
import com.team319.trajectory.SrxTranslator;
import com.team319.trajectory.SrxTranslatorConfig;
import com.team319.ui.PathViewer;

/**
 * Forked from 254's 2014 Trajectory library just a comment to make a change
 * 
 * @author Jared341
 * @author ttremblay
 */
public class Main {

	public static void main(String[] args) {
		//SrxTranslator translator = new SrxTranslator();
		SrxTranslatorConfig standardConfig = new SrxTranslatorConfig();
		
		//Standard configs between all trajectories
		standardConfig.name = "StandardConfig";
		standardConfig.dt = .01;
		standardConfig.max_acc = 20.0;
		standardConfig.max_jerk = 60.0;
		standardConfig.max_vel = 4.0;
		standardConfig.wheelbase_width_feet = 32.5/12.0; //23.25 / 12  - original, Jhanson
		standardConfig.wheel_dia_inches = 3.5;
		standardConfig.scale_factor = .743;
		
		//config.name = "BlueHopperAutoPt1";
		//config.name = "BlueHopperAutoPt2";
		//config.name = "BlueHopperAutoPt3";
		//config.name = "RedHopperAutoPt1";
		//config.name = "RedHopperAutoPt2";
		//config.name = "RedHopperAutoPt3";
		//config.name = "DriveForwardFiveFeet";

		//config.direction = 1;
		
		BobPathGenerator blueHopperAutoPt1 = new BobPathGenerator(standardConfig);
		blueHopperAutoPt1.config.name = "BlueHopperAutoPt1";
		blueHopperAutoPt1.config.direction = 1;		
		blueHopperAutoPt1.waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		blueHopperAutoPt1.waypointSequence.addWaypoint(new WaypointSequence.Waypoint(5.5, .625, Math.toRadians(35.0)));
		blueHopperAutoPt1.waypointSequence.addWaypoint(new WaypointSequence.Waypoint(6.75, 2.75, Math.toRadians(90.0)));
		
		BobPathGenerator blueHopperAutoPt2 = new BobPathGenerator(standardConfig);
		blueHopperAutoPt2.config.name = "BlueHopperAutoPt2";
		blueHopperAutoPt2.config.direction = -1;
		blueHopperAutoPt2.waypointSequence.addWaypoint(new WaypointSequence.Waypoint(6.75, 2.75, Math.toRadians(90.0)));
		blueHopperAutoPt2.waypointSequence.addWaypoint(new WaypointSequence.Waypoint(10, 0, Math.toRadians(179)));
		
		blueHopperAutoPt1.appendPath(blueHopperAutoPt2.getPath());
		
		
		

		// Description of this auto mode path.
		//WaypointSequence p = new WaypointSequence(10);
		//p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		//p.addWaypoint(new WaypointSequence.Waypoint(5.5, .625, Math.toRadians(35.0)));//BHApt1
		//p.addWaypoint(new WaypointSequence.Waypoint(6.75, 2.75, Math.toRadians(90.0)));//BHApt1//3.5
		//p.addWaypoint(new WaypointSequence.Waypoint(-4, -1, Math.toRadians(45)));//BHApt2
		//p.addWaypoint(new WaypointSequence.Waypoint(6.5, 2, Math.toRadians(-5)));//BHApt3
		
		//p.addWaypoint(new WaypointSequence.Waypoint(5.5, .625, Math.toRadians(35.0)));//RHApt1
		//p.addWaypoint(new WaypointSequence.Waypoint(6.75, 2.75, Math.toRadians(90.0)));//RHApt1
		//p.addWaypoint(new WaypointSequence.Waypoint(-4, -1, Math.toRadians(45)));//RHApt2
		//p.addWaypoint(new WaypointSequence.Waypoint(6.5, 2, Math.toRadians(-5)));//RHApt3
		
		//p.addWaypoint(new WaypointSequence.Waypoint(5, 0, 0));//drive forward 5 feet
		//p.addWaypoint(new WaypointSequence.Waypoint(15, 0, 0));//drive forward 15 feet
		
		
		
		blueHopperAutoPt1.exportPath("Paths");
		blueHopperAutoPt2.exportPath("Paths");
	}
}
