package com.team319.trajectory;

import org.json.simple.JSONObject;

public class Waypoint {

	private double x = 0d;
	private double y = 0d;
	private double theta = 0d;

	public Waypoint(double x, double y, double theta) {
      this.x = x;
      this.y = y;
      this.theta = theta;
    }

	public JSONObject toJson(){
		JSONObject waypoint = new JSONObject();
		waypoint.put("x", x);
		waypoint.put("y", y);
		waypoint.put("z", theta);
		return waypoint;
	}

}
