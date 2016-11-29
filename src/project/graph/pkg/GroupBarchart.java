package project.graph.pkg;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import project.backend.pkg.DayAndTime;
import project.backend.pkg.WaterConsumptionLog;
import project.db.pkg.ConnectToDB;
import project.db.pkg.QueryDB;

public class GroupBarchart   {
	public static JPanel panel;
	private static  ConnectToDB connectDBCon ;
	private static Connection con ;
	private static QueryDB query ;
	private static ArrayList<WaterConsumptionLog> waterLogList;

    public GroupBarchart(final String title,String month) {
    	panel = new JPanel();
        waterLogList = new ArrayList<WaterConsumptionLog>();
        try {
			connectDBCon = new ConnectToDB();
			con          = connectDBCon.openConnection();
			query		 = new QueryDB();
			showGroupChart(month);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    /**
     * Returns a sample dataset.
     * @return The dataset.
     */
    private static CategoryDataset createGroupDataset(String month) {
        //waterLogList  	= query.getWaterConsumpByGroup(con, month);
    	waterLogList  	= query.getWaterConsumpByGroupSched(con, month);
        connectDBCon.closeConnection(con);
        DefaultCategoryDataset datasetGroup = new DefaultCategoryDataset();
        for(WaterConsumptionLog k : waterLogList)
        	datasetGroup.addValue(k.getTotalWaterConsump(),month, k.getGroup());
        return datasetGroup;       
    }
  
    /**
     * Creates a chart.
     * @param dataset  the dataset.
     * @return The chart.
     */
    private static JFreeChart createGroupChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Group Bar Chart",      // chart title
            "Group",               	  // domain axis label
            "WaterConsumption",       // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips
            false                     // URLs?
        );

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot 
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.magenta, 
            0.0f, 0.0f, Color.lightGray
        );

        renderer.setSeriesPaint(0, gp0);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        ); 
        return chart;   
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     * @return 
     */
  //  public static void main(final String[] args) {
    public  void showGroupChart(String month){
       // GroupBarchart demo = new GroupBarchart("Water Consumption by Month");
        CategoryDataset dataset = createGroupDataset(month);
        JFreeChart chart = createGroupChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 500));
        panel.add(chartPanel);
        //demo.pack();
        //RefineryUtilities.centerFrameOnScreen(demo);
       // demo.setVisible(true);
    }

}

