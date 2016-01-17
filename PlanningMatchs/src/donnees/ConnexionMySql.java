
package donnees;

import java.sql.*;
import com.mysql.jdbc.Driver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author dave
 */
public class ConnexionMySql {
    
    private static Connection connexion = null;
    
    private static int creerConnexion() {
        
        try {
            java.io.FileInputStream fichier = new java.io.FileInputStream("./src/donnees/connexion_1.properties");
            java.util.Properties props;
            props = new java.util.Properties();
            props.load(fichier);
            
            // Library : MySQL JDBC Driver - mysql-connector-java-5.1.23-bin.jar
            
            connexion = DriverManager.getConnection("jdbc:mysql://iutdoua-webetu.univ-lyon1.fr/p1407734", "p1407734", "215859");
            
        } catch (IOException ex) {
            Logger.getLogger(ConnexionMySql.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static Connection getConnexion() {
        // if not created yet
        if (connexion == null) {
            creerConnexion();
        }
        return connexion;
    }
    
    
    
}
