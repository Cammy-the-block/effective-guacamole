package jasonscode;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TestWindow extends JFrame {

	private JPanel contentPane;
	private static JProgressBar progressBar;
	private static JLabel lblPower;
	
	private static int powerDraws[] = {10, 50};
	private static boolean currentStatus[];
	private static ArrayList<JLabel> labels=new ArrayList<JLabel>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestWindow frame = new TestWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setOrientation(SwingConstants.VERTICAL);
		progressBar.setBounds(47, 25, 62, 283);
		progressBar.setBackground(Color.LIGHT_GRAY);
		progressBar.setForeground(Color.GREEN);
		progressBar.setValue(55);
		contentPane.add(progressBar);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1, 1);
		contentPane.add(desktopPane);
		
		lblPower = new JLabel("Power 33");
		lblPower.setBounds(29, 317, 80, 15);
		contentPane.add(lblPower);
		
		JSlider slider = new JSlider();
		slider.setBounds(159, 461, 200, 16);
		slider.addChangeListener(new SliderListener ());
		contentPane.add(slider);
		
		JPanel panel = new JPanel();
		panel.setBounds(253, 102, 434, 230);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 3, 0, 0));
		
		
		
		String objectNames[] = {"Lights", "Power Supply"};
		boolean initialStatus[] = {false, true};
		
		
		currentStatus = initialStatus;
		
		for (int x = 0; x < objectNames.length; x++){
			labels.add(new JLabel(objectNames[x]));
			System.out.println(x);
			panel.add(labels.get( x * 3 ));
			
			labels.add(new JLabel(Integer.toString(powerDraws[x])));
			panel.add(labels.get( (x) * 3 + 1 ));
			
			String currentPowerDraw = "0";
			if (initialStatus[x] == true){
				currentPowerDraw = Integer.toString(powerDraws[x]);
			}
			
			labels.add(new JLabel(currentPowerDraw));
			panel.add(labels.get( (x) * 3 + 2 ));
		}
		
		
		JLabel lblNewLabel = new JLabel("Total");
		int lightDraw = 10;
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("60");
		panel.add(lblNewLabel_2);
		
		
		JLabel lightStatus = new JLabel("50");
		panel.add(lightStatus);
		
		
		/*
		 
		
		JLabel lblNewLabel_1 = new JLabel("Life Support");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("20");
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("0");
		panel.add(lblNewLabel_5);
		
		JCheckBox chckbxLights = new JCheckBox("Lights");
		chckbxLights.setSelected(true);
		chckbxLights.setBounds(385, 481, 129, 23);
		contentPane.add(chckbxLights);
		
		*/
		JCheckBox chckbxLifeSupport = new JCheckBox("Life Support");
		chckbxLifeSupport.setBounds(520, 481, 129, 23);
		chckbxLifeSupport.addChangeListener(new CheckboxListener());
		contentPane.add(chckbxLifeSupport);
		
	}
	
	public static void setPower(int value){
		progressBar.setValue(value);
		lblPower.setText("Power: " + value);
	}
	
	public static void setStatus (int index, boolean isEnabled){
		currentStatus[index] = isEnabled;
		
		String currentPowerDraw = "0";
		if (isEnabled == true){
			currentPowerDraw = Integer.toString(powerDraws[index]);
		}
		System.out.println(labels.size());
		JLabel label = labels.get((index) * 3 + 2 );
		label.setText(currentPowerDraw);
	}
}

class SliderListener implements ChangeListener {
	  public void stateChanged(ChangeEvent changeEvent) {
	    Object source = changeEvent.getSource();
	     if (source instanceof JSlider) {
	      JSlider slider = (JSlider) source;
	      TestWindow.setPower(slider.getValue());
	      
	    }
	  }
}

class CheckboxListener implements ChangeListener {
	  public void stateChanged(ChangeEvent changeEvent) {
	    Object source = changeEvent.getSource();
	     if (source instanceof JCheckBox) {
	    	 JCheckBox checkbox = (JCheckBox) source;
	    	 TestWindow.setStatus(0, checkbox.isSelected());
	    	 
	    }
	  }
}
