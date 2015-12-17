<?php
    /*
    include_once("../CompteHebergement.php");
    include_once("../CompteStaff.php");
    */
    $title;
    $content;
    $compte;
    
    $compte = -1;
    $title = "Connexion à compte";
    
    $content = "<h1>Connexion à votre session</h1>"
            . "<div id = \"cadre\"> "
                ."<form>"
                    . "<h3> Identifiant : </h3>"
                    . "<input type = \"text\" name = \"login\" /><br />"
                    . "<h3> Mot de passe : </h3>"
                    . "<input type = \"text\" name = \"password\" /><br /><br />"
                    . "<input type = \"submit\" value = \"Connexion\" /><br />"
                . "</form>"
            . "</div>";
    
    // appel de la fonction que Emile aura créée pour vérifier si c'est un utilisateur valide
   
    /*
    if($compte != -1){
        if(gettype($compte) == CompteHebergement){
           header("./FenetreMenuCompteHebergement.php");
        }else{
            header("./FenetreMenuCompteStaff.php");
        }
    }else{
        $content = " Erreur mot de passe ou identifiant inconnu ! ";
    }
    */
    require_once("../Vue/Layout.php");
?>
