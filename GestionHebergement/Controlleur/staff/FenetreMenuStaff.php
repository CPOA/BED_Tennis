<?php

    $title = "Menu Staff";

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


    $content = "<div id = \"bouton\"><a href = \"../../Controlleur/staff/FenetreAjoutHebergeant.php\">"
                    . "<h1> Ajouter Hébergeant </h1>"
                . "</a></div>"
                . "<br /><br />"
                . "<div id = \"bouton\"><a href = \"../../Controlleur/staff/FenetreReservation.php\">"
                    . "<h1> Effectuer Réservation </h1>"
                . "</a></div>"
                . "<br /><br />"
                . "<div id = \"bouton\"><a href = \"./FenetreConsulterHebergement.php\">"
                    . "<h1> Consulter liste hébergements </h1>"
                . "</a></div>"
               . "<br /><br />"
                ;
    

    require_once("../../Vue/Layout.php");
?>
