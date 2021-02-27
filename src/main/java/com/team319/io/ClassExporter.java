package com.team319.io;

import com.team254.lib.trajectory.Segment;
import com.team254.lib.trajectory.TrajectorySet;

public class ClassExporter {

    private static final String NEW_LINE = "\r\n";

    public static String createClass(TrajectorySet trajectory, String name) {
		return new StringBuilder()

		// package and imports
        .append("package frc.paths;").append(NEW_LINE).append(NEW_LINE)
		.append("import com.team319.trajectory.Path;").append(NEW_LINE).append(NEW_LINE)

		// beginning of the class
		.append("public class ").append(name).append(" extends Path {").append(NEW_LINE)

        .append("   // dt,x,y,left.pos,left.vel,left.acc,left.jerk,center.pos,center.vel,center.acc,center.jerk,right.pos,right.vel,right.acc,right.jerk,heading").append(NEW_LINE)
		.append("	private static final double[][] points = {").append(NEW_LINE)

		.append(serializeTrajectoryPoints(trajectory))
        .append("	    };").append(NEW_LINE).append(NEW_LINE)
        .append("	@Override").append(NEW_LINE)
        .append("	public double[][] getPath() {").append(NEW_LINE)
        .append("	    return points;").append(NEW_LINE)
        .append("	}").append(NEW_LINE)
        .append("}")
		.toString();
	}


	private static String serializeTrajectoryPoints(TrajectorySet trajectory) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < trajectory.center.getNumSegments(); i++) {
            Segment left = trajectory.left.getSegments().get(i);
            Segment center = trajectory.center.getSegments().get(i);
            Segment right = trajectory.right.getSegments().get(i);
            sb.append(String.format("				{%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f}", 
                center.dt,
                center.x,
                center.y,
                left.pos,
                left.vel,
                left.acc,
                left.jerk,
                center.pos,
                center.vel,
                center.acc,
                center.jerk,
                right.pos,
                right.vel,
                right.acc,
                right.jerk,
                center.heading
            )).append(",").append(NEW_LINE);
		}
        return sb.append(NEW_LINE).toString();
	}	
}
