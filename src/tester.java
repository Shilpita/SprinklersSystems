import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Map.Entry;

import project.backend.pkg.Sprinkler;
import project.db.pkg.InsertToDB;
import project.db.pkg.InsertToSchedule;
import project.db.pkg.QueryDB;

public class Tester {
		private static  InsertToDB insertDBCon ;
		private static Connection con ;
		private static ArrayList<Sprinkler> activeSprinklerList ;
		public Tester(){
			activeSprinklerList = new ArrayList<Sprinkler>();
		}
		
        public static void insertScheduleForGroup(Connection con,QueryDB query,String group){
		
			       InsertToSchedule insertSchedule = new InsertToSchedule();
			       ArrayList<String> sprinkler	   = query.getAllSprinklers(con ,group);

			       // Display elements and insert schedule per sprinkler in group 
					for(String i : sprinkler) {
								System.out.println(i);
					           //insert schedule in Db
					           insertSchedule.processInsertSchedQuery(con,"Test5" , i ,group ,"LOW"
								   									 ,"11/1/2016", "11/30/2016"
								   									 ,"21", "30"
								   									 ,"23", "30" );
					}
       }//end insert schedule method
	    
 	    
       	public static void main(String[] args) throws ParseException {
		
				try {
						insertDBCon     	 = new InsertToDB();
						con  				 = insertDBCon.openConnection();
						QueryDB query 		 = new QueryDB();	
						
					  //  insertScheduleForGroup(con ,query,"North"); //Insert schedule for group
					 	activeSprinklerList = query.getActiveScheduleSprinklerGroup(con, "North", "11/23/2016", "22:50");
						System.out.println(activeSprinklerList.toString());
						
				} catch (ClassNotFoundException e) {
						e.printStackTrace();
				} catch (SQLException e) {
						e.printStackTrace();
				}finally {
					if (con != null)
						   insertDBCon.closeConnection(con);
			    }

       	}//end main()
	
       	
}//end class



