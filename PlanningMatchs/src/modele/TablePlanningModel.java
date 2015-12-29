/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dave
 */
public class TablePlanningModel extends AbstractTableModel {

    HashMap<Integer, Match> matchs;
    
    @Override
    public int getRowCount() {
        return matchs.size();
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return null;
    }
    
    
    
}
