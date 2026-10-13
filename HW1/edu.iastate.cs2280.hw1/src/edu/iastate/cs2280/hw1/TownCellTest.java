package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

/**
 * @author Simon Samuel
 *
 * Tests for the TownCell class. Uses ISP4x4.txt:
 */
public class TownCellTest {

	@Test
	public void testCensusCorner() throws FileNotFoundException {

		Town t = new Town("ISP4x4.txt");
		int[] counts = new int[TownCell.NUM_CELL_TYPE];
		t.grid[0][0].census(counts);
		assertEquals(1, counts[TownCell.RESELLER]);
		assertEquals(2, counts[TownCell.EMPTY]);
		assertEquals(0, counts[TownCell.CASUAL]);
		assertEquals(0, counts[TownCell.OUTAGE]);
		assertEquals(0, counts[TownCell.STREAMER]);
	}

	@Test
	public void testCensusMiddle() throws FileNotFoundException {
		Town t = new Town("ISP4x4.txt");
		int[] counts = new int[TownCell.NUM_CELL_TYPE];
		t.grid[1][1].census(counts);
		assertEquals(1, counts[TownCell.RESELLER]);
		assertEquals(2, counts[TownCell.EMPTY]);
		assertEquals(1, counts[TownCell.CASUAL]);
		assertEquals(3, counts[TownCell.OUTAGE]);
		assertEquals(1, counts[TownCell.STREAMER]);
	}

	@Test
	public void testCensusBottom() throws FileNotFoundException {
		Town t = new Town("ISP4x4.txt");
		int[] counts = new int[TownCell.NUM_CELL_TYPE];
		t.grid[3][2].census(counts);
		assertEquals(1, counts[TownCell.RESELLER]);
		assertEquals(0, counts[TownCell.EMPTY]);
		assertEquals(0, counts[TownCell.CASUAL]);
		assertEquals(2, counts[TownCell.OUTAGE]);
		assertEquals(2, counts[TownCell.STREAMER]);
	}

	@Test
	public void testCensusResets() {
		Town t = new Town(1, 2);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		int[] counts = {9, 9, 9, 9, 9};
		t.grid[0][0].census(counts);
		assertEquals(0, counts[TownCell.RESELLER]);
		assertEquals(0, counts[TownCell.EMPTY]);
		assertEquals(1, counts[TownCell.CASUAL]);
		assertEquals(0, counts[TownCell.OUTAGE]);
		assertEquals(0, counts[TownCell.STREAMER]);
	}
}
