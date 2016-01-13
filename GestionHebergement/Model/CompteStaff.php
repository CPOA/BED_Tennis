<?php

require_once 'Compte.php';
require_once 'Connection.php';
require_once 'CompteHebergement.php';

class CompteStaff extends Compte{
    public function __construct($login, $motDePasse) {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Select * from CompteStaff where login=?");
            $bd->execute(array($login));
            $liste=array();
            $c=$bd->fetch();
            $bd->closeCursor();
        }
        catch (PDOException $e) {
            echo($e->getMessage());
        }
        if (strcmp($c['login'],'')==0) {
            throw new Exception('Login incorrecte',1);
        }
        else if(strcmp($motDePasse,$c['mdp'])!=0) {
            throw new Exception('Mot de passe incorrecte',2);
        }
        else {
            parent::__construct($login, $motDePasse);
            $this->setType("s");
        }
    }
    
    public function creerCompteHebergement($login, $motDePasse, $adresseMail, $nom, $typeHebergement, $adresse, $nombrePlaces, $nbEtoiles) {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Insert into CompteHebergement values(?,?,?,?,?,?,?,?,?,?)");
            echo($bd->execute(array($login,$motDePasse,$adresseMail, null, $nom, $typeHebergement, $adresse, $nbEtoiles, PlacesDispo, null)));
            $bd->closeCursor();
        }
        catch (PDOException $e) {
            echo($e->getMessage());
        }
    }
    
    public function effectuerReservation($loginHebergement, $idVIP, $dateDebut, $dateFin, $nbPersonnes) {
        $reservation = new Reservation($loginHebergement, $idVIP, $dateDebut, $dateFin, $nbPersonnes);
    }
    
}

