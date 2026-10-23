package edu.iastate.cs2280.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 *  
 * @author Simon Samuel
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "mergesort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		int n = pts.length;
		if (n <= 1)
		{
			return;   //an array of 0 or 1 elements is already sorted.
		}

		// copy the two halves into new arrays.
		int mid = n / 2;
		Point[] left = new Point[mid];
		Point[] right = new Point[n - mid];
		for (int i = 0; i < mid; i++)
		{
			left[i] = pts[i];
		}
		for (int i = mid; i < n; i++)
		{
			right[i - mid] = pts[i];
		}

		//sort each half recursively.
		mergeSortRec(left);
		mergeSortRec(right);

		//merge the sorted halves.
		// then copy the result back into pts.
		Point[] merged = merge(left, right);
		for (int i = 0; i < n; i++)
		{
			pts[i] = merged[i];
		}
	}


	/**
	 * This method provides the sorting for merge sort, takes each recursively divided array
	 * then splices them back together based on which one has the smallest element at a set index.
	 * It also takes into account for if one of the arrays has yet to be iterated through.
	 *
	 * @param b  sorted array
	 * @param c  sorted array
	 * @return   sorted array containing every element of b and c
	 */
	private Point[] merge(Point[] b, Point[] c)
	{
		int p = b.length;
		int q = c.length;
		Point[] d = new Point[p + q];
		int i = 0, j = 0, k = 0;

		while (i < p && j < q)
		{
			if (pointComparator.compare(b[i], c[j]) <= 0)
			{
				d[k++] = b[i++];
			}
			else
			{
				d[k++] = c[j++];
			}
		}

		// this appends the remaining array that hasn't been fully iterated through.
		if (i >= p)
		{
			while (j < q)
			{
				d[k++] = c[j++];
			}
		}
		else
		{
			while (i < p)
			{
				d[k++] = b[i++];
			}
		}
		return d;
	}

}
