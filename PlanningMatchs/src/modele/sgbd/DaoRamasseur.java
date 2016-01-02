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
import modele.personne.Ramasseur;
import modele.personne.Sexe;
import modele.arbitre.Arbitre;
import modele.personne.Joueur;
import modele.personne.TypeVIP;
import static modele.sgbd.Dao.queryUpdate;

/**
 *
 * @author dave
 */
public class DaoRamasseur extends Dao{
    
    private java.sql.Connection connexion;
    private Ramasseur ramasseur;

    
    
    public static List<Ramasseur> getRamasseurs(){
        List<Ramasseur> ramasseurs = new ArrayList<Ramasseur>();
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
        
        
        ResultSet res = query("Select id_ramasseur, nom, prenom, adressemail, sexe, nationalite from ramasseur");
        
        try {
            while (res.next()) {
                
                Ramasseur ramasseur = new Ramasseur(
                                                    res.getInt("id_ramasseur"),
                                                    res.getString("nom"), 
                                                    res.getString("prenom"),
                                                    res.getString("adressemail"),
                                                    Sexe.valueOf(res.getString("sexe")),
                                                    res.getString("nationalite"));
                //ramasseurs.put(res.getInt("id_ramasseur"), ramasseur);
                ramasseurs.add(ramasseur);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoRamasseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ramasseurs;
    }
    
    
    
    public static int getMaxIdRamasseur() {
        
        int n = 0;
        
        ResultSet res = query("Select max(id_ramasseur) from ramasseur");
        
        try {
            res.next();
            n = res.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DaoRamasseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    
    
    public int getNbRamasseurs() {
        int n = 0;
        
        ResultSet res = query("Select count(id_ramasseur) from ramasseur");
        
        try {
            res.next();
            n = res.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DaoRamasseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    /**
     * Crée un nouveau joueur dans la table joueur, l'insère également dans la table VIP,
     * Et instancie un nouvel objet Joueur avec un id valide, puis retourne ce joueur.
     * 
     * 
     */
    public static Ramasseur insertRamasseur(String nom, String prenom, String adresseMail, Sexe sexe, String nationalite) {
       
        //int id = Dao.getIdMax() + 1;
        int id = Dao.idMaxAttribue+1;
        Ramasseur nouveauRamasseur = new Ramasseur(id, nom, prenom, adresseMail, sexe, nationalite);
        
        queryUpdate("Insert into ramasseur values ("
                                            + nouveauRamasseur.getId() + ", "
                                            + "'" + nouveauRamasseur.getNom() + "', " 
                                            + "'" + nouveauRamasseur.getPrenom() + "', "
                                            + "'" + nouveauRamasseur.getAdresseMail() + "', " 
                                            + "'" + nouveauRamasseur.getSexe() + "', "
                                            + "'" + nouveauRamasseur.getNationalite() + "')");
        Dao.idMaxAttribue++;
        return nouveauRamasseur;
    }
    
     public static void viderRamasseurs() {

        queryUpdate("DELETE from ramasseur");
        
        System.out.println("Table ramasseur vidée.");
        
    }
    
    public static void deleteRamasseur(int idRamasseur) {
        queryUpdate("DELETE from joueur WHERE id_ramasseur = " + idRamasseur);
    }
    
    public static void deleteRamasseur(Ramasseur r) {
        deleteRamasseur(r.getId());
    }
    
}
