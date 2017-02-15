package com.team319.trajectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.Trajectory.Pair;
import com.team319.ui.PathViewer;

import com.team254.lib.trajectory.WaypointSequence;
import com.team254.lib.trajectory.io.IPathSerializer;

public class BobPathGenerator extends PathGenerator {

	
	public SrxTranslatorConfig config;
	public WaypointSequence waypointSequence;
	private Path _p;
	
	public BobPathGenerator (SrxTranslatorConfig config){
		this.config = new SrxTranslatorConfig(config);
		this.waypointSequence = new WaypointSequence(10);
		
	}
	
	private void makePath(){
		Path p = PathGenerator.makePath(this.waypointSequence, this.config, this.config.wheelbase_width_feet, this.config.name);
		
		if (config.direction == -1){
			this._p = reversePath(p);
		}
		else{
			this._p = p;
		}
	}
	
	private Path reversePath(Path p){
		Trajectory oldLeft = p.getLeftWheelTrajectory();
		Trajectory oldRight = p.getRightWheelTrajectory();
		
		oldLeft.scale(-1);
		oldRight.scale(-1);
		
		return new Path(p.getName(),new Pair(oldRight, oldLeft));
	}
	
	public void exportPath(String relativePathName){
		SrxTrajectoryExporter exporter = new SrxTrajectoryExporter(relativePathName);
		
		makePath();

		SrxTranslator srxt = new SrxTranslator();
		SrxTrajectory combined = srxt.getSrxTrajectoryFromChezyPath(_p, this.config);

		if (!exporter.exportSrxTrajectory(combined, this.config, this.waypointSequence)) {
			System.err.println("A path could not be written!!!!");
			System.exit(1);
		} else {
			///SrxTrajectory t = importer.importSrxTrajectory(config.name);
			PathViewer.showPath(_p);
		}
	}
	
	public void exportPathWithSerializer(IPathSerializer serializer, String relativePathName) {
		makePath();
		
		String pathName = relativePathName + "/" + _p.getName();
		String data = serializer.serialize(_p);
		try {
			Files.write(Paths.get(pathName), data.getBytes(), 
					StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			PathViewer.showPath(_p);
		} catch (IOException e) {
			System.err.println("A path could not be written!!!!");
			System.exit(1);
		}
	}
}
