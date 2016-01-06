<?php
    $title = "";
    
    $content = "";
    
    require_once '../../Model/CompteHebergement.php';
    $compte = unserialize(stripslashes(urldecode($_GET['compte'])));
    $compte->setNom($_POST['nom']);
    $compte->setTypeHebergement($_POST['typeHebergement']);
    $compte->setAdresse($_POST['adresse']);
    $compte->setNbEtoile($_POST['nbEtoile']);
    $compte->setPlaceDispo($_POST['placesDispo']);
    foreach($_POST['service'] as $service) {
        $compte->addService($service);
    }
    $compte->update();
    require_once("../../Vue/Layout.php");
?>