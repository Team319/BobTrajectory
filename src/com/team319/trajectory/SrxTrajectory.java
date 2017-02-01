package com.team319.trajectory;

import org.json.simple.JSONObject;

import com.team254.lib.trajectory.TrajectoryGenerator;

//Combines left and right motion profiles in one object
public class SrxTrajectory {
	public SrxMotionProfile leftProfile;
	public SrxMotionProfile rightProfile;
	
	public static class Config extends TrajectoryGenerator.Config{
		public String name;
	    public double wheelbase_width;
	    public double wheel_dia;
	    public double scale_factor;  //used for reductions between encoder and wheel
	    public int direction = 1;  //1 = forward, -1 = backward
	}

	public SrxTrajectory(SrxMotionProfile left, SrxMotionProfile right) {
		this.leftProfile = left;
		this.rightProfile = right;
	}

	public SrxTrajectory(JSONObject combinedProfile){
		leftProfile = new SrxMotionProfile((JSONObject) combinedProfile.get("left"));
		rightProfile = new SrxMotionProfile((JSONObject) combinedProfile.get("right"));
	}

	public JSONObject toJson(){
		JSONObject trajectory = new JSONObject();
		trajectory.put("left", leftProfile.toJson());
		trajectory.put("right",rightProfile.toJson());
		return trajectory;
	}

}
