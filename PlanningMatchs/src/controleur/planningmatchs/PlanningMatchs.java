/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur.planningmatchs;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import modele.AssociationReservationEntrainement;
import modele.Creneau;
import modele.Joueur;
import modele.Match;
import modele.arbitre.Arbitre;
import modele.court.CourtEntrainement;
import modele.sgbd.DaoJoueur;
import org.joda.time.DateTime;

/**
 *
 * @author dave
 */
public class PlanningMatchs {
    
    
    private static List<Joueur> joueurs;
    
    private static HashMap<Integer, Arbitre> arbitres;
    
    private static java.util.Set<Creneau> creneaux;
    
    private static HashMap<Integer, Match> matchs;
    
    private static List<AssociationReservationEntrainement> reservations;
    
    public static void main(String[] args) {
        
        System.out.println("Hello, Miserable World.");
        
        DaoJoueur daoJoueur = new DaoJoueur();
        
        //joueurs = new HashMap<>();
        joueurs = new ArrayList<>();
        
        joueurs = daoJoueur.getJoueurs();
        
        for (Joueur j : joueurs) {
            System.out.println(j.getDetails());
        }
        
        
        
    }

    
    public PlanningMatchs() {
        
        
        //HashMap<Integer, Joueur> joueursRestants = (HashMap<Integer, Joueur>) joueurs;
        DaoJoueur daoJoueur = new DaoJoueur();
        List<Joueur> joueursRestants = daoJoueur.getJoueurs();
        Joueur joueur1 = null, joueur2 = null;
        int i = joueursRestants.size();
        System.out.println(i + " joueurs recuperes");
        
        while (!(joueursRestants.size() > 2)) {
            joueur1 = joueursRestants.get(joueursRestants.size() - 1);
            joueur2 = joueursRestants.get(joueursRestants.size() - 2);
            
            
            
            
            Match match = new Match();
            
            
        }
        
    }
    
    
    
    public static void planifierTournoi(String typeTournoi) {
        
        List<Joueur> joueurs;
        
        
    }
    
    public static void ajouterResultatMatch(int idMatch, List<modele.Set> sets) {
        
        Match match = matchs.get(idMatch);
        
        match.setScore(sets);
        
        
    }
    
    public static List<AssociationReservationEntrainement> getReservationsEntrainement() {
        return reservations;
    }
    
    public static int reserverCourtEntrainement(Joueur joueur, CourtEntrainement court, Creneau creneau) {
        System.out.println("in reserverCourtEntrainement() :");
        
        for (AssociationReservationEntrainement r : reservations) {
            if (r.getCreneau().conflitCreneau(creneau)) {
                System.out.println(creneau + " in conflict with "+ r);
                return -1;
            }
        }
        
        // at this point, no conflict.
        
        AssociationReservationEntrainement reservation = null;
        reservation = new AssociationReservationEntrainement(joueur, court, creneau);
        
        reservations.add(reservation);
        
        return 0;
    }
    
    
}
