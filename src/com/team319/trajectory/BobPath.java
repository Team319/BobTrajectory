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
		this.config = new SrxTranslatorConfig(config);
		this.config.name = name;
		this.config.direction = direction;
		waypointSequence = new WaypointSequence(10);
	}

	public BobPath(BobPath toCopy) {
		config = toCopy.config;
		waypointSequence = toCopy.waypointSequence;
	}
	
	public void setWaypointSequence(WaypointSequence wps){
		waypointSequence = wps;
	}
	
	public WaypointSequence getWaypointSequence(){
		return waypointSequence;
	}
	
	public void addWaypoint(Waypoint wp){
		this.waypointSequence.addWaypoint(wp);
	}
	
	public void setConfig(SrxTranslatorConfig c){
		this.config = c;
	}
	
	public SrxTranslatorConfig getConfig(){
		return this.config;
	}
}
