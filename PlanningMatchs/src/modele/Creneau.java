
package modele;



import java.util.Date;
import modele.court.Court;
import org.joda.time.DateTime;

/**
 * Creneau est une classe associative entre un Court et un Match.
 * 
 * 
*/
public class Creneau {
        
        private DateTime m_dateTime;
        
	//private Date m_date;
	private TrancheHoraire m_heure;
        
        private Court m_court;
        
        private boolean m_libre;
        
        private Match m_match;
        
        
        public Creneau (Court court, int year, int month, int day, TrancheHoraire trancheHoraire) {
            int heure = 0, minutes = 0;
            switch(trancheHoraire) {
                case MATIN:
                    heure = 8;
                    break;
                case MATINEE:
                    heure = 11;
                    break;
                case MIDI:
                    heure = 15;
                    break;
                case APRES_MIDI:
                    heure = 18;
                    break;
                case SOIREE:
                    heure = 21;
                    break;
            }
            
            m_dateTime = new DateTime(year, month, day, heure, minutes);
            
            m_libre = true;
            m_match = null;
            m_court = court;
        }
        
        public int assigne(Match match) {
            if (m_libre == false) 
                return -1;
            m_match = match;
            m_libre = false;
            return 0;
        }
        
        public int libere() {
            if (m_libre)
                return -1;
            m_libre = true;
            m_court = null;
            m_match = null;
            return 0;
        }
        
        public DateTime getDateTime() {
            return m_dateTime;
        }

        public void setDateTime(DateTime m_dateTime) {
            this.m_dateTime = m_dateTime;
        }

        public TrancheHoraire getHeure() {
            return m_heure;
        }

        public void setHeure(TrancheHoraire m_heure) {
            this.m_heure = m_heure;
        }
        
        
        public Court getCourt() {
            return m_court;
        }

        public void setCourt(Court m_court) {
            this.m_court = m_court;
        }
	
        
        public boolean estLibre() {
            return m_libre;
        }
        
        @Override
	public String toString() {
            return "creneau{" + m_dateTime.toString("d-M-y@HH:mm, ") + m_court + ", " + m_libre + "}";
        }
        
        public boolean conflitCreneau(Creneau other) {
            
            int dureeMatch = 3;
            
            if (this.m_dateTime.dayOfYear().equals(other.getDateTime().dayOfYear())) {
                if (other.getDateTime().isAfter(m_dateTime)) {
                    if (other.getDateTime().isBefore(m_dateTime.plusHours(dureeMatch))) {
                        return true;
                    }
                }
                else {  // other is before current
                    if (other.getDateTime().plusHours(dureeMatch).isAfter(m_dateTime)) {
                        return true;
                    }
                }
            }
            return false;
        }

}