
package metier;



import java.util.Date;
import metier.court.Court;
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
	private TrancheHoraire m_trancheHoraire;
        
        //private int m_heure;
        
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
            m_dateTime = new DateTime(year, month, day, getHeure(trancheHoraire), 0);
            m_trancheHoraire = trancheHoraire;
            m_match = null;
            m_court = court;
            m_libre = true;
        }
        
        /** Créneau existant déjà dans la base de données.
         * 
         * @param idCreneau
         * @param court
         * @param year
         * @param month
         * @param day
         * @param trancheHoraire 
         */
        public Creneau(int idCreneau, Court court, int year, int month, int day, TrancheHoraire trancheHoraire, boolean libre, Match match) {
            this(court, year, month, day, trancheHoraire);
            m_idCreneau = idCreneau;
            m_libre = libre;
            m_match = match;
        }
        
        public void setId(int idCreneau) {
            m_idCreneau = idCreneau;
        }
        
        public int getId() {
           return m_idCreneau;
        }
        
        public int assigne(Match match) throws Error {
            if (m_libre == false) 
                throw new Error("Le créneau " + m_idCreneau + " (" + toString() + ") n'est pas disponible");
            m_match = match;
            m_libre = false;
            return 0;
        }
        
        public int libere() throws Error {
            if (m_libre)
                throw new Error("Le créneau est déjà libre");
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
        /*
        public int getHeure() {
            return m_heure;
        }*/
        
        public TrancheHoraire getTrancheHoraire() {
            return m_trancheHoraire;
        }

        public void setTrancheHoraire(TrancheHoraire trancheHoraire) {
            m_trancheHoraire = trancheHoraire;
            m_dateTime = new DateTime(m_dateTime.year().get(), m_dateTime.monthOfYear().get(), m_dateTime.dayOfMonth().get(), getHeure(trancheHoraire), 0);

        }
        
        private int getHeure(TrancheHoraire trancheHoraire) {
            int heure = 0;
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
            return heure;
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
        int compareDate = this.m_dateTime.compareTo(other.getDateTime());
        if (compareDate == 0) {
            // si le moment est le même (dans ce cas il s'agit de cours différents)
            if (this.m_court.getId() <= other.getCourt().getId()) {
                return -1;
            }
            else
                return 1;
        }
        else
            return compareDate;
    }

}