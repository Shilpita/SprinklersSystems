package project.backend.pkg;


import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Preethi
 */
public class DayAndTime {
    private String day;
    private Date date;
    private Time startTime;
    private Time endTime;
	private String[] monthList = {	"JAN"
									,"FEB"
									,"MAR"
									,"APR"
									,"MAY"
									,"JUN"
									,"JUL"
									,"AUG"
									,"SEP"
									,"OCT"
									,"NOV"
									,"DEC" };
    
    public DayAndTime(){
        this.day = "";
        this.date =null;
        this.startTime = null;
        this.endTime = null;
    }
    
    public DayAndTime(String day, Time startTime, Time endTime){
        setDay(day);
        setStartTime(startTime);
        setEndTime(endTime);
    }
    
    public String getDay(){
        return this.day;
    }
    
    public Time getStartTime(){
        return this.startTime;
    }
    
    public Time getEndTime(){
        return this.endTime;
    }
    
    public void setDay(String day){
        this.day=day;
    }
    
    public void setStartTime(Time startTime){
        this.startTime=startTime;
    }
    
    public void setEndTime(Time endTime){
        this.endTime=endTime;
    }
    
	/**
	 * Function returns time in string Hr:min eg(12:20)
	 * @param hr
	 * @param min
	 * @return String Hr:min
	 */
    public String getTimeString(String hr , String min){
		return hr+":"+min;
	}
	
	/**
	 * Function returns date in sql date MM/dd/yyyy eg:12/20/2009 => 20-DEC-09.
	 * @param mydate
	 * @return  date in sql data format MM/dd/yyyy.
	 */
	public java.sql.Date getFormatedDate(String mydate){
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
	
	public long getTotalTime(String starttime, String endtime,TimeUnit timeUnit,SimpleDateFormat sdf) {
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
     * Get formated string for current date
     * @param currentDate
     * @return formated date string eg. 11-DEC-16
     */
    public String  getFormattedDatetoString() { 
    	SimpleDateFormat newFormat=new SimpleDateFormat("dd-MMM-yy");
    	String formatedCurrentDate = null;

		                Calendar c = Calendar.getInstance();
		                 c.setTime(new Date());			    	    
	    formatedCurrentDate = newFormat.format(c.getTime());   // newFormat.format(sqlDate);
    	return formatedCurrentDate;
    }
    
    /**
     * This method takes a string input in HH:mm format and returns Calender time in hh:mm format
     * @param time
     * @return Calender time in hh:mm format
     */
    public Calendar getStringToTime (String time){
    	Calendar resultTime = Calendar.getInstance();
    	resultTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0, 2)));
    	resultTime.set(Calendar.MINUTE, Integer.parseInt(time.substring(3)));
    	resultTime.set(Calendar.SECOND, 0);
    	resultTime.set(Calendar.MILLISECOND, 0);
        return resultTime;
    }
    
    public String getTimeToString(Calendar c){
    	SimpleDateFormat newFormat=new SimpleDateFormat("HH:mm");
    	String output = newFormat.format(c.getTime());
    	return output;
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
        Calendar calStart = getStringToTime(startTime);
        Calendar calEnd = getStringToTime(endTime);
        if (Calendar.getInstance().after(calStart) && Calendar.getInstance().before(calEnd)) {
            System.out.println("start sprinkler");
            isStartSprinkler = true;
        } else {
            System.out.println("Do not start sprinkler");
        }
        
        return isStartSprinkler;
    }
	/*
	public boolean checkToStartSprinkler(Calendar startTime,Calendar endTime){ //throws ParseException {
        boolean isStartSprinkler = false;
     //   Calendar calStart = getStringToTime(startTime);
     //   Calendar calEnd = getStringToTime(endTime);
        if (Calendar.getInstance().after(startTime) && Calendar.getInstance().before(endTime)) {
            System.out.println("start sprinkler");
            isStartSprinkler = true;
        } else {
            System.out.println("Do not start sprinkler");
        } 
        return isStartSprinkler;
    }
	*/
	public String[] getMonthList() {
		return monthList;
	}

	
	public void setMonthList(String[] monthList) {
		this.monthList = monthList;
	}
    
}
