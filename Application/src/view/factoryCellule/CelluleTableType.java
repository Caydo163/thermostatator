package view.factoryCellule;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.capteur.CTempVirtuel;
import view.ItemTableView;

public class CelluleTableType extends TableCell<ItemTableView, String> {
    @FXML
    ImageView image;
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty && item != null) {
            image = new ImageView();
            image.setFitWidth(30);
            image.setFitHeight(30);
            image.setImage(new Image("/images/"+item));

            setGraphic(image);
        } else {
            setGraphic(null);
        }
    }
}
