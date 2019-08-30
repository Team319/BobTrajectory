package com.team319.ui;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WaypointPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField x = new JTextField();
    private JTextField y = new JTextField();
    private JTextField heading = new JTextField();
    private JTextField currentVelocity = new JTextField();
    private JCheckBox isBackwards = new JCheckBox();
    private boolean isFirst;
    private boolean isLast;

    public WaypointPanel(DraggableWaypoint waypoint) {
        x.setText("" + waypoint.getX());
        y.setText("" + waypoint.getY());
        heading.setText("" + Math.toDegrees(waypoint.getHeading()));
        currentVelocity.setText("" + waypoint.getCurrentVelocity());
        isBackwards.setSelected(waypoint.isBackwards());
        isFirst = waypoint.isFirst();
        isLast = waypoint.isLast();
        setupWindowLayout();
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
        } else if (isFirst) {
            add(new JLabel("Backwards:"));
            add(isBackwards);
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

    public boolean isBackwards() {
        return isBackwards.isSelected();
    }
}
