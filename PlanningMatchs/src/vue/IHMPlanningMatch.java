package vue;

import controleur.planningmatchs.PlanningMatchs;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.TableModel;
import modele.Error;
import modele.Match;
import modele.TablePlanningModel;
import modele.personne.Sexe;
import static modele.personne.Sexe.HOMME;
import modele.sgbd.DaoMatch;


public class IHMPlanningMatch extends javax.swing.JFrame {

    private TablePlanningModel tablePlanningModel;
    
    public IHMPlanningMatch() {
        initComponents();
        
        
        panelBoutons = new javax.swing.JPanel();
        panelBoutons.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelBoutonsLayout = new javax.swing.GroupLayout(panelBoutons);
        panelBoutons.setLayout(panelBoutonsLayout);
        panelBoutonsLayout.setHorizontalGroup(
            panelBoutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBoutonsLayout.setVerticalGroup(
            panelBoutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBoutons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBoutons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        //actualiserPlanning();
        
        afficherMatchs();
        
        jRadioButtonSimple.setSelected(true);
        jRadioButtonMessieurs.setSelected(true);
        
        this.setTitle("Planning des Matchs");
        
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupTypeTournoi = new javax.swing.ButtonGroup();
        buttonGroupGenreTournoi = new javax.swing.ButtonGroup();
        jPanel_table = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAffichagePlanning = new javax.swing.JTable();
        jPanel_planification = new javax.swing.JPanel();
        jRadioButtonSimple = new javax.swing.JRadioButton();
        jRadioButtonDouble = new javax.swing.JRadioButton();
        jRadioButtonDames = new javax.swing.JRadioButton();
        jRadioButtonMessieurs = new javax.swing.JRadioButton();
        jButton_PlanifierMatchs = new javax.swing.JButton();
        jPanel_bdd = new javax.swing.JPanel();
        jButton_RecupererMatchs = new javax.swing.JButton();
        jButton_RemplirTables = new javax.swing.JButton();
        jButton_ViderMatchs = new javax.swing.JButton();
        jButton_ViderTables = new javax.swing.JButton();
        jPanel_Match = new javax.swing.JPanel();
        jButton_detailsMatch = new javax.swing.JButton();
        jButton_resultatMatch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_table.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tableAffichagePlanning.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableAffichagePlanningKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableAffichagePlanning);

        javax.swing.GroupLayout jPanel_tableLayout = new javax.swing.GroupLayout(jPanel_table);
        jPanel_table.setLayout(jPanel_tableLayout);
        jPanel_tableLayout.setHorizontalGroup(
            jPanel_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_tableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel_tableLayout.setVerticalGroup(
            jPanel_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_tableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_planification.setBorder(javax.swing.BorderFactory.createTitledBorder("Planification des Matchs"));
        jPanel_planification.setName("jPanel_planification"); // NOI18N

        buttonGroupTypeTournoi.add(jRadioButtonSimple);
        jRadioButtonSimple.setText("Simple");
        jRadioButtonSimple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSimpleActionPerformed(evt);
            }
        });

        buttonGroupTypeTournoi.add(jRadioButtonDouble);
        jRadioButtonDouble.setText("Double");
        jRadioButtonDouble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDoubleActionPerformed(evt);
            }
        });

        buttonGroupGenreTournoi.add(jRadioButtonDames);
        jRadioButtonDames.setText("Dames");

        buttonGroupGenreTournoi.add(jRadioButtonMessieurs);
        jRadioButtonMessieurs.setText("Messieurs");

        jButton_PlanifierMatchs.setText("Planifier Matchs");
        jButton_PlanifierMatchs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PlanifierMatchsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_planificationLayout = new javax.swing.GroupLayout(jPanel_planification);
        jPanel_planification.setLayout(jPanel_planificationLayout);
        jPanel_planificationLayout.setHorizontalGroup(
            jPanel_planificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_planificationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_planificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_PlanifierMatchs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_planificationLayout.createSequentialGroup()
                        .addGroup(jPanel_planificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_planificationLayout.createSequentialGroup()
                                .addComponent(jRadioButtonDouble)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonMessieurs))
                            .addGroup(jPanel_planificationLayout.createSequentialGroup()
                                .addComponent(jRadioButtonSimple)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonDames)))
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_planificationLayout.setVerticalGroup(
            jPanel_planificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_planificationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_planificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonSimple)
                    .addComponent(jRadioButtonDames))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_planificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonDouble)
                    .addComponent(jRadioButtonMessieurs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_PlanifierMatchs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_bdd.setBorder(javax.swing.BorderFactory.createTitledBorder("Base de données"));
        jPanel_bdd.setName("jPanel_bdd"); // NOI18N

        jButton_RecupererMatchs.setText("Récupérer les matchs");
        jButton_RecupererMatchs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RecupererMatchsActionPerformed(evt);
            }
        });

        jButton_RemplirTables.setText("Remplir les Tables");
        jButton_RemplirTables.setToolTipText("Remplit les tables joueur, arbitre et ramasseur");
        jButton_RemplirTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RemplirTablesActionPerformed(evt);
            }
        });

        jButton_ViderMatchs.setText("Supprimer les matchs");
        jButton_ViderMatchs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ViderMatchsActionPerformed(evt);
            }
        });

        jButton_ViderTables.setText("Vider les Tables");
        jButton_ViderTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ViderTablesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_bddLayout = new javax.swing.GroupLayout(jPanel_bdd);
        jPanel_bdd.setLayout(jPanel_bddLayout);
        jPanel_bddLayout.setHorizontalGroup(
            jPanel_bddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_bddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_RecupererMatchs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_RemplirTables, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_ViderMatchs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_ViderTables, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_bddLayout.setVerticalGroup(
            jPanel_bddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bddLayout.createSequentialGroup()
                .addComponent(jButton_ViderTables)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_RemplirTables)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton_RecupererMatchs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_ViderMatchs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel_Match.setBorder(javax.swing.BorderFactory.createTitledBorder("Match Sélectionné"));
        jPanel_Match.setName("jPanel_Match"); // NOI18N

        jButton_detailsMatch.setText("Détails du Match");
        jButton_detailsMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_detailsMatchActionPerformed(evt);
            }
        });

        jButton_resultatMatch.setText("Inscrire les résultats du Match");

        javax.swing.GroupLayout jPanel_MatchLayout = new javax.swing.GroupLayout(jPanel_Match);
        jPanel_Match.setLayout(jPanel_MatchLayout);
        jPanel_MatchLayout.setHorizontalGroup(
            jPanel_MatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MatchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_MatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_detailsMatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_resultatMatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        jPanel_MatchLayout.setVerticalGroup(
            jPanel_MatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MatchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_detailsMatch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_resultatMatch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel_bdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_planification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_Match, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_planification, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_bdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Match, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_planification.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonSimpleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSimpleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonSimpleActionPerformed

    private void jRadioButtonDoubleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDoubleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonDoubleActionPerformed

    private void jButton_PlanifierMatchsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PlanifierMatchsActionPerformed
        String type = "";
        if (jRadioButtonSimple.isSelected())
            type = "simple";
        else if (jRadioButtonDouble.isSelected())
            type = "double";
        else {
            System.out.println("Vous devez sélectionner un type de tournoi.");
            return;
        }
        
        Sexe genre;
        if (jRadioButtonDames.isSelected())
            genre = Sexe.FEMME;
        else if (jRadioButtonMessieurs.isSelected())
            genre = Sexe.HOMME;
        else {
            System.out.println("Vous devez sélectionner le genre du tournoi.");
            return;
        }
        
        try {
            PlanningMatchs.planifierMatchs(type, genre);
        } catch (Error ex) {
            Logger.getLogger(IHMPlanningMatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        afficherMatchs();
        //System.out.println("end");
    }//GEN-LAST:event_jButton_PlanifierMatchsActionPerformed

    private void jButton_RecupererMatchsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RecupererMatchsActionPerformed
        PlanningMatchs.recupererMatchs();
        afficherMatchs();
    }//GEN-LAST:event_jButton_RecupererMatchsActionPerformed

    
    
    private void tableAffichagePlanningKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableAffichagePlanningKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            afficherDetailsMatchSelectionne();
        }
    }//GEN-LAST:event_tableAffichagePlanningKeyPressed

    private void jButton_RemplirTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemplirTablesActionPerformed
        verrouillerIHM();
        PlanningMatchs.creerRemplirTables();
        deverrouillerIHM();
    }//GEN-LAST:event_jButton_RemplirTablesActionPerformed

    private void jButton_ViderTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ViderTablesActionPerformed
        verrouillerIHM();
        PlanningMatchs.viderTables();
        deverrouillerIHM();
    }//GEN-LAST:event_jButton_ViderTablesActionPerformed

    private void jButton_detailsMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_detailsMatchActionPerformed
        verrouillerIHM();
        afficherDetailsMatchSelectionne();
        deverrouillerIHM();
    }//GEN-LAST:event_jButton_detailsMatchActionPerformed

    private void jButton_ViderMatchsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ViderMatchsActionPerformed
        DaoMatch.viderMatchs();
        afficherMatchs();
    }//GEN-LAST:event_jButton_ViderMatchsActionPerformed
    
    
    private void afficherMatchs() {
        
        int n = PlanningMatchs.nbMatchs();
        System.out.println(n + " matchs à afficher.");
        if (n == 0)
            tablePlanningModel = new TablePlanningModel();
        else 
            tablePlanningModel = new TablePlanningModel(new ArrayList<>(PlanningMatchs.getMatchs().values()));
        
        tableAffichagePlanning.setModel(tablePlanningModel);
        tableAffichagePlanning.getColumnModel().getColumn(0).setMaxWidth(60);
        tableAffichagePlanning.getColumnModel().getColumn(0).setMinWidth(60);
        
        tableAffichagePlanning.getColumnModel().getColumn(1).setMaxWidth(130);
        tableAffichagePlanning.getColumnModel().getColumn(1).setMinWidth(130);
        
        tableAffichagePlanning.getColumnModel().getColumn(2).setMaxWidth(110);
        tableAffichagePlanning.getColumnModel().getColumn(2).setMinWidth(110);
        
        tableAffichagePlanning.getColumnModel().getColumn(3).setMaxWidth(110);
        tableAffichagePlanning.getColumnModel().getColumn(3).setMinWidth(110);
        
        tableAffichagePlanning.getColumnModel().getColumn(4).setMaxWidth(200);
        tableAffichagePlanning.getColumnModel().getColumn(4).setMinWidth(200);
        
        tableAffichagePlanning.getColumnModel().getColumn(5).setMaxWidth(200);
        tableAffichagePlanning.getColumnModel().getColumn(5).setMinWidth(200);
    }
    
    private void afficherDetailsMatchSelectionne() {
        int row = tableAffichagePlanning.getSelectedRow();
        if (row < 0)
            return;
        Match match = PlanningMatchs.getMatch((int)tableAffichagePlanning.getValueAt(row, 0));
        IHMInfoMatch infoMatch = new IHMInfoMatch(match);
        infoMatch.setVisible(true);
        infoMatch = null;
    }
    
    
    private void verrouillerIHM() {
       jPanel_table.setEnabled(false);
       jPanel_bdd.setEnabled(false);
            jButton_RemplirTables.setEnabled(false);
            jButton_ViderTables.setEnabled(false);
            jButton_RecupererMatchs.setEnabled(false);
       jPanel_planification.setEnabled(false);
            jButton_PlanifierMatchs.setEnabled(false);
       jPanel_Match.setEnabled(false);
            jButton_detailsMatch.setEnabled(false);
            jButton_resultatMatch.setEnabled(false);
    }
    
    private void deverrouillerIHM() {
       jPanel_table.setEnabled(true);
       jPanel_bdd.setEnabled(true);
            jButton_RemplirTables.setEnabled(true);
            jButton_ViderTables.setEnabled(true);
            jButton_RecupererMatchs.setEnabled(true);
       jPanel_planification.setEnabled(true);
            jButton_PlanifierMatchs.setEnabled(true);
       jPanel_Match.setEnabled(true);
            jButton_detailsMatch.setEnabled(true);
            jButton_resultatMatch.setEnabled(true);
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IHMPlanningMatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IHMPlanningMatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IHMPlanningMatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IHMPlanningMatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IHMPlanningMatch().setVisible(true);
            }
        });
        
        
        
    }
    
    
    
    public void actualiserPlanning() {
        
        //PlanningMatchs.creerRemplirTables();
        try {
            PlanningMatchs.planifierMatchs("simple", HOMME);
        } catch (Error ex) {
            Logger.getLogger(IHMPlanningMatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
    
    
    private javax.swing.JPanel panelBoutons;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupGenreTournoi;
    private javax.swing.ButtonGroup buttonGroupTypeTournoi;
    private javax.swing.JButton jButton_PlanifierMatchs;
    private javax.swing.JButton jButton_RecupererMatchs;
    private javax.swing.JButton jButton_RemplirTables;
    private javax.swing.JButton jButton_ViderMatchs;
    private javax.swing.JButton jButton_ViderTables;
    private javax.swing.JButton jButton_detailsMatch;
    private javax.swing.JButton jButton_resultatMatch;
    private javax.swing.JPanel jPanel_Match;
    private javax.swing.JPanel jPanel_bdd;
    private javax.swing.JPanel jPanel_planification;
    private javax.swing.JPanel jPanel_table;
    private javax.swing.JRadioButton jRadioButtonDames;
    private javax.swing.JRadioButton jRadioButtonDouble;
    private javax.swing.JRadioButton jRadioButtonMessieurs;
    private javax.swing.JRadioButton jRadioButtonSimple;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableAffichagePlanning;
    // End of variables declaration//GEN-END:variables
}
