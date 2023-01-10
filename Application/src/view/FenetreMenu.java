package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import model.*;
import model.capteur.*;

import java.io.IOException;

public class FenetreMenu {
    //private CTemperature capteur = new CTempManuel("Capteur 1",0);
    //private CTempAbstrait capteur = new CTempAuto("Capteur 3", 5, new GenerateurAlea());
    //private CTempAbstrait capteur = new CTempAuto("Capteur 2", 5, new GenerateurIntervalle(15, 20));
    //private CTempAbstrait capteur = new CTempAuto("Capteur 1", 5, new GenerateurVariation(5));
    private CTempAbstrait capteurV = new CTempVirtuel("Capteur virtuel", 5);
    private CTempAbstrait capteur;

    @FXML
    private Button boutonSlider;

    @FXML
    private TreeView<CTempAbstrait> treeView;
    private CTempAbstrait arbre;

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
        CTemperature capteur1 = new CTempManuel("Capteur 1", 5);
        CTemperature capteur2 = new CTempManuel("Capteur 2", 5);
        CTemperature capteur3 = new CTempManuel("Capteur 3", 10);
        CTemperature capteur4 = new CTempManuel("Capteur 4", -9);
        this.capteur = capteur1;
        ((CTempVirtuel)capteurV).ajouterCapteur(capteur1,1);
        ((CTempVirtuel)capteurV).ajouterCapteur(capteur2,1);
        ((CTempVirtuel)capteurV).ajouterCapteur(capteur3,2);
        ((CTempVirtuel)capteurV).majTemp();
        if(capteurV instanceof CTempAuto) {
            new Bipper((CTempAuto) capteurV).start();
        }

        arbre = capteurV;
        try {
            ((CTempVirtuel)capteurV).getLesCapteurs().forEach(System.out::println);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        var root = FabriqueCTempAbstraitVue.from(arbre);
        treeView.setRoot(root);
    }
}