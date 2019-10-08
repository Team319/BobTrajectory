package com.team319.ui;

import java.awt.GridLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WaypointPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField x = new JTextField();
    private JTextField y = new JTextField();
    private JTextField heading = new JTextField();
    private JTextField currentVelocity = new JTextField();
    private boolean isFirst;
    private boolean isLast;

    public WaypointPanel(DraggableWaypoint waypoint) {
        x.setText("" + roundTo4(waypoint.getX()));
        y.setText("" + roundTo4(waypoint.getY()));
        heading.setText("" + roundTo4(Math.toDegrees(waypoint.getHeading())));
        currentVelocity.setText("" + roundTo4(waypoint.getCurrentVelocity()));
        isFirst = waypoint.isFirst();
        isLast = waypoint.isLast();
        setupWindowLayout();
    }

    private double roundTo4(double value) {
        BigDecimal rounded = new BigDecimal(Double.toString(value));
        return rounded.setScale(4, RoundingMode.HALF_DOWN).doubleValue();
    }

    private void setupWindowLayout() {
        GridLayout layout = new GridLayout(3, 2);
        setLayout(layout);
        add(new JLabel("X:"));
        add(x);
        add(new JLabel("Y:"));
        add(y);
        add(new JLabel("Heading:"));
        add(heading);
        if (!isFirst && !isLast) {
            add(new JLabel("Current Velocity:"));
            add(currentVelocity);
            layout.setRows(4);
        }
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
