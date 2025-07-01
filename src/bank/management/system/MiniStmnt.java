package bank.management.system;

import javax.swing.*;
import java.awt.Color;
import java.sql.*;
public class MiniStmnt extends JFrame{

    JLabel mstmnt, bnknm, cardn, blnc;
    String pinnumber;

    MiniStmnt(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("Mini Statement");
        int blnce = 0;

        mstmnt = new JLabel();
        mstmnt.setBounds(20, 140, 400, 200);
        add(mstmnt);

        bnknm = new JLabel("Indian Bank");
        bnknm.setBounds(150, 20, 100, 20);
        add(bnknm);

        cardn = new JLabel();
        cardn.setBounds(20, 80, 300, 20);
        add(cardn);

        blnc = new JLabel();
        blnc.setBounds(20, 400, 300, 20);
        add(blnc);

        try {
            Conn con = new Conn();
            ResultSet rs = con.statement.executeQuery("select * from atmlogin where pinnumber = '2003'");
            while (rs.next()) {
                cardn.setText("Card Nummber : " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        try {
            Conn con = new Conn();
            ResultSet rs = con.statement.executeQuery("select * from bank where pinnumber = '"+pinnumber+"'");
            String text = "<html>";
            while(rs.next()) {
                text +=  rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><br>"; 
                if (rs.getString("type").equals("Deposit")) {
                    blnce += Integer.parseInt(rs.getString("amount"));
                } else {
                    blnce -= Integer.parseInt(rs.getString("amount"));
                }
            }

            text += "</html>";
            mstmnt.setText(text);
            blnc.setText("Your current account balance is Rs : " + blnce);
            
        } catch (Exception E) {
            E.printStackTrace();
        }

        setLayout(null);
        setSize(400,600);
        setLocation(20, 40);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);

    }
 
    public static void main(String[] args) {
        new MiniStmnt("");
    } 
}
