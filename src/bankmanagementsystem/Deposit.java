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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bankmanagementsystem.base.ATMBaseFrame;
import bankmanagementsystem.db.Conn;

public class Deposit extends ATMBaseFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
    private JLabel heading;
    private JTextField amountField;
    private JButton depositButton, backButton;
    
    private String accountNumber;

    Deposit(String accountNumber) {
    		super();
        this.accountNumber = accountNumber;

        heading = new JLabel("Enter the amount you want to deposit ");
        heading.setFont(new Font("System", Font.BOLD, 16));
        heading.setBounds(190, 300, 400, 30);
        heading.setForeground(Color.WHITE);
        bgLabel.add(heading);

        amountField = new JTextField();
        amountField.setBounds(190, 350, 300, 30);
        bgLabel.add(amountField);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(355, 485, 150, 30);
        depositButton.addActionListener(this);
        bgLabel.add(depositButton);

        backButton = new JButton("Back");
        backButton.setBounds(355, 520, 150, 30);
        backButton.addActionListener(this);
        bgLabel.add(backButton);
        
        animation.fadeIn(this);
        
    }

    public void actionPerformed (ActionEvent e) {
    	
        if (e.getSource() == depositButton) {
        	
            String amountText = amountField.getText().trim();
            
            if (amountText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter amount");
                return;
            } 
            
            int amount;
            try {
				amount = Integer.parseInt(amountText);
				if (amount <= 0) {
					JOptionPane.showMessageDialog(this, "Enter a valid number");
					return;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Enter a valid number");
				return;
			}
            
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
	    				
	    				rs.close();
	    				ps1.close();
	    				
	    				balance += amount;
	    				
	    				String query2 =
	    					"INSERT INTO transactions " +
	    					"(accountnumber,date,type,amount,balance) " +
	    					"VALUES (?,?,?,?,?)";

	    				PreparedStatement ps2 =
	    					conn.prepareStatement(query2);
	    				
	    				ps2.setString(1, accountNumber);
	    		        ps2.setString(2, new Date().toString());
	    		        ps2.setString(3, "Deposit");
	    		        ps2.setInt(4, amount);
	    		        ps2.setInt(5, balance);

	    		        ps2.executeUpdate();
	    		        ps2.close();
	    				
	    				SwingUtilities.invokeLater(() -> {
	    					JOptionPane.showMessageDialog(
	    						Deposit.this,
	                        "Rs " + amount + " deposited successfully"
	                     );
	    					animation.fadeOutAndOpen(
	    							this, 
	    							() -> new Transaction(accountNumber)
	    					);
	                });
	    				
	            	} catch (Exception ex) {
	            		ex.printStackTrace();
	            		
	            		SwingUtilities.invokeLater(() -> {
	            			JOptionPane.showMessageDialog(
	            				Deposit.this,
	            				"Database error occurred"
	            			);
	            		});
				}
            }).start(); 

        } else if (e.getSource() == backButton) {
        		animation.fadeOutAndOpen(this, () -> new Transaction(accountNumber));
        }
    }
}
