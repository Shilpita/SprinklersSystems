package project.backend.pkg;


import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Preethi
 */
public class DayAndTime {
    private String day;
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
     * Get formated string for current date
     * @param currentDate
     * @return formated date string eg. 11-DEC-16
     */
    public String  getFormattedDate() { 
    	SimpleDateFormat newFormat=new SimpleDateFormat("dd-MMM-yy");
    	String formatedCurrentDate = null;

		                Calendar c = Calendar.getInstance();
		                 c.setTime(new Date());			    	    
	    formatedCurrentDate = newFormat.format(c.getTime());   // newFormat.format(sqlDate);
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

	public String[] getMonthList() {
		return monthList;
	}

	public void setMonthList(String[] monthList) {
		this.monthList = monthList;
	}
    
}
