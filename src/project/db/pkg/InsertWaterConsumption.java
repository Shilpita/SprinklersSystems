package project.db.pkg;

import java.sql.PreparedStatement;

import project.backend.pkg.DayAndTime;

public class InsertWaterConsumption {
	private PreparedStatement preparedStatement ;
	private String insertStmt ;
	private DayAndTime dateTime;
	
	public InsertWaterConsumption(){
		this.insertStmt = null;
		dateTime = new DayAndTime();
	}

	
	
	

}
