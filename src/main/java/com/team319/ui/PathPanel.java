package com.team319.ui;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.team319.trajectory.GenerationStrategy;

public class PathPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField maxVelocity = new JTextField();

    private final JComboBox<GenerationStrategy> generationStrategyCB = new JComboBox<GenerationStrategy>(
            GenerationStrategy.values());

    public PathPanel(final DraggableWaypoint endPoint) {
        setupWindowLayout();
        maxVelocity.setText("" + endPoint.getMaxVelocity());
    }

    private void setupWindowLayout() {
        setLayout(new GridLayout(2, 2));
        add(new JLabel("Maximum Velocity:"));
        add(maxVelocity);
        add(new JLabel("Strategy:"));
        add(generationStrategyCB);

    }

    public double getMaxVelocity() {
        return Double.valueOf(maxVelocity.getText());
    }

    public GenerationStrategy getGenerationStrategy() {
        return generationStrategyCB.getItemAt(generationStrategyCB.getSelectedIndex());

    }
}
