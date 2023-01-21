package view.treeView;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import model.capteur.CTempAbstrait;

public class CTemperatureVue extends CTempAbstraitVue {
    public CTemperatureVue(CTempAbstrait capteur) {
        super(capteur);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public ObservableList<TreeItem<CTempAbstrait>> getChildren() {
        throw new RuntimeException();
    }
}
