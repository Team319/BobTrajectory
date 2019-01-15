/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team319.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.team319.trajectory.BobPath;
import com.team319.trajectory.RobotConfig;

/**
 * Add your docs here.
 */
public class ConfigExporter {

    public static void exportConfig() {
        
        StringBuilder data = new StringBuilder();
			
        data.append(RobotConfig.length).append(System.lineSeparator());
        data.append(RobotConfig.width).append(System.lineSeparator());
        data.append(RobotConfig.maxVelocity).append(System.lineSeparator());
        data.append(RobotConfig.maxAcceleration).append(System.lineSeparator());

        try {
            File file = new File( "src\\main\\java\\frc\\arcs\\config.txt");
            file.getParentFile().mkdirs();
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            String output = data.toString();
            bw.write(output);
            bw.close();
        } catch (Exception e) {}
    }
}
