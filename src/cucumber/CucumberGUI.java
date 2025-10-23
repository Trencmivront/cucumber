package cucumber;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class CucumberGUI extends JFrame {
	
	private JFileChooser fileChooser = new JFileChooser();
	
	public boolean isFileSelected = false;
	
	File selectedFile;
	int t = 0;
	
	CucumberGUI(){
		
		setSize(300, 400);
		setLocationRelativeTo(null);
		
		setLayout(null);
		
		setResizable(false);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		addComponents();
	}
	
	public void addComponents() {
		
		addTimeChoices();
		
		addFileChooser();
		
		addStartButton();
		
	};
	
	public void addTimeChoices() {
		
		JMenuBar menuBar = new JMenuBar();
		
		menuBar.setBounds(120, 100, 60, 30);
		
		JMenu menu = new JMenu("Süre DK");
		
		// Array of times we include
		int[] times = {15, 30, 45, 60, 75, 90, 105, 120};
		
		for(int i = 0; i < 8;i++) {
			
			JMenuItem menuItem = new JMenuItem("  " + times[i] + "  ");
			
			int n = i;
			
			menuItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					menu.setText(Integer.toString(times[n]));
					
					t = times[n];
					
				}
			});
			
			menu.add(menuItem);
		}
		
		menuBar.add(menu);
		
		add(menuBar);
		
	};
	
	public void addFileChooser() {
		
		JButton choose = new JButton("Exe Dosyası");
		
		choose.setBounds(50, 160, 200, 30);
		
		JLabel fileName = new JLabel("");
		fileName.setBounds(80, 200, 300, 20);
		
		choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent a) {
				int result = fileChooser.showDialog(CucumberGUI.this , "Uygulamanın .exe dosyasını seçiniz");
				
				isFileSelected = false;
				
				// canceling event if user didn't push ok button
				if(result != fileChooser.APPROVE_OPTION) return;
				
				try {
					
					selectedFile = fileChooser.getSelectedFile();
					
					//check if file having correct extend
					if(!selectedFile.getName().contains(".exe")) {
						JOptionPane.showMessageDialog(fileChooser, "Dosya .exe uzantılı değil!");
						return;
					};
					
					isFileSelected = true;
					
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
		
		JButton start = new JButton("Başlat");
		
		start.setBounds(100, 300, 100, 30);
 		
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					Desktop desktop = Desktop.getDesktop();
					desktop.open(selectedFile);
					
					try {
						
						Timer timer = new Timer();
						
						timer.schedule(new TimerTask() {
							
							@Override
							public void run() {
								
								J
								System.exit(0);
								
							}
						}, t * 100);
						
						
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
