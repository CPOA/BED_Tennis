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
    
    public function creerCompteHebergement($login, $motDePasse, $adresseMail, $nom, $typeHebergement, $adresse, $nbEtoiles, $nombrePlaces) {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Insert into CompteHebergement(`login`, `mdp`,'type', `adressemail`, `nom`, `typeHebergement`, `adresse`, `nbetoile`, `placesdispo`) values(?,?,?,?,?,?,?,?,?)");
            $bd->execute(array($login,$motDePasse,$adresseMail, 'h', $nom, $typeHebergement, $adresse, $nbEtoiles, $nombrePlaces));
            $bd->closeCursor();
        }
        catch (PDOException $e) {
            echo($e->getMessage());
        }
    }
    
    public function effectuerReservation($loginHebergement, $idVIP, $dateDebut, $dateFin, $nbPersonnes, $typeVIP) {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Insert into reservation values(?,?,?,?,?)");
            $bd->execute(array($loginHebergement, $idVIP, $dateDebut, $dateFin, $nbPersonnes));
            $bd->prepare("Update CompteHebergement set typevip=? where login=?");
            $bd->execute(array($typeVIP, $loginHebergement));
            $bd->closeCursor();
        }
        catch (PDOException $e) {
            echo ($e->getMessage());
        }
    }
    
    static function getListComptes() {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Select * from CompteStaff");
            $bd->execute();
            $liste=array();
            $comptes=$bd->fetchAll();
            foreach ($comptes as $compte){
                array_push($liste,new CompteStaff($compte['login'], $compte['mdp']));
            }
            $bd->closeCursor();
            return $liste;
        }
        catch (PDOException $e) {
            echo ($e->getMessage());
        }
    }
}

