package com.team319.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.team319.ui.DraggableWaypoint;
import com.team319.ui.Plotter;

public class PathImporter {

    public static List<Plotter> importPaths() {
        List<Plotter> paths = new ArrayList<>();
        File file = new File( "src\\main\\java\\frc\\arcs\\Paths.txt");
        if (!file.exists()) {
            return paths;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> data = collectLines(br);
            br.close();
            while (!data.isEmpty()) {
                String name = data.remove(0);
                boolean isBackwards = Boolean.valueOf(data.remove(0));
                Plotter plotter = new Plotter(name);
                paths.add(plotter);
                importWaypoints(data, plotter, isBackwards);
            }
        } catch (Exception e) {
            System.out.println("There was an error importing the saved paths.");
            e.printStackTrace();
            return new ArrayList<>();
        }
        return paths;
    }

    private static List<String> collectLines(BufferedReader br) throws IOException {
        List<String> data = new ArrayList<>();
        String read;
        while ((read = br.readLine()) != null) {
            data.add(read);
        }
        return data;
    }

    private static void importWaypoints(List<String> data, Plotter plotter, boolean isBackwards) {
        while (!data.isEmpty() && isNumeric(data.get(0))) {
            importWaypoint(data, plotter, isBackwards);
        }
        plotter.getWaypointListener().updateVelocities();
    }

    private static void importWaypoint(List<String> data, Plotter plotter, boolean isBackwards) {
        double x = Double.parseDouble(data.remove(0));
        double y = Double.parseDouble(data.remove(0));
        double heading = Double.parseDouble(data.remove(0));
        double currentVelocity = Double.parseDouble(data.remove(0));
        double maxVelocity = Double.parseDouble(data.remove(0));

        DraggableWaypoint waypoint = new DraggableWaypoint(x, y, heading, currentVelocity, maxVelocity, plotter);
        waypoint.setBackwards(isBackwards);
        plotter.getWaypointListener().getWaypoints().add(waypoint);
    }

    public static boolean isNumeric(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
