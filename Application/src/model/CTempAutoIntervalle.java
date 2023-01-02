package model;

import java.util.Random;

public class CTempAutoIntervalle extends CTempAuto {
    private final double min_intervalle;
    private final double max_intervalle;


    public CTempAutoIntervalle(String nom, double min, double max) {
        super(nom, 0);
        min_intervalle = min;
        max_intervalle = max;
    }

    @Override
    public void compute() {
        Random random = new Random();
        double value = min_intervalle + random.nextDouble(max_intervalle-min_intervalle);
        temperatureProperty().set(value);
    }
}
