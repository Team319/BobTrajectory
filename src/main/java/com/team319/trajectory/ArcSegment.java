package com.team319.trajectory;

import com.team319.ui.DraggableWaypoint;

import javax.swing.text.html.Option;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ArcSegment
 */
public class ArcSegment implements CurveSegment {

    private Arc2D.Double arc_;
    private Point2D center;
    private double radius;

    public ArcSegment(LineSegment a, LineSegment b, double radius) {
        if (a.getEnd().distance(b.getStart()) > 1E-9) {
            throw new Error("Could not generate arc.  Line Segments must share a waypoint.");
        }

        this.center = calculateCenter(a, b, radius).get();
        this.radius = radius;

        Point2D intersectA = this.tangentIntersect(a).get();
        Point2D intersectB = this.tangentIntersect(b).get();

        LineSegment normal1 = new LineSegment(intersectA, center);
        LineSegment normal2 = new LineSegment(center, intersectB);

        double startAngle = Math.toDegrees(normal2.getAngleRadians());
        double extentAngle = Math.toDegrees(normal1.getAngleRadians(normal2));

        if (center.getY() > a.getEnd().getY()){
            extentAngle *= -1;
        }

        this.arc_ = new Arc2D.Double(
                center.getX() - radius,
                center.getY() + radius,
                radius * 2,
                radius * 2,
                startAngle,
                extentAngle,
                Arc2D.OPEN);
    }

    @Override
    public boolean isLine() {
        return false;
    }

    @Override
    public Point2D getStart() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Point2D getEnd() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Point2D getCenter() {
        return center;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    public Arc2D getArc() {
        return this.arc_;
    }

    @Override
    public boolean contains(Point2D point) {
        // TODO Auto-generated method stub
        return false;
    }

    private static Optional<Point2D> calculateCenter(LineSegment l1, LineSegment l2, double radius) {
        LineSegment newL1 = l1.offsetAlongNormal(-radius);
        LineSegment newL2 = l2.offsetAlongNormal(-radius);

        if (newL1.intersects(newL2)) {
            return newL1.intersect(newL2);
        } else {
            return l1.offsetAlongNormal(radius).intersect(l2.offsetAlongNormal(radius));
        }
    }

    public Optional<Point2D> tangentIntersect(LineSegment l) {
        final double x1 = l.getLine().x1 - center.getX();
        final double x2 = l.getLine().x2 - center.getX();
        final double y1 = l.getLine().y1 - center.getY();
        final double y2 = l.getLine().y2 - center.getY();

        final double dx = x2 - x1;
        final double dy = y2 - y1;
        final double dr = Math.sqrt((dx * dx) + (dy * dy));
        final double D = x1 * y2 - x2 * y1;

        final double discriminant = (radius * radius) * (dr * dr) - (D * D);

        if (discriminant > 1E-9) {
            throw new Error("Line Segment is not tangent to the circle.  " +
                    "Try intersect() instead of tangentIntersect()");
        }

        double x = (D * dy) / (dy * dy) + center.getX();
        double y = (-D * dx) / (dy * dy) + center.getY();

        return Optional.of(new Point2D.Double(x, y));
    }

    public Optional<List<Point2D>> intersect(LineSegment l) {
        double m, d, a, b, r;
        m = l.getSlope();
        d = l.getYIntercept();
        a = center.getX();
        b = center.getY();
        r = radius + 1E-9;  //Add a bit of fuzz for fuzzy math.

        final double delta = r * r * (1 + (m * m)) - (b - m * a - d) * (b - m * a - d);
        final double denominator = 1 + m * m;

        double x_minus = (a + b * m - d * m - Math.sqrt(delta)) / denominator;
        double x = (a + b * m - d * m + Math.sqrt(delta)) / denominator;
        double y_minus = (d + a * m - b * m * m - m * Math.sqrt(delta)) / denominator;
        double y = (d + a * m - b * m * m + m * Math.sqrt(delta)) / denominator;

        List<Point2D> intersections = new ArrayList<>();

        Point2D.Double plus = new Point2D.Double(x, y);
        Point2D.Double minus = new Point2D.Double(x_minus, y_minus);

        intersections.add(plus);

        // add the minus intersection if its significantly different from the
        // plus intersection.
        if (plus.distance(minus) > 1E-9) {
            intersections.add(minus);
        }

        return Optional.of(intersections);
    }

}