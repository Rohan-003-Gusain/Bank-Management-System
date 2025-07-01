package bank.management.system;
import java.awt.Image;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deposit extends JFrame implements ActionListener{

    JLabel hdln;
    JTextField amount;
    JButton dpost, back;
    String pinnumber;

    Deposit(String pinnumber) {
        this.pinnumber = pinnumber;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, +900, 900);
        add(img);

        hdln = new JLabel("Enter the amount you want to deposit ");
        hdln.setFont(new Font("System", Font.BOLD, 16));
        hdln.setBounds(190, 300, 400, 30);
        hdln.setForeground(Color.WHITE);
        img.add(hdln);

        amount = new JTextField();
        amount.setBounds(190, 350, 300, 30);
        img.add(amount);

        dpost = new JButton("Deposit");
        dpost.setBounds(355, 485, 150, 30);
        dpost.addActionListener(this);
        img.add(dpost);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        img.add(back);

        setLayout(null);
        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == dpost) {
            String peshe = amount.getText();
            Date date = new Date();
            if (peshe.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
            } else {
                try {
                    Conn con = new Conn();
                    String query = "insert into bank values('"+pinnumber+"','"+date+"','Deposit','"+peshe+"')";
                    con.statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs " +peshe+ " Deposited Successfully");

                    setVisible(false);
                    new Transaction(pinnumber).setVisible(true);
                    
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }

        } else if (e.getSource() == back) {
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);

        }
    }

    public static void main(String [] args) {
        new Deposit("");
    }

}
