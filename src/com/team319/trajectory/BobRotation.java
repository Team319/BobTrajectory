package com.team319.trajectory;

public class BobRotation extends BobPath {
	
	public BobRotation(SrxTranslatorConfig config, String name, double degrees) {
		super(config, name, degrees > 0 ? 1 : -1);
		
		addWaypoint(0, 0, 0);
		addWaypoint(inDegrees(degrees), 0, 0);
	}
	
	private double inDegrees(double degree) {
		return getConfig().wheelbase_width_feet * degree / 360.0;
	}
}
