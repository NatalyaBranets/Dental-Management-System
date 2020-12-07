package com.softserve.demo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class LoginSystem extends JFrame {

    private static final long serialVersionUID = 1L;

    private JButton buttonLogin;
    private JLabel labelUser;
    private JLabel labelPassword;
    private JLabel imageIcon;
    private JTextField textFieldUser;
    private JPasswordField paswwField;
    private JCheckBox showPassword;
    private JPanel contentPane;

    private Image imgLogin = new ImageIcon(LoginSystem.class.getResource("/Images/logo.png")).getImage()
            .getScaledInstance(355, 556, Image.SCALE_SMOOTH);

    private static Logger log = Logger.getLogger(LoginSystem.class.getName());

    public LoginSystem() {
        guiLoginSystem();
    }

    public void guiLoginSystem() {
        setTitle("Login System");
        setBounds(100, 100, 755, 603);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBackground(Color.white);
        contentPane.setLayout(null);

        buttonLogin = new JButton("LOGIN");
        buttonLogin.setFont(new Font("Dialog", Font.BOLD, 15));
        buttonLogin.setBounds(563, 390, 136, 43);
        buttonLogin.setForeground(Color.white);
        buttonLogin.setBackground(new Color(32, 178, 170));
        contentPane.add(buttonLogin);

        labelUser = new JLabel("USERNAME");
        labelUser.setFont(new Font("Tahoma", Font.BOLD, 13));
        labelUser.setBounds(402, 110, 123, 29);
        contentPane.add(labelUser);

        labelPassword = new JLabel("PASSWORD");
        labelPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
        labelPassword.setBounds(402, 215, 123, 29);
        contentPane.add(labelPassword);

        showPassword = new JCheckBox("Show password");
        showPassword.setBounds(402, 318, 150, 25);
        showPassword.setBackground(Color.white);
        contentPane.add(showPassword);

        textFieldUser = new JTextField(30);
        textFieldUser.setBounds(402, 152, 297, 29);
        contentPane.add(textFieldUser);

        paswwField = new JPasswordField();
        paswwField.setBounds(402, 257, 297, 29);
        contentPane.add(paswwField);

        imageIcon = new JLabel();
        imageIcon.setBounds(0, 0, 355, 556);
        imageIcon.setIcon(new ImageIcon(imgLogin));
        contentPane.add(imageIcon);

        Container container = this.getContentPane();
        container.add(contentPane);

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = textFieldUser.getText();
                char[] password = paswwField.getPassword();

                Connection connect;
                PreparedStatement ps;
                ResultSet rs;

                String query = "SELECT username, password FROM users WHERE username=? and password=?";

                try {
                    connect = ConnectionData.getConnectionData();
                    ps = connect.prepareStatement(query);
                    ps.setString(1, userName);
                    ps.setString(2, String.valueOf(password));
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        HomePage hp = new HomePage();
                        hp.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(buttonLogin, "You have entered an invalid username or password");
                    }
                } catch (SQLException ex) {
                    log.log(Level.SEVERE, null, ex.getStackTrace());

                }
            }
        });

        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    paswwField.setEchoChar((char) 0);
                } else {
                    paswwField.setEchoChar('*');
                }
            }
        });

    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new LoginSystem().setVisible(true);
        });
    }
}