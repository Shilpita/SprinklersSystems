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

	protected JPanel  panel ,panelSchedule, panelTimeDay,panelWaterFlow;
	private JRadioButton groupRdBtn ,sprinklerRdBtn ,selectedSprinklerRdBtn ;
	protected JRadioButton low , medium, high;
	private ButtonGroup sprinklerSelectionGrp,waterFlowGrp;
	private JTextField startDateField, endDateField, startHrField, endHrField, startMinField, endMinField;
	private JTextField sprinklerInput, scheduleInput;
	private JLabel scheduleID, sprinklerID , startDateLabel,endDateLabel ,startHrLabel ,endHrLabel,startMinLabel ,endMinLabel ;

	protected JCheckBox N, S, E, W;
	
	//data member
	private String scheduleName, startDate,endDate , sprinkler , group , waterflow ;
	private String startHrTime,startMinTime, endHrTime,endMinTime ;
	private ArrayList<String> sprinklerList;
	//db member
	
	private  ConnectToDB connectDBCon ;
	private  Connection con ;
	private QueryDB query;


	public ConfigureSchedule() {
		initialize();
	}

	private void initialize() {
		initializationSchedulePanel();
	}
	
	private void initializationSchedulePanel(){
		
		panel = new JPanel();

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

		low.setActionCommand("low");
		medium = new JRadioButton("Medium");

		medium.setActionCommand("medium");
		high = new JRadioButton("High");

		high.setActionCommand("high");
		waterFlowGrp = new ButtonGroup();
		waterFlowGrp.add(low);
		waterFlowGrp.add(medium);
		waterFlowGrp.add(high);
		
		panelWaterFlow.add(low);
		panelWaterFlow.add(medium);
		panelWaterFlow.add(high);
		panel.add(panelWaterFlow);
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