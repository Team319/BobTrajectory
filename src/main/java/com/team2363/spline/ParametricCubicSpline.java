/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2363.spline;

import com.team2363.geometry.Pose2d;

public class ParametricCubicSpline extends Spline {

    private double vectorMagnitude;
    private double dx0,dx1,dy0,dy1;
    private double ax,bx,cx,dx,ay,by,cy,dy;

    public ParametricCubicSpline(Pose2d p0, Pose2d p1) {

        vectorMagnitude = 1.5 * p0.getDistance(p1);

        dx0 = Math.cos(p0.getRotation()) * vectorMagnitude;
        dx1 = Math.cos(p1.getRotation()) * vectorMagnitude;

        dy0 = Math.sin(p0.getRotation()) * vectorMagnitude;
        dy1 = Math.sin(p1.getRotation()) * vectorMagnitude;

        ax = 2 * p0.x() - 2 * p1.x() + dx0 + dx1;
        bx = -2 * dx0 - dx1 - 3 * p0.x() + 3 * p1.x();
        cx = dx0;
        dx = p0.x();

        ay = 2 * p0.y() - 2 * p1.y() + dy0 + dy1;
        by = -2 * dy0 - dy1 - 3 * p0.y() + 3 * p1.y();
        cy = dy0;
        dy = p0.y();
    }

    public Pose2d getPoint(double t) {
        return new Pose2d(x(t), y(t), Math.atan2(dy(t), dx(0)));
    }

    private double x(double t) {
        return ax * t * t * t + bx * t * t + cx * t + dx;
    }

    private double y(double t) {
        return ay * t * t * t + by * t * t + cy * t + dy;
    }

    private double dx(double t) {
        return 3 * ax * t * t + 2 * bx * t + cx;
    }

    private double dy(double t) {
        return 3 * ay * t * t + 2 * by * t + cy;
    }

    public double calculateLength() {

        final int kNumSamples = 100000;
		double integral = 0;
        double integrand, last_integrand = Math.sqrt(dx(0) * dx(0) + dy(0) * dy(0)) / kNumSamples;
        
		for (double i = 1; i <= kNumSamples; ++i) {
            double t = i / kNumSamples;
			integrand = Math.sqrt(dx(t) * dx(t) + dy(t) * dy(t)) / kNumSamples;
			integral += (integrand + last_integrand) / 2;
			last_integrand = integrand;
        }
        
		return integral;
    }
    
    public double calculateInput(double distance) {

        final int kNumSamples = 100000;
        double integral = 0;
        double last_integral = 0;
        double t = 0;
        double integrand, last_integrand = Math.sqrt(dx(0) * dx(0) + dy(0) * dy(0)) / kNumSamples;
        
		for (double i = 1; i <= kNumSamples; ++i) {
            t = i / kNumSamples;
			integrand = Math.sqrt(dx(t) * dx(t) + dy(t) * dy(t)) / kNumSamples;
            integral += (integrand + last_integrand) / 2;
            if (integral > distance) break;
            last_integrand = integrand;
            last_integral = integral;
        }
        
        if (integral != last_integral) {
            return t + ((distance - last_integral) / (integral - last_integral) - 1) / kNumSamples;
        }

		return t;
	}
}