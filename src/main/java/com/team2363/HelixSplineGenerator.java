package com.team2363;

import java.util.List;

import com.team319.ui.DraggableWaypoint;

public class HelixSplineGenerator {

    public static QuinticHermiteSpline[] getSplines(List<DraggableWaypoint> waypoints) {
        QuinticHermiteSpline[] splines = new QuinticHermiteSpline[waypoints.size() - 1];
		for (int i = 0; i < splines.length; ++i) {
            DraggableWaypoint p0 = waypoints.get(i);
            DraggableWaypoint p1 = waypoints.get(i + 1);
            splines[i] = new QuinticHermiteSpline(p0.getX(), p0.getY(), p0.getHeading(), p1.getX(), p1.getY(), p1.getHeading());
        }
        return splines;
    }
}
