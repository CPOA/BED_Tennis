/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donnees;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Creneau;
import modele.Error;
import modele.Match;
import modele.TrancheHoraire;
import modele.court.Court;
import static donnees.Dao.query;

/**
 *
 * @author dave
 */
public class DaoCreneau extends Dao {
    
    public static List<Creneau> getCreneaux() {
        List<Creneau> creneaux = new ArrayList<Creneau>();
        
        connect();
        
        HashMap<Integer, Court> courts = DaoCourt.getCourts();

        
        ResultSet res = query("Select id_creneau, id_court, annee, mois, jour, tranchehoraire, libre, id_match from creneau");
        
        try {
            while(res.next()) {
                
                int idMatch = res.getInt("id_match");
                Match match = null;
                if (idMatch != 0)
                    match = DaoMatch.getMatchs().get(idMatch);
                Creneau c = new Creneau(res.getInt("id_creneau"),
                                        courts.get(res.getInt("id_court")),
                                        res.getInt("annee"),
                                        res.getInt("mois"),
                                        res.getInt("jour"),
                                        TrancheHoraire.valueOf(res.getString("tranchehoraire")),
                                        res.getBoolean("libre"),
                                        match);
                System.out.println("Créneau récupéré : " + c.toString() + ", " + c.estLibre());
                creneaux.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCreneau.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Error ex) {
            Logger.getLogger(DaoCreneau.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return creneaux;
    }
    
    public static int getMaxIdCreneau() {
        
        int n = 0;
        ResultSet res = query("Select max(id_creneau) from creneau");
        try {
            res.next();
            n = res.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DaoArbitre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    
    public static List<Creneau> getCreneauxLibres() {
        List<Creneau> creneaux = new ArrayList<Creneau>();
        
        connect();
        
        HashMap<Integer, Court> courts = DaoCourt.getCourts();

        
        ResultSet res = query("Select id_creneau, id_court, annee, mois, jour, tranchehoraire, libre, id_match from creneau WHERE libre='true'");
        
        try {
            while(res.next()) {
                
                int idMatch = res.getInt("id_match");
                Match match = null;
                if (idMatch != 0)
                    match = DaoMatch.getMatchs().get(idMatch);
                Creneau c = new Creneau(res.getInt("id_creneau"),
                                        courts.get(res.getInt("id_court")),
                                        res.getInt("annee"),
                                        res.getInt("mois"),
                                        res.getInt("jour"),
                                        TrancheHoraire.valueOf(res.getString("tranchehoraire")),
                                        res.getBoolean("libre"),
                                        match);
                System.out.println("Créneau récupéré : " + c.toString() + ", " + c.estLibre());
                creneaux.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCreneau.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Error ex) {
            Logger.getLogger(DaoCreneau.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return creneaux;
    }
    
     public static Creneau getPremierCreneauLibre() {
        Creneau creneau = null;
        
        connect();
        
        HashMap<Integer, Court> courts = DaoCourt.getCourts();

        
        ResultSet res = query("Select id_creneau, id_court, annee, mois, jour, tranchehoraire, libre, id_match from creneau WHERE libre='true' ORDER BY annee, mois, jour");
        
        try {
            if (res.next()) {
                
                int idMatch = res.getInt("id_match");
                Match match = null;
                if (idMatch != 0)
                    match = DaoMatch.getMatchs().get(idMatch);
                creneau = new Creneau(res.getInt("id_creneau"),
                                        courts.get(res.getInt("id_court")),
                                        res.getInt("annee"),
                                        res.getInt("mois"),
                                        res.getInt("jour"),
                                        TrancheHoraire.valueOf(res.getString("tranchehoraire")),
                                        res.getBoolean("libre"),
                                        match);
                System.out.println("Créneau récupéré : " + creneau.toString() + ", " + creneau.estLibre());
            }
        } catch (SQLException | Error ex) {
            Logger.getLogger(DaoCreneau.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return creneau;
    }
    
    
    public static void insertCreneau(Creneau creneau) {
       
        //int id = Dao.getIdMax() + 1;
        int id = Dao.idMaxAttribue+1;
        creneau.setId(id);
        
        int idMatch = 0;
        if (creneau.getMatch() != null) {
            idMatch = creneau.getMatch().getId();
        }
        queryUpdate("Insert into creneau values ("
                                                    + creneau.getId() + ", "
                                                    + creneau.getCourt().getId() + ", "
                                                    + creneau.getDateTime().year().get() + ", "
                                                    + creneau.getDateTime().monthOfYear().get() + ", "
                                                    + creneau.getDateTime().dayOfMonth().get() + ", '"
                                                    + creneau.getTrancheHoraire().toString() + "', '"
                                                    + Boolean.toString(creneau.estLibre()) + "', "
                                                    + idMatch + ")");
        Dao.idMaxAttribue++;
    }

    
    public static void viderCreneaux() {
        queryUpdate("DELETE from creneau");   
        System.out.println("Table creneau vidée.");
    }
    
}
