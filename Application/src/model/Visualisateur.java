package model;

import model.capteur.CTempAbstrait;

public abstract class Visualisateur  {
    protected CTempAbstrait capteur;

    public Visualisateur(CTempAbstrait capteur) {
        this.capteur = capteur;
    }


}
