

package modele.court;



public class CourtAnnexe extends Court {

    public CourtAnnexe(int id, int nom, int adresse, int capacité) {
        super(id, nom, adresse, capacité);
    }

    @Override
    public String getType() {
        return "Court Annexe";
    }
    
}