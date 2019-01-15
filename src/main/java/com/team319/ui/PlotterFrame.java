package com.team319.ui;

import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import com.team319.trajectory.BobPath;
import com.team319.io.ConfigExporter;
import com.team319.io.ConfigImporter;
import com.team319.io.PathExporter;
import com.team319.io.PathImporter;

public class PlotterFrame extends JFrame {

    public static void main(String... args) {
        new PlotterFrame();
    }

    private static final long serialVersionUID = 1L;
    JTabbedPane tabs = new JTabbedPane();

    public PlotterFrame() {
        importPaths();
        ConfigImporter.importConfig();
        setTitle("Paths");
        setLayout(new FlowLayout());
        setVisible(true);
        addWindowListener(new SaveHandler());
        setupTabPanel();
        pack();
    }

    private void setupTabPanel() {
        tabs.setTabPlacement(JTabbedPane.LEFT);
        add(tabs);

        Button newPathButton = new Button("New Path");
        newPathButton.addActionListener(new CreateNewPath());
        add(newPathButton);

        Button deletePathButton = new Button("Delete Path");
        deletePathButton.addActionListener(new DeletePath());
        add(deletePathButton);
    }

    private void importPaths() {
        for (BobPath path : PathImporter.importPaths()) {
            tabs.addTab(path.getName(), new Plotter(path, "/field_image.png"));
        }
    }

    private void exportPaths() {
        List<BobPath> paths = new ArrayList<>();
        for (Component tab : tabs.getComponents()) {
            paths.add(((Plotter) tab).getPath());        }
        PathExporter.exportPaths(paths);
    }

    private class CreateNewPath implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("New path name: ");
            Plotter newPath = new Plotter(new BobPath(name, new ArrayList<>(), false), "/field_image.png");
            tabs.addTab(newPath.getPathName(), newPath);
		}
    }

    private class DeletePath implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
            if (JOptionPane.showConfirmDialog (
                null, 
                "Are you sure you want to delete this path?",
                "Delete Path", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return;
                }
              tabs.remove(tabs.getSelectedComponent());      
		}
    }

    private class SaveHandler implements WindowListener {

        @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                exportPaths();
                ConfigExporter.exportConfig();
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