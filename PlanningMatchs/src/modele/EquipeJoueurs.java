
package modele;

import modele.personne.Joueur;



public class EquipeJoueurs {

    
        private static int dernierIdDonne = 0;
	private int m_id;
	private int m_nbJoueurs;

        private Joueur m_joueurA;
        private Joueur m_joueurB;
        
        public EquipeJoueurs(Joueur joueurA) {
            m_joueurA = joueurA;
        }
        
	public int getId() {
            return m_id;
	}

	public boolean ajouterJoueur(Joueur j) {
            if (m_joueurA == null) {
                m_joueurA = j;
                return true;
            }
            else if (m_joueurB == null) {
                if (j.getNationalite() != m_joueurB.getNationalite()) 
                    return false;
                m_joueurB = j;
                return true;
            }
            else {
                // il y a déjà deux joueurs
                return false;
            }
            
	}
        
        public Joueur getJoueurA() {
            return m_joueurA;
        }
        
        public Joueur getJoueurB() {
            return m_joueurB;
        }

	public int getNbJoueurs() {
            return m_nbJoueurs;
	}
        
        public String getNationalite() {
            // joueur 1 et 2 ont nécessairement la même nationalité, voir ajouterJoueur()
            return m_joueurA.getNationalite();
        }
        
	public boolean estComplete() {
            return (m_nbJoueurs == 2);
	}
        
        @Override
        public String toString() {
            String txt = "";
            if (m_joueurA != null) {
                txt += m_joueurA.toString();
                if (m_joueurB != null) {
                    txt += " & ";
                    txt += m_joueurB.toString();
                }
            }
            return txt;
        }

}