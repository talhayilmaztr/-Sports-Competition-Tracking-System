
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

class FetchData {

    Connection conn;
    private String team;
    private JTable jtable;
    private JList jlist;

    public JList getJlist() {
        return jlist;
    }

    public void setJlist(JList jlist) {
        this.jlist = jlist;
    }

    public String getTakım() {
        return team;
    }

    public void setTakım(String takım) {
        this.team = takım;
    }

    public JTable getJtable() {
        return jtable;
    }

    public void setJtable(JTable jtable) {
        this.jtable = jtable;
    }

    public void fetchDataForSport(String sportName, JTable table, Connection conn) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM " + sportName.toUpperCase());
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                String teams = rs.getString("TAKIMLAR");
                String place = rs.getString("YER");
                String league = rs.getString("LİG");
                String date = rs.getString("TARİH");
                String score = rs.getString("SKOR");
                model.addRow(new String[]{teams, place, league, date, score});

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadLeagues(String sportName, JComboBox combo, Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + sportName);
            ResultSet rs = ps.executeQuery();
            combo.removeAllItems();
            while (rs.next()) {
                String league = rs.getString("LİG");

                boolean leagueExists = false;

                for (int i = 0; i < combo.getItemCount(); i++) {
                    if (combo.getItemAt(i).equals(league)) {
                        leagueExists = true;
                        break;
                    }
                }

                if (!leagueExists) {
                    combo.addItem(league.toString());
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leagues(String sportName, JList list, Connection conn) {
        ListModel model = list.getModel();
        DefaultListModel<String> defaultModel = new DefaultListModel<>();

        list.setModel(defaultModel);
        defaultModel.removeAllElements();

        try {
            String query = "SELECT * FROM " + sportName.toUpperCase();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String league = rs.getString("LİG");
                boolean leagueExist = false;

                for (int i = 0; i < defaultModel.getSize(); i++) {
                    if (defaultModel.getElementAt(i).equals(league)) {
                        leagueExist = true;
                        break;
                    }
                }

                if (!leagueExist) {
                    defaultModel.addElement(league);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

public class Main extends javax.swing.JFrame {

    FetchData data = new FetchData();
    private Connection conn;
    String path = "file";

    public Main() {
        initComponents();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SporM?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "password");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        data.loadLeagues("Football", jComboBox2, conn);
        data.loadLeagues("Volleyball", jComboBox3,  conn);
        data.loadLeagues("Handball", jComboBox4,  conn);
        data.loadLeagues("Basketball", jComboBox5, conn);

        data.fetchDataForSport("Futbol", jTable5, conn);
        data.fetchDataForSport("BAsketbo", jTable8, conn);
        data.fetchDataForSport("Handball", jTable7, conn);
        data.fetchDataForSport("Volleyball", jTable6, conn);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem fileMenuItem = new JMenuItem("Export to File");
        JMenuItem editMenuItem = new JMenuItem("Edit");
        JMenuItem refreshMenuItem = new JMenuItem("Refresh");

        popupMenu.add(fileMenuItem);
        popupMenu.add(editMenuItem);
        popupMenu.add(refreshMenuItem);

        jTable5.setComponentPopupMenu(popupMenu);
        jTable6.setComponentPopupMenu(popupMenu);
        jTable7.setComponentPopupMenu(popupMenu);
        jTable8.setComponentPopupMenu(popupMenu);

        fileMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String path = "file_path";
                if (jTable5.isShowing()) {
                    printToFile(jTable5, path);
                }
                if (jTable6.isShowing()) {
                    printToFile(jTable6, path);
                }
                if (jTable7.isShowing()) {
                    printToFile(jTable7, path);
                }
                if (jTable8.isShowing()) {
                    printToFile(jTable8, path);
                }

            }
        });

        editMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MatchAdmin m = new MatchAdmin();
                m.setVisible(true);
                Main.this.setVisible(false);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        sorgu = new javax.swing.JTextField();
        searchMatchF = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        refresh1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        vol = new javax.swing.JTextField();
        searchMatchV = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        refresh2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        hent = new javax.swing.JTextField();
        searchMatchH = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        refresh3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        basket = new javax.swing.JTextField();
        searchMatchB = new javax.swing.JButton();
        jComboBox5 = new javax.swing.JComboBox<>();
        filter = new javax.swing.JLabel();
        refresh4 = new javax.swing.JButton();
        label1 = new java.awt.Label();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exportAll = new javax.swing.JMenuItem();
        exportSelected = new javax.swing.JMenuItem();
        importFromFile = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPaneMouseClicked(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TAKIMLAR", "YER", "LİG", "TARİH", "Skor"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        searchMatchF.setText("Search");
        searchMatchF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMatchFActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ligler", "Takımlar", "Yer", "Tarih", "Skor" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Filtrele");

        refresh1.setText("Refresh");
        refresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(sorgu, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(searchMatchF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(refresh1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sorgu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchMatchF)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refresh1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(259, 259, 259))
        );

        tabbedPane.addTab("Futbol", jPanel2);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TAKIMLAR", "YER", "LİG", "TARİH", "Skor"
            }
        ));
        jScrollPane6.setViewportView(jTable6);

        searchMatchV.setText("Search");
        searchMatchV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMatchVActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ligler", "Takımlar", "Yer", "Tarih", "Skor" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Filtrele");

        refresh2.setText("Refresh");
        refresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(vol, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(searchMatchV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(refresh2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane6)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchMatchV)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addComponent(refresh2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Voleybol", jPanel3);

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TAKIMLAR", "YER", "LİG", "TARİH", "Skor"
            }
        ));
        jScrollPane7.setViewportView(jTable7);

        searchMatchH.setText("Search");
        searchMatchH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMatchHActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ligler", "Takımlar", "Yer", "Tarih", "Skor" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel4.setText("Filtrele");

        refresh3.setText("Refresh");
        refresh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(hent, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(searchMatchH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(refresh3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchMatchH)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(36, 36, 36)
                .addComponent(refresh3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Hentbol", jPanel4);

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TAKIMLAR", "YER", "LİG", "TARİH", "Skor"
            }
        ));
        jScrollPane8.setViewportView(jTable8);

        searchMatchB.setText("Search");
        searchMatchB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMatchBActionPerformed(evt);
            }
        });

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Leagues", "Takımlar", "Yer", "Tarih", "Skor" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        filter.setText("Filter:");

        refresh4.setText("Refresh");
        refresh4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(basket, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(searchMatchB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filter)
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(refresh4)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(basket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchMatchB)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filter))
                .addGap(26, 26, 26)
                .addComponent(refresh4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Basketbol", jPanel6);

        label1.setBackground(new java.awt.Color(142, 142, 142));
        label1.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        label1.setForeground(new java.awt.Color(200, 232, 101));
        label1.setText(" Sports Match Tracking System");

        jMenu1.setText("File");

        exportAll.setText("Export All");
        exportAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exportAllMousePressed(evt);
            }
        });
        exportAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportAllActionPerformed(evt);
            }
        });
        jMenu1.add(exportAll);

        exportSelected.setText("Export Selected");
        exportSelected.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exportSelectedMousePressed(evt);
            }
        });
        jMenu1.add(exportSelected);

        importFromFile.setText("Import From File");
        importFromFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                importFromFileMousePressed(evt);
            }
        });
        importFromFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importFromFileActionPerformed(evt);
            }
        });
        jMenu1.add(importFromFile);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Main Page");
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
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Müabaka Sil");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });
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
                    .addComponent(tabbedPane)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchMatchFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMatchFActionPerformed
        String sorgul = sorgu.getText();
        search("Futbol", jTable5, sorgul);
    }//GEN-LAST:event_searchMatchFActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String lig2 = (String) jComboBox2.getSelectedItem();
        queryLeague(jTable5, "futbol", lig2, jComboBox2);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void refresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        model.setRowCount(0);
        data.fetchDataForSport("Futbol", jTable5, conn);
    }//GEN-LAST:event_refresh1ActionPerformed

    private void searchMatchVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMatchVActionPerformed
        String sorgul = vol.getText();
        search("Voleybol", jTable6, sorgul);
    }//GEN-LAST:event_searchMatchVActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        String lig4 = (String) jComboBox3.getSelectedItem();
        queryLeague(jTable6, "voleybol", lig4, jComboBox3);
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void refresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh2ActionPerformed
        data.fetchDataForSport("Voleybol", jTable6, conn);
    }//GEN-LAST:event_refresh2ActionPerformed

    private void searchMatchHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMatchHActionPerformed
        String sorgul = hent.getText();
        search("Hentbol", jTable7, sorgul);
    }//GEN-LAST:event_searchMatchHActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        String lig1 = (String) jComboBox4.getSelectedItem();
        queryLeague(jTable7, "hentbol", lig1, jComboBox4);
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void refresh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh3ActionPerformed
        data.fetchDataForSport("Hentbol", jTable7, conn);
    }//GEN-LAST:event_refresh3ActionPerformed

    private void searchMatchBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMatchBActionPerformed
        String sorgul = basket.getText();
        search("Basketbol", jTable8, sorgul);
    }//GEN-LAST:event_searchMatchBActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        String lig3 = (String) jComboBox5.getSelectedItem();
        queryLeague(jTable8, "basketbol", lig3, jComboBox5);
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void refresh4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh4ActionPerformed
        data.fetchDataForSport("Basketbol", jTable8, conn);
    }//GEN-LAST:event_refresh4ActionPerformed

    private void tabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabbedPaneMouseClicked

    private void exportSelectedMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportSelectedMousePressed

        printToFile(jTable5, path);
    }//GEN-LAST:event_exportSelectedMousePressed

    private void importFromFileMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importFromFileMousePressed
        int selectedIndex = tabbedPane.getSelectedIndex();
        String selectedTabName = tabbedPane.getTitleAt(selectedIndex);

        readFile(selectedTabName, path);

    }//GEN-LAST:event_importFromFileMousePressed

    private void importFromFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importFromFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_importFromFileActionPerformed

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

        int cevap = JOptionPane.showConfirmDialog(null, "Çıkış Yapmak istediğinize emin misiniz?", "ÇIKIŞ", JOptionPane.YES_NO_OPTION);

        if (cevap == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            login.setVisible(true);
        }
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed

    }//GEN-LAST:event_jMenu5ActionPerformed

    private void exportAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exportAllActionPerformed

    private void exportAllMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportAllMousePressed

        if (jTable5.isShowing()) {
            printAllToFile(jTable5, path);
        }
        if (jTable6.isShowing()) {
            printAllToFile(jTable6, path);
        }
        if (jTable7.isShowing()) {
            printAllToFile(jTable7, path);
        }
        if (jTable8.isShowing()) {
            printAllToFile(jTable8, path);
        }


    }//GEN-LAST:event_exportAllMousePressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed

        MatchAdmin m = new MatchAdmin();
        this.setVisible(false);
        m.setVisible(true);
    }//GEN-LAST:event_jMenuItem2MousePressed
    public void printToFile(JTable table, String file) {
    int row = table.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(null, "Please select a row.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        for (int i = 0; i < model.getColumnCount(); i++) {
            writer.write(model.getValueAt(row, i).toString());
            if (i < model.getColumnCount() - 1) {
                writer.write(",");
            }
        }
        writer.write("\n");
        writer.close();
        System.out.println("Match transferred to file.");

    } catch (IOException ex) {
        System.err.println("An error occurred while transferring the table to the file: " + ex.getMessage());
    }
}

public void readFile(String sprtName, String file) {
    String line;
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            for (String d : data) {
                System.out.print(d.trim() + " ");
            }
            System.out.println();
        }

    } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public void printAllToFile(JTable table, String file) {
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                writer.write(model.getValueAt(row, col).toString());
                if (col < model.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.write("\n");
        }

        writer.close();
        JOptionPane.showMessageDialog(null, "All rows in the table have been transferred to the file.", "Information", JOptionPane.INFORMATION_MESSAGE);

    } catch (IOException ex) {
        System.err.println("An error occurred while transferring the table to the file: " + ex.getMessage());
    }
}


    private void queryLeague(JTable table, String sprtName, String leagueName, JComboBox combo) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try {
            String query = "SELECT * FROM " + sprtName.toUpperCase();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);
            combo.addItem("All");
            while (rs.next()) {
                String teams = rs.getString("TAKIMLAR");
                String place = rs.getString("YER");
                String league = rs.getString("LİG");
                String date = rs.getString("TARİH");
                String score = rs.getString("SKOR");

                if (leagueName != null && leagueName.contains(league) || "All".equals(leagueName)) {
                model.addRow(new String[]{teams, place, league, date, score});
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void search(String sportName, JTable table, String keyword) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        PreparedStatement ps = null;
        try {
            String query = "SELECT * FROM " + sportName.toUpperCase();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               String teams = rs.getString("TAKIMLAR");
                String place = rs.getString("YER");
                String league = rs.getString("LİG");
                String date = rs.getString("TARİH");
                String score = rs.getString("SKOR");

                if (teams.toLowerCase().contains(keyword) || place.toLowerCase().contains(keyword) || league.toLowerCase().contains(keyword) || date.contains(keyword) || score.contains(keyword)) {
                model.addRow(new String[]{teams, place, league, date, score});
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField basket;
    private javax.swing.JMenuItem exportAll;
    private javax.swing.JMenuItem exportSelected;
    private javax.swing.JLabel filter;
    private javax.swing.JTextField hent;
    private javax.swing.JMenuItem importFromFile;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private java.awt.Label label1;
    private javax.swing.JButton refresh1;
    private javax.swing.JButton refresh2;
    private javax.swing.JButton refresh3;
    private javax.swing.JButton refresh4;
    private javax.swing.JButton searchMatchB;
    private javax.swing.JButton searchMatchF;
    private javax.swing.JButton searchMatchH;
    private javax.swing.JButton searchMatchV;
    private javax.swing.JTextField sorgu;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField vol;
    // End of variables declaration//GEN-END:variables
}
