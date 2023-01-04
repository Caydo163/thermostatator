package model;

import java.util.Random;

public class GenerateurVariation implements StrategieGenerateur {
    private final double val_variation;


    public GenerateurVariation(double variation) {
        val_variation = variation;
    }

    public double compute(double temp) {
        Random random = new Random();
        double variation = random.nextDouble(val_variation);
        int signe = random.nextInt(2);
        double value = (signe==0) ? temp-variation : temp+variation;
        if(value < -30) {
            return -30;
        } else if (value > 70) {
            return 70;
        } else {
            return value;
        }
    }
}
