package edu.iastate.cs2280.hw2;

/**
 * 
 * @author Simon Samuel
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds.

	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if (pts == null || pts.length == 0)
		{
			throw new IllegalArgumentException("Point array must be non-null and non-empty.");
		}
		points = new Point[pts.length];
		for (int i = 0; i < pts.length; i++)
		{
			points[i] = new Point(pts[i]);
		}
		sortingAlgorithm = algo;
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		sortingAlgorithm = algo;

		// Read every integer in the file, skip anything that is not an integer.
		ArrayList<Integer> values = new ArrayList<Integer>();
		Scanner in = new Scanner(new File(inputFileName));   // throws FileNotFoundException
		try
		{
			while (in.hasNext())
			{
				if (in.hasNextInt())
				{
					values.add(in.nextInt());
				}
				else
				{
					in.next();
				}
			}
		}
		finally
		{
			in.close();
		}

		if (values.size() % 2 != 0)
		{
			throw new InputMismatchException("File " + inputFileName + " contains an odd number of integers.");
		}

		// Each consecutive pair of integers (x, y) is one point.
		points = new Point[values.size() / 2];
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(values.get(2 * i), values.get(2 * i + 1));
		}
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 *
	 */
	public void scan()
	{
		AbstractSorter aSorter;

		// Create the sorter for this scanner's algorithm. The static type is AbstractSorter;
		// the dynamic type decides which sort() runs (dynamic binding).
		if (sortingAlgorithm == Algorithm.SelectionSort)
		{
			aSorter = new SelectionSorter(points);
		}
		else if (sortingAlgorithm == Algorithm.InsertionSort)
		{
			aSorter = new InsertionSorter(points);
		}
		else if (sortingAlgorithm == Algorithm.MergeSort)
		{
			aSorter = new MergeSorter(points);
		}
		else
		{
			aSorter = new QuickSorter(points);
		}

		//sort by x-coordinate.
		aSorter.setComparator(0);
		long start = System.nanoTime();
		aSorter.sort();
		long xTime = System.nanoTime() - start;
		int medianX = aSorter.getMedian().getX();

		//sort by y-coordinate.
		aSorter.setComparator(1);
		start = System.nanoTime();
		aSorter.sort();
		long yTime = System.nanoTime() - start;
		int medianY = aSorter.getMedian().getY();

		medianCoordinatePoint = new Point(medianX, medianY);
		scanTime = xTime + yTime;

		// Keep the sorted order in this scanner's array.
		aSorter.getPoints(points);
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		return String.format("%-18s%-7d%d", sortingAlgorithm, points.length, scanTime);
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		return "MCP: " + medianCoordinatePoint;
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter("MCP.txt");
		out.println(toString());
		out.close();
	}	

	

		
}
