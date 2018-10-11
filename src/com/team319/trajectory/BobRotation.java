package com.team319.trajectory;

public class BobRotation {

	private final SrxTranslatorConfig config;
	private final String name;
	private final double degrees;
	private final double seconds;

	public BobRotation(SrxTranslatorConfig config, String name, double degrees, double seconds) {
		this.config = config;
		this.name = name;
		this.degrees = degrees;
		this.seconds = seconds;
	}

	public String getName() {
		return name;
	}

	public double getDegrees() {
		return degrees;
	}

	public double getSeconds() {
		return seconds;
	}

	public SrxTranslatorConfig getConfig() {
		return config;
	}
}
