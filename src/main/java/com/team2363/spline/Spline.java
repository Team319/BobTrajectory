package com.team2363.spline;

import com.team2363.geometry.Pose2d;

public abstract class Spline {

    public abstract Pose2d getPoint(double input);

    public abstract double calculateLength();

    public abstract double calculateInput(double distance);
}