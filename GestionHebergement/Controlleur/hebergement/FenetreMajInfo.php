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
    $title = "";
    
    $content = "";
    
    require_once '../../Model/CompteHebergement.php';
    $compte->setNom($_POST['nom']);
    $compte->setTypeHebergement($_POST['typeHebergement']);
    $compte->setAdresse($_POST['adresse']);
    $compte->setNbEtoile($_POST['nbEtoile']);
    $compte->setPlaceDispo($_POST['placesDispo']);
    foreach($_POST['service'] as $service) {
        $compte->addService($service);
    }
    $compte->update();
    $_SESSION['compte']=$compte;
    require_once("../../Vue/Layout.php");
?>
