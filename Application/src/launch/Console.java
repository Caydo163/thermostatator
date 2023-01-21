package launch;

import model.capteur.CTemperature;
import model.Visualisateur;
import view.FenetreImage;
import view.FenetreThermostat;

public class Console {
    public static void main(String[] args) {
        CTemperature capteur = new CTemperature("capteur1", 15, null);
        Visualisateur thermostat = new FenetreThermostat(capteur);
        Visualisateur image = new FenetreImage(capteur);
    }
}
