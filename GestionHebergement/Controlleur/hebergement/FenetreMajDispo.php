<?php

/*
 * Cette classe permet à l'hebergeant de consulter la liste des réservation
 * et de modifier la disponibilité de celui-ci.
 * 
 */
    require_once '../../Model/CompteHebergement.php';
    require_once '../../Model/Service.php';
    require_once '../../Model/Reservation.php';
    require_once '../../Model/VIP.php';
    
    $title = "Mise à jour disponibilité";
    $content = "";
            
    session_start();
    if (!isset($_SESSION['compte'])) {
      header("Location: ../FenetreConnexion.php");
    } else {
      $compte = $_SESSION['compte'];
      if(strcmp($_SESSION['compte']->getType(), "h")!=0) {
        header("Location: ../staff/FenetreMenuStaff.php");
      }
    }
    
    $reservations = Reservation::getListReservations($compte->getLogin()); // récupération de la liste des reservation
    $total=0;
    $table="";
    foreach ($reservations as $reservation){
        if(strcmp($reservation[0]->getDateDebut(),date("j/m/Y"))<=0 && strcmp($reservation[0]->getDateFin(),date("j/m/Y")>=0)) {
            $table = $table."<tr><td>".$reservation[1]->getNom()."</td><td>".$reservation[1]->getPrenom()."</td><td>".$reservation[0]->getDateDebut()."</td><td>".$reservation[0]->getDateFin()."</td><td>".$reservation[0]->getNbPersonnes()."</td></tr>";
            $total=$total+$reservation[0]->getNbPersonnes();
        }
    }
    $content = $content
            ."<div id = \"cadre2\"><form action=\"FenetreMajDispo.php?maj=1\" method=\"post\">"
                ."<div id =\"formulaire\">"
                    . "<label for = \"cap\">Capacité de l'hébergement :</label>"
                    . "<input type = \"number\" id =\"cap\" name =\"cap\" value=".$compte->getPlacesTotal()." disabled/><br /><br />"
                    . "<label for = \"act\">Disponibilités actuelles :</label>"
                    . "<input type = \"number\" id =\"act\" name =\"act\" value=".$compte->getPlacesDispo()." disabled/><br /><br />"
                    . "<label for = \"disp\">Nouvelles disponiblités pour le ".date("j/m/y")." :</label>"
                    . "<input type = \"number\" id =\"disp\" name =\"disp\" value=".($compte->getPlacesTotal()-$total)." /><br /><br /><br />"
                . "</div>"
            . "<h3> Liste des réservations pour le ".date("j/m/y")."</h3>";
    
    //Tableau des réservation
    $content = $content
            ."<div id = \"tableau2\"><table>"
                . "<tr><td><h3>Nom</h3></td><td><h3> Prénom </h3></td><td><h3> Date de début </h3></td><td><h3> Date de fin </h3></td><td><h3> Nombre de personnes </h3></td></tr>";
    $content=$content.$table;
    $content = $content."</table></div><br /><br />";
     $content=$content. "<input type =\"submit\" value = \"Mettre à jour\"/>"
    . "</form>"
    . "<form action=\"./FenetreMenuCompteHebergement.php\">"
    . "<input type=\"submit\" value=\"Annuler\">"
    . "</form><br />";
    if(isset($_GET["maj"])) {
        if(empty($_POST["disp"])) {
            $content=$content."<p class=\"erreur\">Veuillez renseigner une valeur</p>";
        }
        else {
            $compte->setPlacesDispo($_POST["disp"]);
            $compte->update();
            $_SESSION["compte"]=$compte;
            $content="<p>Disponibilités mise à jour</p>";
            $content = $content."<form action=\"./FenetreMajDispo.php\">"
                    . "<input type=\"submit\" value=\"Retour\">"
                    . "</form><br />";
        }
    }
   
    $content=$content."</div><br /></div>";

    require_once ("../../Vue/Layout.php");
?>
