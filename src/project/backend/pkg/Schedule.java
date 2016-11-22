package project.backend.pkg;

import java.sql.Time;

/**
 *
 * @author Preethi
 */
public class Schedule {
    private String typeOfSchedule;      //Can be either "Ongoing" (weekly) or "Ondemand"
    private DayAndTime dayAndTime;
    private Sprinkler sprinkler;
    
    public Schedule(){
        this.typeOfSchedule="";
        this.dayAndTime = new DayAndTime();
        this.sprinkler = new Sprinkler();
    }
    
    public Schedule(String typeOfSchedule, String day, Time startTime, Time endTime, String sprinklerId ,String location ,boolean funtionalStatus ,boolean currentStatus ,String waterConsumption){
        this.typeOfSchedule=typeOfSchedule;
        this.dayAndTime= new DayAndTime(day, startTime, endTime);
        
    }
    
    
}

