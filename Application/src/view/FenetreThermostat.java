package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.capteur.CTempAbstrait;
import model.Visualisateur;
import model.capteur.CTempVirtuel;

public class FenetreThermostat extends Visualisateur {

    @FXML
    private Text valTemperature;

    @FXML
    private Text nomCapteur;

    @FXML
    private Slider slider;

    @FXML
    private ListView<CTempAbstrait> listView;

    //private CTemperature capteur;

    public FenetreThermostat(CTempAbstrait capteur) {
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
        valTemperature.textProperty().bind(capteur.temperatureProperty().asString());
        nomCapteur.textProperty().bind(capteur.nomProperty());
    }

/*
    @Override
    public void update() {
        System.out.println("J'ai changé");
    }*/
}
