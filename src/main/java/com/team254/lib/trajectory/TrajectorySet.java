/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team254.lib.trajectory;

/**
 * Add your docs here.
 */
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
