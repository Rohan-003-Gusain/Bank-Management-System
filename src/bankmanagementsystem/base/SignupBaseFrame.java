package bankmanagementsystem.base;

import java.awt.Color;
import javax.swing.JFrame;

public abstract class SignupBaseFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	protected Animation animation = new Animation();
	
	protected SignupBaseFrame() {
		setUndecorated(true);
		setLayout(null);
		setSize(850, 800);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.white);
	}
}
