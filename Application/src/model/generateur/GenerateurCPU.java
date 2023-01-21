package model.generateur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class GenerateurCPU implements StrategieGenerateur {
    @Override
    public double compute(double temp) {
        double value = 0;
        InputStream file = null;
        try {
            file = new FileInputStream("/sys/class/thermal/thermal_zone2/temp");
            Scanner obj = new Scanner(file);
            while (obj.hasNextLine())
                value = Double.parseDouble(obj.nextLine())/1000;
        } catch (FileNotFoundException e) {
            Random random = new Random();
            value = -30 + random.nextDouble(70-(-30));
        }
        //temperatureProperty().set(value);
        return value;
    }

    @Override
    public String toString() {
        return "CPU";
    }
}
