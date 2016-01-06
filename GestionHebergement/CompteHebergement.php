<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of CompteHebergement
 *
 * @author Emile Bex
 */
class CompteHebergement extends Compte{
    private $_nom;
    private $_typeHebergement;
    private $_adresse;
    private $_nbEtoile;
    private $_typeVIP;
    private $_placesDispo;
    private $_service;
    public function __construct($login, $motDePasse, $adresseMail) {
        parent($login, $motDePasse,$adresseMail);
        $_type='h';
        $_service=array();
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
        return array($_nom,$_typeHebergement,$_adresse,$_nbEtoile,$_typeVIP,$_placesDispo);
    }
    
    function getService() {
        return $this->_service;
    }

    function addService($service) {
        array_push($this->_service, $service);
    }


}
