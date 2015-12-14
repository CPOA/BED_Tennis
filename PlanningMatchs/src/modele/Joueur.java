package modele;

public class Joueur extends Personne {

	private int login;
	private int motDePasse;
	private int classementATP;

        public Joueur(int id, String nom, String prenom, String mail, int sexe, String nationalite, int login, int motDePasse, int classementATP) {
            super(id, nom, prenom, mail, sexe, nationalite);
            this.login = login;
            this.motDePasse = motDePasse;
            this.classementATP = classementATP;
        }
        
        
	public void authentifier() {
		
	}

	public void setMotDePasse() {
		
	}

	public void getLogin() {
		
	}

	public void setLogin() {
		
	}

	public void getClassementATP() {
		
	}

}