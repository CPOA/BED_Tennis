<?php
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

    $compte->setPlacesDispos($nbr);
    $compte->update();
    $_SESSION['compte']=$compte;
    
    $reservations=Reservation::getListReservations($compte->getLogin());
    $content=$content."<table><tr><th>Nom</th><th>Prénom</th><th>Date de début</th><th>Date de fin</th><th>Nombre de personnes</th></tr>";
    foreach ($reservations as $reservation){     
        $content=$content."<tr><td>".$reservation[1]->getNom()."</td><td>".$reservation[1]->getPrenom()."</td><td>".$reservation[0]->getDateDebut()."</td><td>".$reservation[0]->getDateFin()."</td><td>".$reservation[0]->getNbPersonnes()."</td><td>";
    }
    $content=$content."</table>";
