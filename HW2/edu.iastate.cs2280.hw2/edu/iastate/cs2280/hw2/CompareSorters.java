package edu.iastate.cs2280.hw2;

/**
 *  
 * @author Simon Samuel
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{
		PointScanner[] scanners = new PointScanner[4];
		Algorithm[] algorithms = { Algorithm.SelectionSort, Algorithm.InsertionSort,
				Algorithm.MergeSort, Algorithm.QuickSort };

		Scanner stdin = new Scanner(System.in);
		Random rand = new Random();

		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		System.out.println();
		System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");

		int trial = 1;
		while (true)
		{
			System.out.print("Trial " + trial + ": ");
			if (!stdin.hasNextInt())
			{
				if (!stdin.hasNext())
				{
					break;          // end of input
				}
				stdin.next();       // discard a non-integer key and ask again
				continue;
			}
			int key = stdin.nextInt();

			if (key == 1)
			{
				System.out.print("Enter number of random points: ");
				int numPts = stdin.nextInt();
				Point[] pts;
				try
				{
					pts = generateRandomPoints(numPts, rand);
				}
				catch (IllegalArgumentException e)
				{
					System.out.println("Number of points must be at least 1.");
					System.out.println();
					continue;
				}

				// Every scanner gets the same input sequence.
				for (int i = 0; i < scanners.length; i++)
				{
					scanners[i] = new PointScanner(pts, algorithms[i]);
				}
			}
			else if (key == 2)
			{
				System.out.println("Points from a file");
				System.out.print("File name: ");
				String fileName = stdin.next();
				try
				{
					for (int i = 0; i < scanners.length; i++)
					{
						scanners[i] = new PointScanner(fileName, algorithms[i]);
					}
				}
				catch (FileNotFoundException e)
				{
					System.out.println("File not found: " + fileName);
					System.out.println();
					continue;
				}
				catch (InputMismatchException e)
				{
					System.out.println("File contains an odd number of integers: " + fileName);
					System.out.println();
					continue;
				}
			}
			else if (key == 3)
			{
				break;
			}
			else
			{
				System.out.println("Unknown key. Use 1, 2, or 3.");
				System.out.println();
				continue;
			}

			// Scan with each algorithm.
			for (PointScanner s : scanners)
			{
				s.scan();
			}

			// Print the statistics table.
			System.out.println();
			System.out.println(String.format("%-18s%-7s%s", "algorithm", "size", "time (ns)"));
			System.out.println("----------------------------------");
			for (PointScanner s : scanners)
			{
				System.out.println(s.stats());
			}
			System.out.println("----------------------------------");
			System.out.println();

			trial++;
		}
		stdin.close();
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{
		if (numPts < 1)
		{
			throw new IllegalArgumentException("numPts must be at least 1.");
		}
		Point[] pts = new Point[numPts];
		for (int i = 0; i < numPts; i++)
		{
			int x = rand.nextInt(101) - 50;   // -50 .. 50
			int y = rand.nextInt(101) - 50;
			pts[i] = new Point(x, y);
		}
		return pts;
	}
	
}
