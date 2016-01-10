package donnees;

        
public class ConnexionOracleFactory {
    
public static java.sql.Connection creerConnexion(){
    try {
        java.util.Properties props;
        props = new java.util.Properties();
        java.io.FileInputStream fichier;
        //fichier = new java.io.FileInputStream("./src/connexion/connexion_1.properties");
        fichier = new java.io.FileInputStream("./src/donnees/connexion_1.properties");
        props.load(fichier);
        oracle.jdbc.pool.OracleDataSource ods;
        
        ods = new oracle.jdbc.pool.OracleDataSource();
        
        
        ods.setDriverType(props.getProperty("pilote"));
        ods.setPortNumber(Integer.parseInt(props.getProperty("port")));
        ods.setDatabaseName(props.getProperty("service"));
        ods.setUser(props.getProperty("user"));
        ods.setPassword(props.getProperty("pwd"));
        ods.setServerName(props.getProperty("serveur"));
       return(ods.getConnection());
    }catch (java.sql.SQLException | java.io.IOException | NumberFormatException e){
        System.out.println("Erreur lors de la lecture du fichier de configuration : " + e.getMessage());
        return null;
    }
 } 
 
 private static java.sql.Connection connexion;
 
}// fin ConnexionOracleFactory
