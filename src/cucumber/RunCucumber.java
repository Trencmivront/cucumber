package cucumber;

import javax.swing.*;

public class RunCucumber {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					
					new CucumberGUI().setVisible(true);
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
	}

}
