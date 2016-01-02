
package modele;



import java.util.Date;
import modele.court.Court;
import org.joda.time.DateTime;

/**
 * Creneau est une classe associative entre un Court et un Match.
 * 
 * 
*/
public class Creneau implements Comparable<Creneau>{
        
        private int m_idCreneau;
        
    
        private DateTime m_dateTime;
        
	//private Date m_date;
	private TrancheHoraire m_heure;
        
        private Court m_court;
        
        private boolean m_libre;
        
        private Match m_match;
        
        /**
         * Création d'un nouveau créneau n'existant pas déjà dans la base de données
         * @param court
         * @param year
         * @param month
         * @param day
         * @param trancheHoraire 
         */
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
        
        public Creneau(int idCreneau, Court court, int year, int month, int day, TrancheHoraire trancheHoraire) {
            this(court, year, month, day, trancheHoraire);
            m_idCreneau = idCreneau;
        }
        
        public void setId(int idCreneau) {
            m_idCreneau = idCreneau;
        }
        
        public int getId() {
           return m_idCreneau;
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
        
        public Match getMatch() {
            return m_match;
        }
        
        public void setMatch(Match match) {
            this.m_match = match;
        }
        
	
        
        public boolean estLibre() {
            return m_libre;
        }
        
        @Override
	public String toString() {
            return m_dateTime.toString("dd/MM/yy à HH:mm");
        }
        
        public String description() {
            return "creneau {" + m_dateTime.toString("d-M-y@HH:mm, ") + "court=" + m_court + ", libre=" + m_libre + "}";
        }
        
        public boolean conflitCreneau(Creneau other) {
            
            int dureeMatch = 3;
            
            if (this == other)
                return false; // un créneau n'est pas en conflit avec lui-même
            
            if (this.m_dateTime.equals(other.getDateTime()))
                return true;
            
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

    @Override
    public int compareTo(Creneau other) {
        return this.m_dateTime.compareTo(other.getDateTime());
    }

}