package modele.sgbd;

import modele.personne.Joueur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.personne.Sexe;
import modele.personne.TypeVIP;

public class DaoJoueur extends Dao {
    //private java.sql.Connection connexion;
    private Joueur joueur;

    public DaoJoueur() {
    }
    /*
    public void openConnexion(javax.swing.DefaultListModel listModel)  {
        
        connexion = ConnexionOracleFactory.creerConnexion();
        if (connexion == null) {
            listModel.addElement("Probleme de connection.");
            System.exit(1);
        }
    }

    public void closeConnexion() {
      try {
        connexion.close();	// Fermeture de la connexion
      }catch (java.sql.SQLException e){
            System.out.println("ERREUR ORACLE" + e.getMessage());
      }
    }
    */
    public List<Joueur> getJoueurs(){
        List<Joueur> joueurs = new ArrayList<>();
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
            System.out.print("Executing query...");*/
            /*
            res = requete.executeQuery("select idjoueur,rpad(nomjoueur,20),rpad(prenomjoueur,20),rpad(adressemailjoueur,50),"
                    + "rpad(sexejoueur,10),rpad(nationalitejoueur,20),rpad(loginjoueur,20),"
                    + "rpad(mdpjoueur,20), classementatp from joueur");
            */
            /*
            res = requete.executeQuery("select idjoueur, nomjoueur, prenomjoueur, adressemailjoueur,"
                    + "sexejoueur, nationalitejoueur, loginjoueur,"
                    + "mdpjoueur, classementatp from joueur");
            */
            /*
            System.out.println(" done.");
            
            while (res.next()) {
                    joueur = new Joueur(
                            res.getInt(1),      // id
                            res.getString(2),   // nom
                            res.getString(3),   // prenom
                            res.getString(4),   // mail
                            Sexe.valueOf(res.getString(5).toUpperCase()),      // sexe
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
            //Logger.getLogger(DaoJoueur.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return joueurList;*/
        
        connect();
        ResultSet res = query("Select id_joueur, nom, prenom, adressemail, sexe, nationalite, login, mdp, classementatp from joueur");
        
        try {
            
            while(res.next()) {
                Joueur j = new Joueur(
                                      res.getInt("id_joueur"),   
                                      res.getString("nom"),
                                      res.getString("prenom"),
                                      res.getString("adressemail"),
                                      res.getObject("sexe", Sexe.class),
                                      res.getString("nationalite"),
                                      res.getString("login"),
                                      res.getString("mdp"),
                                      res.getInt("classementatp"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return joueurs;
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
            
            String query = "SELECT COUNT(*)  FROM joueur";
            System.out.println("Query : " + query);
            System.out.print("Executing query...");
            res = requete.executeQuery(query);
            res.next();
            System.out.println(" done." + res.getInt(1));
            
            return res.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
        */
        int n = 0;
        connect();
        ResultSet res = query("Select count(*) from joueur");
        try {
            res.next();
            n = res.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DaoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int getIdMax() {
        int n = 0;
        connect();
        ResultSet res = query("Select max(id_joueur) from joueur");
        try {
            res.next();
            n = res.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DaoArbitre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public void insertJoueur(Joueur joueur) {
        
        connect();
        
        queryUpdate("Insert into joueur values ("
                                            + joueur.getId() + ", "
                                            + "'" + joueur.getNom() + "', " 
                                            + "'" + joueur.getPrenom() + "', "
                                            + "'" + joueur.getAdresseMail() + "', " 
                                            + "'" + joueur.getSexe() + "', "
                                            + "'" + joueur.getNationalite() + "', "
                                            + "'" + joueur.getLogin() + "', " 
                                            + "'" + joueur.getMdP() + "', "
                                            + joueur.getClassementATP() + ")");
        
        // on n'a pas le droit de faire des triggers, on le fait donc comme ça...
        
        queryUpdate("Insert into vip values ("
                                            + joueur.getId() + ", "
                                            + "'" + joueur.getNom() + "', " 
                                            + "'" + joueur.getPrenom() + "', "
                                            + "'" + joueur.getAdresseMail() + "', " 
                                            + "'" + joueur.getSexe() + "', "
                                            + "'" + joueur.getNationalite() + ", " 
                                            + "'" + TypeVIP.JOUEUR + ")");
        
    }
    
    public void viderJoueurs() {
        connect();
        queryUpdate("DELETE from joueur");
    }
    
}
