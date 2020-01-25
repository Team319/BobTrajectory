package com.team319.io;

import com.team319.trajectory.RobotConfig;
import java.io.File;

public class ConfigExporter {

    private static File parentFolder;

    /**
     * Allows overriding of config file location
     * @param folder
     */
    public static void setConfigFolder(File folder) {
        parentFolder = folder;
    }

    public static void exportConfig() {
        if(parentFolder != null){
            exportConfig(parentFolder);
        } else {
            exportConfig(new File("src/main/java/frc/paths"));
        }
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
        data.append(RobotConfig.pathPackage).append(System.lineSeparator());
        data.append(RobotConfig.pathLocation).append(System.lineSeparator());
        

        FilePrinter.write(file.getAbsolutePath(), "/config.txt", data.toString());
    }
}
