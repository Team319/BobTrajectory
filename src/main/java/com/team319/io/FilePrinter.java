package com.team319.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FilePrinter {

    public static void write(String fileLocation, String fileName, String data) {
        try {
            File file = new File(fileLocation + fileName);
            file.getParentFile().mkdirs();
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (Exception e) {}
    }
}
