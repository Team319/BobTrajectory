package com.team319.follower;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;



public interface FollowsArc {
    public TalonSRX getLeft();
    public TalonSRX getRight();
    public double getDistance();
    public Subsystem getRequiredSubsystem();
}