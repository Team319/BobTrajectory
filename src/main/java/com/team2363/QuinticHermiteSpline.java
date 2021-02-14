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

    public double xpos(double s) {
        return ax * s * s * s * s * s + bx * s * s * s * s + cx * s * s * s + dx * s * s + ex * s + fx;
    }

    public double ypos(double s) {
        return ay * s * s * s * s * s + by * s * s * s * s + cy * s * s * s + dy * s * s + ey * s + fy;
    }

    public double dx(double s) {
        return 5 * ax * s * s * s * s + 4 * bx * s * s * s + 3 * cx * s * s + 2 * dx * s + ex;
    }

    public double dy(double s) {
        return 5 * ay * s * s * s * s + 4 * by * s * s * s + 3 * cy * s * s + 2 * dy * s + ey;
    }

    public double ddx(double s) {
        return 20 * ax * s * s * s + 12 * bx * s * s + 6 * cx * s + 2 * dx;
    }

    public double ddy(double s) {
        return 20 * ay * s * s * s + 12 * by * s * s + 6 * cy * s + 2 * dy;
    }

    public double getAngle(double s) {
        return Math.atan2(dy(s), dx(s));
    }

    public double getCurvature(double s) {
        return Math.atan2(dy(s), dx(s));
    }
}