package com.softserve.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class HomePage extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel imageIconFull;
    private JLabel labelIconPayment;
    private JLabel labelPaymentService;
    private JLabel labelIconPatients;
    private JLabel labelPatients;
    private JLabel labelWelcome;
    private JButton buttonLogout;
    private JPanel panelMain;
    private JPanel panelWelcome;
    private JPanel panelPatients;
    private JPanel panelPayment;
    private JPanel contentPane;

    private Image imgIcon = new ImageIcon(HomePage.class.getResource("/Images/cabinet.jpg")).getImage()
            .getScaledInstance(918, 510, Image.SCALE_SMOOTH);

    public HomePage() {
        guiHomePage();
    }

    public void guiHomePage() {
        setTitle("Home Page");
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 936, 557);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panelMain = new JPanel();
        panelMain.setBackground(new Color(255, 255, 255));
        panelMain.setBounds(0, 0, 918, 510);
        contentPane.add(panelMain);
        panelMain.setLayout(null);

        imageIconFull = new JLabel();
        imageIconFull.setBounds(0, 0, 918, 510);

        panelPayment = new JPanel();
        panelPayment.setBackground(new Color(32, 178, 170));
        panelPayment.setBounds(579, 224, 188, 166);
        panelMain.add(panelPayment);
        panelPayment.setLayout(null);

        labelIconPayment = new JLabel("");
        labelIconPayment.setIcon(new ImageIcon(HomePage.class.getResource("/Icon/cash-payment.png")));
        labelIconPayment.setBounds(0, 0, 188, 133);
        labelIconPayment.setHorizontalAlignment(SwingConstants.CENTER);
        panelPayment.add(labelIconPayment);

        labelIconPayment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                labelIconPaymentMouseClicked(ev);
            }
        });

        labelPaymentService = new JLabel("Payment service");
        labelPaymentService.setBounds(0, 137, 188, 29);
        labelPaymentService.setHorizontalAlignment(SwingConstants.CENTER);
        labelPaymentService.setFont(new Font("Tahoma", Font.BOLD, 20));
        panelPayment.add(labelPaymentService);

        panelPatients = new JPanel();
        panelPatients.setBackground(new Color(32, 178, 170));
        panelPatients.setBounds(148, 224, 188, 166);
        panelMain.add(panelPatients);
        panelPatients.setLayout(null);

        labelIconPatients = new JLabel("");
        labelIconPatients.setHorizontalAlignment(SwingConstants.CENTER);
        labelIconPatients.setIcon(new ImageIcon(HomePage.class.getResource("/Icon/add-user.png")));
        labelIconPatients.setBounds(0, 0, 188, 133);
        panelPatients.add(labelIconPatients);

        labelIconPatients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                labelIconUserMouseClicked(ev);
            }
        });

        labelPatients = new JLabel("Patients");
        labelPatients.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelPatients.setHorizontalAlignment(SwingConstants.CENTER);
        labelPatients.setBounds(0, 137, 188, 29);
        panelPatients.add(labelPatients);

        panelWelcome = new JPanel();
        panelWelcome.setBackground(new Color(32, 178, 170));
        panelWelcome.setBounds(0, 29, 918, 69);
        panelMain.add(panelWelcome);
        panelWelcome.setLayout(null);

        labelWelcome = new JLabel("WELCOME TO ADMIN PORTAL");
        labelWelcome.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        labelWelcome.setBounds(12, 13, 605, 43);
        panelWelcome.add(labelWelcome);

        buttonLogout = new JButton("Logout");
        buttonLogout.setBounds(799, 26, 107, 25);
        buttonLogout.setFont(new Font("Tahoma", Font.BOLD, 13));
        buttonLogout.setBackground(UIManager.getColor("Button.highlight"));
        panelWelcome.add(buttonLogout);

        imageIconFull.setIcon(new ImageIcon(imgIcon));
        panelMain.add(imageIconFull);

        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure to close this application?", "Confirmation",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    HomePage.this.dispose();
                }
            }

        });
    }

    public void labelIconPaymentMouseClicked(MouseEvent evt) {
        Payment payment = new Payment();
        payment.setVisible(true);
        dispose();
    }

    public void labelIconUserMouseClicked(MouseEvent evt) {
        PatientDisplay patient = new PatientDisplay();
        patient.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new HomePage().setVisible(true);
        });
    }

}

