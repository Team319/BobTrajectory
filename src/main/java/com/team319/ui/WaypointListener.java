/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team319.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JPanel;

/**
 * Add your docs here.
 */
public class WaypointListener {

    private static final int RIGHT_CLICK = 3;

    private Plotter panel;
    private List<DraggableWaypoint> waypoints = new ArrayList<>();
    private Optional<DraggableWaypoint> currentlySelectedWaypoint = Optional.empty();
    private boolean wasRotatorClicked = false;

    public WaypointListener(Plotter panel) {
        this.panel = panel;
        panel.addMouseListener(new WaypointCreator());
        panel.addMouseMotionListener(new WaypointMover());
    }

    private Optional<DraggableWaypoint> getClickedWaypoint(double x, double y) {
        for (DraggableWaypoint waypoint : waypoints) {
            if (waypoint.wasClicked(x, y)) {
                wasRotatorClicked = false;
                return Optional.of(waypoint);
            } else if (waypoint.wasRotatorClicked(x, y)) {
                wasRotatorClicked = true;
                return Optional.of(waypoint);
            }
        }
        return Optional.empty();
    }
    
    private class WaypointCreator extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == RIGHT_CLICK && currentlySelectedWaypoint.isPresent()) {
                waypoints.remove(currentlySelectedWaypoint.get());
                panel.repaint();
            } else if (!currentlySelectedWaypoint.isPresent()) {
                double lastWaypointHeading = waypoints.size() > 0 ? waypoints.get(waypoints.size() - 1).getHeading() : 0;
                waypoints.add(new DraggableWaypoint(
                    Plotter.convertFromPixel(e.getX()), 
                    Plotter.convertFromPixel(e.getY()), 
                    lastWaypointHeading, 
                    0, 
                    0));
                panel.repaint();
            }
            currentlySelectedWaypoint = Optional.empty();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            currentlySelectedWaypoint = getClickedWaypoint(e.getX(), e.getY());
        }
    }

    private class WaypointMover extends MouseMotionAdapter {
        
        @Override
        public void mouseDragged(MouseEvent e) {
            if (!currentlySelectedWaypoint.isPresent()) {
                return;
            }

            DraggableWaypoint waypoint = currentlySelectedWaypoint.get();
            if (wasRotatorClicked) {
                double newHeading = Math.atan2(
                    Plotter.convertToPixel(waypoint.getY()) - e.getY(), 
                    Plotter.convertToPixel(waypoint.getX()) - e.getX());
                waypoint.setHeading(newHeading);
            } else {
                waypoint.setXY(e.getX(), e.getY());
            }

            panel.repaint();
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
        if (waypoints == null) {
            this.waypoints.clear();
            return;
        }
        this.waypoints = waypoints;
    }

    public void drawWaypoints(Graphics g) {
        g.setColor(Color.ORANGE);
        for (DraggableWaypoint waypoint : getWaypoints()) {
            waypoint.draw(g);
        }
    }
}
