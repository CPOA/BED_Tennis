<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of VIP
 *
 * @author Emile Bex
 */
class VIP {
    private $_nom;
    private $_prenom;
    private $_id;
    private $_type;
    function __construct($nom, $prenom) {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Select * from vip where nom=? AND prenom=?");
            $bd->execute(array($nom, $prenom));
            $c=$bd->fetch();
            $bd->closeCursor();
        }
        catch (PDOException $e) {
            echo($e->getMessage());
        }
        if (strcmp($c['nom'],'')==0 || strcmp($c['prenom'],'')==0) {
            throw new Exception('VIP inexistant', 1);
        }
        else {
            $this->_nom=$c['nom'];
            $this->_prenom=$c['prenom'];
            $this->_id=$c['id_vip'];
            $this->_type=$c['typeVIP'];
        }   
    }
    function getNom() {
        return $this->_nom;
    }

    function getPrenom() {
        return $this->_prenom;
    }

    function getId() {
        return $this->_id;
    }

    function setNom($nom) {
        $this->_nom = $nom;
    }

    function setPrenom($prenom) {
        $this->_prenom = $prenom;
    }

    function setId($id) {
        $this->_id = $id;
    }
    function getType() {
        return $this->_type;
    }

    function setType($type) {
        $this->_type = $type;
    }



}
