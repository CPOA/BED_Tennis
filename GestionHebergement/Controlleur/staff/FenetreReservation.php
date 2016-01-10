<?php
    require_once '../../Model/CompteStaff.php';
    require_once '../../Model/Service.php';
    session_start();
    
    if (!isset($_SESSION['compte'])) {
        header("Location: ../FenetreConnexion.php");
    } else {
        $compte=$_SESSION['compte'];
        if(strcmp($_SESSION['compte']->getType(), "s")!=0) {
            header("Location: ../hebergement/FenetreMenuCompteHebergement.php");
        }
    }
    $title = "Création d'une réservation";
    
    $content =  "<form>"
                    . "<label for = \"Nom\"> Nom </label>"
                    . "<input type = \"text\" name = \"Nom\" id =\"Nom\" /><br />"
                ."</form>";
    
    require_once("../../Vue/Layout.php");
?>

