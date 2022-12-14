package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CTemperature;
import model.Capteur;
import java.io.IOException;

public class FenetreMenu {
    private CTemperature capteur = new CTemperature("Capteur 1",15);

    @FXML
    public void boutonThermostat() throws IOException {
        Stage stage = new Stage();
        FenetreThermostat ft = new FenetreThermostat(capteur);
        FXMLLoader loader = new FXMLLoader();

        loader.setController(ft);

        loader.setRoot(getClass().getResource("/FenetreThermostat.fxml"));
        loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("/FenetreThermostat.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void boutonImage() {
    }
}
/*
instancier code behind
instancier loader
fournir instance code behind au fxml
puis appeler load sur FXMLLoader
 */