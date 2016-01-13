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
    
    //$compte->creerCompteHebergement($_POST("login"), $_POST("mdp"), $_POST("adressemail"));
    
    $content = "<div id = \"cadre\">"
                . "<form action = \"FenetreAjoutHebergeant.php\" method = \"post\">"
                    . "<div id = \"formulaire\">"
                    . "<label for = \"log\"> Login de l'hébergeant :</label>"
                    . "<input type = \"text\" id = \"log\" name = \"log\"/><br /><br />"
                    . "<label for = \"pwd\"> Mot de passe :</label>"
                    . "<input type = \"password\" id = \"pwd\" name = \"pwd\"/><br /><br />"
                    . "<label for = \"pwdVerif\"> Retaper mot de passe :</label>"
                    . "<input type = \"password\" id = \"pwdVerif\" name = \"pwdVerif\"/><br /><br /><br /><br />"
            
                    . "<label for = \"name\"> Nom de l'hébergement :</label>"
                    . "<input type = \"text\" id = \"name\" name = \"name\"/><br /><br />"
                    . "<label for = \"type\"> Type de l'hébergement :</label>"
                    . "<input type = \"text\" id = \"type\" name = \"type\"/><br /><br />"
                    . "<label for = \"address\"> Adresse :</label>"
                    . "<input type = \"text\" id = \"address\" name = \"addr\"/><br /><br />"
                    . "<label for = \"addressM\"> Adresse Mail :</label>"
                    . "<input type = \"text\" id = \"addressM\" name = \"addrM\"/><br /><br />"
                    . "<label for = \"star\"> Nombre d'étoiles :</label>"
                    . "<input type = \"number\" id = \"star\" name = \"star\"/><br /><br />"
                    . "<label for = \"nbPlace\"> Nombre de places disponibles :</label>"
                    . "<input type = \"number\" id = \"nbPlace\" name = \"nbPlace\"/><br /><br /><br /><br />"
                    . "</div>"
            
                    ."<input type =\"submit\" value =\"Création du compte\"/><br />"
                . "</form>"
            . "</div>";
    
    require_once("../../Vue/Layout.php");

?>

