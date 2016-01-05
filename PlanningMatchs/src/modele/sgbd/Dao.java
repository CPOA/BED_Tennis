/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.sgbd;

import com.sun.corba.se.impl.util.Utility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.personne.Joueur;

/**
 *
 * @author dave
 */
public class Dao {
    
    private static java.sql.Connection connexion = null;

    
    protected static int idMaxAttribue = 0;
    
    public static void connect() {
        if (connexion == null) {
            try {
                System.out.print("Creating connexion...");

                //connexion = ConnexionOracleFactory.creerConnexion();
                connexion = ConnexionMySql.getConnexion();

                if (connexion == null) {
                    throw new Exception("connexion = null");
                }
                else {
                    System.out.println(" done.");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        
        idMaxAttribue = getIdMax();
        }
    }
    
    
    public static java.sql.ResultSet query(String queryText) {
        connect();
        java.sql.ResultSet res = null;
        
        try {
            java.sql.Statement statement;
            statement = connexion.createStatement();
            
            System.out.println("Query : " + queryText);
            System.out.print("  Executing query...");
            
            res = statement.executeQuery(queryText);
            
            System.out.println(" done.");
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public static int queryUpdate(String queryUpdate) {
        connect();
        int res = 0;
        
        try {
            java.sql.Statement statement;
            statement = connexion.createStatement();
            
            
            System.out.println("Update : " + queryUpdate);
            System.out.println("Executing update...");
            
            res = statement.executeUpdate(queryUpdate);
            
            System.out.println(" done.");
            
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public static int getIdMax() {
        
        int n = 0;
        
        int a = DaoArbitre.getMaxIdArbitre();
        int j = DaoJoueur.getMaxIdJoueur();
        int m = DaoMatch.getMaxIdMatch();
        int r = DaoRamasseur.getMaxIdRamasseur();
        int cr = DaoCreneau.getMaxIdCreneau();
        int co = DaoCourt.getMaxIdCourt();
        
        n = Math.max(a, j);
        n = Math.max(n, m);
        n = Math.max(n, r);
        n = Math.max(n, co);
        n = Math.max(n, cr);
        
        
        idMaxAttribue = n;
        
        System.out.println("Dao.idMaxAttribue = " + n);
        return n;
    }
    
}
