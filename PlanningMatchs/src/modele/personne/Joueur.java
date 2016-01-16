package modele.personne;

import donnees.DaoJoueur;

public class Joueur extends Personne {

	private String m_login;
	private String m_motDePasse;
	private int m_classementATP;
        
        private int m_rangTournoi;

        public Joueur(int id, String nom, String prenom, String mail, Sexe sexe, String nationalite, String login, String motDePasse, int classementATP, int rangTournoi) {
            super(id, nom, prenom, mail, sexe, nationalite);
            this.m_login = login;
            this.m_motDePasse = motDePasse;
            this.m_classementATP = classementATP;
            this.m_rangTournoi = rangTournoi;
        }
        
        public String getMdP() {
            // la gestion de la sécurité des mots de passe n'est pas requise dans notre cas.
            return m_motDePasse;
        }

	public int setMotDePasse(String ancienMdp, String nouveauMdp) {
            if (ancienMdp.equals(this.m_motDePasse))
                throw new Error("Mot de passe incorrect");
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
        
        public int getRangTournoi() {
            return m_rangTournoi;
        }
        
        public String getStrRangTournoi() {
            String strRangTournoi = "";
            switch (m_rangTournoi) {
                case 16:
                    strRangTournoi = "16e de finale";
                    break;
                case 8:
                    strRangTournoi = "8e de finale";
                    break;
                case 4:
                    strRangTournoi = "Quart de Finale";
                    break;
                case 2:
                    strRangTournoi = "Demi-Finale";
                    break;
                case 1:
                    strRangTournoi = "Finale";
                    break;
            }
            return strRangTournoi;
        }
        
        public void setRangTournoi(int rangTournoi) {
            if (rangTournoi != 16 && rangTournoi != 8 && rangTournoi != 4 && rangTournoi != 2 && rangTournoi != 1)
                throw new Error("Rang de tournoi incorrect : " + rangTournoi);
            m_rangTournoi = rangTournoi;
        }

    @Override
    public String typePersonne() {
        return "Joueur";
    }
    
    public String getDetails() {
        return toString() + " login: " + m_login + " - mdp : " + m_motDePasse;
    }

}