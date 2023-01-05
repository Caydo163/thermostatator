package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.capteur.CTempAbstrait;
import model.Visualisateur;
import model.capteur.CapteurVirtuel;

public class FenetreThermostat extends Visualisateur {

    @FXML
    private Text valTemperature;

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
        if(capteur instanceof CapteurVirtuel) {
            //listView.itemsProperty().bind();
        } else {
            slider.valueProperty().bindBidirectional(capteur.temperatureProperty());
            valTemperature.textProperty().bind(capteur.temperatureProperty().asString());
        }
    }

/*
    @Override
    public void update() {
        System.out.println("J'ai changé");
    }*/
}
