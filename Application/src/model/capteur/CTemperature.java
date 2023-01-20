package model.capteur;

import javafx.collections.ObservableList;
import model.Bipper;
import model.generateur.StrategieGenerateur;

public class CTemperature extends CTempAbstrait {

    public CTemperature(String nom, double temperature, StrategieGenerateur strat) {
        super(nom, temperature);
        setStratGen(strat);
    }
    @Override
    public double getTemperature() {return this.temperature.get();}

    public void setTemperature(double temperature) {setTemperature(temperature);}


    @Override
    public ObservableList<CTempAbstrait> getLesCapteurs() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    private Bipper bipper;
    public Bipper getBipper() { return bipper; }
    private StrategieGenerateur stratGen;
    public StrategieGenerateur getStratGen() {return stratGen;}
    public void setStratGen(StrategieGenerateur strat) {
        stratGen = strat;
        if(strat != null) {
            this.bipper = new Bipper(this, 1000);
        }
    }

    public void compute() {
        if(stratGen != null) {
            temperatureProperty().setValue(stratGen.compute(temperatureProperty().getValue()));
        }
    }
}
