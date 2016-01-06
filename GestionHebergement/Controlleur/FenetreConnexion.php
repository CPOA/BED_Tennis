<?php
    include_once("../Model/CompteHebergement.php");
    include_once("../Model/CompteStaff.php");

    $title;
    $content;
    $compte;
    
    $title = "Connexion à votre session";
    
    $content = "<div id = \"cadre\"> "
                ."<form action=\"FenetreConnexion.php?connexion=1\" method=\"post\">"
                    . "<h3> Identifiant : </h3>"
                    . "<input type = \"text\" name = \"login\" /><br />"
                    . "<h3> Mot de passe : </h3>"
                    . "<input type = \"text\" name = \"password\" /><br /><br />"
                    . "<input type = \"submit\" value = \"Connexion\" /><br />"
                . "</form>";
    
    if (isset($_GET['connexion']) && !empty($_POST["login"])) {
        try {
            $compte=new CompteHebergement($_POST["login"], $_POST["password"]);
            header("Location: ./hebergement/FenetreMenuCompteHebergement.php?compte=".urlencode(serialize($compte)));
        } catch (Exception $ex) {
            if($ex->getCode()==1){
                try {
                    $compte=new CompteStaff($_POST['login'], $_POST["password"]);
                    urlencode(serialize($compte));
                    header("Location: ./staff/FenetreMenuCompteStaff.php?compte=".urlencode(serialize($compte)));
                } catch (Exception $ex) {
                        $content = $content."<div>".$ex->getMessage()."</div>";  
                }
            }
            else {
                $content = $content."<div>".$ex->getMessage()."</div>";
            }
        }
        
    }
    $content=$content. "</div>";
    require_once("../Vue/Layout.php");
?>
