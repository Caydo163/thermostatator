package view.treeView;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import model.capteur.CTempAbstrait;

public class CTempManuelVue extends CTempAbstraitVue {
    public CTempManuelVue(CTempAbstrait capteur) {
        super(capteur);
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public ObservableList<TreeItem<CTempAbstrait>> getChildren() {
        throw new RuntimeException();
    }
}
