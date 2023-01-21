package model.capteur;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import model.util.Bipper;
import model.generateur.StrategieGenerateur;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CTemperature extends CTempAbstrait {

    private LocalDateTime updatedTime;

    public CTemperature(String nom, double temperature, StrategieGenerateur strat, Bipper bipper) {
        super(nom, temperature);
        setStratGen(strat);
        setTick(1);
        setActivate(true);
        updatedTime = LocalDateTime.now();
        bipper.addCapteur(this);
    }
    @Override
    public double getTemperature() {return this.temperature.get();}

    private IntegerProperty tick = new SimpleIntegerProperty();
    public IntegerProperty tickProperty() {
        return tick;
    }
    private void setTick(int tick) {this.tick.set(tick);}
    public int getTick() { return tick.get();}

    private BooleanProperty activate = new SimpleBooleanProperty();
    public BooleanProperty activateProperty() {
        return activate;
    }

    private void setActivate(boolean activate) {
        this.activate.set(activate);
    }
    public boolean getActivate() { return activate.get();}

    //public void setTemperature(double temperature) {setTemperature(temperature);}


    @Override
    public ObservableList<CTempAbstrait> getLesCapteurs() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    private StrategieGenerateur stratGen;
    public StrategieGenerateur getStratGen() {return stratGen;}
    public void setStratGen(StrategieGenerateur strat) {
        this.stratGen = strat;
        /*if(strat != null) {
            this.bipper = new Bipper(this, 1);
        }*/
    }

    public void compute() {
        if(stratGen != null && getActivate()) {
            if(ChronoUnit.SECONDS.between(updatedTime, LocalDateTime.now()) >= getTick()) {
                updatedTime = LocalDateTime.now();
                temperatureProperty().setValue(stratGen.compute(temperatureProperty().getValue()));
            }
        }
    }
}
