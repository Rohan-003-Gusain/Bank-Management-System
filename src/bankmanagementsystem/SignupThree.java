package bankmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import bankmanagementsystem.base.SignupBaseFrame;
import bankmanagementsystem.db.Conn;
import bankmanagementsystem.model.SignupData;

public class SignupThree extends SignupBaseFrame  implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private JLabel acctype, cardno, cnumber, cardinfo, pinno, pininfo, cpin, serreq;
	private JRadioButton r1, r2, r3, r4; 
	private JCheckBox c1, c2, c3, c4, c5, c6, c7;
	private ButtonGroup groupaccount;
	private JButton back, submit, cancel;
	
	private SignupData data;
	
	SignupThree(SignupData data) {
		this.data = data;
		initUI();
	}

    private void initUI() {

        JLabel l1 = new JLabel("Page 3: ACCOUNT DETAILS");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

        acctype = new JLabel("Account Type:");
        acctype.setFont(new Font("Raleway", Font.BOLD, 22));
        acctype.setBounds(100, 140, 200, 30);
        add(acctype);

        r1 = createRadio("Saving Account", 100, 180);
        r2 = createRadio("Fixed Deposit Account", 350, 180);
        r3 = createRadio("Current Account", 100, 220);
        r4 = createRadio("Recurring Deposit Account", 350, 220);

        groupaccount = new ButtonGroup();
        groupaccount.add(r1);
        groupaccount.add(r2);
        groupaccount.add(r3);
        groupaccount.add(r4);
        
        cardno = label("Card Number:", 100, 300, 22);
        cnumber = label("XXXX-XXXX-XXXX-XXXX", 330, 300, 22);
        cardinfo = label("Your 16 Digit Card Number", 100, 330, 12);

        pinno = label("PIN Number:", 100, 370, 22);
        cpin = label("XXXX", 330, 370, 22);
        pininfo = label("Your 4 Digit PIN Number", 100, 400, 12);

        serreq = label("Services Required:", 100, 450, 22);

        c1 = check("ATM Card", 100, 500);
        c2 = check("Internet Banking", 350, 500);
        c3 = check("Mobile Banking", 100, 550);
        c4 = check("Email & SMS Alerts", 350, 550);
        c5 = check("Check Book", 100, 600);
        c6 = check("E-Statement", 350, 600);

        c7 = new JCheckBox(
                "I hereby declare that the above entered details are correct to the best of my knowledge");
        c7.setBackground(Color.WHITE);
        c7.setFont(new Font("Raleway", Font.BOLD, 12));
        c7.setBounds(100, 680, 650, 30);
        add(c7);
        
        back = button("Back", 100, 720);
        submit = button("Submit", 275, 720);
        cancel = button("Cancel", 450, 720);
        
        loadData();
        animation.fadeIn(this);
    }
    
    private void loadData() {

	    if (data == null) return;

	    if ("Saving Account".equals(data.accountType))r1.setSelected(true);
	    if ("Fixed Deposit Account".equals(data.accountType))r2.setSelected(true);
	    if ("Current Account".equals(data.accountType))r3.setSelected(true);
	    if ("Recurring Deposit Account".equals(data.accountType))r4.setSelected(true);

	    if (data.facilities != null) {
	        if (data.facilities.contains("ATM Card")) c1.setSelected(true);
	        if (data.facilities.contains("Internet Banking")) c2.setSelected(true);
	        if (data.facilities.contains("Mobile Banking")) c3.setSelected(true);
	        if (data.facilities.contains("Email & SMS Alerts")) c4.setSelected(true);
	        if (data.facilities.contains("Check Book")) c5.setSelected(true);
	        if (data.facilities.contains("E-Statement")) c6.setSelected(true);
	    }
	}
    
    private void savedFormData() {
    	
    		data.accountType = 
        		r1.isSelected() ? "Saving Account" :
			r2.isSelected() ? "Fixed Deposit Account" :
			r3.isSelected() ? "Current Account" :
			r4.isSelected() ? "Recurring Deposit Account" : null;

        StringBuilder facilities = new StringBuilder();

        if (c1.isSelected()) facilities.append("ATM Card, ");
        if (c2.isSelected()) facilities.append("Internet Banking, ");
        if (c3.isSelected()) facilities.append("Mobile Banking, ");
        if (c4.isSelected()) facilities.append("Email & SMS Alerts, ");
        if (c5.isSelected()) facilities.append("Check Book, ");
        if (c6.isSelected()) facilities.append("E-Statement, ");

        data.facilities =
                facilities.length() > 0
                ? facilities.substring(0, facilities.length() - 2)
                : "";
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    		if (e.getSource() == back) {
    			savedFormData();
            animation.fadeOutAndOpen(this,
                    () -> new SignupTwo(data));
            return;
        }
    		
    		if (e.getSource() == cancel) {
    			int result = JOptionPane.showConfirmDialog(
    		            this,
    		            "Do you really want to cancel signup?",
    		            "Confirm Cancel",
    		            JOptionPane.YES_NO_OPTION,
    		            JOptionPane.WARNING_MESSAGE
    		    );

    		    if (result == JOptionPane.YES_OPTION) {
    		        animation.fadeOutAndOpen(
    		                this,
    		                () -> new Login());
    		    }
    		    return;
    		}
    		
    		if (e.getSource() == submit){
            	
    	        if (!c7.isSelected()) {
    	        		JOptionPane.showMessageDialog(
    	        				this, 
    	        				"Please confirm declaration");
    	        		
    	        		c7.requestFocus();
    	        		return;
    	        }
    	        
    	        savedFormData();
    	        
    	        submit.setText("Processing...");
    	        submit.setEnabled(false);
    	        
    	        Random rn = new Random();
    	        
    	        data.accountNumber =
    	        		String.format("%04d%04d%04d%04d",
    	        				rn.nextInt(10000),
    	        				rn.nextInt(10000),
    	        				rn.nextInt(10000),
    	        				rn.nextInt(10000));
    	        
    	        data.pinNumber =
    	        		String.format("%04d", rn.nextInt(10000));
    	        
    	        new Thread(() -> saveToDatabase()).start();
    		}
    }
    
    private void saveToDatabase() {
    	
    		Connection conn = null;
    		
    		try {
    			conn = new Conn().getConnection();
    			conn.setAutoCommit(false);
  
    			// ---------- Sign up one ----------
    			PreparedStatement ps1 = 
    					conn.prepareStatement(
    							"INSERT INTO signupone "
    							+ "(formno, name, fname, dob, gender, "
    							+ "phoneNumber, email, marital, "
    							+ "address, city, state, pincode) "
    							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    			
    			ps1.setString(1, data.formno);    
    			ps1.setString(2, data.name);
    			ps1.setString(3, data.fatherName);
    			ps1.setDate(4, new java.sql.Date(data.dob.getTime()));
    			ps1.setString(5, data.gender);
    			ps1.setString(6, data.phone);
    			ps1.setString(7, data.email);
    			ps1.setString(8, data.marital);
    			ps1.setString(9, data.address);
    			ps1.setString(10, data.city);
    			ps1.setString(11, data.state);
    			ps1.setString(12, data.pinCode);
                
    			ps1.executeUpdate();
    			
    			// ---------- Sign up two ----------	
    			PreparedStatement ps2 = conn.prepareStatement(
                        "INSERT INTO signuptwo "
                        + "(formno, religion, category, income, "
                        + "eduQualification, occupation, "
                        + "panNumber, aadharNumber, "
                        + "seniorCitizen, exisitngAccount) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    			
    			ps2.setString(1, data.formno);
    			ps2.setString(2, data.religion);
    			ps2.setString(3, data.category);
    			ps2.setString(4, data.income);
    			ps2.setString(5, data.education);
    			ps2.setString(6, data.occupation);
    			ps2.setString(7, data.pan);
    			ps2.setString(8, data.aadhar);
    			ps2.setString(9, data.seniorCitizen);
    			ps2.setString(10, data.existingAccount);
    			
    			ps2.executeUpdate();
				
    			// ---------- Sign up three ----------
			PreparedStatement ps3 = conn.prepareStatement(
						"INSERT INTO signupthree "
						+ "(formno, acctype, accountnumber, pinnumber, facility) "
						+ "VALUES (?, ?, ?, ?, ?)");
			
			ps3.setString(1, data.formno);
			ps3.setString(2, data.accountType);
			ps3.setString(3, data.accountNumber);
			ps3.setString(4, data.pinNumber);
			ps3.setString(5, data.facilities);
			
			ps3.executeUpdate();
			
			// ---------- ATM login----------
			PreparedStatement ps4 = conn.prepareStatement(
	            "INSERT INTO atmlogin "
	            + "(formno, accountnumber, pinnumber) "
	            + "VALUES (?, ?, ?)"
	        );
	        ps4.setString(1, data.formno);
	        ps4.setString(2, data.accountNumber);
	        ps4.setString(3, data.pinNumber);
	        
	        ps4.executeUpdate();
	        
	        conn.commit();
	        
	        ps1.close();
	        ps2.close();
	        ps3.close();
	        ps4.close();
	        
	        SwingUtilities.invokeLater(() -> {
            	
        		submit.setText("Submit");
            submit.setEnabled(true);
            
            JOptionPane.showMessageDialog(
                this,
                "Account Created Successfully!\n\n" 
                		+ "Account Number: "
                    + data.accountNumber +
                    "\nPIN: " + data.pinNumber
            );
            
            animation.fadeOutAndOpen(
                this,
                () -> new Login());
        });
    			
    		} catch (Exception ex) {
				
    			try {
    				if (conn != null) {
    					conn.rollback();
    				}
    			} catch (Exception ignored) {}
	                
    			SwingUtilities.invokeLater(() -> {
	            		
    				submit.setText("Submit");
    				submit.setEnabled(true);
    	            		
    				JOptionPane.showMessageDialog(
    						this,
    						"Signup failed",
    						"Error",
    						JOptionPane.ERROR_MESSAGE);
    			});

    			ex.printStackTrace();
    		}
    }
    	        
    		
    private JRadioButton createRadio(String text, int x, int y) {
        JRadioButton rb = new JRadioButton(text);
        rb.setFont(new Font("Raleway", Font.BOLD, 16));
        rb.setBackground(Color.WHITE);
        rb.setBounds(x, y, 250, 30);
        add(rb);
        return rb;
    }
    
    private JLabel label(String text, int x, int y, int size) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Raleway", Font.BOLD, size));
        l.setBounds(x, y, 400, 30);
        add(l);
        return l;
    }

    private JCheckBox check(String text, int x, int y) {
        JCheckBox cb = new JCheckBox(text);
        cb.setBackground(Color.WHITE);
        cb.setFont(new Font("Raleway", Font.BOLD, 16));
        cb.setBounds(x, y, 250, 30);
        add(cb);
        return cb;
    }
    
    private JButton button(String text, int x, int y) {
        JButton b = new JButton(text);
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Raleway", Font.BOLD, 14));
        b.setBounds(x, y, 100, 30);
        b.addActionListener(this);
        add(b);
        return b;
    }
 
}
