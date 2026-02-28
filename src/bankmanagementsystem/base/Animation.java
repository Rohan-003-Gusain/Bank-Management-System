package bankmanagementsystem.base;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Animation {
	
	public void fadeIn(JFrame frame) {
		final int duration = 250;
		final long start = System.currentTimeMillis();
		
	    frame.setOpacity(0f);
	    frame.setVisible(true);
	
	    Timer timer = new Timer(20, null);
	    timer.addActionListener(e -> {
	        float progress = 
	        		(System.currentTimeMillis() - start) / (float) duration;
	        
	        if (progress < 1f) {
	        		frame.setOpacity(progress);
	        } else {
	        		frame.setOpacity(1f);
	        		timer.stop();
	        }
	    });
	    timer.start();
	}

	public void fadeOutAndOpen(JFrame frame, Runnable nextScreen) {
		final int duration = 250;
		final long start = System.currentTimeMillis();
			
	    Timer timer = new Timer(15, null);
	    timer.addActionListener(e -> {
		    	float progress = 
		        		(System.currentTimeMillis() - start) / (float) duration;
		    	
		    	if (progress < 1f) {
		    		frame.setOpacity(1f - progress);
		    } else {
		        	frame.setOpacity(0f);
		        timer.stop();
		        frame.dispose();
		        SwingUtilities.invokeLater(nextScreen);
		    }
		});
	    timer.start();
	}
}
