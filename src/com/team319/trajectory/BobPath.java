package com.team319.trajectory;

import com.team254.lib.trajectory.WaypointSequence;
import com.team254.lib.trajectory.WaypointSequence.Waypoint;

public class BobPath {
	private SrxTranslatorConfig config;
	private WaypointSequence waypointSequence;

	public BobPath(SrxTranslatorConfig config) {
		this.config = new SrxTranslatorConfig(config);
		this.waypointSequence = new WaypointSequence(10);
	}

	public BobPath(SrxTranslatorConfig config, String name, int direction) {
		this(config);
		this.config.name = name;
		this.config.direction = direction;
	}

	public BobPath(SrxTranslatorConfig config, String name, int direction, boolean useDegrees) {
		this(config, name, direction);
	}

	public BobPath(BobPath toCopy) {
		config = toCopy.config;
		waypointSequence = toCopy.waypointSequence;
	}

	public boolean isExportEnabled() {
		return this.isExportEnabled();
	}

	public void setWaypointSequence(WaypointSequence wps) {
		waypointSequence = wps;
	}

	public WaypointSequence getWaypointSequence() {
		return waypointSequence;
	}

	public void addWaypoint(Waypoint wp) {
		this.waypointSequence.addWaypoint(wp);
	}

	public void addWaypointRadians(double x, double y, double theta_rad, double endVelocity, double maxVelocity) {
		this.waypointSequence.addWaypoint(new Waypoint(x, y, theta_rad, endVelocity, maxVelocity));
	}

	public void addWaypoint(double x, double y, double theta_deg, double endVelocity, double maxVelocity) {
		this.waypointSequence.addWaypoint(new Waypoint(x, y, Math.toRadians(theta_deg), endVelocity, maxVelocity));
	}

	public void addWaypointRelative(double x, double y, double theta_deg, double endVelocity, double maxVelocity) {
		Waypoint lastWaypoint = getLastWaypoint();
		Waypoint newWaypoint = new Waypoint(lastWaypoint.x + x, lastWaypoint.y + y,
				lastWaypoint.theta + Math.toRadians(theta_deg), endVelocity, maxVelocity);
		this.waypointSequence.addWaypoint(newWaypoint);
	}

	public Waypoint getLastWaypoint() {
		Waypoint lastWaypoint = this.waypointSequence.getWaypoint(this.waypointSequence.getNumWaypoints() - 1);
		return lastWaypoint;
	}

	public void setConfig(SrxTranslatorConfig c) {
		this.config = c;
	}

	public SrxTranslatorConfig getConfig() {
		return this.config;
	}
}
