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
import com.team319.ui.PathViewer;

/**
 * Forked from 254's 2014 Trajectory library just a comment to make a change
 * 
 * @author Jared341
 * @author ttremblay
 */
public class Main {

	public static void main(String[] args) {

		//SrxTrajectoryImporter importer = new SrxTrajectoryImporter("Paths");
		SrxTrajectoryExporter exporter = new SrxTrajectoryExporter("Paths");

		SrxTranslator.Config config = new SrxTranslator.Config();
		config.name = "BlueHopperAutoPt2";
		config.dt = .01;
		config.max_acc = 10.0;
		config.max_jerk = 60.0;
		config.max_vel = 15.0;
		config.wheelbase_width_feet = 23.25 / 12;
		config.wheel_dia_inches = 5.875;
		config.scale_factor = 2.778;
		config.direction = -1;

		// Description of this auto mode path.
		WaypointSequence p = new WaypointSequence(10);
		p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0));
		p.addWaypoint(new WaypointSequence.Waypoint(-3, -1.8, .8));

		Path path = BobPathGenerator.makePath(p, config);

		SrxTranslator srxt = new SrxTranslator();
		SrxTrajectory combined = srxt.getSrxTrajectoryFromChezyPath(path, config);

		if (!exporter.exportSrxTrajectory(combined, config, p)) {
			System.err.println("A path could not be written!!!!");
			System.exit(1);
		} else {
			///SrxTrajectory t = importer.importSrxTrajectory(config.name);
			PathViewer.showPath(path);
		}
	}
}
