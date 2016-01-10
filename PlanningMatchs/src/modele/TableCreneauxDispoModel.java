/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import donnees.DaoCreneau;

/**
 *
 * @author dave
 */
public class TableCreneauxDispoModel extends AbstractTableModel {
    
    List<Creneau> m_creneaux;
    
    ArrayList<String> m_titreColonnes;
    
    public TableCreneauxDispoModel(List<Creneau> creneaux) {
        m_creneaux = new ArrayList<>();
        m_titreColonnes = new ArrayList<>();
        
        m_titreColonnes.add("Créneau");
        m_titreColonnes.add("Court");
        
        m_creneaux = creneaux;
        
    }
    
    public Creneau getCreneau(int index) {
        return m_creneaux.get(index);
    }
    
    @Override
    public int getRowCount() {
        return m_creneaux.size();
    }

    @Override
    public int getColumnCount() {
        return m_titreColonnes.size();
    }

    @Override
    public Object getValueAt(int i, int i1) {
        
        Creneau creneau = m_creneaux.get(i);
        
        switch (i1) {
            case 0:
                return creneau;
            case 1:
                return creneau.getCourt().getNom();
            default:
                return 0;
        }
    }
    
}
