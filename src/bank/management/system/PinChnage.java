package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinChnage extends JFrame implements ActionListener{

    JLabel text, npin, rnpin;
    JPasswordField enpin, renpin;
    JButton change, back;
    String pinnumber;

    PinChnage(String pinnumber) {
        this.pinnumber = pinnumber;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, +900, 900);
        add(img);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color. WHITE);
        text.setFont(new Font ("Syetem", Font.BOLD, 16));
        text.setBounds(265, 300, 500, 30 );
        img.add(text);

        npin = new JLabel("New PIN:");
        npin.setForeground(Color. WHITE);
        npin.setFont(new Font ("Syetem", Font.BOLD, 16));
        npin.setBounds(180, 350, 150, 30 );
        img.add(npin);

        enpin = new JPasswordField();
        enpin.setFont(new Font ("Raleway", Font.BOLD, 16));
        enpin.setBounds(350, 350, 150, 25 );
        img.add(enpin);

        rnpin = new JLabel("Re-Enter New PIN:");
        rnpin.setForeground(Color. WHITE);
        rnpin.setFont(new Font ("Syetem", Font.BOLD, 16));
        rnpin.setBounds(180, 400, 300, 30 );
        img.add(rnpin);

        renpin = new JPasswordField();
        renpin.setFont(new Font ("Raleway", Font.BOLD, 16));
        renpin.setBounds(350, 400, 150, 25 );
        img.add(renpin);

        change = new JButton("Change");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        img.add(change);

        back = new JButton("Back");
        back.setBounds(355, 519, 150, 30);
        back.addActionListener(this);
        img.add(back);

        setLayout(null);
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == change) {
            try {
                String npin = new String (enpin.getPassword());
                String rnpin = new String (renpin.getPassword());
    
                if (!npin.equals(rnpin)) {
                    JOptionPane.showMessageDialog(null, "Enterd PIN does not matched");
                    return;
                }
    
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }
    
                if (rnpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
                    return;
                }
    
                Conn con = new Conn();
                String query1 = "update bank set pinnumber = '"+rnpin+"' where pinnumber = '"+pinnumber+"'";
                String query2 = "update atmlogin set pinnumber = '"+rnpin+"' where pinnumber = '"+pinnumber+"'";
                String query3 = "update signupthree set pinnumber = '"+rnpin+"' where pinnumber = '"+pinnumber+"'";
    
                con.statement.executeUpdate(query1);
                con.statement.executeUpdate(query2);
                con.statement.executeUpdate(query3);
    
                JOptionPane.showMessageDialog(null, "PIN changed successfully");
    
                setVisible(false);
                new Transaction(rnpin).setVisible(true);

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
    }

    public static void main(String[] args) {
        new PinChnage("");
    }
}
