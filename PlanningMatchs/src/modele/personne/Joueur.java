package modele.personne;

public class Joueur extends Personne {

	private String m_login;
	private String m_motDePasse;
	private int m_classementATP;

        public Joueur(int id, String nom, String prenom, String mail, Sexe sexe, String nationalite, String login, String motDePasse, int classementATP) {
            super(id, nom, prenom, mail, sexe, nationalite);
            this.m_login = login;
            this.m_motDePasse = motDePasse;
            this.m_classementATP = classementATP;
        }
        
        public String getMdP() {
            // la gestion de la sécurité des mots de passe n'est pas requise dans notre cas.
            return m_motDePasse;
        }

	public int setMotDePasse(String ancienMdp, String nouveauMdp) {
            if (ancienMdp.equals(this.m_motDePasse))
                return -1;
            m_motDePasse = nouveauMdp;
            return 0;
	}

	public String getLogin() {
		return this.m_login;
	}

	public void setLogin(String login) {
            m_login = login;
	}

	public int getClassementATP() {
            return this.m_classementATP;
	}

    @Override
    public String typePersonne() {
        return "Joueur";
    }
    
    public String getDetails() {
        return toString() + " login: " + m_login + " - mdp : " + m_motDePasse;
    }

}