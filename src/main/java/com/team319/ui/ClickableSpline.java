package com.team319.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import com.team254.lib.trajectory.Spline;

public class ClickableSpline implements MouseListener{
    private static final int DIAMETER = 20;
    private static final int BUFFER_WIDTH = 10;
    private static final Color PURPLE = new Color(148, 0, 211);

    private Spline spline;
    private double clickableX;
    private double clickableY;
    private DraggableWaypoint endPoint;
    private DraggableWaypoint startPoint;

    private boolean highlighted;

    private Plotter parentPanel;

    public ClickableSpline(Spline spline, Plotter parentPanel, DraggableWaypoint startPoint, DraggableWaypoint endPoint) {
        this.spline = spline;
        this.parentPanel = parentPanel;
        this.clickableX = spline.getXandY(0.5)[0];
        this.clickableY = spline.getXandY(0.5)[1];
        this.endPoint = endPoint;
        this.startPoint = startPoint;
        parentPanel.addMouseListener(this);
    }

    public void draw(Graphics g) {
        Graphics2D gc = (Graphics2D) g;
        drawSpline(gc);
        drawHighlight(gc);
        drawClickable(gc);
    }

    private void drawHighlight(Graphics2D gc) {
        if (!highlighted) {
            return;
        }

        int x = Plotter.convertXToPixel(clickableX);
        int y = Plotter.convertYToPixel(clickableY);
        gc.setColor(PURPLE);
        gc.fillOval(x - (DIAMETER), y - (DIAMETER), DIAMETER * 2, DIAMETER * 2);
    }

    private void drawSpline(Graphics2D gc) {
        for (double i = 0; i <= 1; i += 0.005) {
            double[] xy = spline.getXandY(i);
                gc.fillOval(
                    Plotter.convertXToPixel(xy[0]) - 2, Plotter.convertYToPixel(xy[1]) - 2, 4, 4);
        }
    }

    private void drawClickable(Graphics2D gc) {
        gc.fillOval(
            Plotter.convertXToPixel(clickableX) - DIAMETER/2, Plotter.convertYToPixel(clickableY) - DIAMETER/2, DIAMETER, DIAMETER);
    }

    public boolean wasClicked(double x, double y) {
        double y1 = y;
        double y2 = Plotter.convertYToPixel(clickableY);
        double x1 = x;
        double x2 = Plotter.convertXToPixel(clickableX);
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)) < (DIAMETER + BUFFER_WIDTH) / 2;
    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { 
        if (!wasClicked(e.getX(), e.getY())) {
            return;
        }

        SplinePanel splineConfiguration = new SplinePanel(endPoint);
        int result = JOptionPane.showConfirmDialog(null, splineConfiguration, 
        "Path Configuration", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            endPoint.setMaxVelocity(splineConfiguration.getMaxVelocity());
            endPoint.setCurrentVelocity(Math.min(splineConfiguration.getMaxVelocity(), endPoint.getCurrentVelocity()));
            startPoint.setCurrentVelocity(Math.min(splineConfiguration.getMaxVelocity(), startPoint.getCurrentVelocity()));
        }

        parentPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }
}
