<?php

require_once 'Compte.php';
require_once 'Service.php';

class CompteHebergement extends Compte{
    private $_nom;
    private $_typeHebergement;
    private $_adresse;
    private $_nbEtoile;
    private $_typeVIP;
    private $_placesDispo;
    private $_service=array();
    public function __construct($login, $motDePasse) {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Select * from CompteHebergement where login=?");
            $bd->execute(array($login));
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
        }
        $_type='h';
    }
    function getNom() {
        return $this->_nom;
    }

    function getTypeHebergement() {
        return $this->_typeHebergement;
    }

    function getAdresse() {
        return $this->_adresse;
    }

    function getNbEtoile() {
        return $this->_nbEtoile;
    }

    function getTypeVIP() {
        return $this->_typeVIP;
    }

    function getPlacesDispo() {
        return $this->_placesDispo;
    }

    function setNom($nom) {
        $this->_nom = $nom;
    }

    function setTypeHebergement($typeHebergement) {
        $this->_typeHebergement = $typeHebergement;
    }

    function setAdresse($adresse) {
        $this->_adresse = $adresse;
    }

    function setNbEtoile($nbEtoile) {
        $this->_nbEtoile = $nbEtoile;
    }

    function setTypeVIP($typeVIP) {
        $this->_typeVIP = $typeVIP;
    }

    function setPlacesDispo($placesDispo) {
        $this->_placesDispo = $placesDispo;
    }

    function getHotel() {
        return array($this->_nom,$this->_typeHebergement,$this->_adresse,$this->_nbEtoile,$this->_typeVIP,$this->_placesDispo);
    }
    
    function getServices() {
        return $this->_service;
    }

    function addService($id) {
        $service= new Service($id);
        array_push($this->_service, $service);
    }
    
    static function getListHotels() {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Select * from CompteHebergement");
            $bd->execute();
            $liste=array();
            while ($hotel=$bd->fetch()) {
                array_push($liste,$hotel);
            }
            $bd->closeCursor();
            return $liste;
        }
        catch (PDOException $e) {
            echo ($e->getMessage());
        }
    }
    
    function update() {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Update CompteHebergement set nom=?,typehebergement=?,adresse=?,nbetoile=?,placesdispo=?) where login=?");
            $bd->execute(array($this->_nom, $this->_typeHebergement, $this->_adresse, $this->_nbEtoile, $this->_placesDispo, $this->_login));
            $bd->closeCursor();
        }
        catch (PDOException $e) {
            echo ($e->getMessage());
        }
    }
}
