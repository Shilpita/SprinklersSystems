import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Map.Entry;

import project.backend.pkg.DayAndTime;
import project.backend.pkg.Sprinkler;
import project.db.pkg.ConnectToDB;
import project.db.pkg.InsertToSchedule;
import project.db.pkg.InsertWaterConsumption;
import project.db.pkg.QueryDB;

public class Tester {
		private static  ConnectToDB connectDBCon ;
		private static Connection con ;
		private static ArrayList<Sprinkler> activeSprinklerList ;
		private static DayAndTime dateTime;
		
		public Tester(){
			activeSprinklerList = new ArrayList<Sprinkler>();
			dateTime = new DayAndTime();
		}
		
        public static void insertScheduleForGroup(Connection con,QueryDB query,String group , String scheduleName
        										  , String waterFlow , String startDate ,String endDate
        										  , String startHr ,String startMin ,String endHr , String endMin){
		
			       InsertToSchedule insertSchedule = new InsertToSchedule();
			       ArrayList<String> sprinkler	   = query.getAllSprinklers(con ,group);

			       // Display elements and insert schedule per sprinkler in group 
					for(String i : sprinkler) {
								System.out.println(i);
					           //insert schedule in Db
					           insertSchedule.processInsertSchedQuery(con,scheduleName , i ,group ,waterFlow
								   									 ,startDate, endDate
								   									 ,startHr, startMin
								   									 ,endHr, endMin );
					}
       }//end insert schedule method
	    
 	    
        public static void insertWaterForGroup(Connection con,QueryDB query,String group 
				  , String waterFlow , String startDate 
				  , String startTime  ,String endTime ){
				
					InsertWaterConsumption insertWater = new InsertWaterConsumption();
					ArrayList<String> sprinkler	   = query.getAllSprinklers(con ,group);
					
					// Display elements and insert schedule per sprinkler in group 
					for(String i : sprinkler) {
					System.out.println(i);
					//insert schedule in Db
					insertWater.processInsertWaterQuery(con , i ,group 
					 									 ,startDate,startTime,endTime ,waterFlow);
					 									 
					 									 
					}
        }//end insert schedule method
       	public static void main(String[] args) throws ParseException {
		
				try {
						connectDBCon     	 = new ConnectToDB();
						con  				 = connectDBCon.openConnection();
						QueryDB query 		 = new QueryDB();	
						insertWaterForGroup(con, query,"North","LOW","01/01/2016","21:00","23:00");
						insertWaterForGroup(con, query,"North","LOW","01/02/2016","21:00","23:00");
						insertWaterForGroup(con, query,"North","LOW","01/03/2016","21:00","23:00");
						insertWaterForGroup(con, query,"North","LOW","01/04/2016","21:00","23:00");
						insertWaterForGroup(con, query,"North","LOW","01/05/2016","21:00","23:00");
						insertWaterForGroup(con, query,"South","HIGH","01/01/2016","21:00","23:00");
						insertWaterForGroup(con, query,"South","HIGH","01/02/2016","21:00","23:00");
						insertWaterForGroup(con, query,"South","HIGH","01/03/2016","21:00","23:00");
						insertWaterForGroup(con, query,"South","HIGH","01/04/2016","21:00","23:00");
						insertWaterForGroup(con, query,"South","HIGH","01/05/2016","21:00","23:00");
						insertWaterForGroup(con, query,"North","HIGH","02/01/2016","21:00","23:00");
						insertWaterForGroup(con, query,"North","HIGH","03/01/2016","21:00","23:00");
					  // insertScheduleForGroup(con ,query,"West"); //Insert schedule for group
					 //activeSprinklerList = query.getActiveScheduleSprinklerGroup(con);//  , "North", "11/24/2016", "22:50");
					//	System.out.println(activeSprinklerList.toString());
						
				} catch (ClassNotFoundException e) {
						e.printStackTrace();
				} catch (SQLException e) {
						e.printStackTrace();
				}finally {
					if (con != null)
						   connectDBCon.closeConnection(con);
			    }

       	}//end main()
	
       	
}//end class



