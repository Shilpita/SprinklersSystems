package project.backend.pkg;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.*;
import project.UI.pkg.*;
import project.db.pkg.*;




public class ScheduleBank extends Observable {
	private ArrayList<Schedule> todaysScheduleList;
	private Clock clock;
	private static  ConnectToDB connectDBCon ;
	private static Connection con ;
    private ScheduledExecutorService service;
    
	public ScheduleBank() throws ParseException{
		this.todaysScheduleList = new ArrayList<Schedule>();
		this.service = Executors.newSingleThreadScheduledExecutor();
	}
	
	public void startNotifying(){
		Schedule s = todaysScheduleList.get(0);
		
		//DateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
	    //Date date = dateFormatter.parse("2016-11-25 18:41:10");
		Date startDate = s.getStart();
		Date endDate = s.getEnd();
		
		Calendar now= Calendar.getInstance();
		String hour =   String.valueOf(now.get(Calendar.HOUR_OF_DAY));
		String minute = String.valueOf(now.get(Calendar.MINUTE));
		while (true) {
		    hour =   String.valueOf(now.get(Calendar.HOUR_OF_DAY));
		    minute = String.valueOf(now.get(Calendar.MINUTE));
		    System.out.println(hour);
		    System.out.println(minute);
		    String currTime = hour +":"+minute;
		    
		   // boolean flag = dateTime.checkToStartSprinkler(rs.getString("STARTTIME"),rs.getString("ENDTIME"));
		    
		    
		    if (currTime.equals(s.getStart().toString())){
				setChanged();
	        	notifyObservers("Turn on");
	        }
	        if (currTime.equals(s.getEnd().toString())){
	        	setChanged();
	        	notifyObservers("Turn off");
	        }
	        
	        
		    // sleep for 5 secs (so minute updates will be accurate to 5 secs)
		    // Thread.sleep is not always precise and inaccuracies
		    // could build up if we slept for 1 minute
		    try { Thread.sleep(5000); } catch(Exception e){}
		    // later on when you build more complex programs,
		    // you will make use of the catch block, but for now, ignore it
		}
	}
	
	public void loadSchedules(){
			try {
						connectDBCon     	 = new ConnectToDB();
						con  				 = connectDBCon.openConnection();
						QueryDB query 		 = new QueryDB();
						todaysScheduleList = query.getActiveScheduleSprinklerList(con);//  , "North", "11/24/2016", "22:50");
						System.out.println(todaysScheduleList.toString());
				} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		    
	}
	
	public void addSchedule(Schedule s){
		this.todaysScheduleList.add(s);
	}
	
	public void sort(ArrayList<Schedule> todaysScheduleList){
			Collections.sort(todaysScheduleList);
	}
}
