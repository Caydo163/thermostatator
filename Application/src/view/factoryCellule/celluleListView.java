package view.factoryCellule;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.capteur.CTempAbstrait;
import model.capteur.CTempVirtuel;

public class celluleListView extends TreeCell {
    @FXML
    private ImageView icone;
    @FXML
    private HBox hbox;

    @FXML
    private Text id = new Text();

    @FXML
    private TextField coeff = new TextField();

    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty) {
            hbox = new HBox();
            icone = new ImageView();
            icone.setFitWidth(30);
            icone.setFitHeight(30);
            if(item instanceof CTempVirtuel) {
                icone.setImage(new Image("/images/multi_captor_icon.png"));
            } else {
                icone.setImage(new Image("/images/captor_icon.png"));
            }

            id.setText(String.valueOf(((CTempAbstrait)item).getId()));
            //coeff.textProperty().bind(((CTempAbstrait)item));
            //nomC.setUserData(item);
            hbox.getChildren().addAll(icone, coeff, id);
            setGraphic(hbox);
        } else {
            //valTemp.textProperty().unbind();
            setGraphic(null);
        }

    }
}
