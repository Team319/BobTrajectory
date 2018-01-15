package com.team319.trajectory;

import org.json.simple.JSONObject;

import com.team254.lib.trajectory.TrajectoryGenerator;

public class SrxTranslatorConfig extends TrajectoryGenerator.Config {
	public String name;
	public double wheelbase_width_feet;
	public double wheel_dia_inches;
	public int encoder_ticks_per_rev;
	public double scale_factor; // used for reductions between encoder and wheel
	public int direction = 1; // 1 = forward, -1 = backward

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
	}

	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("wheelbase_width_feet", wheelbase_width_feet);
		obj.put("wheel_dia_inches", wheel_dia_inches);
		obj.put("scale_factor", scale_factor);
		obj.put("direction", direction);
		obj.put("dt", dt);
		obj.put("max_vel", max_vel);
		obj.put("max_acc", max_acc);
		obj.put("max_jerk", max_jerk);

		return obj;
	}

}
