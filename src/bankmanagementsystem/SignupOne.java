package bankmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import bankmanagementsystem.base.SignupBaseFrame;
import bankmanagementsystem.model.SignupData;

public class SignupOne extends SignupBaseFrame  implements ActionListener{
	
	private static final long serialVersionUID = 1L;
    
	private Long random;
    private JLabel personalDetails, formno, name, fname, dob, gender,phoneNumber, email, marital, address, city, state, pincode;
    private JTextField tname, tfname, tphoneNumber, temail, taddress, tcity, tstate, tpincode;
    private JButton back, next;
    private JDateChooser tdob;
    private JRadioButton male, female, single, married;
    private ButtonGroup bgender, bmarital;
    
    private SignupData data;

    SignupOne() {
    		data = new SignupData();
    		initUI();
    }
    
    SignupOne(SignupData data) {
    		this.data = data;
    		initUI();
    }
    
    private void savedFormData() {
    	
    		data.formno = String.valueOf(random);
        data.name = tname.getText();
        data.fatherName = tfname.getText();
        
        data.dob = tdob.getDate() != null
                ? new java.util.Date(tdob.getDate().getTime())
                : null;
        
        data.gender = male.isSelected() ? "Male" :
                      female.isSelected() ? "Female" : null;
        
        data.phone = tphoneNumber.getText(); 
        data.email = temail.getText();
        
        data.marital = single.isSelected() ? "Single" :
                       married.isSelected() ? "Married" : null;
        
        data.address = taddress.getText();
        data.city = tcity.getText();
        data.state = tstate.getText();
        data.pinCode = tpincode.getText();
    }
    
    private void initUI() {
    	
		Random ran = new Random();
	    random = (Math.abs((ran.nextLong() % 9000L) + 1000L));
	
	    formno = new JLabel("APPLICATION FORM NO: " + random);
	    formno.setFont(new Font("Raleway", Font.BOLD, 38));
	    formno.setBounds(140, 20, 600, 40);
	    add(formno);
	
	    personalDetails = new JLabel("Page 1: Personal Details");
	    personalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
	    personalDetails.setBounds(290, 80, 400, 30);
	    add(personalDetails);
	
	    name = new JLabel("Name:");
	    name.setFont(new Font("Raleway", Font.BOLD, 22));
	    name.setBounds(100, 140, 400, 30);
	    add(name);
	
	    tname = new JTextField();
	    tname.setFont(new Font ("Raleway", Font.PLAIN, 16));
	    tname.setBounds(300, 140, 400, 30);
	    add(tname);
	
	    fname = new JLabel("Father's Name:");
	    fname.setFont(new Font("Raleway", Font.BOLD, 22));
	    fname.setBounds(100, 190, 400, 30);
	    add(fname);
	
	    tfname = new JTextField();
	    tfname.setFont(new Font("Raleway", Font.PLAIN, 16));
	    tfname.setBounds(300, 190, 400, 30);
	    add(tfname); 
	
	    dob = new JLabel("Date of birth:");
	    dob.setFont(new Font("Raleway", Font.BOLD, 22));
	    dob.setBounds(100, 240, 400,30);
	    add(dob);
	
	    tdob = new JDateChooser();
	    tdob.setMaxSelectableDate(new java.util.Date());
	    tdob.setBounds(300, 240, 400, 30);
	    tdob.setForeground(Color.RED);
	    add(tdob);
	
	    gender = new JLabel("Gender:");
	    gender.setFont(new Font("Raleway", Font.BOLD, 22));
	    gender.setBounds(100, 290, 400, 30);
	    add(gender);
	
	    male = new JRadioButton("Male");
	    male.setBackground(Color.WHITE);
	    male.setBounds(300, 290, 100, 30);
	    add(male);
	
	    female = new JRadioButton("Female");
	    female.setBackground(Color.WHITE);
	    female.setBounds(450, 290, 100, 30);
	    add(female);
	
	    bgender = new ButtonGroup();
	    bgender.add(male);
	    bgender.add(female);
	
	    phoneNumber = new JLabel("Phone Number:");
	    phoneNumber.setFont(new Font("Raleway", Font.BOLD, 22));
	    phoneNumber.setBounds(100, 340, 400, 30);
	    add(phoneNumber);
	
	    tphoneNumber = new JTextField();
	    tphoneNumber.setFont(new Font ("Raleway", Font.PLAIN, 20));
	    tphoneNumber.setBounds(300, 340, 400, 30);
	    add(tphoneNumber);
	
	    email = new JLabel("Email:");
	    email.setFont(new Font("Raleway", Font.BOLD, 22));
	    email.setBounds(100, 390, 400, 30);
	    add(email);
	
	    temail = new JTextField();
	    temail.setFont(new Font("Raleway", Font.PLAIN, 16));
	    temail.setBounds(300, 390, 400, 30);
	    add(temail);
	
	    marital = new JLabel("Marital Status:");
	    marital.setFont(new Font("Raleway", Font.BOLD, 22));
	    marital.setBounds(100, 440, 400, 30);
	    add(marital);
	
	    married = new JRadioButton("Married");
	    married.setBackground(Color.WHITE);
	    married.setBounds(300, 440, 100, 30);
	    add(married);
	
	    single = new JRadioButton("Single");
	    single.setBackground(Color.WHITE);
	    single.setBounds(450, 440, 100, 30);
	    add(single);
	
	    bmarital = new ButtonGroup();
	    bmarital.add(married);
	    bmarital.add(single);
	    
	    address = new JLabel("Address:");
	    address.setFont(new Font("Raleway", Font.BOLD, 22));
	    address.setBounds(100, 490, 400, 30);
	    add(address);
	
	    taddress = new JTextField();
	    taddress.setFont(new Font("Raleway", Font.PLAIN, 16));
	    taddress.setBounds(300, 490, 400, 30);
	    add(taddress);
	
	    city = new JLabel("City:");
	    city.setFont(new Font("Raleway", Font.BOLD, 22));
	    city.setBounds(100, 540, 400, 30);
	    add(city);
	
	    tcity = new JTextField();
	    tcity.setFont(new Font("Raleway", Font.PLAIN, 16));
	    tcity.setBounds(300, 540, 400, 30);
	    add(tcity);
	
	    state = new JLabel("State:");
	    state.setFont(new Font("Raleway", Font.BOLD, 22));
	    state.setBounds(100, 590, 400, 30);
	    add(state);
	
	    tstate = new JTextField();
	    tstate.setFont(new Font("Raleway", Font.PLAIN, 16));
	    tstate.setBounds(300, 590, 400, 30);
	    add(tstate);
	
	    pincode = new JLabel("Pin Code:");
	    pincode.setFont(new Font("Raleway", Font.BOLD, 22));
	    pincode.setBounds(100, 640, 400, 30);
	    add(pincode);
	
	    tpincode = new JTextField();
	    tpincode.setFont(new Font("Raleway", Font.PLAIN, 16));
	    tpincode.setBounds(300, 640, 400, 30);
	    add(tpincode);
	    
	    back = new JButton("Back");
	    back.setBackground(Color.BLACK);
	    back.setForeground(Color.WHITE);
	    back.setFont(new Font("Raleway", Font.BOLD, 14));
	    back.setBounds(300, 700, 80, 30);
	    back.addActionListener(this);
	    add(back);
	
	    next = new JButton("Next");
	    next.setBackground(Color.BLACK);
	    next.setForeground(Color.WHITE);
	    next.setFont(new Font("Raleway", Font.BOLD, 14));
	    next.setBounds(620, 700, 80, 30);
	    next.addActionListener(this);
	    add(next);
	
	    loadData();
	    animation.fadeIn(this);
    }
    
    private void loadData() {

        if (data == null) return;

        if (data.formno != null) {
        		this.random = Long.parseLong(data.formno);
            formno.setText("APPLICATION FORM NO: " + random);
        }

            tname.setText(data.name);
            tfname.setText(data.fatherName);
            
            if (data.dob != null)
                tdob.setDate(data.dob);

            tphoneNumber.setText(data.phone);
            temail.setText(data.email);
            taddress.setText(data.address);
            tcity.setText(data.city);
            tstate.setText(data.state);
            tpincode.setText(data.pinCode);

            if ("Male".equals(data.gender)) {
                male.setSelected(true);
            } else if ("Female".equals(data.gender)) {
                female.setSelected(true);
            }

            if ("Single".equals(data.marital)) {
                single.setSelected(true);
            } else if ("Married".equals(data.marital)) {
                married.setSelected(true);
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        
	    if (e.getSource() == back) {
	    		animation.fadeOutAndOpen(this, () -> new Login());
	    }
	    	
	    if (e.getSource() == next) {
	        
	        String name = tname.getText();
	        if (name.isEmpty()) {
		        	JOptionPane.showMessageDialog(
		        			this,
		        			"Name is Requried",
		        			"Validation Error",
		        			JOptionPane.WARNING_MESSAGE
		        			);
		        	return;
	        }
	        
	        java.util.Date selectedDate = tdob.getDate();
	        if (selectedDate == null) {
	            JOptionPane.showMessageDialog(this, "Date of Birth is required");
	            return;
	        }
	        
	        if (!male.isSelected() && !female.isSelected()) {
	            JOptionPane.showMessageDialog(this, "Please select Gender");
	            return;
	        }
	        
	        if (!tphoneNumber.getText().matches("\\d{10}")) {
	            JOptionPane.showMessageDialog(this, "Enter valid 10 digit phone number");
	            return;
	        }
	        
	        if (!temail.getText().isEmpty() &&
	        	    !temail.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	        	    JOptionPane.showMessageDialog(this, "Enter valid email address");
	        	    return;
	        	}
	        
	        if (!single.isSelected() && !married.isSelected()) {
	            JOptionPane.showMessageDialog(this, "Please select Marital Status");
	            return;
	        }
	        
	        if (!tpincode.getText().matches("\\d{6}")) {
	            JOptionPane.showMessageDialog(this, "Enter valid 6 digit pincode");
	            return;
	        }
	        
	        savedFormData();
	        
	        animation.fadeOutAndOpen(this, () -> new SignupTwo(data));
	    }
    	
    }
}
