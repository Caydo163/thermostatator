package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CTemperature;
import model.Capteur;
import model.Visualisateur;

public class FenetreThermostat extends Visualisateur {

    @FXML
    private Text nomCapteur;

    @FXML
    private Slider slider;

    //private CTemperature capteur;

    public FenetreThermostat(CTemperature capteur) {
        super(capteur);
    }

    @FXML
    public void onChange() {

    }

    @FXML
    public void boutonFermer() {
        Stage stage = (Stage) slider.getScene().getWindow();
        stage.close();
    }

    public void initialize() {
        slider.valueProperty().bindBidirectional(capteur.temperatureProperty());
    }

/*
    @Override
    public void update() {
        System.out.println("J'ai changé");
    }*/
}
