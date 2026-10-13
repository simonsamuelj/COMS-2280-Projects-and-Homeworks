package edu.iastate.cs2280.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author Simon Samuel
 *  A Town is a length x width grid of TownCell objects. Each position in
 *  the grid holds one of the five cell subclasses.
 */
public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		//TODO: Write your code here.
		this.width = width;
		this.length = length;
		grid = new TownCell[length][width];
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		//TODO: Write your code here.
		Scanner input = new Scanner(new File(inputFileName));
		length = input.nextInt();
		width = input.nextInt();
		grid = new TownCell[length][width];


		for (int r = 0; r < length; r++) {
			for (int c = 0; c < width; c++) {
				char letter = input.next().charAt(0);
				grid[r][c] = cellFromLetter(letter, r, c);
			}
		}

		input.close();

	}
	
	/**
	 * Returns width of the grid.
	 * @return
	 */
	public int getWidth() {
		//TODO: Write/update your code here.
		return width;
	}
	
	/**
	 * Returns length of the grid.
	 * @return
	 */
	public int getLength() {
		//TODO: Write/update your code here.
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		for (int r = 0; r < length; r++) {
			for (int c = 0; c < width; c++) {
				int type = rand.nextInt(TownCell.NUM_CELL_TYPE);
				grid[r][c] = cellFromIndex(type, r, c);


			}
		}
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		//TODO: Write your code here.
		for (int r = 0; r < length; r++) {
			for (int c = 0; c < width; c++) {

				s += grid[r][c].who().name().charAt(0);
				if (c < width - 1) {
					s += " ";
				}
			}
			if (r < length - 1) {
				s += "\n";
			}
		}
		return s;
	}

	/**
	 * Helper method, made to build the TownCell method using the files.
	 * @param letter, different cell types, based on their constant value.
	 * @param r, row of cell.
	 * @param c, column of cell.
	 **/
	private TownCell cellFromLetter(char letter, int r, int c) {
        if (letter == 'C') {
            return new Casual(this, r, c);
        } else if (letter == 'S') {
            return new Streamer(this, r, c);
        } else if (letter == 'R') {
            return new Reseller(this, r, c);
        } else if (letter == 'E') {
            return new Empty(this, r, c);
        }
        return new Outage(this, r, c);
    }

	/**
	 * Helper method, made to build the TownCell method using the random seed function.
	 * @param type, different cell types, based on their constant value.
	 * @param r, row of cell.
	 * @param c, column of cell.
	 **/
	private TownCell cellFromIndex(int type, int r, int c) {
        if (type == TownCell.RESELLER) {
            return new Reseller(this, r, c);
        } else if (type == TownCell.EMPTY) {
            return new Empty(this, r, c);
        } else if (type == TownCell.CASUAL) {
            return new Casual(this, r, c);
        } else if (type == TownCell.OUTAGE) {
            return new Outage(this, r, c);
        }
        return new Streamer(this, r, c);
    }
}
