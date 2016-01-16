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
import metier.personne.Joueur;
import metier.Match;
import metier.personne.Sexe;
import metier.personne.arbitre.Arbitre;
import metier.personne.arbitre.ArbitreChaise;
import metier.personne.arbitre.ArbitreFilet;
import metier.personne.arbitre.ArbitreLigne;
import metier.personne.arbitre.TypeArbitre;
import metier.personne.TypeVIP;
import static donnees.Dao.queryUpdate;

/**
 *
 * @author dave
 */
public class DaoArbitre extends Dao {
    
    
    public static HashMap<Integer, Arbitre> getArbitres(){
        HashMap<Integer, Arbitre> arbitres = new HashMap<>();
        
        ResultSet res = query("Select id_arbitre, nom, prenom, adressemail, sexe, nationalite, type_arbitre, nb_matchs_simples, nb_matchs_doubles from arbitre");
        
        try {
            
            while(res.next()) {
                int idArbitre = res.getInt("id_arbitre"); 
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                String mail = res.getString("adressemail");
                Sexe sexe = Sexe.valueOf(res.getString("sexe"));
                String nat = res.getString("nationalite");
                TypeArbitre typeArbitre = TypeArbitre.valueOf(res.getString("type_arbitre"));
                int nbMatchsSimples = res.getInt("nb_matchs_simples");
                int nbMatchsDoubles = res.getInt("nb_matchs_doubles");
                
                switch (typeArbitre) {
                    case ARBITRE_CHAISE:
                        arbitres.put(idArbitre, new ArbitreChaise(idArbitre, nom, prenom, mail, sexe, nat, nbMatchsSimples, nbMatchsDoubles));
                        break;
                    case ARBITRE_FILET:
                        arbitres.put(idArbitre, new ArbitreFilet(idArbitre, nom, prenom, mail, sexe, nat, nbMatchsSimples, nbMatchsDoubles));
                        break;
                    case ARBITRE_LIGNE:
                        arbitres.put(idArbitre, new ArbitreLigne(idArbitre, nom, prenom, mail, sexe, nat, nbMatchsSimples, nbMatchsDoubles));
                        break;
                    default:
                        throw new Error("Erreur : mauvais type d'arbitre");
                }
                //System.out.println("Arbitre récupéré : " + idArbitre);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arbitres;
    }
    
    
    
    public static int getMaxIdArbitre() {
        
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
    
    
    
    public static int getNbJoueurs() {
        
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
    
    public static void viderArbitres() {
        queryUpdate("DELETE from arbitre");
        queryUpdate("DELETE from vip WHERE typeVIP = 'ARBITRE'");
        
        System.out.println("Table arbitre vidée.");
    }
    
    
    public static void deleteArbitre(int idArbitre) {
        queryUpdate("DELETE from arbitre WHERE id_arbitre = " + idArbitre);
        queryUpdate("DELETE from vip WHERE id_vip = " + idArbitre);
    }
    
    public static void deleteArbitre(Arbitre a) {
        deleteArbitre(a.getId());
    }
    
    /**
     * Crée un nouvel arbitre dans la table joueur, l'insère également dans la table VIP,
     * Et instancie un nouvel objet Arbitre avec un id valide, puis retourne cet objet.
     * 
     * 
     */
    public static Arbitre insertArbitre(String nom, String prenom, String mail, Sexe sexe, String nationalite, TypeArbitre typeArbitre, int nbMatchsSimples, int nbMatchsDoubles) {
        
        //int id = Dao.getIdMax() + 1;
        int id = Dao.idMaxAttribue+1;
        Arbitre nouvelArbitre = null;
        
        switch (typeArbitre) {
            case ARBITRE_CHAISE:
                nouvelArbitre = new ArbitreChaise(id, nom, prenom, mail, sexe, nationalite, nbMatchsSimples, nbMatchsDoubles);
                break;
            case ARBITRE_FILET:
                nouvelArbitre = new ArbitreFilet(id, nom, prenom, mail, sexe, nationalite, nbMatchsSimples, nbMatchsDoubles);
                break;
            case ARBITRE_LIGNE:
                nouvelArbitre = new ArbitreLigne(id, nom, prenom, mail, sexe, nationalite, nbMatchsSimples, nbMatchsDoubles);
                break;
            default:
                throw new Error("Erreur : mauvais type d'arbitre");
        }
        
        queryUpdate("Insert into arbitre values ("
                                                + nouvelArbitre.getId() + ", "
                                                + "'" + nom + "', " 
                                                + "'" + prenom + "', "
                                                + "'" + mail + "', " 
                                                + "'" + sexe + "', "
                                                + "'" + nationalite + "', "
                                                + "'" + typeArbitre + "', " 
                                                + nbMatchsSimples + ", "
                                                + nbMatchsDoubles + ")");
        
        queryUpdate("Insert into vip values ("
                                            + nouvelArbitre.getId() + ", "
                                            + "'" + nouvelArbitre.getNom() + "', " 
                                            + "'" + nouvelArbitre.getPrenom() + "', "
                                            + "'" + TypeVIP.ARBITRE + "')");
        Dao.idMaxAttribue++;
        return nouvelArbitre;
    }

    public static void assignerMatchSimple(int idArbitre) {
        queryUpdate("UPDATE arbitre set nb_matchs_simples=nb_matchs_simples+1 WHERE id_arbitre=" + idArbitre);
    }

    

}
