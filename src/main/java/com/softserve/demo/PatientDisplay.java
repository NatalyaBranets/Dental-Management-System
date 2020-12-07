package com.softserve.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class PatientDisplay extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablePatient;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private JButton buttonInsert;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JButton buttonBack;
    private JButton buttonReset;
    private JLabel labelId;
    private JLabel labelFirstName;
    private JLabel labelLastName;
    private JLabel labelAge;
    private JLabel labelGender;
    private JTextField textFieldId;
    private JTextField textFieldFirstName;
    private JTextField textFieldLastName;
    private JTextField textFieldAge;
    private JTextField textFieldPhoneNumber;
    private JTextField textFieldEmailAddress;
    private JTextField textFieldAddress;
    private JTextField textFieldJoiningDay;
    private JLabel labelPhoneNumber;
    private JLabel labelEmailAddress;
    private JLabel labelAddress;
    private JLabel labelJoiningDate;
    private JComboBox<String> comboBoxGender;

    private static Logger log = Logger.getLogger(PatientDisplay.class.getName());

    public PatientDisplay() {

        guiPatient();
        displayPatientInTable();
    }

    public List<Patient> getPatientList() {
        List<Patient> patientList = new ArrayList<>();
        Connection conn = ConnectionData.getConnectionData();
        Statement st;
        ResultSet rs;
        Patient patient;
        String query = "SELECT id, first_name, last_name, age, gender, phone_number, email_address, address, joining_date FROM patients";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                patient = new Patient(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getInt("age"), rs.getString("gender"), rs.getInt("phone_number"),
                        rs.getString("email_address"), rs.getString("address"), rs.getString("joining_date"));
                patientList.add(patient);
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex.getStackTrace());
        }
        return patientList;
    }

    public void displayPatientInTable() {
        List<Patient> list = getPatientList();
        model = new DefaultTableModel();
        Object columns[] = { "Id", "First Name", "Last Name", "Age", "Gender", "Phone Number", "Email Address",
                "Address", "Joining Date" };
        model.setColumnIdentifiers(columns);
        Object[] rowData = new Object[9];

        for (int i = 0; i < list.size(); i++) {
            rowData[0] = list.get(i).getId();
            rowData[1] = list.get(i).getFirst_name();
            rowData[2] = list.get(i).getLast_name();
            rowData[3] = list.get(i).getAge();
            rowData[4] = list.get(i).getGender();
            rowData[5] = list.get(i).getPhone_number();
            rowData[6] = list.get(i).getEmail_address();
            rowData[7] = list.get(i).getAddress();
            rowData[8] = list.get(i).getJoining_date();
            model.addRow(rowData);
        }
        tablePatient.setModel(model);
    }

    public void guiPatient() {

        setTitle("Patients");
        setBounds(100, 100, 936, 557);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground((new Color(32, 178, 170)));
        contentPane.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 53, 886, 216);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tablePatient = new JTable();
        scrollPane.setViewportView(tablePatient);
        tablePatient.setBorder(new LineBorder(new Color(0, 0, 0)));

        tablePatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = tablePatient.getSelectedRow();
                textFieldId.setText(tablePatient.getValueAt(i, 0).toString());
                textFieldFirstName.setText(tablePatient.getValueAt(i, 1).toString());
                textFieldLastName.setText(tablePatient.getValueAt(i, 2).toString());
                textFieldAge.setText(tablePatient.getValueAt(i, 3).toString());
                comboBoxGender.setSelectedItem(tablePatient.getValueAt(i, 4).toString());
                textFieldPhoneNumber.setText(tablePatient.getValueAt(i, 5).toString());
                textFieldEmailAddress.setText(tablePatient.getValueAt(i, 6).toString());
                textFieldAddress.setText(tablePatient.getValueAt(i, 7).toString());
                textFieldJoiningDay.setText(tablePatient.getValueAt(i, 8).toString());

            }
        });

        buttonInsert = new JButton("Insert");
        buttonInsert.setBounds(109, 431, 135, 31);
        contentPane.add(buttonInsert);

        buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(295, 431, 128, 31);
        contentPane.add(buttonUpdate);

        buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(476, 431, 135, 31);
        contentPane.add(buttonDelete);

        buttonBack = new JButton("Back");
        buttonBack.setBounds(20, 16, 121, 24);
        contentPane.add(buttonBack);

        buttonReset = new JButton("Reset");
        buttonReset.setBounds(672, 431, 135, 31);
        contentPane.add(buttonReset);

        labelId = new JLabel("Id:");
        labelId.setBounds(20, 299, 56, 16);
        contentPane.add(labelId);

        labelFirstName = new JLabel("First Name:");
        labelFirstName.setBounds(20, 328, 72, 16);
        contentPane.add(labelFirstName);

        labelLastName = new JLabel("Last Name:");
        labelLastName.setBounds(20, 357, 72, 16);
        contentPane.add(labelLastName);

        labelAge = new JLabel("Age:");
        labelAge.setBounds(327, 299, 72, 16);
        contentPane.add(labelAge);

        labelGender = new JLabel("Gender:");
        labelGender.setBounds(327, 328, 72, 16);
        contentPane.add(labelGender);

        textFieldId = new JTextField();
        textFieldId.setBounds(109, 296, 175, 22);
        contentPane.add(textFieldId);
        textFieldId.setColumns(10);

        textFieldFirstName = new JTextField();
        textFieldFirstName.setColumns(10);
        textFieldFirstName.setBounds(110, 326, 175, 22);
        contentPane.add(textFieldFirstName);

        textFieldLastName = new JTextField();
        textFieldLastName.setColumns(10);
        textFieldLastName.setBounds(109, 358, 175, 22);
        contentPane.add(textFieldLastName);

        textFieldAge = new JTextField();
        textFieldAge.setColumns(10);
        textFieldAge.setBounds(428, 296, 175, 22);
        contentPane.add(textFieldAge);

        textFieldPhoneNumber = new JTextField();
        textFieldPhoneNumber.setColumns(10);
        textFieldPhoneNumber.setBounds(428, 354, 175, 22);
        contentPane.add(textFieldPhoneNumber);

        textFieldEmailAddress = new JTextField();
        textFieldEmailAddress.setColumns(10);
        textFieldEmailAddress.setBounds(731, 296, 175, 22);
        contentPane.add(textFieldEmailAddress);

        textFieldAddress = new JTextField();
        textFieldAddress.setColumns(10);
        textFieldAddress.setBounds(731, 325, 175, 22);
        contentPane.add(textFieldAddress);

        textFieldJoiningDay = new JTextField();
        textFieldJoiningDay.setColumns(10);
        textFieldJoiningDay.setBounds(731, 354, 175, 22);
        contentPane.add(textFieldJoiningDay);

        labelPhoneNumber = new JLabel("Phone Number:");
        labelPhoneNumber.setBounds(327, 357, 99, 16);
        contentPane.add(labelPhoneNumber);

        labelEmailAddress = new JLabel("Email Address:");
        labelEmailAddress.setBounds(632, 299, 93, 16);
        contentPane.add(labelEmailAddress);

        labelAddress = new JLabel("Address:");
        labelAddress.setBounds(632, 328, 72, 16);
        contentPane.add(labelAddress);

        labelJoiningDate = new JLabel("Joining Date:");
        labelJoiningDate.setBounds(632, 357, 84, 16);
        contentPane.add(labelJoiningDate);

        comboBoxGender = new JComboBox<>();
        comboBoxGender.setModel(new DefaultComboBoxModel<>(new String[] { "make a selection", "male", "female" }));
        comboBoxGender.setBounds(428, 325, 175, 22);
        contentPane.add(comboBoxGender);

        Container container = this.getContentPane();
        container.add(contentPane);

        buttonInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textFieldId.getText().equals("")) {
                    Connection conn = ConnectionData.getConnectionData();
                    PreparedStatement ps;
                    String query = "INSERT INTO patients(id, first_name, last_name, age, gender, phone_number, email_address, address, joining_date)"
                            + "VALUES(?,?,?,?,?,?,?,?,?)";
                    try {
                        ps = conn.prepareStatement(query);
                        ps.setInt(1, Integer.parseInt(textFieldId.getText()));
                        ps.setString(2, textFieldFirstName.getText());
                        ps.setString(3, textFieldLastName.getText());
                        ps.setInt(4, Integer.parseInt(textFieldAge.getText()));
                        ps.setString(5, (String) comboBoxGender.getSelectedItem());
                        ps.setInt(6, Integer.parseInt(textFieldPhoneNumber.getText()));
                        ps.setString(7, textFieldEmailAddress.getText());
                        ps.setString(8, textFieldAddress.getText());
                        ps.setString(9, textFieldJoiningDay.getText());
                        ps.execute();
                        displayPatientInTable();
                        JOptionPane.showMessageDialog(null, "Data Saved");

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,
                                "The system is currently unavailable. Please try again or contact your administrator");
                        log.log(Level.SEVERE, null, ex.getStackTrace());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No data to insert.");
                }
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tablePatient.getSelectedRow();
                if (row > 1) {
                    String cell = tablePatient.getModel().getValueAt(row, 0).toString();
                    Connection conn = ConnectionData.getConnectionData();
                    PreparedStatement ps;
                    String query = "DELETE FROM patients WHERE id = ?";
                    try {
                        ps = conn.prepareStatement(query);
                        ps.setInt(1, Integer.parseInt(cell));
                        ps.execute();
                        displayPatientInTable();

                        textFieldId.setText("");
                        textFieldFirstName.setText("");
                        textFieldLastName.setText("");
                        textFieldAge.setText("");
                        comboBoxGender.setSelectedIndex(0);
                        textFieldPhoneNumber.setText("");
                        textFieldEmailAddress.setText("");
                        textFieldAddress.setText("");
                        textFieldJoiningDay.setText("");

                        JOptionPane.showMessageDialog(null, "Deleted");

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,
                                "The system is currently unavailable. Please try again or contact your administrator");
                        log.log(Level.SEVERE, null, ex.getStackTrace());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No data to delete.");
                }
            }
        });

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldId.setText("");
                textFieldFirstName.setText("");
                textFieldLastName.setText("");
                textFieldAge.setText("");
                comboBoxGender.setSelectedIndex(0);
                textFieldPhoneNumber.setText("");
                textFieldEmailAddress.setText("");
                textFieldAddress.setText("");
                textFieldJoiningDay.setText("");
            }

        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textFieldId.getText().equals("")) {
                    Connection conn = ConnectionData.getConnectionData();
                    PreparedStatement ps;

                    try {
                        String query = "UPDATE patients SET id= ?, first_name=?, last_name=?, age= ?, gender= ?, phone_number=?, email_address=?, address=?, joining_date=? WHERE id= ?";
                        ps = conn.prepareStatement(query);

                        String fieldText = textFieldId.getText();
                        int value = Integer.parseInt(fieldText);
                        ps.setInt(1, value);

                        ps.setString(2, textFieldFirstName.getText());
                        ps.setString(3, textFieldLastName.getText());
                        ps.setInt(4, Integer.parseInt(textFieldAge.getText()));
                        ps.setString(5, (String) comboBoxGender.getSelectedItem());
                        ps.setInt(6, Integer.parseInt(textFieldPhoneNumber.getText()));
                        ps.setString(7, textFieldEmailAddress.getText());
                        ps.setString(8, textFieldAddress.getText());
                        ps.setString(9, textFieldJoiningDay.getText());
                        ps.setInt(10, Integer.parseInt(textFieldId.getText()));
                        ps.execute();
                        displayPatientInTable();
                        JOptionPane.showMessageDialog(null, "Data updated");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,
                                "The system is currently unavailable. Please try again or contact your administrator");
                        log.log(Level.SEVERE, null, ex.getStackTrace());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No data to update.");
                }
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage home = new HomePage();
                home.setVisible(true);
                PatientDisplay.this.dispose();
            }

        });

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PatientDisplay frame = new PatientDisplay();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

