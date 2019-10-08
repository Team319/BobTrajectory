package com.team319.io;

import java.util.List;

import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectorySet;
import com.team319.trajectory.BobPath;
import com.team319.trajectory.RobotConfig;

public class TrajectoryExporter {

    public static void exportTrajectory(List<BobPath> paths) {
        for (BobPath path : paths) {
            try {
                Trajectory trajectory = PathGenerator.generateTrajectory(path.getWaypoints());
                TrajectorySet trajectorySet = PathGenerator.makeLeftAndRightTrajectories(trajectory);
                switch (RobotConfig.exportType) {
                    case CLASS:
                        exportToClass(path.getName(), trajectorySet);
                        break;
                    case FILE:
                        exportToFile(path.getName(), trajectorySet);
                        break;
                }
                
            } catch (Exception e) { 
                e.printStackTrace();
            }
        }
    }

    private static void exportToFile(String pathName, TrajectorySet set) {
        FilePrinter.write("src\\main\\deploy\\paths\\", pathName + ".left.csv", set.left.toString());
        FilePrinter.write("src\\main\\deploy\\paths\\", pathName + ".right.csv", set.right.toString());
        FilePrinter.write("src\\main\\deploy\\paths\\", pathName + ".center.csv", set.center.toString());
    }

    private static void exportToClass(String pathName, TrajectorySet set) {
        FilePrinter.write("src\\main\\java\\frc\\paths\\", pathName + ".java", ClassExporter.createClass(set, pathName));
    }
}
