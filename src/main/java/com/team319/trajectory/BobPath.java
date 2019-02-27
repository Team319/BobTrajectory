package com.team319.trajectory;

import com.team254.lib.trajectory.WaypointSequence;
import com.team254.lib.trajectory.WaypointSequence.Waypoint;

public class BobPath {
	private SrxTranslatorConfig config;
	private WaypointSequence waypointSequence;

	public BobPath(SrxTranslatorConfig config) {
		this(config, config.name);
	}

	public BobPath(SrxTranslatorConfig config, String name) {
		this(config, name, false);
	}

	public BobPath(SrxTranslatorConfig config, String name, boolean driveBackwards) {
		this.config = new SrxTranslatorConfig(config);
		this.config.name = name;
		this.waypointSequence = new WaypointSequence(10);
		this.config.direction = driveBackwards ? -1 : 1;
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
		if (wp.maxVelocity == 0.0 && waypointSequence.getNumWaypoints() > 0) {
			getLastWaypoint().endVelocity = config.max_vel;
		}
		this.waypointSequence.addWaypoint(new Waypoint(wp, 0, config.max_vel));
	}

	public void addWaypointRadians(double x, double y, double theta_rad, double endVelocity, double maxVelocity) {
		this.waypointSequence.addWaypoint(new Waypoint(x, y, theta_rad, endVelocity, maxVelocity));
	}

	public void addWaypoint(double x, double y, double theta_deg, double endVelocity, double maxVelocity) {
		this.waypointSequence.addWaypoint(new Waypoint(x, y, Math.toRadians(theta_deg), endVelocity, maxVelocity));
	}

	public void addWaypoint(double x, double y, double theta_deg) {
		if (waypointSequence.getNumWaypoints() > 0) {
			getLastWaypoint().endVelocity = config.max_vel;
		}
		addWaypoint(new Waypoint(x, y, Math.toRadians(theta_deg), 0, config.max_vel));
	}

	public void addWaypoint(Waypoint wp, double endVelocity, double maxVelocity) {
		this.waypointSequence.addWaypoint(new Waypoint(wp, endVelocity, maxVelocity));
	}

	public void addWaypointRelative(double x, double y, double theta_deg) {
		if (waypointSequence.getNumWaypoints() > 1) {
			getLastWaypoint().endVelocity = config.max_vel;
		}
		addWaypointRelative(x, y, theta_deg, 0, config.max_vel);
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
