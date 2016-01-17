package metier.court;


// Singleton

public abstract class Court {

	private int m_id;
	private String m_nom;
	private String m_adresse;
	private int m_capacite;
        
        public Court(int id, String nom, String adresse, int capacité) {
            this.m_id = id;
            this.m_nom = nom;
            this.m_adresse = adresse;
            this.m_capacite = capacité;
        }

        public int getId() {
            return m_id;
        }

        public void setId(int id) {
            this.m_id = id;
        }
        
        public String getNom() {
            return m_nom;
        }

        public String getAdresse() {
            return m_adresse;
        }

        public void setAdresse(String adresse) {
            this.m_adresse = adresse;
        }

        public int getCapacite() {
            return m_capacite;
        }

        public void setCapacite(int capacite) {
            this.m_capacite = capacite;
        }

        public abstract String getType();
        
        @Override
        public String toString() {
            return getType();
        }

}