<?php
require_once '../../Model/CompteHebergement.php';
$compte = unserialize(stripslashes(urldecode($_GET['compte'])));
$compte->setPlacesDispos($nbr);
$compte->update();