/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2363.spline;

import com.team2363.geometry.Pose2d;

public class ParametricQuinticSpline extends Spline {

    private double vectorMagnitude;
    private double dx0,dx1,dy0,dy1;
    private double ax,bx,cx,dx,ex,fx,ay,by,cy,dy,ey,fy;

    public ParametricQuinticSpline(Pose2d p0, Pose2d p1) {

        // All equations are derived with the assumtion that the second derivative is zero at both endpoints
        // Equations will have to be rederived for implementation of gradient curvature optimization function

        vectorMagnitude = 1.5 * p0.getDistance(p1);

        dx0 = Math.cos(p0.getRotation()) * vectorMagnitude;
        dx1 = Math.cos(p1.getRotation()) * vectorMagnitude;

        dy0 = Math.sin(p0.getRotation()) * vectorMagnitude;
        dy1 = Math.sin(p1.getRotation()) * vectorMagnitude;

        ax = 6 * (p1.x() - p0.x()) - 3 * (dx0 + dx1);
        bx = 15 * (p0.x() - p1.x()) + 8 * dx0 + 7 * dx1;
        cx = 10 * (p1.x() - p0.x()) - 6 * dx0 - 4 * dx1;
        dx = 0;
        ex = dx0;
        fx = p0.x();

        ay = 6 * (p1.y() - p0.y()) - 3 * (dy0 + dy1);
        by = 15 * (p0.y() - p1.y()) + 8 * dy0 + 7 * dy1;
        cy = 10 * (p1.y() - p0.y()) - 6 * dy0 - 4 * dy1;
        dy = 0;
        ey = dy0;
        fy = p0.y();
    }

    public Pose2d getPoint(double t) {
        return new Pose2d(x(t), y(t), Math.atan2(dy(t), dx(t)));
    }

    private double x(double t) {
        return ax * t * t * t * t * t + bx * t * t * t * t + cx * t * t * t + dx * t * t + ex * t + fx;
    }

    private double y(double t) {
        return ay * t * t * t * t * t + by * t * t * t * t + cy * t * t * t + dy * t * t + ey * t + fy;
    }

    private double dx(double t) {
        return 5 * ax * t * t * t * t + 4 * bx * t * t * t + 3 * cx * t * t + 2 * dx * t + ex;
    }

    private double dy(double t) {
        return 5 * ay * t * t * t * t + 4 * by * t * t * t + 3 * cy * t * t + 2 * dy * t + ey;
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