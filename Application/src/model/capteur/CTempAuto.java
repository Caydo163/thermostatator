package model.capteur;

import model.generateur.StrategieGenerateur;

public class CTempAuto extends CTemperature {
    public CTempAuto(String nom, double temperature, StrategieGenerateur strat) {
        super(nom, temperature);
        stratGen = strat;
    }
    private StrategieGenerateur stratGen;

    public void compute() {
        temperatureProperty().setValue(stratGen.compute(temperatureProperty().getValue()));
    }
}
