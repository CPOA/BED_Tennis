package modele.personne;


public class Ramasseur extends Personne {

    public Ramasseur(int id, String nom, String prenom, String mail, Sexe sexe, String nationalite) {
        super(id, nom, prenom, mail, sexe, nationalite);
    }
    
    
    
    @Override
    public String typePersonne() {
        return "Ramasseur de balle";
    }

    
}