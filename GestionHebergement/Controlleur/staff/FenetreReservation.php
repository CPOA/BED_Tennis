<?php
    require_once '../../Model/CompteStaff.php';
    require_once '../../Model/Service.php';
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
    
    $content =  "<div id =\"cadre2\"><form action=\"FenetreReservation.php\" method=\"post\">"
                    . "<div id = \"formulaire\"><br />"
                    . "<label for = \"name\"> Nom :</label>"
                    . "<input type = \"text\" name = \"name\" id =\"name\" /><br /><br />"
            
                    . "<label for = \"surname\"> Prénom :</label>"
                    . "<input type = \"text\" name = \"surname\" id =\"surname\" /><br /><br />"
            
                    . "<label for = \"vipType\"> Type de VIP :</label>"
                    . "<input type = \"text\" name = \"vipType\" id =\"vipType\" /><br /><br /><br /><br />"
            
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
            $content = $content."";
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
                       ."<input type = \"date\" name = \"datefin\" id =\"datefin\" /><br />";
            
    $content = $content."</div><br /><input type=\"submit\" value=\" Réservation \"><br />";
    $content = $content."</form></div>";
    if (isset($_POST["reservation"])) {
        try {
            $vip = new VIP($_POST["nom"], $_POST ["prenom"]);
            if (CompteHebergement::cmpTypeVip($_POST["hotel"], $vip->getType())) {
                $content = $content."Reservation effectuer";
            }
            else {
                $content = $content."L'hebergement contient déjà un VIP du type opposé.";
            }
        }
        catch (Exception $ex) {
            $content = $content.$ex->getMessage();
        }
    }
    require_once("../../Vue/Layout.php");
?>

