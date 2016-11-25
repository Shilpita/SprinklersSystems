package project.db.pkg;

/***
 * The Class Inserts the new schedule for sprinkler in DB
 */

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import project.backend.pkg.*;

/***
 * 
 * @author shilpita_roy
 **/

public class InsertToSchedule {
	private PreparedStatement preparedStatement ;
	private String insertStmt ;
	private DayAndTime dateTime;
	/**
	 * Default Constructor 
	 */
	public InsertToSchedule(){
			this.insertStmt = null;
			dateTime = new DayAndTime();
	}
	
	/**
	 * Function returns time in string Hr:min eg(12:20)
	 * @param hr
	 * @param min
	 * @return String Hr:min
	 */
	private String getTimeString(String hr , String min){
		return hr+":"+min;
	}
	
	/**
	 * Function returns date in sql date MM/dd/yyyy eg:12/20/2009 => 20-DEC-09.
	 * @param mydate
	 * @return  date in sql data format MM/dd/yyyy.
	 */
	private java.sql.Date getFormatedDate(String mydate){
		java.util.Date myDate = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat( "MM/dd/yyyy" );  // United States style of format.
		try {
			myDate = format.parse( mydate );
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date( myDate.getTime() );
		return sqlDate;
	}
	
	/**
	 * Function to get the total hours (or days) between two times (or days) in String.
	 * @param starttime
	 * @param endtime
	 * @param timeUnit  HOURS OR DAYS
	 * @param sdf  eg MM/dd/yyyy  or Hr:mm
	 * @return totalHours
	 */
	
	private long getTotalTime(String starttime, String endtime,TimeUnit timeUnit,SimpleDateFormat sdf) {
	        Date date = null;
	        Date date1 = null;
	        try {
		            date = sdf.parse(starttime);
		            date1 = sdf.parse(endtime);            
	        } catch (ParseException e) {
	        		e.printStackTrace();
	        }
	        long diffInMillies = date1.getTime() - date.getTime();
	        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Function gets the total water consumption for complete schedule based on water flow
	 * @param waterFlow
	 * @param totalHrs
	 * @param totalDays
	 * @return total water consumption for schedule
	 */
	private long getTotalWaterConsumption(String waterFlow, long totalHrs , long totalDays){
			int waterPerhr = WaterFlow.valueOf(waterFlow.toUpperCase()).calFlowPerHr();
			return (waterPerhr * totalHrs * totalDays);
	}
	
	/**
	 * Prepare query to insert schedule row in DB
	 * @param con
	 * @param scheduleName
	 * @param sprinkler
	 * @param group
	 * @param startDate
	 * @param endDate
	 * @param month
	 * @param startTime
	 * @param endTime
	 * @param totalTime
	 * @param totalDays
	 * @param totalWaterConsumption
	 * @param waterFlow
	 */
	
	public void insertScheduleRow(Connection con ,String scheduleName , String sprinkler,String group 
								 , String startDate ,String endDate ,String month
								 , String startTime,String endTime
								 , long totalTime,long totalDays
								 , long totalWaterConsumption, String waterFlow)
	{
							try {
									insertStmt              = "INSERT INTO SPRINKLER_SCHEDULE VALUES (?,?,?,?,?,?,?,?,?,?,?,?)" ;
									
									preparedStatement       = con.prepareStatement(insertStmt);
									
									preparedStatement.setString(1, scheduleName);
									preparedStatement.setString(2, sprinkler);
									preparedStatement.setString(3, group);
									preparedStatement.setDate(4, getFormatedDate(startDate));
									preparedStatement.setDate(5, getFormatedDate(endDate));
									preparedStatement.setString(6,month);
									preparedStatement.setString(7,startTime );
									preparedStatement.setString(8, endTime);
									preparedStatement.setLong(9, totalTime);
									preparedStatement.setLong(10, totalDays);
									preparedStatement.setString(11, waterFlow);
									preparedStatement.setLong(12, totalWaterConsumption);
									
									preparedStatement.executeUpdate();
									preparedStatement.close();
									
							} catch (SQLException e) {
									e.printStackTrace();
							} 	
		
	}
	
	
	/**
	 * Insert the Schedule row for each sprinkler in DB
	 * @param con
	 * @param scheduleName
	 * @param sprinkler
	 * @param group
	 * @param waterflow
	 * @param startDate
	 * @param endDate
	 * @param startHrTime
	 * @param startMinTime
	 * @param endHrTime
	 * @param endMinTime
	 */
	
	
    public void processInsertSchedQuery(Connection con,String scheduleName , String sprinkler, String group,String waterflow
    									,String startDate, String endDate 
    									,String startHrTime, String startMinTime
    									, String endHrTime, String endMinTime )
    {	
								String startTime = getTimeString(startHrTime,startMinTime);
								String endTime = getTimeString(endHrTime,endMinTime);
								long totalTime = getTotalTime(startTime,endTime,TimeUnit.HOURS,new SimpleDateFormat("hh:mm"));
								if(totalTime < 0) 
									totalTime = 12 + totalTime;
								long totalDays = getTotalTime(startDate,endDate,TimeUnit.DAYS,new SimpleDateFormat("MM/dd/yyyy"))+1;
								String[] str =  startDate.split("/");
								String month = dateTime.getMonthList()[Integer.parseInt(str[0])-1];
								
								long totalWaterConsumption = getTotalWaterConsumption(waterflow,totalTime,totalDays);
								///if the start or end time is midnight or midday then convert to positive difference hours 
								if(totalTime < 0)  
									  totalTime = 12+totalTime;

								
								this.insertScheduleRow(con, scheduleName, sprinkler, group
													  ,startDate, endDate ,month
													  ,startTime, endTime
													  ,totalTime,totalDays, totalWaterConsumption,waterflow);
										
				
	}//end insert method
	
}
