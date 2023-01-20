package view.treeView;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import model.capteur.CTempAbstrait;

public class CTempAutoVue extends CTempAbstraitVue {
    public CTempAutoVue(CTempAbstrait capteur) {
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
