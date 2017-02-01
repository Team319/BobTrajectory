package com.team319.trajectory;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//Generic Motion Profile Class
public class SrxMotionProfile {

	public int numPoints;
	// Position (rotations) Velocity (RPM) Duration (ms)
	public double[][] points;

	public SrxMotionProfile() {

	}


	public SrxMotionProfile(int numPoints, double[][] points) {
		this.numPoints = numPoints;
		this.points = points;
	}

	public SrxMotionProfile(JSONObject srxJson){
		numPoints = ((Long) srxJson.get("numPoints")).intValue();
		JSONArray jsonPoints = (JSONArray) srxJson.get("points");

		points = new double[jsonPoints.size()][3];
		if (points != null) {
			int len = jsonPoints.size();
			for (int i = 0; i < len; i++) {
				JSONObject singlePoint = (JSONObject) jsonPoints.get(i);
				points[i][0] = (double) singlePoint.get("pos");
				points[i][1] = (double) singlePoint.get("vel");
				points[i][2] = (double) singlePoint.get("dt");
				//System.out.println(pointsArray[i][0] + "," + pointsArray[i][1] + "," + pointsArray[i][2]);
			}
		}
	}



	public String toJsonString() {
		return this.toJson().toString();
	}

	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("numPoints", numPoints);
		// obj.put("points", points);

		JSONArray list = new JSONArray();
		DecimalFormat df = new DecimalFormat("0.000");
		df.setRoundingMode(RoundingMode.HALF_UP);

		// format the values to 3 decimal places and add to the JSON object
		for (int i = 0; i < points.length; i++) {
			JSONObject point = new JSONObject();
			point.put("pos", Double.parseDouble(df.format(points[i][0])));
			point.put("vel", Double.parseDouble(df.format(points[i][1])));
			point.put("dt", points[i][2]);

			list.add(point);
		}

		obj.put("points", list);
		// System.out.print(obj);
		return obj;
	}
}
