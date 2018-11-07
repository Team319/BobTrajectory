package com.team319;

import static java.util.Arrays.asList;

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
    public static Waypoint startingPoint = new Waypoint(robotLengthInFeet / 2.0, 45.5 / 12.0, 0, 0, 0);
    
    public static void main(String[] args) {
        new BobPathCreator().generatePaths();
    }

    @Override
    protected List<BobPath> getArcs() {
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
        
        return asList(exampleArc); // return asList(path1, path2, path3, ...);
    }

    @Override
    public SrxTranslatorConfig getConfigFile() {
        config = new SrxTranslatorConfig();
		config.max_acc = 8.0;
		config.max_jerk = 60.0;
		config.max_vel = 10.0; // 8.0
		config.wheelbase_width_feet = 23.5 / 12.0;// 23.5, then 29.5, 35.5
		config.wheel_dia_inches = 6.0;
		config.scale_factor = 7.43; //UNH value is 5.75, practice bot value is 7.42
		config.encoder_ticks_per_rev = 4096;
		config.robotLength = 39;
        config.robotWidth = 33;
        config.highGear = true;

        return config;
    }
}