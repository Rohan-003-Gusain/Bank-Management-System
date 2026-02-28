package bankmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import bankmanagementsystem.base.ATMBaseFrame;

public class Transaction extends ATMBaseFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
    private JLabel heading;
    private JButton deposit, withdraw, fastCash, miniStatement, pinChange, balanceEnquiry, exit;
    
    private String accountNumber;

    Transaction(String accountNumber) {
    		super();
        this.accountNumber = accountNumber;

        heading = new JLabel("Please select your Transaction");
        heading.setFont(new Font("System", Font.BOLD, 16));
        heading.setBounds(220, 300, 400, 30);
        heading.setForeground(Color.WHITE);
        bgLabel.add(heading);

        deposit = new JButton("Deposit");
        deposit.setBounds(170, 418, 150, 30);
        deposit.addActionListener(this);
        bgLabel.add(deposit);

        withdraw = new JButton("Cash Withdrawal");
        withdraw.setBounds(355, 418, 150, 30);
        withdraw.addActionListener(this);
        bgLabel.add(withdraw);

        fastCash = new JButton("Fast Cash");
        fastCash.setBounds(170, 452, 150, 30);
        fastCash.addActionListener(this);
        bgLabel.add(fastCash);
        
        miniStatement = new JButton("Mini Statement");
        miniStatement.setBounds(355, 452, 150, 30);
        miniStatement.addActionListener(this);
        bgLabel.add(miniStatement);
        
        pinChange = new JButton("PIN Change");
        pinChange.setBounds(170, 485, 150, 30);
        pinChange.addActionListener(this);
        bgLabel.add(pinChange);

        balanceEnquiry = new JButton("Balance Enquiry");
        balanceEnquiry.setBounds(355, 485, 150, 30);
        balanceEnquiry.addActionListener(this);
        bgLabel.add(balanceEnquiry);

        exit = new JButton("EXIT");
        exit.setBounds(355, 519, 150, 30);
        exit.addActionListener(this);
        bgLabel.add(exit);

        animation.fadeIn(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
        if (e.getSource() == exit) {
            System.exit(0);
            
        } else if (e.getSource() == deposit) {
        		animation.fadeOutAndOpen(this, () -> new Deposit(accountNumber));

        } else if (e.getSource() == withdraw) {
        		animation.fadeOutAndOpen(this, () -> new Withdrawl(accountNumber));	
            
        } else if (e.getSource() == fastCash) {
        		animation.fadeOutAndOpen(this, () -> new FastCash(accountNumber));

        } else if (e.getSource() == pinChange) {
        		animation.fadeOutAndOpen(this, () -> new PinChnage(accountNumber));

        } else if (e.getSource() == balanceEnquiry) {
        		animation.fadeOutAndOpen(this, () -> new BalanceEnquiry(accountNumber));
	        	
        } else if (e.getSource() == miniStatement) {
        		new MiniStmnt(accountNumber);
        }
    }
    
}
