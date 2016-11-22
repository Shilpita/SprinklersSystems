package project.backend.pkg;


import java.sql.Time;

/**
 *
 * @author Preethi
 */
public class DayAndTime {
    private String day;
    private Time startTime;
    private Time endTime;
    
    public DayAndTime(){
        this.day="";
        this.startTime=new Time(0,0,0);
        this.endTime=new Time(0,0,12);
    }
    
    public DayAndTime(String day, Time startTime, Time endTime){
        setDay(day);
        setStartTime(startTime);
        setEndTime(endTime);
      
//        this.day=day;
//        this.startTime=startTime;
//        this.endTime=endTime;
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
    
    //TODO
    //Need to have checks
    public void setDay(String day){
        this.day=day;
    }
    
    //TODO
    //Need to have checks
    public void setStartTime(Time startTime){
        this.startTime=startTime;
    }
    
    //TODO
    //Need to have checks
    public void setEndTime(Time endTime){
        this.endTime=endTime;
    }
}
