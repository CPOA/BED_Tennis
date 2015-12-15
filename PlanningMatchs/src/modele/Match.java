package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modele.arbitre.Arbitre;
import modele.arbitre.ArbitreChaise;
import modele.arbitre.ArbitreFilet;
import modele.arbitre.ArbitreLigne;
import modele.court.Court;



public class Match {
        
        private static int dernierIdDonne = 0;
    
	private int m_idMatch;
        
	private List<modele.Set> m_sets;
        
        private Court m_court;
        
        private Creneau m_creneau;
        
	private String m_type;
        
	private Joueur m_gagnant;
	private Joueur m_perdant;
        
	private boolean m_fini;
        
        private Joueur m_joueur1;
        private Joueur m_joueur2;
        
        
        private EquipeJoueurs m_equipe1;
        private EquipeJoueurs m_equipe2;
       
        private ArbitreChaise m_arbitreChaise;
        private ArbitreFilet m_arbitreFilet;
        private Map<Integer, ArbitreLigne> m_arbitresLigne;
        
        private EquipeRamasseurs m_equipeRamasseurs;
        
        public Match() {
            dernierIdDonne++;
            m_idMatch = dernierIdDonne;
            
            m_sets = new ArrayList();
            
            m_arbitresLigne = new HashMap<>();
        }
        
	public int setJoueur1(Joueur j1) {
            if (m_joueur2.getId() == j1.getId()) {
                return -1;
            }
            m_joueur1 = j1;
            return 0;
        }
        
        public int setJoueur2(Joueur j2) {
            if (m_joueur1.getId() == j2.getId())
                return -1;
            m_joueur2 = j2;
            return 0;
        }
        
        /**
         * Méthode servant à ajouter les arbitres au match.
         * La méthode gère d'elle-même les différents types d'arbitres.
         * @param a 
         */
	public void ajouterArbitre(Arbitre a) {
            if (a instanceof ArbitreChaise) {
                m_arbitreChaise = (ArbitreChaise)a;
            }
            else if (a instanceof ArbitreFilet) {
                m_arbitreFilet = (ArbitreFilet)a;
            }
            else if (a instanceof ArbitreLigne) {
                m_arbitresLigne.put(m_arbitresLigne.size(), (ArbitreLigne) a);
            }
            
	}
        

	public void affecterCourt(Court court) {
            m_court = court;
	}

	public void affecterEquipeRamasseurs(EquipeRamasseurs equipeRamasseurs) {
            m_equipeRamasseurs = equipeRamasseurs;
	}

	public void affecterCreneau(Creneau creneau) {
            m_creneau = creneau;
            // indiquer que le creneau est occupé
            
	}

	public int getId() {
            return m_idMatch;
	}

	public Joueur getGagnant() {
            return m_gagnant;
	}

	public Joueur getPerdant() {
            return m_perdant;
	}

	public void ajouterSet(modele.Set set) {
            m_sets.add(set);
	}

	public String getType() {
            return m_type;
    	}
        
        public int setScore(List<modele.Set> sets) {
            if (sets.isEmpty()) return -1;
            m_sets = sets;
            int sets_j1 = 0;
            int sets_j2 = 0;
            for (modele.Set s : m_sets) {
                if (s.gagnant() == 1) {
                    sets_j1++;
                }
                else {
                    sets_j2++;
                }
            }
            
            if (sets_j1 > sets_j2) {
                m_gagnant = m_joueur1;
                m_perdant = m_joueur2;
            }
            else {
                m_gagnant = m_joueur2;
                m_perdant = m_joueur1;
            }
            
            return 0;
        }
        
	public List<modele.Set> getScoreFinal() {
            return m_sets;
	}

	public boolean estFini() {
            return m_fini;
	}
        
        @Override
        public String toString() {
            return "Match n°" + m_idMatch + ", type = " + m_type + ", opposant " + m_joueur1 + " et " + m_joueur2 + ", Creneau : " + m_creneau + ", Court : " + m_court + ", ArbitreChaise : " + m_arbitreChaise + ", ArbitreFilet : " + m_arbitreFilet + " ... " ;
        }

}