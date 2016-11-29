package project.UI.pkg;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import project.db.pkg.ConnectToDB;
import project.db.pkg.QueryDB;

public class SprinklerStatus {
	protected JPanel panel;
	private JPanel north;
	private JLabel N1, N2, N3, N4;

	private JPanel south;
	private JLabel S1, S2, S3, S4;

	private JPanel east;
	private JLabel E1, E2, E3, E4;

	private JPanel west;
	private JLabel W1, W2, W3, W4;
	
	private static  ConnectToDB connectDBCon ;
	private static Connection con ;
	private static QueryDB query;
	private static String[] groups ={"North","South","East","West"};
	
	public SprinklerStatus() {
		initializeDB();
		initialize();
	}

	private void initializeDB() {
		try {
			connectDBCon     	 = new ConnectToDB();
			con  				 = connectDBCon.openConnection();
			query				 = new QueryDB();
			for(String i : groups){
				    query.getAllSprinklers(con ,i);
				    System.out.println(QueryDB.sprinklerStatusMap.size());
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
		if (con != null)
			   connectDBCon.closeConnection(con);
		}
		
	}

	private void initialize() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(300, 400));
		
		north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.PAGE_AXIS));
		north.setMaximumSize(new Dimension(300, 100));
		north.setBorder(new TitledBorder("North Sector Sprinkler"));
		N1 = new JLabel("N1: 	"+QueryDB.sprinklerStatusMap.get("N1"));
		N2 = new JLabel("N2: 	"+QueryDB.sprinklerStatusMap.get("N2"));
		N3 = new JLabel("N3: 	"+QueryDB.sprinklerStatusMap.get("N3"));
		N4 = new JLabel("N4: 	"+QueryDB.sprinklerStatusMap.get("N4"));
		
		north.add(N1);		
		north.add(N2);		
		north.add(N3);		
		north.add(N4);	
		
		panel.add(north);
		
		south = new JPanel();
		south.setLayout(new BoxLayout(south, BoxLayout.PAGE_AXIS));
		south.setMaximumSize(new Dimension(300, 100));
		south.setBorder(new TitledBorder("South Sector Sprinkler"));
		S1 = new JLabel("S1: 	"+QueryDB.sprinklerStatusMap.get("S1"));
		S2 = new JLabel("S2: 	"+QueryDB.sprinklerStatusMap.get("S2"));
		S3 = new JLabel("S3: 	"+QueryDB.sprinklerStatusMap.get("S3"));
		S4 = new JLabel("S4: 	"+QueryDB.sprinklerStatusMap.get("S4"));
		
		south.add(S1);		
		south.add(S2);		
		south.add(S3);		
		south.add(S4);
		panel.add(south);
		
		east  = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.PAGE_AXIS));
		east.setMaximumSize(new Dimension(300, 100));
		east.setBorder(new TitledBorder("East Sector Sprinkler"));
		E1 = new JLabel("E1: 	"+QueryDB.sprinklerStatusMap.get("E1"));
		E2 = new JLabel("E2: 	"+QueryDB.sprinklerStatusMap.get("E2"));
		E3 = new JLabel("E3: 	"+QueryDB.sprinklerStatusMap.get("E3"));
		E4 = new JLabel("E4: 	"+QueryDB.sprinklerStatusMap.get("E4"));

		east.add(E1);		
		east.add(E2);		
		east.add(E3);		
		east.add(E4);		
		panel.add(east);
		
		west  = new JPanel();
		west.setLayout(new BoxLayout(west, BoxLayout.PAGE_AXIS));
		west.setMaximumSize(new Dimension(300, 100));
		west.setBorder(new TitledBorder("West Sector Sprinkler"));
		W1 = new JLabel("W1: 	"+QueryDB.sprinklerStatusMap.get("W1"));
		W2 = new JLabel("W2: 	"+QueryDB.sprinklerStatusMap.get("W2"));
		W3 = new JLabel("W3: 	"+QueryDB.sprinklerStatusMap.get("W3"));
		W4 = new JLabel("W4: 	"+QueryDB.sprinklerStatusMap.get("W4"));

		west.add(W1);		
		west.add(W2);		
		west.add(W3);		
		west.add(W4);
		panel.add(west);
		
	}
	
	

}
