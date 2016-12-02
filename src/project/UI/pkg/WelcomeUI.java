package project.UI.pkg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeUI window = new WelcomeUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Go Green");
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel welcome = new JPanel();
		welcome.add(setAnimation("http://grow-our-own.co.uk/wp-content/uploads/2013/04/Watering_Can.gif", 500, 500));      //setDisplayPic("./images/succulent.jpg",500,500));
		welcome.setBackground(Color.WHITE);
		JPanel enterSystem = new JPanel();
		enterSystem.setBackground(Color.WHITE);
		JButton waterCan = new JButton("Welcome To HummingBee Sprinkler System");
		SystemButtonHandler handler = new SystemButtonHandler();
		waterCan.addActionListener(handler);
		//waterCan.setBackground(new Color(234,255,130));
		enterSystem.add(waterCan);
		frame.getContentPane().add(welcome, BorderLayout.CENTER);
		frame.getContentPane().add(enterSystem, BorderLayout.SOUTH);
		//frame.pack();
		//frame.setVisible(true);
	}

	/**
	 * This method reads the url given in parameter and sets it to a label
	 * @param urlname
	 * @param width
	 * @param height
	 * @exception MalformedURLException for the parameter url
	 * @return JLabel with the animated gif
	 */
	
	private JLabel setAnimation(String urlname , int width, int height){
				URL url;
				JLabel label =new JLabel();
				try {
					url = new URL(urlname);
					Icon icon = new ImageIcon(url);
			        label = new JLabel(icon);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      return label;  
	}
	
	class SystemButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
				//public static void main(String[] args) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								HummingBirdUI window = new HummingBirdUI();
								window.frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
				}}});
				frame.dispose();	
		}
		
	}

}
