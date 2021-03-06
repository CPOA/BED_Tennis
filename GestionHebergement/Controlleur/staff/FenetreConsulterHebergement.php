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
    
    $title = "Liste des Hebergements";
    $content = "";
    require_once '../../Model/CompteHebergement.php';
    
    $hotels = CompteHebergement::getListHotels();
    $content = $content."<div id = \"tableau\"><table><tr><th>Nom</th><th>Adresse</th><th>Type d'hébergement</th><th>Nombre d'étoiles</th><th>Type VIP</th><th>Services</th><th>Places disponibles</th></tr>";
    foreach ($hotels as $hotel){
        $content = $content."<tr><td>".$hotel->getNom()."</td><td>".$hotel->getAdresse()."</td><td>".$hotel->getTypeHebergement()."</td><td>".$hotel->getNbEtoile()."</td><td>".$hotel->getTypeVIP()."</td><td>";
        $services = $hotel->getService();
        if(!empty($services)){
            $content = $content."";
            foreach($services as $service) {
                $content = $content."<p>".$service->getType()."</p>";
            }
        } 
        $content = $content."</td><td>".$hotel->getPlacesDispo()."</td></tr>";
    }
    $content = $content."</table></div>";
    require_once ("../../Vue/Layout.php");


