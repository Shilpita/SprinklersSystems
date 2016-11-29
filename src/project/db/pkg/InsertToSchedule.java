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
	private  ConnectToDB connectDBCon ;
	private  Connection con ;
	private QueryDB query;
	
	/**
	 * Default Constructor 
	 */
	public InsertToSchedule(){
			this.insertStmt = null;
			dateTime = new DayAndTime();
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
									preparedStatement.setDate(4, dateTime.getFormatedDate(startDate));
									preparedStatement.setDate(5, dateTime.getFormatedDate(endDate));
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
	
	
    public void processInsertSchedQuery(String scheduleName ,  String group,String waterflow
    									,String startDate, String endDate 
    									,String startHrTime, String startMinTime
    									, String endHrTime, String endMinTime )
    {	
								String startTime = dateTime.getTimeString(startHrTime,startMinTime);
								String endTime = dateTime.getTimeString(endHrTime,endMinTime);
								long totalTime = dateTime.getTotalTime(startTime,endTime,TimeUnit.MINUTES,new SimpleDateFormat("hh:mm"));
								///if the start or end time is midnight or midday then convert to positive difference hours
								if(totalTime < 0) 
									totalTime = 12 + totalTime;
								else if(totalTime == 0)
									totalTime = 1 + totalTime;
								long totalDays = dateTime.getTotalTime(startDate,endDate,TimeUnit.DAYS,new SimpleDateFormat("MM/dd/yyyy"))+1;
								String[] str =  startDate.split("/");
								String month = dateTime.getMonthList()[Integer.parseInt(str[0])-1];
								
								long totalWaterConsumption = WaterFlow.getTotalWaterConsumption(waterflow,totalTime,totalDays);
								try {
									connectDBCon     	 = new ConnectToDB();
									con  				 = connectDBCon.openConnection();
								    query 				 = new QueryDB();
									//ArrayList<String> sprinkler	   = query.getAllSprinklers(con ,group);
								    query.getAllSprinklers(con ,group);
									//System.out.println(sprinkler);
								 // Display elements and insert schedule per sprinkler in group 
									for(String i : QueryDB.sprinklerList) {
											System.out.println(i);
											//insert schedule in Db
											this.insertScheduleRow(con, scheduleName, i, group
															  ,startDate, endDate ,month
															  ,startTime, endTime
															  ,totalTime,totalDays, totalWaterConsumption,waterflow);
									}
								} catch (ClassNotFoundException ex) {
									ex.printStackTrace();
								} catch (SQLException ex) {
									ex.printStackTrace();
								}finally {
								if (con != null)
									   connectDBCon.closeConnection(con);
								}
				
	}//end insert method
    
    
	
}
