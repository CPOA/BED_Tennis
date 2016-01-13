<?php
    require_once '../../Model/CompteStaff.php';
    require_once '../../Model/Service.php';
    require_once '../../Model/VIP.php';
    session_start();
    
    //Vérification de la session
    if (!isset($_SESSION['compte'])) {
        header("Location: ../FenetreConnexion.php");
    } else {
        $compte=$_SESSION['compte'];
        if(strcmp($_SESSION['compte']->getType(), "s")!=0) {
            header("Location: ../hebergement/FenetreMenuCompteHebergement.php");
        }
    }
    
    $title = "Création d'une réservation";
    
    $content =  "<div id =\"cadre2\"><form action=\"FenetreReservation.php?reservation=1\" method=\"post\">"
                    . "<div id = \"formulaire\"><br />"
                    . "<label for = \"name\"> Nom :</label>"
                    . "<input type = \"text\" name = \"name\" id =\"name\" /><br /><br />"
            
                    . "<label for = \"surname\"> Prénom :</label>"
                    . "<input type = \"text\" name = \"surname\" id =\"surname\" /><br /><br />"
            
                    . "<label for = \"vipType\"> Type de VIP :</label>"
                    . "<select name = \"vipType\" id =\"vipType\"><option value=\"ARBITRE\">ARBITRE</option><option value=\"JOUEUR\">JOUEUR</option><option value=\"ACCOMPAGNANT_JOUEUR\">ACCOMPAGNANT JOUEUR</option></select><br /><br /><br /><br />"
            
                    . "<label for = \"name\"> Liste des Hébergement :</label><br /><br />";
    
    //Début de la création d'un tableau des hébergement dans la base de données
    $hotels = CompteHebergement::getListHotels();
    $content = $content."<div id = \"tableau\"><table>"
                        . "<tr>"
                            . "<th></th> <th>Nom</th> <th>Adresse</th> <th>Type d'hébergement</th> <th>Nombre d'étoiles</th> <th>Type VIP</th> <th>Services</th> <th>Places disponibles</th>"
                        . "</tr>";
    foreach ($hotels as $hotel){
        if($hotel->getPlacesDispo()==0) {
            $content = $content."<tr><td><input type=\"radio\" name=\"hotel\" value=".$hotel->getLogin()." disabled></td>";
        }
        else {
            $content = $content."<tr><td><input type=\"radio\" name=\"hotel\" value=".$hotel->getLogin()." ></td>";
        }
        $content = $content."<td>".$hotel->getNom()."</td><td>".$hotel->getAdresse()."</td><td>".$hotel->getTypeHebergement()."</td><td>".$hotel->getNbEtoile()."</td><td>".$hotel->getTypeVIP()."</td><td>";
        $services=$hotel->getService();
        if(!empty($services)){
            foreach($services as $service) {
                $content = $content."<p>".$service->getType()."</p>";
            }
        } 
        $content = $content."</td><td>".$hotel->getPlacesDispo()."</td></tr>";
    }
    $content = $content."</table></div><br /><br /><br /><br />";
    //Fin Tableau
    
    $content = $content."<label for = \"nbPersonnes\"> Nombre de personnes :</label>"
                       ."<input type = \"number\" name = \"nbPersonnes\" id =\"nbPersonnes\" /><br /><br />"
                       ."<label for = \"rien\"> Durée du séjour </label><br /><br />"
                       ."<label for = \"dateDebut\"> Du :</label>"
                       ."<input type = \"date\" name = \"dateDebut\" id =\"dateDebut\" /><br /><br />"
                       ."<label for = \"datefin\"> Au :</label>"
                       ."<input type = \"date\" name = \"dateFin\" id =\"dateFin\" /><br />";
            
    $content = $content."</div><br /><input type=\"submit\" value=\" Réservation \"><br />";
    $content = $content."</form>";
    if (isset($_GET["reservation"])) {
        if(empty($_POST["name"])||empty($_POST["surname"])||empty($_POST["vipType"])||empty($_POST["hotel"])||empty($_POST["nbPersonnes"])||empty($_POST["dateDebut"])||empty($_POST["dateFin"])) {
            $content = $content."<p class=\"erreur\">Veuillez renseigner toutes les valeurs</p>";
        }
        else {
            try {
                $vip = new VIP($_POST["name"], $_POST ["surname"]);
                if (CompteHebergement::cmpTypeVip($_POST["hotel"], $vip->getType())) {
                    $compte->effectuerReservation($_POST["hotel"],$vip->getId(), $_POST["dateDebut"],$_POST["dateFin"],$_POST["nbPersonnes"]);
                    $content="<div><p>Réservation effectuer</p>";
                }
                else {
                    $content = $content."<p class=\"erreur\">L'hébergement contient déjà un VIP du type opposé.</p>";
                }
            }
            catch (Exception $ex) {
                $content = $content.$ex->getMessage();
            }   
        }
        
    }
    $content=$content."</div>";
    require_once("../../Vue/Layout.php");
?>

