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
                    . "<input type = \"password\" name = \"password\" /><br /><br />"
                    . "<input type = \"submit\" value = \"Connexion\" /><br />"
                . "</form>";
    session_start();
    if (!isset($_SESSION['compte'])) {
        if (isset($_GET['connexion']) && !empty($_POST["login"])) {
          try {
              $compte=new CompteHebergement($_POST["login"], $_POST["password"]);
              $_SESSION['compte']=$compte;
              header("Location: ./hebergement/FenetreMenuCompteHebergement.php");
          } catch (Exception $ex) {
              if($ex->getCode()==1){
                  try {
                      $compte=new CompteStaff($_POST['login'], $_POST["password"]);
                      $_SESSION['compte']=$compte;
                      header("Location: ./staff/FenetreMenuStaff.php");
                  } catch (Exception $ex) {
                          $content = $content."<div>".$ex->getMessage()."</div>";  
                  }
              }
              else {
                  $content = $content."<div>".$ex->getMessage()."</div>";
              }
          }
        }
    } else {
        if(isset($_GET['deconnexion'])) {
            unset($_SESSION['compte']);
        }
        else {
            $compte=$_SESSION['compte'];
            if(strcmp($compte->getType(), "s")==0) {
              header("Location: ./staff/FenetreMenuStaff.php");
            }
            else {
              header("Location: ./hebergement/FenetreMenuCompteHebergement.php");
            }
        }
    }

    $content=$content. "</div>";
    require_once("../Vue/Layout.php");
?>
