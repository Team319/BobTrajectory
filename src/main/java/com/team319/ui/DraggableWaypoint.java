/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team319.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Add your docs here.
 */
public class DraggableWaypoint {

    private static final int WAYPOINT_DIAMETER = 20;
    private static final int BUFFER_WIDTH = 20;
    private static final int TAIL_LENGTH = 75;

    private double x;
    private double y;
    private double heading;
    private double maxVelocity;
    private double currentVelocity;

    public DraggableWaypoint(double x, double y, double heading, double currentVelocity, double maxVelocity) {
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.currentVelocity = currentVelocity;
        this.maxVelocity = maxVelocity;
    }

	public boolean wasClicked(double mouseX, double mouseY) {
        double y1 = mouseY;
        double y2 = Plotter.convertToPixel(y);
        double x1 = mouseX;
        double x2 = Plotter.convertToPixel(x);
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)) < (WAYPOINT_DIAMETER + BUFFER_WIDTH) / 2;
    }
    
    public boolean wasRotatorClicked(double mouseX, double mouseY) {
        double y1 = mouseY;
        double x1 = mouseX;
        double y2 = Plotter.convertToPixel(y) - TAIL_LENGTH * Math.sin(heading);
        double x2 = Plotter.convertToPixel(x) - TAIL_LENGTH * Math.cos(heading);
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)) < (WAYPOINT_DIAMETER + BUFFER_WIDTH) / 2;
	}

	public void setXY(int x, int y) {
        this.x = Plotter.convertFromPixel(x);
        this.y = Plotter.convertFromPixel(y);
    }

    public void draw(Graphics g) {
        Graphics2D gc = (Graphics2D)g;	   
        int x1 = Plotter.convertToPixel(x);
        int y1 = Plotter.convertToPixel(y);
        gc.setColor(Color.white);
        gc.fillOval(x1 - (WAYPOINT_DIAMETER / 2), y1 - (WAYPOINT_DIAMETER / 2), WAYPOINT_DIAMETER, WAYPOINT_DIAMETER);
        
        int y2 = (int)(y1 - TAIL_LENGTH * Math.sin(heading));
        int x2 = (int)(x1 - TAIL_LENGTH * Math.cos(heading));

        gc.fillOval(x2 - (WAYPOINT_DIAMETER / 4), y2 - (WAYPOINT_DIAMETER / 4), WAYPOINT_DIAMETER / 2, WAYPOINT_DIAMETER / 2);
        gc.drawLine(x1, y1, x2, y2);
        String waypointPosText = String.format("(%.2f, %.2f)", x, y);
        String waypointHeadingText = String.format("%.2f deg", Math.toDegrees(heading));
        drawCenteredString(g, waypointPosText, x1, y1 - 30, g.getFont());
        drawCenteredString(g, waypointHeadingText, x1, y1 + 30, g.getFont());
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
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

    public void drawCenteredString(Graphics g, String text, int x, int y, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int xPos = x + (-metrics.stringWidth(text)) / 2;
        int yPos = y + ((-metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, xPos, yPos);
    }

    @Override
    public String toString() {
        return new StringBuilder()
        .append(x).append("\n")
        .append(y).append("\n")
        .append(heading).append("\n")
        .append(currentVelocity).append("\n")
        .append(maxVelocity).toString();
    }
}
