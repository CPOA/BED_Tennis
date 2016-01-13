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
    private $_loginHebergement;
    private $_idVIP;
    private $_dateDebut;
    private $_dateFin;
    private $_nbPersonnes;
    function __construct($loginHebergement, $idVIP, $dateDebut, $dateFin, $nbPersonnes) {
        $this->_loginHebergement=$loginHebergement;
        $this->_idVIP=$idVIP;
        $this->_dateDebut=$dateDebut;
        $this->_dateFin=$dateFin;
        $this->_nbPersonnes=$nbPersonnes;
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
    
    static function getListReservations($loginHebergement) {
        try {
            $bd=Connection::getInstance();
            $bd->prepare("Select * from reservation,vip where reservation.loginhebergement=? and reservation.idvip=vip.id_vip");
            $bd->execute(array($loginHebergement));
            $liste=array();
            $reservations=$bd->fetchAll();
            foreach ($reservations as $reservation) {
                array_push($liste, array(new Reservation($reservation['loginhebergement'], $reservation["idvip"], $reservation["datedebut"], $reservation["datefin"],$reservation["nbpersonnes"]), new VIP($reservation["nom"], $reservation["prenom"])));
            }
            $bd->closeCursor();
            return $liste;
        }
        catch (PDOException $e) {
            echo ($e->getMessage());
        }
    }
           
}
