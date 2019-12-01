package com.team319.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.team254.lib.trajectory.Spline;
import com.team254.lib.trajectory.SplineGenerator;
import com.team319.trajectory.BobPath;
import com.team319.trajectory.CurveSegment;
import com.team319.trajectory.GenerationStrategy;
import com.team319.trajectory.SegmentedCurve;
import com.team319.trajectory.SegmentedCurveGenerator;

public class Plotter extends JPanel {

    private static final long serialVersionUID = 1L;
    private static String fieldImage = "/field_image.png";
    private BufferedImage img;
    private static double scale;
    private int fieldHeight;
    private WaypointListener waypointListener;
    private String pathName;
    private GenerationStrategy generationStrategy;

    public Plotter(String pathName, GenerationStrategy generationStrategy) {
        waypointListener = new WaypointListener(this);
        try {
            this.pathName = pathName;
            this.generationStrategy = generationStrategy;
            img = ImageIO.read(getClass().getResourceAsStream(fieldImage));
            scale = img.getHeight() / 27.0;
            fieldHeight = img.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw field
        g.drawImage(img, 0, 0, null);
        if (waypointListener.getWaypoints().size() >= 2) {
            switch (generationStrategy) {
                case SPLINE:
                    drawSplines(g);
                    break;
                case SEGMENTED_CURVE:
                    drawSegmentedCurves(g);
                    break;
                default:
                    drawSplines(g);

            }
        }

        waypointListener.getWaypoints().stream().forEach(w -> w.draw(g));
    }

    private void drawSplines(Graphics g) {
        g.setColor(Color.WHITE);
        for (ClickableSpline spline : waypointListener.getSplines()) {
            removeMouseListener(spline);
        }

        List<DraggableWaypoint> waypoints = new ArrayList<>(waypointListener.getWaypoints());
        try {
            Spline[] splines = SplineGenerator.getSplines(waypoints);
            List<ClickableSpline> clickableSplines = new ArrayList<>();
            for (int i = 0; i < splines.length; i++) {
                ClickableSpline clickableSpline = new ClickableSpline(splines[i], this, waypoints.get(i),
                        waypoints.get(i + 1));
                clickableSpline.draw(g);
                clickableSplines.add(clickableSpline);
            }
            waypointListener.getSplines().clear();
            waypointListener.getSplines().addAll(clickableSplines);
        } catch (Exception e) {
        }
    }

    private void drawSegmentedCurves(Graphics g) {
        g.setColor(Color.WHITE);
        for (ClickableSegmentedCurve segCurve : waypointListener.getSegmentedCurves()) {
            removeMouseListener(segCurve);
        }

        SegmentedCurve curve = SegmentedCurveGenerator.getSegmentedCurve(waypointListener.getWaypoints(), this);
        ClickableSegmentedCurve clickableCurve = new ClickableSegmentedCurve(curve, this);
        clickableCurve.draw(g);
        waypointListener.getSegmentedCurves().clear();
        waypointListener.getSegmentedCurves().add(clickableCurve);

    }

    @Override
    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(100, 100);
        } else {
            return new Dimension(img.getWidth(), img.getHeight());
        }
    }

    public static int convertXToPixel(double value) {
        return (int) (value * scale);
    }

    public static double convertXFromPixel(double pixel) {
        return pixel / scale;
    }

    public static int convertYToPixel(double value) {
        return (int) ((13.5 - value) * scale);
    }

    public static double convertYFromPixel(double pixel) {
        return (27 - pixel / scale) - 13.5;
    }

    public BobPath getPath() {
        return new BobPath(pathName, waypointListener.getWaypoints());
    }

    /**
     * @return the fieldHeight
     */
    public int getFieldHeight() {
        return fieldHeight;
    }

    /**
     * @param fieldHeight the fieldHeight to set
     */
    public void setFieldHeight(int fieldHeight) {
        this.fieldHeight = fieldHeight;
    }

    /**
     * @return the waypointListener
     */
    public WaypointListener getWaypointListener() {
        return waypointListener;
    }

    /**
     * @param waypointListener the waypointListener to set
     */
    public void setWaypointListener(WaypointListener waypointListener) {
        this.waypointListener = waypointListener;
    }

    /**
     * @return the pathName
     */
    public String getPathName() {
        return pathName;
    }

    /**
     * @param pathName the pathName to set
     */
    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
}