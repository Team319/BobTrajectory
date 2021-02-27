/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2363.geometry;

public class Pose2d {

    private double x;
    private double y;
    private double rotation;

    public Pose2d(double x, double y, double rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double getRotation() {
        return rotation;
    }

    public double getDistance(Pose2d p1) {
        return Math.sqrt((p1.x() - x) * (p1.x() - x) + (p1.y() - y) * (p1.y() - y));
    }
}