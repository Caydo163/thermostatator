package model.capteur;

public abstract class Capteur {
    private static int idEnCours = 0;
    private int id;
    private String nom;

    public Capteur(String nom) {
        this.nom = nom;
        idEnCours ++;
        id = idEnCours;
    };

}
