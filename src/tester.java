import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Map.Entry;

import project.backend.pkg.Sprinkler;
import project.db.pkg.ConnectToDB;
import project.db.pkg.InsertToSchedule;
import project.db.pkg.QueryDB;

public class Tester {
		private static  ConnectToDB connectDBCon ;
		private static Connection con ;
		private static ArrayList<Sprinkler> activeSprinklerList ;
		public Tester(){
			activeSprinklerList = new ArrayList<Sprinkler>();
		}
		
        public static void insertScheduleForGroup(Connection con,QueryDB query,String group , String scheduleName
        										  , String waterFlow , String startDate ,String endTime
        										  , String startHr ,String startMin ,String endHr , String endMin){
		
			       InsertToSchedule insertSchedule = new InsertToSchedule();
			       ArrayList<String> sprinkler	   = query.getAllSprinklers(con ,group);

			       // Display elements and insert schedule per sprinkler in group 
					for(String i : sprinkler) {
								System.out.println(i);
					           //insert schedule in Db
					           insertSchedule.processInsertSchedQuery(con,scheduleName , i ,group ,waterFlow
								   									 ,startDate, endTime
								   									 ,startHr, startMin
								   									 ,endHr, endMin );
					}
       }//end insert schedule method
	    
 	    
       	public static void main(String[] args) throws ParseException {
		
				try {
						connectDBCon     	 = new ConnectToDB();
						con  				 = connectDBCon.openConnection();
						QueryDB query 		 = new QueryDB();	
						
					  // insertScheduleForGroup(con ,query,"West"); //Insert schedule for group
					 	activeSprinklerList = query.getActiveScheduleSprinklerGroup(con);//  , "North", "11/24/2016", "22:50");
						System.out.println(activeSprinklerList.toString());
						
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



