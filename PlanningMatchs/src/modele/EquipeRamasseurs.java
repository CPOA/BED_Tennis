
package modele;

import java.util.List;

public class EquipeRamasseurs {

	private int m_id;
	private int m_nbRamasseurs;

        List<Ramasseur> m_ramasseurs;

        public EquipeRamasseurs(int id, List<Ramasseur> m_ramasseurs) {
            this.m_ramasseurs = m_ramasseurs;
            this.m_nbRamasseurs = m_ramasseurs.size();
        }
        
        
        
	public int getId() {
            return m_id;
	}

	public void ajouterRamasseur(Ramasseur ramasseur) {
          m_ramasseurs.add(ramasseur);
	}

	public int getNbRamasseurs() {
            return m_nbRamasseurs;
	}

	public void estComplete() {
		
	}

}