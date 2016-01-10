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
import modele.EquipeJoueurs;
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
import static modele.arbitre.TypeArbitre.ARBITRE_CHAISE;
import static modele.arbitre.TypeArbitre.ARBITRE_FILET;
import static modele.arbitre.TypeArbitre.ARBITRE_LIGNE;
import modele.court.Court;
import modele.court.CourtAnnexe;
import modele.court.CourtCentral;
import modele.court.CourtEntrainement;
import static modele.personne.Sexe.FEMME;
import donnees.Dao;
import donnees.DaoArbitre;
import donnees.DaoCourt;
import donnees.DaoCreneau;
import donnees.DaoJoueur;
import donnees.DaoMatch;
import donnees.DaoRamasseur;
import org.joda.time.DateTime;

/**
 *
 * @author dave
 */
public class PlanningMatchs {
    
    
    //private static HashMap<Integer, Joueur> joueurs;
    private static List<Joueur> joueurs;
    
    
    private static List<EquipeJoueurs> joueurs16emeDeFinale;
    private static List<EquipeJoueurs> joueurs8emeDeFinale;
    private static List<EquipeJoueurs> joueursQuartDeFinale;
    private static List<EquipeJoueurs> joueursDemiFinale;
    private static List<EquipeJoueurs> joueursFinale;
    
    private static HashMap<Integer, Arbitre> arbitres;
    
    //private static HashMap<Integer, Ramasseur> ramasseurs;
    private static List<Ramasseur> ramasseurs;
    
    private static List<Creneau> creneaux;
    
    //private static List<Match> matchs;
    private static HashMap<Integer, Match> matchs;
    
    private static List<AssociationReservationEntrainement> reservations;
    
    
    private static final int jourTournoi = 14;
    private static final int moisTournoi = 3;
    private static final int anneeTournoi = 2016;
    private static final int dureeTournoi = 9;
    
    static {
        
        int annee = 2016;
        int mois = 3;
        int jourDebut = 14;
        
        int nbJours = 7;
        System.out.println("PlanningMatch {static}");
        //creerCreneaux(annee, mois, jourDebut, nbJours);
        
        // récupération des joueurs
        
        //DaoJoueur daoJoueur = new DaoJoueur();
        
        //joueurs = DaoJoueur.getJoueurs();
        
        joueurs16emeDeFinale = DaoJoueur.getEquipes("simple", 16);
        joueurs8emeDeFinale = DaoJoueur.getEquipes("simple", 8);
        joueursQuartDeFinale = DaoJoueur.getEquipes("simple", 4);
        joueursDemiFinale = DaoJoueur.getEquipes("simple", 2);
        joueursFinale = DaoJoueur.getEquipes("simple", 1);

        
        matchs = new HashMap<>();
        
        //DaoJoueur.viderJoueurs();
        //System.out.println(DaoJoueur.getNbJoueurs() + " joueurs après vidage");

        
        // récupération des arbitres
        
        //DaoArbitre daoArbitre = new DaoArbitre();
        //arbitres = daoArbitre.getArbitres();
        
        
        // récupération des ramasseurs
        //DaoRamasseur daoRamasseur = new DaoRamasseur();
        ramasseurs = DaoRamasseur.getRamasseurs();
        
        // récupération des matchs
        //matchs = new ArrayList<>();
        //matchs = new HashMap<>();
        /*
        try {    
            //matchs = DaoMatch.getMatchs();
        } catch (Error ex) {
            Logger.getLogger(PlanningMatchs.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    public static void viderTables() {
        DaoMatch.viderMatchs();
        DaoCourt.viderCourts();
        DaoCreneau.viderCreneaux();
        DaoJoueur.viderJoueurs();
        DaoArbitre.viderArbitres();
        DaoRamasseur.viderRamasseurs();
        Dao.getIdMax();
        System.out.println("Tables vidées.");
    }
    
    public static void creerCreneaux(int annee, int mois, int jourDebut, int nbJours) {
        
        DaoCreneau.viderCreneaux();
        
        // Récupération des courts
        HashMap<Integer, Court> courts = DaoCourt.getCourts();
        System.out.println("Courts : " + courts);
        // Création des créneaux
        creneaux = new ArrayList<>();
        Creneau creneau = null;
        for (int jour = 0; jour < nbJours; jour++) {
            for (TrancheHoraire trancheHoraire : TrancheHoraire.values()) {
                
                for (Court court : courts.values()) {
                    System.out.println("- " + court + annee + mois + new Integer(jourDebut + jour) + trancheHoraire);
                    creneau = new Creneau(court, annee, mois, jourDebut+jour, trancheHoraire);
                    DaoCreneau.insertCreneau(creneau);
                    creneaux.add(creneau);
                }
                
                
            }
        }
        
        System.out.println("Creneaux : ");
        for (Creneau c : creneaux) {
            System.out.println(c);
        }
        
        
        
    }
    
    public static void creerRemplirTables() {
        System.out.println(DaoJoueur.getNbJoueurs() + " joueurs avant vidage");
        
        viderTables();
        
        Dao.getIdMax();
        
        creerCreneaux(anneeTournoi, moisTournoi, jourTournoi, dureeTournoi);
        
        System.out.println(DaoJoueur.getNbJoueurs() + " joueurs avant ajout");
        //DaoJoueur.insertJoueur(new Joueur(1, "Jaloux", "Christophe", "profchauve@univ-lyon1.fr", HOMME, "nintendo", "c.jaloux", "mario", 1));
        
        DaoJoueur.insertJoueur("Jaloux", "Christophe", "profchauve@example.com", HOMME, "France", "c.jaloux", "cj", 1, 16);
        DaoJoueur.insertJoueur("Einstein", "Albert", "alberteinstein@example.com", HOMME, "Allemagne", "a.einstein", "ae", 2, 16);
        DaoJoueur.insertJoueur("Simon", "Claude", "ab@example.com", HOMME, "France", "a.b", "ab", 4, 16);
        DaoJoueur.insertJoueur("Durif", "Sylvain Pierre", "christcosmique@example.com", HOMME, "Extraterrestre", "sp.durif", "spd", 9000, 16);
        DaoJoueur.insertJoueur("Chirac", "Jacques", "jaqueschirac@example.com", HOMME, "France", "j.c", "jc", 5, 16);
        DaoJoueur.insertJoueur("Nutella", "Coco", "choco74@example.com", HOMME, "AMERIQUE", "admin", "nimda", 1, 16);
        DaoJoueur.insertJoueur("Paul", "Jack", "khot@example.com",HOMME , "FRANCE", "fr38", "etoile", 2, 16);
        DaoJoueur.insertJoueur("Ani", "Walk", "jedii@example.com",HOMME , "CANADA", "plop", "lol", 3, 16);
        DaoJoueur.insertJoueur("Pro", "Stalker", "illuminati@example.com",HOMME , "HOLLANDE", "oeil", "triangle", 4, 16);
        DaoJoueur.insertJoueur("Yanick", "Balboa", "tennissinnet@example.com",HOMME , "FRANCE", "balle", "trou", 5, 16);
        DaoJoueur.insertJoueur("Roni", "Jack", "atlas@example.com",HOMME , "ESPAGNE", "tals", "zip", 6, 16);
        DaoJoueur.insertJoueur("Vivier", "David", "shawinigan@example.com",HOMME , "FRANCE", "presque", "parti", 7, 16);
        DaoJoueur.insertJoueur("Boni", "Bryan", "cloud@example.com",HOMME , "FRANCE", "azerty", "12345", 8, 16);
        DaoJoueur.insertJoueur("Trik", "Almir", "tetris@example.com",HOMME , "ALLEMAGNE", "blue", "ocean", 10, 16);
        DaoJoueur.insertJoueur("Kris", "Red", "bbf@example.com",HOMME , "FRANCE", "coucou", "5698", 11, 16);
        DaoJoueur.insertJoueur("Onagi", "Plex", "bbqb@example.com",HOMME , "FRANCE", "enrevoir", "oupas", 12, 16);
        DaoJoueur.insertJoueur("Along", "William", "willllllll@example.com",HOMME , "FRANCE", "feglfhlsqrihnt", "jjdjdjd", 13, 16);
        DaoJoueur.insertJoueur("Kelle", "Willy", "fucj@example.com",HOMME , "RUSSIE", "mother", "russia", 14, 16);
        DaoJoueur.insertJoueur("Monkey", "Luffy", "onepiece@example.com",HOMME , "JAPON", "Ace", "rip2012", 15, 16);
        DaoJoueur.insertJoueur("Rocky", "Bannane", "rockycky@example.com",HOMME , "FRANCE", "dur", "acuire", 16, 16);
        DaoJoueur.insertJoueur("Deep", "Blue", "echecs@example.com",HOMME , "AMERIQUE", "Ima", "winner1997", 17, 16);
        DaoJoueur.insertJoueur("Garry", "Kasp", "echecs2@example.com",HOMME , "RUSSIE", "Ima", "looser1997", 18, 16);
        DaoJoueur.insertJoueur("Sky", "Luck", "star@example.com",HOMME , "AMERIQUE", "jesuis", "tonfils", 19, 16);
        DaoJoueur.insertJoueur("Dark", "Vadrouille", "wars@example.com",HOMME , "AMERIQUE", "nooooo", "ooooo", 20, 16);
        DaoJoueur.insertJoueur("Jffff", "Sian", "hello@example.com",HOMME , "FRANCE", "word", "php5", 21, 16);
        DaoJoueur.insertJoueur("Krigaya", "Kazuto", "SAO@example.com",HOMME , "JAPON", "krito", "asuna", 22, 16);
        DaoJoueur.insertJoueur("Bex", "Emile", "BED@example.com",HOMME , "FRANCE", "balls", "steels", 23, 16);
        DaoJoueur.insertJoueur("Fgray", "Shade", "book@example.com",HOMME , "ESPAGNE", "hhhhhhh", "7898", 24, 16);
        DaoJoueur.insertJoueur("Merveille", "Alice", "imaginaire@example.com",HOMME , "FRANCE", "lapin", "chapeau", 25, 16);
        DaoJoueur.insertJoueur("Samsung", "Apple", "Telephonie@example.com",HOMME , "FRANCE", "cher", "encoreplus", 26, 16);
        DaoJoueur.insertJoueur("Skype", "Fail", "freeware@example.com",HOMME , "AMERIQUE", "or", "not", 27, 16);
        DaoJoueur.insertJoueur("Djikstra", "Astar", "perdu58@example.com",HOMME , "FRANCE", "pathfinding", "dedale", 28, 16);
        
        /*
        DaoJoueur.insertJoueur("Sparkle", "Twi", "poney@example.com",HOMME , "ESPAGNE", "mylittle", "fantasy", 29);
        DaoJoueur.insertJoueur("First", "Adam", "jardin00@example.com",HOMME , "FRANCE", "pomme", "serpent", 30);
        DaoJoueur.insertJoueur("Simpson", "Bart", "Skate@example.com",HOMME , "AMERIQUE", "aye", "caramba", 31);
       DaoJoueur.insertJoueur("Moi", "Etmoi", "seul@example.com",HOMME , "FRANCE", "estecrit", "cestables", 32);
        
        
        
        DaoJoueur.insertJoueur("Yugi", "Josie", "lok@example.com",FEMME , "FRANCE", "admine", "enimda", 1);
        DaoJoueur.insertJoueur("Simpson", "Lisa", "example@example.com",FEMME , "AMERIQUE", "login74", "motdepasse", 2);
        DaoJoueur.insertJoueur("Noel", "Marry", "xmas@example.com",FEMME , "FRANCE", "cadeau", "vilain13", 3);
        DaoJoueur.insertJoueur("Disney", "Minnie", "Moussedecafe@example.com",FEMME , "FRANCE", "park", "sansatraction", 4);
        DaoJoueur.insertJoueur("Coca", "Zero", "calorie@example.com",FEMME , "FRANCE", "mespas0", "conneries", 5);
        DaoJoueur.insertJoueur("R2", "D2", "petitrobotincomprit@example.com",FEMME , "FRANCE", "etouiR2D2", "estunefemme", 6);
        DaoJoueur.insertJoueur("Mich", "Jaquie", "xxx@example.com",FEMME , "FRANCE", "2girls", "1coupedefrance", 7);
        DaoJoueur.insertJoueur("Lolld", "Loli", "littleTennis@example.com",FEMME , "ESPAGNE", "rateau", "1548963df", 8);
        DaoJoueur.insertJoueur("Mais", "Bonduelle", "pub@example.com",FEMME , "ALLEMAGNE", "placement", "deproduits75", 10);
        DaoJoueur.insertJoueur("Quiche", "Lorraine", "delicieuse@example.com",FEMME , "ALLEMAGNE", "maisque", "sicestfaitmaison", 11);
        DaoJoueur.insertJoueur("Sonne", "Loire", "region@example.com",FEMME , "JAPON", "lohlgsz", "444445j", 12);
        DaoJoueur.insertJoueur("Php", "MyAdmin", "cestnotre@example.com",FEMME , "ESPAGNE", "base", "dedonnees", 13);
        DaoJoueur.insertJoueur("Univers", "Reponse", "geek@example.com",FEMME , "JAPON", "michelle42", "42x101100", 14);
        DaoJoueur.insertJoueur("Joubert", "Aude", "math@example.com",FEMME , "JAPON", "prof", "admin", 15);
        DaoJoueur.insertJoueur("Rood", "Helle", "dfhzs@example.com",FEMME , "FRANCE", "jenest", "marre", 16);
        DaoJoueur.insertJoueur("Ces", "Table", "sonVraiment@example.com",FEMME , "ALLEMAGNE", "longue", "aremplir", 17);
        DaoJoueur.insertJoueur("Mais", "Bon", "aumoin@example.com",FEMME , "FRANCE", "saoccupe", "lesmains69", 18);
        DaoJoueur.insertJoueur("Toujours", "Pas", "didées@example.com",FEMME , "JAPON", "denom", "amettre", 19);
        DaoJoueur.insertJoueur("Doc", "Quinn", "doctissiomo@example.com",FEMME , "JAPON", "jesuis", "unbonmedecin", 20);
        DaoJoueur.insertJoueur("Maladie", "Lapeste", "moyenage@example.com",FEMME , "FRANCE", "oncherche", "encore", 21);
        DaoJoueur.insertJoueur("Marie", "Antoinette", "tete@example.com",FEMME , "JAPON", "guillotine", "vraimentpascool1793", 22);
        DaoJoueur.insertJoueur("Red", "Hood", "heroine@example.com",FEMME , "FRANCE", "memesi", "cestenfaitunhomme", 23);
        DaoJoueur.insertJoueur("Monroe", "Marilyn", "normajeanemortenson@example.com",FEMME , "ALLEMAGNE", "blonde", "maispasque1960", 24);
        DaoJoueur.insertJoueur("Pefez", "Geae", "fezbfzsjfedhsje@example.com",FEMME , "JAPON", "steel", "woman4444", 25);
        DaoJoueur.insertJoueur("Graar", "Maggie", "jjjjjjyyyyjjj@example.com",FEMME , "ESPAGNE", "input0", "output1", 26);
        DaoJoueur.insertJoueur("Holez", "Bda", "plop@example.com",FEMME , "ALLEMAGNE", "oups", "pasgrave", 27);
        DaoJoueur.insertJoueur("Koiiddz", "Grrra", "dgfcZ8986@example.com",FEMME , "ESPAGNE", "fallout", "gamu7447", 28);
        DaoJoueur.insertJoueur("Lopez", "Jennifer", "staracademie@example.com",FEMME , "ALLEMAGNE", "oubliee", "delatelerealite", 29);
        DaoJoueur.insertJoueur("Hhirfthrf", "Jffff", "complex@example.com",FEMME , "FRANCE", "number", "burt69", 30);
        DaoJoueur.insertJoueur("Steel", "Dong", "llllllddddkkkddd@example.com",FEMME , "FRANCE", "mince", "jairippe", 31);
        DaoJoueur.insertJoueur("Enfin", "Ladreniere", "maispas@example.com",FEMME , "ESPAGNE", "destoutes", "lestables", 32);
            */

        DaoArbitre.insertArbitre("Lang", "Baron", "CavaSripton@example.com", FEMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Moonswag", "Super", "java@example.com", HOMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Deslandres", "Véronique", "lirisCnrs@example.com", FEMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Chin", "Chang", "pasraciste@example.com", HOMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Rick", "Albert", "normal@example.com", HOMME, "Allemagne", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Pierre", "Aymeric", "aypierre@example.com", HOMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Yoda", "Maître", "petitmaispuissantjesuis@example.com", HOMME, "Dagobah", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Sparkle", "Twi", "poney@example.com",HOMME , "ESPAGNE", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("First", "Adam", "jardin00@example.com",HOMME , "FRANCE", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Simpson", "Bart", "Skate@example.com",HOMME , "AMERIQUE", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Moi", "Etmoi", "seul@example.com",HOMME , "FRANCE", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Yugi", "Josie", "lok@example.com",FEMME , "FRANCE", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Simpson", "Lisa", "example@example.com",FEMME , "AMERIQUE", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Noel", "Marry", "xmas@example.com",FEMME , "FRANCE", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Disney", "Minnie", "Moussedecafe@example.com",FEMME , "FRANCE",  ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Coca", "Zero", "calorie@example.com",FEMME , "FRANCE",  ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("R2", "D2", "petitrobotincomprit@example.com",FEMME , "FRANCE", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Mich", "Jaquie", "xxx@example.com",FEMME , "FRANCE", ARBITRE_CHAISE, 0, 0);

        DaoArbitre.insertArbitre("Tonini", "Spageti", "pattes@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("TTT", "AA", "TaTaT@example.com", HOMME, "Allemagne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Laroque", "Dolme", "villagedariege@example.com", HOMME, "Allemagne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Andorre", "Pasdelacase", "entrelespagneetlariege@example.com", HOMME, "Allemagne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Fff", "Ttt", "FrFrf@example.com", HOMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Calamity", "Camille", "blazblue@example.com", FEMME, "Pologne", ARBITRE_FILET, 0, 0);
        
        
        
        DaoArbitre.insertArbitre("Roko", "Basilic", "theorie@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Super", "Ordinateur", "faitavecdesminitel@example.com", FEMME, "Chine", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Princesse", "Hellena", "paysimaginaire@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Capitain", "Crochet", "tictac@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Mieux", "Vaut", "tardquejamais@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Castafolte", "Henry", "aboutrobot@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Le", "Renard", "levisiteurdupasse@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Aert", "Zyio", "Zalapfghj@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Mortal", "Kombat", "gamegame74@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Hello", "World", "...@example.com", FEMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Guy", "Humain", "ouais@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Pyramide", "Pharaon", "egypteenfrance@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Nice", "Janne", "Tarzan@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Gary", "Colman", "celebre45@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Attia", "Adam", "pedobear@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Huyn", "Christian", "manchotquiplonge@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Hawking", "Stephen", "hawking@example.com", HOMME, "Angleterre", ARBITRE_LIGNE, 0, 0);
        
        
        DaoArbitre.insertArbitre("Roko", "Basilic", "theorie@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Super", "Ordinateur", "faitavecdesminitel@example.com", FEMME, "Chine", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Princesse", "Hellena", "paysimaginaire@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Capitain", "Crochet", "tictac@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Lang", "Baron", "CavaSripton@example.com", FEMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Moonswag", "Super", "java@example.com", HOMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Deslandres", "Véronique", "lirisCnrs@example.com", FEMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Chin", "Chang", "pasraciste@example.com", HOMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Rick", "Albert", "normal@example.com", HOMME, "Allemagne", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Tonini", "Spageti", "pattes@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("TTT", "AA", "TaTaT@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Laroque", "Dolme", "villagedariege@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Andorre", "Pasdelacase", "entrelespagneetlariege@example.com", HOMME, "Allemagne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Fff", "Ttt", "FrFrf@example.com", HOMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Calamity", "Camille", "blazblue@example.com", FEMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Mieux", "Vaut", "tardquejamais@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Castafolte", "Henry", "aboutrobot@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        
        
        
        /*
        DaoArbitre.insertArbitre("Le", "Renard", "levisiteurdupasse@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        
        
        DaoArbitre.insertArbitre("Aert", "Zyio", "Zalapfghj@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Mortal", "Kombat", "gamegame74@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Hello", "World", "...@example.com", FEMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Guy", "Humain", "ouais@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Pyramide", "Pharaon", "egypteenfrance@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Nice", "Janne", "Tarzan@example.com", FEMME, "Japon", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Gary", "Colman", "celebre45@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Attia", "Adam", "pedobear@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Huyn", "Christian", "manchotquiplonge@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);

        
        
        DaoArbitre.insertArbitre("Aert", "Zyio", "Zalapfghj@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Mortal", "Kombat", "gamegame74@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Hello", "World", "...@example.com", FEMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Guy", "Humain", "ouais@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Pyramide", "Pharaon", "egypteenfrance@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Nice", "Janne", "Tarzan@example.com", FEMME, "Japon", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Gary", "Colman", "celebre45@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Attia", "Adam", "pedobear@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Huyn", "Christian", "manchotquiplonge@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Roko", "Basilic", "theorie@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Super", "Ordinateur", "faitavecdesminitel@example.com", FEMME, "Chine", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Princesse", "Hellena", "paysimaginaire@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Capitain", "Crochet", "tictac@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Lang", "Baron", "CavaSripton@example.com", FEMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Moonswag", "Super", "java@example.com", HOMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Deslandres", "Véronique", "lirisCnrs@example.com", FEMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Chin", "Chang", "pasraciste@example.com", HOMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Rick", "Albert", "normal@example.com", HOMME, "Allemagne", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Tonini", "Spageti", "pattes@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("TTT", "AA", "TaTaT@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Laroque", "Dolme", "villagedariege@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Andorre", "Pasdelacase", "entrelespagneetlariege@example.com", HOMME, "Allemagne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Fff", "Ttt", "FrFrf@example.com", HOMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Calamity", "Camille", "blazblue@example.com", FEMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Mieux", "Vaut", "tardquejamais@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Castafolte", "Henry", "aboutrobot@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Le", "Renard", "levisiteurdupasse@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Aert", "Zyio", "Zalapfghj@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Mortal", "Kombat", "gamegame74@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Hello", "World", "...@example.com", FEMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Guy", "Humain", "ouais@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Pyramide", "Pharaon", "egypteenfrance@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Nice", "Janne", "Tarzan@example.com", FEMME, "Japon", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Gary", "Colman", "celebre45@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Attia", "Adam", "pedobear@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Huyn", "Christian", "manchotquiplonge@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);

        DaoArbitre.insertArbitre("Roko", "Basilic", "theorie@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Super", "Ordinateur", "faitavecdesminitel@example.com", FEMME, "Chine", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Princesse", "Hellena", "paysimaginaire@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Capitain", "Crochet", "tictac@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Lang", "Baron", "CavaSripton@example.com", FEMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Moonswag", "Super", "java@example.com", HOMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Deslandres", "Véronique", "lirisCnrs@example.com", FEMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Chin", "Chang", "pasraciste@example.com", HOMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Rick", "Albert", "normal@example.com", HOMME, "Allemagne", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Tonini", "Spageti", "pattes@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("TTT", "AA", "TaTaT@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Laroque", "Dolme", "villagedariege@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Andorre", "Pasdelacase", "entrelespagneetlariege@example.com", HOMME, "Allemagne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Fff", "Ttt", "FrFrf@example.com", HOMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Calamity", "Camille", "blazblue@example.com", FEMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Mieux", "Vaut", "tardquejamais@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Castafolte", "Henry", "aboutrobot@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Le", "Renard", "levisiteurdupasse@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Aert", "Zyio", "Zalapfghj@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Mortal", "Kombat", "gamegame74@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Hello", "World", "...@example.com", FEMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Guy", "Humain", "ouais@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Pyramide", "Pharaon", "egypteenfrance@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Nice", "Janne", "Tarzan@example.com", FEMME, "Japon", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Gary", "Colman", "celebre45@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Attia", "Adam", "pedobear@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Huyn", "Christian", "manchotquiplonge@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Roll", "Rick", "nevergonnagiveyouup@example.com", HOMME, "Angleterre", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Poutine", "Vladislav", "weloverussia@example.com", HOMME, "URSS", ARBITRE_LIGNE, 0, 0);

        
        DaoArbitre.insertArbitre("Roko", "Basilic", "theorie@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Super", "Ordinateur", "faitavecdesminitel@example.com", FEMME, "Chine", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Princesse", "Hellena", "paysimaginaire@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Capitain", "Crochet", "tictac@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Lang", "Baron", "CavaSripton@example.com", FEMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Moonswag", "Super", "java@example.com", HOMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Deslandres", "Véronique", "lirisCnrs@example.com", FEMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Chin", "Chang", "pasraciste@example.com", HOMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Rick", "Albert", "normal@example.com", HOMME, "Allemagne", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Tonini", "Spageti", "pattes@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("TTT", "AA", "TaTaT@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Laroque", "Dolme", "villagedariege@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Andorre", "Pasdelacase", "entrelespagneetlariege@example.com", HOMME, "Allemagne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Fff", "Ttt", "FrFrf@example.com", HOMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Calamity", "Camille", "blazblue@example.com", FEMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Mieux", "Vaut", "tardquejamais@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Castafolte", "Henry", "aboutrobot@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Le", "Renard", "levisiteurdupasse@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Aert", "Zyio", "Zalapfghj@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Mortal", "Kombat", "gamegame74@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Hello", "World", "...@example.com", FEMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Guy", "Humain", "ouais@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Pyramide", "Pharaon", "egypteenfrance@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Nice", "Janne", "Tarzan@example.com", FEMME, "Japon", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Gary", "Colman", "celebre45@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Attia", "Adam", "pedobear@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Huyn", "Christian", "manchotquiplonge@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);

        DaoArbitre.insertArbitre("Roko", "Basilic", "theorie@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Super", "Ordinateur", "faitavecdesminitel@example.com", FEMME, "Chine", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Princesse", "Hellena", "paysimaginaire@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Capitain", "Crochet", "tictac@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Lang", "Baron", "CavaSripton@example.com", FEMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Moonswag", "Super", "java@example.com", HOMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Deslandres", "Véronique", "lirisCnrs@example.com", FEMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Chin", "Chang", "pasraciste@example.com", HOMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Rick", "Albert", "normal@example.com", HOMME, "Allemagne", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Tonini", "Spageti", "pattes@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("TTT", "AA", "TaTaT@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Laroque", "Dolme", "villagedariege@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Andorre", "Pasdelacase", "entrelespagneetlariege@example.com", HOMME, "Allemagne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Fff", "Ttt", "FrFrf@example.com", HOMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Calamity", "Camille", "blazblue@example.com", FEMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Mieux", "Vaut", "tardquejamais@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Castafolte", "Henry", "aboutrobot@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Le", "Renard", "levisiteurdupasse@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Aert", "Zyio", "Zalapfghj@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Mortal", "Kombat", "gamegame74@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Hello", "World", "...@example.com", FEMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Guy", "Humain", "ouais@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Pyramide", "Pharaon", "egypteenfrance@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Nice", "Janne", "Tarzan@example.com", FEMME, "Japon", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Gary", "Colman", "celebre45@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Attia", "Adam", "pedobear@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Huyn", "Christian", "manchotquiplonge@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);

        DaoArbitre.insertArbitre("Roko", "Basilic", "theorie@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Super", "Ordinateur", "faitavecdesminitel@example.com", FEMME, "Chine", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Princesse", "Hellena", "paysimaginaire@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Capitain", "Crochet", "tictac@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Lang", "Baron", "CavaSripton@example.com", FEMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Moonswag", "Super", "java@example.com", HOMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Deslandres", "Véronique", "lirisCnrs@example.com", FEMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Chin", "Chang", "pasraciste@example.com", HOMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Rick", "Albert", "normal@example.com", HOMME, "Allemagne", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Tonini", "Spageti", "pattes@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("TTT", "AA", "TaTaT@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Laroque", "Dolme", "villagedariege@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Andorre", "Pasdelacase", "entrelespagneetlariege@example.com", HOMME, "Allemagne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Fff", "Ttt", "FrFrf@example.com", HOMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Calamity", "Camille", "blazblue@example.com", FEMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Mieux", "Vaut", "tardquejamais@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Castafolte", "Henry", "aboutrobot@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Le", "Renard", "levisiteurdupasse@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Aert", "Zyio", "Zalapfghj@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Mortal", "Kombat", "gamegame74@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Hello", "World", "...@example.com", FEMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Guy", "Humain", "ouais@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Pyramide", "Pharaon", "egypteenfrance@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Nice", "Janne", "Tarzan@example.com", FEMME, "Japon", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Gary", "Colman", "celebre45@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Attia", "Adam", "pedobear@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Huyn", "Christian", "manchotquiplonge@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        
        DaoArbitre.insertArbitre("Roko", "Basilic", "theorie@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Super", "Ordinateur", "faitavecdesminitel@example.com", FEMME, "Chine", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Princesse", "Hellena", "paysimaginaire@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Capitain", "Crochet", "tictac@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Lang", "Baron", "CavaSripton@example.com", FEMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Moonswag", "Super", "java@example.com", HOMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Deslandres", "Véronique", "lirisCnrs@example.com", FEMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Chin", "Chang", "pasraciste@example.com", HOMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Rick", "Albert", "normal@example.com", HOMME, "Allemagne", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Tonini", "Spageti", "pattes@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("TTT", "AA", "TaTaT@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Laroque", "Dolme", "villagedariege@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Andorre", "Pasdelacase", "entrelespagneetlariege@example.com", HOMME, "Allemagne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Fff", "Ttt", "FrFrf@example.com", HOMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Calamity", "Camille", "blazblue@example.com", FEMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Mieux", "Vaut", "tardquejamais@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Castafolte", "Henry", "aboutrobot@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Le", "Renard", "levisiteurdupasse@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Aert", "Zyio", "Zalapfghj@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Mortal", "Kombat", "gamegame74@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Hello", "World", "...@example.com", FEMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Guy", "Humain", "ouais@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Pyramide", "Pharaon", "egypteenfrance@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Nice", "Janne", "Tarzan@example.com", FEMME, "Japon", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Gary", "Colman", "celebre45@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Attia", "Adam", "pedobear@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Huyn", "Christian", "manchotquiplonge@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);

        DaoArbitre.insertArbitre("Roko", "Basilic", "theorie@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Super", "Ordinateur", "faitavecdesminitel@example.com", FEMME, "Chine", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Princesse", "Hellena", "paysimaginaire@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Capitain", "Crochet", "tictac@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Lang", "Baron", "CavaSripton@example.com", FEMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Moonswag", "Super", "java@example.com", HOMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Deslandres", "Véronique", "lirisCnrs@example.com", FEMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Chin", "Chang", "pasraciste@example.com", HOMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Rick", "Albert", "normal@example.com", HOMME, "Allemagne", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Tonini", "Spageti", "pattes@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("TTT", "AA", "TaTaT@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Laroque", "Dolme", "villagedariege@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Andorre", "Pasdelacase", "entrelespagneetlariege@example.com", HOMME, "Allemagne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Fff", "Ttt", "FrFrf@example.com", HOMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Calamity", "Camille", "blazblue@example.com", FEMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Mieux", "Vaut", "tardquejamais@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Castafolte", "Henry", "aboutrobot@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Le", "Renard", "levisiteurdupasse@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Aert", "Zyio", "Zalapfghj@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Mortal", "Kombat", "gamegame74@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Hello", "World", "...@example.com", FEMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Guy", "Humain", "ouais@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Pyramide", "Pharaon", "egypteenfrance@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Nice", "Janne", "Tarzan@example.com", FEMME, "Japon", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Gary", "Colman", "celebre45@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Attia", "Adam", "pedobear@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Huyn", "Christian", "manchotquiplonge@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);

        DaoArbitre.insertArbitre("Roko", "Basilic", "theorie@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Super", "Ordinateur", "faitavecdesminitel@example.com", FEMME, "Chine", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Princesse", "Hellena", "paysimaginaire@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Capitain", "Crochet", "tictac@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Lang", "Baron", "CavaSripton@example.com", FEMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Moonswag", "Super", "java@example.com", HOMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Deslandres", "Véronique", "lirisCnrs@example.com", FEMME, "France", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Chin", "Chang", "pasraciste@example.com", HOMME, "Chine", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Rick", "Albert", "normal@example.com", HOMME, "Allemagne", ARBITRE_CHAISE, 0, 0);
        DaoArbitre.insertArbitre("Tonini", "Spageti", "pattes@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("TTT", "AA", "TaTaT@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Laroque", "Dolme", "villagedariege@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Andorre", "Pasdelacase", "entrelespagneetlariege@example.com", HOMME, "Allemagne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Fff", "Ttt", "FrFrf@example.com", HOMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Calamity", "Camille", "blazblue@example.com", FEMME, "Pologne", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Mieux", "Vaut", "tardquejamais@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Castafolte", "Henry", "aboutrobot@example.com", HOMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Le", "Renard", "levisiteurdupasse@example.com", FEMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Aert", "Zyio", "Zalapfghj@example.com", HOMME, "Allemagne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Mortal", "Kombat", "gamegame74@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Hello", "World", "...@example.com", FEMME, "Pologne", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Guy", "Humain", "ouais@example.com", HOMME, "Japon", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Pyramide", "Pharaon", "egypteenfrance@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Nice", "Janne", "Tarzan@example.com", FEMME, "Japon", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Gary", "Colman", "celebre45@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Attia", "Adam", "pedobear@example.com", HOMME, "France", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Huyn", "Christian", "manchotquiplonge@example.com", HOMME, "France", ARBITRE_FILET, 0, 0);
        DaoArbitre.insertArbitre("Roll", "Rick", "nevergonnagiveyouup@example.com", HOMME, "Angleterre", ARBITRE_LIGNE, 0, 0);
        DaoArbitre.insertArbitre("Poutine", "Vladislav", "weloverussia@example.com", HOMME, "URSS", ARBITRE_LIGNE, 0, 0);
            */
        
        
        // ramasseurs
        
        DaoRamasseur.insertRamasseur("Pedro", "Henry", "porto@example.com",HOMME , "Espagne");
        DaoRamasseur.insertRamasseur("Guzz", "Prod", "youtube@example.com",HOMME , "Japon");
        DaoRamasseur.insertRamasseur("Shia", "Labeouf", "justdoit@example.com",HOMME , "Corée");
        DaoRamasseur.insertRamasseur("Dream", "Come", "Trueornot@example.com",HOMME , "Allemagne");
        DaoRamasseur.insertRamasseur("Tsar", "Jasmine", "Aladin@example.com",FEMME , "France");
        DaoRamasseur.insertRamasseur("Super", "Nes", "retro@example.com",FEMME , "France");
        DaoRamasseur.insertRamasseur("Heartfilia", "Lucy", "fairytail@example.com",FEMME , "Japon");
        DaoRamasseur.insertRamasseur("Belle", "Mere", "blancheneige@example.com",FEMME , "Chine");
        DaoRamasseur.insertRamasseur("Ledernier", "Ramaseur", "estunefemme@example.com",FEMME , "Espagne");
        DaoRamasseur.insertRamasseur("truc", "machin", "truc@example.com", HOMME, "France");
        DaoRamasseur.insertRamasseur("Daniel", "Antoine", "wtc@example.com", FEMME, "Internet");
        DaoRamasseur.insertRamasseur("Melenchon", "Jean-Luc", "jlm@example.com", HOMME, "URSS");
        DaoRamasseur.insertRamasseur("Moulin", "Jean", "max@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("Mason", "Nick", "qzelf@example.com", FEMME, "Angleterre");
        DaoRamasseur.insertRamasseur("3003", "Pacificsound", "qzelf@example.com", FEMME, "Internet");
        DaoRamasseur.insertRamasseur("Crying", "Johnny", "drogue@example.com", HOMME, "Internet");
        DaoRamasseur.insertRamasseur("U2", "Bono", "u2@example.com", HOMME, "Irlande");
        DaoRamasseur.insertRamasseur("Zephyrr", "Minecraft", "peaceandredstone@example.com", HOMME, "Québec");
        
        DaoRamasseur.insertRamasseur("Pedro", "Henry", "porto@example.com",HOMME , "Espagne");
        DaoRamasseur.insertRamasseur("Guzz", "Prod", "youtube@example.com",HOMME , "Japon");
        DaoRamasseur.insertRamasseur("Shia", "Labeouf", "justedoit@example.com",HOMME , "Corée");
        DaoRamasseur.insertRamasseur("Dream", "Come", "Trueornot@example.com",HOMME , "Allemagne");
        DaoRamasseur.insertRamasseur("Tsar", "Jasmine", "Aladin@example.com",FEMME , "France");
        DaoRamasseur.insertRamasseur("Super", "Nes", "retro@example.com",FEMME , "France");
        DaoRamasseur.insertRamasseur("Heartfilia", "Lucy", "fairytail@example.com",FEMME , "Japon");
        DaoRamasseur.insertRamasseur("Belle", "Mere", "blancheneige@example.com",FEMME , "Chine");
        DaoRamasseur.insertRamasseur("Ledernier", "Ramaseur", "estunefemme@example.com",FEMME , "Espagne");
        DaoRamasseur.insertRamasseur("truc", "machin", "truc@example.com", HOMME, "France");
        DaoRamasseur.insertRamasseur("Daniel", "Antoine", "wtc@example.com", HOMME, "Internet");
        DaoRamasseur.insertRamasseur("Melenchon", "Jean-Luc", "jlm@example.com", HOMME, "URSS");
        DaoRamasseur.insertRamasseur("Moulin", "Jean", "max@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("Mason", "Nick", "qzelf@example.com", FEMME, "Angleterre");
        DaoRamasseur.insertRamasseur("3003", "Pacificsound", "qzelf@example.com", FEMME, "Internet");
        DaoRamasseur.insertRamasseur("Crying", "Johnny", "drogue@example.com", HOMME, "Internet");
        DaoRamasseur.insertRamasseur("U2", "Bono", "u2@example.com", HOMME, "Irlande");
        DaoRamasseur.insertRamasseur("Zephyrr", "Minecraft", "peaceandredstone@example.com", HOMME, "Québec");
        
        
        
        DaoRamasseur.insertRamasseur("Pedro", "Henry", "porto@example.com",HOMME , "Espagne");
        DaoRamasseur.insertRamasseur("Guzz", "Prod", "youtube@example.com",HOMME , "Japon");
        DaoRamasseur.insertRamasseur("Shia", "Labeouf", "justedoit@example.com",HOMME , "Corée");
        DaoRamasseur.insertRamasseur("Dream", "Come", "Trueornot@example.com",HOMME , "Allemagne");
        DaoRamasseur.insertRamasseur("Tsar", "Jasmine", "Aladin@example.com",FEMME , "France");
        DaoRamasseur.insertRamasseur("Super", "Nes", "retro@example.com",FEMME , "France");
        DaoRamasseur.insertRamasseur("Heartfilia", "Lucy", "fairytail@example.com",FEMME , "Japon");
        DaoRamasseur.insertRamasseur("Belle", "Mere", "blancheneige@example.com",FEMME , "Chine");
        DaoRamasseur.insertRamasseur("Ledernier", "Ramaseur", "estunefemme@example.com",FEMME , "Espagne");
        DaoRamasseur.insertRamasseur("truc", "machin", "truc@example.com", HOMME, "France");
        DaoRamasseur.insertRamasseur("Daniel", "Antoine", "wtc@example.com", HOMME, "Internet");
        DaoRamasseur.insertRamasseur("Melenchon", "Jean-Luc", "jlm@example.com", HOMME, "URSS");
        DaoRamasseur.insertRamasseur("Moulin", "Jean", "max@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("Mason", "Nick", "qzelf@example.com", FEMME, "Angleterre");
        DaoRamasseur.insertRamasseur("3003", "Pacificsound", "qzelf@example.com", FEMME, "Internet");
        DaoRamasseur.insertRamasseur("Crying", "Johnny", "drogue@example.com", HOMME, "Internet");
        DaoRamasseur.insertRamasseur("U2", "Bono", "u2@example.com", HOMME, "Irlande");
        DaoRamasseur.insertRamasseur("Zephyrr", "Minecraft", "peaceandredstone@example.com", HOMME, "Québec");
        
        
        DaoRamasseur.insertRamasseur("Pedro", "Henry", "porto@example.com",HOMME , "Espagne");
        DaoRamasseur.insertRamasseur("Guzz", "Prod", "youtube@example.com",HOMME , "Japon");
        DaoRamasseur.insertRamasseur("Shia", "Labeouf", "justedoit@example.com",HOMME , "Corée");
        DaoRamasseur.insertRamasseur("Dream", "Come", "Trueornot@example.com",HOMME , "Allemagne");
        DaoRamasseur.insertRamasseur("Tsar", "Jasmine", "Aladin@example.com",FEMME , "France");
        DaoRamasseur.insertRamasseur("Super", "Nes", "retro@example.com",FEMME , "France");
        DaoRamasseur.insertRamasseur("Heartfilia", "Lucy", "fairytail@example.com",FEMME , "Japon");
        DaoRamasseur.insertRamasseur("Belle", "Mere", "blancheneige@example.com",FEMME , "Chine");
        DaoRamasseur.insertRamasseur("Ledernier", "Ramaseur", "estunefemme@example.com",FEMME , "Espagne");
        DaoRamasseur.insertRamasseur("truc", "machin", "truc@example.com", HOMME, "France");
        DaoRamasseur.insertRamasseur("Daniel", "Antoine", "wtc@example.com", HOMME, "Internet");
        DaoRamasseur.insertRamasseur("Melenchon", "Jean-Luc", "jlm@example.com", HOMME, "URSS");
        DaoRamasseur.insertRamasseur("Moulin", "Jean", "max@example.com", FEMME, "Roumanie");
        DaoRamasseur.insertRamasseur("Mason", "Nick", "qzelf@example.com", FEMME, "Angleterre");
        DaoRamasseur.insertRamasseur("3003", "Pacificsound", "qzelf@example.com", FEMME, "Internet");
        DaoRamasseur.insertRamasseur("Crying", "Johnny", "drogue@example.com", HOMME, "Internet");
        DaoRamasseur.insertRamasseur("U2", "Bono", "u2@example.com", HOMME, "Irlande");
        DaoRamasseur.insertRamasseur("Zephyrr", "Minecraft", "peaceandredstone@example.com", HOMME, "Québec");
        
        DaoCourt.insertCourt(new CourtEntrainement(0, "Court entrainement 1", "adresse1", 2000));
        DaoCourt.insertCourt(new CourtEntrainement(0, "Court entrainement 2", "adresse2", 2000));
        DaoCourt.insertCourt(new CourtEntrainement(0, "Court entrainement 3", "adresse3", 2000));
        DaoCourt.insertCourt(new CourtEntrainement(0, "Court entrainement 4", "adresse4", 2000));
        
        
        System.out.println("Remplissage des tables terminé.");
        
    }
    
    public static void main(String[] args) {
        
        System.out.println("Hello, Miserable World.");
                
        //joueurs = new HashMap<>();
        
        //creerRemplirTables();
        
        
        joueurs = new ArrayList<>();
        /*
        joueurs = DaoJoueur.getJoueurs();
        
        for (Joueur j : joueurs) {
            System.out.println(j.getDetails());
        }*/
        /*
        try {
            planifierMatchs("simple", HOMME);
        } catch (Error ex) {
            Logger.getLogger(PlanningMatchs.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }

    
    public static void planifierMatchs(String typeTournoi, Sexe genre) throws Error {
        
        if (matchs == null)
            matchs = new HashMap<>();
        
        creneaux = DaoCreneau.getCreneaux();
        
        //List<Joueur> joueursRestants = joueurs;
        //DaoJoueur daoJoueur = new DaoJoueur();
        List<Joueur> joueursRestants = new ArrayList<Joueur>();
        joueursRestants = new ArrayList<>(DaoJoueur.getJoueurs().values());
        Joueur joueur1 = null, joueur2 = null;
        int i = joueursRestants.size();
        System.out.println(i + " joueurs recupérés (" + genre.toString() + ").");
        
        
        arbitres = DaoArbitre.getArbitres();
        System.out.println(arbitres.size() + " arbitres récupérés");
        
        
        ramasseurs = DaoRamasseur.getRamasseurs();
        System.out.println(ramasseurs.size() + " ramasseurs récupérés.");
        //System.out.println("Ramasseurs : " + ramasseurs);
        
        /*
        while (!(joueursRestants.size() > 2)) {

            
            
            
            Match match = new Match();
            //match = new Match(court, creneau, type, joueur1, joueur2, m_arbitreChaise, arbitreFilet, arbitresLigne, equipeRamasseurs);
            
            
        }*/
        
        
        for (Creneau creneau : creneaux) {
            System.out.println("créneau " + creneau + " " + creneau.getCourt().getType() + " day = " + creneau.getDateTime().getDayOfMonth());
            if (creneau.getCourt() instanceof CourtEntrainement) {
                if (creneau.getDateTime().getDayOfMonth() == jourTournoi) {
                    // Le premier jour du tournoi : on utilise aussi les courts d'entrainement
                    
                }
                else {
                    continue; // on n'utilise pas ce court d'entrainement
                }
            }
            
            
            System.out.println("Attribution pour créneau : " + creneau + ", " + creneau.getCourt() + " libre=" + creneau.estLibre());
            if (!creneau.estLibre())
                continue;
            
            //System.out.println("1");
            
            //if (joueursRestants.size() < 2)
            //    break;
            
            //System.out.println("2");
            
            
            joueur1 = getJoueurLibre(joueursRestants, creneau);
            if (joueur1 == null) {
                System.out.println("fin de la liste de joueurs.");
                break;
            }
            joueursRestants.remove(joueur1);
            
            joueur2 = getJoueurLibre(joueursRestants, creneau);
            if (joueur2 == null) {
                System.out.println("fin de la liste de joueurs.");
                break;
            }
            
            joueursRestants.remove(joueur1);
            joueursRestants.remove(joueur2);
            
            Match nouveauMatch = new Match(creneau, typeTournoi, genre, joueur1, joueur2);
            System.out.println("nouveauMatch : " + nouveauMatch);
            

            // assignation des arbitres
            
            ArbitreChaise arbitreChaise = null;
            boolean arbitreValide = true;
            
            for (Arbitre a : arbitres.values()) {
                if (a instanceof ArbitreChaise) {
                    arbitreValide = true;
                    System.out.print("ArbitreChaise " + a.getId() + " : ");
                    
                    for (Creneau c : creneaux) {
                        if (c.conflitCreneau(creneau) && !c.estLibre()) { 
                            if (c.getMatch().getArbitreChaise().getId() == a.getId()) {
                                System.out.println(" cet arbitre est déjà occupé.");
                                arbitreValide = false;
                                break;
                            }
                        }
                    }
                    if (!arbitreValide)
                        continue;
                    
                    if (a.peutArbitrer(nouveauMatch)) {
                        System.out.println("oui.");
                        arbitreChaise = (ArbitreChaise) a;
                        nouveauMatch.ajouterArbitre(arbitreChaise);
                        a.assigneMatchSimple();
                        break;
                    }
                    else {
                        System.out.println("non.");
                    }
                }
            }
            if (arbitreChaise == null) {
                //throw new Error("Pas assez d'arbitres de chaise disponibles.");
                System.out.println("Pas assez d'arbitres de chaise disponibles.");
                nouveauMatch = null;

                continue;
            }
            
            
            // arbitre de filet
            
            ArbitreFilet arbitreFilet = null;
            for (Arbitre af : arbitres.values()) {
                if (af instanceof ArbitreFilet) {
                    arbitreValide = true;
                    System.out.print("ArbitreFilet " + af.getId() + " : ");
                    //if (a.peutArbitrer(nouveauMatch)) {
                    
                    for (Creneau c : creneaux) {
                        if (c.conflitCreneau(creneau) && !c.estLibre()) { 
                            System.out.print(" compare avec " + c.description());
                            if (c.getMatch().getArbitreFilet().getId() == af.getId()) {
                                System.out.println(" cet arbitre est déjà occupé.");
                                arbitreValide = false;
                                break;
                            }
                        }
                    }
                    
                    if (!arbitreValide)
                        continue;
                    
                    System.out.println(" oui.");
                    arbitreFilet = (ArbitreFilet) af;
                    //a.assigneMatchSimple();
                    nouveauMatch.ajouterArbitre(arbitreFilet);
                    break;
                    //}
                    //else 
                    //    System.out.println("non.");
                }
            }
            if (arbitreFilet == null) {
                //throw new Error("Pas assez d'arbitres de filet disponibles.");
                System.out.println("Pas assez d'arbitres de filet disponibles.");

                nouveauMatch = null;
                continue;
            }
            
            
            
            // 8 arbitres de ligne
            Map<Integer, ArbitreLigne> arbitresLignes = new HashMap<>();
            int nbArbitresLigne = 0;
            for (Arbitre arbitre : arbitres.values()) {
                if (arbitre instanceof ArbitreLigne) {
                    arbitreValide = true;
                    System.out.print("ArbitreLigne " + arbitre.getId() + " : ");
                        for (Creneau c : creneaux) {
                            if (c.conflitCreneau(creneau) && !c.estLibre()) { 
                                if (c.getMatch().contient((ArbitreLigne) arbitre)) {
                                    System.out.println(" cet arbitre est déjà occupé.");
                                    arbitreValide = false;
                                    break;
                                }
                            }
                        }
                    
                        if (!arbitreValide)
                            continue;
                    
                        System.out.println(" oui.");
                        arbitresLignes.put(nbArbitresLigne, (ArbitreLigne) arbitre);
                        //arbitre.assigneMatchSimple();
                        //System.out.println("arbitre ajouté : " + arbitre.getId() + " - " + arbitresLignes.size() + " arbitres de ligne");
                        nbArbitresLigne++;
                        //System.out.println(nbArbitresLigne);
                        if (nbArbitresLigne == 8) 
                            break;
                }
                
            }
            if (nbArbitresLigne < 8) {
                //System.out.println("Pas assez d'arbitres de ligne disponibles (" + nbArbitresLigne + "/8 arbitres disponibles)");
                //throw new Error("Pas assez d'arbitres de ligne disponibles.");
                System.out.println("Pas assez d'arbitres de ligne disponibles.");

                nouveauMatch = null;
                continue;
            }

            nouveauMatch.setArbitresLigne(arbitresLignes);
            
            
            // Attribution des ramasseurs de balle  -  12 ramasseurs (2 equipes de 6)
            //  on les distribue au hasard pour éviter que ce soient toujours les mêmes
            
            EquipeRamasseurs equipeRamasseurs = new EquipeRamasseurs();
            Random rand = new Random();
            int n = ramasseurs.size();
            
            //HashMap<Integer, Ramasseur> ramasseursRestants = (HashMap<Integer, Ramasseur>) ramasseurs.clone();
            //List<Ramasseur> ramasseursRestants = DaoRamasseur.getRamasseurs();
            List<Ramasseur> ramasseursRestants = new ArrayList<>(ramasseurs);
            
            while (!equipeRamasseurs.estComplete()) {
                System.out.println("nbRamasseurs = " + equipeRamasseurs.getNbRamasseurs() + " Ramasseurs restants : " + ramasseursRestants.size());
                Ramasseur ramasseur = null;
                boolean ramasseurValide = false;
                int index = 0;
                while (!ramasseurValide && !ramasseursRestants.isEmpty()) {
                    index = rand.nextInt(ramasseursRestants.size());
                
                    ramasseur = ramasseurs.get(index);
                    ramasseurValide = true;
                    for (Creneau c : creneaux) {
                        if (c.conflitCreneau(creneau) && !c.estLibre()) {
                            // autre créneau au même moment
                            if (c.getMatch().getEquipeRamasseurs().contient(ramasseur)) {
                                // ce ramasseur participe à un autre match au même moment et donc n'est pas disponible.
                                System.out.println("Ramasseur " + ramasseur.getId() + " déjà sur match " + c.getMatch().getId() + " (" + c.getMatch().getEquipe1().toString() + "/" + c.getMatch().getEquipe2() + ")" );
                                ramasseurValide = false;
                                ramasseursRestants.remove(index);
                                break;
                            }
                        }
                    }
                    
                }
                if (ramasseursRestants.isEmpty()) {
                    // tous les ramasseurs ont été enlevés car non disponibles
                    //throw new Error("Pas assez de ramasseurs.");
                    
                    break;
                }
                
                System.out.println("Ramasseur " + ramasseur.getId() + " ok. (" + ramasseur.toString() + ")");
                
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
            if (ramasseursRestants.isEmpty()) {
                System.out.println("Pas assez de ramasseurs disponibles.");
                nouveauMatch = null;

                continue;
            }
            
            
            nouveauMatch.affecterEquipeRamasseurs(equipeRamasseurs);
            creneau.assigne(nouveauMatch);
            //matchs.put(nouveauMatch.getId(), nouveauMatch);
            
            DaoMatch.insertMatch(nouveauMatch);
            int id = nouveauMatch.getId();
            System.out.println("nouveauMatch (" + id + ") : " + nouveauMatch);
            matchs.put(id, nouveauMatch);
            //matchs.add(nouveauMatch);
            
            //nouveau Match : " + nouveauMatch
            
        } // creneaux
        
        System.out.println("Planification terminée.");
        
    }
    
    //public static Joueur getJoueurLibre(List<Joueur> joueursRestants, Creneau creneau) {
    public static Joueur getJoueurLibre(List<Joueur> joueursRestants, Creneau creneau) {
        return getJoueurLibre(joueursRestants, creneau, "");
    }
    
    public static Joueur getJoueurLibre(List<Joueur> joueursRestants, Creneau creneau, String nationalite) {
        
        if (joueursRestants.size() < 1)
            return null;
        Joueur joueur;
        boolean joueurValide = false;
        int i = joueursRestants.size()-1;
        System.out.println(" size = " + joueursRestants.size());
        do {
            
            joueur = joueursRestants.get(i);
            joueurValide = true;
            System.out.println("i=" + i + ", joueur : " + joueur.toString());
            
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
        } while (!joueurValide && joueursRestants.size() > 1);
        if (!joueurValide) {
            return null;
        }
        return joueur;
    }
    
    public static void recupererMatchs() {
        try {
            matchs = DaoMatch.getMatchs();
        } catch (Error ex) {
            Logger.getLogger(PlanningMatchs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ajouterResultatMatch(Match match, List<modele.Set> sets) throws Error {
        
        match.setScore(sets);
        
        EquipeJoueurs gagnant = match.getEquipeGagnante();
        
        int rangTournoi = gagnant.getJoueurA().getRangTournoi();
        
        switch (rangTournoi) {
            case 16:
                
                joueurs8emeDeFinale.add(gagnant);
                
                if (joueurs8emeDeFinale.size() == 2) {
                    // il y a deux joueurs, on peut creer un nouveau match de 8eme de finale
                }
                
                break;
            case 8:
                break;
            case 4:
                break;
            case 2:
                break;
            case 1:
                break;
            default:
                throw new Error("RangTournoi invalide : " + rangTournoi);
        }
        
        
    }
    
    
    public static boolean deplacerMatch(Match matchADeplacer, Creneau creneauCible) throws Error {
        if (!creneauCible.estLibre()) {
            //   ne devrait pas arriver car vérifié avant
            throw new Error("Le créneau cible n'est pas libre");
        }
        
        if (matchADeplacer.getCreneau().equals(creneauCible)) {
            throw new Error("Le match est déjà sur ce créneau");
        }
        
        matchADeplacer.setCreneau(creneauCible);
        creneauCible.assigne(matchADeplacer);
        return true;
    }
    
    
    
    
    public static int nbMatchs() {
        if (matchs == null)
            return 0;
        return matchs.size();
    }
    
    public static Match getMatch(int idMatch) {
        /*
        for (Match m : matchs) {
            if (idMatch == m.getId()) {
                return m;
            }
        }*/
        return matchs.get(idMatch);
    }
    
    public static HashMap<Integer, Match> getMatchs() {
        if (matchs != null)
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
