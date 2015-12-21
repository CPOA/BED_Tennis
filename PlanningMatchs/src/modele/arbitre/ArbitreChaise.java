package modele.arbitre;



import modele.arbitre.Arbitre;


public class ArbitreChaise extends Arbitre {

    public ArbitreChaise(String categorie, int nbMatchsSimples, int nbMatchsDoubles, int id, String nom, String prenom, String mail, int sexe, String nationalite) {
        super(categorie, nbMatchsSimples, nbMatchsDoubles, id, nom, prenom, mail, sexe, nationalite);
    }

    @Override
    public String type() {
        return "Arbitre de chaise";
    }
    
}