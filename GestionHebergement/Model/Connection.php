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
    private $_bd;
    private static $_instance;

    private function __construct(){
        $this->_host="iutdoua-webetu.univ-lyon1.fr";
        $this->_user="p1407734";
        $this->_password="215859";
        $this->_dbname="p1407734";
        try{
            $this->_bd = new PDO('mysql:host='.$this->_host.';dbname='.$this->_dbname.';charset=utf8',$this->_user,$this->_password);
        }catch(PDOException $e) {
            echo 'ERROR: ' . $e->getMessage();
        }
    }

    public function prepare($query) {
        $this->_query=$this->_bd->prepare($query);
    }

    public function execute(array $param=null) {
        if ($param==null) {
                $r=$this->_query->execute();
        }
        else {
                $r=$this->_query->execute($param);
        }
        return $r;
    }

    public function fetch(){
        return $this->_query->fetch(PDO::FETCH_ASSOC);
    }
    public function fetchAll() {
        return $this->_query->fetchAll(PDO::FETCH_ASSOC);
    }

    public function closeCursor(){
            $this->_query->closeCursor();
    }
    
    public static function getInstance() {
            if (!isset(self::$_instance)) {
                    self::$_instance = new self;
            }
            return self::$_instance;
    }
}
