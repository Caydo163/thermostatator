package model.capteur;

public class CTempManuel extends CTemperature{
    public CTempManuel(String nom, double temperature) {
        super(nom, temperature, null);
    }

    public void setTemperature(double temperature) {setTemperature(temperature);}
}
