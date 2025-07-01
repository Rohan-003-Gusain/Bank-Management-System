package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener{
        JButton back;
        String pinnumber;

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 900, 900);
        add(img);

        back = new JButton("Back");
        back.setBounds(355, 519, 150, 30);
        back.addActionListener(this);
        img.add(back);

        Conn con  = new Conn();
        int blnce = 0;
        try {
            ResultSet rs = con.statement.executeQuery("select * from bank where pinnumber = '"+pinnumber+"'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    blnce += Integer.parseInt(rs.getString("amount"));
                } else {
                    blnce -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel text = new JLabel("Your Current Account balance is Rs : " + blnce);
        text.setForeground(Color.WHITE);
        text.setBounds(170, 300, 400, 30);
        img.add(text);

        setLayout(null);
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Transaction(pinnumber).setVisible(true);
    }
    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
    
}
