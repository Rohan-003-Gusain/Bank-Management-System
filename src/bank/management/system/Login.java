package bank.management.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.SQLException;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{

    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;
    
    Login() {
        setTitle("AUTOMATED TELLER MACHINE");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100,100);
        add(label);
        
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward",Font.BOLD,38));
        text.setBounds(300, 40, 400, 40);
        add(text);

        JLabel cardno = new JLabel("Card No:");
        cardno.setFont(new Font("Raleway",Font.BOLD,28));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway",Font.BOLD,28));
        pin.setBounds(120, 220, 400, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener((ActionListener) this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener((ActionListener)this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener((ActionListener)this);
        add(signup);

        getContentPane().setBackground(Color.WHITE);
        
        setLayout(null);
        setSize(800, 500);
        setVisible(true);
        setLocation(350, 200);
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            Conn con = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = new String (pinTextField.getPassword());
            String query = "select * from atmlogin where cardnumber = '"+cardnumber+"' and pinnumber = '"+pinnumber+"'";
            try {
                ResultSet rs = con.statement.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Transaction(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Inncorrect Card Number or Pin");
                }
            } catch (SQLException e1) {
        
                e1.printStackTrace();
            }

        } else if (e.getSource() == clear ) {
            cardTextField.setText("");
            pinTextField.setText("");

        } else if (e.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        } 
    }

    public static void main(String[] args) {
        new Login();
    }
}
