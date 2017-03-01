package com.team319.trajectory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.team254.lib.trajectory.WaypointSequence;

public class SrxTrajectoryExporter {
	
	public SrxTrajectoryExporter(String directory){
		this.directory = directory;
	}

	public String directory;
	
	public boolean exportSrxTrajectory(SrxTrajectory combined, String pathName, BobPath...bobPaths){
		JSONObject exportJson = new JSONObject();
		
		String combinedPath = joinFilePaths(directory, pathName + "_SrxTrajectory.json");
		
		exportJson.put("config", bobPaths[0].getConfig().toJson());
		for (BobPath bobPath : bobPaths) {
			exportJson.put(bobPath.getConfig().name + "_waypoints", waypointSequenceToJson(bobPath.getWaypointSequence()));
		}
		//exportJson.put("waypoints", waypointSequenceToJson(waypoints));
		exportJson.put("trajectory", combined.toJson());

		String exportString = exportJson.toJSONString();
		
		if (!writeFile(combinedPath, exportString)){
			System.err.println(combinedPath + " could not be written!!!!1");
			return false;
		}
		
		return true;
	}

	public boolean exportSrxTrajectory(SrxTrajectory combined, SrxTranslatorConfig config, WaypointSequence waypoints){
		
		JSONObject exportJson = new JSONObject();
		
		String combinedPath = joinFilePaths(directory, config.name + "_SrxTrajectory.json");
		
		exportJson.put("config", config.toJson());
		exportJson.put("waypoints", waypointSequenceToJson(waypoints));
		exportJson.put("trajectory", combined.toJson());

		String exportString = exportJson.toJSONString();
		
		if (!writeFile(combinedPath, exportString)){
			System.err.println(combinedPath + " could not be written!!!!1");
			return false;
		}
		
		return true;
	}
	
	public JSONArray waypointSequenceToJson(WaypointSequence wps){
		JSONArray arr = new JSONArray();
		for (int i=0; i < wps.getNumWaypoints(); i++){
			JSONObject j = new JSONObject();
			j.put("x", wps.getWaypoint(i).x);
			j.put("y", wps.getWaypoint(i).y);
			j.put("theta", wps.getWaypoint(i).theta);
			arr.add(j);
		}
		
		return arr;
	}

	private boolean writeFile(String filePath, String data) {
		try {
			File file = new File(filePath);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
		} catch (IOException e) {
			return false;
		}

		return true;
	}

	public String joinFilePaths(String path1, String path2) {
		File file1 = new File(path1);
		File file2 = new File(file1, path2);
		return file2.getPath();
	}

}
