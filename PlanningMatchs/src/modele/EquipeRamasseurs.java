
package modele;

import modele.personne.Ramasseur;
import java.util.List;

public class EquipeRamasseurs {

	private int m_id;
	private int m_nbRamasseurs;

        List<Ramasseur> m_ramasseurs;

        public EquipeRamasseurs(int id, List<Ramasseur> m_ramasseurs) {
            this.m_ramasseurs = m_ramasseurs;
            this.m_nbRamasseurs = m_ramasseurs.size();
        }

    public EquipeRamasseurs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
	public int getId() {
            return m_id;
	}

	public void ajouterRamasseur(Ramasseur ramasseur) {
          m_ramasseurs.add(ramasseur);
          System.out.println("Ramasseur ajouté : " + ramasseur);
	}

	public int getNbRamasseurs() {
            return m_nbRamasseurs;
	}

	public boolean estComplete() {
            return (m_nbRamasseurs == 8);
        }

}