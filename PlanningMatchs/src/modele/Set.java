package modele;

public class Set {

	private int m_pointsJoueur1;
	private int m_pointsJoueur2;

	public void getPointsJoueur1() {
            
	}

	public void getPointsJoueur2() {
		
	}

	public void setPointsJoueur1() {
		
	}

	public void setPointsJoueur2() {
		
	}
        
        // renvoie lequel des joueurs a gagné le set
        int gagnant() {
            if (m_pointsJoueur1 > m_pointsJoueur2) {
                return 1;
            }
            else {
                return 2;
            }
        }
}