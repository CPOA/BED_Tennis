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
    
    if (isset($_GET["choix"])) {
        if (isset($_POST['vip']) && isset($_POST['nbPersonnes']) && isset($_POST["dateDebut"]) && isset($_POST["dateFin"])) {
            if(empty($_POST['vip']) || empty($_POST['nbPersonnes']) || empty($_POST["dateDebut"]) || empty($_POST["dateFin"])){
                header("Location: ./FenetreReservation.php?erreur=1");
            }
            else {
                if(strcmp($_POST["dateDebut"],$_POST["dateFin"])>0 || strcmp($_POST["dateDebut"], date("Y-m-j"))<0 || strcmp($_POST["dateFin"], date("Y-m-j"))<0) {
                    header("Location: ./FenetreReservation.php?erreurDate=1");
                }
                else {
                    $vip=new VIP($_POST["vip"]);
                    $_SESSION['vip']=$vip;
                    $_SESSION['dateDebut']=$_POST["dateDebut"];
                    $_SESSION['dateFin']=$_POST["dateFin"];
                    $_SESSION['nbPersonnes']=$_POST["nbPersonnes"];
                    $dateDebut=$_SESSION['dateDebut'];
                    $dateFin=$_SESSION['dateFin'];
                    $nbPersonnes=$_SESSION['nbPersonnes'];
                }
            }
        }
        else {
            if (!isset( $_SESSION['vip']) || !isset( $_SESSION['dateDebut']) || !isset( $_SESSION['dateFin']) ||  !isset( $_SESSION['nbPersonnes'])) {
                header("Location: ./FenetreReservation.php");
            }
            else {
                $vip=$_SESSION['vip'];
                $dateDebut=$_SESSION['dateDebut'];
                $dateFin=$_SESSION['dateFin'];
                $nbPersonnes=$_SESSION['nbPersonnes'];
            }
        }
        $content =  "<div id =\"cadre2\"><form action=\"FenetreReservation.php?reservation=1\" method=\"post\">"
                        . "<div id = \"formulaire\"><br />"
                        . "<label for = \"name\"> Liste des Hébergements (cocher un des hebergement) : </label>"
                        . "<br /><br />";                

        //Début de la création d'un tableau des hébergement dans la base de données
        $hotels = CompteHebergement::getListHotels();
        $content = $content."<div id = \"tableau\"><table>"
                            . "<tr>"
                                . "<th></th> <th>Nom</th> <th>Adresse</th> <th>Type d'hébergement</th> <th>Nombre d'étoiles</th> <th>Type VIP</th> <th>Services</th> <th>Places disponibles</th>"
                            . "</tr>";
        foreach ($hotels as $hotel){
            if($hotel->getPlacesDispo()==0 || $hotel->getPlacesDispo()<$nbPersonnes) {
                $content = $content."<tr><td><input type=\"radio\" name=\"hotel\" value=".$hotel->getLogin()." disabled></td>";
            }
            else if(strcmp($hotel->getTypeVIP(),"")!=0 && $hotel->getTypeVIP()!=$vip->getType()) {
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
        $content = $content."</table></div><br /><br />";
        if(isset($_GET["erreur"])) {
            $content = $content."<p class=\"erreur\">Veuillez selectionner un hébergement</p>";
        }
        $content = $content."</div><br /><input type=\"submit\" value=\" Reserver \">";
        $content = $content."</form><br />";
        $content = $content."<form action=\"./FenetreReservation.php\">"
                . "<input type=\"submit\" value=\"Annuler\">"
                . "</form><br />";
        $content=$content."</div>";
    }
    else if (isset($_GET["reservation"])) {
        if (!isset( $_SESSION['vip']) || !isset( $_SESSION['dateDebut']) || !isset( $_SESSION['dateFin']) ||  !isset( $_SESSION['nbPersonnes'])) {
            header("Location: ./FenetreReservation.php");
        }
        else {
            if(empty($_POST["hotel"])) {
                header("Location: ./FenetreReservation.php?choix=1&erreur=1");
            }
            else {
                try {
                    $vip=$_SESSION['vip'];
                    $dateDebut=$_SESSION['dateDebut'];
                    $dateFin=$_SESSION['dateFin'];
                    $nbPersonnes=$_SESSION['nbPersonnes'];
                    $compte->effectuerReservation($_POST["hotel"],$vip->getId(),$dateDebut,$dateFin,$nbPersonnes, $vip->getType());
                    $content="<div><p>Réservation effectué</p>";
                    unset($_SESSION['vip']);
                    unset($_SESSION['dateDebut']);
                    unset($_SESSION['dateFin']);
                    unset($_SESSION['nbPersonnes']);
                    $content = $content."<form action=\"./FenetreReservation.php\">"
                    . "<input type=\"submit\" value=\"Retour\">"
                    . "</form><br />";
                }
                catch (Exception $ex) {
                    $content = $content.$ex->getMessage();
                }   
            }
            $content=$content."</div>";
        }
    }
    else{
        unset($_SESSION['vip']);
        unset($_SESSION['dateDebut']);
        unset($_SESSION['dateFin']);
        unset($_SESSION['nbPersonnes']);
        $content =  "<div id =\"cadre2\"><form action=\"FenetreReservation.php?choix=1\" method=\"post\">"
            . "<div id = \"formulaire\"><br />"
            . "<label for = \"vip\">VIP :</label><select name=\"vip\" id=\"vip\" >";
            foreach (VIP::getListVip() as $vip) {
                $content=$content."<option value=".$vip->getId().">".$vip->getNom()." ". $vip->getPrenom()."</option>";
            }
            $content=$content."</select><br /><br />"
                           ."<label for = \"nbPersonnes\"> Nombre de personnes :</label>"
                           ."<input type = \"number\" name = \"nbPersonnes\" id =\"nbPersonnes\" /><br /><br />"
                           ."<label for = \"rien\"> Durée du séjour </label><br /><br />"
                           ."<label for = \"dateDebut\"> Du :</label>"
                           ."<input type = \"date\" name = \"dateDebut\" id =\"dateDebut\" /><br /><br />"
                           ."<label for = \"datefin\"> Au :</label>"
                           ."<input type = \"date\" name = \"dateFin\" id =\"dateFin\" /><br />";

        $content = $content."</div><br /><input type=\"submit\" value=\" Trouver Hebergement \"></form><br />";
        $content = $content."<form action=\"./FenetreMenuStaff.php\">"
                . "<input type=\"submit\" value=\"Annuler\">"
                . "</form><br />";
        if(isset($_GET["erreur"])) {
            $content = $content."<p class=\"erreur\">Veuillez renseigner toutes les valeurs</p>";
        }
        else if(isset($_GET["erreurDate"])) {
            $content = $content."<p class=\"erreur\">Veuillez entrer des dates valides</p>";
        }
        $content=$content."</div>";
    }

    require_once("../../Vue/Layout.php");
?>

