package project.backend.pkg;

import java.util.*;
import java.text.*;


public class Schedule implements Comparable<Schedule>{
	private Date start;
	private Date end;
	private Calendar startTime;
	private Calendar endTime;
	private String sprinklerGroups[];
	private String sprinkler;
	private DayAndTime dateTime;
	
	public Schedule(){}
	
	public Schedule(String start, String end,String startTime,String endTime, String sprinklers){
		this.dateTime = new DayAndTime();
		this.start = dateTime.getFormatedDate(start);   //start;
		this.end = dateTime.getFormatedDate(end);
		this.startTime = dateTime.getStringToTime(startTime);
		this.endTime =dateTime.getStringToTime(endTime);
		this.setSprinkler(sprinklers);
	}

	/*public static void main(String[] args) throws ParseException{
		String starttime = "20:30:18";
		DateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
		Date startd = sdf1.parse(starttime);
		
		String endtime = "15:30:18";
		DateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
		Date endd = sdf2.parse(endtime);
		
		Scheduler s = new Scheduler(startd, endd, "N");
	}*/
	
	public Date getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = dateTime.getFormatedDate(start); 
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = dateTime.getFormatedDate(end);
	}

	public String[] getSprinklerGroups() {
		return sprinklerGroups;
	}

	public void setSprinklerGroups(String[] sprinklerGroups) {
		this.sprinklerGroups = sprinklerGroups;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = dateTime.getStringToTime(startTime);
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = dateTime.getStringToTime(endTime);
	}

	public String getSprinkler() {
		return sprinkler;
	}

	public void setSprinkler(String sprinkler) {
		this.sprinkler = sprinkler;
	}
	
	@Override
	public String toString() {
		return "Schedule [start=" + start + ", end=" + end + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", sprinklerGroups=" + Arrays.toString(sprinklerGroups) + ", sprinkler=" + sprinkler + "]";
	}


	@Override
	public int compareTo(Schedule o) {
		if (getStartTime() == null || o.getStartTime() == null)
		      return 0;
		    return getStartTime().compareTo(o.getStartTime());
	}

}
