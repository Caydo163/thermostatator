package data;

import model.Bipper;
import model.capteur.*;
import model.generateur.GenerateurAlea;
import model.generateur.GenerateurIntervalle;

public class Stub {
    public static CTempAbstrait loadTreeView() {
        CTempAbstrait capteurV = new CTempVirtuel("Capteur virtuel", 5);
        CTempAbstrait capteurV2 = new CTempVirtuel("Capteur virtuel 2", 5);
        CTemperature capteur1 = new CTempAuto("Capteur 1", 5, new GenerateurAlea());
        //new Bipper((CTempAuto) capteur1, 1000).start();

        CTemperature capteur2 = new CTempManuel("Capteur 2", 5);
        CTemperature capteur3 = new CTempAuto("Capteur 3", 10, new GenerateurIntervalle(-10,30));
        CTemperature capteur4 = new CTempManuel("Capteur 4", -9);

        ((CTempVirtuel)capteurV).ajouterCapteur(capteur1,1);
        ((CTempVirtuel)capteurV).ajouterCapteur(capteur2,1);
        ((CTempVirtuel)capteurV).ajouterCapteur(capteur3,2);
        ((CTempVirtuel)capteurV).ajouterCapteur(capteurV2,2);
        ((CTempVirtuel)capteurV2).ajouterCapteur(capteur4,2);
        ((CTempVirtuel)capteurV2).ajouterCapteur(capteur1,2);
        ((CTempVirtuel)capteurV2).majTemp();
        ((CTempVirtuel)capteurV).majTemp();
        return capteurV;
    }
}
