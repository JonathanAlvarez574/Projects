package Assignment8;

import java.awt.Point;
import java.text.DecimalFormat;

import javax.swing.SwingUtilities;


public class Plot1
{
   static final boolean debug = true;
   static final boolean outerLoopFlag = false;
   static int LegthofArray;

   static int maxArrayLegth = 50000;

   static int Incrementing_Array_Length = 500;
   static int Num_Times_Loop = maxArrayLegth / Incrementing_Array_Length;

   static int MaxLmitInterval = 300;
   static int IntervalRecLimt = 2;

   static Integer[] randomIntArray;
   static Point[] minPoint;
   static Point[] maxPoint;
   static Point[] answerPoint;

   static DecimalFormat numFormat = new DecimalFormat("#.0000");

   public static Integer[] generateRandIntArray(int arraySize)    ////This part takes an array length and produces a random array of Integers
   {
      Integer[] randIntArray = new Integer[arraySize];
      for (int i = 0; i < arraySize; i++)
      {
         randIntArray[i] = (int) (Math.random() * arraySize / 5);
      }
      return randIntArray;
   }

// Make an Array of doubles to collect the max times to sort different sizes of arrays
   public static Point[] initPointArray(int arrayLength)
   {
      Point[] maxPoint = new Point[Num_Times_Loop];
      for (int p = 0; p < Num_Times_Loop; p++)
      {
         maxPoint[p] = new Point();
      }
      return maxPoint;
   }

//This part generates an Array of Points to plot the recursion Limit:
   public static Point[] generateRecLimitArray(int arrayLength)
   {
      Point[] recLimitsPoint = new Point[Num_Times_Loop];
      for (int p = 0; p < Num_Times_Loop; p++)
      {
         recLimitsPoint[p] = new Point();
      }
      return recLimitsPoint;
   }

   public static void main(String[] args)
   {

      // This sets up timing
      long startTime, estimatedTime;

      FHsort fh = new FHsort();

      int LengthofCount;

      Point[] maxArrayPoint = initPointArray(Num_Times_Loop);
      Point[] minArrayPoint = initPointArray(Num_Times_Loop);
      Point[] recLimitArrayPoint = initPointArray(Num_Times_Loop);

      // This goes through the increasing larger arrays to max
      for (LengthofCount = 1; LengthofCount <= Num_Times_Loop; LengthofCount++)
      {
         // This calculate size of array this is for the iteration
         LegthofArray = Incrementing_Array_Length * LengthofCount;

         Integer[] randIntArray = generateRandIntArray(LegthofArray);

         double maximumD = 0.0;
         double minimumD = 1.0E18; // a ridiculously large number to initialize

         int iterOfMax = 0;

         for (int i = 2; i <= MaxLmitInterval; i = i + 2)
         {

            // recursion limit for loop
            fh.setRecursionLimit(i);


            startTime = System.nanoTime();   // Starts time


            fh.quickSort(randIntArray); //Sorts


            estimatedTime = System.nanoTime() - startTime;  //End time

            if (estimatedTime > maximumD)
            {
               maximumD = estimatedTime;
               iterOfMax = i;
            }
            if (estimatedTime < minimumD)
            {
               minimumD = estimatedTime;

            }

         }


         Point iterationP = new Point();  // gathers the points and iteration from the inner loop
         iterationP.setLocation(LengthofCount, iterOfMax);
         Point maxPoint = new Point();
         maxPoint.setLocation(LengthofCount, maximumD);
         Point minPoint = new Point();
         minPoint.setLocation(LengthofCount, minimumD);


         maxArrayPoint[LengthofCount - 1] = maxPoint; // adds to  point arrays
         minArrayPoint[LengthofCount - 1] = minPoint;
         recLimitArrayPoint[LengthofCount - 1] = iterationP;

         if (debug)
         {
            System.out.println("Maximum ns/M: "

                  + numFormat.format(maximumD / 1000000) + "; iterOfMax: "
                  + iterOfMax + "; arraySize: " + LengthofCount);

            System.out.println("maxArrayP.length " + maxArrayPoint.length);
         }

      }

      SwingUtilities.invokeLater(new Runnable()     // This calls the XYLineChart in a new thread
      {
         @Override
         public void run()
         {
            new LineChart(maxArrayPoint, minArrayPoint, recLimitArrayPoint,
                  maxArrayPoint.length).setVisible(true);
         }
      });
   }
}
