package bank.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

import javax.swing.*;

public class FastCash extends JFrame implements ActionListener{

    JLabel hdln;
    JButton  r1, r2, r3, r4, r5, r6, back;
    String pinnumber;

    FastCash(String pinnumber) {

        this.pinnumber = pinnumber;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 900, 900);
        add(img);

        hdln = new JLabel("SELECT WIDTDRAWL AMOUNT");
        hdln.setFont(new Font("System", Font.BOLD, 16));
        hdln.setBounds(220, 300, 400, 30);
        hdln.setForeground(Color.WHITE);
        img.add(hdln);

        r1 = new JButton("Rs 100");
        r1.setBounds(170, 417, 150, 30);
        r1.addActionListener(this);
        img.add(r1);

        r2 = new JButton("Rs 500");
        r2.setBounds(355, 417, 150, 30);
        r2.addActionListener(this);
        img.add(r2);

        r3 = new JButton("Rs 1000");
        r3.setBounds(170, 451, 150, 30);
        r3.addActionListener(this);
        img.add(r3);
        
        r4 = new JButton("Rs 2000");
        r4.setBounds(355, 451, 150, 30);
        r4.addActionListener(this);
        img.add(r4);
        
        r5 = new JButton("Rs 5000");
        r5.setBounds(170, 485, 150, 30);
        r5.addActionListener(this);
        img.add(r5);

        r6 = new JButton("Rs 10000");
        r6.setBounds(355, 485, 150, 30);
        r6.addActionListener(this);
        img.add(r6);

        back = new JButton("Back");
        back.setBounds(355, 519, 150, 30);
        back.addActionListener(this);
        img.add(back);

        getContentPane().setBackground(Color.WHITE);

        setLayout(null);
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
        } else {
            String amount = ((JButton)e.getSource()).getText().substring(3);
            Conn con  = new Conn();
            try {
                ResultSet rs = con.statement.executeQuery("select * from bank where pinnumber = '"+pinnumber+"'");
                int blnce = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        blnce += Integer.parseInt(rs.getString("amount"));
                    } else {
                        blnce -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                if (e.getSource() != back && blnce < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insuficient Balance");
                    return;

                }

                Date date = new Date();
                String query = "insert into bank values('"+pinnumber+"','"+date+"','Withdrawl','"+amount+"')";
                con.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Succsessfully");
                
            } catch (Exception E) {
                E.printStackTrace();
               
            }
        } 
    }

    public static void main(String [] args) {
        new FastCash("");
    }

}
