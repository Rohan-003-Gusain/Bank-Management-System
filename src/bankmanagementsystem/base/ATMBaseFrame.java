package bankmanagementsystem.base;

import java.awt.Image;
import javax.swing.*;

public abstract class ATMBaseFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	protected Animation animation = new Animation();
    protected static ImageIcon ATM_BG;
    protected JLabel bgLabel;

    static {
        java.net.URL url = ATMBaseFrame.class.getResource("/bankmanagementsystem/images/atm.jpg");
        if (url == null) {
        	throw new RuntimeException("ATM background image not found");
        }
        ImageIcon i1 = new ImageIcon(url);
        Image i2 = i1.getImage()
                     .getScaledInstance(900, 900, Image.SCALE_SMOOTH);
        ATM_BG = new ImageIcon(i2);
    }

    public ATMBaseFrame() {
    		setUndecorated(true);
        setLayout(null);
        setSize(900, 900);
        setLocationRelativeTo(null);

        bgLabel = new JLabel(ATM_BG);
        bgLabel.setBounds(0, 0, 900, 900);
        add(bgLabel);
    }
}