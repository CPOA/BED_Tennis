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
    $title = "Création d'un Compte hebergement";
    
    $compte->creerCompteHebergement($_POST("login"), $_POST("mdp"), $_POST("adressemail"));
    
    require_once("../../Vue/Layout.php");

?>

