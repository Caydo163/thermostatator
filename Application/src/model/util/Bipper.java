package model.util;

import javafx.application.Platform;
import javafx.beans.property.*;
import model.capteur.CTemperature;

import java.util.ArrayList;
import java.util.List;

public class Bipper extends Thread{

    private List<CTemperature> capteurs = new ArrayList<>();

    private BooleanProperty stop = new SimpleBooleanProperty();
    public BooleanProperty stopProperty() {
        return stop;
    }
    public void setStop(boolean stop) {
        this.stop.set(stop);
        if(stop) {
            this.start();
        }
    }
    public boolean getStop() { return stop.get();}

    public void addCapteur(CTemperature c) {
        capteurs.add(c);
    }

    public void removeCapteur(CTemperature c) {
        capteurs.remove(c);
    }

    public Bipper() {
        setStop(true);
    }
    @Override
    public void run() {
        while(getStop()) {
            try {
                Thread.sleep(1000);
                Platform.runLater(() -> {
                    capteurs.forEach(e -> e.compute());
                });
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
