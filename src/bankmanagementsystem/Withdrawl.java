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

public class Withdrawl extends ATMBaseFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
    private JLabel heading;
    private JTextField amountField;
    private JButton withdrawButton, backButoon;
    
    private String accountNumber;

    Withdrawl(String accountNumber) {
    		super();
        this.accountNumber = accountNumber;

        heading = new JLabel("Enter the amount you want to Withdrawl ");
        heading.setFont(new Font("System", Font.BOLD, 16));
        heading.setBounds(190, 300, 400, 30);
        heading.setForeground(Color.WHITE);
        bgLabel.add(heading);

        amountField = new JTextField();
        amountField.setBounds(190, 350, 300, 30);
        bgLabel.add(amountField);

        withdrawButton = new JButton("Withdrawl");
        withdrawButton.setBounds(355, 485, 150, 30);
        withdrawButton.addActionListener(this);
        bgLabel.add(withdrawButton);

        backButoon = new JButton("Back");
        backButoon.setBounds(355, 520, 150, 30);
        backButoon.addActionListener(this);
        bgLabel.add(backButoon);

        animation.fadeIn(this);
    }

    public void actionPerformed (ActionEvent e) {
    	
        if (e.getSource() == backButoon) {
        	animation.fadeOutAndOpen(this, () -> new Transaction(accountNumber));
        		return;
        } else if (e.getSource() == withdrawButton) {
        	
        		String amountText = amountField.getText().trim();
        		
        		if (amountText.isEmpty()) {
        			JOptionPane.showMessageDialog(null, "Please enter amount");
        			return;
        		}
        		
        		if (!amountText.matches("\\d+")) {
        			JOptionPane.showMessageDialog(null, "Enter valid numeric amount");
        			return;
        		}
        		
        		int withdrawAmount = Integer.parseInt(amountText);
        		if (withdrawAmount <= 0) {
        			JOptionPane.showMessageDialog(null, "Amount must be greater than zero");
        			return;
        		}	
 
            new Thread(() ->{
            		try {
                    Connection conn = new Conn().getConnection();
                    int balance = 0;
                    
                    String query1 = 
                    		"SELECT balance FROM transactions WHERE accountnumber = ? ORDER BY transaction_id DESC LIMIT 1";
                    
                    PreparedStatement ps1 = conn.prepareStatement(query1);
                    ps1.setString(1, accountNumber);
                        
                    ResultSet rs1 = ps1.executeQuery();
                    
                    if (rs1.next()) {
                    		balance = rs1.getInt("balance");
                    }
                    
                    rs1.close();
                    ps1.close();
                        
                    if (balance < withdrawAmount) {
	                    	SwingUtilities.invokeLater(() ->
	                        JOptionPane.showMessageDialog(
	                            Withdrawl.this,
	                            "Insufficient Balance"
	                        )
	                    );
	                    	return;
                    }
                    
                    balance -= withdrawAmount;
                    
                    String query2 =
                    		"INSERT INTO transactions (accountnumber, date, type, amount, balance) VALUES (?, ?, ?, ?, ?)";
                    
                    PreparedStatement ps2 = conn.prepareStatement(query2);
                    ps2.setString(1, accountNumber);
                    ps2.setString(2, new Date().toString());
                    ps2.setString(3, "Withdrawl");
                    ps2.setInt(4, withdrawAmount);
                    ps2.setInt(5, balance);
                    
                    ps2.executeUpdate();
                    ps2.close();
                    
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(
                            Withdrawl.this,
                            "Rs " + withdrawAmount + " withdrawn successfully"
                        );
                        animation.fadeOutAndOpen(this, () -> new Transaction(accountNumber));
                    });
                        
                } catch (Exception ex) {
                    ex.printStackTrace();
                    SwingUtilities.invokeLater(() ->
	                    JOptionPane.showMessageDialog(
	                        Withdrawl.this,
	                        "Withdrawal failed"
	                    )
	                );
                }
            }).start();
        }
    }
}
