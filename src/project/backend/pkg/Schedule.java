package project.backend.pkg;

import java.util.*;
import java.text.*;


public class Schedule implements Comparable<Schedule>{
//	private Date start;
//	private Date end;
	private Calendar startTime;
	private Calendar endTime;
	private String sprinklerGroup;
//	private String sprinkler;
	private DayAndTime dateTime;
	
	public Schedule(){}
	
//	public Schedule(String start, String end,String startTime,String endTime, String sprinklers){
	public Schedule(String sprinklerGroup, String startTime,String endTime){
		this.dateTime = new DayAndTime();
//		this.start = dateTime.getFormatedDate(start);   //start;
//		this.end = dateTime.getFormatedDate(end);
		this.startTime = dateTime.getStringToTime(startTime);
		this.endTime =dateTime.getStringToTime(endTime);
		this.sprinklerGroup = sprinklerGroup;
	}

	public String getSprinklerGroup() {
		return sprinklerGroup;
	}

	public void setSprinklerGroup(String sprinklerGroups) {
		this.sprinklerGroup = sprinklerGroups;
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


	@Override
	public int compareTo(Schedule o) {
		if (getStartTime() == null || o.getStartTime() == null)
		      return 0;
		    return getStartTime().compareTo(o.getStartTime());
	}

	@Override
	public String toString() {
		return "Schedule [startTime=" + startTime + "\n, endTime=" + endTime + "\n, sprinklerGroup=" + sprinklerGroup + "]\n\n";
	}
	

}



/******
 * 
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


********/