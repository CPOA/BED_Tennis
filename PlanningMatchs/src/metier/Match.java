package metier;

import metier.personne.Joueur;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metier.personne.arbitre.Arbitre;
import metier.personne.arbitre.ArbitreChaise;
import metier.personne.arbitre.ArbitreFilet;
import metier.personne.arbitre.ArbitreLigne;
import metier.court.Court;
import metier.personne.Sexe;
import donnees.Dao;
import donnees.DaoMatch;



public class Match implements Comparable<Match>{
        
        private static int dernierIdDonne;
    
	private int m_idMatch;
        
	private List<metier.Set> m_sets;
            
        //private Court m_court;
        
        private Creneau m_creneau;
        
	private String m_type;
        private Sexe m_genre;
        
        private int m_rangTournoi;
        
	private EquipeJoueurs m_equipeGagnante;
	private EquipeJoueurs m_equipePerdante;
        
	private boolean m_fini;
        
        private Joueur m_joueur1;
        private Joueur m_joueur2;
        
        private EquipeJoueurs m_equipe1;
        private EquipeJoueurs m_equipe2;
        
        private int m_quiAGagne; // Qui a gagné : 1 ou 2
       
        private ArbitreChaise m_arbitreChaise;
        private ArbitreFilet m_arbitreFilet;
        private Map<Integer, ArbitreLigne> m_arbitresLigne;
        
        private EquipeRamasseurs m_equipeRamasseurs;
    
        
    static {
        //DaoMatch daoMatch = new DaoMatch();
        //dernierIdDonne = DaoMatch.getIdMax();
        //System.out.println("daoMatch.getIdMax");
        //dernierIdDonne = 0;
    }
        
    /**
     * Basic constructor.
     * 
     */
    public Match() {
        //dernierIdDonne++;
        //m_idMatch = dernierIdDonne;
        
        //System.out.println("Match() - id = " + m_idMatch);
        m_sets = new ArrayList();

        m_arbitresLigne = new HashMap<>();
        
        m_equipeRamasseurs = new EquipeRamasseurs();
        
        m_rangTournoi = 0;
        
    }
    
    
    /**
     * Constructs a NEW Match wich didn't exist before.
     * To represent a match already existing in the database, use the constructor with the id attribute instead.
     * 
     * @param m_creneau
     * @param m_type le type de match, "simple" ou "double"
     * @param sexe 
     * @param m_joueur1
     * @param m_joueur2
     */
    
    
    public Match(Creneau creneau, String type, Sexe sexe, EquipeJoueurs equipe1, EquipeJoueurs equipe2, int rangTournoi) {
        this();
        // on met l'id à zéro, et un id valide sera attribué lors de l'ajout dans la base de données
        //      voir DaoMatch.insertMatch()
        this.m_idMatch = 0;
        
        this.m_creneau = creneau;
        this.m_type = type;
        this.m_genre = sexe;
        this.m_equipe1 = equipe1;
        this.m_equipe2 = equipe2;
        this.m_rangTournoi = rangTournoi;
    }
    
    /**
     * Constructeur pour un nouveau Match en passant deux joueurs en paramètre au lieu de deux équipes.
     * Les deux équipes de un sont créées en interne avec ces deux joueurs.
     * 
     */
    public Match(Creneau creneau, String type, Sexe sexe, Joueur joueur1, Joueur joueur2, int rangTournoi) {
        this(creneau, type, sexe, new EquipeJoueurs(joueur1), new EquipeJoueurs(joueur2), rangTournoi);
        //System.out.println(m_equipe1.toString() + m_equipe2.toString());
    }

    
    /**
     * Constructor for a Match which already exists in the database (already has an id)
     * @param m_idMatch
     * @param m_sets
     * @param m_court
     * @param m_creneau
     * @param m_type
     * @param m_gagnant
     * @param m_perdant
     * @param m_fini
     * @param m_joueur1
     * @param m_joueur2
     * @param m_arbitreChaise
     * @param m_arbitreFilet
     * @param m_arbitresLigne
     * @param m_equipeRamasseurs 
     */
    public Match(int idMatch, List<Set> sets, Creneau creneau, String type, Sexe genre, boolean fini, EquipeJoueurs equipe1, EquipeJoueurs equipe2, ArbitreChaise arbitreChaise, ArbitreFilet arbitreFilet, Map<Integer, ArbitreLigne> arbitresLigne, EquipeRamasseurs equipeRamasseurs, int rangTournoi) {
        this();
        this.m_idMatch = idMatch;
        this.m_sets = sets;
        this.m_creneau = creneau;
        this.m_type = type;
        this.m_genre = genre;
        this.m_fini = fini;
        
        this.m_rangTournoi = rangTournoi;
        
        this.m_equipe1 = equipe1;
        this.m_equipe2 = equipe2;
        
        this.m_arbitreChaise = arbitreChaise;
        this.m_arbitreFilet = arbitreFilet;
        this.m_arbitresLigne = arbitresLigne;
        this.m_equipeRamasseurs = equipeRamasseurs;
        
        calculerResultat();
        
    }
    
        public void setIdMatch(int idMatch) {
            this.m_idMatch = idMatch;
        }
    
        public Joueur getJoueur1() {
            //return m_joueur1;
            return m_equipe1.getJoueurA();
        }
        
        public Joueur getJoueur2() {
            //return m_joueur2;
            return m_equipe2.getJoueurA();
        }
        
        
        
	public int setJoueur1(Joueur j1) {
            
            if (m_joueur2 != null) {
                if (m_joueur2.getId() == j1.getId())
                    return -1;
            }
            m_joueur1 = j1;
            return 0;
        }
        
        public int setJoueur2(Joueur j2) {
            if (m_joueur1 != null) {
                if (m_joueur1.getId() == j2.getId())
                    return -1;
            }
            m_joueur2 = j2;
            return 0;
        }
        
        
        public EquipeJoueurs getEquipe1() {
            return m_equipe1;
        }
        
        public EquipeJoueurs getEquipe2() {
            return m_equipe2;
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
        
        public void setArbitreChaise(ArbitreChaise a) {
            m_arbitreChaise = a;
        }
        
        public void setArbitreFilet(ArbitreFilet a) { 
            m_arbitreFilet = a;
        }
        
        public void setArbitresLigne(Map<Integer, ArbitreLigne> arbitresLignes) {
            m_arbitresLigne = arbitresLignes;
        }
        
        public Arbitre getArbitreChaise() {
            return m_arbitreChaise;
        }
        
        public Arbitre getArbitreFilet() {
            return m_arbitreFilet;
        }
        
        public Map<Integer, ArbitreLigne> getArbitresLigne() {
            return m_arbitresLigne;
        }
        
        public Court getCourt() {
            return m_creneau.getCourt();
        }

	public void affecterEquipeRamasseurs(EquipeRamasseurs equipeRamasseurs) {
            m_equipeRamasseurs = equipeRamasseurs;
	}
        
        public EquipeRamasseurs getEquipeRamasseurs() {
            return m_equipeRamasseurs;
        }

	public void affecterCreneau(Creneau creneau) {
            m_creneau = creneau;
            // indiquer que le creneau est occupé
            
	}

	public int getId() {
            return m_idMatch;
	}
        
        public Creneau getCreneau() {
            return m_creneau;
        }
        
        public void setCreneau(Creneau c) {
            m_creneau = c;
        }

	public EquipeJoueurs getEquipeGagnante() {
            return m_equipeGagnante;
	}

        public int getGagnant() {
            return m_quiAGagne;
        }
        
	/*public Joueur getPerdant() {
            return m_perdant;
	}*/

	public void ajouterSet(metier.Set set) {
            m_sets.add(set);
	}

	public String getType() {
            return m_type;
    	}
        
        public Sexe getGenre() {
            return m_genre;
        }
        
        public void setRangTournoi(int rangTournoi) throws Error {
            if (rangTournoi == 1 || rangTournoi == 2 || rangTournoi == 4 || rangTournoi == 8 || rangTournoi == 16) {
                m_rangTournoi = rangTournoi;
            }
            else {
                throw new Error("Rang tournoi invalide : " + rangTournoi);
            }
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
        
        private void calculerResultat() {
            if (estFini()) {
                int sets_e1 = 0;
                int sets_e2 = 0;
                for (metier.Set s : m_sets) {
                    if (s.gagnant() == 1) {
                        sets_e1++;
                    }
                    else {
                        sets_e2++;
                    }
                }

                if (sets_e1 > sets_e2) {
                    m_equipeGagnante = m_equipe1;
                    m_equipePerdante = m_equipe2;
                    m_quiAGagne = 1;
                }
                else {
                    m_equipeGagnante = m_equipe2;
                    m_equipePerdante = m_equipe1;
                    m_quiAGagne = 2;
                }
            }
        }
        
        public int setScore(List<metier.Set> sets) throws Error {
            if (sets.isEmpty())
                throw new Error("La liste de sets est vide");
            m_sets = sets;
            m_fini = true;
            calculerResultat();
            return 0;
        }
        
	public List<metier.Set> getScoreFinal() {
            return m_sets;
	}

	public boolean estFini() {
            return m_fini;
	}
        
        public boolean contient(ArbitreLigne arbitreLigne) {
            for (ArbitreLigne a : m_arbitresLigne.values()) {
                if (a.getId() == arbitreLigne.getId())
                    return true;
            }
            return false;
             
        }
        
        @Override
        public String toString() {
            String arbitresLignes = "{ ";
            for (int i = 0; i < 8; i++) {
                arbitresLignes += i + ": " + m_arbitresLigne.get(i) + ", ";
            }
            arbitresLignes += "}";
            return "Match n°" + m_idMatch + ", type = " + m_type + ", opposant " + m_equipe1 + " et " + m_equipe2 + ", Creneau : " + m_creneau + ", Court : " + (getCourt() == null ? "" : getCourt()) + ", ArbitreChaise : " + m_arbitreChaise + ", ArbitreFilet : " + m_arbitreFilet + " arbitresLignes : " + arbitresLignes;
        }
        
        @Override
        public int hashCode() {
            return m_idMatch;
        }
        
        
        @Override
        public boolean equals(Object other) {
            if (other instanceof Match)
                return (((Match) other).getId() == (this.m_idMatch));
            else
                return false;
        }

        @Override
        public int compareTo(Match other) {
            return this.m_creneau.compareTo(other.getCreneau());
        }
        
        

}