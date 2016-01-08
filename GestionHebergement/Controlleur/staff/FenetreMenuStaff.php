<?php
    $title = "Menu du Staff";
    $content =  
            
                     "<div id = \"bouton\"><a href = \"../../Controlleur/staff/FenetreAjoutHebergeant.php\">"
                        . "<h1> Ajouter un compte Hébergement </h1>"
                    . "</a></div>"
                    . "<br /><br />"
                    . "<div id = \"bouton\"><a href = \"../../Controlleur/staff/FenetreReservation.php\">"
                        . "<h1> Effectuer Réservation </h1>"
                    . "</a></div>"
                    . "<br /><br />"
                    . "<div id = \"bouton\"><a href = \"../../Controlleur/staff/FenetreConsulterHebergement.php\">"
                        . "<h1> Consulter liste hébergements </h1>"
                    . "</a></div>"
                    . "<br /><br />"
                ;
    
    require_once("../../Vue/Layout.php");
?>