package model;

public abstract class CTempAuto extends CTemperature {
    public CTempAuto(String nom, double temperature) {
        super(nom, temperature);
    }

    public abstract void compute();
}
