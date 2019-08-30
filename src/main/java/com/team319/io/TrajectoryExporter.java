package com.team319.io;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.TrajectorySet;
import com.team254.lib.trajectory.Trajectory.Segment;
import com.team319.trajectory.BobPath;
import com.team319.ui.DraggableWaypoint;

public class TrajectoryExporter {

    public static void exportTrajectory(List<BobPath> paths) {
        for (BobPath path : paths) {
            try {
                boolean isBackwards = path.getWaypoints().get(0).isBackwards();
                prepareWaypoints(path.getWaypoints(), isBackwards);
                TrajectorySet trajectory = PathGenerator.makeLeftAndRightTrajectories(PathGenerator.generateTrajectory(path.getWaypoints()));
                prepareTrajectory(trajectory, isBackwards);
                FilePrinter.write(path.getName() + ".left.csv", trajectory.left.toString());
                FilePrinter.write(path.getName() + ".right.csv", trajectory.right.toString());
                FilePrinter.write(path.getName() + ".center.csv", trajectory.center.toString());
            } catch (Exception e) { 
                System.out.println("Could not same trajectories for " + path.getName());
            }
        }
    }

    private static void prepareWaypoints(List<DraggableWaypoint> waypoints, boolean isBackwards) {
         if (!isBackwards) {
             return;
         }

         Collections.reverse(waypoints);
    }

    private static void prepareTrajectory(TrajectorySet trajectorySet, boolean isBackwards) {
        List<Segment> center = Arrays.asList(trajectorySet.center.getSegments());
        List<Segment> left = Arrays.asList(trajectorySet.left.getSegments());
        List<Segment> right = Arrays.asList(trajectorySet.right.getSegments());

        Collections.reverse(center);
        Collections.reverse(left);
        Collections.reverse(right);

        double centerTotalDistance = center.get(0).pos;
        double leftTotalDistance = left.get(0).pos;
        double rightTotalDistance = right.get(0).pos;
        for (int i = 0; i < center.size(); i++) {
            center.get(i).pos -= centerTotalDistance;
            left.get(i).pos -= leftTotalDistance;
            right.get(i).pos -= rightTotalDistance;

            center.get(i).vel *= -1;
            left.get(i).vel *= -1;
            right.get(i).vel *= -1;

            // center.get(i).acc *= -1;
            // left.get(i).acc *= -1;
            // right.get(i).acc *= -1;

            center.get(i).jerk *= -1;
            left.get(i).jerk *= -1;
            right.get(i).jerk *= -1;
        }
    }
}
