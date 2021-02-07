package com.team319.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FilePrinter {

    public static void write(String fileLocation, String fileName, String data) {
        try {
            File file = new File(fileLocation + fileName);
            System.out.println("Saving to file: " + file.getCanonicalPath());
            file.getParentFile().mkdirs();
            // if file doesn't exist, then create it
            if (!file.exists()) {
                if(!file.createNewFile()) {
                    System.out.println("Could not create output file "+file.getCanonicalPath());
                    return;
                }                
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (Exception e) {
            System.out.println("Problem printing file: " + e);
        }
    }
}
