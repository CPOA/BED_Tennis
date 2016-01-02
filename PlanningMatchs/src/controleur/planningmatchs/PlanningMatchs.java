/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur.planningmatchs;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
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
import modele.arbitre.TypeArbitre;
import modele.court.Court;
import modele.court.CourtAnnexe;
import modele.court.CourtCentral;
import modele.court.CourtEntrainement;
import static modele.personne.Sexe.FEMME;
import modele.sgbd.DaoArbitre;
import modele.sgbd.DaoCourt;
import modele.sgbd.DaoJoueur;
import modele.sgbd.DaoMatch;
import modele.sgbd.DaoRamasseur;
import org.joda.time.DateTime;

/**
 *
 * @author dave
 */
public class PlanningMatchs {
    
    
    //private static HashMap<Integer, Joueur> joueurs;
    private static List<Joueur> joueurs;
    
    private static HashMap<Integer, Arbitre> arbitres;
    
    //private static HashMap<Integer, Ramasseur> ramasseurs;
    private static List<Ramasseur> ramasseurs;
    
    private static List<Creneau> creneaux;
    
    private static List<Match> matchs;
    
    private static List<AssociationReservationEntrainement> reservations;
    
    
    static {
        
        int annee = 2016;
        int mois = 3;
        int jourDebut = 14;
        
        int nbJours = 1;
        
        // Récupération des courts
        List<Court> courts = DaoCourt.getCourts();
        
        // Création des créneaux
        creneaux = new ArrayList<>();
        Creneau creneau = null;
        for (int jour = 0; jour < nbJours; jour++) {
            for (TrancheHoraire trancheHoraire : TrancheHoraire.values()) {
                
                // changer et boucler sur tous les types de courts existants,
                //      à récupérer par DaoCourt
                /*
                // court central
                Creneau creneau = new Creneau(CourtAnnexe.getInstance(), annee, mois, jourDebut + jour, trancheHoraire);
                creneaux.add(creneau);
                
                // court annexe
                creneau = new Creneau(CourtCentral.getInstance(), annee, mois, jourDebut + jour, trancheHoraire);
                creneaux.add(creneau);
                
                
                // courts d'entrainement  */
                
                for (Court court : courts) {
                    System.out.println("- " + court + annee + mois + new Integer(jourDebut + jour) + trancheHoraire);
                    creneau = new Creneau(court, annee, mois, jourDebut+jour, trancheHoraire);
                    creneaux.add(creneau);
                }
            }
        }
        
        System.out.println("Creneaux : ");
        for (Creneau c : creneaux) {
            System.out.println(c);
        }
        
        
        
        // récupération des joueurs
        
        //DaoJoueur daoJoueur = new DaoJoueur();
        
        joueurs = DaoJoueur.getJoueurs();
        
        //DaoJoueur.viderJoueurs();
        //System.out.println(DaoJoueur.getNbJoueurs() + " joueurs après vidage");

        
        // récupération des arbitres
        
        DaoArbitre daoArbitre = new DaoArbitre();
        //arbitres = daoArbitre.getArbitres();
        
        
        // récupération des ramasseurs
        //DaoRamasseur daoRamasseur = new DaoRamasseur();
        ramasseurs = DaoRamasseur.getRamasseurs();
        
        
        // récupération des matchs
        matchs = new ArrayList<>();
        
    }
    
    public static void viderTables() {
        //DaoMatch.viderMatchs();
        DaoJoueur.viderJoueurs();
        DaoArbitre.viderArbitres();
        DaoRamasseur.viderRamasseurs();
    }
    
    public static void creerRemplirTables() {
        System.out.println(DaoJoueur.getNbJoueurs() + " joueurs avant vidage");
        
        //DaoMatch.viderMatchs();
        
        DaoJoueur.viderJoueurs();
        
        DaoArbitre.viderArbitres();
        
        DaoRamasseur.viderRamasseurs();
        
        
        System.out.println(DaoJoueur.getNbJoueurs() + " joueurs avant ajout");
        //DaoJoueur.insertJoueur(new Joueur(1, "Jaloux", "Christophe", "profchauve@univ-lyon1.fr", HOMME, "nintendo", "c.jaloux", "mario", 1));
        
        DaoJoueur.insertJoueur("Jaloux", "Christophe", "profchauve@example.com", HOMME, "France", "c.jaloux", "cj", 1);
        DaoJoueur.insertJoueur("Einstein", "Albert", "alberteinstein@example.com", HOMME, "Allemagne", "a.einstein", "ae", 2);
        DaoJoueur.insertJoueur("Bex", "Emile", "emilebex@example.com", HOMME, "France", "e.bex", "eb", 3);
        DaoJoueur.insertJoueur("Simon", "Claude", "ab@example.com", HOMME, "France", "a.b", "ab", 4);
        DaoJoueur.insertJoueur("Durif", "Sylvain Pierre", "christcosmique@example.com", HOMME, "Extraterrestre", "sp.durif", "spd", 9000);
        DaoJoueur.insertJoueur("Chirac", "Jacques", "jaqueschirac@example.com", HOMME, "France", "j.c", "jc", 5);
        
        
        System.out.println(DaoJoueur.getNbJoueurs() + " joueurs après ajout");
        
        
        
        
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Espagne", TypeArbitre.ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Allemagne", TypeArbitre.ARBITRE_FILET, 0, 0);

        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Espagne", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Italie", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", FEMME, "France", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Québec", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Irlande", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Angleterre", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", FEMME, "Croatie", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Russie", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Espagne", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Italie", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", FEMME, "France", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Québec", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Irlande", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Angleterre", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", FEMME, "Croatie", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Russie", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Espagne", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Italie", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", FEMME, "France", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Québec", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Irlande", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Angleterre", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", FEMME, "Croatie", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("A", "B", "a@example.com", HOMME, "Russie", TypeArbitre.ARBITRE_LIGNE, 0, 0);
        
        
        
        DaoRamasseur.insertRamasseur("truc", "machin", "truc@example.com", HOMME, "France");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("a", "b", "qzelf@example.com", FEMME, "Roumanie");
        
        
        System.out.println("Remplissage des tables terminé.");
        
        
    }
    
    public static void main(String[] args) {
        
        System.out.println("Hello, Miserable World.");
                
        //joueurs = new HashMap<>();
        
        //creerRemplirTables();
        
        
        joueurs = new ArrayList<>();
        
        joueurs = DaoJoueur.getJoueurs();
        
        for (Joueur j : joueurs) {
            System.out.println(j.getDetails());
        }
        
        try {
            planifierMatchs("simple", HOMME);
        } catch (Error ex) {
            Logger.getLogger(PlanningMatchs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public static void planifierMatchs(String typeTournoi, Sexe genre) throws Error {
        
        
        //List<Joueur> joueursRestants = joueurs;
        DaoJoueur daoJoueur = new DaoJoueur();
        List<Joueur> joueursRestants = new ArrayList<Joueur>();
        joueursRestants = daoJoueur.getJoueurs();
        Joueur joueur1 = null, joueur2 = null;
        int i = joueursRestants.size();
        System.out.println(i + " joueurs recupérés");
        
        
        arbitres = DaoArbitre.getArbitres();
        System.out.println(arbitres.size() + " arbitres récupérés");
        
        
        ramasseurs = DaoRamasseur.getRamasseurs();
        System.out.println(ramasseurs.size() + " ramasseurs récupérés.");
        System.out.println("Ramasseurs : " + ramasseurs);
        
        /*
        while (!(joueursRestants.size() > 2)) {

            
            
            
            Match match = new Match();
            //match = new Match(court, creneau, type, joueur1, joueur2, m_arbitreChaise, arbitreFilet, arbitresLigne, equipeRamasseurs);
            
            
        }*/
        
        
        for (Creneau creneau : creneaux) {
            
            System.out.println("Attribution pour créneau : " + creneau);
            if (!creneau.estLibre())
                continue;
            
            //System.out.println("1");
            
            //if (joueursRestants.size() < 2)
            //    break;
            
            //System.out.println("2");
            
            
            joueur1 = getJoueurLibre(joueursRestants, creneau, genre);
            if (joueur1 == null)
                break;
            joueursRestants.remove(joueur1);
            
            joueur2 = getJoueurLibre(joueursRestants, creneau, genre);
            if (joueur2 == null)
                break;
            joueursRestants.remove(joueur2);
            
            /*
            // à changer
            joueur1 = joueursRestants.get(joueursRestants.size() - 1);
            joueur2 = joueursRestants.get(joueursRestants.size() - 2);
            */
            
            joueursRestants.remove(joueur1);
            joueursRestants.remove(joueur2);
            
            Match nouveauMatch = new Match(creneau, typeTournoi, genre, joueur1, joueur2);
            System.out.println("nouveauMatch : " + nouveauMatch);
            // assignation des arbitres
            
            for (Arbitre a : arbitres.values()) {
                if (a instanceof ArbitreChaise) {
                    if (a.peutArbitrer(nouveauMatch)) {
                        nouveauMatch.ajouterArbitre(a);
                        break;
                    }
                }
            }
            
            for (Arbitre a : arbitres.values()) {
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
            for (Arbitre arbitre : arbitres.values()) {
                if (arbitre instanceof ArbitreLigne) {
                    if (arbitre.peutArbitrer(nouveauMatch)) {
                        arbitresLignes.put(nbArbitresLigne, (ArbitreLigne) arbitre);
                        //System.out.println("arbitre ajouté : " + arbitre.getId() + " - " + arbitresLignes.size() + " arbitres de ligne");
                        nbArbitresLigne++;
                        if (nbArbitresLigne == 8) 
                            break;
                    }
                }
                
            }
            if (nbArbitresLigne < 8) {
                System.out.println("Pas assez d'arbitres de ligne disponibles (" + nbArbitresLigne + "/8 arbitres disponibles)");
            }

            nouveauMatch.setArbitresLigne(arbitresLignes);
            
            // Attribution des ramasseurs de balle
            //  on les distribue au hasard pour éviter que ce soient toujours les mêmes
            
            EquipeRamasseurs equipeRamasseurs = new EquipeRamasseurs();
            Random rand = new Random();
            int n = ramasseurs.size();
            
            //HashMap<Integer, Ramasseur> ramasseursRestants = (HashMap<Integer, Ramasseur>) ramasseurs.clone();
            List<Ramasseur> ramasseursRestants = DaoRamasseur.getRamasseurs();
            
            while (equipeRamasseurs.getNbRamasseurs() < 8) {
                //System.out.println("nbRamasseurs = " + equipeRamasseurs.getNbRamasseurs() + " Ramasseurs restants : " + ramasseursRestants.size());
                Ramasseur ramasseur = null;
                boolean ramasseurValide = false;
                int index = 0;
                do {
                    index = rand.nextInt(ramasseursRestants.size());
                
                    ramasseur = ramasseurs.get(index);
                    ramasseurValide = true;
                    for (Creneau c : creneaux) {
                        if (c.conflitCreneau(creneau) && !c.estLibre()) {
                            if (c.getMatch().getEquipeRamasseurs().contient(ramasseur)) {
                                // ce ramasseur participe à un autre match au même moment et donc n'est pas disponible.
                                ramasseurValide = false;
                                ramasseursRestants.remove(index);
                                break;
                            }
                        }
                    }
                } while (!ramasseurValide);
                ramasseursRestants.remove(index);  // on l'enlève pour qu'il ne soit pas repris ensuite

                //System.out.println("Ramasseur : " + ramasseur.getId());
                try {
                    equipeRamasseurs.ajouterRamasseur(ramasseur);
                }
                catch (Error e) {
                    // ramasseur est déjà présent
                    //System.out.println("err : " + e.getMessage());
                }
                
            }
            
            
            nouveauMatch.affecterEquipeRamasseurs(equipeRamasseurs);
            creneau.assigne(nouveauMatch);
            //matchs.put(nouveauMatch.getId(), nouveauMatch);
            matchs.add(nouveauMatch);
            
            //nouveau Match : " + nouveauMatch
            
        } // creneaux
        
        System.out.println("Planification terminée.");
        
    }
    
    public static Joueur getJoueurLibre(List<Joueur> joueursRestants, Creneau creneau, Sexe sexe) {
        return getJoueurLibre(joueursRestants, creneau, sexe, "");
    }
    
    public static Joueur getJoueurLibre(List<Joueur> joueursRestants, Creneau creneau, Sexe sexe, String nationalite) {
        
        if (joueursRestants.size() < 1)
            return null;
        Joueur joueur;
        boolean joueurValide = false;
        int i = joueursRestants.size()-1;
        
        do {
            //System.out.println("i=" + i);
            joueur = joueursRestants.get(i);
            joueurValide = true;
            
            if (!joueur.getSexe().equals(sexe))
                joueurValide = false;
            
            if (!nationalite.equals("")) {
                if (!joueur.getNationalite().equals(nationalite))
                    joueurValide = false;
            }
            /*
            for (Creneau c : creneaux) {
                if (c.conflitCreneau(c) && !c.estLibre()) {
                    if (c.getMatch().getJoueur1().equals(joueur) || c.getMatch().getJoueur2().equals(joueur)) {
                        joueurValide = false;
                        break;
                    }
                }
            }*/
            i--;
        } while (!joueurValide);
        if (!joueurValide) {
            return null;
        }
        return joueur;
    }
    
    
    public static void ajouterResultatMatch(int idMatch, List<modele.Set> sets) {
        
        Match match = matchs.get(idMatch);
        
        match.setScore(sets);
        
        
    }
    
    public static Match getMatch(int idMatch) {
        for (Match m : matchs) {
            if (idMatch == m.getId()) {
                return m;
            }
        }
        return null;
    }
    
    public static List<Match> getMatchs() {
        System.out.println("matchs : " + matchs + "nbMatchs : " + matchs.size());
        return matchs;
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
