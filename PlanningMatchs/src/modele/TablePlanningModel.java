/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dave
 */
public class TablePlanningModel extends AbstractTableModel {

    List<Match> m_matchs;
    
    ArrayList<String> titreColonnes;
    
    public TablePlanningModel(List<Match> matchs) {
        m_matchs = matchs;
        
        titreColonnes = new ArrayList<>();
        
        titreColonnes.add("idMatch");
        titreColonnes.add("Créneau");
        titreColonnes.add("Court");
        titreColonnes.add("Type de tournoi");
        titreColonnes.add("Equipe 1");
        titreColonnes.add("Equipe 2");
        
        //System.out.println("TablePlanningModel() - " + matchs.size() + " matchs");
    }
    
    @Override
    public int getRowCount() {
        //System.out.println(m_matchs.size() + " matchs existants");
        return m_matchs.size();
    }

    @Override
    public int getColumnCount() {
        return titreColonnes.size();
    }

    @Override
    public Object getValueAt(int ligne, int colonne) {
        
        // = m_matchs.values().toArray();
        Match match = m_matchs.get(ligne);
        //System.out.println("in getValueAt(" + ligne + ", " + colonne + ") : " + match);
        switch (colonne) {
            case 0:
                return match.getId();
            case 1:
                return match.getCreneau();
            case 2:
                return match.getCourt();
            case 3:
                return match.getType() + " - " + match.getGenre();
            case 4:
                //return match.getJoueur1().toString();
                return match.getEquipe1().toString();
            case 5: 
                return match.getJoueur2().toString();
            default:
                return 0;
        }
        
    }
    

    public String getColumnName(int col) {
        return titreColonnes.get(col);
    }
    
    
    
}
