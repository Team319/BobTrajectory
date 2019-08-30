package com.team319.ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.team319.trajectory.RobotConfig;

public class ConfigurationPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField length = new JTextField();
    private JTextField width = new JTextField();
    private JTextField wheelBase = new JTextField();
    private JTextField maxAcceleration = new JTextField();
    private JTextField maxVelocity = new JTextField();

    public ConfigurationPanel() {
        setupWindowLayout();
        length.setText("" + RobotConfig.length);
        width.setText("" + RobotConfig.width);
        wheelBase.setText("" + RobotConfig.wheelBase);
        maxAcceleration.setText("" + RobotConfig.maxAcceleration);
        maxVelocity.setText("" + RobotConfig.maxVelocity);
    }

    private void setupWindowLayout() {
        setLayout(new GridLayout(5, 2));
        add(new JLabel("Robot Length (f):"));
        add(length);
        add(new JLabel("Robot Width (f):"));
        add(width);
        add(new JLabel("Wheel Base (f):"));
        add(wheelBase);
        add(new JLabel("Maximum Acceleration (f/s2):"));
        add(maxAcceleration);
        add(new JLabel("Maximum Velocity (f/s):"));
        add(maxVelocity);
    }

    public double getRobotLength() {
        return Double.valueOf(length.getText());
    }

    public double getRobotWidth() {
        return Double.valueOf(width.getText());
    }

    public double getWheelBase() {
        return Double.valueOf(wheelBase.getText());
    }

    public double getMaxAcceleration() {
        return Double.valueOf(maxAcceleration.getText());
    }

    public double getMaxVelocity() {
        return Double.valueOf(maxVelocity.getText());
    }
}
