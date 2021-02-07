package com.team319.io;

import java.util.List;
import java.io.File;

import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectorySet;
import com.team319.trajectory.BobPath;
import com.team319.trajectory.RobotConfig;

public class TrajectoryExporter {
    private static final String defaultPathPath = "src/main/deploy/paths/";
    private static final String defaultClassPath = "src/main/java/frc/paths/";

    /**
     * 
     * @param paths - the list of paths to export
     */
    public static void exportTrajectory(List<BobPath> paths) {
        for (BobPath path : paths) {
            exportTrajectory(path, null);
        }
    }

    /**
     * 
     * @param path - the BobPath to export
     * @param file - the directory to save the paths or class to
     */
    public static void exportTrajectory(BobPath path, File file) {
        try {
            Trajectory trajectory = PathGenerator.generateTrajectory(path.getWaypoints());
            TrajectorySet trajectorySet = PathGenerator.makeLeftAndRightTrajectories(trajectory);
            switch (RobotConfig.exportType) {
                case CLASS:
                    if (null == file) {
                        exportToClass(defaultClassPath, path.getName(), trajectorySet);
                    } else {
                        exportToClass(file.getAbsolutePath() + "/", path.getName(), trajectorySet);                    
                    }
                    break;
                case FILE:
                    if (null == file) {
                        exportToFile(defaultPathPath, path.getName(), trajectorySet);
                    } else {
                        exportToFile(file.getAbsolutePath() + "/", path.getName(), trajectorySet);
                    }
                    break;
            }            
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }

    private static void exportToFile(String pathPath, String pathName, TrajectorySet set) {
        FilePrinter.write(pathPath, pathName + ".left.csv", set.left.toString());
        FilePrinter.write(pathPath, pathName + ".right.csv", set.right.toString());
        FilePrinter.write(pathPath, pathName + ".center.csv", set.center.toString());
    }

    private static void exportToClass(String pathPath, String pathName, TrajectorySet set) {
        FilePrinter.write(pathPath, pathName + ".java", ClassExporter.createClass(set, pathName));
    }
}
