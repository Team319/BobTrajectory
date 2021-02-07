package com.team319.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.team254.lib.trajectory.Spline;
import com.team254.lib.trajectory.SplineGenerator;
import com.team319.trajectory.BobPath;

public class Plotter extends JPanel {

    private static final long serialVersionUID = 1L;
    private static String fieldImage = "/2021_at_home.png";
    private BufferedImage img;
    private static double scale;
    private int fieldHeight;
    private WaypointListener waypointListener;
    private String pathName;

    private static final double FIELD_HEIGHT = 15.0;

    public Plotter(String pathName) {
        waypointListener = new WaypointListener(this);
        try {
            this.pathName = pathName;
            img = ImageIO.read(getClass().getResourceAsStream(fieldImage));
            scale = img.getHeight() / FIELD_HEIGHT;
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
            drawSplines(g);
        }

        waypointListener.getWaypoints().stream().forEach(w -> w.draw(g));
    }

    private void drawSplines(Graphics g) {
        g.setColor(Color.WHITE);
        for (ClickableSpline spline :  waypointListener.getSplines()) {
            removeMouseListener(spline);
        }
        
        List<DraggableWaypoint> waypoints = new ArrayList<>(waypointListener.getWaypoints());
        try {
            Spline[] splines = SplineGenerator.getSplines(waypoints);
            List<ClickableSpline> clickableSplines = new ArrayList<>();
            for (int i = 0; i < splines.length; i++) {
                ClickableSpline clickableSpline = new ClickableSpline(splines[i], this, waypoints.get(i), waypoints.get(i+1));
                clickableSpline.draw(g);
                clickableSplines.add(clickableSpline);
            }
        waypointListener.getSplines().clear();
        waypointListener.getSplines().addAll(clickableSplines);
        } catch (Exception e) { }
    }

    @Override
    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(100,100);
        } else {
            return new Dimension(img.getWidth(), img.getHeight());
        }
    }

    public static int convertXToPixel(double value) {
        return (int)(value * scale);
    }

    public static double convertXFromPixel(double pixel) {
        return pixel / scale;
    }

    public static int convertYToPixel(double value) {
        return (int)((FIELD_HEIGHT/2 - value) * scale);
    }

    public static double convertYFromPixel(double pixel) {
        return (FIELD_HEIGHT - pixel / scale) - FIELD_HEIGHT/2;
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