package model;

import javafx.application.Platform;

public class TemperatureGenerateur extends Thread{

    public  TemperatureGenerateur(CTemperature c) {
        while(true) {
            Platform.runLater(() -> {
                //c.setTemperature()
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }

}
