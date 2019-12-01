package com.team319.trajectory;

import java.awt.geom.Point2D;

import com.team319.ui.DraggableWaypoint;

/**
 * CurveSegment
 */
public interface CurveSegment {

    boolean isLine();

    Point2D getStart();

    Point2D getEnd();

    Point2D getCenter();

    double getRadius();

    boolean contains(Point2D point);

}