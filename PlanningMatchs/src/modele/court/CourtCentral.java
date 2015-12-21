package modele.court;

public class CourtCentral extends Court {

    public CourtCentral(int id, int nom, int adresse, int capacité) {
        super(id, nom, adresse, capacité);
    }

    @Override
    public String getType() {
        return "Court Central";
    }
}