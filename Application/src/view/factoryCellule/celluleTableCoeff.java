package view.factoryCellule;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.capteur.CTempVirtuel;

public class celluleTableCoeff extends TableCell {
    @FXML
    TextField textField;
    @Override
    protected void updateItem(Object o, boolean empty) {
        super.updateItem(o, empty);
        if(!empty) {
            textField = new TextField();
            textField.setText("fzef");
            setGraphic(textField);
        } else {
            setGraphic(null);
        }
    }
}
