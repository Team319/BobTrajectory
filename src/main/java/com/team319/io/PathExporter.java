package com.team319.io;

import java.util.List;

import com.team319.trajectory.BobPath;

public class PathExporter {

    public static void exportPaths(List<BobPath> paths) {
        
        StringBuilder data = new StringBuilder();
			
        for (BobPath path : paths) {
            data.append(path.toString());
        }
        FilePrinter.write("Paths.txt", data.toString());
    }
}
