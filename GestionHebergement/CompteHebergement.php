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
    public function __construct($login, $motDePasse, $adresseMail) {
        parent($login, $motDePasse,$adresseMail);
        $_type='h';
    }  
}
