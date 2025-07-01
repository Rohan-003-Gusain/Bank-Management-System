package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SignupTwo extends JFrame implements ActionListener{

    JLabel additionalDetails, religion, category, income,  eduQualification, occupation, panNumber, aadharNumber, seniorCitizen, exisitngAccount;
    JTextField tpanNumber, taadharNumber;
    JComboBox<String> creligion, ccategory, cincome, ceduQualification, cquakification, coccupation;
    JRadioButton syes, sno, eyes, eno;
    ButtonGroup bseniorCitizen, bexistingAccount;
    String formno;

    SignupTwo(String formno) {

        this.formno = formno;
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
        occupation.setFont(new Font("Ralewat", Font.BOLD, 22));
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

        JButton next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLayout(null);
        setLocation(350,10);
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String religion = (String) creligion.getSelectedItem();
        String category = (String) ccategory.getSelectedItem();
        String income = (String) cincome.getSelectedItem();
        String eduQualification = (String) ceduQualification.getSelectedItem();

        String occupation = (String) coccupation.getSelectedItem();
        String panNumber = (String) tpanNumber.getText();
        String aadharNumber = (String) taadharNumber.getText();
        
        String seniorCitizen = null;
        if (syes.isSelected()) {
            seniorCitizen = "Yes";
        } else if (sno.isSelected()) {
            seniorCitizen = "No";
        }

        String exisitngAccount = null;
        if (eyes.isSelected()) {
            exisitngAccount = "Yes";
        } else if (eno.isSelected()) {
            exisitngAccount = "No";
        }

        try {
                Conn con = new Conn();
                String query = "insert into signuptwo  values('"+formno+"','"+religion+"','"+category+"','"+income+"','"+eduQualification+"','"+occupation+"','"+panNumber+"','"+aadharNumber+"','"+seniorCitizen+"','"+exisitngAccount+"')";
                con.statement.executeUpdate(query);
                System.out.println(query);

                setVisible(false);
                new SignupThree(formno).setVisible(true);
            
        } catch (Exception E) {
            E.printStackTrace();
        }

    }
    public static void main(String[] args) {
        new SignupTwo("");
    }
}
