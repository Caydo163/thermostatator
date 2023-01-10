package view;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import model.capteur.CTempAbstrait;

public class CTempAbstraitVue extends TreeItem<CTempAbstrait> {
    private CTempAbstrait model;

    public CTempAbstraitVue(CTempAbstrait capteur) {
        model = capteur;
    }
}
