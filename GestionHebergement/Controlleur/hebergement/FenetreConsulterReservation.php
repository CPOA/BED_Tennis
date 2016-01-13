<?php
    require_once '../../Model/CompteHebergement.php';
    require_once '../../Model/Service.php';
    require_once '../../Model/Reservation.php';
    require_once '../../Model/VIP.php';
    
    $title="Consulter Réservation";
    $content="";
            
    session_start();
    if (!isset($_SESSION['compte'])) {
      header("Location: ../FenetreConnexion.php");
    } else {
      $compte=$_SESSION['compte'];
      if(strcmp($_SESSION['compte']->getType(), "h")!=0) {
        header("Location: ../staff/FenetreMenuStaff.php");
      }
    }
    
    $reservations=Reservation::getListReservations($compte->getLogin());
    $content=$content."<table><tr><th>Nom</th><th>Prénom</th><th>Date de début</th><th>Date de fin</th><th>Nombre de personnes</th></tr>";
    foreach ($reservations as $reservation){     
        $content=$content."<tr><td>".$reservation[1]->getNom()."</td><td>".$reservation[1]->getPrenom()."</td><td>".$reservation[0]->getDateDebut()."</td><td>".$reservation[0]->getDateFin()."</td><td>".$reservation[0]->getNbPersonnes()."</td><td>";
    }
    $content=$content."</table>";
    
    require_once ("../../Vue/Layout.php");
?>
