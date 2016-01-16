package donnees;

import metier.personne.Joueur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.EquipeJoueurs;
import metier.personne.Sexe;
import metier.personne.TypeVIP;

public class DaoJoueur extends Dao {

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
    
    public static HashMap<Integer, Joueur> getJoueurs(){
        //List<Joueur> joueurs = new ArrayList<>();
        HashMap<Integer, Joueur> joueurs = new HashMap<>();
        /*String condition = "";
        if (genre != null) {
            condition = " WHERE sexe='" + genre.toString() + "'";
        }*/
        ResultSet res = query("Select id_joueur, nom, prenom, adressemail, sexe, nationalite, login, mdp, classementatp, rang_tournoi from joueur");
        
        try {
            
            while(res.next()) {
                Joueur j = new Joueur(
                                      res.getInt("id_joueur"),   
                                      res.getString("nom"),
                                      res.getString("prenom"),
                                      res.getString("adressemail"),
                                      Sexe.valueOf(res.getString("sexe")),
                                      res.getString("nationalite"),
                                      res.getString("login"),
                                      res.getString("mdp"),
                                      res.getInt("classementatp"),
                                      res.getInt("rang_tournoi"));
                joueurs.put(res.getInt("id_joueur"), j);
                //joueurs.add(j);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return joueurs;
    }
    
    /*
    public static List<Joueur> getJoueurs() {
        return getJoueurs(null);
    }*/
    
    public static List<EquipeJoueurs> getEquipes(String typeTournoi, int rangTournoi){
        List<EquipeJoueurs> equipes = new ArrayList<>();
        String condition = "";
        
        if (rangTournoi != 0) {
            condition = " WHERE rang_tournoi = " + rangTournoi;
        }
        ResultSet res = query("Select id_joueur, nom, prenom, adressemail, sexe, nationalite, login, mdp, classementatp, rang_tournoi from joueur " + condition);
        
        EquipeJoueurs equipe = new EquipeJoueurs();
        
        try {
            int pos = 1;
            
            while(res.next()) {
                Joueur j = new Joueur(
                                      res.getInt("id_joueur"),   
                                      res.getString("nom"),
                                      res.getString("prenom"),
                                      res.getString("adressemail"),
                                      Sexe.valueOf(res.getString("sexe")),
                                      res.getString("nationalite"),
                                      res.getString("login"),
                                      res.getString("mdp"),
                                      res.getInt("classementatp"),
                                      res.getInt("rang_tournoi"));
                //joueurs.put(res.getInt("id_joueur"), j);
                
                if (typeTournoi == "simple") {
                    equipe = new EquipeJoueurs(j);
                    equipes.add(equipe);
                }
                else {  // double
                    if (pos == 1) {
                        equipe = new EquipeJoueurs(j);
                        pos = 2;
                    }
                    else { // pos == 2
                        equipe.ajouterJoueur(j);
                        equipes.add(equipe);
                        pos = 1;
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipes;
    }
    
    
    public static int getNbJoueurs() {
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
        ResultSet res = query("Select count(*) from joueur");
        try {
            res.next();
            n = res.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DaoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public static List<EquipeJoueurs> getEquipes(String typeTournoi) {
        return getEquipes(typeTournoi, 0);
    }
    
    public static int getMaxIdJoueur() {
        int n = 0;
        ResultSet res = query("Select max(id_joueur) from joueur");
        try {
            res.next();
            n = res.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DaoArbitre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    /**
     * Crée un nouveau joueur dans la table joueur, l'insère également dans la table VIP,
     * Et instancie un nouvel objet Joueur avec un id valide, puis retourne ce joueur.
     * 
     * 
     */
    
    public static Joueur insertJoueur(String nom, String prenom, String adresseMail, Sexe sexe, String nationalite, String login, String motDePasse, int classementATP, int rangTournoi) {
       
        //int id = Dao.getIdMax() + 1;
        int id = Dao.idMaxAttribue + 1;
        Joueur nouveauJoueur = new Joueur(id, nom, prenom, login, sexe, nationalite, login, motDePasse, classementATP, rangTournoi);
        
        queryUpdate("Insert into joueur values ("
                                            + nouveauJoueur.getId() + ", "
                                            + "'" + nouveauJoueur.getNom() + "', " 
                                            + "'" + nouveauJoueur.getPrenom() + "', "
                                            + "'" + nouveauJoueur.getAdresseMail() + "', " 
                                            + "'" + nouveauJoueur.getSexe() + "', "
                                            + "'" + nouveauJoueur.getNationalite() + "', "
                                            + "'" + nouveauJoueur.getLogin() + "', " 
                                            + "'" + nouveauJoueur.getMdP() + "', "
                                            + nouveauJoueur.getClassementATP() + ", "
                                            + nouveauJoueur.getRangTournoi() + ")");
        
        // on n'a pas le droit de faire des triggers, on le fait donc comme ça...
        
        queryUpdate("Insert into vip values ("
                                            + nouveauJoueur.getId() + ", "
                                            + "'" + nouveauJoueur.getNom() + "', " 
                                            + "'" + nouveauJoueur.getPrenom() + "', "
                                            //+ "'" + joueur.getAdresseMail() + "', " 
                                            //+ "'" + joueur.getSexe() + "', "
                                            //+ "'" + joueur.getNationalite() + "', " 
                                            + "'" + TypeVIP.JOUEUR + "')");
        Dao.idMaxAttribue++;
        return nouveauJoueur;
    }
    
    
    
    public static void updateJoueurRangTournoi(Joueur joueur, int rangTournoi) {
        queryUpdate("UPDATE joueur"
                + "     SET rang_tournoi = " + rangTournoi + ""
                + "     WHERE joueur.id_joueur = " + joueur.getId());    
    }
        
    public static void viderJoueurs() {

        queryUpdate("DELETE from joueur");
        queryUpdate("DELETE from vip WHERE typeVIP = 'JOUEUR'");
        
        System.out.println("Table joueur vidée.");
        
    }
    
    public static void deleteJoueur(int idJoueur) {
        queryUpdate("DELETE from joueur WHERE id_joueur = " + idJoueur);
        queryUpdate("DELETE from vip WHERE id_vip = " + idJoueur);
    }
    
    public static void deleteJoueur(Joueur j) {
        deleteJoueur(j.getId());
    }
    
}
