package com.team254.lib.trajectory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.team319.trajectory.RobotConfig;

/**
 * Based on 254's implmentation
 */
public class Trajectory {

	private List<Segment> segments = new ArrayList<>();

	public Trajectory() { }

	public Trajectory(List<Segment>  segments) {
		this.segments = segments;
	}
	
	public void offsetHeading(double theta_rad) {
		for (Segment segment : segments) {
			segment.heading += theta_rad;
		}
	}

	public int getNumSegments() {
		return segments.size();
	}

	public List<Segment> getSegments() {
		return segments;
	}

	public void setSegment(int index, Segment segment) {
		if (index < getNumSegments()) {
			segments.set(index, segment);
		}
	}

	public void scale(double scaling_factor) {
		for (int i = 0; i < getNumSegments(); ++i) {
			segments.get(i).pos *= scaling_factor;
			segments.get(i).vel *= scaling_factor;
			segments.get(i).acc *= scaling_factor;
			segments.get(i).jerk *= scaling_factor;
		}
	}

	public void append(Trajectory to_append) {
		this.segments.addAll(to_append.getSegments());
	}

	public Trajectory copy() {
		Trajectory cloned = new Trajectory();
		cloned.segments = copySegments(segments);
		return cloned;
	}

	private List<Segment> copySegments(List<Segment> tocopy) {
		List<Segment> copied = new ArrayList<>();
		for (int i = 0; i < tocopy.size(); ++i) {
			copied.add(new Segment(tocopy.get(i)));
		}
		return copied;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("dt,x,y,position,velocity,acceleration,jerk,heading\n");
		for (int i = 0; i < getNumSegments(); ++i) {
			Segment segment = segments.get(i);
			str.append(RobotConfig.dt).append(",")
			.append(new BigDecimal(segment.x).setScale(4, RoundingMode.HALF_UP)).append(",")
			.append(new BigDecimal(segment.y).setScale(4, RoundingMode.HALF_UP)).append(",")
			.append(new BigDecimal(segment.pos).setScale(4, RoundingMode.HALF_UP)).append(",")
			.append(new BigDecimal(segment.vel).setScale(4, RoundingMode.HALF_UP)).append(",")
			.append(new BigDecimal(segment.acc).setScale(4, RoundingMode.HALF_UP)).append(",")
			.append(new BigDecimal(segment.jerk).setScale(4, RoundingMode.HALF_UP)).append(",")
			.append(new BigDecimal(segment.heading).setScale(4, RoundingMode.HALF_UP))
			.append("\n");
		}

		return str.toString();
	}

}
