<?php

require_once 'Compte.php';
require_once 'Service.php';
require_once 'Connection.php';

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
            $this->_adressemail=$c['adressemail'];
            $this->_adresse=$c['adresse'];
            $this->_nom=$c['nom'];
            $this->_typeHebergement=$c['typeHebergement'];
            $this->_adresse=$c['adresse'];
            $this->_nbEtoile=$c['nbetoile'];
            $this->_typeVIP=$c['typevip'];
            $this->_placesDispo=$c['placesdispo'];
            $this->setType("h");
            $services=explode(",", $c['service']);
            foreach($services as $idService) {
                if (strcmp($idService, "")!=0) {
                    $service=new Service($idService);
                    array_push($this->_service, $service);
                }
            }
        }
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
    
    function getService() {
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
            $hotels=$bd->fetchAll();
            foreach ($hotels as $hotel){
                array_push($liste,new CompteHebergement($hotel['login'], $hotel['mdp']));
            }
            $bd->closeCursor();
            return $liste;
        }
        catch (PDOException $e) {
            echo ($e->getMessage());
        }
    }
    
    static function cmpTypeVip($login, $type){
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
        if (strcmp($c["typevip"],$type)!=0 && (isset($c["typevip"]) || !empty($c["typevip"]))) {
            $b=false;
        }
        else {
            try {
                $bd=Connection::getInstance();
                $bd->prepare("Update CompteHebergement set typevip=? where login=?");
                $bd->execute(array($type, $login));
                $c=$bd->fetch();
                $bd->closeCursor();
            }
            catch (PDOException $e) {
                echo($e->getMessage());
            }
            $b=true;
        }
        return $b;
    }
    
    function update() {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Update CompteHebergement set (nom=?,typehebergement=?,adresse=?,nbetoile=?,placesdispo=?, service=?) where login=?");
            foreach($_service as $service) {
                $serviceID=$serviceID.",".$service->getIdType();
            }
            $bd->execute(array($this->_nom, $this->_typeHebergement, $this->_adresse, $this->_nbEtoile, $this->_placesDispo, $this->_login ,$serviceID));
            $bd->closeCursor();
        }
        catch (PDOException $e) {
            echo ($e->getMessage());
        }
    }
}
