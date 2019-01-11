package com.team319.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.team254.lib.trajectory.Spline;
import com.team254.lib.trajectory.SplineGenerator;
import com.team319.trajectory.BobPath;

public class Plotter extends JPanel {

    private static final long serialVersionUID = 1L;
    private BufferedImage img;
    private static double scale;
    private int fieldHeight;
    private WaypointListener waypointListener;
    private String pathName;

    public Plotter(BobPath path, String image) {
        waypointListener = new WaypointListener(this);
        try {
            this.pathName = path.getName();
            waypointListener.setWaypoints(path.getWaypoints());
            img = ImageIO.read(getClass().getResourceAsStream(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw field
        g.drawImage(img, 0, 0, null);
        drawWaypoints(g);
        if (waypointListener.getWaypoints().size() >= 2) {
            drawSplines(g);
        }
    }

    private void drawWaypoints(Graphics g) {
        for (DraggableWaypoint waypoint : waypointListener.getWaypoints()) {
            waypoint.draw(g);
            drawRobot(g, Color.ORANGE, 2, 
            Plotter.convertToPixel(waypoint.getX()), 
            Plotter.convertToPixel(waypoint.getY()), 
            waypoint.getHeading());
        }
    }

    private void drawSplines(Graphics g) {
        g.setColor(Color.BLACK);
        Spline[] splines = SplineGenerator.getSplines(waypointListener.getWaypoints());
        for (Spline spline : splines) {
           drawSpline(spline, g);
       }
    }

    private void drawSpline(Spline spline, Graphics g) {
        for (double i = 0; i <= 1; i += 0.005) {
            double[] xy = spline.getXandY(i);
            g.fillOval(
                ((int)(xy[0] * scale)), ((int)(xy[1] * scale)), 2, 2);
        }
    }

    private void drawRobot(Graphics g, Color color, int thickness, int robotX, int robotY, double heading) {
        double height = 3 * scale;
	    double width = 3 * scale;
		double x = robotX;
        double y = robotY;
        int[] xPoints = {(int)width / 2 - 5, (int)(width / 2) - 15, (int)(width / 2) - 15};
        int[] yPoints = {0, 10, -10};
	   
        Graphics2D gc = (Graphics2D)g;	   
        gc.setPaint(color);
        gc.setStroke(new BasicStroke(thickness));
        gc.translate(x, y);
        gc.rotate(heading);
        gc.draw(new RoundRectangle2D.Double(-width / 2 , -height / 2, width, height, 10, 10));
        gc.fillPolygon(xPoints, yPoints, 3);
        gc.rotate(-heading);
        gc.translate(-x, -y);
    }

    @Override
    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(100,100);
        } else {
            scale = img.getHeight() / 27.0;
            fieldHeight = img.getHeight();
            return new Dimension(img.getWidth(), img.getHeight());
        }
    }

    public static int convertToPixel(double value) {
        return (int)(value * scale);
    }

    public static double convertFromPixel(double pixel) {
        return pixel / scale;
    }

    public BobPath getPath() {
        return new BobPath(pathName, waypointListener.getWaypoints(), false);
    }

    /**
     * @return the img
     */
    public BufferedImage getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(BufferedImage img) {
        this.img = img;
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