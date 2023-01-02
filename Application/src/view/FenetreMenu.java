package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

public class FenetreMenu {
    //private CTemperature capteur = new CTempManuel("Capteur 1",0);
    //private CTemperature capteur = new CTempAutoIntervalle("Capteur 1", 0, 10);
    //private CTemperature capteur = new CTempAutoAlea("Capteur 1");
    private CTemperature capteur = new CTempAutoVariation("Capteur 1", 20, 10);

    @FXML
    private Button boutonSlider;
    @FXML
    public void clicBoutonSlider() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FenetreThermostat.fxml"));
        loader.setController(new FenetreThermostat(capteur));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initOwner(boutonSlider.getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void clicBoutonImage(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FenetreImage.fxml"));
        loader.setController(new FenetreImage(capteur));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initOwner(((Button)e.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        if(capteur instanceof CTempAuto) {
            new TemperatureGenerateur((CTempAuto) capteur).start();
        }
    }
}