package launch;

import model.CTempManuel;
import model.CTemperature;
import model.Capteur;
import model.Visualisateur;
import view.FenetreImage;
import view.FenetreThermostat;

public class Console {
    public static void main(String[] args) {
        CTemperature capteur = new CTempManuel("capteur1", 15);
        Visualisateur thermostat = new FenetreThermostat(capteur);
        Visualisateur image = new FenetreImage(capteur);
    }
}
