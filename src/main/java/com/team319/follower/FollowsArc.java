package com.team319.follower;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.models.BobTalonSRX;


public interface FollowsArc {
    public BobTalonSRX getLeft();
    public BobTalonSRX getRight();
    public double getDistance();
    public Subsystem getSubsystem();
}