package model;

public class CTempAuto extends CTemperature {
    public CTempAuto(String nom, double temperature, StrategieGenerateur strat) {
        super(nom, temperature);
        stratGen = strat;
    }
    private StrategieGenerateur stratGen;
    public StrategieGenerateur getStratGen() {return stratGen;}

    public void compute() {
        temperatureProperty().setValue(stratGen.compute(temperatureProperty().getValue()));
    }
}
