package com.team319.ui;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.team319.trajectory.BobPath;
import com.team319.trajectory.PathExporter;
import com.team319.trajectory.PathImporter;

public class PlotterFrame extends JFrame {

    public static void main(String... args) {
        new PlotterFrame();
    }

    private static final long serialVersionUID = 1L;
    private List<Plotter> paths = new ArrayList<>();

    public PlotterFrame() {
        importPaths();
        setTitle("Paths");
        setLayout(new FlowLayout());
        setVisible(true);
        JTabbedPane tabs = new JTabbedPane();
        tabs.setTabPlacement(JTabbedPane.LEFT);
        add(tabs);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayField(tabs);
        pack();

        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                exportPaths();
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }

    private void importPaths() {
        for (BobPath path : PathImporter.importPaths()) {
            paths.add(new Plotter(path, "/field_image.png"));
        }
        if (paths.isEmpty()) {
            paths.add(new Plotter(new BobPath("TEST", new ArrayList<>(), false), "/field_image.png"));
        }
    }

    private void displayField(JTabbedPane tabs) {
        for (Plotter path : paths) {
            tabs.addTab(path.getPathName(), path);
        }
    }

    private void exportPaths() {
        PathExporter.exportPaths(paths.stream().map(p -> p.getPath()).collect(Collectors.toList()));
    }
}