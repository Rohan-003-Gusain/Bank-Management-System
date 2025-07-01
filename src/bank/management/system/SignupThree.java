package bank.management.system;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SignupThree extends JFrame implements ActionListener {

    JLabel acctype, cardno, cnumber, cardinfo, pinno, pininfo, cpin, serreq;
    JRadioButton r1, r2, r3, r4; 
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    ButtonGroup groupaccount;
    JButton submit, cancel;
    String formno;

    SignupThree(String formno) {
        this.formno = formno;

        JLabel l1 = new JLabel("Page 3: ACCOUNT DETAILS");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

        acctype = new JLabel("Account Type:");
        acctype.setFont(new Font("Raleway", Font.BOLD, 22));
        acctype.setBounds(100, 140, 200, 30);
        add(acctype);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        r1.setBackground(Color.WHITE);
        r1.setBounds(100, 180, 200, 30);
        add(r1);

        r2 = new JRadioButton("Fixed Deposite Account");
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        r2.setBackground(Color.WHITE);
        r2.setBounds(350, 180, 250, 30);
        add(r2);

        r3 = new JRadioButton("Current Account");
        r3.setFont(new Font("Raleway", Font.BOLD, 16));
        r3.setBackground(Color.WHITE);
        r3.setBounds(100, 220, 200, 30);
        add(r3);

        r4 = new JRadioButton("Reccurring Account");
        r4.setFont(new Font("Raleway", Font.BOLD, 16));
        r4.setBackground(Color.WHITE);
        r4.setBounds(350, 220, 200, 30);
        add(r4);

        groupaccount = new ButtonGroup();
        groupaccount.add(r1);
        groupaccount.add(r2);
        groupaccount.add(r3);
        groupaccount.add(r4);

        cardno = new JLabel("Card Number:");
        cardno.setFont(new Font("Raleway", Font.BOLD, 22));
        cardno.setBounds(100, 300, 200, 30);
        add(cardno);

        cnumber = new JLabel("XXXX-XXXX-XXXX-4175");
        cnumber.setFont(new Font("Raleway", Font.BOLD, 22));
        cnumber.setBounds(330, 300, 300,30);
        add(cnumber);

        cardinfo = new JLabel("Your 16 Digit Card Number");
        cardinfo.setFont(new Font("Raleway", Font.BOLD, 12));
        cardinfo.setBounds(100, 330, 300,20);
        add(cardinfo);

        pinno = new JLabel("PIN Number:");
        pinno.setFont(new Font("Raleway", Font.BOLD, 22));
        pinno.setBounds(100, 370, 200, 30);
        add(pinno);

        cpin = new JLabel("XXXX");
        cpin.setFont(new Font("Raleway", Font.BOLD, 22));
        cpin.setBounds(330, 370, 300,30);
        add(cpin);

        pininfo = new JLabel("Your 4 Digit PIN Number");
        pininfo.setFont(new Font("Raleway", Font.BOLD, 12));
        pininfo.setBounds(100, 400, 300,20);
        add(pininfo);

        serreq = new JLabel("Services Requried:");
        serreq.setFont(new Font("Raleway", Font.BOLD, 22));
        serreq.setBounds(100, 450, 300, 30);
        add(serreq);

        c1 = new JCheckBox("ATM CARD");
        c1.setBackground(Color.white);
        c1.setFont(new Font("Raleway", Font.BOLD, 16));
        c1.setBounds(100, 500, 200, 30);
        add(c1);
        
        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(Color.white);
        c2.setFont(new Font("Raleway", Font.BOLD, 16));
        c2.setBounds(350, 500, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(Color.white);
        c3.setFont(new Font("Raleway", Font.BOLD, 16));
        c3.setBounds(100, 550, 200, 30);
        add(c3);

        c4 = new JCheckBox("Email & SMS  Alerts");
        c4.setBackground(Color.white);
        c4.setFont(new Font("Raleway", Font.BOLD, 16));
        c4.setBounds(350, 550, 200, 30);
        add(c4);

        c5 = new JCheckBox("Check Book");
        c5.setBackground(Color.white);
        c5.setFont(new Font("Raleway", Font.BOLD, 16));
        c5.setBounds(100, 600, 200, 30);
        add(c5);

        c6 = new JCheckBox("E Statement");
        c6.setBackground(Color.white);
        c6.setFont(new Font("Raleway", Font.BOLD, 16));
        c6.setBounds(350, 600, 200, 30);
        add(c6);

        c7 = new JCheckBox("I hereby declares that the above entered details are corect to the best of my knowledge");
        c7.setBackground(Color.white);
        c7.setFont(new Font("Raleway", Font.BOLD, 12));
        c7.setBounds(100, 680, 600, 30);
        add(c7);

        submit = new JButton("Submit");
        submit.setBackground(Color.black);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBounds(250, 720 ,100 ,30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("CanceL");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBounds(450, 720 ,100 ,30);
        cancel.addActionListener(this);
        add(cancel);        

        getContentPane().setBackground(Color.WHITE);

        setLayout(null);
        setSize(850, 820);
        setLocation(350, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String acctype = null;
            if (r1.isSelected()) {
                acctype = "Saving Account";
            } else if (r2.isSelected()) {
                acctype = "Fixed Deposit Account";
            } else if (r3.isSelected()) {
                acctype = "Current Account";
            } else if (r4.isSelected()) {
                acctype = "Reccuring Deposit Acoount";
            }

            Random rn = new Random();
            String cardnumber = "" +  String.format("%016d", Math.abs(rn.nextLong() % 10000000000000000L));
            String pinnumber = "" + String.format("%04d", rn.nextInt(10000));

            String facility = "";
            if (c1.isSelected()) {
                facility = facility + " ATM Card";
            } if (c2.isSelected()) {
                facility = facility + " Internet Banking";
            } if (c3.isSelected()) {
                facility = facility + " Mobile Banking";
            } if (c4.isSelected()) {
                facility = facility + " Email & SMS Alerts";
            } if (c5.isSelected()) {
                facility = facility + " Check Book";
            } if (c6.isSelected()) {
                facility = facility + " E Statement";
            }
            
            try { 
                if (acctype == null) {
                    JOptionPane.showMessageDialog(null, "Account Type is Requried");
                    return;
                } else {
                    Conn con = new Conn();
                    String query1 = "insert into signupthree values('"+formno+"','"+acctype+"','"+cardnumber+"','"+pinnumber+"','"+facility+"')";
                    String query2 = "insert into atmlogin values('"+formno+"','"+cardnumber+"','"+pinnumber+"')";

                    con.statement.executeUpdate(query1);
                    con.statement.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null,"Card Number: " + cardnumber + "\n Pin: " + pinnumber );
                }                
                setVisible(false);
                new Deposit(pinnumber).setVisible(true);

            } catch (Exception E) {
                E.printStackTrace();
            }

        } else if (e.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);

        }
    }

    public static void main(String[] args) {
        new SignupThree("");
    }
}
