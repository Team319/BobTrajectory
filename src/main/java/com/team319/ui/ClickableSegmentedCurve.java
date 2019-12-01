package com.team319.ui;

import com.team319.trajectory.ArcSegment;
import com.team319.trajectory.CurveSegment;
import com.team319.trajectory.SegmentedCurve;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import javax.swing.JOptionPane;


public class ClickableSegmentedCurve implements MouseListener {
    private static final int DIAMETER = 20;
    private static final int BUFFER_WIDTH = 10;
    private static final Color PURPLE = new Color(148, 0, 211);

    private SegmentedCurve sCurve;
    private double clickableX;
    private double clickableY;
    private DraggableWaypoint endPoint;
    private DraggableWaypoint startPoint;

    private boolean highlighted;

    private Plotter parentPanel;

    public ClickableSegmentedCurve(SegmentedCurve sCurve, Plotter parentPanel) {
        this.sCurve = sCurve;
        this.parentPanel = parentPanel;
        this.clickableX = sCurve.getXandY(0.5)[0];
        this.clickableY = sCurve.getXandY(0.5)[1];
        this.endPoint = sCurve.getEndWaypoint();
        this.startPoint = sCurve.getStartWaypoint();
        parentPanel.addMouseListener(this);
    }

    public void draw(Graphics g) {
        Graphics2D gc = (Graphics2D) g;
        drawSegmentedCurve(gc);
        drawHighlight(gc);
        // drawClickable(gc);
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

    private void drawSegmentedCurve(Graphics2D gc) {
        sCurve.getCurveSegments().forEach(s -> {

            gc.setStroke(new BasicStroke(3));

            if (s.isLine()) {
                gc.drawLine(Plotter.convertXToPixel(s.getStart().getX()), Plotter.convertYToPixel(s.getStart().getY()),
                        Plotter.convertXToPixel(s.getEnd().getX()), Plotter.convertYToPixel(s.getEnd().getY()));
            } else {
                Arc2D arc = ((ArcSegment)s).getArc();
                Arc2D.Double drawArc = new Arc2D.Double(
                        Plotter.convertXToPixel(arc.getX()),
                        Plotter.convertYToPixel(arc.getY()),
                        Plotter.convertXToPixel(arc.getWidth()),
                        Plotter.convertXToPixel(arc.getHeight()),
                        arc.getAngleStart(),
                        arc.getAngleExtent(),
                        arc.getArcType());

                gc.draw(drawArc);
            }
        });
    }

    private void drawClickable(Graphics2D gc) {
        gc.fillOval(Plotter.convertXToPixel(clickableX) - DIAMETER / 2,
                Plotter.convertYToPixel(clickableY) - DIAMETER / 2, DIAMETER, DIAMETER);
    }

    public boolean wasClicked(double x, double y) {
        for (CurveSegment segment : sCurve.getCurveSegments()) {
            if (segment.contains(new Point2D.Double(x, y))) {
                System.out.println("segment clicked");
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!wasClicked(e.getX(), e.getY())) {
            return;
        }

        PathPanel configuration = new PathPanel(endPoint);
        int result = JOptionPane.showConfirmDialog(null, configuration, "Path Configuration",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            endPoint.setMaxVelocity(configuration.getMaxVelocity());
            endPoint.setCurrentVelocity(Math.min(configuration.getMaxVelocity(), endPoint.getCurrentVelocity()));
            startPoint.setCurrentVelocity(Math.min(configuration.getMaxVelocity(), startPoint.getCurrentVelocity()));
        }

        parentPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
}
