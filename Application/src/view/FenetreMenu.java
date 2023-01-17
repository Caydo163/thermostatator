package view;

import data.Stub;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.capteur.*;

import java.io.IOException;

public class FenetreMenu {
    private CTempAbstrait capteur;

    @FXML
    private Button boutonSlider;

    @FXML
    private Text nom;

    @FXML
    private Text temperature;

    @FXML
    private Text id;
    @FXML
    private TreeView<CTempAbstrait> treeView;

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
        nom.textProperty().setValue(treeView.getSelectionModel().getSelectedItem().getValue().getNom());
        temperature.textProperty().setValue(String.valueOf(treeView.getSelectionModel().getSelectedItem().getValue().getTemperature()));
        id.textProperty().setValue(String.valueOf(treeView.getSelectionModel().getSelectedItem().getValue().getId()));
    }


    public void initialize() {
        treeView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<TreeItem<CTempAbstrait>>) c -> majInfoCapteur());
        capteur = Stub.loadTreeView();
        treeView.setCellFactory(__ -> new MaCellule());
        var root = FabriqueCTempAbstraitVue.from(capteur);
        treeView.setRoot(root);
    }
}