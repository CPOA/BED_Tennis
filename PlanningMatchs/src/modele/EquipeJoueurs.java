
import modele.Joueur;



public class EquipeJoueurs {

    
        private static int dernierIdDonne = 0;
	private int m_id;
	private int m_nbJoueurs;

        private Joueur joueur1;
        private Joueur joueur2;
        
	public int getId() {
            return m_id;
	}

	public void ajouterJoueur(Joueur j) {
		
	}

	public int getNbJoueurs() {
            return m_nbJoueurs;
	}

	public boolean estComplete() {
            return (m_nbJoueurs == 2);
	}

}