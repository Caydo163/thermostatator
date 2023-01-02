package model;

import javafx.application.Platform;
import launch.Console;

import java.util.Random;

public class TemperatureGenerateur extends Thread{

    private CTempAuto capteur;

    public TemperatureGenerateur(CTempAuto capteur) {
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
