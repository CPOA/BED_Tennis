<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Service
 *
 * @author Emile Bex
 */
class Service {
    private $_idType;
    private $_type;
    function __construct($idType) {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Select * from service where id=?");
            $bd->execute(array($idType));
            $c=$bd->fetch();
            $bd->closeCursor();
            $this->_idType=$c['id'];
            $this->_type=$c['type'];
        }
        catch (PDOException $e) {
            echo ($e->getMessage());
        }      
    }
 
    function getIdType() {
        return $this->_idType;
    }

    function getType() {
        return $this->_type;
    }

    function setIdType($idType) {
        $this->_idType = $idType;
    }

    function setType($type) {
        $this->_type = $type;
    }
    
    static function getListServices() {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Select * from service");
            $bd->execute();
            $liste=array();
            $services=$bd->fetchAll();
            foreach ($services as $service){
                array_push($liste,new Service($service['id']));
            }
            $bd->closeCursor();
            return $liste;
        }
        catch (PDOException $e) {
            echo ($e->getMessage());
        }
    }

}

