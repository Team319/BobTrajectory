package com.team319.io;

import com.team319.trajectory.RobotConfig;
import java.io.File;

public class ConfigExporter {

    public static void exportConfig() {
        exportConfig(new File("src/main/java/frc/paths"));
    }

    /**
     * 
     * @param file - The Path where the config file is to be stored
     */
    public static void exportConfig(File file) {
        StringBuilder data = new StringBuilder();
			
        data.append(RobotConfig.length).append(System.lineSeparator());
        data.append(RobotConfig.width).append(System.lineSeparator());
        data.append(RobotConfig.wheelBase).append(System.lineSeparator());
        data.append(RobotConfig.maxVelocity).append(System.lineSeparator());
        data.append(RobotConfig.maxAcceleration).append(System.lineSeparator());
        data.append(RobotConfig.exportType).append(System.lineSeparator());

        FilePrinter.write(file.getAbsolutePath(), "/config.txt", data.toString());
    }
}
