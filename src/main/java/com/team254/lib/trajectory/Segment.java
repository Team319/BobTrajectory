package com.team254.lib.trajectory;

public class Segment {

    public double pos, vel, acc, jerk, heading, dt, x, y;

    public Segment() { }

    public Segment(double pos, double vel, double acc, double jerk, double heading, double dt, double x, double y) {
        this.pos = pos;
        this.vel = vel;
        this.acc = acc;
        this.jerk = jerk;
        this.heading = heading;
        this.dt = dt;
        this.x = x;
        this.y = y;
    }

    public Segment(Segment to_copy) {
        pos = to_copy.pos;
        vel = to_copy.vel;
        acc = to_copy.acc;
        jerk = to_copy.jerk;
        heading = to_copy.heading;
        dt = to_copy.dt;
        x = to_copy.x;
        y = to_copy.y;
    }

    public String toString() {
        return "pos: " + pos + "; vel: " + vel + "; acc: " + acc + "; jerk: " + jerk + "; heading: " + heading;
    }
}
