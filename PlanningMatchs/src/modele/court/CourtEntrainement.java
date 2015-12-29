package modele.court;

public class CourtEntrainement extends Court {

    public CourtEntrainement(int id, String nom, String adresse, int capacité) {
        super(id, nom, adresse, capacité);
    }

    @Override
    public String getType() {
        return "Court d'Entraînement";
    }
}