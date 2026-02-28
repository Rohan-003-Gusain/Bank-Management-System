package bankmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bankmanagementsystem.base.Animation;
import bankmanagementsystem.db.Conn;

public class Login extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private Animation animation = new Animation();
    private JButton loginButton, signupButton, clearButton;
    private JTextField cardTextField;
    private JPasswordField pinTextField;
    
    Login() {
        setTitle("SecureBank ATM - Login");

        ImageIcon i1 = new ImageIcon(getClass().getResource("images/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100,100);
        add(label);
        
        JLabel text = new JLabel("Welcome to SecureBank ATM");
        text.setFont(new Font("Osward",Font.BOLD,35));
        text.setBounds(200, 40, 500, 40);
        add(text);

        JLabel cardno = new JLabel("Card No:");
        cardno.setFont(new Font("Raleway",Font.BOLD,28));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway",Font.BOLD,28));
        pin.setBounds(120, 220, 400, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        loginButton = new JButton("SIGN IN");
        loginButton.setBounds(300, 300, 100, 30);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener((ActionListener) this);
        add(loginButton);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(430, 300, 100, 30);
        clearButton.setBackground(Color.BLACK);
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener((ActionListener)this);
        add(clearButton);

        signupButton = new JButton("SIGN UP");
        signupButton.setBounds(300, 350, 230, 30);
        signupButton.setBackground(Color.BLACK);
        signupButton.setForeground(Color.WHITE);
        signupButton.addActionListener((ActionListener)this);
        add(signupButton);

        getContentPane().setBackground(Color.WHITE);
        setUndecorated(true);
        setLayout(null);
        setSize(800, 500);
        setLocation(350, 200);
        setVisible(true);
        
        animation.fadeIn(this);
        
    }

    public void actionPerformed(ActionEvent e) {
    	
        if (e.getSource() == loginButton) {
        	
            new Thread(() -> {
            		String accountNumber = cardTextField.getText();
                String pinNumber = String.valueOf(pinTextField.getPassword());
                
                String query = 
                		"SELECT * FROM atmlogin "
                		+ "where accountnumber = ? AND pinnumber = ?";
                
                try (Connection conn = new Conn().getConnection();
                     PreparedStatement ps = conn.prepareStatement(query);
                		) {
                		
                    ps.setString(1, accountNumber);
                    ps.setString(2, pinNumber);
                    
                    ResultSet rs = ps.executeQuery();
                    
                    if (rs.next()) {
    	                    SwingUtilities.invokeLater(() -> {
    	                    		animation.fadeOutAndOpen(Login.this,() -> new Transaction(accountNumber));
                        });
                        
                    } else {
                        	SwingUtilities.invokeLater(() -> {
                        		JOptionPane.showMessageDialog(
                        			Login.this,
                        			"Incorrect Card Number or PIN"
                        		);
                        	});
                    }
                    
                    rs.close();
                    ps.close();
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    SwingUtilities.invokeLater(() -> {
                    		JOptionPane.showMessageDialog(
                    			Login.this,
                    			"Database error occurred"
                    		);
                    });
                }
            }).start();

        } else if (e.getSource() == clearButton) {
            cardTextField.setText("");
            pinTextField.setText("");

        } else if (e.getSource() == signupButton) {
            SwingUtilities.invokeLater(() -> {
            		animation.fadeOutAndOpen(this,() -> new SignupOne());
            });
        } 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login();
        });
    }
}
