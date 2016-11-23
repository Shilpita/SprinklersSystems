package project.db.pkg;

import java.io.*;
import java.sql.*;


public class InsertGroupSprinklers {
	private static String file ;
	private static String line ;
	private static  InsertToDB insertDBCon;
	private static Connection con ;
	private PreparedStatement preparedStatement ;
	private String insertStmt ;
	
	public InsertGroupSprinklers() {
		this.file = "SprinklerGroup.txt";
		this.line = null;
	}
	
	public void insertGroup(String[] tokens){
			try {
					insertStmt              = "INSERT INTO SPRINKLER_GROUP VALUES(?,?,?,?,?)" ;
			
					preparedStatement       = con.prepareStatement(insertStmt);
					preparedStatement.setString(1, tokens[0]);
					preparedStatement.setString(2, tokens[1]);
					preparedStatement.setString(3, tokens[2]);
					preparedStatement.setString(4, tokens[3]);
					preparedStatement.setString(5, tokens[4]);
					
					preparedStatement.executeUpdate();
					preparedStatement.close();
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
	}
	
	public static void main(String[] args) { 
		   InsertGroupSprinklers grp = new InsertGroupSprinklers();
	       try {
	    	   	con             = insertDBCon.openConnection();
	    	   	FileInputStream fstream  = new FileInputStream(file);
	    	   	BufferedReader br 		 = new BufferedReader(new InputStreamReader(fstream));
	    	   	String delimiter = ",";
	    	  //Read File Line By Line
                while ((line = br.readLine()) != null)   {
								String[] tokens = line.split(delimiter);
								grp.insertGroup(tokens);
                }
	       } catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException | SQLException ex) {
				ex.printStackTrace();
			} finally {
					if (con != null)
					   insertDBCon.closeConnection(con);
			}
	}
}
