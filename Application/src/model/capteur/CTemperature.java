package model.capteur;

public abstract class CTemperature extends CTempAbstrait {

    public CTemperature(String nom, double temperature) {
        super(nom, temperature);
    }
    @Override
    public double getTemperature() {return this.temperature.get();}
}
