package project.UI.pkg;

import java.awt.EventQueue;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;

public class ConfigureSchedule {

	private JFrame frame;
	private JPanel  panel ,panelSchedule, panelTimeDay,panelWaterFlow;
	private JRadioButton groupRdBtn ,sprinklerRdBtn ,selectedSprinklerRdBtn ;
	private JRadioButton low , medium, high;
	private ButtonGroup sprinklerSelectionGrp,waterFlowGrp;
	private JTextField scheduleNameField, startDateField,endDateField, startHrField,endHrField, startMinField,endMinField;
	private JTextField sprinklerInput ,scheduleInput ;
	private JLabel scheduleID, sprinklerID , startDateLabel,endDateLabel ,startHrLabel ,endHrLabel,startMinLabel ,endMinLabel ;
	private JButton submit;
	
	//data member
	private String scheduleName, startDate,endDate , sprinkler , group , waterflow ;
	private String startHrTime,startMinTime, endHrTime,endMinTime ;
	private ArrayList<String> sprinklerList;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigureSchedule window = new ConfigureSchedule();
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
	public ConfigureSchedule() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Configuration");
		frame.setBounds(100, 100, 450, 300);
		
		initializationSchedulePanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
        frame.pack();
	}
	
	/**
	 * Initialize panel for entering the schedule for 
	 */
	
	private void initializationSchedulePanel(){
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder("Sprinkler Setting"));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		panelSchedule = new JPanel();
		panelSchedule.setLayout(new BoxLayout(panelSchedule, BoxLayout.PAGE_AXIS));
		panelSchedule.setBorder(new TitledBorder("Select Sprinkler"));
		
		JPanel panel1 = new JPanel();
		scheduleID = new JLabel("Enter ScheduleName :");
		panel1.add(scheduleID);
		scheduleInput = new JTextField(10);
		panel1.add(scheduleInput);
		panelSchedule.add(panel1);
		
		JPanel panel2 = new JPanel();
		groupRdBtn = new JRadioButton("Group");
		sprinklerRdBtn = new JRadioButton("Sprinkler"); 
		selectedSprinklerRdBtn =new JRadioButton("Multiple Sprinklers");
		
		sprinklerSelectionGrp = new ButtonGroup();
		sprinklerSelectionGrp.add(groupRdBtn);
		sprinklerSelectionGrp.add(sprinklerRdBtn);
		sprinklerSelectionGrp.add(selectedSprinklerRdBtn);
		
		panel2.add(groupRdBtn);
		panel2.add(sprinklerRdBtn);
		panel2.add(selectedSprinklerRdBtn);
		
		sprinklerID = new JLabel("Enter SprinklerID(s)/Group :");
		panel2.add(sprinklerID);
		sprinklerInput = new JTextField(10);
		panel2.add(sprinklerInput);
		panelSchedule.add(panel2);
		
		panel.add(panelSchedule);
		
		panelTimeDay = new JPanel();
		panelTimeDay.setBorder(new TitledBorder("Day and Time setting"));
		startDateLabel = new JLabel("Enter Start Date :");
		startDateField= new JTextField(10); 
		endDateLabel = new JLabel("Enter End Date :");
		endDateField= new JTextField(10); 
		startHrLabel = new JLabel("Enter Start Hour :"); 
		startHrField= new JTextField(5);
		endHrLabel  = new JLabel("Enter End Hour :");
		endHrField = new JTextField(5);
		startMinLabel = new JLabel("Enter Start Min :"); 
		startMinField= new JTextField(5);
		endMinLabel  = new JLabel("Enter End Min :");
		endMinField = new JTextField(5);
		panelTimeDay.add(startDateLabel);
		panelTimeDay.add(startDateField);
		panelTimeDay.add(endDateLabel);
		panelTimeDay.add(endDateField);
		panelTimeDay.add(startHrLabel);
		panelTimeDay.add(startHrField);
		panelTimeDay.add(startMinLabel);
		panelTimeDay.add(startMinField);
		panelTimeDay.add(endHrLabel);
		panelTimeDay.add(endHrField);
		panelTimeDay.add(endMinLabel);
		panelTimeDay.add(endMinField);
		
		panel.add(panelTimeDay);
		
		panelWaterFlow = new JPanel();
		panelWaterFlow.setBorder(new TitledBorder("Waterflow setting"));
		low = new JRadioButton("Low Waterflow");
		medium = new JRadioButton("Medium Waterflow");
		high = new JRadioButton("High Waterflow");
		waterFlowGrp = new ButtonGroup();
		waterFlowGrp.add(low);
		waterFlowGrp.add(medium);
		waterFlowGrp.add(high);
		
		panelWaterFlow.add(low);
		panelWaterFlow.add(medium);
		panelWaterFlow.add(high);
		panel.add(panelWaterFlow);
		
		JPanel panel3 = new JPanel();
		submit = new JButton("Submit");
		panel3.add(submit);
		
		panel.add(panel3);
		
	}

}
