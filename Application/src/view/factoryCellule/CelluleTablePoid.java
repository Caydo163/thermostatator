package view.factoryCellule;

import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import view.ItemTableView;

public class CelluleTablePoid extends TableCell<ItemTableView, Integer> {
    @FXML
    TextField textField;
    @Override
    protected void updateItem(Integer o, boolean empty) {
        super.updateItem(o, empty);
        if(!empty) {
            textField = new TextField();
            textField.setText(String.valueOf(o));
            setGraphic(textField);
        } else {
            setGraphic(null);
        }
    }
}
