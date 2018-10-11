package com.team319.trajectory;

import com.team254.lib.trajectory.TrajectoryGenerator;

public class SrxTranslatorConfig extends TrajectoryGenerator.Config {
	public String name;
	public double wheelbase_width_feet;
	public double wheel_dia_inches;
	public int encoder_ticks_per_rev;
	public double scale_factor; // used for reductions between encoder and wheel
	public int direction = 1; // 1 = forward, -1 = backward
	public double robotLength;
	public double robotWidth;
	public boolean highGear;

	public SrxTranslatorConfig() {
	}

	public SrxTranslatorConfig(SrxTranslatorConfig toCopy) {
		this.direction = toCopy.direction;
		this.dt = toCopy.dt;
		this.max_acc = toCopy.max_acc;
		this.max_jerk = toCopy.max_jerk;
		this.max_vel = toCopy.max_vel;
		this.name = toCopy.name;
		this.scale_factor = toCopy.scale_factor;
		this.wheel_dia_inches = toCopy.wheel_dia_inches;
		this.wheelbase_width_feet = toCopy.wheelbase_width_feet;
		this.encoder_ticks_per_rev = toCopy.encoder_ticks_per_rev;
		this.highGear = toCopy.highGear;
		this.robotLength = toCopy.robotLength;
		this.robotWidth = toCopy.robotWidth;
	}
}
