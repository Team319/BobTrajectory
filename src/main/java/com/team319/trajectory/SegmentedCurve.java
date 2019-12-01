package com.team319.trajectory;

import java.awt.dnd.DragGestureEvent;
import java.util.ArrayList;
import java.util.List;

import com.team319.ui.DraggableWaypoint;
import com.team319.ui.Plotter;

import java.awt.geom.Point2D;

/**
 * Piecewise
 */
public class SegmentedCurve {
    private Plotter plotter_;
    private List<DraggableWaypoint> waypoints_;
    private List<CurveSegment> curveSegments_;

    public SegmentedCurve(List<CurveSegment> curveSegments, List<DraggableWaypoint> waypoints) {
        this.curveSegments_ = curveSegments;
        this.waypoints_ = waypoints;
    }

    public SegmentedCurve(Plotter parentPanel) {
        this.curveSegments_ = new ArrayList<>();
        this.waypoints_ = new ArrayList<>();
        this.plotter_ = parentPanel;
    }

    public List<CurveSegment> getCurveSegments() {
        return curveSegments_;
    }

    public void addCurveSegment(CurveSegment curveSegment) {
        this.curveSegments_.add(curveSegment);
    }

    public void addCurveSegment(CurveSegment curveSegment, int index) {this.curveSegments_.add(index, curveSegment);}

    public void addWaypoint(DraggableWaypoint wp){
        this.waypoints_.add(wp);
    }

    public DraggableWaypoint getWaypoint(Point2D p){
        return this.waypoints_.stream()
                .filter(wp -> p.distance(wp.getPoint()) < 1E-9)
                .findFirst()
                .orElse(new DraggableWaypoint(p.getX(), p.getY(), 0,0,0, this.plotter_));
    }

    public double[] getXandY(double percentage) {
        double[] result = new double[2];

        DraggableWaypoint first = getStartWaypoint();
        DraggableWaypoint last = getEndWaypoint();

        result[0] = (first.getX() + last.getX()) / 2;
        result[1] = (first.getY() + last.getY()) / 2;

        return result;
    }

    public DraggableWaypoint getStartWaypoint(){
        return getWaypoint(curveSegments_.get(0).getStart());
    }

    public DraggableWaypoint getEndWaypoint(){
        return getWaypoint(curveSegments_.get(curveSegments_.size() - 1).getEnd());
    }

}