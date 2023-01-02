package model;

import java.util.Random;

public class CTempAutoAlea extends CTempAuto {
    public CTempAutoAlea(String nom) {
        super(nom, 0);
    }

    @Override
    public void compute() {
        Random random = new Random();
        double value = -30 + random.nextDouble(70-(-30));
        temperatureProperty().set(value);
    }
}
