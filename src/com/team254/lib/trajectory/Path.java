package com.team254.lib.trajectory;

import com.team254.lib.trajectory.Trajectory.Segment;

/**
 * Base class for an autonomous path.
 * 
 * @author Jared341
 */
public class Path {
  protected Trajectory.Pair pair;
  protected String name_;
  protected boolean goLeft;
  
  public Path(String name, Trajectory.Pair newPair) {
    name_ = name;
    pair = newPair;
    goLeft = true;
  }
  
  public Path() {
    
  }
  
  public String getName() { return name_; }
  
  public void goLeft() { 
    goLeft = true; 
    pair.left.setInvertedY(false);
    pair.right.setInvertedY(false);
  }
  
  public void goRight() {
    goLeft = false; 
    pair.left.setInvertedY(true);
    pair.right.setInvertedY(true);
  }
  
  public Trajectory getLeftWheelTrajectory() {
    return (goLeft ? pair.left : pair.right);
  }
  
  public Trajectory getRightWheelTrajectory() {
    return (goLeft ? pair.right : pair.left);
  }
  
  public Trajectory.Pair getPair() {
    return pair;
  }

  public double getEndHeading() {
    int numSegments = getLeftWheelTrajectory().getNumSegments();
    Trajectory.Segment lastSegment = getLeftWheelTrajectory().getSegment(numSegments - 1);
    return lastSegment.heading;
  }
}
