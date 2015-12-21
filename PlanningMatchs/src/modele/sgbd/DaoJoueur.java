package modele.sgbd;

import modele.Joueur;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoJoueur {
    private java.sql.Connection connexion;
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
        List<Joueur> joueurList = new ArrayList<>();
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
            System.out.print("Executing query...");
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
            Logger.getLogger(DaoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return joueurList;
    }
}
