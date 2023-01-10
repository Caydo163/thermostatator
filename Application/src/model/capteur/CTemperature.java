package model.capteur;

import javafx.collections.ObservableList;

public abstract class CTemperature extends CTempAbstrait {

    public CTemperature(String nom, double temperature) {
        super(nom, temperature);
    }
    @Override
    public double getTemperature() {return this.temperature.get();}

    @Override
    public ObservableList<CTempAbstrait> getLesCapteurs() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }
}
