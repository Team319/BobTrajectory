package com.team319.io;

import java.util.List;

import com.team319.trajectory.BobPath;
import java.io.File;

public class PathExporter {

    private static final String defaultPath = "src/main/java/frc/paths/";
    public static void exportPaths(List<BobPath> paths) {
        
        StringBuilder data = new StringBuilder();
			
        for (BobPath path : paths) {
            data.append(path.toString());
        }
        FilePrinter.write(defaultPath, "Paths.txt", data.toString());
    }

    public static void exportPath(BobPath path, File file) {
        try {
            FilePrinter.write(file.getAbsolutePath(),"/" + path.getName() + ".txt", path.toString());
        } catch (Exception e) {
            System.out.println("Execption trying to save a path. " + e + " " + e.getMessage());
        }
    }
}
