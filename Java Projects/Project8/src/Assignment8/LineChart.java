package Assignment8;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


 // This program demonstrates how to draw XY line chart with JfreeChart

public class LineChart extends JFrame
{

   protected Point[] maxPoints;
   protected Point[] minPoints;
   protected Point[] recussionLimit;
   protected double[] x =
   {};
   protected double[] y =
   {};
   protected static String stringForTitle = "";
   protected String truncatedSize = "";
   protected static boolean DEBUG = false;


   public LineChart(double[] x, double[] y, Integer arraysize) // Generates line chart
   {
      super("For Array size = " + stringForTitle.toString());

      this.x = x;
      this.y = y;
      this.stringForTitle = Integer.toString(arraysize);

      JPanel chartPanel = createChartPanel();
      add(chartPanel, BorderLayout.CENTER);

      setSize(640, 480);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
   }


   public LineChart(Point[] maxArray, Point[] minArray, Point[] recLimit, // Constructor for point array
         Integer arraysize)
   {
      super("For Array size = " + stringForTitle.toString());

      this.maxPoints = maxArray.clone();
      this.minPoints = minArray.clone();
      this.recussionLimit = recLimit.clone();

      this.stringForTitle = Integer.toString(arraysize);

      JPanel chartPanel = createChartPanelP();
      add(chartPanel, BorderLayout.CENTER);

      setSize(640, 480);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
   }

   private JPanel createChartPanel() // Chart Panel created
   {
      String chartTitle = "Array size = " + this.stringForTitle;
      String xAxisLabel = "Recursion Limit ";
      String yAxisLabel = "Array Size";

      XYDataset dataset = createDataset();

      JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel,
            yAxisLabel, dataset);

      return new ChartPanel(chart);
   }


   private JPanel createChartPanelP()
   {
      String chartTitle = "The Array Size vs The Max, Min and Rec Limit ";
      String xAxisLabel = "The Array Size [ x 1000] ";
      String yAxisLabel = "The Max and Min of sort (nanoSecs / 100 K); Rec Limit of Max";

      XYDataset dataset = createDatasetP();

      JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel,
            yAxisLabel, dataset);

      return new ChartPanel(chart);
   }


   private XYDataset createDataset()
   {

      XYSeriesCollection dataset = new XYSeriesCollection();
      XYSeries series1 = new XYSeries("Recursion Limit");


      for (int i = 0; i < x.length; i++)
      {
         series1.add(x[i], y[i]);
      }

      dataset.addSeries(series1);

      return dataset;
   }

   private XYDataset createDatasetP() // Creates data set
   {

      XYSeriesCollection dataset = new XYSeriesCollection();
      XYSeries series1 = new XYSeries("Max Sort Time:");
      XYSeries series2 = new XYSeries("Min Sort Time:");
      XYSeries series3 = new XYSeries("Recursion Limit:");

      for (int a = 0; a < maxPoints.length; a++)
      {
         series1.add(maxPoints[a].getX() * 0.5, maxPoints[a].getY() / 100000);
         series2.add(minPoints[a].getX() * 0.5, minPoints[a].getY() / 100000);
         series3.add(recussionLimit[a].getX() * 0.5, recussionLimit[a].getY());
         if (DEBUG)
         {
            System.out.println("maxPoints:" + maxPoints[a].getX() * 20 + " "
                  + maxPoints[a].getY() / 100000);
            System.out.println("minPoints:" + minPoints[a].getX() * 20 + " "
                  + minPoints[a].getY() / 100000);
            System.out.println("recursionLimit: " + recussionLimit[a].getX() * 20 + " "
                  + recussionLimit[a].getY());
         }
      }

      dataset.addSeries(series1);

      dataset.addSeries(series2);

      dataset.addSeries(series3);

      return dataset;
   }

}
