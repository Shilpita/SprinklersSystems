package project.backend.pkg;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import project.db.pkg.*;


public class ScheduleBank extends Observable {
	private ArrayList<Schedule> todaysScheduleList;
	private ArrayList<ScheduledGroup> scheduleGroupList;
	private static ScheduledGroup currentSchedule;
	private static  ConnectToDB connectDBCon ;
	private static Connection con ;
	private Timer timer;
	private static int index;
	private DayAndTime dayTime;
	
	
	public ScheduleBank() throws ParseException{
		this.todaysScheduleList = new ArrayList<Schedule>();
		this.scheduleGroupList = new ArrayList<ScheduledGroup>();
		this.dayTime = new DayAndTime();
		index =0;
		
	}
	
	public void startNotifying(){
		timer = new Timer();
		ScheduleNotifier sn = new ScheduleNotifier();
		timer.schedule(sn, 0, 1000);
	}
	
	public void loadSchedules(){
			try {
						connectDBCon     	 = new ConnectToDB();
						con  				 = connectDBCon.openConnection();
						QueryDB query 		 = new QueryDB();
						todaysScheduleList 	 = query.getActiveScheduleSprinklerGroup(con);//  , "North", "11/24/2016", "22:50");
						System.out.println(todaysScheduleList.toString());
				} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		    
	}
	
	public void addSchedule(Schedule s){
		this.todaysScheduleList.add(s);
	}
	
	public void sort(){
			Collections.sort(todaysScheduleList);
	}
	
	public void getScheduledGroupList(){
		//ArrayList<ScheduledGroup> resultList = new ArrayList<ScheduledGroup>();
		ScheduledGroup tempSG = new ScheduledGroup(todaysScheduleList.get(0).getSprinklerGroup()
												,todaysScheduleList.get(0).getStartTime()
												, todaysScheduleList.get(0).getEndTime());
		for(int i = 1 ; i< todaysScheduleList.size(); i++){
			if(tempSG.getStartTime().equals(dayTime.getTimeToString(todaysScheduleList.get(i).getStartTime()))
				&& tempSG.getEndTime().equals(dayTime.getTimeToString(todaysScheduleList.get(i).getEndTime()))){
				   tempSG.setGroups(tempSG.getGroups()+" "+ todaysScheduleList.get(i).getSprinklerGroup());	
				}
			else{
				scheduleGroupList.add(tempSG);
					tempSG =  new ScheduledGroup(todaysScheduleList.get(i).getSprinklerGroup()
							,todaysScheduleList.get(i).getStartTime()
							, todaysScheduleList.get(i).getEndTime()); 
			}	
		}
		scheduleGroupList.add(tempSG);
		
		
		
	}
	
	public void moveIndexToCurrent() throws ParseException{
		  for(int i =0 ; i< scheduleGroupList.size() ;i++){
			 // String startTime = todaysScheduleList.get(i).getStartTime();
			  boolean moveFlag = dayTime.checkToStartSprinkler(scheduleGroupList.get(i).getStartTime()
					  										  ,scheduleGroupList.get(i).getEndTime());
			  if(!moveFlag){
				  index =i;
				  break;
			  }
				  
		  }
	}
	
	/**
	 * Inner Class
	 */
	private class ScheduleNotifier extends TimerTask{
		public void run() {
			currentSchedule = scheduleGroupList.get(index);
			if (getCurrentTimeStamp().equals(currentSchedule.getStartTime()+":00")){
				setChanged();
				notifyObservers("ON:" + currentSchedule.getGroups());
			}
			if (getCurrentTimeStamp().equals(currentSchedule.getEndTime()+":00")){
				setChanged();
				notifyObservers("OFF:" + currentSchedule.getGroups());
				index++;
				if (index == todaysScheduleList.size()) {}
				else {currentSchedule = scheduleGroupList.get(index);}
			}
		}
	}
	
	//Returns 24 hour format time. HH:MM:ss
	public String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
		return sdfDate.format(new Date());
	}
	
	
}
