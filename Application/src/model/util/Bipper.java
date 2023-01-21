package model.util;

import javafx.application.Platform;
import javafx.beans.property.*;
import model.capteur.CTemperature;

public class Bipper extends Thread{

    private CTemperature capteur;
    private IntegerProperty tick = new SimpleIntegerProperty();
    public IntegerProperty tickProperty() {
        return tick;
    }
    private void setTick(int tick) {this.tick.set(tick);}
    public int getTick() { return tick.get();}

    private BooleanProperty stop = new SimpleBooleanProperty();
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
                    Thread.sleep(tickProperty().get()*1000);
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
