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
    
    $title = "Création d'une réservation";
    
    $content =  "<form action=\"FenetreReservation.php?reservation=1\" method=\"post\">"
                    . "<label for = \"Nom\"> Nom </label>"
                    . "<input type = \"text\" name = \"Nom\" id =\"Nom\" /><br />";

    $hotels=CompteHebergement::getListHotels();
    $content=$content."<table><tr><th></th><th>Nom</th><th>Adresse</th><th>Type d'hébergement</th><th>Nombre d'étoiles</th><th>Type VIP</th><th>Services</th><th>Places disponibles</th></tr>";
    foreach ($hotels as $hotel){
        if($hotel->getPlacesDispo()==0) {
            $content=$content."<tr><td><input type=\"radio\" name=\"hotel\" value=".$hotel->getLogin()." disabled></td>";
        }
        else {
            $content=$content."<tr><td><input type=\"radio\" name=\"hotel\" value=".$hotel->getLogin()." ></td>";
        }
        $content=$content."<td>".$hotel->getNom()."</td><td>".$hotel->getAdresse()."</td><td>".$hotel->getTypeHebergement()."</td><td>".$hotel->getNbEtoile()."</td><td>".$hotel->getTypeVIP()."</td><td>";
        $services=$hotel->getService();
        if(!empty($services)){
            $content=$content."<ul>";
            foreach($services as $service) {
                $content=$content."<li>".$service->getType()."</li>";
            }
        } 
        $content=$content."</ul></td><td>".$hotel->getPlacesDispo()."</td></tr>";
    }
    $content=$content."</table>";
    $content=$content."<input type=\"submit\" value=\"Réserver\">";
    $content=$content."</form>";
    if (isset($_POST["reservation"])) {
        try {
            $vip=new VIP($_POST["nom"], $_POST ["prenom"]);
            if (CompteHebergement::cmpTypeVip($_POST["hotel"], $vip->getType())) {
                $compte->effectuerReservation($_POST["hotel"],$vip->getId(), $_POST["dateDebut"],$_POST["dateDebut"],$_POST["nbPersonnes"]);
                $content="Reservation effectuer";
            }
            else {
                $content=$content."L'hebergement contient déjà un VIP du type opposé.";
            }
        }
        catch (Exception $ex) {
            $content=$content.$ex->getMessage();
        }
    }
    require_once("../../Vue/Layout.php");
?>

