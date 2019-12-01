package com.team319.ui;

import com.team319.trajectory.RobotConfig;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JOptionPane;


public class DraggableWaypoint implements MouseListener, MouseMotionListener {

    static final int WAYPOINT_RADIUS = 7;
    static final int HIGHLIGHT_RADIUS = 14;
    static final int TAIL_RADIUS = 4;
    private static final int BUFFER_WIDTH = 10;
    private static final Color PURPLE = new Color(148, 0, 211);
    private Ellipse2D waypointIndicator;
    private Ellipse2D highlightIndicator;
    private Ellipse2D headingIndicator;

    private Point2D.Double point;
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

    /**
     * @param x
     * @param y
     * @param heading
     * @param currentVelocity
     * @param maxVelocity
     * @param parentPanel
     */
    public DraggableWaypoint(double x, double y, double heading, double currentVelocity, double maxVelocity, Plotter parentPanel) {
        this.point = new Point2D.Double(x, y);
        this.heading = heading;
        this.currentVelocity = currentVelocity;
        this.maxVelocity = maxVelocity;
        this.parentPanel = parentPanel;
        int xPixels = Plotter.convertXToPixel(point.x);
        int yPixels = Plotter.convertYToPixel(point.y);
        this.waypointIndicator = new Ellipse2D.Double(
                xPixels - WAYPOINT_RADIUS,
                yPixels - WAYPOINT_RADIUS,
                WAYPOINT_RADIUS * 2,
                WAYPOINT_RADIUS * 2);
        this.highlightIndicator = new Ellipse2D.Double(
                xPixels - WAYPOINT_RADIUS,
                yPixels - WAYPOINT_RADIUS,
                HIGHLIGHT_RADIUS * 2,
                HIGHLIGHT_RADIUS * 2);
        this.headingIndicator = new Ellipse2D.Double(
                getTailX(xPixels) - TAIL_RADIUS,
                getTailY(yPixels) - TAIL_RADIUS,
                TAIL_RADIUS * 2,
                TAIL_RADIUS * 2);
        parentPanel.addMouseListener(this);
        parentPanel.addMouseMotionListener(this);
    }

    public DraggableWaypoint(DraggableWaypoint other) {
        this(other.getX(), other.getY(), other.heading, other.currentVelocity, other.maxVelocity, other.parentPanel);
    }

    private double roundTo2(double value) {
        BigDecimal rounded = new BigDecimal(Double.toString(value));
        return rounded.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public boolean wasClicked(double x, double y) {
        double y1 = y;
        double y2 = Plotter.convertYToPixel(getY());
        double x1 = x;
        double x2 = Plotter.convertXToPixel(getX());
        return this.waypointIndicator.contains(x2, y2);
        //return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)) < (WAYPOINT_DIAMETER + BUFFER_WIDTH) / 2;
    }

    public boolean wasRotatorClicked(double x, double y) {
        double y1 = y;
        double x1 = x;
        double y2 = getTailY(Plotter.convertYToPixel(getY()));
        double x2 = getTailX(Plotter.convertXToPixel(getX()));
        return this.headingIndicator.contains(x2, y2);
        //return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)) < (WAYPOINT_RADIUS + BUFFER_WIDTH) / 2;
    }

    public void draw(Graphics g) {
        Graphics2D gc = (Graphics2D) g;
        gc.setStroke(new BasicStroke(3));

        drawHighlight(gc);
        drawPoint(gc);
        drawRobot(gc);
        drawTail(gc);
    }

    private void drawHighlight(Graphics2D gc) {
        int x = Plotter.convertXToPixel(getX());
        int y = Plotter.convertYToPixel(getY());
        if (highlighted) {
            gc.setColor(PURPLE);
            gc.fill(this.highlightIndicator);
            //gc.fillOval(x - (WAYPOINT_DIAMETER), y - (WAYPOINT_DIAMETER), WAYPOINT_DIAMETER * 2, WAYPOINT_DIAMETER * 2);
        }
    }

    private void drawPoint(Graphics2D gc) {
        int x = Plotter.convertXToPixel(getX());
        int y = Plotter.convertYToPixel(getY());
        gc.setColor(Color.BLACK);
        gc.fill(this.waypointIndicator);
        //gc.fillOval(x - (WAYPOINT_DIAMETER / 2), y - (WAYPOINT_DIAMETER / 2), WAYPOINT_DIAMETER, WAYPOINT_DIAMETER);
    }

    private void drawTail(Graphics2D gc) {
        int x = Plotter.convertXToPixel(getX());
        int y = Plotter.convertYToPixel(getY());
        int x2 = getTailX(x);
        int y2 = getTailY(y);
        gc.setColor(Color.BLACK);
        gc.fill(this.headingIndicator);
        gc.fillOval(x2 - (WAYPOINT_RADIUS / 3), y2 - (WAYPOINT_RADIUS / 3), (int) (WAYPOINT_RADIUS / 1.5), (int) (WAYPOINT_RADIUS / 1.5));
        // gc.drawLine(x, y, x2, y2);
    }

    private void drawRobot(Graphics2D gc) {
        double height = Plotter.convertXToPixel(RobotConfig.width);
        double width = Plotter.convertXToPixel(RobotConfig.length);
        int x = Plotter.convertXToPixel(getX());
        int y = Plotter.convertYToPixel(getY());

        gc.setColor(Color.ORANGE);
        gc.translate(x, y);
        gc.rotate(-heading);
        gc.draw(new RoundRectangle2D.Double(-width / 2, -height / 2, width, height, 10, 10));
        gc.rotate(heading);
        gc.translate(-x, -y);
    }

    private int getTailX(int x) {
        return (int) (x + Plotter.convertXToPixel(RobotConfig.length / 2) * Math.cos(-heading));
    }

    private int getTailY(int y) {
        return (int) (y + Plotter.convertXToPixel(RobotConfig.length / 2) * Math.sin(-heading));
    }

    public Point2D getPoint() {
        return this.point;
    }

    /**
     * @return the x
     */
    public double getX() {
        return this.point.getX();
    }

    /**
     * @return the y
     */
    public double getY() {
        return this.point.getY();
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
            System.out.println("waypoint clicked");
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
        this.point.x = waypointConfiguration.getWaypointX();
        this.point.y = waypointConfiguration.getWaypointY();
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
                    e.getY() - Plotter.convertYToPixel(getY()),
                    e.getX() - Plotter.convertXToPixel(getX()));
            setHeading(-newHeading);
        } else {
            this.point.x = roundTo2(Plotter.convertXFromPixel(e.getX()));
            this.point.y = roundTo2(Plotter.convertYFromPixel(e.getY()));
            highlighted = true;
        }
        parentPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }
}
