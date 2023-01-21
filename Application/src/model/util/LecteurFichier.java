package model.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class LecteurFichier {
    public static double lectureTemperatureCPU() throws NumberFormatException {
        InputStream file = null;
        try {
            file = new FileInputStream("/sys/class/thermal/thermal_zone2/temp");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner obj = new Scanner(file);
        while (obj.hasNextLine()) {
            return Double.parseDouble(obj.nextLine());
        }
        throw new NumberFormatException();
    }
}
