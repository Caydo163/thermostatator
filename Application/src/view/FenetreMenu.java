package view;

import data.Stub;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.capteur.*;
import model.generateur.GenerateurAlea;
import model.generateur.GenerateurIntervalle;
import model.generateur.GenerateurVariation;
import view.treeView.FabriqueCTempAbstraitVue;

import java.io.IOException;

public class FenetreMenu {
    private CTempAbstrait capteur;

    @FXML
    private Button boutonSlider;

    @FXML
    private TextField nom;

    @FXML
    private Text temperature;

    @FXML
    private Text id;
    @FXML
    private TreeView<CTempAbstrait> treeView;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private ToggleButton toggleButton;

    @FXML
    public void clicBoutonSlider() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FenetreThermostat.fxml"));
        loader.setController(new FenetreThermostat(treeView.getSelectionModel().getSelectedItem().getValue()));
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
        loader.setController(new FenetreImage(treeView.getSelectionModel().getSelectedItem().getValue()));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initOwner(((Button)e.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void clicBoutonFermer() {
        Stage stage = (Stage) nom.getScene().getWindow();
        stage.close();
    }


    private void majInfoCapteur() {
        if (capteur != null) {
            nom.textProperty().unbindBidirectional(capteur.nomProperty());
            temperature.textProperty().unbind();
            if (capteur instanceof CTemperature && ((CTemperature) capteur).getStratGen() != null) {
                toggleButton.selectedProperty().unbindBidirectional(((CTemperature) capteur).getBipper().stopProperty());
            }
        }
        capteur = treeView.getSelectionModel().getSelectedItem().getValue();
        nom.textProperty().bindBidirectional(capteur.nomProperty());
        temperature.textProperty().bind(capteur.temperatureProperty().asString());
        id.textProperty().setValue(String.valueOf(capteur.getId()));
        


        if (capteur instanceof CTemperature && ((CTemperature) capteur).getStratGen() != null) {
            toggleButton.setVisible(true);
            if(((CTemperature) capteur).getBipper().stopProperty().get()) {
                toggleButton.setText("Arrêter la génération automatique");
            } else {
                toggleButton.setText("Démarrer la génération automatique");
            }
            spinner.setVisible(true);
            toggleButton.selectedProperty().bindBidirectional(((CTemperature) capteur).getBipper().stopProperty());
        } else {
            toggleButton.setVisible(false);
            spinner.setVisible(false);
        }

        if(capteur instanceof CTempVirtuel) {

        } else {

        }
    }

    private void changementStrat(String strat) {
        var capteurRecup = treeView.getSelectionModel().getSelectedItem().getValue();
        if (capteurRecup instanceof CTemperature) {
            switch(strat) {
                case "Aléatoire":
                    ((CTemperature) capteurRecup).setStratGen(new GenerateurAlea());
                    break;
                case "Variation":
                    ((CTemperature) capteurRecup).setStratGen(new GenerateurVariation(5));
                    break;
                case "Intervalle":
                    ((CTemperature) capteurRecup).setStratGen(new GenerateurIntervalle(-10,30));
                    break;
                case "Manuel":
                    ((CTemperature) capteurRecup).setStratGen(null);
                    break;
            }
        }

    }

    public void initialize() {
        ObservableList<String> listeStratGen = FXCollections.observableArrayList();
        listeStratGen.add("Aléatoire");
        listeStratGen.add("Intervalle");
        listeStratGen.add("Variation");
        listeStratGen.add("Manuel");

        comboBox.setItems(listeStratGen);
        treeView.setCellFactory(__ -> new MaCellule());
        var root = FabriqueCTempAbstraitVue.from(Stub.loadTreeView());
        treeView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<TreeItem<CTempAbstrait>>) c -> majInfoCapteur());
        comboBox.setOnAction(actionEvent -> changementStrat(comboBox.getValue()));

        treeView.setRoot(root);
        treeView.setShowRoot(false);
        //treeView.getSelectionModel().selectFirst();
    }
}