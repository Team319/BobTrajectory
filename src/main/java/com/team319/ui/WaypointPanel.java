package com.team319.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WaypointPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField x = new JTextField();
    private JTextField y = new JTextField();
    private JTextField heading = new JTextField();
    private JTextField currentVelocity = new JTextField();
    private boolean isFirst;
    private boolean isLast;

    private static final double INCH = 1.0/12.0;

    public WaypointPanel(DraggableWaypoint waypoint) {
        x.setText("" + roundTo4(waypoint.getX()));
        y.setText("" + roundTo4(waypoint.getY()));
        heading.setText("" + roundTo4(Math.toDegrees(waypoint.getHeading())));
        currentVelocity.setText("" + roundTo4(waypoint.getCurrentVelocity()));
        isFirst = waypoint.isFirst();
        isLast = waypoint.isLast();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setupFieldPanel();
        add(new JSeparator());
        setupBumpPanel();
    }

    private double roundTo4(double value) {
        BigDecimal rounded = new BigDecimal(Double.toString(value));
        return rounded.setScale(4, RoundingMode.HALF_DOWN).doubleValue();
    }

    private void setupFieldPanel() {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridLayout layout = new GridLayout(3, 2);
        fieldPanel.setLayout(layout);
        fieldPanel.add(new JLabel("X:"));
        fieldPanel.add(x);
        fieldPanel.add(new JLabel("Y:"));
        fieldPanel.add(y);
        fieldPanel.add(new JLabel("Heading:"));
        fieldPanel.add(heading);
        if (!isFirst && !isLast) {
            fieldPanel.add(new JLabel("Current Velocity:"));
            fieldPanel.add(currentVelocity);
            layout.setRows(4);
        }
        add(fieldPanel);
    }

    private void setupBumpPanel() {
        JPanel bumpPanel = new JPanel(new BorderLayout());
        bumpPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton forward = new JButton("Forward");
        forward.addActionListener(e -> { bumpForward(); });
        bumpPanel.add(forward, BorderLayout.NORTH);

        JButton left = new JButton("Left");
        left.addActionListener(e -> { bumpLeft(); });
        bumpPanel.add(left, BorderLayout.WEST);

        JButton right = new JButton("Right");
        right.addActionListener(e -> { bumpRight(); });
        bumpPanel.add(right, BorderLayout.EAST);

        JButton back = new JButton("Back");
        back.addActionListener(e -> { bumpBack(); });
        bumpPanel.add(back, BorderLayout.SOUTH);

        JLabel bumpLabel = new JLabel("Bump an inch");
        bumpLabel.setHorizontalAlignment(JLabel.CENTER);
        bumpLabel.setVerticalAlignment(JLabel.CENTER);
        bumpPanel.add(bumpLabel, BorderLayout.CENTER);

        add(bumpPanel);
    }

    private void bumpForward() {
        double currentX = getWaypointX();
        double currentY = getWaypointY();
        double bumpedX = currentX + Math.cos(-getWaypointHeading()) * INCH;
        double bumpedY = currentY + Math.sin(-getWaypointHeading()) * INCH;
        x.setText("" + roundTo4(bumpedX));
        y.setText("" + roundTo4(bumpedY));
    }

    private void bumpLeft() {
        double currentX = getWaypointX();
        double currentY = getWaypointY();
        double bumpedX = currentX + Math.cos(-getWaypointHeading() + Math.toRadians(90)) * INCH;
        double bumpedY = currentY + Math.sin(-getWaypointHeading() + Math.toRadians(90)) * INCH;
        x.setText("" + roundTo4(bumpedX));
        y.setText("" + roundTo4(bumpedY));
    }

    private void bumpRight() {
        double currentX = getWaypointX();
        double currentY = getWaypointY();
        double bumpedX = currentX + Math.cos(-getWaypointHeading() - Math.toRadians(90)) * INCH;
        double bumpedY = currentY + Math.sin(-getWaypointHeading() - Math.toRadians(90)) * INCH;
        x.setText("" + roundTo4(bumpedX));
        y.setText("" + roundTo4(bumpedY));
    }

    private void bumpBack() {
        double currentX = getWaypointX();
        double currentY = getWaypointY();
        double bumpedX = currentX - Math.cos(-getWaypointHeading()) * INCH;
        double bumpedY = currentY - Math.sin(-getWaypointHeading()) * INCH;
        x.setText("" + roundTo4(bumpedX));
        y.setText("" + roundTo4(bumpedY));
    }

    public double getWaypointX() {
        return Double.valueOf(x.getText());
    }

    public double getWaypointY() {
        return Double.valueOf(y.getText());
    }

    public double getWaypointHeading() {
        return Math.toRadians(Double.valueOf(heading.getText()));
    }

    public double getCurentVelocity() {
        return Double.valueOf(currentVelocity.getText());
    }
}
