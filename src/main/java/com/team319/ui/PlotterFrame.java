package com.team319.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.team319.io.ConfigExporter;
import com.team319.io.ConfigImporter;
import com.team319.io.PathExporter;
import com.team319.io.PathImporter;
import com.team319.io.TrajectoryExporter;
import com.team319.trajectory.BobPath;
import com.team319.trajectory.RobotConfig;

public class PlotterFrame extends JFrame {

    public static void main(String... args) {
        new PlotterFrame();
    }

    private static final long serialVersionUID = 1L;
    FieldTabs tabs = new FieldTabs();

    public PlotterFrame() {
        importPaths();
        ConfigImporter.importConfig();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-755/2, dim.height/2-730/2);
        setTitle("Paths");
        setVisible(true);
        addWindowListener(new SaveHandler());
        setLayout(new BorderLayout());
        setupTabPanel();
        setResizable(false);
        setBackground(new Color(50, 50, 50));
        pack();
    }

    private void setupTabPanel() {
        // setContentPane(tabs);
        getContentPane().setBackground(new Color(50, 50, 50));
        NewPathButton newPathButton = new NewPathButton();
        newPathButton.addActionListener(new CreateNewPath());
        // newPathButton.setBounds(0, -50, 50, 50);

        DeletePathButton deletePathButton = new DeletePathButton();
        deletePathButton.addActionListener(new DeletePath());
        // deletePathButton.setBounds(70, 635, 50, 50);

        ConfigurationButton configurationButton = new ConfigurationButton();
        configurationButton.addActionListener(new OpenConfiguration());
        // configurationButton.setBounds(130, 635, 50, 50);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(50, 50, 50));
        buttons.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        buttons.add(newPathButton);
        buttons.add(deletePathButton);
        buttons.add(configurationButton);

        add(tabs);
        add(buttons, BorderLayout.SOUTH);
    }

    private void importPaths() {
        for (Plotter path : PathImporter.importPaths()) {
            tabs.addTab(path.getPathName(), path);
        }
    }

    private void exportPaths() {
        List<BobPath> paths = new ArrayList<>();
        for (Component tab : tabs.getComponents()) {
            if (!(tab instanceof Plotter)) {
                continue;
            }
            paths.add(((Plotter) tab).getPath());
        }
        PathExporter.exportPaths(paths);
        TrajectoryExporter.exportTrajectory(paths);
    }

    private class CreateNewPath implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("New path name: ");
            Plotter newPath = new Plotter(name);
            tabs.addTab(newPath.getPathName(), newPath);
		}
    }

    private class DeletePath implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
            if (JOptionPane.showConfirmDialog (
                null, 
                "Are you sure you want to delete this path?",
                "Delete Path", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.NO_OPTION) {
                    return;
            }
            tabs.remove(tabs.getSelectedComponent());      
		}
    }

    private class OpenConfiguration implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
            ConfigurationPanel configuration = new ConfigurationPanel();
            int result = JOptionPane.showConfirmDialog(null, configuration, 
            "Configuration", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                updateConfig(configuration);
                tabs.repaint();
            }      
		}
    }

    private void updateConfig(ConfigurationPanel configuration) {
        RobotConfig.length = configuration.getRobotLength();
        RobotConfig.width = configuration.getRobotWidth();
        RobotConfig.wheelBase = configuration.getWheelBase();
        RobotConfig.maxAcceleration = configuration.getMaxAcceleration();
        RobotConfig.maxVelocity = configuration.getMaxVelocity();
    }

    private class SaveHandler implements WindowListener {

        @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog (
                            null, 
                            "Do you want to save the paths and config?",
                            "Save", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
                    exportPaths();
                    ConfigExporter.exportConfig();
                }
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
    }
}