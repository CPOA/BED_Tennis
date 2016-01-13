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
    $title = "Création d'un Compte Hébergement";
    
    $content = "<div id = \"cadre\">"
                . "<form action = \"FenetreAjoutHebergeant.php?creation=1\" method = \"post\">"
                    . "<div id = \"formulaire\">"
                    . "<label for = \"log\"> Login de l'hébergeant :</label>"
                    . "<input type = \"text\" id = \"log\" name = \"log\"/><br /><br />"
                    . "<label for = \"pwd\"> Mot de passe :</label>"
                    . "<input type = \"password\" id = \"pwd\" name = \"pwd\"/><br /><br />"
                    . "<label for = \"pwdVerif\"> Retaper mot de passe :</label>"
                    . "<input type = \"password\" id = \"pwdVerif\" name = \"pwdVerif\"/><br /><br />"
                    . "<label for = \"addressM\"> Adresse Mail :</label>"
                    . "<input type = \"text\" id = \"addressM\" name = \"addrM\"/><br /><br /><br /><br />"
            
                    . "<label for = \"name\"> Nom de l'hébergement :</label>"
                    . "<input type = \"text\" id = \"name\" name = \"name\"/><br /><br />"
                    . "<label for = \"type\"> Type de l'hébergement :</label>"
                    . "<input type = \"text\" id = \"type\" name = \"type\"/><br /><br />"
                    . "<label for = \"address\"> Adresse :</label>"
                    . "<input type = \"text\" id = \"address\" name = \"addr\"/><br /><br />"
                    . "<label for = \"star\"> Nombre d'étoiles :</label>"
                    . "<input type = \"number\" id = \"star\" name = \"star\"/><br /><br />"
                    . "<label for = \"nbPlace\"> Nombre de places disponibles :</label>"
                    . "<input type = \"number\" id = \"nbPlace\" name = \"nbPlace\"/><br /><br /><br /><br />"
                    . "</div>"
            
                    ."<input type =\"submit\" value =\"Création du compte\"/><br />"
                . "</form>";

    if (isset($_GET["creation"])) {
        if(empty($_POST["log"])) {
            $content=$content."<p class=\"erreur\">Veuillez entrer un login</p>";
        }
        else if(empty($_POST["pwd"])) {
            $content=$content."<p class=\"erreur\">Veuillez entrer un mot de passe</p>";
        }
        else if (strcmp($_POST["pwd"],$_POST["pwdVerif"])!=0) {
            $content=$content."<p class=\"erreur\">Le mot de passe de vérification est différent de l'original</p>";
        }
        else if(empty($_POST["addrM"])) {
            $content=$content."<p class=\"erreur\">Veuillez entrer une adresse mail</p>";
        }
        else {
            $b=false;
            $liste=CompteHebergement::getListHotels();
            foreach($liste as $hotel) {
                if (strcmp($hotel->getLogin(),$_POST["log"])==0){
                    $b=true;
                }
            }
            $liste=CompteStaff::getListComptes();
            foreach($liste as $compteS) {
                if (strcmp($compteS->getLogin(),$_POST["log"])==0){
                    $b=true;
                }
            }
            if($b) {
                $content=$content."<p class=\"erreur\">Un compte possédant le même login existe déja</p>";
            }
            else {
                $compte->creerCompteHebergement($_POST["log"],$_POST["pwd"],$_POST["addrM"],$_POST["name"],$_POST["type"],$_POST["addr"],$_POST["star"],$_POST["nbPlace"]);
                $content="<div><p>Compte hébergement crée</p>";
            }
        }        
    }
    $content=$content. "</div>";
    
    require_once("../../Vue/Layout.php");

?>

