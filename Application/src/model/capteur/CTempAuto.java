package model.capteur;

import model.Bipper;
import model.generateur.StrategieGenerateur;

public class CTempAuto extends CTemperature {
    public CTempAuto(String nom, double temperature, StrategieGenerateur strat) {
        super(nom, temperature);
        stratGen = strat;
        bipper = new Bipper(this, 1000);
    }

    private Bipper bipper;
    public Bipper getBipper() { return bipper; }
    private StrategieGenerateur stratGen;
    public StrategieGenerateur getStratGen() {return stratGen;}
    public void setStratGen(StrategieGenerateur strat) {stratGen = strat;}

    public void compute() {
        temperatureProperty().setValue(stratGen.compute(temperatureProperty().getValue()));
    }
}
