package com.team319.trajectory;

public abstract class Path {

     public enum SegmentValue { 
         TIME_STAMP, X, Y, 
         LEFT_POSITION, LEFT_VELOCITY, LEFT_ACCELERATION, LEFT_JERK, 
         CENTER_POSITION, CENTER_VELOCITY, CENTER_ACCELERATION, CENTER_JERK, 
         RIGHT_POSITION, RIGHT_VELOCITY, RIGHT_ACCELERATION, RIGHT_JERK, 
         HEADING;
    }

    public abstract double[][] getPath();

    public double getValue(int index, SegmentValue value) {
        return getPath()[index][value.ordinal()];
    }

    public int getSegmentCount() {
        return getPath().length;
    }
}
