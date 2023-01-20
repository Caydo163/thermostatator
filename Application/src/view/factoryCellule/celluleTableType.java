package view.factoryCellule;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.capteur.CTempAbstrait;
import model.capteur.CTempVirtuel;

public class celluleTableType extends TableCell {
    @FXML
    ImageView image;
    @Override
    protected void updateItem(Object o, boolean empty) {
        super.updateItem(o, empty);
        if(!empty) {
            image = new ImageView();
            image.setFitWidth(30);
            image.setFitHeight(30);
            if(o instanceof CTempVirtuel) {
                image.setImage(new Image("/images/multi_captor_icon.png"));
            } else {
                image.setImage(new Image("/images/captor_icon.png"));
            }
            setGraphic(image);
        } else {
            setGraphic(null);
        }
    }
}
