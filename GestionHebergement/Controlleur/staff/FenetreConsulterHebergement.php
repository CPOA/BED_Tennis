<?php
    $title;
    $content;
    $title="Liste des Hebergements";
    require_once '../../Model/CompteHebergement.php';
    //$compte = unserialize(stripslashes(urldecode($_GET['compte'])));
    $hotels=CompteHebergement::getListHotels();
    foreach ($hotels as $hotel){
        $content= $hotel->getNom()."\n"
        .$hotel->getAdresse()."\n"
        .$hotel->getNbEtoile()."\n"
        .$hotel->getTypeVIP()."\n";
        if(!empty($hotel->getService())) {
            "Services :\n";
            foreach($hotel->getService() as $service) {
                $content=$content.$service->getType()."\n";
            }     
        }
        $content=$content.$hotel->getPlacesDispo();
        
    }
    require_once ("../../Vue/Layout.php");


