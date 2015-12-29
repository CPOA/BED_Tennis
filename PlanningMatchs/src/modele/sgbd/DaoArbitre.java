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
import modele.personne.Joueur;
import modele.Match;
import modele.personne.Sexe;
import modele.arbitre.Arbitre;
import modele.arbitre.ArbitreChaise;
import modele.arbitre.ArbitreFilet;
import modele.arbitre.ArbitreLigne;
import modele.arbitre.TypeArbitre;

/**
 *
 * @author dave
 */
public class DaoArbitre extends Dao {
    
    private java.sql.Connection connexion;
    private Arbitre arbitre;

    public DaoArbitre() {
        
    }
    
    
    public List<Arbitre> getArbitres(){
        List<Arbitre> arbitres = new ArrayList<>();
        
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
            
            String query = "SELECT * from arbitre";
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
        */
        
        //query("Select * from arbitre");
        
        
        ResultSet res = query("Select id_joueur, nom, prenom, adressemail, sexe, nationalite, login, mdp, classementatp from joueur");
        
        try {
            
            while(res.next()) {
                int idArbitre = res.getInt("id_arbitre"); 
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                String mail = res.getString("adressemail");
                Sexe sexe = res.getObject("sexe", Sexe.class);
                String nat = res.getString("nationalite");
                TypeArbitre typeArbitre = TypeArbitre.valueOf(res.getString("typeArbitre"));
                int nbMatchsSimples = res.getInt("nb_matchs_simples");
                int nbMatchsDoubles = res.getInt("nb_matchs_doubles");
                
                switch (typeArbitre) {
                    case ARBITRE_CHAISE:
                        arbitres.add(new ArbitreChaise(idArbitre, nom, prenom, mail, sexe, nat, nbMatchsSimples, nbMatchsDoubles));
                    case ARBITRE_FILET:
                        arbitres.add(new ArbitreFilet(idArbitre, nom, prenom, mail, sexe, nat, nbMatchsSimples, nbMatchsDoubles));
                    case ARBITRE_LIGNE:
                        arbitres.add(new ArbitreLigne(idArbitre, nom, prenom, mail, sexe, nat, nbMatchsSimples, nbMatchsDoubles));
                    default:
                        throw new Error("Erreur : mauvais type d'arbitre");
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arbitres;
    }
    
    
    
    public int getIdMax() {
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
            
            String query = "SELECT max(idMatch) FROM match";
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
        */
        int n = 0;
        ResultSet res = query("Select max(id_arbitre) from arbitre");
        try {
            res.next();
            n = res.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DaoArbitre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
            
            String query = "SELECT count(*)  FROM arbitre";
            System.out.println("Query : " + query);
            System.out.print("Executing query...");
            res = requete.executeQuery(query);
            res.next();
            System.out.println(" done.");
            
            return res.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
        */
        
        int n = 0;
        ResultSet res = query("Select count(*) from arbitre");
        try {
            res.next();
            n = res.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DaoArbitre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
        
    }
    
}
