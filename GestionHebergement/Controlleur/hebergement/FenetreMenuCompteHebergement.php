<?php
    $title = "Menu hébergeant";
    $content =  
            
                     "<div id = \"bouton\"><a href = \"\">"
                        . "<h1> Mettre à jour informations </h1></a>"
                    . "</div>"
                    . "<br /><br />"
                    . "<div id = \"bouton\"><a href = \"\">"
                        . "<h1> Mettre à jour disponibilité </h1>"
                    . "</a></div>"
                    . "<br /><br />"
                    . "<div id = \"bouton\"><a href = \"\">"
                        . "<h1> Consulter la liste des réservations </h1>"
                    . "</a></div>"
                    . "<br /><br />"
                ;

    require_once '../../Model/CompteHebergement.php';
    require_once '../../Model/Service.php';
    session_start();
    if (!isset($_SESSION['compte'])) {
      header("Location: ../FenetreConnexion.php");
    } else {
      $compte=$_SESSION['compte'];
      if(strcmp($_SESSION['compte']->getType(), "h")!=0) {
        header("Location: ../staff/FenetreMenuStaff.php");
      }
    }
    
    require_once ("../../Vue/Layout.php");
