package bankmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import bankmanagementsystem.base.ATMBaseFrame;
import bankmanagementsystem.db.Conn;

public class PinChnage extends ATMBaseFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
    private JLabel newPinLabel, reNewPinLabel;
    private JPasswordField newPinField, reNewPinField;
    private JButton changeButton, backButton;
    
    private String accountNumber ;

    PinChnage(String accountNumber ) {
    		super();
        this.accountNumber  = accountNumber ;

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color. WHITE);
        text.setFont(new Font ("Syetem", Font.BOLD, 16));
        text.setBounds(265, 300, 500, 30 );
        bgLabel.add(text);

        newPinLabel = new JLabel("New PIN:");
        newPinLabel.setForeground(Color. WHITE);
        newPinLabel.setFont(new Font ("Syetem", Font.BOLD, 16));
        newPinLabel.setBounds(180, 350, 150, 30 );
        bgLabel.add(newPinLabel);

        newPinField = new JPasswordField();
        newPinField.setFont(new Font ("Raleway", Font.BOLD, 16));
        newPinField.setBounds(350, 350, 150, 25 );
        bgLabel.add(newPinField);

        reNewPinLabel = new JLabel("Re-Enter New PIN:");
        reNewPinLabel.setForeground(Color. WHITE);
        reNewPinLabel.setFont(new Font ("Syetem", Font.BOLD, 16));
        reNewPinLabel.setBounds(180, 400, 300, 30 );
        bgLabel.add(reNewPinLabel);

        reNewPinField = new JPasswordField();
        reNewPinField.setFont(new Font ("Raleway", Font.BOLD, 16));
        reNewPinField.setBounds(350, 400, 150, 25 );
        bgLabel.add(reNewPinField);

        changeButton = new JButton("Change");
        changeButton.setBounds(355, 484, 150, 30);
        changeButton.addActionListener(this);
        bgLabel.add(changeButton);

        backButton = new JButton("Back");
        backButton.setBounds(355, 518, 150, 30);
        backButton.addActionListener(this);
        bgLabel.add(backButton);
        
        animation.fadeIn(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
        if (e.getSource() == changeButton) {
        	
	        	String newPin = new String(newPinField.getPassword());
	        	String reNewPin = new String(reNewPinField.getPassword());
	        	
	        	
	        	if (newPin.isEmpty() || reNewPin.isEmpty()) {
	        		JOptionPane.showMessageDialog(newPinField, "PIN fields cannot be empty");
	        		return;
	        	}
	        	
	        	if (!newPin.equals(reNewPin)) {
	        		JOptionPane.showMessageDialog(newPinField, "PINs do not match");
	        		return;
	        	}
	        	
	        	if (!newPin.matches("\\d{4}")) {
	        		JOptionPane.showMessageDialog(newPinField, "PINs must be exaclty 4 digits");
	        		return;
	        	}
	        	
	        	changePin(reNewPin);
	        	
        } else if (e.getSource() == backButton) {
    				animation.fadeOutAndOpen(this, 
    						() -> new Transaction(accountNumber));	
        }
    }
    
    private void changePin(String newPin) {

        Connection connection = null;

        try {
            connection = new Conn().getConnection();
            connection.setAutoCommit(false);

            updatePin(connection, "atmlogin", newPin);
            updatePin(connection, "signupthree", newPin);

            connection.commit();

            JOptionPane.showMessageDialog(this,
                    "PIN changed successfully");

            animation.fadeOutAndOpen(this, () -> new Login());

        } catch (Exception ex) {

            try {
                if (connection != null)
                    connection.rollback();
            } catch (Exception ignored) {}

            JOptionPane.showMessageDialog(this,
                    "PIN change failed");
        }

        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception ignored) {}
        }
    }
    
    private void updatePin(Connection con,
				            String table,
				            String pin) throws Exception {

		String query =
				"UPDATE " + table +
				" SET pinnumber=? WHERE accountnumber=?";
		
		try (PreparedStatement ps =
		 con.prepareStatement(query)) {
		
		ps.setString(1, pin);
		ps.setString(2, accountNumber);
		ps.executeUpdate();
		
		}
    }
    
}
