package com.team319.ui;

import java.awt.FlowLayout;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.team254.lib.trajectory.Path;
import com.team319.trajectory.SrxTranslatorConfig;

public class Viewer extends JFrame {

    JTabbedPane tabs;

    public Viewer() {
        setTitle("Paths");
        setLayout(new FlowLayout());
        setVisible(true);
        tabs = new JTabbedPane();
        tabs.setTabPlacement(JTabbedPane.LEFT);
        add(tabs);
    }

    public void addPath(Path path, SrxTranslatorConfig config) {
        displayField(path, config);
        pack();
    }

    private String getPathNameAndTime(Path path) {
        DecimalFormat df = new DecimalFormat("0.00##");
        StringBuilder title = new StringBuilder();
		title.append(path.getName()).append(" : ")
		.append(df.format(path.getTrajectory().getNumSegments() * path.getTrajectory().getSegment(0).dt))
        .append("s");
        return title.toString();
    }

    private void displayField(Path path, SrxTranslatorConfig config) {
        tabs.addTab(getPathNameAndTime(path), new FieldComponent("/field_image.png", path, config));
    }
}