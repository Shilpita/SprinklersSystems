package project.graph.pkg;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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

public class MonthlyBarchart extends ApplicationFrame {
	private static  ConnectToDB connectDBCon ;
	private static Connection con ;
	private static QueryDB query ;
	private static ArrayList<WaterConsumptionLog> waterLogList;

    public MonthlyBarchart(final String title) {
        super(title);
        waterLogList = new ArrayList<WaterConsumptionLog>();
        try {
			connectDBCon = new ConnectToDB();
			con          = connectDBCon.openConnection();
			query		 = new QueryDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    /**
     * Returns a sample dataset.
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {

        waterLogList  	= query.getMonthlyWaterConsump(con);
        connectDBCon.closeConnection(con);
        DayAndTime dayTime  = new DayAndTime();
        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(WaterConsumptionLog k : waterLogList)
        	dataset.addValue(k.getTotalWaterConsump(), k.getGroup(), k.getMonth());
        return dataset;       
    }
    
    /**
     * Creates a sample chart.
     * @param dataset  the dataset.
     * @return The chart.
     */
    private static JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Monthly Bar Chart",      // chart title
            "Month",                  // domain axis label
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
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp3 = new GradientPaint(
                0.0f, 0.0f, Color.magenta, 
                0.0f, 0.0f, Color.lightGray
        );
        
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        renderer.setSeriesPaint(3, gp3);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        return chart;  
    }

    /**
     * Starting point for the demonstration application.
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        MonthlyBarchart demo = new MonthlyBarchart("Monthly BarChart");
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 500));
        demo.setContentPane(chartPanel);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}

