package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class CTemperature extends Capteur {
    private DoubleProperty temperature = new SimpleDoubleProperty();

    public DoubleProperty temperatureProperty() {
        return temperature;
    }
    public void setTemperature(double temperature) {this.temperature.set(temperature);}
    public double getTemperature() {return this.temperature.get();}
    public CTemperature(String nom, double temperature) {
        super(nom);
        this.temperature.set(temperature);
    }



}
