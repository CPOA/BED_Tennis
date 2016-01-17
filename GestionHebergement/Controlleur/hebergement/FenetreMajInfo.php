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
    $content= "";
    /*
    * Affichage des informations récuperer grace à l'id du compte de la session actuel,
    * dans un tableau avec possibilité de les modifiers. 
    */
    
    $content = $content. "<div id =\"tableau2\"><form action = \"FenetreMajInfo.php?modification=1\" method = \"post\">";
            
    $content = $content
            ."<table>"
                . "<tr> <td><h3> Informations </h3></td> <td><h3> Actuelles </h3></td><td><h3> Nouvelles </h3></td></tr>"
                . "<tr> <td> Nom </td> <td> ".$compte -> getNom()." </td> <td><input type = \"text\" name = \"newName\" /></td> </tr>"
                . "<tr> <td> Type </td> <td> ".$compte -> getTypeHebergement()." </td> <td><input type = \"text\" name = \"newType\" /></td> </tr>"
                . "<tr> <td> Adresse </td> <td> ".$compte -> getAdresse()." </td> <td><input type = \"text\" name = \"newAddress\" /></td> </tr>"
                . "<tr> <td> Adresse Mail </td> <td> ".$compte -> getAdresseMail()." </td> <td><input type = \"mail\" name = \"newAddressMail\" /></td> </tr>"
                . "<tr> <td> Nombre d'étoiles </td> <td> ".$compte -> getNbEtoile()." </td> <td><input type = \"number\" name = \"newNbEtoile\"/></td> </tr>"
                . "<tr> <td> Capacité </td> <td> ".$compte -> getPlacesTotal()." </td> <td><input type = \"number\" name = \"newCapacite\"/></td> </tr>"
                . "<tr> <td> Services </td> <td> ";
                foreach ($compte->getService() as $service) {
                    $content=$content."<p>".$service->getType()."</p>";
                }
                $content=$content." </td> <td><select name=\"services[]\" multiple>";
                foreach (Service::getListServices() as $service) {
                    $content=$content."<option value=".$service->getIdType().">".$service->getType()."</option>";
                }
                $content=$content."</select></td> </tr>"
                . "</table><br /><br />"
                . "<input type = \"submit\" value = \"Modification\">";
    
    $content = $content ."</form>";
    $content = $content."<form action=\"./FenetreMenuCompteHebergement.php\">"
                . "<input type=\"submit\" value=\"Annuler\">"
                . "</form></div><br />";
    
    if(isset($_GET["modification"])) {
        if(!empty($_POST['newName'])) {
            $compte->setNom($_POST['newName']);
        }
        if(!empty($_POST['newType'])) {
            $compte->setTypeHebergement($_POST['newType']);
        }
        if(!empty($_POST['newAddressMail'])) {
            $compte->setAdresseMail($_POST['newAddressMail']);
        }
        if(!empty($_POST['newAddress'])) {
            $compte->setAdresse($_POST['newAddress']);
        }
        if(!empty($_POST['newNbEtoile'])) {
            $compte->setNbEtoile($_POST['newNbEtoile']);
        }
        if(!empty($_POST['services'])) {
            $compte->setService($_POST["services"]);
        }
        if(!empty($_POST['newCapacite'])) {
            $compte->setPlacesTotal($_POST["newCapacite"]);
        }
        $compte->update();
        $_SESSION['compte']=$compte;
        $content="<p>Modifications effectuées</p>";
        $content = $content."<form action=\"./FenetreMajInfo.php\">"
                    . "<input type=\"submit\" value=\"Retour\">"
                    . "</form><br />";
    }
    require_once("../../Vue/Layout.php");
?>
