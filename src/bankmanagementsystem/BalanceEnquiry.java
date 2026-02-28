package bankmanagementsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import bankmanagementsystem.base.ATMBaseFrame;
import bankmanagementsystem.db.Conn;

public class BalanceEnquiry extends ATMBaseFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JButton backButton;
	private JLabel balanceLabel;
	
    private String accountNumber;

    BalanceEnquiry(String accountNumber) {
    		super();
        this.accountNumber = accountNumber;

        backButton = new JButton("Back");
        backButton.setBounds(355, 519, 150, 30);
        backButton.addActionListener(this);
        bgLabel.add(backButton);

        balanceLabel = new JLabel("Fetching balance...");
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBounds(170, 300, 400, 30);
        bgLabel.add(balanceLabel);
        
        animation.fadeIn(this);
        
        loadBalance();

    }
    
    private void loadBalance() {

        new Thread(() -> {
            int balance = 0;

            try {
            		Connection conn = new Conn().getConnection();
                
                String query =
                        "SELECT balance FROM transactions " +
                        "WHERE accountnumber=? " +
                        "ORDER BY transaction_id DESC LIMIT 1";
                	
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, accountNumber);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                		balance = rs.getInt("balance");
                }

                rs.close();
                ps.close();

                int finalBalance = balance;

                SwingUtilities.invokeLater(() -> {
                    balanceLabel.setText(
                        "Your Current Account balance is Rs : " 
                        	+ finalBalance
                    );
                });

            } catch (Exception ex) {
                ex.printStackTrace();
                
                SwingUtilities.invokeLater(() -> {
                    balanceLabel.setText(
                    		"Unable to fetch balance");
                });
            }
        }).start();
    }

    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
        		animation.fadeOutAndOpen(this, () -> new Transaction(accountNumber));
        });
    }
  
}
