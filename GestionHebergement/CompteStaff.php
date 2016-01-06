<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of CompteStaff
 *
 * @author Emile Bex
 */
class CompteStaff extends Compte{
    public function __construct($login, $motDePasse, $adresseMail) {
        parent($login, $motDePasse,$adresseMail);
        $_type='s';
    }   
}

