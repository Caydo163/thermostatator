package model;

import javafx.application.Platform;
import model.capteur.CTempAuto;

public class Bipper extends Thread{

    private CTempAuto capteur;
    public Bipper(CTempAuto capteur) {
        this.capteur = capteur;
    }
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                Platform.runLater(() -> {
                    capteur.compute();
                });
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
