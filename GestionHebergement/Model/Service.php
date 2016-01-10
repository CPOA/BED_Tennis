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
    function __construct($idType, $_type) {
        $_idType=$idType;
        $_type=$type;
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


