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
import modele.court.Court;
import modele.court.CourtAnnexe;
import modele.court.CourtCentral;
import modele.court.CourtEntrainement;

/**
 *
 * @author dave
 */
public class DaoCourt extends Dao{
    
    /**
     * Récupère les courts d'entrainement dans la base de données, en plus des deux courts CourtCentral et CourtAnnexe.
     * @return 
     */
    public static List<Court> getCourts() {
        //HashMap<Integer, Court> courts = new HashMap<>();
        List<Court> courts = new ArrayList<>();
        
        // courts connus
        //courts.put(1, CourtCentral.getInstance());
        //courts.put(2, CourtAnnexe.getInstance());
        courts.add(CourtCentral.getInstance());
        courts.add(CourtAnnexe.getInstance());
        
        // les courts restants sont uniquement des CourtEntrainement, stockés dans la base
        
        connect();
        
        ResultSet res = query("Select id_court, nom, adresse, capacite from court");
        
        try {
            while (res.next()) {
                
                Court court = new CourtEntrainement(res.getInt("id_court"),
                                                    res.getString("nom"), 
                                                    res.getString("adresse"),
                                                    res.getInt("capacite"));
                    
                //courts.put(res.getInt("id_court"), court);
                courts.add(court);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCourt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return courts;
    }
    
    public static void insertCourt(CourtEntrainement court) {
       
        //int id = Dao.getIdMax() + 1;
        int id = Dao.idMaxAttribue+1;
        court.setId(id);
                
        queryUpdate("Insert into ramasseur values ("
                                            + id + ", "
                                            + "'" + court.getNom() + "', " 
                                            + "'" + court.getAdresse() + "', "
                                            + "'" + court.getCapacite() + "')");
        Dao.idMaxAttribue++;
    }
}
