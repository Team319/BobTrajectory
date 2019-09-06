package com.team319.io;

import com.team319.trajectory.RobotConfig;

public class ConfigExporter {

    public static void exportConfig() {
        
        StringBuilder data = new StringBuilder();
			
        data.append(RobotConfig.length).append(System.lineSeparator());
        data.append(RobotConfig.width).append(System.lineSeparator());
        data.append(RobotConfig.wheelBase).append(System.lineSeparator());
        data.append(RobotConfig.maxVelocity).append(System.lineSeparator());
        data.append(RobotConfig.maxAcceleration).append(System.lineSeparator());

        FilePrinter.write("config.txt", data.toString());
    }
}
