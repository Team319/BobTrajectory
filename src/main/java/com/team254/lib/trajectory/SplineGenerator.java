/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team254.lib.trajectory;

import java.util.List;

import com.team319.ui.DraggableWaypoint;

/**
 * Add your docs here.
 */
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
