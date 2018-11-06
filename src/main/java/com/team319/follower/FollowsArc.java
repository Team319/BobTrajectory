package com.team319.follower;

import com.team319.models.BobTalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;



public interface FollowsArc {
    public BobTalonSRX getLeft();
    public BobTalonSRX getRight();
    public double getDistance();
    public Subsystem getSubsystem();
}