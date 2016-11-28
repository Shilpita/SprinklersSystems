package project.backend.pkg;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScheduledGroup {
	
	private String groups ;
	private String startTime;
	private String endTime;
	
	public ScheduledGroup(){}
	
	public ScheduledGroup(String groups, String startTime, String endTime) {
		super();
		this.groups = groups;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public ScheduledGroup(String group, Calendar startTime, Calendar endTime){
    	SimpleDateFormat newFormat=new SimpleDateFormat("HH:mm");
    	this.startTime  = newFormat.format(startTime.getTime());
    	this.endTime = newFormat.format(endTime.getTime());
    	this.groups = group;
	}
	
	
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(Calendar startTime) {
		SimpleDateFormat newFormat=new SimpleDateFormat("HH:mm");
    	this.startTime  = newFormat.format(startTime.getTime());
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Calendar endTime) {
		SimpleDateFormat newFormat=new SimpleDateFormat("HH:mm");
    	this.endTime = newFormat.format(endTime.getTime());
	}

	@Override
	public String toString() {
		return "ScheduledGroup [groups=" + groups + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
    
	

}
