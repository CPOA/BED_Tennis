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
    
    $title = "Réservations";
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
    
    //Tableau des réservation
    $content = $content . "<h3> Liste des réservations </h3>"
            ."<div id = \"tableau2\"><table>"
                . "<tr><td><h3>Nom</h3></td><td><h3> Prénom </h3></td><td><h3> Date de début </h3></td><td><h3> Date de fin </h3></td><td><h3> Nombre de personnes </h3></td></tr>";
    
    foreach ($reservations as $reservation){     
        $content = $content
                ."<tr><td>".$reservation[1]->getNom()."</td><td>".$reservation[1]->getPrenom()."</td><td>".$reservation[0]->getDateDebut()."</td><td>".$reservation[0]->getDateFin()."</td><td>".$reservation[0]->getNbPersonnes()."</td></tr>";
    }
    $content = $content."</table></div><br /><br />";
    
    require_once ("../../Vue/Layout.php");
?>
