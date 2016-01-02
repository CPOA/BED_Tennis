/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.sgbd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Match;
import modele.personne.Joueur;
import modele.personne.Sexe;
import modele.personne.TypeVIP;
import static modele.sgbd.Dao.queryUpdate;

/**
 *
 * @author dave
 */
public class DaoMatch extends Dao{
    

    
    public static HashMap<Integer, Match> getMatchs(){
        
        
        HashMap<Integer, Match> matchs = new HashMap<>();
        
        connect();
        ResultSet res = query("Select id_match, creneau, type, genre, fini, sets, joueur_1_A, joueur_1_B, joueur_2_A, joueur_2_B, arbitre_chaise, arbitre_filet, arbitre_ligne_1, arbitre_ligne_2, arbitre_ligne_3, arbitre_ligne_4, arbitre_ligne_5, arbitre_ligne_6, arbitre_ligne_7, arbitre_ligne_8, ramasseur_1, ramasseur_2, ramasseur_3, ramasseur_4, ramasseur_5, ramasseur_6, ramasseur_7, ramasseur_8 from match");
        
        try {
            while (res.next()) {
                Match m = new Match(
                                    
                                    );
                matchs.put(res.getInt("id_match"), m);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoMatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return matchs;
    }
    
    
    
    public static int getMaxIdMatch() {
        
        int n = 0;
        try {
            connect();
            
            ResultSet res = query("Select count(*) from match");
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
        
        String stringSets = "";
        List<modele.Set> listSets = match.getScoreFinal();
        for (modele.Set set : listSets) {
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
        
        
        
        queryUpdate("Insert into match values ("
                                            + id + ", "
                                            + match.getCreneau().getId() + ", '"
                                            + match.getType() + "', '"
                                            + match.getGenre().toString() + "', '"
                                            + Boolean.toString(match.estFini()) + "', '"
                                            + stringSets + "', "
                                            + joueur_1_A + ", "
                                            + joueur_1_B + ", "
                                            + joueur_2_A + ", "
                                            + joueur_2_B + ", "
                                            + ")"
                                            );
        
        
        Dao.idMaxAttribue++;
    }
    
    public static void viderMatchs() {

        queryUpdate("DELETE from match");
        
        System.out.println("Table match vidée.");
        
    }
    
}
