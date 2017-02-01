package com.team319.trajectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SrxTrajectoryImporter {

	public String path; 

	public SrxTrajectoryImporter (String directory){
		this.path = directory;
	}
	
	public SrxTrajectory importSrxTrajectory (String trajectoryName){
		//these are actually json files, so append appropriately
		String fileName = trajectoryName + "_SrxTrajectory.json";
		
		//get the full filepath
		
		String relativeFilePath = joinFilePaths(this.path, fileName);
		File file = new File(relativeFilePath);
		
		JSONObject combinedJson = null;
		JSONParser parser = new JSONParser();
		
		try {
			String profileData = readFile(file.getAbsolutePath());
			combinedJson = (JSONObject) parser.parse(profileData);
		} catch (Exception e) {
			return null;
		}
		
		SrxTrajectory cSrxProfile = new SrxTrajectory(combinedJson);
		
		return cSrxProfile;
		
	}
	
	static String readFile(String path) throws IOException 
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded);
	}


	public static String joinFilePaths(String path1, String path2) {
		File file1 = new File(path1);
		File file2 = new File(file1, path2);
		return file2.getPath();
	}

}
