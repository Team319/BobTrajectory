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

    /**
     * 
     * @param file - The directory where the config file is found
     */
    public static void importConfig(File file) {
        if (null == file){
            file = new File( "src/main/java/frc/paths/config.txt");
        } else {
            file = new File(file, "config.txt");
        }
        if (!file.exists()) {
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> data = collectLines(br);
            br.close();
            RobotConfig.length = Double.parseDouble(data.get(0));
            RobotConfig.width = Double.parseDouble(data.get(1));
            RobotConfig.wheelBase = Double.parseDouble(data.get(2));
            RobotConfig.maxVelocity = Double.parseDouble(data.get(3));
            RobotConfig.maxAcceleration = Double.parseDouble(data.get(4));
            RobotConfig.exportType = ExportType.valueOf(data.get(5));
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
