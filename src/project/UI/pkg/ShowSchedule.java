package project.UI.pkg;

import java.awt.*;
import java.text.ParseException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import project.backend.pkg.*;

public class ShowSchedule {
	protected JPanel panel;
	protected ArrayList<JLabel> schedule;
	
	public ShowSchedule(){
		//initializeSchedules();
		initialize();
	}
	
	private void initializeSchedules(){
		try {	
				schedule = new ArrayList<JLabel> ();
				ScheduleBank sg = new ScheduleBank();
				for(ScheduledGroup i : sg.getScheduleGroupList()){
					JLabel l = new JLabel(i.toString());
					schedule.add(l);
				}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		schedule = new ArrayList<JLabel> ();
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(500, 200));
		panel.setBorder(new TitledBorder("Todays Schedules"));
		
		try {	
			ScheduleBank sg = new ScheduleBank();
			for(ScheduledGroup i : sg.getScheduleGroupList()){
				schedule.add(new JLabel(i.toString()));
				//schedule.add(l);
				panel.add(new JLabel(i.toString()));
			}
		} catch (ParseException e) {
				e.printStackTrace();
		}
//		for(JLabel i : schedule){
//			panel.add(i);
//		}

		
	}
	
	
}
