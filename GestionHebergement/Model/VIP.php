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
    function __construct($id) {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Select * from vip where id_vip=?");
            $bd->execute(array($id));
            $c=$bd->fetch();
            $bd->closeCursor();
        }
        catch (PDOException $e) {
            echo($e->getMessage());
        }
        if (strcmp($c['id_vip'],'')==0) {
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

    static function getListVip() {
    try {
        $bd=Connection::getInstance();
        $bd->prepare("Select * from vip");
        $bd->execute();
        $liste=array();
        $vips=$bd->fetchAll();
        foreach ($vips as $vip){
            array_push($liste,new VIP($vip['id_vip']));
        }
        $bd->closeCursor();
        return $liste;
    }
    catch (PDOException $e) {
        echo ($e->getMessage());
    }
}

}
