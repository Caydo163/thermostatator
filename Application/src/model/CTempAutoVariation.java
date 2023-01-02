package model;

import java.util.Random;

public class CTempAutoVariation extends CTempAuto {
    private final double val_variation;


    public CTempAutoVariation(String nom, double variation, double temp) {
        super(nom, temp);
        val_variation = variation;
    }

    @Override
    public void compute() {
        Random random = new Random();
        double variation = random.nextDouble(val_variation);
        int signe = random.nextInt(2);
        double value = (signe==0) ? getTemperature()-variation : getTemperature()+variation;
        if(value < -30) {
            temperatureProperty().set(-30);
        } else if (value > 70) {
            temperatureProperty().set(70);
        } else {
            temperatureProperty().set(value);
        }
    }
}
