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

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory.Segment;
import com.team319.trajectory.BobPath;
import com.team319.trajectory.SrxTranslatorConfig;

public class FieldComponent extends JPanel {

    private static final long serialVersionUID = 1L;
    private BufferedImage img;
    private Path path;
    private  SrxTranslatorConfig config;
    private BobPath bobPath;

    private double scale;
    private int fieldHeight;

    public FieldComponent(String image, BobPath bobPath, Path path) {
        this.bobPath = bobPath;
        this.path = path;
        this.config = bobPath.getConfig();
        try {
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

        // Draw initial and final robot positions
        drawRobot(path.getTrajectory().getSegment(0), g, Color.BLUE, 7);
        drawRobot(path.getTrajectory().getSegment(
            path.getTrajectory().getNumSegments() - 1), g, Color.ORANGE, 7);

        drawPath(g);
        drawWaypoints(g);
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

    private void drawPath(Graphics g) {
        for (int i = 0; i < path.getTrajectory().getNumSegments(); i++) {
            Segment segment = path.getTrajectory().getSegment(i);
            drawPoint(segment.x, segment.y, g, getColor(segment.vel, config.max_vel), 2);
            if (i % 50 == 0) {
                drawRobot(segment, g, Color.GRAY, 1);
            }
        }
    }

    private void drawWaypoints(Graphics g) {
        for (int i = 0; i < bobPath.getWaypointSequence().getNumWaypoints(); i++) {
            double x = bobPath.getWaypointSequence().getWaypoint(i).x;
            double y = bobPath.getWaypointSequence().getWaypoint(i).y;
            drawPoint(x,  y, g, Color.DARK_GRAY, 8);
        }
    }

    private void drawRobot(Segment segment, Graphics g, Color color, int thickness) {
        double height = config.robotWidth / 12 * scale;
	    double width = config.robotLength / 12 * scale;
		double x = segment.x * scale;
	    double y = fieldHeight - segment.y * scale;
	   
        Graphics2D gc = (Graphics2D)g;	   
        gc.setPaint(color);
        gc.setStroke(new BasicStroke(thickness));
        gc.translate(x, y);
        gc.rotate(segment.heading);
        gc.draw(new RoundRectangle2D.Double(-width / 2 , -height / 2, width, height, 10, 10));
        gc.rotate(-segment.heading);
        gc.translate(-x, -y);
    }
    
    private void drawPoint(double x, double y, Graphics g, Color color, int thickness) {
        g.setColor(color);
        g.fillOval((int)(x * scale - thickness / 2), fieldHeight - (int)(y * scale + thickness / 2), thickness, thickness);

    }

    private Color getColor(double speed, double limit) {
		double absSpeed = Math.abs(speed);
		double absLimit = Math.abs(limit);
		if (absSpeed >= absLimit) {
			return Color.BLACK;
		}
		if (absSpeed / absLimit == 0.5) {
			return Color.decode("#ffff00");
		}
		double percentage = absSpeed / absLimit * 100;
	    int green = percentage < 50 ? 255 : (int)Math.floor(256 - (percentage - 50 ) * 5.12);
	    int red = percentage > 50 ? 255 : (int)Math.floor((percentage) * 5.12);
	    return Color.decode("#" + String.format("%02X", red) + String.format("%02X", green) + "00");
	}
}