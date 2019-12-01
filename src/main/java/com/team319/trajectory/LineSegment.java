package com.team319.trajectory;

import com.team319.ui.DraggableWaypoint;

import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Optional;


/**
 * LineSegment
 */
public class LineSegment implements CurveSegment {

    private Line2D.Double line_;

    public LineSegment(Point2D start, Point2D end) {
        line_ = new Line2D.Double(start, end);
    }

    public LineSegment(Line2D.Double line){
        this.line_ = line;
    }

    public LineSegment(LineSegment other) {
        this(other.getLine().getP1(), other.getLine().getP2());
    }

    public LineSegment offsetAlongNormal(double distance){
        double x_hat = Math.cos(this.getNormal()) * distance;
        double y_hat = Math.sin(this.getNormal()) * distance;

        Line2D.Double newLine = new Line2D.Double(line_.x1 + x_hat, line_.y1 + y_hat, line_.x2+x_hat, line_.y2+y_hat);
        return new LineSegment(newLine);
    }

    public Optional<Point2D> intersect(LineSegment other) {
        if (this.getLine().intersectsLine(other.getLine())) {
            return getIntersection(this, other);
        }
        return Optional.empty();
    }

    public boolean intersects(LineSegment other){
        return this.line_.intersectsLine(other.getLine());
    }

    public Optional<Point2D> getIntersection(final LineSegment line1, final LineSegment line2) {

        final double x1, y1, x2, y2, x3, y3, x4, y4;
        x1 = line1.getLine().getX1();
        y1 = line1.getLine().getY1();
        x2 = line1.getLine().getX2();
        y2 = line1.getLine().getY2();
        x3 = line2.getLine().getX1();
        y3 = line2.getLine().getY1();
        x4 = line2.getLine().getX2();
        y4 = line2.getLine().getY2();

        final double den = (x2-x1)*(y4-y3) - (x4-x3)*(y2-y1);

        final double xNum = (x2*y1 - x1*y2)*(x4-x3) - (x4*y3 - x3*y4) * (x2 - x1);
        final double yNum = (x2*y1 - x1*y2)*(y4-y3) - (x4*y3 - x3*y4) * (y2 - y1);

        final double x = xNum / den;
        final double y = yNum / den;

        return Optional.of(new Point2D.Double(x,y));
    }

    @Override
    public Point2D getStart() {
        return line_.getP1();
    }

    @Override
    public Point2D getEnd() {
        return line_.getP2();
    }

    @Override
    public Point2D getCenter() {
        return null;
    }

    @Override
    public double getRadius() {
        return 0;
    }

    public Line2D.Double getLine() {
        return this.line_;
    }

    @Override
    public boolean isLine() {
        return true;
    }

    @Override
    public boolean contains(Point2D point) {
        return line_.getBounds().contains(point);
    }

    public double getLength(){
        return line_.getP1().distance(line_.getP2());
    }

    public double getSlope() {
        return (line_.getY2() - line_.getY1()) / (line_.getX2() - line_.getX1());
    }

    public double getYIntercept() {
        return  line_.getY1() - (getSlope() * line_.getX1());
    }

    public double getAngleRadians() {
        double angle = Math.atan2((line_.y2 - line_.y1), (line_.x2 - line_.x1));
        return angle;
    }

    public double getAngleRadians(LineSegment connectedLine){
        if (this.getLine().getP2().distance(connectedLine.getLine().getP1()) > 1E-9){
            throw new Error("Connected line must start where this line ends.");
        }
        double a, b, c;
        a = connectedLine.getLength();
        b = this.getLength();
        c = new LineSegment(this.line_.getP1(), connectedLine.getLine().getP2()).getLength();

        final double theta = Math.acos((a*a+b*b-c*c) / (2*a*b));
        return theta;
    }

    public double getNormal() {
        return getAngleRadians() - Math.PI / 2;
    }

}