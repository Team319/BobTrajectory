package com.team254.lib.trajectory.io;

import com.team254.lib.trajectory.Path;

/**
 * Serializes a Path to a simple space and CR separated text file.
 * 
 * @author Jared341
 */
public class VelocityOnlyFileSerializer implements IPathSerializer {

  /**
   * Format:
   * Each segment is in the format:
   *   t leftVelocity rightVelocity
   * 
   * @param path The path to serialize.
   * @return A string representation.
   */
  public String serialize(Path path) {
	  StringBuilder builder = new StringBuilder();
	  double timeStamp = 0;
	  for (int i = 0; i < path.getLeftWheelTrajectory().getNumSegments(); i++) {
		  double time = path.getLeftWheelTrajectory().getSegment(i).dt;
		  timeStamp += time;
		  double leftVelocity = path.getLeftWheelTrajectory().getSegment(i).vel;;
		  double rightVelocity = path.getRightWheelTrajectory().getSegment(i).vel;
		  builder.append(timeStamp).append("\t")
		  .append(leftVelocity).append("\t")
		  .append(rightVelocity).append("\n");
	  }
    return builder.toString();
  }
}
