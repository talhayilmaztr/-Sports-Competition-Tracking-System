
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
    private String takım;
    private JTable jtable;
    private JList jlist;

    public JList getJlist() {
        return jlist;
    }

    public void setJlist(JList jlist) {
        this.jlist = jlist;
    }

    public String getTakım() {
        return takım;
    }

    public void setTakım(String takım) {
        this.takım = takım;
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
                String takımlar = rs.getString("TAKIMLAR");
                String yer = rs.getString("YER");
                String lig = rs.getString("LİG");
                String tarih = rs.getString("TARİH");
                String skor = rs.getString("SKOR");
                model.addRow(new String[]{takımlar, yer, lig, tarih, skor});

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AnaSayfaa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ligler(String sportName, JComboBox combo, JTable table, Connection conn) {
        DefaultTableModel defaultModel = (DefaultTableModel) table.getModel();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + sportName);
            ResultSet rs = ps.executeQuery();
            combo.removeAllItems();
            while (rs.next()) {
                String lig = rs.getString("LİG");

                boolean ligVar = false;

                for (int i = 0; i < combo.getItemCount(); i++) {
                    if (combo.getItemAt(i).equals(lig)) {
                        ligVar = true;
                        break;
                    }
                }

                if (!ligVar) {
                    combo.addItem(lig.toString());
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AnaSayfaa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ligler(String sportName, JList list, Connection conn) {
        ListModel model = list.getModel();
        DefaultListModel<String> defaultModel = new DefaultListModel<>();

        list.setModel(defaultModel);
        defaultModel.removeAllElements();

        try {
            String query = "SELECT * FROM " + sportName.toUpperCase();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String lig = rs.getString("LİG");
                boolean ligVar = false;

                for (int i = 0; i < defaultModel.getSize(); i++) {
                    if (defaultModel.getElementAt(i).equals(lig)) {
                        ligVar = true;
                        break;
                    }
                }

                if (!ligVar) {
                    defaultModel.addElement(lig);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AnaSayfaa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

public class AnaSayfaa extends javax.swing.JFrame {

    FetchData data = new FetchData();
    private Connection conn;
    String path = "file";

    public AnaSayfaa() {
        initComponents();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SporM?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "password");
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfaa.class.getName()).log(Level.SEVERE, null, ex);
        }

        data.ligler("Futbol", jComboBox2, jTable5, conn);
        data.ligler("Voleybol", jComboBox3, jTable6, conn);
        data.ligler("Hentbol", jComboBox4, jTable7, conn);
        data.ligler("Basketbol", jComboBox5, jTable8, conn);

        data.fetchDataForSport("Futbol", jTable5, conn);
        data.fetchDataForSport("Basketbol", jTable8, conn);
        data.fetchDataForSport("Hentbol", jTable7, conn);
        data.fetchDataForSport("Voleybol", jTable6, conn);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem Dosya = new JMenuItem("Dosyaya Aktar");
        JMenuItem Düzen = new JMenuItem("Düzenle");
        JMenuItem Yenile = new JMenuItem("Yenile");

        popupMenu.add(Dosya);
        popupMenu.add(Düzen);
        popupMenu.add(Yenile);

        jTable5.setComponentPopupMenu(popupMenu);
        jTable6.setComponentPopupMenu(popupMenu);
        jTable7.setComponentPopupMenu(popupMenu);
        jTable8.setComponentPopupMenu(popupMenu);

        Dosya.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String path = "/Users/talhayilmaz/Desktop/example.txt";
                if (jTable5.isShowing()) {
                    dosyaYazdır(jTable5, path);
                }
                if (jTable6.isShowing()) {
                    dosyaYazdır(jTable6, path);
                }
                if (jTable7.isShowing()) {
                    dosyaYazdır(jTable7, path);
                }
                if (jTable8.isShowing()) {
                    dosyaYazdır(jTable8, path);
                }

            }
        });

        Düzen.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MüsabakaAdminn m = new MüsabakaAdminn();
                m.setVisible(true);
                AnaSayfaa.this.setVisible(false);
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

        sorgulaBasketbol = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        sorgu = new javax.swing.JTextField();
        sorgulafutbol = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        yenileFutbol1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        vol = new javax.swing.JTextField();
        sorgulaVoleybol = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        yenileVoleybol = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        hent = new javax.swing.JTextField();
        sorgulHenbol = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        yenileHentbol = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        basket = new javax.swing.JTextField();
        sorgulBasketbol = new javax.swing.JButton();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        yennileBasketbol = new javax.swing.JButton();
        label1 = new java.awt.Label();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        seciliDosyayaAktar = new javax.swing.JMenuItem();
        dosyadanAktar = new javax.swing.JMenuItem();
        hepsiniAktar = new javax.swing.JMenuItem();
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

        sorgulaBasketbol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sorgulaBasketbolMouseClicked(evt);
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

        sorgulafutbol.setText("Müsabaka Ara");
        sorgulafutbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sorgulafutbolActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ligler", "Takımlar", "Yer", "Tarih", "Skor" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Filtrele");

        yenileFutbol1.setText("Yenile");
        yenileFutbol1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yenileFutbol1ActionPerformed(evt);
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
                        .addComponent(sorgulafutbol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(yenileFutbol1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sorgu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sorgulafutbol)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(yenileFutbol1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(259, 259, 259))
        );

        sorgulaBasketbol.addTab("Futbol", jPanel2);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TAKIMLAR", "YER", "LİG", "TARİH", "Skor"
            }
        ));
        jScrollPane6.setViewportView(jTable6);

        sorgulaVoleybol.setText("Müsabaka Ara");
        sorgulaVoleybol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sorgulaVoleybolActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ligler", "Takımlar", "Yer", "Tarih", "Skor" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Filtrele");

        yenileVoleybol.setText("Yenile");
        yenileVoleybol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yenileVoleybolActionPerformed(evt);
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
                        .addComponent(sorgulaVoleybol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(yenileVoleybol)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane6)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sorgulaVoleybol)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addComponent(yenileVoleybol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        sorgulaBasketbol.addTab("Voleybol", jPanel3);

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TAKIMLAR", "YER", "LİG", "TARİH", "Skor"
            }
        ));
        jScrollPane7.setViewportView(jTable7);

        sorgulHenbol.setText("Müsabaka Ara");
        sorgulHenbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sorgulHenbolActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ligler", "Takımlar", "Yer", "Tarih", "Skor" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel4.setText("Filtrele");

        yenileHentbol.setText("Yenile");
        yenileHentbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yenileHentbolActionPerformed(evt);
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
                        .addComponent(sorgulHenbol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(yenileHentbol)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sorgulHenbol)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(36, 36, 36)
                .addComponent(yenileHentbol)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        sorgulaBasketbol.addTab("Hentbol", jPanel4);

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TAKIMLAR", "YER", "LİG", "TARİH", "Skor"
            }
        ));
        jScrollPane8.setViewportView(jTable8);

        sorgulBasketbol.setText("Müsabaka Ara");
        sorgulBasketbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sorgulBasketbolActionPerformed(evt);
            }
        });

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ligler", "Takımlar", "Yer", "Tarih", "Skor" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel5.setText("Ligler:");

        yennileBasketbol.setText("Yenile");
        yennileBasketbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yennileBasketbolActionPerformed(evt);
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
                        .addComponent(sorgulBasketbol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(yennileBasketbol)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(basket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sorgulBasketbol)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addComponent(yennileBasketbol)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        sorgulaBasketbol.addTab("Basketbol", jPanel6);

        label1.setBackground(new java.awt.Color(142, 142, 142));
        label1.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        label1.setForeground(new java.awt.Color(200, 232, 101));
        label1.setText(" Spor Müsabakası Takip Sistemi");

        jMenu1.setText("File");

        seciliDosyayaAktar.setText("Hepsini Dosyaya Aktar");
        seciliDosyayaAktar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                seciliDosyayaAktarMousePressed(evt);
            }
        });
        seciliDosyayaAktar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seciliDosyayaAktarActionPerformed(evt);
            }
        });
        jMenu1.add(seciliDosyayaAktar);

        dosyadanAktar.setText("Seçili Satırı Dosyaya Aktar");
        dosyadanAktar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dosyadanAktarMousePressed(evt);
            }
        });
        jMenu1.add(dosyadanAktar);

        hepsiniAktar.setText("Dosyadan Aktar");
        hepsiniAktar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hepsiniAktarMousePressed(evt);
            }
        });
        hepsiniAktar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hepsiniAktarActionPerformed(evt);
            }
        });
        jMenu1.add(hepsiniAktar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ana Sayfa");
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

        jMenu6.setText("Hakkında");
        jMenuBar1.add(jMenu6);

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
                    .addComponent(sorgulaBasketbol)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(sorgulaBasketbol, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sorgulafutbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sorgulafutbolActionPerformed
        String sorgul = sorgu.getText();
        sorgula("Futbol", jTable5, sorgul);
    }//GEN-LAST:event_sorgulafutbolActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String lig2 = (String) jComboBox2.getSelectedItem();
        sorgulaLig(jTable5, "futbol", lig2, jComboBox2);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void yenileFutbol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yenileFutbol1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        model.setRowCount(0);
        data.fetchDataForSport("Futbol", jTable5, conn);
    }//GEN-LAST:event_yenileFutbol1ActionPerformed

    private void sorgulaVoleybolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sorgulaVoleybolActionPerformed
        String sorgul = vol.getText();
        sorgula("Voleybol", jTable6, sorgul);
    }//GEN-LAST:event_sorgulaVoleybolActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        String lig4 = (String) jComboBox3.getSelectedItem();
        sorgulaLig(jTable6, "voleybol", lig4, jComboBox3);
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void yenileVoleybolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yenileVoleybolActionPerformed
        data.fetchDataForSport("Voleybol", jTable6, conn);
    }//GEN-LAST:event_yenileVoleybolActionPerformed

    private void sorgulHenbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sorgulHenbolActionPerformed
        String sorgul = hent.getText();
        sorgula("Hentbol", jTable7, sorgul);
    }//GEN-LAST:event_sorgulHenbolActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        String lig1 = (String) jComboBox4.getSelectedItem();
        sorgulaLig(jTable7, "hentbol", lig1, jComboBox4);
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void yenileHentbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yenileHentbolActionPerformed
        data.fetchDataForSport("Hentbol", jTable7, conn);
    }//GEN-LAST:event_yenileHentbolActionPerformed

    private void sorgulBasketbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sorgulBasketbolActionPerformed
        String sorgul = basket.getText();
        sorgula("Basketbol", jTable8, sorgul);
    }//GEN-LAST:event_sorgulBasketbolActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        String lig3 = (String) jComboBox5.getSelectedItem();
        sorgulaLig(jTable8, "basketbol", lig3, jComboBox5);
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void yennileBasketbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yennileBasketbolActionPerformed
        data.fetchDataForSport("Basketbol", jTable8, conn);
    }//GEN-LAST:event_yennileBasketbolActionPerformed

    private void sorgulaBasketbolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sorgulaBasketbolMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_sorgulaBasketbolMouseClicked

    private void dosyadanAktarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosyadanAktarMousePressed

        dosyaYazdır(jTable5, path);
    }//GEN-LAST:event_dosyadanAktarMousePressed

    private void hepsiniAktarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hepsiniAktarMousePressed
        int selectedIndex = sorgulaBasketbol.getSelectedIndex();
        String selectedTabName = sorgulaBasketbol.getTitleAt(selectedIndex);

        DosyaOku(selectedTabName,path);

    }//GEN-LAST:event_hepsiniAktarMousePressed

    private void hepsiniAktarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hepsiniAktarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hepsiniAktarActionPerformed

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

    private void seciliDosyayaAktarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seciliDosyayaAktarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seciliDosyayaAktarActionPerformed

    private void seciliDosyayaAktarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seciliDosyayaAktarMousePressed
        
                 if (jTable5.isShowing()) {
                    dosyaYazdırhepsi(jTable5, path);
                }
                if (jTable6.isShowing()) {
                    dosyaYazdırhepsi(jTable6, path);
                }
                if (jTable7.isShowing()) {
                    dosyaYazdırhepsi(jTable7, path);
                }
                if (jTable8.isShowing()) {
                    dosyaYazdırhepsi(jTable8, path);
                }


    }//GEN-LAST:event_seciliDosyayaAktarMousePressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
       
          MüsabakaAdminn müs = new MüsabakaAdminn();
        this.setVisible(false);
        müs.setVisible(true);
    }//GEN-LAST:event_jMenuItem2MousePressed
    public void dosyaYazdır(JTable table, String file) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Lütfen bir satır seçin.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.write(model.getValueAt(row, i).toString());
                if (i < model.getColumnCount() - 1) {
                    writer.write(","); 
                }
            }
            writer.write("\n");
            writer.close();
            System.out.println("Müsabaka dosyaya aktarıldı.");

        } catch (IOException ex) {
            System.err.println("Tablo dosyaya aktarılırken bir hata oluştu: " + ex.getMessage());
        }
    }
    public void DosyaOku(String sprtName, String file) {
    String satir;
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        while ((satir = reader.readLine()) != null) {
            String[] veri = satir.split(",");
            
            for (String data : veri) {
                System.out.print(data.trim() + " "); 
            }
            System.out.println();
        }
        
        
    } catch (IOException ex) {
        Logger.getLogger(AnaSayfaa.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public void dosyaYazdırhepsi(JTable table, String file) {
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
        JOptionPane.showMessageDialog(null, "Tablodaki tüm satırlar dosyaya aktarıldı.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);

    } catch (IOException ex) {
        System.err.println("Tablo dosyaya aktarılırken bir hata oluştu: " + ex.getMessage());
    }
}


    private void sorgulaLig(JTable table, String sprtName, String ligName, JComboBox combo) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try {
            String query = "SELECT * FROM " + sprtName.toUpperCase();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);
            combo.addItem("Tümü");
            while (rs.next()) {
                String takımlar = rs.getString("TAKIMLAR");
                String yer = rs.getString("YER");
                String lig = rs.getString("LİG");
                String tarih = rs.getString("TARİH");
                String skor = rs.getString("SKOR");
                if (ligName != null && ligName.contains(lig) || "Tümü".equals(ligName)) {
                    model.addRow(new String[]{takımlar, yer, lig, tarih, skor});
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AnaSayfaa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sorgula(String sportName, JTable table, String keyword) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        PreparedStatement ps = null;
        try {
            String query = "SELECT * FROM " + sportName.toUpperCase();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String takımlar = rs.getString("TAKIMLAR");
                String yer = rs.getString("YER");
                String lig = rs.getString("LİG");
                String tarih = rs.getString("TARİH");
                String skor = rs.getString("SKOR");
                if (takımlar.toLowerCase().contains(keyword) || yer.toLowerCase().contains(keyword) || lig.toLowerCase().contains(keyword) || tarih.contains(keyword) || skor.contains(keyword)) {
                    model.addRow(new String[]{takımlar, yer, lig, tarih, skor});
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AnaSayfaa.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(AnaSayfaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnaSayfaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnaSayfaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnaSayfaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AnaSayfaa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField basket;
    private javax.swing.JMenuItem dosyadanAktar;
    private javax.swing.JTextField hent;
    private javax.swing.JMenuItem hepsiniAktar;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JMenuItem seciliDosyayaAktar;
    private javax.swing.JTextField sorgu;
    private javax.swing.JButton sorgulBasketbol;
    private javax.swing.JButton sorgulHenbol;
    private javax.swing.JTabbedPane sorgulaBasketbol;
    private javax.swing.JButton sorgulaVoleybol;
    private javax.swing.JButton sorgulafutbol;
    private javax.swing.JTextField vol;
    private javax.swing.JButton yenileFutbol1;
    private javax.swing.JButton yenileHentbol;
    private javax.swing.JButton yenileVoleybol;
    private javax.swing.JButton yennileBasketbol;
    // End of variables declaration//GEN-END:variables
}
