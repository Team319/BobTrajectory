package com.team319.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.team319.trajectory.ExportType;
import com.team319.trajectory.RobotConfig;

public class ConfigImporter {

    private static boolean IS_IMPORTED = false;

    /**
     * Import the configuration file, with the option to forcefully override any existing in-memory configuration
     * @param file
     * @param override
     */
    public static void importConfig(File file, boolean override) {
        if ( IS_IMPORTED && !override) {
            System.out.println("Skipping import");
            return;
        }
        importConfig(file);
    }

    /**
     * 
     * @param file - The directory where the config file is found
     */
    public static void importConfig(File file) {
        System.out.println("Passed config file " + file.getAbsolutePath());
        if (null == file){
            file = new File( "src/main/java/frc/paths/config.txt");
        } else {
            file = new File(file, "config.txt");
        }
        System.out.println("Looking for config file " + file.getAbsolutePath());
        if (!file.exists()) {
            return;
        }
        try {
            System.out.println("Importing config from " + file.getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> data = collectLines(br);
            br.close();
            RobotConfig.length = Double.parseDouble(data.get(0));
            RobotConfig.width = Double.parseDouble(data.get(1));
            RobotConfig.wheelBase = Double.parseDouble(data.get(2));
            RobotConfig.maxVelocity = Double.parseDouble(data.get(3));
            RobotConfig.maxAcceleration = Double.parseDouble(data.get(4));
            RobotConfig.exportType = ExportType.valueOf(data.get(5));
            RobotConfig.pathPackage = data.get(6);
            RobotConfig.pathLocation = data.get(7);

            if (RobotConfig.pathPackage == null || RobotConfig.pathPackage.isEmpty()) {
                RobotConfig.pathPackage = "frc.paths";
            }

            if (RobotConfig.pathLocation == null || RobotConfig.pathLocation.isEmpty()) {
                RobotConfig.pathLocation = "src/main/java/frc/paths/";
            }
            IS_IMPORTED = true;
        } catch (Exception e) {
            System.out.println("There was an error importing the saved paths.");
            e.printStackTrace();
        }
    }

    private static List<String> collectLines(BufferedReader br) throws IOException {
        List<String> data = new ArrayList<>();
        String read;
        while ((read = br.readLine()) != null) {
            data.add(read);
        }
        return data;
    }
}
