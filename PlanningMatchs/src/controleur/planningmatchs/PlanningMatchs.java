/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur.planningmatchs;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.AssociationReservationEntrainement;
import modele.Creneau;
import modele.EquipeRamasseurs;
import modele.Error;
import modele.personne.Joueur;
import modele.Match;
import modele.personne.Ramasseur;
import modele.personne.Sexe;
import static modele.personne.Sexe.HOMME;
import modele.TrancheHoraire;
import modele.arbitre.Arbitre;
import modele.arbitre.ArbitreChaise;
import modele.arbitre.ArbitreFilet;
import modele.arbitre.ArbitreLigne;
import modele.court.CourtAnnexe;
import modele.court.CourtCentral;
import modele.court.CourtEntrainement;
import modele.sgbd.DaoArbitre;
import modele.sgbd.DaoJoueur;
import modele.sgbd.DaoRamasseur;
import org.joda.time.DateTime;

/**
 *
 * @author dave
 */
public class PlanningMatchs {
    
    
    private static HashMap<Integer, Joueur> joueurs;
    
    //private static HashMap<Integer, Arbitre> arbitres;
    private static List<Arbitre> arbitres;
    
    private static HashMap<Integer, Ramasseur> ramasseurs;
    
    private static java.util.Set<Creneau> creneaux;
    
    private static HashMap<Integer, Match> matchs;
    
    private static List<AssociationReservationEntrainement> reservations;
    
    
    static {
        
        int annee = 2016;
        int mois = 3;
        int jourDebut = 14;
        
        int nbJours = 7;
        
        // création des courts
        CourtAnnexe courtAnnexe = CourtAnnexe.getInstance();
        
        // Création des créneaux
        creneaux = new HashSet<>();
        
        for (int jour = 0; jour < nbJours; jour++) {
            for (TrancheHoraire trancheHoraire : TrancheHoraire.values()) {
                
                // court central
                Creneau creneau = new Creneau(CourtAnnexe.getInstance(), annee, mois, jourDebut + jour, trancheHoraire);
                creneaux.add(creneau);
                
                // court annexe
                creneau = new Creneau(CourtCentral.getInstance(), annee, mois, jourDebut + jour, trancheHoraire);
                creneaux.add(creneau);
                
                // courts d'entrainement
            }
        }
        
        System.out.println("Creneaux : ");
        for (Creneau c : creneaux) {
            System.out.println(c);
        }
        
        
        
        // récupération des joueurs
        
        //DaoJoueur daoJoueur = new DaoJoueur();
        
        joueurs = DaoJoueur.getJoueurs();
        System.out.println(DaoJoueur.getNbJoueurs() + " joueurs avant ajout");
        DaoJoueur.insertJoueur(new Joueur(1, "Jaloux", "Christophe", "profchauve@univ-lyon1.fr", HOMME, "nintendo", "c.jaloux", "mario", 1));
        System.out.println(DaoJoueur.getNbJoueurs() + " joueurs après ajout");

        DaoJoueur.viderJoueurs();
        System.out.println(DaoJoueur.getNbJoueurs() + " joueurs après vidage");

        
        // récupération des arbitres
        
        DaoArbitre daoArbitre = new DaoArbitre();
        //arbitres = daoArbitre.getArbitres();
        
        
        // récupération des ramasseurs
        //DaoRamasseur daoRamasseur = new DaoRamasseur();
        ramasseurs = DaoRamasseur.getRamasseurs();
        
        
        // récupération des matchs
        
        
    }
    
    
    public static void main(String[] args) {
        
        System.out.println("Hello, Miserable World.");
        
        DaoJoueur daoJoueur = new DaoJoueur();
        
        //joueurs = new HashMap<>();
        joueurs = new HashMap<>();
        
        //joueurs = daoJoueur.getJoueurs();
        
        for (Joueur j : joueurs.values()) {
            System.out.println(j.getDetails());
        }
        
        try {
            PlanningMatchs p = new PlanningMatchs("simple", HOMME);
        } catch (Error ex) {
            Logger.getLogger(PlanningMatchs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public PlanningMatchs(String typeTournoi, Sexe genre) throws Error {
        
        
        
        //List<Joueur> joueursRestants = joueurs;
        DaoJoueur daoJoueur = new DaoJoueur();
        List<Joueur> joueursRestants = new ArrayList<>();
        //joueursRestants = daoJoueur.getJoueurs();
        Joueur joueur1 = null, joueur2 = null;
        int i = joueursRestants.size();
        System.out.println(i + " joueurs recuperes");
        /*
        while (!(joueursRestants.size() > 2)) {

            
            
            
            Match match = new Match();
            //match = new Match(court, creneau, type, joueur1, joueur2, m_arbitreChaise, arbitreFilet, arbitresLigne, equipeRamasseurs);
            
            
        }*/
        
        
        for (Creneau creneau : creneaux) {
            
            if (!creneau.estLibre())
                continue;
            
            if (joueursRestants.size() < 2)
                break;
            
            joueur1 = joueursRestants.get(joueursRestants.size() - 1);
            joueur2 = joueursRestants.get(joueursRestants.size() - 2);
            
            Match nouveauMatch = new Match(creneau, typeTournoi, joueur1, joueur2);
            
            // assignation des arbitres
            ArbitreChaise arbitreChaise;
            
            for (Arbitre a : arbitres) {
                if (a instanceof ArbitreChaise) {
                    if (a.peutArbitrer(nouveauMatch)) {
                        nouveauMatch.ajouterArbitre(a);
                        break;
                    }
                }
                
            }
            
            for (Arbitre a : arbitres) {
                if (a instanceof ArbitreFilet) {
                    if (a.peutArbitrer(nouveauMatch)) {
                        nouveauMatch.ajouterArbitre(a);
                        break;
                    }
                }
            }
            
            
            // 8 arbitres de ligne
            Map<Integer, ArbitreLigne> arbitresLignes = new HashMap<>();
            int nbArbitresLigne = 0;
            for (Arbitre arbitre : arbitres) {
                if (arbitre instanceof ArbitreLigne) {
                    if (arbitre.peutArbitrer(nouveauMatch)) {
                        arbitresLignes.put(i, (ArbitreLigne) arbitre);
                        nbArbitresLigne++;
                    }
                }
                
            }
            if (i <= 8) {
                throw new Error("Pas assez d'arbitres de ligne disponibles (" + i + "/8 arbitres disponibles)");
            }
            
            nouveauMatch.setArbitresLigne(arbitresLignes);
            
            // ramasseurs
            
            EquipeRamasseurs equipeRamasseurs = new EquipeRamasseurs();
            Random rand = new Random();
            int n = ramasseurs.size();
            for (i = 0; i < 8; i++) {
                equipeRamasseurs.ajouterRamasseur(ramasseurs.get(rand.nextInt(n)));
            }
            
            
            //Match match = new Match(court, creneau, type, joueur1, joueur2, m_arbitreChaise, arbitreFilet, arbitresLigne, equipeRamasseurs);

            creneau.assigne(nouveauMatch);            
            
        } // creneaux
        
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
