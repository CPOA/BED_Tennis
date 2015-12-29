
package modele;

import modele.personne.Joueur;



public class EquipeJoueurs {

    
        private static int dernierIdDonne = 0;
	private int m_id;
	private int m_nbJoueurs;

        private Joueur m_joueur1;
        private Joueur m_joueur2;
        
	public int getId() {
            return m_id;
	}

	public boolean ajouterJoueur(Joueur j) {
            if (m_joueur1 == null) {
                m_joueur1 = j;
                return true;
            }
            else if (m_joueur2 == null) {
                if (j.getNationalite() != m_joueur1.getNationalite())
                    return false;
                m_joueur2 = j;
                return true;
            }
            else {
                // il y a déjà deux joueurs
                return false;
            }
            
	}

	public int getNbJoueurs() {
            return m_nbJoueurs;
	}
        
        public String getNationalite() {
            // joueur 1 et 2 ont nécessairement la même nationalité, voir ajouterJoueur()
            return m_joueur1.getNationalite();
        }
        
	public boolean estComplete() {
            return (m_nbJoueurs == 2);
	}

}