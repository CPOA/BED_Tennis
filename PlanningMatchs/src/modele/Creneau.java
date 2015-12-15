
package modele;



import java.util.Date;

public class Creneau {

	private Date m_date;
	private TrancheHoraire m_heure;
        private boolean m_libre;
        
        public Creneau(Date date, TrancheHoraire heure) {
            m_date = date;
            m_heure = heure;
            m_libre = true;
        }

        public Date getM_date() {
            return m_date;
        }

        public void setM_date(Date m_date) {
            this.m_date = m_date;
        }

        public TrancheHoraire getM_heure() {
            return m_heure;
        }

        public void setM_heure(TrancheHoraire m_heure) {
            this.m_heure = m_heure;
        }

	
        
        public boolean estLibre() {
            return m_libre;
        }
        
        @Override
	public String toString() {
            return m_date.toString() + m_heure.toString();
        }

}