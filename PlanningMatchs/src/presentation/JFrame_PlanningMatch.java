package presentation;

import metier.PlanningMatchs;
import java.awt.Point;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import metier.Error;
import metier.Match;
import metier.TablePlanningModel;
import static metier.personne.Sexe.HOMME;
import donnees.Dao;
import donnees.DaoMatch;


public class JFrame_PlanningMatch extends javax.swing.JFrame {

    private TablePlanningModel tablePlanningModel;
    
    static {
        //PlanningMatchs planningMatchs = new PlanningMatchs();
    }
    
    public JFrame_PlanningMatch() {
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
        
        
        afficherMatchs();
        
        
            
        ///  code écrit par anupammaiti sur Stackoverflow  https://stackoverflow.com/questions/14852719/double-click-listener-on-jtable-in-java et adapté
        //     sous licence Creative Commons Attribution-ShareAlike  https://creativecommons.org/licenses/by-sa/3.0/
            tableAffichagePlanning.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent me) {
                        JTable table =(JTable) me.getSource();
                        Point p = me.getPoint();
                        int row = table.rowAtPoint(p);
                        if (me.getClickCount() == 2) {
                            afficherDetailsMatchSelectionne(row);
                        }
                    }
                });
        
        /// fin du code écrit par anupammaiti

        
        //afficherMatchs();
        
        jRadioButtonSimple.setSelected(true);
        //jRadioButtonMessieurs.setSelected(true);
        
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
        jButton_PlanifierMatchs = new javax.swing.JButton();
        jPanel_bdd = new javax.swing.JPanel();
        jButton_RecupererMatchs = new javax.swing.JButton();
        jButton_RemplirTables = new javax.swing.JButton();
        jButton_ViderMatchs = new javax.swing.JButton();
        jButton_ViderTables = new javax.swing.JButton();
        jPanel_Match = new javax.swing.JPanel();
        jButton_detailsMatch = new javax.swing.JButton();
        jButton_resultatMatch = new javax.swing.JButton();
        jButton_deplacerMatch = new javax.swing.JButton();

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
        jRadioButtonSimple.setText("Simple Messieurs");
        jRadioButtonSimple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSimpleActionPerformed(evt);
            }
        });

        buttonGroupTypeTournoi.add(jRadioButtonDouble);
        jRadioButtonDouble.setText("Double Messieurs");
        jRadioButtonDouble.setEnabled(false);
        jRadioButtonDouble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDoubleActionPerformed(evt);
            }
        });

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
                            .addComponent(jRadioButtonDouble)
                            .addComponent(jRadioButtonSimple))
                        .addGap(0, 135, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_planificationLayout.setVerticalGroup(
            jPanel_planificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_planificationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonSimple)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonDouble)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_PlanifierMatchs, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
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
        jButton_resultatMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_resultatMatchActionPerformed(evt);
            }
        });

        jButton_deplacerMatch.setText("Déplacer");
        jButton_deplacerMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deplacerMatchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_MatchLayout = new javax.swing.GroupLayout(jPanel_Match);
        jPanel_Match.setLayout(jPanel_MatchLayout);
        jPanel_MatchLayout.setHorizontalGroup(
            jPanel_MatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MatchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_MatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_detailsMatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_resultatMatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_deplacerMatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel_MatchLayout.setVerticalGroup(
            jPanel_MatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MatchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_detailsMatch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_resultatMatch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_deplacerMatch)
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
        /*
        Sexe genre;     
        if (jRadioButtonDames.isSelected())
            genre = Sexe.FEMME;
        else if (jRadioButtonMessieurs.isSelected())
            genre = Sexe.HOMME;
        else {
            System.out.println("Vous devez sélectionner le genre du tournoi.");
            return;
        }*/
        
        try {
            PlanningMatchs.planifierMatchs(type, HOMME);
        } catch (Error ex) {
            Logger.getLogger(JFrame_PlanningMatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        afficherMatchs();
        //System.out.println("end");
    }//GEN-LAST:event_jButton_PlanifierMatchsActionPerformed

    private void jButton_RecupererMatchsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RecupererMatchsActionPerformed
        verrouillerIHM();
        PlanningMatchs.recupererMatchs();
        afficherMatchs();
        deverrouillerIHM();
    }//GEN-LAST:event_jButton_RecupererMatchsActionPerformed

    
    
    private void tableAffichagePlanningKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableAffichagePlanningKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            
            int row = tableAffichagePlanning.getSelectedRow();
            afficherDetailsMatchSelectionne(row);
            
        }
    }//GEN-LAST:event_tableAffichagePlanningKeyPressed

    private void jButton_RemplirTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemplirTablesActionPerformed
        verrouillerIHM();
        PlanningMatchs.remplirTables();
        deverrouillerIHM();
    }//GEN-LAST:event_jButton_RemplirTablesActionPerformed

    private void jButton_ViderTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ViderTablesActionPerformed
        int res = JOptionPane.showConfirmDialog(this, "Cela effacera les données des joueurs, arbitres, ramasseurs, courts, créneaux. Continuer ?", "Êtes-vous sûr ?",  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        //JOptionPane.showMessageDialog(this, JOptionPane.WARNING_MESSAGE, "Cela supprimera tous les matchs planifiés. Continuer ?", 0);
        JOptionPane.showMessageDialog(this, "res = " + res);
        if (res == 0) { // yes
            verrouillerIHM();
            PlanningMatchs.viderTables();
            deverrouillerIHM();
            JOptionPane.showMessageDialog(this, "Tables vidées - ");// + Dao.getIdMax());
        }
        else {    // no
            return;
        }
        
    }//GEN-LAST:event_jButton_ViderTablesActionPerformed

    private void jButton_detailsMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_detailsMatchActionPerformed

        int row = tableAffichagePlanning.getSelectedRow();
        afficherDetailsMatchSelectionne(row);

    }//GEN-LAST:event_jButton_detailsMatchActionPerformed

    private void jButton_ViderMatchsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ViderMatchsActionPerformed
        
        int res = JOptionPane.showConfirmDialog(this, "Cela supprimera tous les matchs planifiés. Continuer ?", "Supprimer les matchs",  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        //JOptionPane.showMessageDialog(this, JOptionPane.WARNING_MESSAGE, "Cela supprimera tous les matchs planifiés. Continuer ?", 0);
        JOptionPane.showMessageDialog(this, "res = " + res);
        if (res == 0) { // yes
            DaoMatch.viderMatchs();
            
        }
        else {    // no
            return;
        }
        PlanningMatchs.recupererMatchs();
        afficherMatchs();
    }//GEN-LAST:event_jButton_ViderMatchsActionPerformed

    private void jButton_resultatMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_resultatMatchActionPerformed
        //List<modele.Set> sets = new ArrayList<>();
        
        int row = tableAffichagePlanning.getSelectedRow();
        if (row < 0)
            return;
        Match match = PlanningMatchs.getMatch((int)tableAffichagePlanning.getValueAt(row, 0));
        
        //IHM_ResultatMatch ihm_resultatMatch = new IHM_ResultatMatch(match);
        //ihm_resultatMatch.setVisible(true);
        
        if (match.estFini()) {
            /*
            Object[] options = {"Retour - Ne rien modifier", "Modifier le score existant", "Effacer le score et marquer ce match comme non joué"};
            int res = (JOptionPane.showOptionDialog(this, "Les informations du score ont déjà été enregistrées.", "Match déjà joué", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]));
            JOptionPane.showMessageDialog(this, "res = " + res);
            if (res == 0) {  // retour
                return;
            }
            else if (res == 1) {
                
            }
            else if (res == 2) {
                
            }
            else if (res == 3) {
                
            }*/
            JOptionPane.showMessageDialog(this, "Ce match a déjà été joué ! ");
            return;

        }
        
        JDialog_ResultatMatch resultatMatch = new JDialog_ResultatMatch(this, true, match);
        
        resultatMatch.setVisible(true);
        
        afficherMatchs();
        
        System.out.println("result - " + match.getScoreFinal().toString());
    }//GEN-LAST:event_jButton_resultatMatchActionPerformed

    private void jButton_deplacerMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deplacerMatchActionPerformed
        
        int row = tableAffichagePlanning.getSelectedRow();
        if (row < 0) 
            return;
        Match matchADeplacer = PlanningMatchs.getMatch((int)tableAffichagePlanning.getValueAt(row, 0));
        
        if (matchADeplacer.estFini()) {
            JOptionPane.showMessageDialog(this, "Ce match a déjà été joué ! Il ne peut pas être déplacé.");
            return;
        }
        
        JDialog_DeplacerMatch jDialog_DeplacerMatch = new JDialog_DeplacerMatch(this, true, matchADeplacer);
        
        jDialog_DeplacerMatch.setVisible(true);
        
        afficherMatchs();
            
    }//GEN-LAST:event_jButton_deplacerMatchActionPerformed
    
    
    protected void afficherMatchs() {
        
        int n = PlanningMatchs.nbMatchs();
        System.out.println(n + " matchs à afficher.");
        if (n == 0)
            tablePlanningModel = new TablePlanningModel();
        else 
            tablePlanningModel = new TablePlanningModel(new ArrayList<>(PlanningMatchs.getMatchs().values()));
        
        tableAffichagePlanning.setModel(tablePlanningModel);
        tableAffichagePlanning.getColumnModel().getColumn(0).setMaxWidth(40);
        tableAffichagePlanning.getColumnModel().getColumn(0).setMinWidth(40);
        
        tableAffichagePlanning.getColumnModel().getColumn(1).setMaxWidth(130);
        tableAffichagePlanning.getColumnModel().getColumn(1).setMinWidth(130);
        
        tableAffichagePlanning.getColumnModel().getColumn(2).setMaxWidth(120);
        tableAffichagePlanning.getColumnModel().getColumn(2).setMinWidth(120);
        
        tableAffichagePlanning.getColumnModel().getColumn(3).setMaxWidth(200);
        tableAffichagePlanning.getColumnModel().getColumn(3).setMinWidth(200);
        
        tableAffichagePlanning.getColumnModel().getColumn(4).setMaxWidth(200);
        tableAffichagePlanning.getColumnModel().getColumn(4).setMinWidth(200);
        
        tableAffichagePlanning.getColumnModel().getColumn(5).setMaxWidth(200);
        tableAffichagePlanning.getColumnModel().getColumn(5).setMinWidth(200);
                
        tableAffichagePlanning.getColumnModel().getColumn(6).setMaxWidth(40);
        tableAffichagePlanning.getColumnModel().getColumn(6).setMinWidth(40);

    }
    
    private void afficherDetailsMatchSelectionne(int row) {
        verrouillerIHM();
        
        if (row < 0)
            return;
        Match match = PlanningMatchs.getMatch((int)tableAffichagePlanning.getValueAt(row, 0));
        JFrame_InfoMatch infoMatch = new JFrame_InfoMatch(match);
        infoMatch.setVisible(true);
        infoMatch = null;
        
        deverrouillerIHM();
    }
    
    
    public void verrouillerIHM() {
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
            java.util.logging.Logger.getLogger(JFrame_PlanningMatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_PlanningMatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_PlanningMatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_PlanningMatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_PlanningMatch().setVisible(true);
            }
        });
        
        
        
    }
    
    
    
    public void actualiserPlanning() {
        
        //PlanningMatchs.remplirTables();
        try {
            PlanningMatchs.planifierMatchs("simple", HOMME);
        } catch (Error ex) {
            Logger.getLogger(JFrame_PlanningMatch.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButton_deplacerMatch;
    private javax.swing.JButton jButton_detailsMatch;
    private javax.swing.JButton jButton_resultatMatch;
    private javax.swing.JPanel jPanel_Match;
    private javax.swing.JPanel jPanel_bdd;
    private javax.swing.JPanel jPanel_planification;
    private javax.swing.JPanel jPanel_table;
    private javax.swing.JRadioButton jRadioButtonDouble;
    private javax.swing.JRadioButton jRadioButtonSimple;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableAffichagePlanning;
    // End of variables declaration//GEN-END:variables
}
