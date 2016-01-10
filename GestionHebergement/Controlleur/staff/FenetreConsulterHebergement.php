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
    
    $title;
    $content="";
    $title="Liste des Hebergements";
    require_once '../../Model/CompteHebergement.php';
    $hotels=CompteHebergement::getListHotels();
    foreach ($hotels as $hotel){
        $content=$content."<p>".$hotel->getNom()."\n"
        .$hotel->getAdresse()."\n"
        .$hotel->getNbEtoile()."\n"
        .$hotel->getTypeVIP()."\n";
        $services=$hotel->getService();
        if(!empty($services)){
            $content=$content."Services :\n<ul>";
            foreach($services as $service) {
                $content=$content."<li>".$service->getType()."</li>";
            }     
        }
        $content=$content."</ul>\n".$hotel->getPlacesDispo()."</p>";
        
    }
    require_once ("../../Vue/Layout.php");


