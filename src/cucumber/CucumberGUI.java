package cucumber;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.*;

public class CucumberGUI extends JFrame {
	
	private JFileChooser fileChooser = new JFileChooser();
	private File selectedFile;
	private ProcessBuilder app;

	public int t = 0;
	
	CucumberGUI(){
		
		setSize(300, 400);
		setLocationRelativeTo(null);
		
		// to place components where we desire
		setLayout(null);
		
		// scared to change component sizes after resizing window
		setResizable(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTitle("Welcome " + System.getProperty("user.name"));
		
		try {
			Image cucumber = Toolkit.getDefaultToolkit().getImage("/img/cucumber.jpg");
			setIconImage(cucumber);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		addComponents();
	}
	
	public void addComponents() {
		
		addTimeChoices();
		
		addFileChooser();
		
		addStartButton();
		
	};
	
	public void addTimeChoices() {
		
		// creating menu bar which will contain our time items
		JMenuBar menuBar = new JMenuBar();
		
		// placing menu where I desire
		menuBar.setBounds(120, 100, 60, 30);
		
		JMenu menu = new JMenu("Time Min");
		
		// Array of times we include
		int[] times = {15, 30, 45, 60, 75, 90, 105, 120};
		
		// this block will create menu items and give them their jobs
		for(int i = 0; i < 8;i++) {
			
			JMenuItem menuItem = new JMenuItem("  " + times[i] + "  ");
			
			// program is giving me an error about i must be final or something
			// maybe because of we increase i everytime and it is a value of for block
			// but n here is a value we create in process
			// I don't know the real answer to this
			int n = i;
			
			menuItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					menu.setText(Integer.toString(times[n]));
					
					// time should be what written on selected box
					t = times[n];
					
				}
			});
			
			menu.add(menuItem);
		}
		
		menuBar.add(menu);
		
		add(menuBar);
		
	};
	
	public void addFileChooser() {
		
		JButton choose = new JButton(".exe file");
		
		choose.setBounds(50, 160, 200, 30);
		
		// we will create empty label which later will contain our file name
		// to show user which file was he/she selected
		JLabel fileName = new JLabel("");
		fileName.setBounds(80, 200, 300, 20);
		
		choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent a) {
				// showing file chooser, parent class as this class
				// result here is Decline by default
				// which is a numerical value
				int result = fileChooser.showDialog(CucumberGUI.this , "Please select the application");
				
				// canceling event if user didn't push ok button
				if(result != fileChooser.APPROVE_OPTION) return;
				
				try {
					
					// get selected file
					selectedFile = fileChooser.getSelectedFile();
					
					//check if file having correct extension
					if(!selectedFile.getName().contains(".exe")) {
						// it shows a message
						// what I say error message
						JOptionPane.showMessageDialog(fileChooser, "File doesn't contain .exe extension!");
						return;
					};
					
					// JLabel text will set to our file name
					fileName.setText(selectedFile.getName());
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		add(choose);
		add(fileName);
		
	};
	
	public void addStartButton() {
		
		JButton start = new JButton("Start");
		
		start.setBounds(100, 300, 100, 30);
 		
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					// after selecting file we create our processBuilder at here
					// it will use file path as command parameter
					app = new ProcessBuilder(selectedFile.getPath());
					
					// trying to make it work on different OS's
					// later
					// maybe it is working already who knows?
					// idk...
					String osName = System.getProperty("os.name");
					
					// starting our application as process
					// process here is like a active element
					// it exists as application exits
					Process process = app.start();
					
					// as we start application we should hide our timer so user won't close it by accident
					// nah I just wanted to make it look cool
					setVisible(false);
					
					try {
						
						Timer timer = new Timer();
						
						timer.schedule(new TimerTask() {
							
							@Override
							public void run() {
								// when the timer ends application will be closed
								process.destroy();
								
							}
							// the time is specified as miliseconds
							// so, we should multiply our selected time
							// with 60 * 1000 which means 1 minute
						}, t * 1000 * 60);
						
						// here what I'm doing is checking if process is still running
						// if not, we should show our java application right?
						// otherwise it would stood in the background 
						// until you close the computer
						// and using 209 MegaBytes of ram
						// not the best ram usage
						timer.scheduleAtFixedRate(new TimerTask() {
							
							@Override
							public void run() {
								if(!process.isAlive()) {
									setVisible(true);
									// don't forget to exit from repeated action
									// or it will go on a loop
									return;
								}
									
							}
							// it starts after 1 second
							// repeats every 2 second
							// enough for it
						}, 1000, 2000);
						
					}catch(Exception a) {
						a.printStackTrace();
					}
					
				}catch(IOException a) {
					a.printStackTrace();
				}
				
			}
		});
		
		add(start);
		
	}

}
