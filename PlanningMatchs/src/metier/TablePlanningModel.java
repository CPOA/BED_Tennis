/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dave
 */
public class TablePlanningModel extends AbstractTableModel {

    List<Match> m_matchs;
    
    TreeSet<Match> m_treeMatchs;
    
    ArrayList<String> titreColonnes;
    
    public TablePlanningModel() {
        titreColonnes = new ArrayList<>();
        
        titreColonnes.add("idMatch");
        titreColonnes.add("Créneau");
        titreColonnes.add("Court");
        titreColonnes.add("Type de tournoi");
        titreColonnes.add("Equipe 1");
        titreColonnes.add("Equipe 2");
        titreColonnes.add("Joué");
    }
    
    public TablePlanningModel(List<Match> matchs) {
        this();
        m_matchs = matchs;
        
        
        m_treeMatchs = new TreeSet<Match>();
        for (Match m : m_matchs) {
            m_treeMatchs.add(m);
            //System.out.println("inserting match " + m_treeMatchs.size());
        }
        //System.out.println(m_matchs.size() + " matchs - " + m_treeMatchs.size() + " in tree");
        
        
        //System.out.println("TablePlanningModel() - " + matchs.size() + " matchs");
    }
    
    @Override
    public int getRowCount() {
        //System.out.println(m_matchs.size() + " matchs existants");
        if (m_matchs == null)
            return 0;
        return m_matchs.size();
    }

    @Override
    public int getColumnCount() {
        return titreColonnes.size();
    }

    @Override
    public Object getValueAt(int ligne, int colonne) {
        
        // = m_matchs.values().toArray();
        Match match = null;
        Iterator<Match> iterator = m_treeMatchs.iterator();

        int i = 0;
        while (iterator.hasNext() && i <= ligne) {
            match = iterator.next();
            i++;
        }
        //System.out.println("ligne = " + ligne + " i = " + i);
        //System.out.println("in getValueAt(" + ligne + ", " + colonne + ") : " + match);
        switch (colonne) {
            case 0:
                return match.getId();
            case 1:
                return match.getCreneau();
            case 2:
                return match.getCourt().getNom();
            case 3:
                return match.getType() + " - " + match.getStrRangTournoi();
            case 4:
                //return match.getJoueur1().toString();
                String txt = "";
                if (match.estFini()) {
                    if (match.getGagnant() == 1) {
                        //txt = "<html><strong>STRONG</strong></html>";
                    }
                }
                return match.getEquipe1().toString();
                //return txt;
            case 5: 
                return match.getJoueur2().toString();
            case 6:
                if (match.estFini())
                    return "oui";
                else
                    return "non";
            default:
                return 0;
        }
        
    }
    

    public String getColumnName(int col) {
        
        return titreColonnes.get(col);
    }
    
    
    
}
