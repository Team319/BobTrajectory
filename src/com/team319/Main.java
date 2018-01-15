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

	public static void main(String[] args) {
		//SrxTranslator translator = new SrxTranslator();
		SrxTranslatorConfig standardConfig = new SrxTranslatorConfig();
		
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
		
		
		
		BobPath ThreeFeet = new BobPath(standardConfig, "ThreeFeet", 1);
		ThreeFeet.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		ThreeFeet.addWaypoint(new WaypointSequence.Waypoint(3.0, 0.0, Math.toRadians(0.0)));
		
		BobPath OneFoot = new BobPath(standardConfig, "OneFoot", 1);
		OneFoot.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		OneFoot.addWaypoint(new WaypointSequence.Waypoint(1.0, 0.0, Math.toRadians(0.0)));
		
	

				BobPathGenerator.exportPath("Paths", ThreeFeet);
				BobPathGenerator.exportPath("Paths", OneFoot);
			
		
		//BobPathGenerator.appendAndExportPaths("Paths", "appendedPath", false, blueHopperThenShootAutoLeftSidePt2, toAppend);
		//BobPathGenerator.appendAndExportPaths("Paths", "appendedAndFlippedPath", true, blueHopperThenShootAutoLeftSidePt2, toAppend); 
		//redGear.exportPathWithSerializer(new VelocityOnlyFileSerializer(), "Paths");
	}
}
