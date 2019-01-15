/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team319.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.team319.trajectory.BobPath;
import com.team319.ui.DraggableWaypoint;

/**
 * Add your docs here.
 */
public class PathImporter {

    public static List<BobPath> importPaths() {
        List<BobPath> paths = new ArrayList<>();
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
                List<DraggableWaypoint> waypoints = importWaypoints(data);
                paths.add(new BobPath(name, waypoints, false));
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

    private static List<DraggableWaypoint> importWaypoints(List<String> data) {
        List<DraggableWaypoint> waypoints = new ArrayList<>();
        while (!data.isEmpty() && isNumeric(data.get(0))) {
            waypoints.add(importWaypoint(data));
        }
        return waypoints;
    }

    private static DraggableWaypoint importWaypoint(List<String> data) {
        double x = Double.parseDouble(data.remove(0));
        double y = Double.parseDouble(data.remove(0));
        double heading = Double.parseDouble(data.remove(0));
        double currentVelocity = Double.parseDouble(data.remove(0));
        double maxVelocity = Double.parseDouble(data.remove(0));

        return new DraggableWaypoint(x, y, heading, currentVelocity, maxVelocity);
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
