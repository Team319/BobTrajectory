/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2363;

public class QuinticHermiteSpline {

    private double vectorMagnitude;
    private double dx0,dx1,dy0,dy1;
    private double ax,bx,cx,dx,ex,fx,ay,by,cy,dy,ey,fy;

    public QuinticHermiteSpline(double x0, double y0, double theta0, double x1, double y1, double theta1) {

        // All equations are derived with the assumtion that the second derivative is zero at both endpoints
        // Equations will have to be rederived for implementation of gradient curvature optimization function

        vectorMagnitude = 1.5 * Math.sqrt(Math.pow((x1-x0), 2) + Math.pow((y1-y0), 2));

        dx0 = Math.cos(theta0) * vectorMagnitude;
        dx1 = Math.cos(theta1) * vectorMagnitude;

        dy0 = Math.sin(theta0) * vectorMagnitude;
        dy1 = Math.sin(theta1) * vectorMagnitude;

        ax = 6 * (x1 - x0) - 3 * (dx0 + dx1);
        bx = 15 * (x0 - x1) + 8 * dx0 + 7 * dx1;
        cx = 10 * (x1 - x0) - 6 * dx0 - 4 * dx1;
        dx = 0;
        ex = dx0;
        fx = x0;

        ay = 6 * (y1 - y0) - 3 * (dy0 + dy1);
        by = 15 * (y0 - y1) + 8 * dy0 + 7 * dy1;
        cy = 10 * (y1 - y0) - 6 * dy0 - 4 * dy1;
        dy = 0;
        ey = dy0;
        fy = y0;
    }

    public double x(double t) {
        return ax * t * t * t * t * t + bx * t * t * t * t + cx * t * t * t + dx * t * t + ex * t + fx;
    }

    public double y(double t) {
        return ay * t * t * t * t * t + by * t * t * t * t + cy * t * t * t + dy * t * t + ey * t + fy;
    }

    public double getRotation(double t) {
        return Math.atan2(dy(t), dx(t));
    }

    private double dx(double t) {
        return 5 * ax * t * t * t * t + 4 * bx * t * t * t + 3 * cx * t * t + 2 * dx * t + ex;
    }

    private double dy(double t) {
        return 5 * ay * t * t * t * t + 4 * by * t * t * t + 3 * cy * t * t + 2 * dy * t + ey;
    }

    private double ddx(double t) {
        return 20 * ax * t * t * t + 12 * bx * t * t + 6 * cx * t + 2 * dx;
    }

    private double ddy(double t) {
        return 20 * ay * t * t * t + 12 * by * t * t + 6 * cy * t + 2 * dy;
    }

    public double calculateLength() {

        final int kNumSamples = 100000;
		double integral = 0;
        double integrand, last_integrand = Math.sqrt(dx(0) * dx(0) + dy(0) * dy(0)) / kNumSamples;
        
		for (double i = 1 / kNumSamples; i <= kNumSamples; i += 1 / kNumSamples) {
            double t = i / kNumSamples;
			integrand = Math.sqrt(dx(t) * dx(t) + dy(t) * dy(t)) / kNumSamples;
			integral += (integrand + last_integrand) / (2 * kNumSamples);
			last_integrand = integrand;
        }
        
		return integral;
    }
    
    public double parametrizeSpline(double distance) {

        final int kNumSamples = 100000;
        double integral = 0;
        double last_integral = 0;
        double t = 0;
        double integrand, last_integrand = Math.sqrt(dx(0) * dx(0) + dy(0) * dy(0)) / kNumSamples;
        
		for (double i = 1 / kNumSamples; i <= kNumSamples; i += 1 / kNumSamples) {
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