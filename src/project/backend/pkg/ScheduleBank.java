package project.backend.pkg;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import project.db.pkg.*;


public class ScheduleBank extends Observable {
	private static ArrayList<Schedule> todaysScheduleList;
	private static ArrayList<ScheduledGroup> scheduleGroupList;
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
						System.out.println("todays sched list size "+todaysScheduleList.size());
				} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}finally{
				if(con!= null)
					connectDBCon.closeConnection(con);
			}
	}
	
	public void addSchedule(ScheduledGroup s){
		this.scheduleGroupList.add(s);
	}
	
	public void sort(){
			Collections.sort(todaysScheduleList);
			
			System.out.println("sorted list");
	}
	
	public void getScheduledGroupList(){
		scheduleGroupList = new ArrayList<ScheduledGroup>();
		if(todaysScheduleList.size() >0){
					ScheduledGroup tempSG = new ScheduledGroup(todaysScheduleList.get(0).getSprinklerGroup()
															,todaysScheduleList.get(0).getStartTime()
															, todaysScheduleList.get(0).getEndTime());
					
					for(int i = 1 ; i< todaysScheduleList.size(); i++){
						if(tempSG.getStartTime().equals(dayTime.getTimeToString(todaysScheduleList.get(i).getStartTime()))
							&& tempSG.getEndTime().equals(dayTime.getTimeToString(todaysScheduleList.get(i).getEndTime()))){
							   tempSG.setGroups(tempSG.getGroups()+" "+ todaysScheduleList.get(i).getSprinklerGroup());	
							}
						else{
							//if(!scheduleGroupList.contains(tempSG))
								scheduleGroupList.add(tempSG);
							tempSG =  new ScheduledGroup(todaysScheduleList.get(i).getSprinklerGroup()
										,todaysScheduleList.get(i).getStartTime()
										, todaysScheduleList.get(i).getEndTime()); 
						}	
					}
					//if(!scheduleGroupList.contains(tempSG))
					     scheduleGroupList.add(tempSG);
					
					System.out.println("getScheduledGroupList"+ scheduleGroupList.size() );
					//scheduleGroupList.toString();
		}
		/*	
		ScheduledGroup one = new ScheduledGroup("N S", "20:14", "20:15");
		ScheduledGroup two = new ScheduledGroup("E W", "20:16", "20:17");
		scheduleGroupList.add(one);
		scheduleGroupList.add(two);
	*/	
		
	}
	
	public void moveIndexToCurrent() throws ParseException{
		if(scheduleGroupList.size() > 0){
		  for(int i =0 ; i< scheduleGroupList.size() ;i++){
			 // String startTime = todaysScheduleList.get(i).getStartTime();
			  boolean moveFlag = dayTime.checkToStartSprinkler(scheduleGroupList.get(i).getStartTime()
					  										  ,scheduleGroupList.get(i).getEndTime());
			  if(!moveFlag){
				  index =i;
				  break;
			  }
				  
		  }
		  scheduleGroupList.get(index).toString();
		}  
	}
	
	/**
	 * Inner Class
	 */
	private class ScheduleNotifier extends TimerTask{
		public void run() {
			if(index < scheduleGroupList.size()){
				currentSchedule = scheduleGroupList.get(index);
				if (getCurrentTimeStamp().equals(currentSchedule.getStartTime()+":00")){
					setChanged();
					System.out.println("notified to turn on");
					notifyObservers("ON:" + currentSchedule.getGroups());
				}
				if (getCurrentTimeStamp().equals(currentSchedule.getEndTime()+":00")){
					setChanged();
					System.out.println("notified to turn off");
					notifyObservers("OFF:" + currentSchedule.getGroups());
					index++;
					if (index == todaysScheduleList.size()) {}
					else if(index < scheduleGroupList.size()) {currentSchedule = scheduleGroupList.get(index);}
				}
			}
		}		
	}
	
	//Returns 24 hour format time. HH:MM:ss
	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
		return sdfDate.format(new Date());
	}
	
	//Getter Setter
	public ArrayList<Schedule> getTodaysScheduleList() {
		return todaysScheduleList;
	}

	public void setTodaysScheduleList(ArrayList<Schedule> todaysScheduleList) {
		this.todaysScheduleList = todaysScheduleList;
	}

	public ArrayList<ScheduledGroup> getScheduleGroupList() {
		return scheduleGroupList;
	}

	public void setScheduleGroupList(ArrayList<ScheduledGroup> scheduleGroupList) {
		this.scheduleGroupList = scheduleGroupList;
	}


	
	
}
