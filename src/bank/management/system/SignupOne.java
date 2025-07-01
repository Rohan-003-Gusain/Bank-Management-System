package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import com.toedter.calendar.JDateChooser;

public class SignupOne extends JFrame implements ActionListener{

    Long random;
    JLabel personalDetails, formno, name, fname, dob, gender,phoneNumber, email, marital, address, city, state, pincode;
    JTextField tname, tfname, tgender, tphoneNumber, temail, taddress, tcity, tstate, tpincode;
    JButton next;
    JDateChooser tdob;
    JRadioButton male, female, single, married;
    ButtonGroup bgender, bmarital;

    SignupOne() {

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
        marital.setFont(new Font("Ralewat", Font.BOLD, 22));
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

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 700, 80, 30);
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
        String formno = "" + random;
        String name = "" + tname.getText();
        String fname = "" + tfname.getText();
        String dob = "" + ((JTextField) tdob.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }

        String phoneNumber = "" + tphoneNumber.getText();

        String email = "" + temail.getText();
        String marital = null;
        if (single.isSelected()) {
            marital = "Single";
        } else if (married.isSelected()) {
            marital = "Married";
        }
        String address = "" + taddress.getText();
        String city = "" + tcity.getText();
        String pincode = "" + tpincode.getText();
        String state = "" + tstate.getText();

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is Requried");
            } else {
                Conn con = new Conn();
                String query = "insert into signupone values('"+formno+"','"+name+"','"+fname+"','"+dob+"','"+gender+"','"+phoneNumber+"','"+email+"','"+marital+"','"+address+"','"+city+"','"+state+"','"+pincode+"')";
                con.statement.executeUpdate(query);
                System.out.println(query);

                setVisible(false);
                new SignupTwo(formno).setVisible(true);
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new SignupOne();
    }
}
