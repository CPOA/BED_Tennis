package modele.arbitre;



import modele.personne.Sexe;
import modele.arbitre.Arbitre;


public class ArbitreChaise extends Arbitre {

    public ArbitreChaise(int id, String nom, String prenom, String mail, Sexe sexe, String nationalite, int nbMatchsSimples, int nbMatchsDoubles) {
        super(TypeArbitre.ARBITRE_CHAISE, id, nom, prenom, mail, sexe, nationalite, nbMatchsSimples, nbMatchsDoubles);
    }

    @Override
    public String typePersonne() {
        return "Arbitre de chaise";
    }
    
}