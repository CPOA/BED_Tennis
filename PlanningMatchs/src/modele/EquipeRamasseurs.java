
package modele;

import java.util.ArrayList;
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
            this.m_ramasseurs = new ArrayList<>();
        }
        
        
	public int getId() {
            return m_id;
	}

	public void ajouterRamasseur(Ramasseur ramasseur) throws Error {
            if (m_ramasseurs.contains(ramasseur)) {
                //System.out.println("Ramasseur est déjà présent dans l'équipe");
                throw new Error("Ramasseur est déjà présent dans l'équipe");
                
            }
            m_ramasseurs.add(ramasseur);
            //System.out.println("Ramasseur ajouté : " + ramasseur + " - " + m_ramasseurs.size() + " ramasseurs dans l'équipe.");
	}

	public int getNbRamasseurs() {
            return m_ramasseurs.size();
	}

	public boolean estComplete() {
            return (m_nbRamasseurs == 8);
        }

        public boolean contient(Ramasseur ramasseur) {
            return m_ramasseurs.contains(ramasseur);
        }
        
}