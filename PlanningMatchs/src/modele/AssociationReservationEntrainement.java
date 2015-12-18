package modele;

import java.util.Date;
import modele.court.CourtEntrainement;

/**
 * Classe associative entre Joueur et CourtEntrainement
 * @author dave
 */
public class ReservationEntrainement {
        
        private Joueur m_joueur;
        private CourtEntrainement m_court;
        
	private Date m_date;
	private int heure;
	private int duree;

    public ReservationEntrainement(Joueur m_joueur, CourtEntrainement m_court, Date m_date, int heure, int duree) {
        this.m_joueur = m_joueur;
        this.m_court = m_court;
        this.m_date = m_date;
        this.heure = heure;
        this.duree = duree;
    }
    
    
        

}