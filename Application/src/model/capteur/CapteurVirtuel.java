package model.capteur;

import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CapteurVirtuel extends CTempAbstrait {
    public CapteurVirtuel(String nom, double temperature) {
        super(nom, temperature);
    }

    private Map<Integer, List<CTempAbstrait>> lesCapteurs = new HashMap<>();




    public List<CTempAbstrait> getCapteurs() {
        List<CTempAbstrait> list = new ArrayList<>();
        for (Map.Entry<Integer, List<CTempAbstrait>> val : lesCapteurs.entrySet()) {
            val.getValue().forEach(list::add);
        }
        return list;
    }

    public void ajouterCapteur(CTempAbstrait capteur, Integer coeff) {
        if (lesCapteurs.containsKey(coeff)) {
            lesCapteurs.get(coeff).add(capteur);
        } else {
            lesCapteurs.put(coeff, new ArrayList<>());
            lesCapteurs.get(coeff).add(capteur);
        }
        capteur.temperatureProperty().addListener((__, ___, newValue) -> majTemp());
    }

    public void supprimerCapteur(CTempAbstrait capteur) {
        for (Map.Entry<Integer, List<CTempAbstrait>> val : lesCapteurs.entrySet()) {
            if(val.getValue().contains(capteur)) {
                val.getValue().remove(capteur);
            }
        }
    }

    @Override
    public double getTemperature() {return temperatureProperty().getValue();}

    public void majTemp() {temperatureProperty().setValue(calculMoyenne());}

    private double calculMoyenne() {
        double sommeT = 0;
        double sommeC = 0;
        int nb = 0;
        for (Map.Entry<Integer, List<CTempAbstrait>> val : lesCapteurs.entrySet()) {
            for (CTempAbstrait c : val.getValue()) {
                sommeT += c.temperatureProperty().getValue() * val.getKey();
                sommeC += val.getKey();
            }
        }
        return sommeT/sommeC;
    }

}
