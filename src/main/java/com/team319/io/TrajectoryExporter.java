package com.team319.io;

import java.util.List;

import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectorySet;
import com.team319.trajectory.BobPath;

public class TrajectoryExporter {

    public static void exportTrajectory(List<BobPath> paths) {
        for (BobPath path : paths) {
            try {
                Trajectory trajectory = PathGenerator.generateTrajectory(path.getWaypoints());
                TrajectorySet trajectorySet = PathGenerator.makeLeftAndRightTrajectories(trajectory);
                FilePrinter.write(path.getName() + ".left.csv", trajectorySet.left.toString());
                FilePrinter.write(path.getName() + ".right.csv", trajectorySet.right.toString());
                FilePrinter.write(path.getName() + ".center.csv", trajectorySet.center.toString());
            } catch (Exception e) { 
                e.printStackTrace();
            }
        }
    }
}
