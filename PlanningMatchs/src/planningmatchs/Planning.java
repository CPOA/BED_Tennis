/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planningmatchs;

import java.util.Date;
import java.util.List;
import modele.Creneau;
import static modele.TrancheHoraire.*;

/**
 *
 * @author dave
 */
public class Planning {
    
    
    private List<Creneau> creneaux;
    
    public Planning(Date dateDebut, Date dateFin) {
        
        Creneau creneau;
        
        
        creneau = new Creneau(dateDebut, MATIN);
        
    }
    
    
    public Creneau getPremierCreneauDispo() {
        return null;
    }
    
    
}
