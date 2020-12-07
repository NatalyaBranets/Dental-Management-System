package com.softserve.demo;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;

public class Payment extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField textFieldTreatDisease;
    private JTextField textFieldDentalBridge;
    private JTextField textFieldDentImplant;
    private JTextField textFieldToothExc;
    private JTextField textFieldCosmetic;
    private JTextField textFieldCrown;
    private JTextField textFieldRootCanal;
    private JTextField textFieldEmDentCare;
    private JTextField textFieldSubtotal;
    private JTextField searchTxtFirstName;
    private JTextField searchTxtLastName;

    private JLabel labelFirstName;
    private JLabel labelLastName;
    private JLabel labelSubtotal;

    private JCheckBox chckbxDentalBridges;
    private JCheckBox chckbxDentalImplants;
    private JCheckBox chckbxTreatmentOfPeriodontal;
    private JCheckBox chckbxToothExtraction;
    private JCheckBox chckbxCosmeticDen;
    private JCheckBox chckbxDentalCrowns;
    private JCheckBox chckbxRootCanal;
    private JCheckBox chckbxEmergencyDentalCare;

    private JButton buttonSubmit;
    private JButton buttonReset;
    private JButton buttonBack;

    private JList<String> myJListFirstName;
    private DefaultListModel<String> defaultListModelFirstName;
    private DefaultListModel<String> defaultListModelLastName;
    private JList<String> myJListLastName;

    private JPanel panelFLName;
    private JPanel panelService;
    private JPanel panelImage;
    private JPanel panelTotal;
    private JPanel panelButton;

    private JScrollPane scrollPaneFirstName;
    private JScrollPane scrollPaneLastName;

    private JLabel labelImage;

    private Image imgPay = new ImageIcon(Payment.class.getResource("/Icon/tooth.png")).getImage().getScaledInstance(279,
            350, Image.SCALE_SMOOTH);

    private static Logger log = Logger.getLogger(Payment.class.getName());

    public Payment() {
        guiPayment();
        bindDataFirstName();
        bindDataLastName();

    }

    public void guiPayment() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 936, 557);
        setTitle("Payment service");
        contentPane = new JPanel();
        contentPane.setBackground((new Color(32, 178, 170)));
        setContentPane(contentPane);
        setResizable(false);
        contentPane.setLayout(null);

        panelFLName = new JPanel();
        panelFLName.setBounds(5, 5, 614, 92);
        panelFLName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        contentPane.add(panelFLName);
        panelFLName.setLayout(null);

        panelFLName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                panelFLNameMouseClicked(ev);
            }
        });

        labelFirstName = new JLabel("First Name:");
        labelFirstName.setFont(new Font("Tahoma", Font.BOLD, 13));
        labelFirstName.setBounds(27, 13, 97, 23);
        panelFLName.add(labelFirstName);

        labelLastName = new JLabel("Last Name:");
        labelLastName.setFont(new Font("Tahoma", Font.BOLD, 13));
        labelLastName.setBounds(324, 13, 97, 23);
        panelFLName.add(labelLastName);

        panelService = new JPanel();
        panelService.setBounds(5, 101, 614, 269);
        panelService.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        contentPane.add(panelService);
        panelService.setLayout(null);

        chckbxDentalBridges = new JCheckBox("DENTAL BRIDGES");
        chckbxDentalBridges.setBounds(24, 20, 272, 25);
        panelService.add(chckbxDentalBridges);

        chckbxDentalBridges.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (chckbxDentalBridges.isSelected() == true) {
                    try {
                        ResultSet rs = find(chckbxDentalBridges.getText());
                        while (rs.next()) {
                            textFieldDentalBridge.setText(rs.getString("price"));
                            textFieldDentalBridge.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        log.log(Level.SEVERE, null, ex.getStackTrace());
                    }
                } else {
                    textFieldDentalBridge.setText("0");
                    textFieldDentalBridge.setEditable(false);
                }
            }
        });

        chckbxDentalImplants = new JCheckBox("DENTAL IMPLANTS");
        chckbxDentalImplants.setBounds(24, 50, 272, 25);
        panelService.add(chckbxDentalImplants);

        chckbxDentalImplants.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (chckbxDentalImplants.isSelected() == true) {
                    try {
                        ResultSet rs = find(chckbxDentalImplants.getText());
                        while (rs.next()) {
                            textFieldDentImplant.setText(rs.getString("price"));
                            textFieldDentImplant.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        log.log(Level.SEVERE, null, ex.getStackTrace());
                    }
                } else {
                    textFieldDentImplant.setText("0");
                    textFieldDentImplant.setEditable(false);
                }
            }
        });

        chckbxTreatmentOfPeriodontal = new JCheckBox("TREATMENT OF PERIODONTAL DISEASE");
        chckbxTreatmentOfPeriodontal.setBounds(24, 80, 272, 25);
        panelService.add(chckbxTreatmentOfPeriodontal);

        chckbxTreatmentOfPeriodontal.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (chckbxTreatmentOfPeriodontal.isSelected() == true) {
                    try {
                        ResultSet rs = find(chckbxTreatmentOfPeriodontal.getText());
                        while (rs.next()) {
                            textFieldTreatDisease.setText(rs.getString("price"));
                            textFieldTreatDisease.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        log.log(Level.SEVERE, null, ex.getStackTrace());
                    }
                } else {
                    textFieldTreatDisease.setText("0");
                    textFieldTreatDisease.setEditable(false);
                }
            }
        });

        chckbxToothExtraction = new JCheckBox("TOOTH EXTRACTION");
        chckbxToothExtraction.setBounds(24, 110, 272, 25);
        panelService.add(chckbxToothExtraction);

        chckbxToothExtraction.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (chckbxToothExtraction.isSelected() == true) {
                    try {
                        ResultSet rs = find(chckbxToothExtraction.getText());
                        while (rs.next()) {
                            textFieldToothExc.setText(rs.getString("price"));
                            textFieldToothExc.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        log.log(Level.SEVERE, null, ex.getStackTrace());
                    }
                } else {
                    textFieldToothExc.setText("0");
                    textFieldToothExc.setEditable(false);
                }
            }
        });

        chckbxCosmeticDen = new JCheckBox("COSMETIC DENTISTRY");
        chckbxCosmeticDen.setBounds(24, 140, 272, 25);
        panelService.add(chckbxCosmeticDen);

        chckbxCosmeticDen.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (chckbxCosmeticDen.isSelected() == true) {
                    try {
                        ResultSet rs = find(chckbxCosmeticDen.getText());
                        while (rs.next()) {
                            textFieldCosmetic.setText(rs.getString("price"));
                            textFieldCosmetic.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        log.log(Level.SEVERE, null, ex.getStackTrace());
                    }
                } else {
                    textFieldCosmetic.setText("0");
                    textFieldCosmetic.setEditable(false);
                }
            }
        });

        chckbxDentalCrowns = new JCheckBox("DENTAL CROWNS");
        chckbxDentalCrowns.setBounds(24, 170, 272, 25);
        panelService.add(chckbxDentalCrowns);

        chckbxDentalCrowns.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (chckbxDentalCrowns.isSelected() == true) {
                    try {
                        ResultSet rs = find(chckbxDentalCrowns.getText());
                        while (rs.next()) {
                            textFieldCrown.setText(rs.getString("price"));
                            textFieldCrown.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        log.log(Level.SEVERE, null, ex.getStackTrace());
                    }
                } else {
                    textFieldCrown.setText("0");
                    textFieldCrown.setEditable(false);
                }
            }
        });

        chckbxRootCanal = new JCheckBox("ROOT CANAL");
        chckbxRootCanal.setBounds(24, 200, 272, 25);
        panelService.add(chckbxRootCanal);

        chckbxRootCanal.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (chckbxRootCanal.isSelected() == true) {
                    try {
                        ResultSet rs = find(chckbxRootCanal.getText());
                        while (rs.next()) {
                            textFieldRootCanal.setText(rs.getString("price"));
                            textFieldRootCanal.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        log.log(Level.SEVERE, null, ex.getStackTrace());
                    }
                } else {
                    textFieldRootCanal.setText("0");
                    textFieldRootCanal.setEditable(false);
                }
            }
        });

        chckbxEmergencyDentalCare = new JCheckBox("EMERGENCY DENTAL CARE");
        chckbxEmergencyDentalCare.setBounds(24, 230, 272, 25);
        panelService.add(chckbxEmergencyDentalCare);

        chckbxEmergencyDentalCare.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (chckbxEmergencyDentalCare.isSelected() == true) {
                    try {
                        ResultSet rs = find(chckbxEmergencyDentalCare.getText());
                        while (rs.next()) {
                            textFieldEmDentCare.setText(rs.getString("price"));
                            textFieldEmDentCare.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        log.log(Level.SEVERE, null, ex.getStackTrace());
                    }
                } else {
                    textFieldEmDentCare.setText("0");
                    textFieldEmDentCare.setEditable(false);
                }
            }
        });

        textFieldTreatDisease = new JTextField();
        textFieldTreatDisease.setColumns(10);
        textFieldTreatDisease.setText("0");
        textFieldTreatDisease.setEditable(false);
        textFieldTreatDisease.setBackground(Color.white);
        textFieldTreatDisease.setHorizontalAlignment(JTextField.CENTER);
        textFieldTreatDisease.setBounds(424, 81, 154, 22);
        panelService.add(textFieldTreatDisease);

        textFieldDentalBridge = new JTextField();
        textFieldDentalBridge.setColumns(10);
        textFieldDentalBridge.setText("0");
        textFieldDentalBridge.setBackground(Color.white);
        textFieldDentalBridge.setEditable(false);
        textFieldDentalBridge.setHorizontalAlignment(JTextField.CENTER);
        textFieldDentalBridge.setBounds(424, 21, 154, 22);
        panelService.add(textFieldDentalBridge);

        textFieldDentImplant = new JTextField();
        textFieldDentImplant.setColumns(10);
        textFieldDentImplant.setText("0");
        textFieldDentImplant.setBackground(Color.white);
        textFieldDentImplant.setEditable(false);
        textFieldDentImplant.setHorizontalAlignment(JTextField.CENTER);
        textFieldDentImplant.setBounds(424, 51, 154, 22);
        panelService.add(textFieldDentImplant);

        textFieldToothExc = new JTextField();
        textFieldToothExc.setColumns(10);
        textFieldToothExc.setText("0");
        textFieldToothExc.setEditable(false);
        textFieldToothExc.setBackground(Color.white);
        textFieldToothExc.setHorizontalAlignment(JTextField.CENTER);
        textFieldToothExc.setBounds(424, 111, 154, 22);
        panelService.add(textFieldToothExc);

        textFieldCosmetic = new JTextField();
        textFieldCosmetic.setColumns(10);
        textFieldCosmetic.setText("0");
        textFieldCosmetic.setEditable(false);
        textFieldCosmetic.setBackground(Color.white);
        textFieldCosmetic.setHorizontalAlignment(JTextField.CENTER);
        textFieldCosmetic.setBounds(424, 141, 154, 22);
        panelService.add(textFieldCosmetic);

        textFieldCrown = new JTextField();
        textFieldCrown.setColumns(10);
        textFieldCrown.setText("0");
        textFieldCrown.setEditable(false);
        textFieldCrown.setBackground(Color.white);
        textFieldCrown.setHorizontalAlignment(JTextField.CENTER);
        textFieldCrown.setBounds(424, 171, 154, 22);
        panelService.add(textFieldCrown);

        textFieldRootCanal = new JTextField();
        textFieldRootCanal.setColumns(10);
        textFieldRootCanal.setText("0");
        textFieldRootCanal.setEditable(false);
        textFieldRootCanal.setBackground(Color.white);
        textFieldRootCanal.setHorizontalAlignment(JTextField.CENTER);
        textFieldRootCanal.setBounds(424, 201, 154, 22);
        panelService.add(textFieldRootCanal);

        textFieldEmDentCare = new JTextField();
        textFieldEmDentCare.setColumns(10);
        textFieldEmDentCare.setText("0");
        textFieldEmDentCare.setEditable(false);
        textFieldEmDentCare.setBackground(Color.white);
        textFieldEmDentCare.setHorizontalAlignment(JTextField.CENTER);
        textFieldEmDentCare.setBounds(424, 231, 154, 22);
        panelService.add(textFieldEmDentCare);

        panelImage = new JPanel();
        panelImage.setBounds(627, 5, 279, 445);
        contentPane.add(panelImage);
        panelImage.setLayout(null);

        labelImage = new JLabel("");
        labelImage.setBounds(0, 0, 279, 445);
        labelImage.setIcon(new ImageIcon(imgPay));
        labelImage.setVerticalAlignment(JLabel.CENTER);
        panelImage.add(labelImage);

        panelButton = new JPanel();
        panelButton.setBounds(5, 452, 901, 45);
        panelButton.setBackground((new Color(32, 178, 170)));
        contentPane.add(panelButton);
        panelButton.setLayout(null);

        buttonSubmit = new JButton("SUBMIT");
        buttonSubmit.setFont(new Font("Tahoma", Font.BOLD, 15));
        buttonSubmit.setBounds(148, 13, 107, 27);
        panelButton.add(buttonSubmit);

        buttonReset = new JButton("RESET");
        buttonReset.setFont(new Font("Tahoma", Font.BOLD, 15));
        buttonReset.setBounds(416, 13, 107, 27);
        panelButton.add(buttonReset);

        buttonBack = new JButton("BACK");
        buttonBack.setFont(new Font("Tahoma", Font.BOLD, 15));
        buttonBack.setBounds(662, 13, 107, 27);
        panelButton.add(buttonBack);

        panelTotal = new JPanel();
        panelTotal.setLayout(null);
        panelTotal.setBounds(5, 373, 614, 77);
        panelTotal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        contentPane.add(panelTotal);

        labelSubtotal = new JLabel("SubTotal:");
        labelSubtotal.setFont(new Font("Tahoma", Font.BOLD, 13));
        labelSubtotal.setBounds(26, 27, 68, 25);
        panelTotal.add(labelSubtotal);

        textFieldSubtotal = new JTextField();
        textFieldSubtotal.setColumns(10);
        textFieldSubtotal.setBounds(127, 28, 451, 22);
        panelTotal.add(textFieldSubtotal);

        searchTxtFirstName = new JTextField();
        searchTxtFirstName.setBounds(130, 13, 159, 22);
        panelFLName.add(searchTxtFirstName);
        searchTxtFirstName.setColumns(10);
        searchTxtFirstName.setFont(new java.awt.Font("Tahoma", 0, 13));

        searchTxtFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtKeyReleased(evt);
            }
        });

        scrollPaneFirstName = new JScrollPane();
        scrollPaneFirstName.setBounds(130, 36, 159, 43);
        panelFLName.add(scrollPaneFirstName);

        defaultListModelFirstName = new DefaultListModel<>();
        myJListFirstName = new JList<>();
        myJListFirstName.setVisible(true);
        scrollPaneFirstName.setViewportView(myJListFirstName);
        scrollPaneFirstName.setVisible(false);
        myJListFirstName.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        myJListFirstName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myJListFirstNameMouseClicked(evt);
            }
        });

        searchTxtLastName = new JTextField();
        searchTxtLastName.setColumns(10);
        searchTxtLastName.setBounds(422, 13, 159, 22);
        panelFLName.add(searchTxtLastName);
        searchTxtLastName.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        searchTxtLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtLastNameKeyReleased(evt);
            }
        });

        //

        scrollPaneLastName = new JScrollPane();
        scrollPaneLastName.setBounds(422, 36, 159, 43);
        panelFLName.add(scrollPaneLastName);

        defaultListModelLastName = new DefaultListModel<>();
        myJListLastName = new JList<>();
        scrollPaneLastName.setViewportView(myJListLastName);
        scrollPaneLastName.setVisible(false);
        myJListLastName.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        myJListLastName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myJListLastNameMouseClicked(evt);
            }
        });

        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int[] cost = new int[9];
                cost[0] = Integer.parseInt(textFieldDentalBridge.getText());
                cost[1] = Integer.parseInt(textFieldDentImplant.getText());
                cost[2] = Integer.parseInt(textFieldTreatDisease.getText());
                cost[3] = Integer.parseInt(textFieldToothExc.getText());
                cost[4] = Integer.parseInt(textFieldCosmetic.getText());
                cost[5] = Integer.parseInt(textFieldCrown.getText());
                cost[6] = Integer.parseInt(textFieldRootCanal.getText());
                cost[7] = Integer.parseInt(textFieldEmDentCare.getText());

                cost[8] = cost[0] + cost[1] + cost[2] + cost[3] + cost[4] + cost[5] + cost[6] + cost[7];
                String subtotal = String.valueOf(cost[8]);
                textFieldSubtotal.setText(subtotal);

                if (JOptionPane.showConfirmDialog(null,
                        "First Name: \t\t\t" + searchTxtFirstName.getText() + "\n\n" + "Last Name: "
                                + searchTxtLastName.getText() + "\n\n" + "\n=========================\n"
                                + "Total amount: \t\t\t" + textFieldSubtotal.getText() + "\n\n"
                                + "\n=========================\n",
                        "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    insertAmountIntoSQL();
                }

            }
        });

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldDentalBridge.setText("0");
                textFieldDentImplant.setText("0");
                textFieldTreatDisease.setText("0");
                textFieldToothExc.setText("0");
                textFieldCosmetic.setText("0");
                textFieldCrown.setText("0");
                textFieldRootCanal.setText("0");
                textFieldEmDentCare.setText("0");

                searchTxtFirstName.setText("");
                searchTxtLastName.setText("");

                textFieldSubtotal.setText("");

                chckbxDentalBridges.setSelected(false);
                chckbxDentalImplants.setSelected(false);
                chckbxTreatmentOfPeriodontal.setSelected(false);
                chckbxToothExtraction.setSelected(false);
                chckbxCosmeticDen.setSelected(false);
                chckbxDentalCrowns.setSelected(false);
                chckbxRootCanal.setSelected(false);
                chckbxEmergencyDentalCare.setSelected(false);
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage home = new HomePage();
                home.setVisible(true);
                Payment.this.dispose();
            }
        });

    }

    public ResultSet find(String s) {
        Connection conn = ConnectionData.getConnectionData();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT price FROM service WHERE name = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, s);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex.getStackTrace());
        }
        return rs;
    }

    // Search First Name

    public ArrayList<String> getItemFirstName() {
        ArrayList<String> listFirstName = new ArrayList<>();
        Connection conn = ConnectionData.getConnectionData();
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM patients";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("first_name");
                listFirstName.add(name);
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex.getStackTrace());
        }
        return listFirstName;
    }

    private void bindDataFirstName() {
        getItemFirstName().stream().forEach((star) -> {
            defaultListModelFirstName.addElement(star);
        });
        myJListFirstName.setModel(defaultListModelFirstName);
        myJListFirstName.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }


    private void searchFilterFirstName(String searchTerm) {
        DefaultListModel<String> filteredItems = new DefaultListModel<>();
        ArrayList<String> stars = getItemFirstName();

        stars.stream().forEach((star) -> {
            String starName = star.toString().toLowerCase();
            if (starName.contains(searchTerm.toLowerCase())) {
                filteredItems.addElement(star);
            }
        });
        defaultListModelFirstName = filteredItems;
        myJListFirstName.setModel(defaultListModelFirstName);

    }

    private void searchTxtKeyReleased(KeyEvent evt) {
        searchFilterFirstName(searchTxtFirstName.getText());
        scrollPaneFirstName.setVisible(true);
    }

    private void myJListFirstNameMouseClicked(MouseEvent evt) {
        if (!myJListFirstName.isSelectionEmpty()) {
            String pat = defaultListModelFirstName.getElementAt(myJListFirstName.getSelectedIndex());
            searchTxtFirstName.setText(pat);
            defaultListModelFirstName.removeAllElements();
            scrollPaneFirstName.setVisible(false);
        }
    }

    //search last name
//get all list of last name from SQL
    public ArrayList<String> getItemLastName() {
        ArrayList<String> listLastName = new ArrayList<>();
        Connection conn = ConnectionData.getConnectionData();
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM patients";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String surname = rs.getString("last_name");
                listLastName.add(surname);
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex.getStackTrace());
        }
        return listLastName;
    }

    private void bindDataLastName() {
        getItemLastName().stream().forEach((star) -> {
            defaultListModelLastName.addElement(star);
        });
        myJListLastName.setModel(defaultListModelLastName);
        myJListLastName.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }



    private void searchFilterLastName(String searchTerm) {
        DefaultListModel<String> filteredItems = new DefaultListModel<>();
        ArrayList<String> stars = getItemLastName();

        stars.stream().forEach((star) -> {
            String starName = star.toString().toLowerCase();
            if (starName.contains(searchTerm.toLowerCase())) {
                filteredItems.addElement(star);
            }
        });
        defaultListModelLastName = filteredItems;
        myJListLastName.setModel(defaultListModelLastName);

    }

    private void searchTxtLastNameKeyReleased(KeyEvent evt) {
        searchFilterLastName(searchTxtLastName.getText());
        scrollPaneLastName.setVisible(true);
    }

    private void myJListLastNameMouseClicked(MouseEvent evt) {
        if (!myJListLastName.isSelectionEmpty()) {
            String pat = defaultListModelLastName.getElementAt(myJListLastName.getSelectedIndex());
            searchTxtLastName.setText(pat);
            defaultListModelLastName.removeAllElements();
            scrollPaneLastName.setVisible(false);
        }
    }


    private void panelFLNameMouseClicked(MouseEvent evt) {
        scrollPaneLastName.setVisible(false);
        scrollPaneFirstName.setVisible(false);
    }


    public void insertAmountIntoSQL() {
        Connection conn = ConnectionData.getConnectionData();
        PreparedStatement ps;
        String query = "INSERT INTO orders(patientID, amount)"
                + "VALUES ((SELECT id FROM patients WHERE first_name = ? AND last_name = ?), ?)";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, searchTxtFirstName.getText());
            ps.setString(2, searchTxtLastName.getText());
            ps.setInt(3, Integer.parseInt(textFieldSubtotal.getText()));
            ps.execute();
            JOptionPane.showMessageDialog(null, "Data Saved");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Check correctness of entered first and last name");
            log.log(Level.SEVERE, null, ex.getStackTrace());
        }
    };

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Payment frame = new Payment();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

