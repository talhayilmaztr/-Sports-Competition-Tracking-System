
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
public class Istatistikler extends javax.swing.JFrame {

    FetchData data = new FetchData();

    Connection conn = null;
    int[] puan = new int[100];
    String[] takimlar = new String[100];
    int[] macSayisi = new int[100];
    int[] toplamGol = new int[1000];
    int[] galibiyet = new int[100];
    int[] maglubiyet = new int[100];

    public Istatistikler() {
        initComponents();
         try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SporM?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "password");
        } catch (SQLException ex) {
            Logger.getLogger(Istatistikler.class.getName()).log(Level.SEVERE, null, ex);
        }
        String selected = (String) jComboBox3.getSelectedItem();
        data.ligler(selected, jList3, conn);
        puan(selected);
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
        label2.setText("  İstatistikler");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "TAKIM", "OYNANAN MAÇ", "GALİBİYET", "MAĞLUBİYET", "GOLLER", "PUAN"
            }
        ));
        jScrollPane5.setViewportView(jTable3);

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Galatasaray", "Fenerbahçe", "Trabzon", "Konya", "Beşiktaş" };
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
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Futbol", "Basketbol", "Voleybol", "Hentbol" }));
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

        jLabel1.setText("Ligler:");

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu2.setText("Ana Sayfa");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu2MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Müsabaka Admin");

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

        jMenu6.setText("Hakkında");
        jMenuBar1.add(jMenu6);

        jMenu4.setText("Yenile");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Çıkış");
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
        DefaultTableModel model =(DefaultTableModel)jTable3.getModel();
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
        puan(selected);
        data.ligler(selected, jList3, conn);
        }

        public void reset() {
            for (int i = 0; i < 100; i++) {
                macSayisi[i] = 0;
                puan[i] = 0;
                toplamGol[i] = 0;
                galibiyet[i] = 0;
                maglubiyet[i] = 0;
                takimlar[i] = null;
            }
        }

        public void puan(String sprtName) {
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            PreparedStatement ps = null;
            int k = 0;

            try {
                ps = conn.prepareStatement("SELECT * FROM " + sprtName.toUpperCase());
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String takımlar = rs.getString("TAKIMLAR");
                    String skor = rs.getString("SKOR");
                    String[] takimParcalari = takımlar.split("-");
                    String[] skorParcalari = skor.split("-");

                    String takim1 = takimParcalari[0].trim();
                    String takim2 = takimParcalari[1].trim();
                    int golTakim1 = Integer.parseInt(skorParcalari[0].trim());
                    int golTakim2 = Integer.parseInt(skorParcalari[1].trim());

                    int takim1Index = findTakimIndex(takim1, takimlar);
                    if (takim1Index == -1) {
                        takim1Index = addTakim(takim1, takimlar);
                    }
                    macSayisi[takim1Index]++;
                    toplamGol[takim1Index] += golTakim1;

                    int takim2Index = findTakimIndex(takim2, takimlar);
                    if (takim2Index == -1) {
                        takim2Index = addTakim(takim2, takimlar);
                    }
                    macSayisi[takim2Index]++;
                    toplamGol[takim2Index] += golTakim2;

                    if (golTakim1 > golTakim2) {
                        galibiyet[takim1Index]++;
                        maglubiyet[takim2Index]++;
                        puan[takim1Index] += 3;
                    } else if (golTakim2 > golTakim1) {
                        galibiyet[takim2Index]++;
                        maglubiyet[takim1Index]++;
                        puan[takim2Index] += 3;
                    } else {
                        puan[takim1Index]++;
                        puan[takim2Index]++;
                    }
                }

                for (int i = 0; i < puan.length - 1; i++) {
                    int maxIndex = i;
                    for (int j = i + 1; j < puan.length; j++) {
                        if (puan[j] > puan[maxIndex]) {
                            maxIndex = j;
                        }
                    }

                    if (maxIndex != i) {
                        swap(puan, i, maxIndex);
                        swap(takimlar, i, maxIndex);
                        swap(macSayisi, i, maxIndex);
                        swap(galibiyet, i, maxIndex);
                        swap(maglubiyet, i, maxIndex);
                        swap(toplamGol, i, maxIndex);
                    }
                }

                for (k = 0; k < takimlar.length && takimlar[k] != null; k++) {
                    model.addRow(new Object[]{takimlar[k], macSayisi[k], galibiyet[k], maglubiyet[k], toplamGol[k], puan[k]});
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(Istatistikler.class.getName()).log(Level.SEVERE, null, ex);
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

        public int findTakimIndex(String takim, String[] takimlar) {
            for (int i = 0; i < takimlar.length && takimlar[i] != null; i++) {
                if (takim.equals(takimlar[i])) {
                    return i;
                }
            }
            return -1;
        }

        private int addTakim(String takim, String[] takimlar) {
            for (int i = 0; i < takimlar.length; i++) {
                if (takimlar[i] == null) {
                    takimlar[i] = takim;
                    return i;
                }
            }
            return -1;

    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jMenu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
        AnaSayfaa ans = new AnaSayfaa();
        this.setVisible(false);
        ans.setVisible(true);
    }//GEN-LAST:event_jMenu2MousePressed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked

    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
        MüsabakaAdminn müs = new MüsabakaAdminn();
        this.setVisible(false);
        müs.setVisible(true);
    }//GEN-LAST:event_jMenuItem1MousePressed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        this.setVisible(false);
        Istatistikler is = new Istatistikler();
        is.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        Giris giris = new Giris();

        int cevap = JOptionPane.showConfirmDialog(null, "Çıkış Yapmak istediğinize emin misiniz?", "ÇIKIŞ", JOptionPane.YES_NO_OPTION);

        if (cevap == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            giris.setVisible(true);
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
            String takımlar = rs.getString("TAKIMLAR");
            String lig = rs.getString("LİG");

            if (lig.toUpperCase().contains(ligg.toUpperCase())) {

                String[] takimParcalari = takımlar.split("-");
                String takim1 = takimParcalari[0].trim();
                String takim2 = takimParcalari[1].trim();

                
                if (!addedTeams.contains(takim1)) { 
                    int takim1Index = findTakimIndex(takim1, takimlar);
                    if (takim1Index != -1) {
                        model.addRow(new Object[]{takimlar[takim1Index], macSayisi[takim1Index], galibiyet[takim1Index], maglubiyet[takim1Index], toplamGol[takim1Index], puan[takim1Index]});
                        addedTeams.add(takim1);
                    }
                }

                if (!addedTeams.contains(takim2)) { 
                    int takim2Index = findTakimIndex(takim2, takimlar);
                    if (takim2Index != -1) {
                        model.addRow(new Object[]{takimlar[takim2Index], macSayisi[takim2Index], galibiyet[takim2Index], maglubiyet[takim2Index], toplamGol[takim2Index], puan[takim2Index]});
                        addedTeams.add(takim2);
                    }
                }
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        Logger.getLogger(Istatistikler.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(Istatistikler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Istatistikler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Istatistikler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Istatistikler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Istatistikler().setVisible(true);
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
