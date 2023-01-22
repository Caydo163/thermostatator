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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.capteur.*;
import model.generateur.GenerateurAlea;
import model.generateur.GenerateurCPU;
import model.generateur.GenerateurIntervalle;
import model.generateur.GenerateurVariation;
import model.util.Bipper;
import view.factoryCellule.CelluleTablePoid;
import view.factoryCellule.celluleTableId;
import view.factoryCellule.CelluleTableType;
import view.factoryCellule.CelluleTreeView;
import view.treeView.FabriqueCTempAbstraitVue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private HBox hbox_tick;

    @FXML
    private HBox hbox_strategie;
    @FXML
    private TableView<ItemTableView> tableView;
    @FXML
    private TableColumn<ItemTableView,String> tableId;

    @FXML
    private TableColumn<ItemTableView, Integer> tablePoid;

    @FXML
    private TableColumn<ItemTableView, String> tableType;

    //private ObservableList<ObservableList> listeTableView = FXCollections.observableArrayList();

    private ObservableList<ItemTableView> listeTableView;



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
            if (capteur instanceof CTemperature) {
                toggleButton.selectedProperty().unbindBidirectional(((CTemperature) capteur).activateProperty());
                spinner.getValueFactory().valueProperty().unbindBidirectional(((CTemperature) capteur).tickProperty().asObject());
            }
        }
        capteur = treeView.getSelectionModel().getSelectedItem().getValue();
        nom.textProperty().bindBidirectional(capteur.nomProperty());
        temperature.textProperty().bind(capteur.temperatureProperty().asString());
        id.textProperty().setValue(String.valueOf(capteur.getId()));


        if(capteur instanceof CTemperature) {
            hbox_strategie.setVisible(true);
            if(((CTemperature) capteur).getStratGen() != null) {
                spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60,1));
                spinner.getValueFactory().valueProperty().bindBidirectional(((CTemperature) capteur).tickProperty().asObject());
                toggleButton.selectedProperty().bindBidirectional(((CTemperature) capteur).activateProperty());
                hbox_tick.setVisible(true);
                toggleButton.setVisible(true);
            } else {
                hbox_tick.setVisible(false);
                toggleButton.setVisible(false);
            }
        }
        else {
            hbox_strategie.setVisible(false);
            hbox_tick.setVisible(false);
            toggleButton.setVisible(false);
        }

        if(capteur instanceof CTempVirtuel) {
            listeTableView = ((CTempVirtuel) capteur).getListItem();
            tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tablePoid.setCellValueFactory(new PropertyValueFactory<>("poid"));
            tableType.setCellValueFactory(new PropertyValueFactory<>("icon"));
            tableView.setItems(listeTableView);
            tableView.setVisible(true);
        } else {
            var strat = (((CTemperature) capteur).getStratGen() != null) ? ((CTemperature) capteur).getStratGen().toString() : "Manuel";
            comboBox.getSelectionModel().select(strat);
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
                case "CPU":
                    ((CTemperature) capteurRecup).setStratGen(new GenerateurCPU());
                    break;
                case "Manuel":
                    ((CTemperature) capteurRecup).setStratGen(null);
                    break;
            }
            majInfoCapteur();
        }

    }

    public void initialize() {
        ObservableList<String> listeStratGen = FXCollections.observableArrayList();
        listeStratGen.add("Aléatoire");
        listeStratGen.add("Intervalle");
        listeStratGen.add("Variation");
        listeStratGen.add("CPU");
        listeStratGen.add("Manuel");

        comboBox.setItems(listeStratGen);
        treeView.setCellFactory(__ -> new CelluleTreeView());


        //tableId.setCellFactory(__ -> new celluleTableId());
        tableType.setCellFactory(__ -> new CelluleTableType());
        tablePoid.setCellFactory(__ -> new CelluleTablePoid());
        var bipper = new Bipper();
        var root = FabriqueCTempAbstraitVue.from(Stub.loadTreeView(bipper));
        treeView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<TreeItem<CTempAbstrait>>) c -> majInfoCapteur());
        comboBox.setOnAction(actionEvent -> changementStrat(comboBox.getValue()));
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60,1));
        treeView.setRoot(root);
        treeView.setShowRoot(false);
        toggleButton.setText("Activer / Désactiver");
        //bipper.start();
        //treeView.getSelectionModel().selectFirst();
    }
}