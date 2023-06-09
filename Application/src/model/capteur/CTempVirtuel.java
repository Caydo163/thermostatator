package model.capteur;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.ItemTableView;


public class CTempVirtuel extends CTempAbstrait {
    public CTempVirtuel(String nom, double temperature) {
        super(nom, temperature);
    }

    //private Map<Integer, List<CTempAbstrait>> lesCapteurs = new HashMap<>();

    private ObservableList<Integer> listeCoeffObs = FXCollections.observableArrayList();
    public ListProperty<Integer> listeCoeff = new SimpleListProperty<>(listeCoeffObs);

    private ObservableList<CTempAbstrait> listeCapteursObs = FXCollections.observableArrayList();
    public ListProperty<CTempAbstrait> listeCapteurs = new SimpleListProperty<>(listeCapteursObs);

    public ObservableList<CTempAbstrait> getListeCapteurs() {
        return listeCapteurs.get();
    }

    public ObservableList<Integer> getListeCoeff() {
        return listeCoeff.get();
    }

    public ObservableList<ItemTableView> getListItem() {
        ObservableList<ItemTableView> liste = FXCollections.observableArrayList();
        for(CTempAbstrait c : listeCapteurs) {
            String icon = (c instanceof CTempVirtuel) ? "multi_captor_icon.png" : "captor_icon.png";
            int coeff = listeCoeff.get(listeCapteurs.indexOf(c));
            liste.add(new ItemTableView(c.getId(), icon, coeff));
        }
        return liste;
    }


    public void ajouterCapteur(CTempAbstrait capteur, Integer coeff) {
        listeCapteursObs.add(capteur);
        listeCoeffObs.add(coeff);
        capteur.temperatureProperty().addListener((__, ___, newValue) -> majTemp());
        majTemp();
    }

    public void supprimerCapteur(CTempAbstrait capteur) {
        listeCoeffObs.remove(listeCapteursObs.indexOf(capteur));
        listeCapteursObs.remove(capteur);
        majTemp();
    }

    @Override
    public double getTemperature() {return temperatureProperty().getValue();}

    public void majTemp() {temperatureProperty().setValue(calculMoyenne());}

    private double calculMoyenne() {
        double sommeT = 0;
        double sommeC = 0;
        for(CTempAbstrait capteur : listeCapteursObs) {
            var coeff = listeCoeffObs.get(listeCapteursObs.indexOf(capteur));
            sommeT += capteur.temperatureProperty().getValue() * coeff;
            sommeC += coeff;
        }
        return sommeT/sommeC;
    }


    @Override
    public ObservableList<CTempAbstrait> getLesCapteurs() throws NoSuchMethodException {
        return getListeCapteurs();
    }


}
