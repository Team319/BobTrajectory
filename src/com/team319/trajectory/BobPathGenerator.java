package com.team319.trajectory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.Trajectory.Pair;
import com.team254.lib.trajectory.WaypointSequence;
import com.team254.lib.trajectory.io.IPathSerializer;
import com.team319.ui.PathViewer;

public class BobPathGenerator extends PathGenerator {

	public static Path makePath(BobPath bobPath) {
		Path p = PathGenerator.makePath(bobPath.getWaypointSequence(), bobPath.getConfig(),
				bobPath.getConfig().wheelbase_width_feet, bobPath.getConfig().name);

		if (bobPath.getConfig().direction == -1) {
			p = reversePath(p);
		}

		return p;
	}

	private static Path reversePath(Path p) {
		Trajectory oldLeft = p.getLeftWheelTrajectory();
		Trajectory oldCenter = p.getCenterTrajectory();
		Trajectory oldRight = p.getRightWheelTrajectory();

		oldLeft.scale(-1);
		oldCenter.scale(-1);
		oldRight.scale(-1);

		return new Path(p.getName(), new Pair(oldRight, p.getPair().center, oldLeft));
	}

	public static void exportPathToJavaFile(String relativeDirectoryName, BobPath bobPath) {
		SrxTrajectoryExporter exporter = new SrxTrajectoryExporter(relativeDirectoryName);

		Path chezyPath = makePath(bobPath);

		SrxTranslator srxt = new SrxTranslator();
		SrxTrajectory combined = srxt.getSrxTrajectoryFromChezyPath(chezyPath, bobPath.getConfig());

		if (!exporter.exportSrxTrajectoryAsJavaFile(combined, bobPath.getConfig(), bobPath.getWaypointSequence())) {
			System.err.println("A path could not be written!!!!");
			System.exit(1);
		} else {
			/// SrxTrajectory t = importer.importSrxTrajectory(config.name);
			PathViewer.showPath(chezyPath, bobPath.getConfig());
		}
	}

	public static void exportPathToJavaFile(BobPath bobPath) {
		exportPathToJavaFile("Paths", bobPath);
	}

	public static void exportArcToJavaFile(String relativeDirectoryName, BobPath bobPath) {
		SrxTrajectoryExporter exporter = new SrxTrajectoryExporter(relativeDirectoryName);

		Path chezyPath = makePath(bobPath);

		SrxTranslator srxt = new SrxTranslator();
		SrxTrajectory combined = srxt.getSrxTrajectoryFromChezyPath(chezyPath, bobPath.getConfig());

		if (!exporter.exportSrxArcAsJavaFile(combined, bobPath.getConfig(), bobPath.getWaypointSequence())) {
			System.err.println("A path could not be written!!!!");
			System.exit(1);
		} else {
			/// SrxTrajectory t = importer.importSrxTrajectory(config.name);
			PathViewer.showPath(chezyPath, bobPath.getConfig());
		}
	}

	public static void exportArcToJavaFile(BobPath bobPath) {
		exportArcToJavaFile("Arcs", bobPath);
	}

	public static void exportRotationToJavaFile(String relativeDirectoryName, BobRotation bobRotation) {
		SrxTrajectoryExporter exporter = new SrxTrajectoryExporter(relativeDirectoryName);

		SrxTrajectory rotation = new SrxTrajectory();
		bobRotation.getConfig().name = bobRotation.getName();
		int numPoints = (int) (bobRotation.getSeconds() / bobRotation.getConfig().dt);
		double degreesPerPoint = bobRotation.getDegrees() / (numPoints - 1);
		double[][] points = new double[numPoints][4];
		for (int i = 0; i < numPoints - 1; i++) {
			points[i][0] = 0;
			points[i][1] = 0;
			points[i][2] = bobRotation.getConfig().dt;
			points[i][3] = degreesPerPoint * i;
		}

		points[numPoints - 1][0] = 0;
		points[numPoints - 1][1] = 0;
		points[numPoints - 1][2] = bobRotation.getConfig().dt;
		points[numPoints - 1][3] = bobRotation.getDegrees();

		rotation.centerProfile = new SrxMotionProfile(points.length, points);

		if (!exporter.exportSrxArcAsJavaFile(rotation, bobRotation.getConfig(), new WaypointSequence(0))) {
			System.err.println("A path could not be written!!!!");
			System.exit(1);
		}
	}

	public static void exportPathWithSerializer(IPathSerializer serializer, String relativeDirectoryName,
			BobPath bobPath) {

		Path chezyPath = makePath(bobPath);

		String pathName = relativeDirectoryName + "/" + chezyPath.getName();
		String data = serializer.serialize(chezyPath);
		try {
			Files.write(Paths.get(pathName), data.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
			PathViewer.showPath(chezyPath, bobPath.getConfig());
		} catch (IOException e) {
			System.err.println("A path could not be written!!!!");
			System.exit(1);
		}
	}
	
	public static void copyFilesToRelativeDirectory(String fromDirectoryRelative, String toDirectoryRelative) {
		int fileCount = 0;
		File fromDirectory = new File(fromDirectoryRelative);
		File toDirectory = new File(toDirectoryRelative);
		
		for (File source : fromDirectory.listFiles()) {
			try {
				fileCount++;
				File dest = new File(toDirectory + "\\" + source.getName());
				copyFileUsingStream(source,  dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Copied " + fileCount + " files to " + toDirectoryRelative);
	}
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
}
