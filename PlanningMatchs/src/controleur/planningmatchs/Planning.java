/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur.planningmatchs;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import modele.Creneau;
import modele.Joueur;
import modele.Match;
import static modele.TrancheHoraire.*;
import modele.arbitre.Arbitre;
import modele.arbitre.ArbitreChaise;
import modele.arbitre.ArbitreFilet;

/**
 *
 * @author dave
 */
public class Planning {
    
    
    /*
    private java.util.Set<Joueur> joueurs;
    
    private java.util.Set<Arbitre> arbitres;
    
    private java.util.Set<Creneau> creneaux;
    
    private HashMap<Integer, Match> matchs;
    
    public Planning(Date dateDebut, Date dateFin) {
        
        matchs = new HashMap<>();
        
        
        Creneau creneau;
        
        creneaux = new java.util.TreeSet<>();
        
        creneau = new Creneau(dateDebut, MATIN);
        creneaux.add(creneau);
        
        creneau = new Creneau(dateDebut, APRES_MIDI);
        creneaux.add(creneau);
        
        
        
    }
        
    public int generePlanning() {
        
        joueurs = new HashSet<>();
        
        //joueurs.add();
        
        for (Creneau c : creneaux) {
            System.out.println("Créneau : " + c.toString());
            if (c.estLibre()) {
                
                
                Match newMatch = new Match();
                
                newMatch.affecterCreneau(c);
                
                Joueur joueur1 = null;
                Joueur joueur2 = null;
                
                newMatch.setJoueur1(joueur1);
                newMatch.setJoueur2(joueur2);
                
                // attribution des arbitres
                for (Arbitre a : arbitres) {
                    if (a instanceof ArbitreChaise) {
                        if (!a.aMemeNationalite(joueur1) && !a.aMemeNationalite(joueur2)) {
                            if (a.peutArbitrer()) {
                                newMatch.ajouterArbitre(a);
                            }
                        }
                    }
                    else if (a instanceof ArbitreFilet) {
                        if (!a.aMemeNationalite(joueur1) && !a.aMemeNationalite(joueur2)) {
                            if (a.peutArbitrer()) {
                                newMatch.ajouterArbitre(a);
                            }
                        }
                        
                    }
                }
                
                
                
                
                
            }
        }
        return 0;
    }
    
    
    
    public Creneau getPremierCreneauDispo() {
        for (Creneau c : creneaux) {
            if (c.estLibre())
                return c;
        }
        return null;
    }

    */
    
    
}
