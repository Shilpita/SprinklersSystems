package project.UI.pkg;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import project.backend.pkg.*;
import project.db.pkg.*;
import java.awt.BorderLayout;

public class ConfigureSchedule {

	private JFrame frame;
	private JPanel  panel ,panelSchedule, panelTimeDay,panelWaterFlow;
	private JRadioButton groupRdBtn ,sprinklerRdBtn ,selectedSprinklerRdBtn ;
	private JRadioButton low , medium, high;
	private ButtonGroup sprinklerSelectionGrp,waterFlowGrp;
	private JTextField startDateField, endDateField, startHrField, endHrField, startMinField, endMinField;
	private JTextField sprinklerInput, scheduleInput;
	private JLabel scheduleID, sprinklerID , startDateLabel,endDateLabel ,startHrLabel ,endHrLabel,startMinLabel ,endMinLabel ;
	private JButton submit;
	private JCheckBox N, S, E, W;
	
	//data member
	private String scheduleName, startDate,endDate , sprinkler , group , waterflow ;
	private String startHrTime,startMinTime, endHrTime,endMinTime ;
	private ArrayList<String> sprinklerList;
	//db member
	
	private  ConnectToDB connectDBCon ;
	private  Connection con ;
	private QueryDB query;

	
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

	public ConfigureSchedule() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Schedule configuration");
		frame.setBounds(100, 100, 450, 300);
		
		initializationSchedulePanel();
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
        frame.pack();
	}
	
	private void initializationSchedulePanel(){
		
		panel = new JPanel();
		//panel.setBorder(new TitledBorder("Sprinkler Setting"));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		panelSchedule = new JPanel();
		panelSchedule.setLayout(new BoxLayout(panelSchedule, BoxLayout.PAGE_AXIS));
		panelSchedule.setBorder(new TitledBorder("Select Sprinkler"));
		
		JPanel panel1 = new JPanel();
		scheduleID = new JLabel("ScheduleName: ");
	//	scheduleID.setFont(new Font("Pristina", Font.BOLD, 25));
		panel1.add(scheduleID);
		scheduleInput = new JTextField(10);
	//	scheduleInput.setFont(new Font("Pristina", Font.BOLD, 25));
		panel1.add(scheduleInput);
		panelSchedule.add(panel1);
		
		JPanel panel2 = new JPanel();
		/*groupRdBtn = new JRadioButton("Group");
		groupRdBtn.setActionCommand("Group");
		groupRdBtn.setFont(new Font("Pristina", Font.BOLD, 25));

		sprinklerRdBtn = new JRadioButton("Sprinkler");
		sprinklerRdBtn.setActionCommand("Sprinkler");
		sprinklerRdBtn.setFont(new Font("Pristina", Font.BOLD, 25));

		selectedSprinklerRdBtn =new JRadioButton("Multiple Sprinklers");
		selectedSprinklerRdBtn.setActionCommand("Multiple Sprinklers");
		selectedSprinklerRdBtn.setFont(new Font("Pristina", Font.BOLD, 25));

		sprinklerSelectionGrp = new ButtonGroup();
		sprinklerSelectionGrp.add(groupRdBtn);
		sprinklerSelectionGrp.add(sprinklerRdBtn);
		sprinklerSelectionGrp.add(selectedSprinklerRdBtn);
		
		panel2.add(groupRdBtn);
		panel2.add(sprinklerRdBtn);
		panel2.add(selectedSprinklerRdBtn);*/
		
		sprinklerID = new JLabel("Choose sprinkler group(s)");
	//	sprinklerID.setFont(new Font("Pristina", Font.BOLD, 25));
		panel2.add(sprinklerID);
		
		N = new JCheckBox("N");
	//	N.setFont(new Font("Pristina", Font.BOLD, 25));
		E = new JCheckBox("E");
	//	E.setFont(new Font("Pristina", Font.BOLD, 25));
		W = new JCheckBox("W");
	//	W.setFont(new Font("Pristina", Font.BOLD, 25));
		S = new JCheckBox("S");
	//	S.setFont(new Font("Pristina", Font.BOLD, 25));
		panel2.add(N);
		panel2.add(E);
		panel2.add(W);
		panel2.add(S);
		
		/*sprinklerInput = new JTextField(10);
		sprinklerInput.setFont(new Font("Pristina", Font.BOLD, 25));
		panel2.add(sprinklerInput);*/
		
		/*JLabel label = new JLabel("(press enter after each sprinkler entry)");
		label.setFont(new Font("Pristina", Font.BOLD, 25));
		panel2.add(label);*/
		panelSchedule.add(panel2);
		
		panel.add(panelSchedule);
		
		panelTimeDay = new JPanel();
		panelTimeDay.setBorder(new TitledBorder("Day and Time setting"));
		startDateLabel = new JLabel("Start Date: ");
	//	startDateLabel.setFont(new Font("Pristina", Font.BOLD, 25));

		startDateField= new JTextField(10); 
//		startDateField.setFont(new Font("Pristina", Font.BOLD, 25));
		startDateField.setText("mm/dd/yyyy");
		
		endDateLabel = new JLabel("End Date: ");
//		endDateLabel.setFont(new Font("Pristina", Font.BOLD, 25));

		endDateField= new JTextField(10); 
//		endDateField.setFont(new Font("Pristina", Font.BOLD, 25));
		endDateField.setText("mm/dd/yyyy");
		
		startHrLabel = new JLabel("Start Time Hrs:"); 
//		startHrLabel.setFont(new Font("Pristina", Font.BOLD, 25));

		startHrField= new JTextField(2);
//		startHrField.setFont(new Font("Pristina", Font.BOLD, 25));

		endHrLabel  = new JLabel("End Time Hrs:");
//		endHrLabel.setFont(new Font("Pristina", Font.BOLD, 25));

		endHrField = new JTextField(2);
//		endHrField.setFont(new Font("Pristina", Font.BOLD, 25));

		startMinLabel = new JLabel("Min:"); 
//		startMinLabel.setFont(new Font("Pristina", Font.BOLD, 25));

		startMinField = new JTextField(2);
//		startMinField.setFont(new Font("Pristina", Font.BOLD, 25));

		endMinLabel  = new JLabel("Min:");
//		endMinLabel.setFont(new Font("Pristina", Font.BOLD, 25));

		endMinField = new JTextField(2);
//		endMinField.setFont(new Font("Pristina", Font.BOLD, 25));

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
		low = new JRadioButton("Low");
//		low.setFont(new Font("Pristina", Font.BOLD, 25));
		low.setActionCommand("low");
		medium = new JRadioButton("Medium");
//		medium.setFont(new Font("Pristina", Font.BOLD, 25));
		medium.setActionCommand("medium");
		high = new JRadioButton("High");
//		high.setFont(new Font("Pristina", Font.BOLD, 25));
		high.setActionCommand("high");
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
		SubmitButtonHandler submitButtonHandler = new SubmitButtonHandler();
		submit.addActionListener(submitButtonHandler);
		
		panel3.add(submit);
		
		panel.add(panel3);	
	}
	
	private class SubmitButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String scheduleName = getScheduleInput();
			//String sprinklerInput = getSprinklerInput();
			String startDate = getStartDateField();
			String endDate = getEndDateField();
			if (endDate.length()==0 || endDate==null){
				endDate=startDate;
			}
			String startTimeHr = getStartHrField();
			String startTimeMin = getStartMinField();
			String endTimeHr = getEndHrField();
			String endTimeMin = getEndMinField();
			
			//Get checked groups
			ArrayList<String> group = new ArrayList<String>();
			if (N.isSelected()==true) group.add("North");
			if (S.isSelected()==true) group.add("South");
			if (E.isSelected()==true) group.add("East");
			if (W.isSelected()==true) group.add("West");
			
			//make getTimeString in InserToSchedule public
			
			//Water configuration
			String waterConfig="";
			if (low.isSelected()==true) waterConfig = low.getActionCommand();
			if (medium.isSelected()==true) waterConfig = medium.getActionCommand();
			if (high.isSelected()==true) waterConfig = high.getActionCommand();
			
			//Call the insertToSchedule function
				for(String i:group){
							System.out.println(scheduleName +" "+startDate+" "+ endDate+" "+startTimeHr+" "+ startTimeMin
							+ " "+ endTimeHr + " " + endTimeMin+" "+ i);
							insertScheduleForGroup( i ,  scheduleName
							  ,  waterConfig.toUpperCase() ,  startDate , endDate
							  ,  startTimeHr , startTimeMin , endTimeHr ,  endTimeMin); //Insert schedule for group
				 }
		}
		
	}
	
    public void insertScheduleForGroup(String group , String scheduleName
			  , String waterFlow , String startDate ,String endTime
			  , String startHr ,String startMin ,String endHr , String endMin){
    	
    			System.out.println("inside insert schedule for group"+ group);
				InsertToSchedule insertSchedule = new InsertToSchedule();
				try {
					connectDBCon     	 = new ConnectToDB();
					con  				 = connectDBCon.openConnection();
				    query 				 = new QueryDB();
					ArrayList<String> sprinkler	   = query.getAllSprinklers(con ,group);
					System.out.println(sprinkler);
				 // Display elements and insert schedule per sprinkler in group 
					for(String i : sprinkler) {
							System.out.println(i);
							//insert schedule in Db
							insertSchedule.processInsertSchedQuery(con,scheduleName , i ,group ,waterFlow
																 ,startDate, endTime
																 ,startHr, startMin
																 ,endHr, endMin );
					}
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}finally {
				if (con != null)
					   connectDBCon.closeConnection(con);
				}
	}//end insert schedule method
	
	public String getStartDateField(){
		return startDateField.getText();
	}
	
	public String getEndDateField(){
		return endDateField.getText();
	}
	
	public String getStartHrField(){
		return startHrField.getText();
	}
	
	public String getEndHrField(){
		return endHrField.getText();
	}
	
	public String getStartMinField(){
		return startMinField.getText();
	}

	public String getEndMinField(){
		return endMinField.getText();
	}
	
	public String getSprinklerInput(){
		return sprinklerInput.getText();
	}
	
	public String getScheduleInput(){
		return scheduleInput.getText();
	}
	

	
}