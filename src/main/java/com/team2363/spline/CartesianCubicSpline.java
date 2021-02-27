// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package com.team2363.spline;

// import com.team2363.geometry.Pose2d;

// public class CartesianCubicSpline extends Spline {

//     private double a,b,c;
//     private double dydx0, dydx1;
//     private double x0, y0, thetaOffset, distance;

// 	CartesianCubicSpline(Pose2d p0, Pose2d p1) {

//         x0 = p0.x();
//         y0 = p0.y();
//         thetaOffset = Math.atan2(p1.y() - p0.y(), p1.x() - p0.x());
//         distance = p0.getDistance(p1);

//         dydx0 = Math.tan((p0.getRotation() - thetaOffset) % (2 * Math.PI));
//         dydx1 = Math.tan((p1.getRotation() - thetaOffset) % (2 * Math.PI));

//         a = ;
//         b = ;
//         c = ;
// 	}

// 	private static boolean almostEqual(double x, double y) {
// 		return Math.abs(x - y) < 1E-6;
// 	}

// 	public static boolean reticulateSplines(double x0, double y0, double theta0, double x1, double y1, double theta1,
// 			Spline result) {
//         // System.out.println("Reticulating splines...");

// 		// Transform x to the origin
// 		if (distance == 0) {
// 			return false;
// 		}

// 		double theta0_hat = ChezyMath.getDifferenceInAngleRadians(result.theta_offset_, theta0);
// 		double theta1_hat = ChezyMath.getDifferenceInAngleRadians(result.theta_offset_, theta1);
// 		// We cannot handle vertical slopes in our rotated, translated basis.
// 		// This would mean the user wants to end up 90 degrees off of the straight
// 		// line between p0 and p1.
// 		if (almostEqual(Math.abs(theta0_hat), Math.PI / 2) || almostEqual(Math.abs(theta1_hat), Math.PI / 2)) {
// 			return false;
// 		}
// 		// We also cannot handle the case that the end angle is facing towards the
// 		// start angle (total turn > 90 degrees).
// 		if (Math.abs(ChezyMath.getDifferenceInAngleRadians(theta0_hat, theta1_hat)) >= Math.PI / 2) {
// 			return false;
// 		}
// 		// Turn angles into derivatives (slopes)
// 		double yp0_hat = Math.tan(theta0_hat);
// 		double yp1_hat = Math.tan(theta1_hat);

// 		// Calculate the cubic spline coefficients
// 		result.a_ = 0;
// 		result.b_ = 0;
// 		result.c_ = 0;

// 		return true;
// 	}

// 	public double calculateLength() {

// 		final int kNumSamples = 100000;
// 		double integral = 0;
// 		double integrand, last_integrand = Math.sqrt(1 + derivativeAt(0) * derivativeAt(0)) / kNumSamples;
// 		for (double i = 1; i <= kNumSamples; ++i) {
// 			double t = i / kNumSamples;
// 			integrand = Math.sqrt(1 + dydx(t) * dydx(t)) / kNumSamples;
// 			integral += (integrand + last_integrand) / 2;
// 			last_integrand = integrand;
// 		}

// 		return integral;
// 	}

// 	public double calculateInput(double distance) {
// 		final int kNumSamples = 100000;
// 		double arc_length = 0;
// 		double t = 0;
// 		double last_arc_length = 0;
// 		double dydt;
// 		double integrand, last_integrand = Math.sqrt(1 + dydx(0) * dydx(0)) / kNumSamples;
// 		distance /= knot_distance_;
// 		for (double i = 1; i <= kNumSamples; ++i) {
// 			t = i / kNumSamples;
// 			integrand = Math.sqrt(1 + dydx(t) * dydx(t)) / kNumSamples;
// 			arc_length += (integrand + last_integrand) / 2;
// 			if (arc_length > distance) {
// 				break;
// 			}
// 			last_integrand = integrand;
// 			last_arc_length = arc_length;
// 		}

// 		// Interpolate between samples.
// 		double interpolated = t;
// 		if (arc_length != last_arc_length) {
// 			interpolated += ((distance - last_arc_length) / (arc_length - last_arc_length) - 1) / (double) kNumSamples;
// 		}
// 		return interpolated;
//     }
    
//     public Pose2d getPoint(double t) {
// 		double x_hat = t * distance;
// 		double y_hat = (a_ * x_hat + b_) * x_hat * x_hat * x_hat * x_hat + c_ * x_hat * x_hat * x_hat
// 				+ d_ * x_hat * x_hat + e_ * x_hat;

// 		double cos_theta = Math.cos(thetaOffset);
//         double sin_theta = Math.sin(thetaOffset);
        
//         double x = x_hat * cos_theta - y_hat * sin_theta + x0;
//         double y = x_hat * sin_theta + y_hat * cos_theta + y0;
//         double theta = ;

// 		return new Pose2d(x, y, theta);
//     }
    
//     private double dydx(double t) {
//         return 3 * a * (t * distance) * (t * distance) + 2 * b * (t * distance) + c;
//     }
// }