package modele;


public class Ramasseur extends Personne {

    public Ramasseur(int id, String nom, String prenom, String mail, int sexe, String nationalite) {
        super(id, nom, prenom, mail, sexe, nationalite);
    }

    @Override
    public String type() {
        return "Ramasseur de balle";
    }

    
}