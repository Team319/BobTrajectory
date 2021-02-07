package com.team319.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JPanel;

import com.team319.trajectory.RobotConfig;

public class WaypointListener {

    private Plotter panel;
    private List<DraggableWaypoint> waypoints = new ArrayList<>();
    private List<ClickableSpline> splines = new ArrayList<>();

    public WaypointListener(Plotter panel) {
        this.panel = panel;
        panel.addMouseListener(new WaypointCreator());
    }

    private Optional<DraggableWaypoint> getWaypointClicked(double x, double y) {
        for (DraggableWaypoint waypoint : waypoints) {
            if (waypoint.wasClicked(x, y) || waypoint.wasRotatorClicked(x, y)) {
                return Optional.of(waypoint);
            }
        }
        return Optional.empty();
    }

    private Optional<ClickableSpline> getSplineClicked(double x, double y) {
        for (ClickableSpline spline : splines) {
            if (spline.wasClicked(x, y)) {
                return Optional.of(spline);
            }
        }
        return Optional.empty();
    }
    
    private class WaypointCreator extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            Optional<DraggableWaypoint> waypoint = getWaypointClicked(e.getX(), e.getY());
            Optional<ClickableSpline> spline = getSplineClicked(e.getX(), e.getY());
            if ((spline.isPresent() || waypoint.isPresent()) && e.getButton() != 3) {
                return;
            }
            
            if (waypoint.isPresent() && e.getButton() == 3) {
                waypoints.remove(waypoint.get());
                panel.removeMouseListener(waypoint.get());
                panel.removeMouseMotionListener(waypoint.get());
                updateVelocities();
                return;
            }

            double heading = waypoints.isEmpty() ? 0 : waypoints.get(waypoints.size() - 1).getHeading();
            
            DraggableWaypoint newWaypoint = new DraggableWaypoint(Plotter.convertXFromPixel(e.getX()), Plotter.convertYFromPixel(e.getY()), heading, 0, RobotConfig.maxVelocity, panel);
            waypoints.add(newWaypoint);
            updateVelocities();
            panel.repaint();
        }
    }

    public void updateVelocities() {
        for (int i = 0; i < waypoints.size(); i++) {
            DraggableWaypoint waypoint = waypoints.get(i);
            if (i == 0) {
                waypoint.setCurrentVelocity(0);
                waypoint.setFirst(true);
                waypoint.setLast(false);
            } else if (i == waypoints.size() - 1) {
                waypoint.setCurrentVelocity(0);
                waypoint.setFirst(false);
                waypoint.setLast(true);
            } else {
                if (waypoint.getCurrentVelocity() == 0) {
                    waypoint.setCurrentVelocity(RobotConfig.maxVelocity);
                }
                waypoint.setFirst(false);
                waypoint.setLast(false);
            }
        }
    }

    /**
     * @return the panel
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * @param panel the panel to set
     */
    public void setPanel(Plotter panel) {
        this.panel = panel;
    }

    /**
     * @return the waypoints
     */
    public List<DraggableWaypoint> getWaypoints() {
        return waypoints;
    }

    /**
     * @param waypoints the waypoints to set
     */
    public void setWaypoints(List<DraggableWaypoint> waypoints) {
        this.waypoints.clear();
        this.waypoints.addAll(waypoints);
        updateVelocities();
    }

    /**
     * @return the splines
     */
    public List<ClickableSpline> getSplines() {
        return splines;
    }
}
