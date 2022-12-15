package model;

public abstract class Visualisateur  {
    protected CTemperature capteur;

    public Visualisateur(CTemperature capteur) {
        this.capteur = capteur;
        //this.capteur.add(this);
    }


}
