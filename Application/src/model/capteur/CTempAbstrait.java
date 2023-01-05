package model.capteur;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class CTempAbstrait extends Capteur{
    public CTempAbstrait(String nom, double temperature) {
        super(nom);
        this.temperature.set(temperature);
    }

    protected DoubleProperty temperature = new SimpleDoubleProperty();
    public DoubleProperty temperatureProperty() {
        return temperature;
    }
    private void setTemperature(double temperature) {this.temperature.set(temperature);}
    public abstract double getTemperature() ;
}
