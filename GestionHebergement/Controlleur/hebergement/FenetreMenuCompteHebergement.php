<?php
    $title = "Menu hébergeant";
    $content =  
            
                     "<div id = \"bouton\"><a href = \"\">"
                        . "<h1> Mettre à jour informations </h1></a>"
                    . "</div>"
                    . "<br /><br />"
                    . "<div id = \"bouton\"><a href = \"\">"
                        . "<h1> Mettre à jour disponibilité </h1>"
                    . "</a></div>"
                    . "<br /><br />"
                    . "<div id = \"bouton\"><a href = \"\">"
                        . "<h1> Consulter la liste des réservations </h1>"
                    . "</a></div>"
                    . "<br /><br />"
                ;
    require_once '../../Model/CompteHebergement.php';
    $compte = unserialize(stripslashes(urldecode($_GET['compte'])));
    require_once ("../../Vue/Layout.php");
?>