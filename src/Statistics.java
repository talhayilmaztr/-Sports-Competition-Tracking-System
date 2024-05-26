
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author talhayilmaz
 */
public class Statistics extends javax.swing.JFrame {

    FetchData data = new FetchData();

    Connection conn = null;
    int[] points = new int[100];
    String[] teams = new String[100];
    int[] matchCount = new int[100];
    int[] totalGoals = new int[1000];
    int[] wins = new int[100];
    int[] losses = new int[100];

    public Statistics() {
        initComponents();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SporM?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "password");
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        String selected = (String) jComboBox3.getSelectedItem();
        data.leagues(selected, jList3, conn);
        points(selected);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label2 = new java.awt.Label();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        new javax.swing.DefaultComboBoxModel<>(new String[] { "Takım A", "Takım B", "Takım C" });
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label2.setBackground(new java.awt.Color(142, 142, 142));
        label2.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        label2.setForeground(new java.awt.Color(200, 232, 101));
        label2.setText("  Statistics");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Teams", "Matches Played", "Wins", "Losses", "Goals", "Points"
            }
        ));
        jScrollPane5.setViewportView(jTable3);

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Liverpool", "Real Madrid", "Barcelona", "Turkey" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jList3MousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(jList3);

        new javax.swing.DefaultComboBoxModel<>(new String[] { "Takım A", "Takım B", "Takım C" });
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Football", "Basketbol", "Voleybol", "Hentbol" }));
        new javax.swing.DefaultComboBoxModel<>(new String[] { "Takım A", "Takım B", "Takım C" });
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Leagues:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(124, 124, 124))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        jMenu2.setText("Main Page");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu2MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Match Admin");

        jMenuItem1.setText("Müsabaka Ekle");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Müabaka Sil");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setText("Müsabaka Düzenle");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);
        jMenu3.add(jSeparator2);

        jMenuItem11.setText("Puan Durumu");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("About");
        jMenuBar1.add(jMenu6);

        jMenu4.setText("Yenile");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Log out");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList3MousePressed
        String selectedSprt = (String) jComboBox3.getSelectedItem();
        String selectedLig = jList3.getSelectedValue();
        sorgulaa(selectedSprt, selectedLig, jTable3);

    }//GEN-LAST:event_jList3MousePressed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        String selected = (String) jComboBox3.getSelectedItem();
        reset();
        points(selected);
        data.leagues(selected, jList3, conn);
    }

    public void reset() {
        for (int i = 0; i < 100; i++) {
            matchCount[i] = 0;
            points[i] = 0;
            totalGoals[i] = 0;
            wins[i] = 0;
            losses[i] = 0;
            teams[i] = null;
        }
    }

    public void points(String sprtName) {
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0);

        PreparedStatement ps = null;
        int k = 0;

        try {
            ps = conn.prepareStatement("SELECT * FROM " + sprtName.toUpperCase());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String team = rs.getString("TEAMS");
                String score = rs.getString("SCORE");

                String[] teamParts = team.split("-");
                String[] scoreParts = score.split("-");

                String team1 = teamParts[0].trim();
                String team2 = teamParts[1].trim();
                int goalsTeam1 = Integer.parseInt(scoreParts[0].trim());
                int goalsTeam2 = Integer.parseInt(scoreParts[1].trim());

                int team1Index = findTeamIndex(team1, teams);
                if (team1Index == -1) {
                    team1Index = addTeam(team1, teams);
                }
                matchCount[team1Index]++;
                totalGoals[team1Index] += goalsTeam1;

                int team2Index = findTeamIndex(team2, teams);
                if (team2Index == -1) {
                    team2Index = addTeam(team2, teams);
                }
                matchCount[team2Index]++;
                totalGoals[team2Index] += goalsTeam2;

                if (goalsTeam1 > goalsTeam2) {
                    wins[team1Index]++;
                    losses[team2Index]++;
                    points[team1Index] += 3;
                } else if (goalsTeam2 > goalsTeam1) {
                    wins[team2Index]++;
                    losses[team1Index]++;
                    points[team2Index] += 3;
                } else {
                    points[team1Index]++;
                    points[team2Index]++;
                }
            }

            for (int i = 0; i < points.length - 1; i++) {
                int maxIndex = i;
                for (int j = i + 1; j < points.length; j++) {
                    if (points[j] > points[maxIndex]) {
                        maxIndex = j;
                    }
                }

                if (maxIndex != i) {
                    swap(points, i, maxIndex);
                    swap(teams, i, maxIndex);
                    swap(matchCount, i, maxIndex);
                    swap(wins, i, maxIndex);
                    swap(losses, i, maxIndex);
                    swap(totalGoals, i, maxIndex);
                }
            }

            for (k = 0; k < teams.length && teams[k] != null; k++) {
                model.addRow(new Object[]{teams[k], matchCount[k], wins[k], losses[k], totalGoals[k], points[k]});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int findTeamIndex(String takim, String[] takimlar) {
        for (int i = 0; i < takimlar.length && takimlar[i] != null; i++) {
            if (takim.equals(takimlar[i])) {
                return i;
            }
        }
        return -1;
    }

    private int addTeam(String takim, String[] takimlar) {
        for (int i = 0; i < takimlar.length; i++) {
            if (takimlar[i] == null) {
                takimlar[i] = takim;
                return i;
            }
        }
        return -1;

    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jMenu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
        Main main = new Main();
        this.setVisible(false);
        main.setVisible(true);
    }//GEN-LAST:event_jMenu2MousePressed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked

    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
        MatchAdmin m = new MatchAdmin();
        this.setVisible(false);
        m.setVisible(true);
    }//GEN-LAST:event_jMenuItem1MousePressed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        this.setVisible(false);
        Statistics st = new Statistics();
        st.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        Login login = new Login();

        int cevap = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);

        if (cevap == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            login.setVisible(true);
        }
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed

    }//GEN-LAST:event_jMenu5ActionPerformed

    private void sorgulaa(String sportName, String ligg, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        PreparedStatement ps = null;
        ArrayList<String> addedTeams = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + sportName.toUpperCase();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String team = rs.getString("TAKIMLAR");
                String league = rs.getString("LİGLER");

                if (league.toUpperCase().contains(league.toUpperCase())) {

                    String[] teamParts = team.split("-");
                    String team1 = teamParts[0].trim();
                    String team2 = teamParts[1].trim();

                    if (!addedTeams.contains(team1)) {
                        int team1Index = findTeamIndex(team1, teams);
                        if (team1Index != -1) {
                            model.addRow(new Object[]{teams[team1Index], matchCount[team1Index], wins[team1Index], losses[team1Index], totalGoals[team1Index], points[team1Index]});
                            addedTeams.add(team1);
                        }
                    }

                    if (!addedTeams.contains(team2)) {
                        int team2Index = findTeamIndex(team2, teams);
                        if (team2Index != -1) {
                            model.addRow(new Object[]{teams[team2Index], matchCount[team2Index], wins[team2Index], losses[team2Index], totalGoals[team2Index], points[team2Index]});
                            addedTeams.add(team2);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Statistics().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTable jTable3;
    private java.awt.Label label2;
    // End of variables declaration//GEN-END:variables
}
