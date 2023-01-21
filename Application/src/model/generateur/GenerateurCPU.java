package model.generateur;

import model.util.LecteurFichier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class GenerateurCPU implements StrategieGenerateur {
    @Override
    public double compute(double temp) {
        double value;
        try {
            value = LecteurFichier.lectureTemperatureCPU()/1000;
        } catch (NumberFormatException e) {
            value = 0;
        }
        return value;
    }

    @Override
    public String toString() {
        return "CPU";
    }
}
