package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Sujet {
    private List<Observateur> lesObservateurs = new ArrayList<>();

    public void add(Observateur o) {
        lesObservateurs.add(o);
    }

    public void remove(Observateur o) {
        lesObservateurs.remove(o);
    }

    public void notifier() {
        for(Observateur o : lesObservateurs) {
            o.update();
        }
    }

    public List<Observateur> getLesObservateurs() {
        return Collections.unmodifiableList(lesObservateurs);
    }
}
