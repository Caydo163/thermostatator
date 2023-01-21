package view.factoryCellule;

import javafx.fxml.FXML;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.capteur.CTempAbstrait;
import model.capteur.CTempVirtuel;

public class CelluleTreeView extends TreeCell {
    @FXML
    private ImageView image;
    @FXML
    private HBox hbox;
    @FXML
    private VBox vbox;

    @FXML
    private Text nomC = new Text();

    @FXML
    private Text valTemp = new Text();

    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty) {
            hbox = new HBox();
            vbox = new VBox();
            image = new ImageView();
            image.setFitWidth(30);
            image.setFitHeight(30);
            if(item instanceof CTempVirtuel) {
                image.setImage(new Image("/images/multi_captor_icon.png"));
            } else {
                image.setImage(new Image("/images/captor_icon.png"));
            }

            nomC.textProperty().bind(((CTempAbstrait)item).nomProperty());
            valTemp.textProperty().bind(((CTempAbstrait)item).temperatureProperty().asString());
            //nomC.setUserData(item);
            vbox.getChildren().addAll(nomC, valTemp);
            hbox.getChildren().addAll(image, vbox);
            setGraphic(hbox);
        } else {
            nomC.textProperty().unbind();
            valTemp.textProperty().unbind();
            setGraphic(null);
        }

    }
}
