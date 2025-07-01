package bank.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Transaction extends JFrame implements ActionListener{

    JLabel hdln;
    JButton dpost, cshwdrl, fscsh, ministmnt, pinchng, blncenqry, exit;
    String pinnumber;

    Transaction(String pinnumber) {

        this.pinnumber = pinnumber;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 900, 900);
        add(img);

        hdln = new JLabel("Please select your Transaction");
        hdln.setFont(new Font("System", Font.BOLD, 16));
        hdln.setBounds(220, 300, 400, 30);
        hdln.setForeground(Color.WHITE);
        img.add(hdln);

        dpost = new JButton("Deposit");
        dpost.setBounds(170, 417, 150, 30);
        dpost.addActionListener(this);
        img.add(dpost);

        cshwdrl = new JButton("Cash Withdrwal");
        cshwdrl.setBounds(355, 417, 150, 30);
        cshwdrl.addActionListener(this);
        img.add(cshwdrl);

        fscsh = new JButton("Fast Cash");
        fscsh.setBounds(170, 451, 150, 30);
        fscsh.addActionListener(this);
        img.add(fscsh);
        
        ministmnt = new JButton("Mini Statement");
        ministmnt.setBounds(355, 451, 150, 30);
        ministmnt.addActionListener(this);
        img.add(ministmnt);
        
        pinchng = new JButton("PIN Change");
        pinchng.setBounds(170, 485, 150, 30);
        pinchng.addActionListener(this);
        img.add(pinchng);

        blncenqry = new JButton("Balance Enquiry");
        blncenqry.setBounds(355, 485, 150, 30);
        blncenqry.addActionListener(this);
        img.add(blncenqry);

        exit = new JButton("EXIT");
        exit.setBounds(355, 519, 150, 30);
        exit.addActionListener(this);
        img.add(exit);

        getContentPane().setBackground(Color.WHITE);

        setLayout(null);
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == dpost) {
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);

        } else if (e.getSource() == cshwdrl) {
            setVisible(false);
            new Withdrawl(pinnumber).setVisible(true);
            
        } else if (e.getSource() == fscsh) {
            setVisible(false);
            new FastCash(pinnumber).setVisible(true);

        } else if (e.getSource() == pinchng) {
            setVisible(false);
            new PinChnage(pinnumber).setVisible(true);

        } else if (e.getSource() == blncenqry) {
            setVisible(false);
            new BalanceEnquiry(pinnumber).setVisible(true);

        } else if (e.getSource() == ministmnt) {
            new MiniStmnt(pinnumber).setVisible(true);
        }
    }

    public static void main(String [] args) {
        new Transaction("");
    }

}
