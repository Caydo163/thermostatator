package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class CTemperature extends Capteur {
    protected DoubleProperty temperature = new SimpleDoubleProperty();

    public DoubleProperty temperatureProperty() {
        return temperature;
    }
    private void setTemperature(double temperature) {this.temperature.set(temperature);}
    public double getTemperature() {return this.temperature.get();}
    public CTemperature(String nom, double temperature) {
        super(nom);
        //this.temperature.set(temperature);
    }



}
