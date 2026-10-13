package edu.iastate.cs2280.hw1;

/**
 * 
 * @author Simon Samuel
 * TownCell is the abstract parent of every kind of cell in the town grid
 * (Casual, Streamer, Reseller, Empty, Outage). It stores where the cell is
 * (row, col) and which town it belongs to (plain), and it provides the
 * census() method that every subclass uses to count its neighbors.
 * Each subclass must say what it is (who()) and what it turns into in the
 * next billing cycle (next()).
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;

	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *
	 * @param nCensus counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 

		//TODO: Write your code here.
		for (int r = row - 1; r <= row + 1; r++) {
			for (int c = col - 1; c <= col + 1; c++) {


				boolean isSelf = (r == row && c == col);
				boolean inside = (r >= 0 && r < plain.getLength() && c >= 0 && c < plain.getWidth());

				if (!isSelf && inside) {
					State neighbor = plain.grid[r][c].who();

					if (neighbor == State.RESELLER) {
						nCensus[RESELLER]++;
					} else if (neighbor == State.EMPTY) {
						nCensus[EMPTY]++;
					} else if (neighbor == State.CASUAL) {
						nCensus[CASUAL]++;
					} else if (neighbor == State.OUTAGE) {
						nCensus[OUTAGE]++;
					} else {
						nCensus[STREAMER]++;
					}
				}
			}
		}
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
}
