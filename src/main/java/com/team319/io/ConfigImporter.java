package com.team319.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.team319.trajectory.RobotConfig;

public class ConfigImporter {

    public static void importConfig() {
        File file = new File( "src\\main\\deploy\\paths\\config.txt");
        if (!file.exists()) {
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<Double> data = collectLines(br);
            br.close();
            RobotConfig.length = data.get(0);
            RobotConfig.width = data.get(1);
            RobotConfig.wheelBase = data.get(2);
            RobotConfig.maxVelocity = data.get(3);
            RobotConfig.maxAcceleration = data.get(4);
        } catch (Exception e) {
            System.out.println("There was an error importing the saved paths.");
            e.printStackTrace();
        }
    }

    private static List<Double> collectLines(BufferedReader br) throws IOException {
        List<Double> data = new ArrayList<>();
        String read;
        while ((read = br.readLine()) != null) {
            data.add(Double.parseDouble(read));
        }
        return data;
    }
}
