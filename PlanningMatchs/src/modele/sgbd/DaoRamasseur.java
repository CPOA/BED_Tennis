/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.sgbd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Match;
import modele.personne.Ramasseur;
import modele.personne.Sexe;
import modele.arbitre.Arbitre;

/**
 *
 * @author dave
 */
public class DaoRamasseur extends Dao{
    
    private java.sql.Connection connexion;
    private Ramasseur ramasseur;

    public DaoRamasseur() {
        
    }
    
    
    public List<Ramasseur> getRamasseurs(){
        List<Ramasseur> ramasseurs = new ArrayList<>();
        /*
        try {
            System.out.print("Creating connexion...");
            
            //connexion = ConnexionOracleFactory.creerConnexion();
            connexion = ConnexionMySql.getConnexion();
            
            if (connexion == null) {
                System.exit(1);
            }
            System.out.println(" done.");
            
            java.sql.Statement requete;
            requete = connexion.createStatement();
            java.sql.ResultSet res = null;
            
            String query = "SELECT * from ramasseur";
            System.out.println("Query : " + query);
            System.out.print("Executing query...");
            
            System.out.println(" done.");
            
            while (res.next()) {
                
                    joueur = new Joueur(
                            res.getInt(1),      // id
                            res.getString(2),   // nom
                            res.getString(3),   // prenom
                            res.getString(4),   // mail
                            res.getInt(5),      // sexe
                            res.getString(6),   // nationalite
                            res.getString(7),   // login
                            res.getString(8),   // mdp
                            res.getInt(9)       // classementATP
                    );
                joueurList.add(joueur);
                
            }
            res.close();
            requete.close();
            connexion.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoArbitre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //return joueurList;*/
        
        connect();
        
        ResultSet res = query("Select id_ramasseur, nom, prenom, adressemail, sexe from ramasseur");
        
        try {
            while (res.next()) {
                
                Ramasseur ramasseur = new Ramasseur(
                                                    res.getInt("id_ramasseur"),
                                                    res.getString("nom"), 
                                                    res.getString("prenom"),
                                                    res.getString("adressemail"),
                                                    res.getObject("sexe", Sexe.class),
                                                    res.getString("nationalite"));
                ramasseurs.add(ramasseur);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoRamasseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ramasseurs;
    }
    
    
    
    public int getIdMax() {
        /*
        int n = 0;
        try {
            System.out.print("Creating connexion...");
            
            //connexion = ConnexionOracleFactory.creerConnexion();
            connexion = ConnexionMySql.getConnexion();
            
            if (connexion == null) {
                System.exit(1);
            }
            System.out.println(" done.");
            
            java.sql.Statement requete;
            requete = connexion.createStatement();
            java.sql.ResultSet res = null;
            
            String query = "SELECT max(id) FROM ramasseur";
            System.out.println("Query : " + query);
            System.out.print("Executing query...");
            
            res = requete.executeQuery(query);
            
            res.next();
            
            n = res.getInt(0);
            System.out.println(" done. n =  " + n);
           
            res.close();
            requete.close();
            connexion.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoMatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
        */
        
        int n = 0;
        
        return n;
    }
    
    
    
    public int getNbJoueurs() {
        /*
        try {
            System.out.print("Creating connexion...");
            
            //connexion = ConnexionOracleFactory.creerConnexion();
            connexion = ConnexionMySql.getConnexion();
            
            if (connexion == null) {
                System.exit(1);
            }
            System.out.println(" done.");
            
            java.sql.Statement requete;
            requete = connexion.createStatement();
            java.sql.ResultSet res = null;
            
            String query = "SELECT count(*)  FROM ramasseur";
            System.out.println("Query : " + query);
            System.out.print("Executing query...");
            res = requete.executeQuery(query);
            res.next();
            System.out.println(" done." + res);
            
            return res.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;*/
        
        int n = 0;
        
        return n;
    }
    
    
}
