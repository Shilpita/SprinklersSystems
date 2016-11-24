package project.db.pkg;

import java.sql.*;
import java.text.*;
import java.util.*;

import project.backend.pkg.Sprinkler;

/**
 * @author shilpita_roy
 * SELECT SPRINKLERID , WATERCONSUMPTION FROM SPRINKLER_GROUP WHERE FUNCTIONALSTATUS ='true' AND LOCATION = 'North';
 * 
 */

public class QueryDB {
//	private InsertToDB insertDBCon ;
	private Connection con ;
	private Statement stmt ;
	private ResultSet rs;
	private ArrayList<String> sprinklerList;
	private static int count =0;
	java.util.Date date; 
	java.sql.Date sqlDate ;
	
	//default Constructor
    public QueryDB(){
    	this.sprinklerList = new ArrayList<String>();
    	date = null;
    	sqlDate = null;
    }

    /**
     * Get formated string for current date
     * @param currentDate
     * @return formated date string eg 11-DEC-16
     */
    public String  getFormattedDate(String currentDate) {

    	if (currentDate == null) {
    	    return null;
    	}
    	String formatedCurrentDate = null;
    	try {
	    		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	    		format.setLenient(false);
	    	    date = format.parse(currentDate);
	    	    sqlDate = new java.sql.Date(date.getTime());
	    	    SimpleDateFormat newFormat=new SimpleDateFormat("dd-MMM-yy");
	    	    formatedCurrentDate = newFormat.format(sqlDate);
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	return formatedCurrentDate;
    }
    
    
    /**
     * Method to check if current time is within the given sprinkler schedule time where startFlag = 1 , stopFlag =0 
     * @param startTime
     * @param endTime
     * @return start sprinkler flag
     * @throws ParseException
     */
	public boolean checkToStartSprinkler(String startTime,String endTime) throws ParseException {
        boolean isStartSprinkler = false;
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        calStart.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.substring(0, 2)));
        calStart.set(Calendar.MINUTE, Integer.parseInt(startTime.substring(3)));
        calStart.set(Calendar.SECOND, 0);
        calStart.set(Calendar.MILLISECOND, 0);
        calEnd.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endTime.substring(0, 2)));
        calEnd.set(Calendar.MINUTE, Integer.parseInt(endTime.substring(3)));
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        if (Calendar.getInstance().after(calStart) && Calendar.getInstance().before(calEnd)) {
            System.out.println("start sprinkler");
            isStartSprinkler = true;
        } else {
            System.out.println("Do not start sprinkler");
        }
        return isStartSprinkler;
    }
    
    /**
     * 
     * @param con
     * @param group
     * @return HashMap of 
     */
    public ArrayList<String> getAllSprinklers(Connection con,String group){
			try {
							Statement stmt 	= con.createStatement();
							rs				= stmt.executeQuery("SELECT SPRINKLERID "     ///, WATERCONSUMPTION "
																+ "FROM SPRINKLER_GROUP "
																+ "WHERE FUNCTIONALSTATUS ='true' AND LOCATION = '"+group+"'");
							while ( rs.next() ) {
								sprinklerList.add(rs.getString("SPRINKLERID"));   //,rs.getString("WATERCONSUMPTION"));    
				            }
			    } catch ( SQLException e) {
			    			e.printStackTrace();
			    } 
			
		return sprinklerList;
    }
    
    public ArrayList<Sprinkler> getActiveScheduleSprinklerGroup(Connection con ,String group ,String currentDate ,String currentTime){
    	String todayDate = getFormattedDate(currentDate);
    	ArrayList<Sprinkler> activeSprinklerList = new ArrayList<Sprinkler>();
    	try{
    			Statement stmt 	= con.createStatement();
    			rs				= stmt.executeQuery("SELECT SPRINKLERID,LOCATION,STARTTIME,ENDTIME,WATERFLOW  FROM SPRINKLER_SCHEDULE" 
    												+" WHERE "   // LOCATION = '" + group +"'AND "
    												+"SCHEDULESTARTDATE <= '"+ todayDate.toUpperCase() 
    												+"' AND SCHEDULEENDDATE >= '"+ todayDate.toUpperCase() +"'");
    			while(rs.next()){
    				boolean flag = checkToStartSprinkler(rs.getString("STARTTIME"),rs.getString("ENDTIME"));
    				if(!activeSprinklerList.contains(rs.getString("SPRINKLERID"))){
    					activeSprinklerList.add(new Sprinkler(rs.getString("SPRINKLERID"), rs.getString("LOCATION"), true, flag, rs.getString("WATERFLOW")));
    				}		
    			}
    	} catch ( SQLException | ParseException e) {
			e.printStackTrace();
    	} 
    	return activeSprinklerList;
    }
    
    

}
