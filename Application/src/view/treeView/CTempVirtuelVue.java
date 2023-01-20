package view.treeView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import model.capteur.CTempAbstrait;

public class CTempVirtuelVue extends CTempAbstraitVue {

    private ObservableList<TreeItem<CTempAbstrait>> lesCapteurs = FXCollections.observableArrayList();

    public CTempVirtuelVue(CTempAbstrait capteur) {
        super(capteur);
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public ObservableList<TreeItem<CTempAbstrait>> getChildren() {
        System.out.println(lesCapteurs.stream().count());
        return lesCapteurs;
    }
}
