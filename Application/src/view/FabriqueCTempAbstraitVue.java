package view;

import javafx.collections.ListChangeListener;
import model.capteur.CTempAbstrait;
import model.capteur.CTempAuto;
import model.capteur.CTempVirtuel;

public class FabriqueCTempAbstraitVue {
    public static CTempAbstraitVue from(CTempAbstrait root) {
        CTempAbstraitVue retour;
        if(root instanceof CTempVirtuel) {
            retour = new CTempVirtuelVue(root);
        } else { if(root instanceof CTempAuto) {
            retour = new CTempAutoVue(root);
        } else {
            retour = new CTempAbstraitVue(root);
        }}

        chuter(root, retour);

        try {
            root.getLesCapteurs().addListener(new ListChangeListener<CTempAbstrait>() {
                @Override
                public void onChanged(Change<? extends CTempAbstrait> c) {
                    while(c.next()) {
                        c.getAddedSubList().forEach(o -> {
                            retour.getChildren().add(FabriqueCTempAbstraitVue.from(o));
                        });
                    }
                    chuter(root, retour);
                }
            });
        } catch (NoSuchMethodException e) {}

        return retour;
    }

    public static void chuter(CTempAbstrait root, CTempAbstraitVue retour) {
        try {
            root.getLesCapteurs().forEach(e -> {
                retour.getChildren().add(FabriqueCTempAbstraitVue.from(e));
            });
        } catch (NoSuchMethodException e) {}
    }
}
