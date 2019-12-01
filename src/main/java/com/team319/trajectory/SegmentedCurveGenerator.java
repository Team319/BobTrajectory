package com.team319.trajectory;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.team319.ui.DraggableWaypoint;
import com.team319.ui.Plotter;

/**
 * SegmentedPathGenerator
 */
public class SegmentedCurveGenerator {

    public static SegmentedCurve getSegmentedCurve(List<DraggableWaypoint> waypoints, Plotter parentPanel) {
        List<LineSegment> lines = new ArrayList<>();

        for (int i = 0; i < waypoints.size() - 1; i++) {
            LineSegment newLine = new LineSegment(waypoints.get(i).getPoint(), waypoints.get(i + 1).getPoint());
            waypoints.get(i).setHeading(newLine.getAngleRadians());
            waypoints.get(i+1).setHeading(newLine.getAngleRadians());
            lines.add(newLine);
        }

        SegmentedCurve segCurve = new SegmentedCurve(parentPanel);

        if (lines.size() > 0) {
            segCurve.addCurveSegment(lines.get(0));
        }

        for (int i = 1; i < lines.size(); i++) {
            if (lines.size() >= 2) {
                double radius = 2;
                LineSegment line1 = lines.get(i - 1);
                LineSegment line2 = lines.get(i);
                ArcSegment arc = new ArcSegment(line1, line2, radius);
                segCurve.getWaypoint(line1.getEnd()).setHeading(0);
                segCurve.getWaypoint(line2.getStart()).setHeading(0);
                segCurve.addCurveSegment(arc);

                /*
                Point2D intersectA = arc.tangentIntersect(line1).get();
                Point2D intersectB = arc.tangentIntersect(line2).get();

                LineSegment normal1 = new LineSegment(intersectA, arc.getCenter());
                LineSegment normal2 = new LineSegment(arc.getCenter(), intersectB);

                segCurve.addCurveSegment(normal1);
                segCurve.addCurveSegment(normal2);
                */
            }
            segCurve.addCurveSegment(lines.get(i));
        }

        return segCurve;

    }
}