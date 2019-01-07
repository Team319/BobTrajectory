package com.team319;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.team254.lib.trajectory.WaypointSequence.Waypoint;
import com.team319.trajectory.AbstractBobPathCreator;
import com.team319.trajectory.BobPath;
import com.team319.trajectory.SrxTranslatorConfig;

public class BobPathCreator extends AbstractBobPathCreator {

    private static double robotWidthInFeet = 33.0 / 12.0;
	private static double robotLengthInFeet = 39.0 / 12.0;

	// This point and points like it can be used when there are common starting locatons for the robot
	// Remember that paths should be generated from the center point of the robot
	private static Waypoint startingPoint = new Waypoint(robotLengthInFeet / 2.0, 45.5 / 12.0, 0, 0, 0);
	
	private SrxTranslatorConfig config = new SrxTranslatorConfig();
    
    public static void main(String[] args) {
        new BobPathCreator().generatePaths();
	}
	
	private BobPathCreator() {
		config.max_acc = 8.0; // Maximum acceleration in FPS
		config.max_vel = 10.0; // Maximum velocity in FPS
		config.wheel_dia_inches = 4.0;
		config.scale_factor = 1.0; // Used to adjust for a gear ratio and or distance tuning
		config.encoder_ticks_per_rev = 4096; // Count of ticks on your encoder
		config.robotLength = 39; // Robot length in inches, used for drawing the robot
		config.robotWidth = 33; // Robot width in inches, used for drawing the robot
		config.highGear = true;
	}

    @Override
    protected List<BobPath> getArcs() {
		List<BobPath> paths = new ArrayList<>();
		paths.addAll(getConfigArcs());
		paths.addAll(generateTeamPaths());
        return paths;
	}

	/**
	 * Use this method to generate team paths. You can create more methods like this one to organize your path, 
	 * just make sure to add the method call to the returned list in getArcs()
	 * @return the list of team paths to generate
	 */
	private List<BobPath> generateTeamPaths() {
		 // Create a path with the name of "Example", this will generate a file named ExampleArc
		 BobPath exampleArc = new BobPath(config, "Example");
		 // Set the first point to the starating point, this be done with any of the addWaypoint methods
		 // positive X is forward, positive Y is left, units are in feet and degrees
		 exampleArc.addWaypoint(startingPoint);
		 // Add the next point that 3 ft forward, and doesn't turn, it also has a max speed of 5 FPS, 
		 // it will arrive at this location going 2 FPS
		 exampleArc.addWaypointRelative(3, 0, 0, 2, 5);
		 // Add the next point to be an additional 5 feet forward and 5 feet to the left with max speed of 2 FPS,
		 // it  will arrive at this locaton going 0 FPS 
		 exampleArc.addWaypointRelative(5, 5, 0, 0, 2);

		 BobPath flower = new BobPath(config, "Flower");
		 flower.addWaypoint(10, 10, 0, 0, 0);
		 flower.addWaypoint(12, 12, 45, 2, 2);
		 flower.addWaypoint(14, 14, 90, 2, 2);
		 flower.addWaypoint(12, 16, 135, 2, 2);
		 flower.addWaypoint(10, 18, 180, 2, 2);
		 flower.addWaypoint(8, 16, 225, 2, 2);
		 flower.addWaypoint(6, 14, 270, 2, 2);
		 flower.addWaypoint(8, 12, 315, 2, 2);
		 flower.addWaypoint(10, 10, 0, 0, 2);

		 BobPath imageCalibrator = new BobPath(config, "ImageCalibrator");
		 imageCalibrator.addWaypoint(0, 10, 0, 0, 0);
		 imageCalibrator.addWaypoint(27, 10, 0, 0, 10);
		 
		 return asList(exampleArc, flower, imageCalibrator); // return asList(path1, path2, path3, ...);
	}
	
	
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
	private List<BobPath> getConfigArcs() {
		BobPath distanceScaling = new BobPath(config, "DistanceScaling");
		distanceScaling.addWaypoint(new Waypoint(2, 13.5, 0, 0, 0));
		distanceScaling.addWaypointRelative(3, 0, 0, 0, 3);

		BobPath turnScaling = new BobPath(config, "TurnScaling");
		turnScaling.addWaypoint(new Waypoint(2, 13.5, 0, 0, 0));
		turnScaling.addWaypointRelative(3, 3, 89.99, 0, 3);


		BobPath speedTesting = new BobPath(config, "SpeedTesting");
		speedTesting.addWaypoint(new Waypoint(2, 13.5, 0, 0, 0));
		speedTesting.addWaypointRelative(3, 3, 89.99, 1, 3);
		speedTesting.addWaypointRelative(-3, 3, 89.99, 0, 1);

		return asList(distanceScaling, turnScaling, speedTesting);
	}
}