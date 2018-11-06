package com.team319.follower;

//Combines left and right motion profiles in one object
public class SrxTrajectory {
	public boolean flipped;
	public boolean highGear;
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
