package modele;

public class Set {

	private int m_pointsJoueur1;
	private int m_pointsJoueur2;
        
        public Set(int pointsJoueur1, int pointsJoueur2) {
            m_pointsJoueur1 = pointsJoueur1;
            m_pointsJoueur2 = pointsJoueur2;
        }
        
	public int getPointsJoueur1() {
            return m_pointsJoueur1;
	}

	public int getPointsJoueur2() {
            return m_pointsJoueur2;
	}

	public void setPointsJoueur1(int points) {
            if (points < 0)
                m_pointsJoueur1 = 0;
            else
                m_pointsJoueur1 = points;
	}

	public void setPointsJoueur2(int points) {
            if (points < 0)
                m_pointsJoueur2 = 0;
            else
                m_pointsJoueur2 = points;
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
        
        
        @Override
        public String toString() {
            return m_pointsJoueur1 + " - " + m_pointsJoueur2;
        }
}