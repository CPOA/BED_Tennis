package modele;

public class Joueur extends Personne {

	private String login;
	private String motDePasse;
	private int classementATP;

        public Joueur(int id, String nom, String prenom, String mail, int sexe, String nationalite, String login, String motDePasse, int classementATP) {
            super(id, nom, prenom, mail, sexe, nationalite);
            this.login = login;
            this.motDePasse = motDePasse;
            this.classementATP = classementATP;
        }
        
        
	public void authentifier() {
		
	}

	public void setMotDePasse() {
		
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin() {
		
	}

	public int getClassementATP() {
		return this.classementATP;
	}

}