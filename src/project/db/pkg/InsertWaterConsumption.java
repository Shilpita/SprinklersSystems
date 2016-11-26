package project.db.pkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import project.backend.pkg.DayAndTime;
import project.backend.pkg.WaterFlow;

public class InsertWaterConsumption {
	private PreparedStatement preparedStatement ;
	private String insertStmt ;
	private DayAndTime dateTime;
	
	public InsertWaterConsumption(){
		this.insertStmt = null;
		dateTime = new DayAndTime();
	}

	/**
	 * Prepare query to insert schedule row in DB
	 * INSERT INTO WATER_CONSUMPTION VALUES ( '1N','North','12-NOV-2016','NOV','21:00','22:00',1,'LOW',5)
	 * @param con
	 * @param sprinkler
	 * @param group
	 * @param startDate
	 * @param month
	 * @param startTime
	 * @param endTime
	 * @param totalTime
	 * @param waterFlow
	 * @param totalWaterConsumption
	 */
	
	public void insertWaterConsumptionRow(Connection con , String sprinkler,String group 
								 , String startDate ,String month
								 , String startTime,String endTime
								 , long totalTime , String waterFlow
								 , long totalWaterConsumption)
	{
							try {
									insertStmt              = "INSERT INTO WATER_CONSUMPTION VALUES ( ?,?,?,?,?,?,?,?,?)" ;
									
									preparedStatement       = con.prepareStatement(insertStmt);

									preparedStatement.setString(1, sprinkler);
									preparedStatement.setString(2, group);
									preparedStatement.setDate(3, dateTime.getFormatedDate(startDate));
									preparedStatement.setString(4,month);
									preparedStatement.setString(5,startTime );
									preparedStatement.setString(6, endTime);
									preparedStatement.setLong(7, totalTime);
									preparedStatement.setString(8, waterFlow);
									preparedStatement.setLong(9, totalWaterConsumption);
									
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
	
	
    public void processInsertWaterQuery(Connection con, String sprinkler, String group
    									,String startDate,String startTime, String endTime,String waterflow )
    {	
			
								long totalTime = dateTime.getTotalTime(startTime,endTime,TimeUnit.HOURS,new SimpleDateFormat("hh:mm"));
								if(totalTime < 0) 
									totalTime = 12 + totalTime;
							
								String[] str =  startDate.split("/");
								String month = dateTime.getMonthList()[Integer.parseInt(str[0])-1];
								
								long totalWaterConsumption = WaterFlow.getTotalWaterConsumption(waterflow, totalTime, 1);
								
								this.insertWaterConsumptionRow(con, sprinkler, group ,startDate, month ,startTime, endTime
													  		  ,totalTime,waterflow,totalWaterConsumption);
										
				
	}//end insert method


}
