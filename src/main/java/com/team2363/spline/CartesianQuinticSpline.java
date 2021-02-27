/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2363.spline;

import com.team2363.geometry.Pose2d;

public class CartesianQuinticSpline extends Spline {

    CartesianQuinticSpline(Pose2d p0, Pose2d p1) {
        
    }

    public Pose2d getPoint(double t) {
        return new Pose2d(0, 0, 0);
    }

    public double calculateLength() {
        return 0;
    }

    public double calculateInput(double distance) {
        return 0;
    }
}