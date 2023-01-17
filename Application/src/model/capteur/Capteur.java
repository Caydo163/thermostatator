package model.capteur;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Capteur {
    private static int idEnCours = 0;
    private int id;

    public Capteur(String nom) {
        this.nom.set(nom);
        idEnCours ++;
        id = idEnCours;
    };

    private StringProperty nom = new SimpleStringProperty();
    public StringProperty nomProperty() { return nom; }
    public String getNom() { return nom.get(); }
    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public int getId() {
        return id;
    }
}
