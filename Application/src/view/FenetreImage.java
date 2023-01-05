package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.capteur.CTempAbstrait;
import model.Visualisateur;

public class FenetreImage extends Visualisateur {

    @FXML
    private ImageView image;

    @FXML
    private Button bouton;
    public FenetreImage(CTempAbstrait capteur) {
        super(capteur);
    }

    @FXML
    public void boutonFermer() {
        Stage stage = (Stage) image.getScene().getWindow();
        stage.close();
    }
/*
    @Override
    public void update() {
        System.out.println("j'ai changé l'image");
    }*/


    private static final int NUAGE = 25;
    private static final int NEIGE = 0;

    private void changeImage() {
        String file;
        if(capteur.temperatureProperty().get() <= NEIGE) {
            file = "neige";
        } else if (capteur.temperatureProperty().get() <= NUAGE) {
            file = "nuage";
        } else {
            file = "soleil";
        }
        if(image.getImage() == null || !image.getImage().getUrl().endsWith(file+".jpg")) {
            image.setImage(new Image("/images/"+file+".jpg"));
        }

    }

    public void initialize() {
        changeImage();
        capteur.temperatureProperty().addListener((__, ___, newValue) -> changeImage());
    }
}
