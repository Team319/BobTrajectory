package com.team319.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JOptionPane;

import com.team319.trajectory.RobotConfig;

public class DraggableWaypoint implements MouseListener, MouseMotionListener {

    static final int WAYPOINT_DIAMETER = 20;
    private static final int BUFFER_WIDTH = 10;
    private static final Color PURPLE = new Color(148, 0, 211);

    private double x;
    private double y;
    private double heading;
    private double maxVelocity;
    private double currentVelocity;

    private boolean highlighted;
    private boolean selected;
    private boolean rotatorClicked;
    private boolean moved;
    private boolean isFirst;
    private boolean isLast;

    private Plotter parentPanel;

    public DraggableWaypoint(double x, double y, double heading, double currentVelocity, double maxVelocity, Plotter parentPanel) {
        this.x = roundTo2(x);
        this.y = roundTo2(y);
        this.heading = roundTo2(heading);
        this.currentVelocity = currentVelocity;
        this.maxVelocity = maxVelocity;
        this.parentPanel = parentPanel;
        parentPanel.addMouseListener(this);
        parentPanel.addMouseMotionListener(this);
    }

    private double roundTo2(double value) {
        BigDecimal rounded = new BigDecimal(Double.toString(value));
        return rounded.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

	public boolean wasClicked(double x, double y) {
        double y1 = y;
        double y2 = Plotter.convertToPixel(getY());
        double x1 = x;
        double x2 = Plotter.convertToPixel(getX());
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)) < (WAYPOINT_DIAMETER + BUFFER_WIDTH) / 2;
    }
    
    public boolean wasRotatorClicked(double x, double y) {
        double y1 = y;
        double x1 = x;
        double y2 = getTailY(Plotter.convertToPixel(getY()));
        double x2 = getTailX(Plotter.convertToPixel(getX()));
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)) < (WAYPOINT_DIAMETER + BUFFER_WIDTH) / 2;
	}

    public void draw(Graphics g) {
        Graphics2D gc = (Graphics2D)g;	   
        gc.setStroke(new BasicStroke(3));

        drawHighlight(gc);
        drawPoint(gc);
        drawRobot(gc);
        drawTail(gc);
    }

    private void drawHighlight(Graphics2D gc) {
        int x = Plotter.convertToPixel(getX());
        int y = Plotter.convertToPixel(getY());
        if (highlighted) {
            gc.setColor(PURPLE);
            gc.fillOval(x - (WAYPOINT_DIAMETER), y - (WAYPOINT_DIAMETER), WAYPOINT_DIAMETER * 2, WAYPOINT_DIAMETER * 2);
        }
    }

    private void drawPoint(Graphics2D gc) {
        int x = Plotter.convertToPixel(getX());
        int y = Plotter.convertToPixel(getY());
        gc.setColor(Color.BLACK);
        gc.fillOval(x - (WAYPOINT_DIAMETER / 2), y - (WAYPOINT_DIAMETER / 2), WAYPOINT_DIAMETER, WAYPOINT_DIAMETER);
    }

    private void drawTail(Graphics2D gc) {
        int x = Plotter.convertToPixel(getX());
        int y = Plotter.convertToPixel(getY());
        int y2 = getTailY(y);
        int x2 = getTailX(x);
        gc.setColor(Color.BLACK);
        gc.fillOval(x2 - (WAYPOINT_DIAMETER / 3), y2 - (WAYPOINT_DIAMETER / 3), (int)(WAYPOINT_DIAMETER / 1.5), (int)(WAYPOINT_DIAMETER / 1.5));
        // gc.drawLine(x, y, x2, y2);
    }

    private void drawRobot(Graphics2D gc) {
        double height = Plotter.convertToPixel(RobotConfig.width);
	    double width = Plotter.convertToPixel(RobotConfig.length);
		int x = Plotter.convertToPixel(getX());
        int y = Plotter.convertToPixel(getY());
       
        gc.setColor(Color.ORANGE);
        gc.translate(x, y);
        gc.rotate(heading);
        gc.draw(new RoundRectangle2D.Double(-width / 2 , -height / 2, width, height, 10, 10));
        gc.rotate(-heading);
        gc.translate(-x, -y);
    }

    private int getTailX(int x) {
        return (int)(x + Plotter.convertToPixel(RobotConfig.length / 2) * Math.cos(heading));
    }

    private int getTailY(int y) {
        return (int)(y + Plotter.convertToPixel(RobotConfig.length / 2) * Math.sin(heading));
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the heading
     */
    public double getHeading() {
        return heading;
    }

    /**
     * @param heading the heading to set
     */
    public void setHeading(double heading) {
        this.heading = heading;
    }

    /**
     * @return the currentVelocity
     */
    public double getCurrentVelocity() {
        return currentVelocity;
    }

    /**
     * @param currentVelocity the currentVelocity to set
     */
    public void setCurrentVelocity(double currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    /**
     * @return the maxVelocity
     */
    public double getMaxVelocity() {
        return maxVelocity;
    }

    /**
     * @param maxVelocity the maxVelocity to set
     */
    public void setMaxVelocity(double maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    /**
     * @return the isFirst
     */
    public boolean isFirst() {
        return isFirst;
    }

    /**
     * @param isFirst the isFirst to set
     */
    public void setFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

        /**
     * @return the isLast
     */
    public boolean isLast() {
        return isLast;
    }

    /**
     * @param isLast the isLast to set
     */
    public void setLast(boolean isLast) {
        this.isLast = isLast;
    }

    @Override
    public String toString() {
        return new StringBuilder()
        .append(getX()).append("\n")
        .append(getY()).append("\n")
        .append(heading).append("\n")
        .append(currentVelocity).append("\n")
        .append(maxVelocity).toString();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 3) {
            return;
        }

        if (selected && !moved) {
            WaypointPanel waypointConfiguration = new WaypointPanel(DraggableWaypoint.this);
            int result = JOptionPane.showConfirmDialog(null, waypointConfiguration, 
            "Waypoint Configuration", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                updateWaypoint(waypointConfiguration);
            }
        }

        selected = false;
        moved = false;
        highlighted = false;
        parentPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        selected = wasClicked(e.getX(), e.getY());
        rotatorClicked = wasRotatorClicked(e.getX(), e.getY());
        highlighted = selected || rotatorClicked;
        parentPanel.repaint();
    }

    private void updateWaypoint(WaypointPanel waypointConfiguration) {
        x = waypointConfiguration.getWaypointX();
        y = waypointConfiguration.getWaypointY();
        heading = waypointConfiguration.getWaypointHeading();
        currentVelocity = waypointConfiguration.getCurentVelocity();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (!selected && !rotatorClicked) {
            return;
        }

        moved = true;

        if (rotatorClicked) {
            double newHeading = Math.atan2(
                e.getY() - Plotter.convertToPixel(getY()), 
                e.getX() - Plotter.convertToPixel(getX()));
            setHeading(roundTo2(newHeading));
        } else {
            x = roundTo2(Plotter.convertFromPixel(e.getX()));
            y = roundTo2(Plotter.convertFromPixel(e.getY()));
            highlighted = true;
        }
        parentPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent arg0) { }

    @Override
    public void mouseClicked(MouseEvent arg0) { }

    @Override
    public void mouseEntered(MouseEvent arg0) { }

    @Override
    public void mouseExited(MouseEvent arg0) { }
}
