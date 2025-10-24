package cucumber;

import javax.swing.*;

public class RunCucumber {
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					
					new CucumberGUI().setVisible(true);
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
	}

}
