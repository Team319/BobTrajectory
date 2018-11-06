package com.team319.trajectory;

//Combines left and right motion profiles in one object
public class SrxTrajectory {
	public SrxMotionProfile arcProfile;

	public SrxTrajectory() {
	}
	
	public SrxTrajectory(SrxMotionProfile arc) {
		this.arcProfile = arc;
	}
}
