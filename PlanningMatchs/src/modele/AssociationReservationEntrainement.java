package modele;

import java.util.Date;
import modele.court.CourtEntrainement;
import org.joda.time.DateTime;

/**
 * Classe associative entre Joueur et CourtEntrainement
 * @author dave
 */
public class AssociationReservationEntrainement {
        
        private Joueur m_joueur;
        private CourtEntrainement m_court;
        
	private DateTime m_date;
        
        private Creneau m_creneau;
	
        private int m_heure;
	private int m_duree;
        
    public AssociationReservationEntrainement(Joueur m_joueur, CourtEntrainement m_court, DateTime m_date) {
        this.m_joueur = m_joueur;
        this.m_court = m_court;
        this.m_date = m_date;
        
    }
    
    public AssociationReservationEntrainement(Joueur joueur, CourtEntrainement court, Creneau creneau) {
        this.m_joueur = joueur;
        this.m_court = court;
        this.m_creneau = creneau;
    }

    public Joueur getJoueur() {
        return m_joueur;
    }

    public void setJoueur(Joueur m_joueur) {
        this.m_joueur = m_joueur;
    }

    public CourtEntrainement getCourt() {
        return m_court;
    }

    public void setCourt(CourtEntrainement m_court) {
        this.m_court = m_court;
    }

    public Creneau getCreneau() {
        return m_creneau;
    }
    
    

}