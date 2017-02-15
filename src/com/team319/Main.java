package com.team319;

import com.team254.lib.trajectory.WaypointSequence;
import com.team254.lib.trajectory.io.VelocityOnlyFileSerializer;
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
		blueHopperAutoPt2.waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		blueHopperAutoPt2.waypointSequence.addWaypoint(new WaypointSequence.Waypoint(-4, -1, Math.toRadians(45)));
		
		
		SrxTranslatorConfig thConfig = new SrxTranslatorConfig();
		thConfig.name = "TripleHelixConfig";
		thConfig.dt = .01;
		thConfig.max_acc = 10.0;
		thConfig.max_jerk = 60.0;
		thConfig.max_vel = 5.0;
		thConfig.wheelbase_width_feet = 27/12.0; //23.25 / 12  - original, Jhanson
		thConfig.wheel_dia_inches = 3.5;
		thConfig.scale_factor = .743;
		
		BobPathGenerator redGear = new BobPathGenerator(thConfig);
		redGear.config.name = "RedGear";
		redGear.config.direction = 1;
		redGear.waypointSequence.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		redGear.waypointSequence.addWaypoint(new WaypointSequence.Waypoint(7.4, 2, Math.toRadians(60)));

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
		redGear.exportPathWithSerializer(new VelocityOnlyFileSerializer(), "Paths");
	}
}
