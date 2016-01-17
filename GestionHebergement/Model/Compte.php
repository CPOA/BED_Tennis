<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Compte
 *
 * @author Emile Bex
 */
abstract class Compte {
    private $_login;
    private $_motDePasse;
    private $_adresseMail;
    private $_type;
    
    public function __construct($login, $motDePasse) {
        $this->_login=$login;
        $this->_motDePasse=$motDePasse;
    }
    function getLogin() {
        return $this->_login;
    }

    function getMotDePasse() {
        return $this->_motDePasse;
    }

    function getAdresseMail() {
        return $this->_adresseMail;
    }

    function getType() {
        return $this->_type;
    }

    function setLogin($login) {
        $this->_login = $login;
    }

    function setMotDePasse($motDePasse) {
        $this->_motDePasse = $motDePasse;
    }

    function setAdresseMail($adresseMail) {
        $this->_adresseMail = $adresseMail;
    }

    function setType($type) {
        $this->_type = $type;
    }
}
