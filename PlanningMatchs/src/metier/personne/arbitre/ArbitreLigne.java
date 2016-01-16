package metier.personne.arbitre;


import metier.personne.Sexe;
import metier.personne.arbitre.Arbitre;

public class ArbitreLigne extends Arbitre {

    public ArbitreLigne(int id, String nom, String prenom, String mail, Sexe sexe, String nationalite, int nbMatchsSimples, int nbMatchsDoubles) {
        super(TypeArbitre.ARBITRE_LIGNE, id, nom, prenom, mail, sexe, nationalite, nbMatchsSimples, nbMatchsDoubles);
    }

    @Override
    public String typePersonne() {
        return "Arbitre de ligne";
    }
    
}