package view;

import javafx.fxml.FXML;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.capteur.CTempVirtuel;
import model.capteur.Capteur;

public class MaCellule extends TreeCell {
    @FXML
    private ImageView image;
    @FXML
    private HBox hbox;

    @FXML
    private Text text;

    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty) {
            hbox = new HBox();
            image = new ImageView();
            image.setFitWidth(30);
            image.setFitHeight(30);
            if(item instanceof CTempVirtuel) {
                image.setImage(new Image("/images/soleil.jpg"));
            } else {
                image.setImage(new Image("/images/neige.jpg"));
            }
            text = new Text(item.toString());

            hbox.getChildren().addAll(image, text);
            setGraphic(hbox);
        } else {
            setGraphic(null);
        }

    }
}
