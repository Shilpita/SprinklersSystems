package project.UI.pkg;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SprinklerPanel extends JPanel implements Observer {

	private JPanel north;
	private JLabel N1, N2, N3, N4;

	private JPanel south;
	private JLabel S1, S2, S3, S4;

	private JPanel east;
	private JLabel E1, E2, E3, E4;

	private JPanel west;
	private JLabel W1, W2, W3, W4;

	private URL fountainGIFURL;
	private Icon fountainGIFIcon;

	public SprinklerPanel() throws MalformedURLException{		
		fountainGIFURL = new URL("http://vignette3.wikia.nocookie.net/adventure-town/images/0/0f/Small-fountain.gif/revision/latest?cb=20150615175113");
		fountainGIFIcon = new ImageIcon(fountainGIFURL);

		this.setLayout(new GridLayout(2,2));

		north = new JPanel(null);
		south = new JPanel(null);
		east = new JPanel(null);
		west = new JPanel(null);

		turnOffNorthPanel();
		turnOffEastPanel();
		turnOffWestPanel();	
		turnOffSouthPanel();

		this.add(north);
		this.add(east);
		this.add(west);
		this.add(south);
		this.setOpaque(false);
	}

	public void turnOffNorthPanel() {
		north.removeAll();

		N1 = setDisplayPic("./images/sprinkler.png", 50, 50);		N1.setSize(50, 50);		N1.setLocation(180, 135);
		N2 = setDisplayPic("./images/sprinkler.png", 50, 50);  		N2.setSize(50, 50);		N2.setLocation(320, 135);	
		N3 = setDisplayPic("./images/sprinkler.png", 50, 50);		N3.setSize(50, 50);		N3.setLocation(180, 300);
		N4 = setDisplayPic("./images/sprinkler.png", 50, 50);		N4.setSize(50, 50);		N4.setLocation(320, 300);

		north.add(N1);		
		north.add(N2);		
		north.add(N3);		
		north.add(N4);		

		north.setOpaque(false);
		//north.setBackground(Color.black);
		this.revalidate();
		this.repaint();
	}

	public void turnOnNorthPanel() throws MalformedURLException{
		north.removeAll();

		N1 = new JLabel(fountainGIFIcon);		N1.setSize(50, 50);			N1.setLocation(180, 135);
		N2 = new JLabel(fountainGIFIcon);	 	N2.setSize(50, 50);			N2.setLocation(320, 135);	
		N3 = new JLabel(fountainGIFIcon);		N3.setSize(50, 50);			N3.setLocation(180, 300);
		N4 = new JLabel(fountainGIFIcon);		N4.setSize(50, 50);			N4.setLocation(320, 300);

		north.add(N1);		north.add(N2);		north.add(N3);		north.add(N4);	

		north.setOpaque(false);
		this.revalidate();
		this.repaint();
	}

	public void turnOffSouthPanel() {
		south.removeAll();

		S1 = setDisplayPic("./images/sprinkler.png", 50, 50);		S1.setSize(50, 50);		S1.setLocation(210, 50);
		S2 = setDisplayPic("./images/sprinkler.png", 50, 50);		S2.setSize(50, 50);		S2.setLocation(390, 50);
		S3 = setDisplayPic("./images/sprinkler.png", 50, 50);		S3.setSize(50, 50);		S3.setLocation(210, 220);
		S4 = setDisplayPic("./images/sprinkler.png", 50, 50);		S4.setSize(50, 50);		S4.setLocation(390, 220);

		south.add(S1);		south.add(S2);		south.add(S3);		south.add(S4);

		south.setOpaque(false);
		this.revalidate();
		this.repaint();
	}

	public void turnOnSouthPanel() throws MalformedURLException{
		south.removeAll();

		S1 = new JLabel(fountainGIFIcon);		S1.setSize(50, 50);		S1.setLocation(210, 50);
		S2 = new JLabel(fountainGIFIcon);	 	S2.setSize(50, 50);		S2.setLocation(390, 50);	
		S3 = new JLabel(fountainGIFIcon);		S3.setSize(50, 50);		S3.setLocation(210, 220);
		S4 = new JLabel(fountainGIFIcon);		S4.setSize(50, 50);		S4.setLocation(390, 220);

		south.add(S1);		south.add(S2);		south.add(S3);		south.add(S4);	

		south.setOpaque(false);
		this.revalidate();
		this.repaint();
	}

	public void turnOffEastPanel() {
		east.removeAll();

		E1 = setDisplayPic("./images/sprinkler.png", 50, 50);		E1.setSize(50, 50);		E1.setLocation(220, 130);
		E2 = setDisplayPic("./images/sprinkler.png", 50, 50);		E2.setSize(50, 50);		E2.setLocation(380, 130);
		E3 = setDisplayPic("./images/sprinkler.png", 50, 50);		E3.setSize(50, 50);		E3.setLocation(220, 300);
		E4 = setDisplayPic("./images/sprinkler.png", 50, 50);		E4.setSize(50, 50);		E4.setLocation(380, 300);

		east.add(E1);		east.add(E2);		east.add(E3);		east.add(E4);		

		east.setOpaque(false);
		this.revalidate();
		this.repaint();
	}

	public void turnOnEastPanel() throws MalformedURLException{
		east.removeAll();

		E1 = new JLabel(fountainGIFIcon);		E1.setSize(50, 50);		E1.setLocation(220, 130);
		E2 = new JLabel(fountainGIFIcon);	 	E2.setSize(50, 50);		E2.setLocation(380, 130);	
		E3 = new JLabel(fountainGIFIcon);		E3.setSize(50, 50);		E3.setLocation(220, 300);
		E4 = new JLabel(fountainGIFIcon);		E4.setSize(50, 50);		E4.setLocation(380, 300);

		east.add(E1);		east.add(E2);		east.add(E3);		east.add(E4);	

		east.setOpaque(false);
		this.revalidate();
		this.repaint();
	}

	public void turnOffWestPanel() {
		west.removeAll();

		W1 = setDisplayPic("./images/sprinkler.png", 50, 50);		W1.setSize(50, 50);		W1.setLocation(170, 40);
		W2 = setDisplayPic("./images/sprinkler.png", 50, 50);		W2.setSize(50, 50);		W2.setLocation(325, 40);
		W3 = setDisplayPic("./images/sprinkler.png", 50, 50);		W3.setSize(50, 50);		W3.setLocation(170, 220);
		W4 = setDisplayPic("./images/sprinkler.png", 50, 50);		W4.setSize(50, 50);		W4.setLocation(325, 220);

		west.add(W1);		west.add(W2);		west.add(W3);		west.add(W4);

		west.setOpaque(false);
		this.revalidate();
		this.repaint();
	}

	public void turnOnWestPanel() throws MalformedURLException{
		west.removeAll();

		W1 = new JLabel(fountainGIFIcon);		W1.setSize(50, 50);		W1.setLocation(170, 40);
		W2 = new JLabel(fountainGIFIcon);	 	W2.setSize(50, 50);		W2.setLocation(325, 40);	
		W3 = new JLabel(fountainGIFIcon);		W3.setSize(50, 50);		W3.setLocation(170, 220);
		W4 = new JLabel(fountainGIFIcon);		W4.setSize(50, 50);		W4.setLocation(325, 220);

		west.add(W1);		west.add(W2);		west.add(W3);		west.add(W4);	

		west.setOpaque(false);
		this.revalidate();
		this.repaint();
	}

	@Override
	public void update(Observable o, Object arg) {	
		String message = (String) arg;
		/*System.out.println(message);
		//Update based on temperature
		if (message.equalsIgnoreCase("TOO HOT") || message.equalsIgnoreCase("TOO COLD")){
			if (message.equalsIgnoreCase("TOO HOT")){
				try {
					turnOnEastPanel();
					turnOnNorthPanel();
					turnOnSouthPanel();
					turnOnWestPanel();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			if (message.equalsIgnoreCase("TOO COLD")){
				turnOffEastPanel();
				turnOffNorthPanel();
				turnOffSouthPanel();
				turnOffWestPanel();
			}
		}*/
		//Update based on schedule
		//else {
			String message2[] = message.split(":");
			String groups[] = message2[1].split(" ");
			if (message2[0].equalsIgnoreCase("ON")){
				System.out.println("got notification to turn on");
				for (String each: groups){
					if (each.equalsIgnoreCase("North"))
						try {turnOnNorthPanel();
						System.out.println("in north");} 
						catch (MalformedURLException e3) {e3.printStackTrace();}
					if (each.equalsIgnoreCase("South"))
						try {turnOnSouthPanel();
						System.out.println("in south");} 
						catch (MalformedURLException e2) {e2.printStackTrace();}
					if (each.equalsIgnoreCase("East"))
						try {turnOnEastPanel();
						System.out.println("in east");} 
						catch (MalformedURLException e1) {e1.printStackTrace();}
					if (each.equalsIgnoreCase("West"))
						try {turnOnWestPanel();
						System.out.println("in west");} 
						catch (MalformedURLException e) {e.printStackTrace();}
				}
			this.revalidate();
			this.repaint();
			}
			if (message2[0].equalsIgnoreCase("OFF")){
				System.out.println("got notification to turn off");
				for (String each: groups){
					if (each.equalsIgnoreCase("North")) turnOffNorthPanel(); 
					if (each.equalsIgnoreCase("South"))	turnOffSouthPanel();
					if (each.equalsIgnoreCase("East")) turnOffEastPanel();
					if (each.equalsIgnoreCase("West"))	turnOffWestPanel();
				}
				this.revalidate();
				this.repaint();
			}
		//}
	}

/*	public static void main(String[] args) throws MalformedURLException{
		SprinklerPanel s = new SprinklerPanel();
		JFrame f = new JFrame();
		//s.turnOnWestPanel();

		f.getContentPane().add(s, BorderLayout.CENTER);
		f.setExtendedState(f.getExtendedState()|JFrame.MAXIMIZED_BOTH );		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}*/

	private JLabel setDisplayPic(String filename, int width, int height){
		BufferedImage displayPic;
		JLabel labelHint = null ;
		try {
			displayPic = ImageIO.read(new File(filename));
			displayPic = resize(displayPic, width, height);
			labelHint = new JLabel(new ImageIcon(displayPic));
			return labelHint;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return labelHint;
	}

	public BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

}
