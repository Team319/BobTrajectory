package com.team319.trajectory;

import org.json.simple.JSONObject;

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

	public JSONObject toJson() {
		JSONObject trajectory = new JSONObject();
		trajectory.put("left", leftProfile.toJson());
		trajectory.put("right", rightProfile.toJson());
		return trajectory;
	}

}
