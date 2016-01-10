<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Connection
 *
 * @author Emile Bex
 */
class Connection {
    private $_host;
    private $_query;
    private $_user;
    private $_password;
    private $_dbname;
    private $_bdd;
    private static $_instance;

    private function __construct(){
        $this->_host="iutdoua-webetu.univ-lyon1.fr";
        $this->_user="p1400169";
        $this->_password="210868";
        $this->_dbname="p1400169";
        try{
        $this->_bdd = new PDO('mysql:host='.$this->_host.';dbname='.$this->_dbname.';charset=utf8',$this->_user,$this->_password);
        }catch(PDOException $e) {
                echo 'ERROR: ' . $e->getMessage();
        }
    }

    public static function getInstance() {
            if (!isset(self::$_instance)) {
                    self::$_instance = new self;
            }
            return self::$_instance;
    }
}
