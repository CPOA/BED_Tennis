<?php
/*
 * Cette Classe php permet aux hébergeant de voir les informations concernant
 * leurs établissement et, si nécessaire, les modifiers.
 */
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
    $title = "Mise à jour informations l'hébergement";
    
    /*
    * Affichage des informations récuperer grace à l'id du compte de la session actuel,
    * dans un tableau avec possibilité de les modifiers. 
    */
    $content = "<form action = \"FenetreMajInfo.php\" method = \"POST\"><div id =\"tableau2\">";
            
    $content = $content
            ."<table>"
                . "<tr> <td><h3> Informations </h3></td> <td><h3> Actuelles </h3></td><td><h3> Nouvelles </h3></td></tr>"
                . "<tr> <td> Nom </td> <td> ".$compte -> getNom()." </td> <td><input type = \"text\" name = \"newName\" /></td> </tr>"
                . "<tr> <td> Type </td> <td> ".$compte -> getTypeHebergement()." </td> <td><input type = \"text\" name = \"newType\" /></td> </tr>"
                . "<tr> <td> Adresse </td> <td> ".$compte -> getAdresse()." </td> <td><input type = \"text\" name = \"newAdress\" /></td> </tr>"
                . "<tr> <td> Adresse Mail </td> <td> ".$compte -> getAdresseMail()." </td> <td><input type = \"mail\" name = \"newAdressMail\" /></td> </tr>"
                . "<tr> <td> Nombre d'étoiles </td> <td> ".$compte -> getNbEtoile()." </td> <td><input type = \"number\" name = \"newNbEtoile\"/></td> </tr>"
//Emile do the job        . "<tr> <td> Services </td> <td> ".$compte -> getService()." </td> <td></td> </tr>"
            . "</table><br /><br />"
            . "<input type = \"submit\" value = \"Modification\">";
    
    $content = $content ."</div></form>";

    require_once '../../Model/CompteHebergement.php';
    /*
    $compte->setNom($_POST['nom']);
    $compte->setTypeHebergement($_POST['typeHebergement']);
    $compte->setAdresse($_POST['adresse']);
    $compte->setNbEtoile($_POST['nbEtoile']);
    $compte->setPlaceDispo($_POST['placesDispo']);
    foreach($_POST['service'] as $service) {
        $compte->addService($service);
    }
    $compte->update();
    $_SESSION['compte']=$compte;*/
    require_once("../../Vue/Layout.php");
?>
