/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.planningmatchs.PlanningMatchs;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import modele.Error;
import modele.Match;
import modele.Set;

/**
 *
 * @author dave
 */
public class IHM_ResultatMatch extends javax.swing.JFrame {

    
    List<modele.Set> m_sets;
    
    Match m_mMatch;
    
    /**
     * Creates new form IHM_ResultatMatch
     */
    public IHM_ResultatMatch(Match match) {
        initComponents();
        m_sets = new ArrayList<>();
        m_mMatch = match;
        setTitle("Résultat du match");
        jLabel_Equipe1.setText(match.getEquipe1().toString());
        jLabel_Equipe2.setText(match.getEquipe2().toString());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_NoMatch = new javax.swing.JLabel();
        jLabel_Equipe1 = new javax.swing.JLabel();
        jLabel_Equipe2 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jComboBox_Set1 = new javax.swing.JComboBox<>();
        jComboBox_Set2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton_AjouterSet = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_Sets = new javax.swing.JList<>();
        jButton_SupprimerSets = new javax.swing.JButton();
        jButton_Valider = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_NoMatch.setText("jLabel1");

        jLabel_Equipe1.setText("Equipe 1");

        jLabel_Equipe2.setText("Equipe2");

        jSplitPane1.setDividerLocation(170);
        jSplitPane1.setDividerSize(25);

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1));
        jSpinner1.setEditor(new javax.swing.JSpinner.NumberEditor(jSpinner1, ""));
        jSplitPane1.setRightComponent(jSpinner1);

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1));
        jSpinner2.setEditor(new javax.swing.JSpinner.NumberEditor(jSpinner2, ""));
        jSplitPane1.setLeftComponent(jSpinner2);

        jComboBox_Set1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }));
        jComboBox_Set1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Set1ActionPerformed(evt);
            }
        });
        jSplitPane1.setLeftComponent(jComboBox_Set1);

        jComboBox_Set2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }));
        jComboBox_Set2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Set2ActionPerformed(evt);
            }
        });
        jSplitPane1.setRightComponent(jComboBox_Set2);

        jLabel1.setText("Ajout des sets");

        jButton_AjouterSet.setText("Ajouter un set");
        jButton_AjouterSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AjouterSetActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jList_Sets);

        jButton_SupprimerSets.setText("Supprimer le set sélectionné");
        jButton_SupprimerSets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SupprimerSetsActionPerformed(evt);
            }
        });

        jButton_Valider.setText("Valider le résultat du match");
        jButton_Valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ValiderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel_NoMatch)
                        .addGap(78, 78, 78))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_Equipe1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Equipe2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSplitPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton_AjouterSet, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(31, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_SupprimerSets, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Valider, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_NoMatch)
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Equipe1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Equipe2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_AjouterSet)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_SupprimerSets)
                        .addGap(36, 36, 36)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Valider, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_Set1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Set1ActionPerformed
        int value = jComboBox_Set1.getSelectedIndex();
        if (value == 7)
            jComboBox_Set2.setSelectedIndex(6);
        else if (value < 6) {
            jComboBox_Set2.setSelectedIndex(6);
        }
        
        value = jComboBox_Set2.getSelectedIndex();
        if (value == 7)
            jComboBox_Set1.setSelectedIndex(6);
        else if (value < 6) {
            jComboBox_Set1.setSelectedIndex(6);
        }
        
    }//GEN-LAST:event_jComboBox_Set1ActionPerformed

    private void jComboBox_Set2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Set2ActionPerformed
        int value = jComboBox_Set2.getSelectedIndex();
        if (value == 7)
            jComboBox_Set1.setSelectedIndex(6);
        else if (value < 6) {
            jComboBox_Set1.setSelectedIndex(6);
        }    }//GEN-LAST:event_jComboBox_Set2ActionPerformed

    private void jButton_AjouterSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AjouterSetActionPerformed
        
        String s1 = Integer.toString(jComboBox_Set1.getSelectedIndex());
        System.out.println("selectedIndex=" + jComboBox_Set1.getSelectedIndex() + " - s1 = " + s1);
        String s2 = Integer.toString(jComboBox_Set2.getSelectedIndex());
        
        m_sets.add(new Set(new Integer(s1), new Integer(s2)));
        actualiserListe();
        //jList_Sets = new JList<>(jList_Sets.get)
        //jList_Sets.getSelectedValuesList();
    }//GEN-LAST:event_jButton_AjouterSetActionPerformed

    private void jButton_SupprimerSetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SupprimerSetsActionPerformed
        
        List<Set> setsAEnlever = new ArrayList<>();
        for (int i : jList_Sets.getSelectedIndices()) {
            setsAEnlever.add(m_sets.get(i));
        }
        m_sets.removeAll(setsAEnlever);
        /*
        while (jList_Sets.getSelectedValuesList().size() > 0) {
            //jList_Sets.remove(jList_Sets.getSelectedIndex());
            
            m_sets.removeAll()
        }
        for (int i : jList_Sets.getSelectedIndices()) {
            //jList_Sets.remove(i);
            m_sets.remove(i);
        }*/
        
        actualiserListe();
        
    }//GEN-LAST:event_jButton_SupprimerSetsActionPerformed

    private void jButton_ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ValiderActionPerformed
        
    }//GEN-LAST:event_jButton_ValiderActionPerformed

    private void actualiserListe() {
        DefaultListModel model = new DefaultListModel();
        for (modele.Set set : m_sets) {
            model.addElement(" " + set.getPointsJoueur1() + " - " + set.getPointsJoueur2());
        }
        jList_Sets.setModel(model);
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AjouterSet;
    private javax.swing.JButton jButton_SupprimerSets;
    private javax.swing.JButton jButton_Valider;
    private javax.swing.JComboBox<String> jComboBox_Set1;
    private javax.swing.JComboBox<String> jComboBox_Set2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Equipe1;
    private javax.swing.JLabel jLabel_Equipe2;
    private javax.swing.JLabel jLabel_NoMatch;
    private javax.swing.JList<String> jList_Sets;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
}
