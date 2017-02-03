package com.team319.trajectory;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.Trajectory.Pair;
import com.team254.lib.trajectory.WaypointSequence;

public class BobPathGenerator extends PathGenerator {
	
	public static Path makePath(WaypointSequence waypoints, SrxTranslator.Config config){
		Path p = PathGenerator.makePath(waypoints, config, config.wheelbase_width_feet, config.name);
		
		if (config.direction == -1){
			return reversePath(p);
		}
		else{
			return p;
		}
	}
	
	private static Path reversePath(Path p){
		Trajectory oldLeft = p.getLeftWheelTrajectory();
		Trajectory oldRight = p.getRightWheelTrajectory();
		
		oldLeft.scale(-1);
		oldRight.scale(-1);
		
		return new Path(p.getName(),new Pair(oldRight, oldLeft));
	}

}
