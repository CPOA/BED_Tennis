
package modele.arbitre;

import modele.Match;
import modele.personne.Sexe;
import modele.sgbd.DaoArbitre;



public abstract class Arbitre extends modele.personne.Personne {

	private TypeArbitre m_typeArbitre;
	private int m_nbMatchsSimples;
	private int m_nbMatchsDoubles;

        public Arbitre(TypeArbitre typeArbitre, int id, String nom, String prenom, String mail, Sexe sexe, String nationalite, int nbMatchsSimples, int nbMatchsDoubles) {
            super(id, nom, prenom, mail, sexe, nationalite);
            this.m_typeArbitre = typeArbitre;
            this.m_nbMatchsSimples = nbMatchsSimples;
            this.m_nbMatchsDoubles = nbMatchsDoubles;
        }

	public int getNbMatchsSimples() {
            return this.m_nbMatchsSimples;
	}

	public int getNbMatchsDoubles() {
            return this.m_nbMatchsDoubles;
	}
        
        public void assigneMatchSimple() {
            m_nbMatchsSimples++;
            DaoArbitre.assignerMatchSimple(this.getId());
        }
        
        public void assigneMatchDouble() {
            m_nbMatchsDoubles++;
        }

	public boolean peutArbitrer(Match match) {
            
            if (match.getType() == "simple") {
                if (m_nbMatchsSimples >= 2)
                    return false;
                
                if (this.aMemeNationalite(match.getEquipe1().getNationalite()))
                    return false;
                if (this.aMemeNationalite(match.getEquipe2().getNationalite()))
                    return false;
            }
            else if (match.getType() == "double") {
                if (m_nbMatchsDoubles >= 2)
                    return false;
            }
            
            
            return true;
	}
        
        public boolean acheter(long dollars) {
            if (dollars > 10000000)
                return true;
            else
                return false;
        }
        
        public TypeArbitre getTypeArbitre() {
            return m_typeArbitre;
        }

}