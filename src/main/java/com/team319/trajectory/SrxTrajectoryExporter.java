package com.team319.trajectory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.team254.lib.trajectory.WaypointSequence;

public class SrxTrajectoryExporter {

	public SrxTrajectoryExporter(String directory) {
		this.directory = directory;
	}

	public String directory;
	
	public boolean exportSrxArcAsJavaFile(SrxTrajectory combined, SrxTranslatorConfig config,
			WaypointSequence waypoints) {
		String combinedPath = joinFilePaths(directory, config.name + "Arc.java");

		StringBuilder sb = new StringBuilder();

		// package and imports
		sb.append("package frc.arcs;\r\n" + "\r\n"
				+ "import com.team319.models.SrxMotionProfile;\r\n"
				+ "import com.team319.models.SrxTrajectory;\r\n\r\n");

		// beginning of the class
		sb.append("public class " + config.name + "Arc extends SrxTrajectory {");

		sb.append("\r\n" + "	\r\n" + "	// WAYPOINTS:\r\n" + "	// (X,Y,degrees)\r\n");

		sb.append(serializeWaypoints(waypoints));

		sb.append("	\r\n" + "    public " + config.name + "Arc() {\r\n" + "		super();\r\n"
				+ "		this.highGear = " + config.highGear + ";\r\n"
				+ "		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);\r\n" + "	}\r\n"
				+ "\r\n");

		sb.append("	\r\n" + "    public " + config.name + "Arc(boolean flipped) {\r\n" + "		super();\r\n"
				+ "		this.highGear = " + config.highGear + ";\r\n" + "		this.flipped = flipped;\r\n"
				+ "		centerProfile = new SrxMotionProfile(centerPoints.length, centerPoints);\r\n" + "	}\r\n"
				+ "\r\n");

		sb.append("	public boolean highGear = " + config.highGear + ";\r\n" + "\r\n");

		sb.append("	double[][] centerPoints = {\r\n");

		sb.append(serializeTrajectoryPoints(combined.arcProfile, 1));
		sb.append("		};\r\n" + "\n}");

		if (!writeFile(combinedPath, sb.toString())) {
			System.err.println(combinedPath + " could not be written!!!!1");
			return false;
		}

		return true;
	}

	private String serializeWaypoints(WaypointSequence wps) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < wps.getNumWaypoints(); i++) {
			sb.append(String.format("	// (%.2f,%.2f,%.2f)\r\n", wps.getWaypoint(i).x, wps.getWaypoint(i).y,
					Math.toDegrees(wps.getWaypoint(i).theta)));
		}
		return sb.toString();
	}

	private String serializeTrajectoryPoints(SrxMotionProfile profile, int direction) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < profile.points.length; i++) {
			sb.append(String.format("				{%.3f,%.3f,%.3f,%.3f}", direction * profile.points[i][0],
					direction * profile.points[i][1], profile.points[i][2], profile.points[i][3]));
			if (i < profile.points.length - 1) {
				sb.append(",\r\n");
			}
		}

		return sb.toString();
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
