package model.generateur;

import java.util.Random;

public class GenerateurIntervalle implements StrategieGenerateur {
    private final double min_intervalle;
    private final double max_intervalle;


    public GenerateurIntervalle(double min, double max) {
        min_intervalle = min;
        max_intervalle = max;
    }

    @Override
    public double compute(double temp) {
        Random random = new Random();
        double value = min_intervalle + random.nextDouble(max_intervalle-min_intervalle);
        return value;
    }

    @Override
    public String toString() {
        return "Intervalle";
    }
}
