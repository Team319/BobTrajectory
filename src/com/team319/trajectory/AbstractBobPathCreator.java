
package com.team319.trajectory;

import java.util.Arrays;
import java.util.List;

import com.team254.lib.trajectory.WaypointSequence.Waypoint;

/**
 * Forked from 254's 2014 Trajectory library just a comment to make a change
 * 
 * @author Jared341
 * @author ttremblay
 */
public abstract class AbstractBobPathCreator {

	public SrxTranslatorConfig config;

	/**
	 * Generate the path files, to include config paths, display the paths in GUIS, and 
	 * move the files into the robot code project
	 */
	public void generatePaths() {
		config = getConfigFile();
		generateArcFiles(getConfigArcs());
		generateArcFiles(getArcs());
		copyArcsToRobotCode();
	}

	/**
	 * Return the list of arcs that are to be generated
	 */
	protected abstract List<BobPath> getArcs(); 

	/**
	 * Return the configuration file that logically defines the robot's characteristics.
	 */
	protected abstract SrxTranslatorConfig getConfigFile();

	/**
	 * Return the project name of the robot code project
	 */
	protected abstract String getRobotProjectName();

	/**
	 * Generate the configuration arcs, distance, turning, and speed
	 * DistanceScaling - This path will run 3 feet forward and stop. To tune this
	 * adjust the scaling factor until the robot stops at exactly 3 feet.
	 * TurnScaling - This path will run 3 feet forward and 3 feet to the left, this will 
	 * end at 90 degrees. This path can be used when tuning your heading loop for arc mode.
	 * SpeedTesting - This path will drive 3 feet forward and 3 feet to the left at 3 FPS,
	 * then drive another 3 feed forward and 3 feet to the left. This path will end with 
	 * the robot 6 feet to the left of it's starting position facing the oppostite direction.
	 */
	protected List<BobPath> getConfigArcs() {
		BobPath distanceScaling = new BobPath(config, "DistanceScaling");
		distanceScaling.addWaypoint(new Waypoint(0, 0, 0, 0, 0));
		distanceScaling.addWaypointRelative(3, 0, 0, 0, 3);

		BobPath turnScaling = new BobPath(config, "TurnScaling");
		turnScaling.addWaypoint(new Waypoint(0, 0, 0, 0, 0));
		turnScaling.addWaypointRelative(3, 3, 89.99, 0, 3);

		BobPath speedTesting = new BobPath(config, "SpeedTesting");
		speedTesting.addWaypoint(new Waypoint(0, 0, 0, 0, 0));
		speedTesting.addWaypointRelative(3, 3, 89.99, 3, 3);
		speedTesting.addWaypointRelative(-3, 3, 89.99, 0, 1);

		return Arrays.asList(distanceScaling, turnScaling, speedTesting);
	}

	private void generateArcFiles(List<BobPath> paths) {
		for (BobPath path : paths) {
			BobPathGenerator.exportArcToJavaFile(path);
		}
	}
	
	private void copyArcsToRobotCode() {
		try {
		StringBuilder codePath = new StringBuilder()
		.append("..\\")
		.append(getRobotProjectName())
		.append("\\src\\main\\java\\frc\\arcs");

		BobPathGenerator.copyFilesToRelativeDirectory("Arcs", codePath.toString());
		} catch (Exception e) {
			System.out.println("Files could not be copied to the robot project");
			e.printStackTrace();
		}
	}
}
