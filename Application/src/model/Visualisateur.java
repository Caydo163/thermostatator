package model;

public abstract class Visualisateur  {
    private Capteur capteur;

    public Visualisateur(CTemperature capteur) {
        this.capteur = capteur;
        //this.capteur.add(this);
    }


}
