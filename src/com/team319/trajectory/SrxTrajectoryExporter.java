package com.team319.trajectory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SrxTrajectoryExporter {
	
	public SrxTrajectoryExporter(String directory){
		this.directory = directory;
	}

	public String directory;

	public boolean exportCombinedSrxMotionProfile(SrxTrajectory combined, String profileName){
		
		String combinedPath = joinFilePaths(directory, profileName + "_SrxTrajectory.json");

		String combinedJSON = combined.toJson().toJSONString();
		
		if (!writeFile(combinedPath, combinedJSON)){
			System.err.println(combinedPath + " could not be written!!!!1");
			return false;
		}
		
		return true;
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
