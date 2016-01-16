/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donnees;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Creneau;
import metier.EquipeJoueurs;
import metier.EquipeRamasseurs;
import metier.Error;
import metier.Match;
import metier.Set;
import metier.TrancheHoraire;
import metier.personne.arbitre.Arbitre;
import metier.personne.arbitre.ArbitreChaise;
import metier.personne.arbitre.ArbitreFilet;
import metier.personne.arbitre.ArbitreLigne;
import metier.court.Court;
import metier.personne.Joueur;
import metier.personne.Ramasseur;
import metier.personne.Sexe;
import metier.personne.TypeVIP;
import static donnees.Dao.queryUpdate;

/**
 *
 * @author dave
 */
public class DaoMatch extends Dao{
    
    private static HashMap<Integer, Match> matchs = null;
    
    private static boolean baseModifiee = true;
    
    public static HashMap<Integer, Match> getMatchs() throws Error{
        //HashMap<Integer, Match> matchs = new HashMap<>();
        
        if (baseModifiee == false) {
            return matchs;
        }
        else {  // la base a été modifiée, on va la rechercher
            matchs = new HashMap<>();
            

            connect();
            ResultSet res = query("SElect  "
                                        // colonnes du match, 
                                        + "match.id_match, "
                                        + "creneau, "
                                        + "type, "
                                        + "genre, "
                                        + "rang_tournoi, "
                                        + "fini, "
                                        + "sets, "
                                        + "joueur_1_A, "
                                        + "joueur_1_B, "
                                        + "joueur_2_A, "
                                        + "joueur_2_B, "
                                        + "arbitre_chaise, "
                                        + "arbitre_filet, "
                                        + "arbitres_ligne, "
                                        + "ramasseurs, "
                                        
                                          // colonnes du creneau
                                        + "id_court, "
                                        + "annee, "
                                        + "mois, "
                                        + "jour, "
                                        + "tranchehoraire, "
                                        + "libre "
                                        + "FROM `match`, creneau "
                                        + "WHERE match.creneau=creneau.id_creneau");

            HashMap<Integer, Arbitre> arbitres = DaoArbitre.getArbitres();
            List<Ramasseur> ramasseurs = DaoRamasseur.getRamasseurs();
            List<EquipeJoueurs> equipes = DaoJoueur.getEquipes("simple");
            HashMap<Integer, Joueur> joueurs = DaoJoueur.getJoueurs();
            //List<Creneau> creneaux = DaoCreneau.getCreneaux();
            HashMap<Integer, Court> courts = DaoCourt.getCourts();


            //printStackTrace();
            try {
                while (res.next()) {

                    int idMatch = res.getInt("id_match");

                    //  créneau
                    //Creneau creneau = null;
                    /*
                    int idCreneau = res.getInt("creneau");
                    for (Creneau c : creneaux) {
                        if (c.getId() == idCreneau) {
                            creneau = c;
                            break;
                        }
                    }
                    if (creneau == null)
                        throw new Error("Match n° " + idMatch + " : id creneau " + idCreneau + " n'existe pas");
                    */
                    boolean fini = res.getBoolean("fini");
                    //  sets
                    List<metier.Set> sets = new ArrayList<metier.Set>();
                    if (fini) {
                        String stringSets = res.getString("sets");
                        int i = 0;
                        for (String stringSet : stringSets.split(",")) {
                            //System.out.println("i=" + i + ", stringSet = " + stringSet + "- | " +  stringSet.split("-")[0]);
                            int pointsJoueur1 = new Integer(stringSet.split("-")[0]); //Integer.getInteger(stringSet.split("-")[0]);
                            int pointsJoueur2 = new Integer(stringSet.split("-")[1]);
                            Set set = new metier.Set(pointsJoueur1, pointsJoueur2);
                            sets.add(set);
                            i++;
                        }
                    }

                    //  joueurs


                    int id_equipe1_joueurA = res.getInt("joueur_1_A");
                    int id_equipe1_joueurB = res.getInt("joueur_1_B");
                    int id_equipe2_joueurA = res.getInt("joueur_2_A");
                    int id_equipe2_joueurB = res.getInt("joueur_2_B");

                    Joueur j_1_A = joueurs.get(id_equipe1_joueurA);
                    Joueur j_1_B = joueurs.get(id_equipe1_joueurB);
                    Joueur j_2_A = joueurs.get(id_equipe2_joueurA);
                    Joueur j_2_B = joueurs.get(id_equipe2_joueurB);

                    /*
                    for (Joueur k : ) {

                        if (j.getId() == id_equipe1_joueurA)
                            j_1_A = j;
                        if (j.getId() == id_equipe1_joueurB)
                            j_1_B = j;
                        if (j.getId() == id_equipe2_joueurA)
                            j_2_A = j;
                        if (j.getId() == id_equipe2_joueurB)
                            j_2_B = j;
                    }
                    */

                    EquipeJoueurs equipe1 = new EquipeJoueurs(j_1_A, j_1_B);
                    EquipeJoueurs equipe2 = new EquipeJoueurs(j_2_A, j_2_B);

                    //  arbitres 

                    Arbitre arbitreChaise = arbitres.get(res.getInt("arbitre_chaise"));
                    if (arbitreChaise == null) {
                        throw new Error("match n° " + idMatch + " : arbitre " + res.getInt("arbitre_chaise") + " n'existe pas");
                    }
                    else if (!(arbitreChaise instanceof ArbitreChaise)) {
                        throw new Error("match n° " + idMatch + " : arbitre " + res.getInt("arbitre_chaise") + " n'est pas un arbitre de chaise");
                    }

                    Arbitre arbitreFilet = arbitres.get(res.getInt("arbitre_filet"));
                    if (arbitreFilet == null) {
                        throw new Error("match n° " + idMatch + " : arbitre " + res.getInt("arbitre_filet") + " n'existe pas");
                    }
                    else if (!(arbitreFilet instanceof ArbitreFilet)) {
                        throw new Error("match n° " + idMatch + " : arbitre " + res.getInt("arbitre_filet") + " n'est pas un arbitre de filet");
                    }

                    Map<Integer, ArbitreLigne> arbitresLigne = new HashMap<>();
                    String stringArbitresLigne = res.getString("arbitres_ligne");

                    int ligne = 0;
                    for (String idArbitre : stringArbitresLigne.split("-")) {
                        Arbitre arbitre = arbitres.get(new Integer(idArbitre));
                        if (arbitre == null) {
                            throw new Error("id arbitre non trouvé : " + idArbitre + " pour match n°" + idMatch);
                        }
                        if (!(arbitre instanceof ArbitreLigne)) {
                            throw new Error("Match n° " + idMatch + " : id arbitre " + idArbitre + " n'est pas un arbitre de ligne");
                        }
                        arbitresLigne.put(ligne, (ArbitreLigne) arbitre);
                        //System.out.println("getMatchs() - match " + idMatch + " : " + arbitre);
                        ligne++;
                    }


                    String stringRamasseurs = res.getString("ramasseurs");
                    EquipeRamasseurs equipeRamasseurs = new EquipeRamasseurs();
                    for (String idRamasseur : stringRamasseurs.split("-")) {
                        Ramasseur ramasseur = null;
                        for (Ramasseur ram : ramasseurs) {
                            if ( (new Integer(idRamasseur)).equals(ram.getId())) {
                                ramasseur = ram;
                                break;
                            }
                        }
                        if (ramasseur == null) {
                            throw new Error("id ramasseur non trouvé : " + idRamasseur + "(match n°" + idMatch + ")");
                        }
                        equipeRamasseurs.ajouterRamasseur(ramasseur);

                    }

                    Match match = new Match(
                                        idMatch, 
                                        sets, 
                                        null,   // Temporairement
                                        res.getString("type"), 
                                        Sexe.valueOf(res.getString("genre")),
                                        fini, 
                                        equipe1, 
                                        equipe2, 
                                        (ArbitreChaise) arbitreChaise, 
                                        (ArbitreFilet) arbitreFilet, 
                                        arbitresLigne, 
                                        equipeRamasseurs,
                                        res.getInt("rang_tournoi"));

                    Creneau creneau = new Creneau(res.getInt("creneau"), 
                                          courts.get(res.getInt("id_court")),
                                          res.getInt("annee"),
                                          res.getInt("mois"),
                                          res.getInt("jour"),
                                          TrancheHoraire.valueOf(res.getString("tranchehoraire")),
                                          res.getBoolean("libre"),
                                          match);
                    match.setCreneau(creneau);
                    System.out.println("Match récupéré : " + match);
                    matchs.put(idMatch, match);
                }


            } catch (SQLException ex) {
                Logger.getLogger(DaoMatch.class.getName()).log(Level.SEVERE, null, ex);
            }

            baseModifiee = false;  // la HashMap matchs est maintenant une copie de la table matchs en base de données
            return matchs;
        }
    }
    
    
    public static int getMaxIdMatch() {
        
        int n = 0;
        try {
            connect();
            
            ResultSet res = query("Select max(id_match) from `match`");
            res.next();
            n = res.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoMatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public static void insertMatch(Match match) {
       
        //int id = Dao.getIdMax() + 1;
        int id = Dao.idMaxAttribue + 1;
        match.setIdMatch(id);
        
        
        String stringSets = " ";
        
        List<metier.Set> listSets = match.getScoreFinal();
        for (metier.Set set : listSets) {
            stringSets += set.getPointsJoueur1() + "-" + set.getPointsJoueur2() + "," ;
        }
        
        int joueur_1_A = match.getEquipe1().getJoueurA().getId();
        int joueur_1_B = 0;
        int joueur_2_A = match.getEquipe2().getJoueurA().getId();
        int joueur_2_B = 0;
        if (match.getType() == "double") {
            joueur_1_B = match.getEquipe1().getJoueurB().getId();
            joueur_2_B = match.getEquipe2().getJoueurB().getId();
        }
        
        String arbitresLigne = "";
        for (Entry<Integer, ArbitreLigne> e : match.getArbitresLigne().entrySet()) {
            arbitresLigne += e.getValue().getId() + "-";
        }
        
        String ramasseurs = "";
        for (Ramasseur r : match.getEquipeRamasseurs().getRamasseurs()) {
            ramasseurs += r.getId() + "-";
        }
        
        //queryUpdate("Insert into match values ('"
        queryUpdate("INSERT INTO `p1407734`.`match` (`id_match` ,`creneau` ,`type` ,`genre`, `rang_tournoi`, `fini` ,`sets` ,`joueur_1_A` ,`joueur_1_B` ,`joueur_2_A` ,`joueur_2_B` ,`arbitre_chaise` ,`arbitre_filet` ,`arbitres_ligne` ,`ramasseurs`) VALUES ("
                                            + id + ", "
                                            + match.getCreneau().getId() + ", '"            // créneau
                                            + match.getType() + "', '"                      // type
                                            + match.getGenre().toString() + "', "          // genre
                                            + match.getRangTournoi() + ", '"
                                            + Boolean.toString(match.estFini()) + "', '"    // fini
                                            + stringSets + "', "
                                            + joueur_1_A + ", "
                                            + joueur_1_B + ", "
                                            + joueur_2_A + ", "
                                            + joueur_2_B + ", "
                                            + match.getArbitreChaise().getId() + ", "
                                            + match.getArbitreFilet().getId() + ", '"
                                            + arbitresLigne + "', '"
                                            + ramasseurs + "');"
                                            );
        
        queryUpdate("UPDATE creneau SET libre = 'false', id_match=" + id + " WHERE id_creneau = " + match.getCreneau().getId());
        
        Dao.idMaxAttribue++;
        
        baseModifiee = true;
    }
    
    public static void deleteMatch(int idMatch) {
        queryUpdate("DELETE from match where id_match = " + idMatch);
        queryUpdate("UPDATE creneau SET libre = 'true', id_match=0 Where id_match = " + idMatch);
        baseModifiee = true;
    }
    
    public static void deleteMatch(Match match) {
        deleteMatch(match.getId());
    }
    
    public static void viderMatchs() {

        queryUpdate("DELETE from `match`");
        
        queryUpdate("UPDATE creneau SET libre = 'true', id_match=0 Where 1");
        
        queryUpdate("UPDATE arbitre SET nb_matchs_simples = 0, nb_matchs_doubles = 0 Where 1");
        
        queryUpdate("UPDATE joueur SET rang_tournoi = 16 Where 1");
        
        System.out.println("Table match vidée.");
        matchs = new HashMap<>();
        baseModifiee = true;
    }
    
    public static void modifierMatch(int idMatch, String property, String value) {
        
        queryUpdate("Update `match`"
                + "     SET " + property + " = " + value
                + "     Where id_match = " + idMatch);
        baseModifiee = true;
    }
}
