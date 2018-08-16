package Assignment8;

import java.awt.Point;
import java.util.Random;

import javax.swing.SwingUtilities;

import cs1c.TimeConverter;


public class Plot2 {

    static final boolean InnerLoppFlog = true; // debug flag
    static int arraySize;  //  This provides an array size to evaluate
    static Integer[] largeArray; // This holds randomly produced integers to be sorted
    static Point[] minPoint;
    static Point[] maxPoint;
    static Point[] answerPoint;
    static int MaxLmitInterval = 300; // maximum value of the recursionlimit to be tested
    static int IntervalRecLimt = 2; //interval between rec Limits to be tested

    public static Integer[] generateArray(int arraySize) // Takes an array size as an integer and returns an array of random integers
    {
        Integer[] intArray = new Integer[arraySize];
        for (int i = 0; i < arraySize; i++) {
            intArray[i] = (int) (Math.random() * arraySize / 5);
        }
        return intArray;
    }

    public static void main(String[] args) {

        arraySize = 10000000;

        long startTime, estimatedTime;

        FHsort fh = new FHsort();

        double[] answersX = new double[arraySize];  // make two arrays to collect answers
        double[] answersY = new double[arraySize];
        answerPoint = new Point[arraySize];

        Point maxPoint = new Point();
        Point minPoint = new Point();

        Integer arrayLength = arraySize;


        double answerX = 0.0;  // These doubles catch a single answers
        double answerY = 0.0;

        largeArray = generateArray(arraySize);

        // This goes through the array at various recursion limits
        for (int a = 2, b = 0; a <= MaxLmitInterval; a = a + IntervalRecLimt, b++) {
            Point point = new Point();

            fh.setRecursionLimit(a);

            // capture start time
            startTime = System.nanoTime();

            fh.quickSort(largeArray);

            estimatedTime = System.nanoTime() - startTime; // Calc Est time

            answerX = (double) a;
            answerY = (double) estimatedTime / 10000;

            if (InnerLoppFlog) {

                System.out.println("For the RecursionLimit " + a + " "
                        + TimeConverter.convertTimeToString(estimatedTime));
            }
            point.setLocation(answerX, answerY);

            answerPoint[b] = point;
            if (a == 2) {
                maxPoint.setLocation(answerX, answerY);
                minPoint.setLocation(answerX, answerY);

            }
            if (maxPoint.getY() < answerY)
                maxPoint.setLocation(answerX, answerY);
            if (minPoint.getY() > answerY)
                minPoint.setLocation(answerX, answerY);
            answersX[b] = answerX;
            answersY[b] = answerY;

        }


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LineChart(answersX, answersY, arrayLength)
                        .setVisible(true);
            }
        });
        System.out.println("max = " + maxPoint.getY() + " at Recursion Limit: "
                + maxPoint.getX());
        System.out.println("min = " + minPoint.getY() + " at Recursion Limit: "
                + minPoint.getX());


    }
}
