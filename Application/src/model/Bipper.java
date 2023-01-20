package model;

import javafx.application.Platform;
import javafx.beans.property.*;
import model.capteur.CTempAuto;
import model.capteur.CTemperature;

public class Bipper extends Thread{

    private CTemperature capteur;
    protected IntegerProperty tick = new SimpleIntegerProperty();
    public IntegerProperty tickProperty() {
        return tick;
    }
    private void setTick(int tick) {this.tick.set(tick);}
    public int getTick() { return tick.get();}

    protected BooleanProperty stop = new SimpleBooleanProperty();
    public BooleanProperty stopProperty() {
        return stop;
    }
    private void setStop(boolean stop) {
        this.stop.set(stop);
        if(stop) {
            this.start();
        }
    }
    public boolean getStop() { return stop.get();}


    public Bipper(CTemperature capteur, int tick) {
        this.capteur = capteur;
        setTick(tick);
        setStop(true);
    }
    @Override
    public void run() {
        if(capteur.getStratGen() != null) {
            while(getStop()) {
                try {
                    Thread.sleep(getTick());
                    Platform.runLater(() -> {
                        capteur.compute();
                    });
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
