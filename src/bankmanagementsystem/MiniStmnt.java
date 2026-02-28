package bankmanagementsystem;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import bankmanagementsystem.db.Conn;
public class MiniStmnt extends JFrame{

	private static final long serialVersionUID = 1L;
	
    private JLabel statementLabel, bankNameLabel, cardNumberLabel, balanceLabel;
    
    private String accountNumber;

    MiniStmnt(String accountNumber) {
        this.accountNumber = accountNumber;
        
        setTitle("Mini Statement");

        statementLabel = new JLabel();
        statementLabel.setBounds(20, 140, 400, 200);
        add(statementLabel);

        bankNameLabel = new JLabel("Indian Bank");
        bankNameLabel.setBounds(150, 20, 100, 20);
        add(bankNameLabel);

        cardNumberLabel = new JLabel();
        cardNumberLabel.setBounds(20, 80, 300, 20);
        add(cardNumberLabel);

        balanceLabel = new JLabel();
        balanceLabel.setBounds(20, 400, 300, 20);
        add(balanceLabel);
        
        setLayout(null);
        setSize(400,600);
        setLocation(20, 40);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
        statementLabel.setText("Loading mini statement...");
        
        loadMiniStatement();

    }
    
    private void loadMiniStatement() {

        new Thread(() -> {
        	
            int balance = 0;
            StringBuilder statementHtml = new StringBuilder("<html>");

            try {
	            	Connection conn = new Conn().getConnection();
	
	            	String maskedCard =
	            	accountNumber.substring(0,4)
	            	+ "XXXXXXXX"
	            	+ accountNumber.substring(accountNumber.length() - 4);
	
	            	String query1 =
	            	"SELECT date, type, amount FROM transactions"
	            	+ " WHERE accountnumber=? "
	            	+ "ORDER BY transaction_id DESC LIMIT 5";
	
	            	PreparedStatement ps1 = conn.prepareStatement(query1);
	            	ps1.setString(1, accountNumber);
	
	            	ResultSet rs1 = ps1.executeQuery();
	
	            	while (rs1.next()) {
	            	    statementHtml.append(rs1.getString("date"))
	            	        .append("&nbsp;&nbsp;")
	            	        .append(rs1.getString("type"))
	            	        .append("&nbsp;&nbsp;")
	            	        .append(rs1.getInt("amount"))
	            	        .append("<br><br>");
	            	}
	
	            	rs1.close();
	            	ps1.close();
	
	            	String query2 =
	            	"SELECT balance FROM transactions "
	            	+ "WHERE accountnumber=? "
	            	+ "ORDER BY transaction_id DESC LIMIT 1";
	
	            	PreparedStatement ps2 = conn.prepareStatement(query2);
	            	ps2.setString(1, accountNumber);
	
	            	ResultSet rs2 = ps2.executeQuery();
	
	            	if(rs2.next()){
	            	    balance = rs2.getInt("balance");
	            	}
	
	            	rs2.close();
	            	ps2.close();
	
	            	statementHtml.append("</html>");
	
	            	int finalBalance = balance;
	
	            	SwingUtilities.invokeLater(() -> {
	            	    cardNumberLabel.setText(
	            	    		"Account Number : " + maskedCard);
	            	    statementLabel.setText(statementHtml.toString());
	            	    balanceLabel.setText(
	            	        "Your current account balance is Rs : " 
	            	    + finalBalance);
	            	}); 

            } catch (Exception ex) {
                ex.printStackTrace();
                SwingUtilities.invokeLater(() ->
                    statementLabel.setText("Unable to load mini statement")
                );
            }
        }).start();
    }
}
