package view;

import data.Stub;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.capteur.*;
import model.generateur.GenerateurAlea;
import model.generateur.GenerateurIntervalle;
import model.generateur.GenerateurVariation;
import view.factoryCellule.celluleTableCoeff;
import view.factoryCellule.celluleTableType;
import view.factoryCellule.celluleTreeView;
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
    private TableView<CTempAbstrait> tableView;
    @FXML
    private TableColumn<CTempAbstrait, Integer> tableId;

    @FXML
    private TableColumn<CTempAbstrait, Integer> tableCoeff;

    @FXML
    private TableColumn<CTempAbstrait, String> tableType;


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
            spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60,1));
            spinner.getValueFactory().valueProperty().bindBidirectional(((CTemperature) capteur).getBipper().tickProperty().asObject());
            toggleButton.selectedProperty().bindBidirectional(((CTemperature) capteur).getBipper().stopProperty());
        } else {
            toggleButton.setVisible(false);
            spinner.setVisible(false);
        }

        if(capteur instanceof CTempVirtuel) {
            //tableView.setItems(((CTempVirtuel)capteur).getCapteurs());
            tableView.setVisible(true);
        } else {
            tableView.setVisible(false);
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
        treeView.setCellFactory(__ -> new celluleTreeView());


        tableType.setCellFactory(__ -> new celluleTableType());
        tableCoeff.setCellFactory(__ -> new celluleTableCoeff());
        var root = FabriqueCTempAbstraitVue.from(Stub.loadTreeView());
        treeView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<TreeItem<CTempAbstrait>>) c -> majInfoCapteur());
        comboBox.setOnAction(actionEvent -> changementStrat(comboBox.getValue()));

        treeView.setRoot(root);
        treeView.setShowRoot(false);
        //treeView.getSelectionModel().selectFirst();
    }
}