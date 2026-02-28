package bankmanagementsystem;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import bankmanagementsystem.base.SignupBaseFrame;
import bankmanagementsystem.model.SignupData;

public class SignupTwo extends SignupBaseFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
    private JLabel additionalDetails, religion, category, income,  eduQualification, occupation, panNumber, aadharNumber, seniorCitizen, exisitngAccount;
    private JTextField tpanNumber, taadharNumber;
    private JButton back, next;
    private JComboBox<String> creligion, ccategory, cincome, ceduQualification, coccupation;
    private JRadioButton syes, sno, eyes, eno;
    private ButtonGroup bseniorCitizen, bexistingAccount;
    private SignupData data;
    
    SignupTwo(SignupData data) {
		this.data = data;
		initUI();
	}
    
private void initUI() {
        
        setTitle("NEW ACCOUNT APPLICATION FOMR - PAGE 2");

        additionalDetails = new JLabel("Page 2: Additionl Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);

        religion = new JLabel("Religion:");
        religion.setFont(new Font("Raleway", Font.BOLD, 22));
        religion.setBounds(100, 140, 400, 30);
        add(religion);

        String valReligion[] = {"Hindu","Muslim","Sikh","Christian","Other"};
        creligion = new JComboBox<>(valReligion);
        creligion.setBackground(Color.WHITE);
        creligion.setBounds(300, 140, 400, 30);
        add(creligion);

        category = new JLabel("Category:");
        category.setFont(new Font("Raleway", Font.BOLD, 22));
        category.setBounds(100, 190, 400, 30);
        add(category);

        String valCategroy[] = {"Genral","OBC","ST","SC","Other"};
        ccategory = new JComboBox<>(valCategroy);
        ccategory.setBackground(Color.WHITE);
        ccategory.setBounds(300, 190, 400, 30);
        add(ccategory);
 
        income = new JLabel("Income:");
        income.setFont(new Font("Raleway", Font.BOLD, 22));
        income.setBounds(100, 240, 400,30);
        add(income);

        String valIncome[] = {"Null","< 1,50,000","< 2,50,000","< 5,50,5000","<Upto 10,00,000"};
        cincome = new JComboBox<>(valIncome);
        cincome.setBackground(Color.WHITE);
        cincome.setBounds(300, 240, 400, 30);
        add(cincome);
        

        eduQualification = new JLabel("<html>Educational<br>Qualification</html>:");
        eduQualification.setFont(new Font("Raleway", Font.BOLD, 22));
        eduQualification.setBounds(100, 290, 200, 60);
        add(eduQualification);

        String valEducation[] = {"Non-Graduation","Graduate","Post Graduate","Phd","Ohter"};
        ceduQualification = new JComboBox<>(valEducation);
        ceduQualification.setBackground(Color.WHITE);
        ceduQualification.setBounds(300, 320, 400, 30);
        add(ceduQualification);


        occupation = new JLabel("Occupation:");
        occupation.setFont(new Font("Raleway", Font.BOLD, 22));
        occupation.setBounds(100, 370, 400, 30);
        add(occupation);

        String valOccupation[] = {"Salaried","Self-Employee","Bussiness","Student","Retired","Other"};
        coccupation = new JComboBox<>(valOccupation);
        coccupation.setBackground(Color.WHITE);
        coccupation.setBounds(300, 370, 400, 30);
        add(coccupation);
        
        panNumber = new JLabel("Pan Number:");
        panNumber.setFont(new Font("Raleway", Font.BOLD, 22));
        panNumber.setBounds(100, 420, 400, 30);
        add(panNumber);

        tpanNumber = new JTextField();
        tpanNumber.setFont(new Font("Raleway", Font.PLAIN, 16));
        tpanNumber.setBounds(300, 420, 400, 30);
        add(tpanNumber);

        aadharNumber = new JLabel("Aadhar Number:");
        aadharNumber.setFont(new Font("Raleway", Font.BOLD, 22));
        aadharNumber.setBounds(100, 470, 400, 30);
        add(aadharNumber);

        taadharNumber = new JTextField();
        taadharNumber.setFont(new Font("Raleway", Font.PLAIN, 16));
        taadharNumber.setBounds(300, 470, 400, 30);
        add(taadharNumber);

        seniorCitizen = new JLabel("Senior Citizen:");
        seniorCitizen.setFont(new Font("Raleway", Font.BOLD, 22));
        seniorCitizen.setBounds(100, 520, 400, 30);
        add(seniorCitizen);

        syes = new JRadioButton("Yes");
        syes.setBackground(Color.WHITE);
        syes.setBounds(300, 520, 100, 30);
        add(syes);

        sno = new JRadioButton("no");
        sno.setBackground(Color.WHITE);
        sno.setBounds(450, 520, 100, 30);
        add(sno);

        bseniorCitizen = new ButtonGroup();
        bseniorCitizen.add(syes);
        bseniorCitizen.add(sno);


        exisitngAccount = new JLabel("Exisiting Account:");
        exisitngAccount.setFont(new Font("Raleway", Font.BOLD, 22));
        exisitngAccount.setBounds(100, 570, 400, 30);
        add(exisitngAccount);

        eyes = new JRadioButton("Yes");
        eyes.setBackground(Color.WHITE);
        eyes.setBounds(300, 570, 100, 30);
        add(eyes);

        eno = new JRadioButton("no");
        eno.setBackground(Color.WHITE);
        eno.setBounds(450, 570, 100, 30);
        add(eno);

        bexistingAccount = new ButtonGroup();
        bexistingAccount.add(eyes);
        bexistingAccount.add(eno);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Raleway", Font.BOLD, 14));
        back.setBounds(300, 660, 80, 30);
        back.addActionListener(this);
        add(back);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        loadData();
        animation.fadeIn(this);
    }

	private void loadData() {
	
	    if (data == null) return;
	
	    if (data.religion != null)
	        creligion.setSelectedItem(data.religion);

	    if (data.category != null)
	        ccategory.setSelectedItem(data.category);

	    if (data.income != null)
	        cincome.setSelectedItem(data.income);

	    if (data.education != null)
	        ceduQualification.setSelectedItem(data.education);

	    if (data.occupation != null)
	        coccupation.setSelectedItem(data.occupation);
	
	    tpanNumber.setText(data.pan);
	    taadharNumber.setText(data.aadhar);
	
	    if ("Yes".equals(data.seniorCitizen))
	        syes.setSelected(true);
	    else if ("No".equals(data.seniorCitizen))
	        sno.setSelected(true);
	
	    if ("Yes".equals(data.existingAccount))
	        eyes.setSelected(true);
	    else if ("No".equals(data.existingAccount))
	        eno.setSelected(true);
	}
    
    private void saveFormData() {

        data.religion = (String) creligion.getSelectedItem();
        data.category = (String) ccategory.getSelectedItem();
        data.income = (String) cincome.getSelectedItem();
        data.education = (String) ceduQualification.getSelectedItem();
        data.occupation = (String) coccupation.getSelectedItem();

        data.pan = tpanNumber.getText().trim();
        data.aadhar = taadharNumber.getText().trim();

        data.seniorCitizen =
                syes.isSelected() ? "Yes" :
                sno.isSelected() ? "No" : null;

        data.existingAccount =
                eyes.isSelected() ? "Yes" :
                eno.isSelected() ? "No" : null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
	    	if (e.getSource() == back) {
	    		saveFormData();
	    		animation.fadeOutAndOpen(this, () -> new SignupOne(data));
	    		return;
	    }
	    	
	    	if (e.getSource() == next) {
	    		
	    		String pan = tpanNumber.getText().trim();
	    		String aadhar = taadharNumber.getText().trim();
	        
	        if (pan.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "PAN Number is required");
	            return;
	        }

	        if (!pan.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
	            JOptionPane.showMessageDialog(this, "Enter valid PAN number (ABCDE1234F)");
	            return;
	        }

	        if (aadhar.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Aadhar Number is required");
	            return;
	        }

	        if (!aadhar.matches("\\d{12}")) {
	            JOptionPane.showMessageDialog(this, "Enter valid 12 digit Aadhar number");
	            return;
	        }
	    
	    		saveFormData();
	        
	        animation.fadeOutAndOpen(this, () -> new SignupThree(data));
	    		
	    	}	 
    }
   
}   
