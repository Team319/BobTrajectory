package com.team319.ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SplinePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField maxVelocity = new JTextField();

    public SplinePanel(DraggableWaypoint endPoint) {
        setupWindowLayout();
        maxVelocity.setText("" + endPoint.getMaxVelocity());
    }

    private void setupWindowLayout() {
        setLayout(new GridLayout(1, 2));
        add(new JLabel("Maximum Velocity:"));
        add(maxVelocity);
    }

    public double getMaxVelocity() {
        return Double.valueOf(maxVelocity.getText());
    }
}
