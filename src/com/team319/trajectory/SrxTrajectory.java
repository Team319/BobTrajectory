package com.team319.trajectory;

//Combines left and right motion profiles in one object
public class SrxTrajectory {
	public SrxMotionProfile leftProfile;
	public SrxMotionProfile centerProfile;
	public SrxMotionProfile rightProfile;

	public SrxTrajectory() {
	}

	public SrxTrajectory(SrxMotionProfile left, SrxMotionProfile center, SrxMotionProfile right) {
		this.leftProfile = left;
		this.centerProfile = center;
		this.rightProfile = right;
	}
}
