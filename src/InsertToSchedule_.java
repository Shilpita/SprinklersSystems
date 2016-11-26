

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import project.backend.pkg.WaterFlow;
import project.db.pkg.ConnectToDB;

/***
 * 
 * @author shilpita_roy
 *     String scheduleID VARCHAR2(10)
      String sprinklerID VARCHAR2(5)
      scheduleDate Date
      ,startTime TIMESTAMP 
      ,endTime TIMESTAMP
      ,totalHours NUMBER
      
      INSERT INTO SPRINKLER_SCHEDULE VALUES (?,?,?,?,?,?,?)
 *
 */

public class InsertToSchedule_ {
	private static String scheduleName ;
	private static String startDate;
	private static String endDate;
	private static String sprinkler;
	private String group;
	private String waterflow;
	private static String startHrTime ;
	private static String startMinTime;
	private static String endHrTime;
	private static String endMinTime;
	private static  ConnectToDB connectDBCon ;
	private static Connection con ;
	private PreparedStatement preparedStatement ;
	private String insertStmt ;
	private static int count =0;
	
	public InsertToSchedule_(){
		//Comment Later
		scheduleName = "Test1";
		startDate="12/1/2009";
		endDate ="12/10/2009";
		sprinkler ="1N";
		group = "North";
		waterflow ="LOW";
		startHrTime ="10" ;
	    startMinTime ="30";
	    endHrTime="14";
	    endMinTime="30";
	}
	
	private static String getTimeString(String hr , String min){
		return hr+":"+min;
	}
	
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
	
	private static long getTotalTime(String starttime, String endtime) {
		 	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
	        Date date = null;
	        Date date1 = null;
	        TimeUnit timeUnit = TimeUnit.HOURS;
	        try {
	            date = sdf.parse(starttime);
	            date1 = sdf.parse(endtime);            
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        long diffInMillies = date1.getTime() - date.getTime();
	        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	public void insertScheduleRow(Connection con ,String scheduleName, String sprinkler ,String startDate ,String endDate 
			,String startTime,String endTime, long totalTime){
		try {
				insertStmt              = "INSERT INTO SPRINKLER_SCHEDULE VALUES (?,?,?,?,?,?,?,?)" ;
				
				preparedStatement       = con.prepareStatement(insertStmt);
				preparedStatement.setInt(1, ++count);
				preparedStatement.setString(2, scheduleName);
				preparedStatement.setString(3, sprinkler);
				preparedStatement.setDate(4, getFormatedDate(startDate));
				preparedStatement.setDate(5, getFormatedDate(endDate));
				preparedStatement.setString(6,startTime );
				preparedStatement.setString(7, endTime);
				preparedStatement.setLong(8, totalTime);
				
				preparedStatement.executeUpdate();
				preparedStatement.close();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
		
}

	public static void main(String[] args) { 
		InsertToSchedule_ sched = new InsertToSchedule_();
		String startTime = getTimeString(startHrTime,startMinTime);
		String endTime =getTimeString(endHrTime,endMinTime);
		Long totalTime = getTotalTime(startTime,endTime);
		try {
			connectDBCon     = new ConnectToDB();
			con             = connectDBCon.openConnection();
			sched.insertScheduleRow(con, scheduleName, sprinkler, startDate, endDate, startTime, endTime, totalTime);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (con != null)
				connectDBCon.closeConnection(con);
		}
		
	}
	

}
