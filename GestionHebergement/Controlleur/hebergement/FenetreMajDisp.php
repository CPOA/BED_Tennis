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
