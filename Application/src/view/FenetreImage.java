package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.CTemperature;
import model.Capteur;
import model.Visualisateur;

public class FenetreImage extends Visualisateur {

    @FXML
    private ImageView image;
    private CTemperature capteur;
    public FenetreImage(CTemperature capteur) {
        super(capteur);
    }
/*
    @Override
    public void update() {
        System.out.println("j'ai changé l'image");
    }*/



    public void initialize() {
        capteur.temperatureProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //image.getImage().getUrl().endsWith()

                image.setImage(new Image("https://images.unsplash.com/photo-1525490829609-d166ddb58678?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1469&q=80"));
                if(capteur.temperatureProperty().get() < 10) {
                    image.setImage(new Image("https://images.unsplash.com/photo-1525490829609-d166ddb58678?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1469&q=80"));
                } else if (capteur.temperatureProperty().get() < 20) {

                } else {

                }
            }
        });
    }
}
