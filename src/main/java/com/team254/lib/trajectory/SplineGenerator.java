package com.team254.lib.trajectory;

import java.util.List;

import com.team319.ui.DraggableWaypoint;

public class SplineGenerator {

    public static Spline[] getSplines(List<DraggableWaypoint> waypoints) {
        Spline[] splines = new Spline[waypoints.size() - 1];
		double[] spline_lengths = new double[splines.length];
		for (int i = 0; i < splines.length; ++i) {
			splines[i] = new Spline();
			if (!Spline.reticulateSplines(waypoints.get(i), waypoints.get(i + 1), splines[i],
					Spline.QuinticHermite)) {
				System.out.println("COULDN'T RETICULATE SPLINE!!");
				return null;
			}
			spline_lengths[i] = splines[i].calculateLength();
        }
        return splines;
    }
}
