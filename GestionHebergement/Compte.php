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
    
    public function __construct($login, $motDePasse, $adresseMail) {
        $_login=$login;
        $_motDePasse=$motDePasse;
        $_adresseMail=$adresseMail;
    }
    
}
