package view.factoryCellule;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.capteur.Capteur;

public class celluleTableId extends TableCell {
    @FXML
    Text text;
    @Override
    protected void updateItem(Object o, boolean empty) {
        super.updateItem(o, empty);
        if(!empty) {
            text = new Text();
            text.setText(String.valueOf(o));
            setGraphic(text);
        } else {
            setGraphic(null);
        }
    }
}
