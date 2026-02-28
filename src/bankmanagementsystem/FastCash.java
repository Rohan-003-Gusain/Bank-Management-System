package bankmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import bankmanagementsystem.base.ATMBaseFrame;
import bankmanagementsystem.db.Conn;

public class FastCash extends ATMBaseFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
    private JLabel headline;
    private JButton back;
    
    private String accountNumber;

    FastCash(String accountNumber) {
    		super();
        this.accountNumber = accountNumber;

        headline = new JLabel("SELECT WIDTDRAWL AMOUNT");
        headline.setFont(new Font("System", Font.BOLD, 16));
        headline.setBounds(220, 300, 400, 30);
        headline.setForeground(Color.WHITE);
        bgLabel.add(headline);

        createButton("Rs 100", 170, 418);
        createButton("Rs 500", 355, 418);
        createButton("Rs 1000", 170, 452);
        createButton("Rs 2000", 355, 452);
        createButton("Rs 5000", 170, 486);
        createButton("Rs 10000", 355, 486);

        back = new JButton("Back");
        back.setBounds(355, 519, 150, 30);
        back.addActionListener(this);
        bgLabel.add(back);
        
        animation.fadeIn(this);

    }
    
    private JButton createButton(String text, int x, int y) {
        JButton b = new JButton(text);
        b.setBounds(x, y, 150, 30);
        
        b.setFocusPainted(false);
        b.setDefaultCapable(false);
        b.setRolloverEnabled(false);
        
        b.addActionListener(this);
        bgLabel.add(b);
        return b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
        if (e.getSource() == back) {
        		animation.fadeOutAndOpen(this, () -> new Transaction(accountNumber));
            return;
        } 
        
        String text = ((JButton)e.getSource()).getText();
        int amount = Integer.parseInt(text.replace("Rs", "").trim());
            	
        new Thread(() -> {
	      	try {
	      		Connection conn = new Conn().getConnection();
	      		int balance = 0;
	      		
	      		String query1 =
	      				"SELECT balance FROM transactions " +
	      				"WHERE accountnumber=? " +
	      				"ORDER BY transaction_id DESC LIMIT 1";
	           
	      		PreparedStatement ps1 = conn.prepareStatement(query1);
	      		ps1.setString(1, accountNumber);
	
	      		ResultSet rs = ps1.executeQuery();

	      		if (rs.next()) {
	      			balance = rs.getInt("balance");
	      		}
	      		
	      		rs.close();
	      		ps1.close();
	                
	      		if (balance < amount) {
	      			
	      			SwingUtilities.invokeLater(() -> {
	      				JOptionPane.showMessageDialog(
	      					FastCash.this,
		      				"Insufficient Balance"
		      			);
	      			});
	      			return;
	      		}
	      		
	      		balance -= amount;
	                
	      		String query2 =
	      				"INSERT INTO transactions " +
	      				"(accountnumber,date,type,amount,balance) " +
	      				"VALUES (?,?,?,?,?)";

	      		
	      		PreparedStatement ps2 = conn.prepareStatement(query2);
	                
	      		ps2.setString(1, accountNumber);
	      		ps2.setString(2, new Date().toString());
	      		ps2.setString(3, "Withdrawl");
	      		ps2.setInt(4, amount);
	      		ps2.setInt(5, balance);
	                
	      		ps2.executeUpdate();
	      		ps2.close();
	                
	      		SwingUtilities.invokeLater(() -> {
	      			JOptionPane.showMessageDialog(
	      				FastCash.this,
	                    "Rs " + amount + " Debited Successfully"
	      			);
	      			animation.fadeOutAndOpen(this, () -> new Transaction(accountNumber));
	      		});
	                
	      	} catch (Exception ex) {
	      		ex.printStackTrace();
	      		SwingUtilities.invokeLater(() ->
	      			JOptionPane.showMessageDialog(
	      				FastCash.this,
		                "Fast Cash failed"
	      			)
	      		);    
	      	}
        }).start();
    }
}
