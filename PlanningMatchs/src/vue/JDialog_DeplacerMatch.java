/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.planningmatchs.PlanningMatchs;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modele.Creneau;
import modele.Error;
import modele.Match;
import modele.TableCreneauxDispoModel;
import donnees.DaoCreneau;

/**
 *
 * @author dave
 */
public class JDialog_DeplacerMatch extends javax.swing.JDialog {

    
    private Match m_match;
    
    
    List<Creneau> m_creneaux;
    
    /**
     * Creates new form JDialog_DeplacerMatch
     */
    public JDialog_DeplacerMatch(java.awt.Frame parent, boolean modal, Match matchADeplacer) {
        super(parent, modal);
        initComponents();
        
        m_creneaux = DaoCreneau.getCreneauxLibres();
        
        //TableCreneauxDispoModel tableCreneauxDispoModel = new TableCreneauxDispoModel(m_creneaux);
        jTable_CrenauxDispo.setModel(new TableCreneauxDispoModel(m_creneaux));
        m_match = matchADeplacer;
        
        jLabel_Match.setText(matchADeplacer.toString());
        
        
        jTable_CrenauxDispo.getColumnModel().getColumn(0).setMinWidth(120);
        jTable_CrenauxDispo.getColumnModel().getColumn(0).setMaxWidth(120);

}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Match = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_CrenauxDispo = new javax.swing.JTable();
        jButton_Confirmer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModal(true);

        jLabel_Match.setText("jLabel1");

        jTable_CrenauxDispo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable_CrenauxDispo.setMaximumSize(jTable_CrenauxDispo.getMinimumSize());
        jScrollPane1.setViewportView(jTable_CrenauxDispo);

        jButton_Confirmer.setText("Déplacer le match sur ce créneau");
        jButton_Confirmer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ConfirmerActionPerformed(evt);
            }
        });

        jLabel1.setText("Créneaux disponibles pour le déplacement du match :");

        jButton1.setText("Annuler");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel_Match))
                    .addComponent(jButton_Confirmer, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)))
                .addContainerGap(256, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_Match)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Confirmer, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ConfirmerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ConfirmerActionPerformed
        
        int row = jTable_CrenauxDispo.getSelectedRow();
        if (row < 0) return;
        
        Creneau creneau = m_creneaux.get(row);
        
        String txt = "Confirmer le déplacement :\n";
        txt += "\n  Match " + m_match.getId() + "";
        txt += creneau.toString();
        
        //finale JOptionPane jOptionPane = new JOptionPane(txt, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
        Object[] options = {"Yes", "No"};
        int res = (JOptionPane.showOptionDialog(this, txt, "Confirmer le déplacement du match", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]));
        JOptionPane.showMessageDialog(this, "res = " + res);
        if (res == 0) { // yes
            try {
                PlanningMatchs.deplacerMatch(m_match, creneau);
            } catch (Error ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "erreur", JOptionPane.WARNING_MESSAGE);
                Logger.getLogger(JDialog_DeplacerMatch.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                this.dispose();
            }
            JOptionPane.showMessageDialog(this, "Opération réussie.", "success", JOptionPane.INFORMATION_MESSAGE);

        }
        else {  // no
            JOptionPane.showMessageDialog(this, "Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_ConfirmerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Confirmer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Match;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_CrenauxDispo;
    // End of variables declaration//GEN-END:variables
}