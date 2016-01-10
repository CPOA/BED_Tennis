<?php
    require_once '../../Model/CompteHebergement.php';
    $compte = unserialize(stripslashes(urldecode($_GET['compte'])));
    require_once ("../../Vue/Layout.php");
?>