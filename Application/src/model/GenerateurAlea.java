package model;

import java.util.Random;

public class GenerateurAlea implements StrategieGenerateur {
    @Override
    public double compute(double temp) {
        Random random = new Random();
        double value = -30 + random.nextDouble(70-(-30));
        //temperatureProperty().set(value);
        return value;
    }
}
