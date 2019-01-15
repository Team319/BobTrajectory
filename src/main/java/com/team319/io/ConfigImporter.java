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

import com.team319.trajectory.RobotConfig;

/**
 * Add your docs here.
 */
public class ConfigImporter {

    public static void importConfig() {
        File file = new File( "src\\main\\java\\frc\\arcs\\config.txt");
        if (!file.exists()) {
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<Double> data = collectLines(br);
            br.close();
            RobotConfig.length = data.get(0);
            RobotConfig.width = data.get(1);
            RobotConfig.maxVelocity = data.get(2);
            RobotConfig.maxAcceleration = data.get(3);
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
