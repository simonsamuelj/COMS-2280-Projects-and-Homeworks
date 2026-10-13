package edu.iastate.cs2280.hw1;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Simon Samuel
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		//TODO: Write your code here.
		for (int r = 0; r < tOld.getLength(); r++) {
			for (int c = 0; c < tOld.getWidth(); c++) {
				tNew.grid[r][c] = tOld.grid[r][c].next(tNew);
			}
		}
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		//TODO: Write/update your code here.
		int profit = 0;
		for (int r = 0; r < town.getLength(); r++) {
			for (int c = 0; c < town.getWidth(); c++) {
				if (town.grid[r][c].who() == State.CASUAL) {
					profit++;
				}
			}
		}
		return profit;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		//TODO: Write your code here.
		Scanner scan = new Scanner(System.in);
		Town town = null;

		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed");
		int choice = scan.nextInt();

		if (choice == 1) {
			System.out.println("Please enter file path:");
			String path = scan.next();
			try {
				town = new Town(path);
			} catch (FileNotFoundException e) {
				System.out.println("File not found: " + path);
				scan.close();
				return;
			}
		} else {
			System.out.println("Provide rows, cols and seed integer separated by spaces:");
			int rows = scan.nextInt();
			int cols = scan.nextInt();
			int seed = scan.nextInt();
			town = new Town(rows, cols);
			town.randomInit(seed);
		}
		scan.close();

		int totalProfit = 0;
		for (int month = 0; month < 12; month++) {
			totalProfit += getProfit(town);
			town = updatePlain(town);
		}

		double percent = 100.0 * totalProfit / (town.getLength() * town.getWidth() * 12);
		System.out.printf("%.2f%%", percent);
	}
}
