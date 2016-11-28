package project.UI.pkg;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import project.backend.pkg.*;



public class HummingBirdUI {

	private JFrame frame;
	private JPanel statisticsPanel, tempPanel;
	private JRadioButton btnByMonth, btnByGroup;
	private JLabel dayDate, lblondmd, lblTime, lblcurrTemp, lblcurrTemp2, min, minText, max, maxText;
	private JButton btnAddSchedule, btnShowSchedule, minMinus, minPlus, maxMinus, maxPlus;	
	private ButtonGroup group;
	private JCheckBox n, s, e, w;
	
	private Calendar calendar;
	private GridBagConstraints gbc, gbc2;
	private Clock clock;
	private AddScheduleHandler addScheduleHandler;
	private ShowScheduleHandler showScheduleHandler;
	private BtnByGroup byGroup;
	private BtnByMonth byMonth;
	
	private Temperature temperature;
	private ScheduleBank sb;
	private SprinklerPanel sprinklerPanel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HummingBirdUI window = new HummingBirdUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
	}}});}
	
	public HummingBirdUI() throws ParseException, MalformedURLException {
		temperature = new Temperature();
		sb = new ScheduleBank();
		sprinklerPanel = new SprinklerPanel();
		
		frame = new JFrame("HummingBee Home Garden Sprinkler System");
		frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//Initialize dummy components
		initializeMapPanel();
		initializeStatisticsPanel();
		
		//Start functionality
		sb.loadSchedules();   		//Queries the DB and brings all of todays schedules to front end
		sb.sort();
		sb.getScheduledGroupList();
		sb.moveIndexToCurrent();
		
		sb.addObserver(sprinklerPanel);
		sb.startNotifying();
	}

	public void initializeMapPanel() throws MalformedURLException {
		JPanel bgPanel = new BgPanel();
        bgPanel.setLayout(new BorderLayout());	
        bgPanel.add(sprinklerPanel, BorderLayout.CENTER);
		frame.add(bgPanel, BorderLayout.CENTER);
	}
	
	public void initializeStatisticsPanel(){
		statisticsPanel = new JPanel();
		statisticsPanel.setPreferredSize(new Dimension(300,1000));
		statisticsPanel.setLayout(new GridBagLayout());
		statisticsPanel.setBackground(new Color(221, 160, 221));
		frame.getContentPane().add(statisticsPanel, BorderLayout.EAST);
				
		gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
       
        initializeTimingSection();
        initializeOnDemandSchedulingSection();		//TODO		//Add action listeners
		gbc.insets = new Insets(30,0,0,0);
		gbc.gridx--;
		initializeScheduleSection();
		initializeTemperatureSection();		
		initializeWaterSection();
	}
	
	private void initializeTimingSection(){
		calendar = Calendar.getInstance();
		dayDate = new JLabel(calendar.getTime().toString().substring(0,10));
		//dayDate.setFont(new Font("Pristina", Font.BOLD, 25));
		statisticsPanel.add(dayDate, gbc);
			
		//Time label
		lblTime = new JLabel("Time:  ");
		//lblTime.setFont(new Font("Pristina", Font.BOLD, 25));
		//gbc.gridx--;
		gbc.gridy++;
		statisticsPanel.add(lblTime, gbc);
					
		clock = new Clock();
		clock.start();
		gbc.gridx++;
		statisticsPanel.add(clock.time, gbc);
		
		//Current Temperature
		lblcurrTemp = new JLabel("Curr. Temperature: ");
		gbc.gridx--;
		gbc.gridy++;
		//lblcurrTemp.setFont(new Font("Pristina", Font.BOLD, 25));
		statisticsPanel.add(lblcurrTemp, gbc);
		
		lblcurrTemp2 = new JLabel(String.valueOf(temperature.getCurrTemperature())+ " F");
		gbc.gridx++;
		//lblcurrTemp2.setFont(new Font("Pristina", Font.BOLD, 25));
		statisticsPanel.add(lblcurrTemp2, gbc);
	}
	
	private void initializeOnDemandSchedulingSection(){
		lblondmd = new JLabel("On demand schedule");
		//lblondmd.setFont(new Font("Pristina", Font.BOLD, 25));
		n = new JCheckBox("North");
		//n.setFont(new Font("Pristina", Font.BOLD, 25));
		n.setOpaque(false);
		s = new JCheckBox("South");
		//s.setFont(new Font("Pristina", Font.BOLD, 25));
		s.setOpaque(false);
		e = new JCheckBox("East");
		//e.setFont(new Font("Pristina", Font.BOLD, 25));
		e.setOpaque(false);
		w = new JCheckBox("West");
		//w.setFont(new Font("Pristina", Font.BOLD, 25));
		w.setOpaque(false);
		
		NorthOnDemand north = new NorthOnDemand();
		n.addActionListener(north);
		SouthOnDemand south = new SouthOnDemand();
		s.addActionListener(south);
		EastOnDemand east = new EastOnDemand();
		e.addActionListener(east);
		WestOnDemand west = new WestOnDemand();
		w.addActionListener(west);
		
		gbc.insets = new Insets(10,0,0,0);
		
		gbc.gridx--;
		gbc.gridy++;
		statisticsPanel.add(lblondmd, gbc);
		gbc.gridy++;
		statisticsPanel.add(n, gbc);
		gbc.gridx++;
		statisticsPanel.add(e, gbc);
		gbc.gridx--;
		gbc.gridy++;
		statisticsPanel.add(w, gbc);
		gbc.gridx++;
		statisticsPanel.add(s, gbc);
	}
	
	private class NorthOnDemand implements ActionListener{
		String startTime = ScheduleBank.getCurrentTimeStamp();
		
		public void actionPerformed(ActionEvent e) {
			if (n.isSelected()==true){
				
				try {sprinklerPanel.turnOnNorthPanel();} 
				catch (MalformedURLException e1) {e1.printStackTrace();}
			}
			if (n.isSelected()==false){
				
				sprinklerPanel.turnOffNorthPanel();
			}
		}
	}
	
	private class SouthOnDemand implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (s.isSelected()==true){
				try {sprinklerPanel.turnOnSouthPanel();} 
				catch (MalformedURLException e1) {e1.printStackTrace();}
			}
			if (s.isSelected()==false){
				sprinklerPanel.turnOffSouthPanel();
			}
		}
	}
	
	private class EastOnDemand implements ActionListener{
		public void actionPerformed(ActionEvent e2) {
			if (e.isSelected()==true){
				try {sprinklerPanel.turnOnEastPanel();} 
				catch (MalformedURLException e1) {e1.printStackTrace();}
			}
			if (e.isSelected()==false){
				sprinklerPanel.turnOffEastPanel();
			}
		}
	}
	
	private class WestOnDemand implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (w.isSelected()==true){
				try {sprinklerPanel.turnOnWestPanel();} 
				catch (MalformedURLException e1) {e1.printStackTrace();}
			}
			if (w.isSelected()==false){
				sprinklerPanel.turnOffWestPanel();
			}
		}
	}
	
	private void initializeScheduleSection(){
		//Add schedule
		btnAddSchedule = new JButton("Add Schedule");
		gbc.gridx--;
		gbc.gridy++;
		//btnAddSchedule.setFont(new Font("Pristina", Font.BOLD, 25));
		statisticsPanel.add(btnAddSchedule, gbc);
		addScheduleHandler = new AddScheduleHandler();
		btnAddSchedule.addActionListener(addScheduleHandler);

		//Show schedule
		btnShowSchedule = new JButton("Show Schedule");
		gbc.gridy++;
		//btnShowSchedule.setFont(new Font("Pristina", Font.BOLD, 25));
		statisticsPanel.add(btnShowSchedule, gbc);
		showScheduleHandler = new ShowScheduleHandler();
		btnShowSchedule.addActionListener(showScheduleHandler);

	}

	private void initializeWaterSection(){
		//Show water consumption
		JLabel lblWaterConsumption = new JLabel("Water usage");
		//JButton btnWaterConsumption = new JButton("Water consumption");
		gbc.gridy++;
		//lblWaterConsumption.setFont(new Font("Pristina", Font.BOLD, 25));
		statisticsPanel.add(lblWaterConsumption, gbc);		
				
		//TODO
		btnByGroup = new JRadioButton("Show by group");
		//btnByGroup.setFont(new Font("Pristina", Font.BOLD, 25));
		btnByGroup.setOpaque(false);
		btnByMonth = new JRadioButton("Show by month");
		//btnByMonth.setFont(new Font("Pristina", Font.BOLD, 25));
		btnByMonth.setOpaque(false);
		group = new ButtonGroup();
		group.add(btnByMonth);
		group.add(btnByGroup);
		gbc.gridy++;
		statisticsPanel.add(btnByGroup, gbc);		
		gbc.gridy++;
		statisticsPanel.add(btnByMonth, gbc);		
		byGroup = new BtnByGroup();
		btnByGroup.addActionListener(byGroup);
		byMonth = new BtnByMonth();
		btnByMonth.addActionListener(byMonth);			
	}
	
	private void initializeTemperatureSection(){
		tempPanel = new JPanel(new GridBagLayout());
		tempPanel.setBorder(new TitledBorder("Temperature settings"));
		tempPanel.setBackground(new Color(221, 160, 221));
		gbc2 = new GridBagConstraints();
		min = new JLabel("Min: ");
		//min.setFont(new Font("Pristina", Font.BOLD, 25));
		minText = new JLabel(" " + String.valueOf(temperature.getMin()) + " ");
		//minText.setFont(new Font("Pristina", Font.BOLD, 25));
		minMinus = new JButton(" - ");
		minPlus = new JButton(" + ");
		max = new JLabel("Max: ");
		//max.setFont(new Font("Pristina", Font.BOLD, 25));
		maxText = new JLabel(" " + String.valueOf(temperature.getMax())+ " ");
		//maxText.setFont(new Font("Pristina", Font.BOLD, 25));
		maxMinus = new JButton(" - ");
		maxPlus = new JButton(" + ");
		
		MinMinus minm = new MinMinus();
		minMinus.addActionListener(minm);
		
		MinPlus minp = new MinPlus();
		minPlus.addActionListener(minp);
		
		MaxMinus maxm = new MaxMinus();
		maxMinus.addActionListener(maxm);
		
		MaxPlus maxp = new MaxPlus();
		maxPlus.addActionListener(maxp);
		
		gbc2.gridx = 0;  
		gbc2.gridy = 0;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
		tempPanel.add(min, gbc2);
		gbc2.gridx++;		tempPanel.add(minText, gbc2);
		gbc2.gridx++;		tempPanel.add(minMinus, gbc2);
		gbc2.gridx++;		tempPanel.add(minPlus, gbc2);
		gbc2.gridx--;		gbc2.gridx--;		gbc2.gridx--;
		
		gbc2.gridy++;		tempPanel.add(max, gbc2);
		gbc2.gridx++;		tempPanel.add(maxText, gbc2);
		gbc2.gridx++;		tempPanel.add(maxMinus, gbc2);
		gbc2.gridx++;		tempPanel.add(maxPlus, gbc2);
		
		gbc.gridy++;
		statisticsPanel.add(tempPanel, gbc);
	}
	
	private class BtnByGroup implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//TODO
			JOptionPane.showMessageDialog(null, "BYGRP");
			group.clearSelection();
		}		
	}
	
	private class BtnByMonth implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//TODO
			JOptionPane.showMessageDialog(null, "BYMONTH");
			group.clearSelection();
		}
	}
	
	private class MinMinus implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			temperature.setMin(temperature.getMin()-1);
			minText.setText(String.valueOf(temperature.getMin()));
		}

	}

	private class MinPlus implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			temperature.setMin(temperature.getMin()+1);
			minText.setText(String.valueOf(temperature.getMin()));
		}

	}
	
	private class MaxMinus implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			temperature.setMax(temperature.getMax()-1);
			maxText.setText(String.valueOf(temperature.getMax()));
		}

	}
	
	private class MaxPlus implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			temperature.setMax(temperature.getMax()+1);
			maxText.setText(String.valueOf(temperature.getMax()));
		}
	}
	
	private class AddScheduleHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ConfigureSchedule cs = new ConfigureSchedule();
			cs.frame.setVisible(true);
			System.out.println("loaded schedulesssssss");
			sb.loadSchedules();   		//Queries the DB and brings all of todays schedules to front end
			sb.sort();
			sb.getScheduledGroupList();
			try {
				sb.moveIndexToCurrent();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sb.startNotifying();
		
		}
	}
	
	private class ShowScheduleHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//TODO
			//basically need to display table from DB
			//get show results from yelp gui
		}
	}
	
	private class BgPanel extends JPanel {
	    Image bg = new ImageIcon("./images/gardenCopy3.jpg").getImage();
	    @Override
	    public void paintComponent(Graphics g) {
	        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	    }
	}
	
	public BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	private JLabel setDisplayPic(String filename, int width , int height){
		BufferedImage displayPic;
		JLabel labelHint = null ;
		try {
				displayPic = ImageIO.read(new File(filename));
				displayPic = resize(displayPic,width,height);
				labelHint = new JLabel(new ImageIcon(displayPic));
			//	labelHint.setMaximumSize(new Dimension(100, 100)); 
				return labelHint;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return labelHint;
	}
}
