<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Reservation
 *
 * @author Emile Bex
 */
class Reservation {
    private $_dateDebut;
    private $_dateFin;
    private $_nbPersonnes;
    function __construct($dateDebut, $dateFin, $nbPersonnes) {
        $_dateDebut=$dateDebut;
        $_dateFin=$dateFin;
        $_nbPersonnes=$nbPersonnes;
    }
    function getDateDebut() {
        return $this->_dateDebut;
    }

    function getDateFin() {
        return $this->_dateFin;
    }

    function getNbPersonnes() {
        return $this->_nbPersonnes;
    }

    function setDateDebut($dateDebut) {
        $this->_dateDebut = $dateDebut;
    }

    function setDateFin($dateFin) {
        $this->_dateFin = $dateFin;
    }

    function setNbPersonnes($nbPersonnes) {
        $this->_nbPersonnes = $nbPersonnes;
    }


           
}
