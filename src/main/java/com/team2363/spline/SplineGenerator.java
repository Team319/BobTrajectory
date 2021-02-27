package com.team2363.spline;

import java.util.List;

import com.team2363.geometry.Pose2d;
import com.team319.ui.DraggableWaypoint;

public class SplineGenerator {

    public static Spline[] getSplines(List<DraggableWaypoint> waypoints) {
        Spline[] splines = new Spline[waypoints.size() - 1];
		for (int i = 0; i < splines.length; ++i) {
            DraggableWaypoint w0 = waypoints.get(i);
            DraggableWaypoint w1 = waypoints.get(i + 1);
            Pose2d p0 = new Pose2d(w0.getX(), w0.getY(), w0.getHeading());
            Pose2d p1 = new Pose2d(w1.getX(), w1.getY(), w1.getHeading());
            splines[i] = new ParametricQuinticSpline(p0, p1);
        }
        return splines;
    }
}
