package com.beymen.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.Platform;

public class TxtResponse {
    private static final String currentDir = System.getProperty("user.dir");
    private static final String fileName = "response.txt";
    private static String getFilePath() {
        if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
            return currentDir + "/src/test/resources/" + fileName;
        } else {
            return currentDir + "\\src\\test\\resources\\" + fileName;
        }
    }

    public static void writeToTxt(String text) {
        try {
            String filePath = getFilePath();
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text + "\n");
            bufferedWriter.close();
            Log.info("Text successfully written to txt file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
