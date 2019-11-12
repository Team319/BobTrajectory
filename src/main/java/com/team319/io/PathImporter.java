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
        File file = new File( "src/main/java/frc/paths/Paths.txt");
        return importPaths(file);
    }

    public static List<Plotter> importPaths(File file) {
        List<Plotter> paths = new ArrayList<>();
        if (!file.exists()) {
            return paths;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> data = collectLines(br);
            br.close();
            while (!data.isEmpty()) {
                String name = data.remove(0);
                Plotter plotter = new Plotter(name);
                paths.add(plotter);
                importWaypoints(data, plotter);
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

    private static void importWaypoints(List<String> data, Plotter plotter) {
        while (!data.isEmpty() && isNumeric(data.get(0))) {
            importWaypoint(data, plotter);
        }
        plotter.getWaypointListener().updateVelocities();
    }

    private static void importWaypoint(List<String> data, Plotter plotter) {
        double x = Double.parseDouble(data.remove(0));
        double y = Double.parseDouble(data.remove(0));
        double heading = Double.parseDouble(data.remove(0));
        double currentVelocity = Double.parseDouble(data.remove(0));
        double maxVelocity = Double.parseDouble(data.remove(0));

        DraggableWaypoint waypoint = new DraggableWaypoint(x, y, heading, currentVelocity, maxVelocity, plotter);
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
