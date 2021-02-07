package com.team254.lib.trajectory;

public class TrajectorySet {
    public Trajectory left;
    public Trajectory center;
    public Trajectory right;

    public TrajectorySet(Trajectory left, Trajectory center, Trajectory right) {
        this.left = left;
        this.center = center;
        this.right = right;
    }
}
