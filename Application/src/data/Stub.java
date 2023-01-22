package data;

import model.capteur.*;
import model.generateur.GenerateurAlea;
import model.generateur.GenerateurIntervalle;
import model.util.Bipper;

public class Stub {
    public static CTempAbstrait loadTreeView(Bipper bipper) {
        CTempAbstrait racine = new CTempVirtuel("Capteur virtuel", 5);
        CTempAbstrait capteurV = new CTempVirtuel("Capteur virtuel", 5);
        CTempAbstrait capteurV2 = new CTempVirtuel("Capteur virtuel 2", 5);
        CTemperature capteur1 = new CTemperature("Capteur 1", 5, new GenerateurAlea(), bipper);

        CTemperature capteur2 = new CTemperature("Capteur 2", 5, null,bipper);
        CTemperature capteur3 = new CTemperature("Capteur 3", 10, new GenerateurIntervalle(-10,30),bipper);
        CTemperature capteur4 = new CTemperature("Capteur 4", -9, null,bipper);
        CTemperature capteur5 = new CTemperature("Capteur 5", 65, null,bipper);

        ((CTempVirtuel)capteurV).ajouterCapteur(capteur1,1);
        ((CTempVirtuel)capteurV).ajouterCapteur(capteur2,1);
        ((CTempVirtuel)capteurV).ajouterCapteur(capteur3,2);
        ((CTempVirtuel)capteurV).ajouterCapteur(capteurV2,2);
        ((CTempVirtuel)capteurV2).ajouterCapteur(capteur4,2);
        ((CTempVirtuel)capteurV2).ajouterCapteur(capteur1,2);
        ((CTempVirtuel)capteurV2).majTemp();
        ((CTempVirtuel)capteurV).majTemp();
        ((CTempVirtuel)racine).ajouterCapteur(capteurV,2);
        ((CTempVirtuel)racine).ajouterCapteur(capteurV2,2);
        ((CTempVirtuel)racine).ajouterCapteur(capteur5,2);
        return racine;
    }
}
