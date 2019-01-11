package com.team319.trajectory;

import java.util.List;

import com.team319.ui.DraggableWaypoint;

public class BobPath {

	private String name;
	private List<DraggableWaypoint> waypoints;
	private boolean driveBackwards;

	public BobPath(String name, List<DraggableWaypoint> waypoints, boolean driveBackwards) {
		this.name = name;
		this.waypoints = waypoints;
		this.driveBackwards = driveBackwards;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the waypoints
	 */
	public List<DraggableWaypoint> getWaypoints() {
		return waypoints;
	}

	/**
	 * @param waypoints the waypoints to set
	 */
	public void setWaypoints(List<DraggableWaypoint> waypoints) {
		this.waypoints = waypoints;
	}

	/**
	 * @return the driveBackwards
	 */
	public boolean isDriveBackwards() {
		return driveBackwards;
	}

	/**
	 * @param driveBackwards the driveBackwards to set
	 */
	public void setDriveBackwards(boolean driveBackwards) {
		this.driveBackwards = driveBackwards;
	}

	@Override
	public String toString() {
		StringBuilder pathString = new StringBuilder();
		pathString.append(name).append("\n");
		for (DraggableWaypoint waypoint : waypoints) {
			pathString.append(waypoint.toString()).append("\n");
		}
		return pathString.toString();
	}
}
