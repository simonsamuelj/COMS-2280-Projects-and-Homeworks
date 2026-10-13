package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Simon Samuel
 *
 * Tests for the Casual class.
 */
public class CasualTest {

	@Test
	public void testWho() {
		Town t = new Town(1, 1);
		t.grid[0][0] = new Casual(t, 0, 0);
		assertEquals(State.CASUAL, t.grid[0][0].who());
	}

	@Test
	public void testNextRule6a() {
		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Casual(t, r, c);
			}
		}
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.RESELLER, result.who());
	}

	@Test
	public void testNextRule1a() {
		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Empty(t, r, c);
			}
		}
		t.grid[1][1] = new Casual(t, 1, 1);
		t.grid[0][0] = new Reseller(t, 0, 0);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.OUTAGE, result.who());
	}

	@Test
	public void testNextRule1b() {
		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Empty(t, r, c);
			}
		}
		t.grid[1][1] = new Casual(t, 1, 1);
		t.grid[0][0] = new Streamer(t, 0, 0);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.STREAMER, result.who());
	}

	@Test
	public void testNextRule6b() {

		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Casual(t, r, c);
			}
		}
		t.grid[0][0] = new Empty(t, 0, 0);
		t.grid[0][1] = new Empty(t, 0, 1);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.STREAMER, result.who());
	}

	@Test
	public void testNextRule7() {

		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Empty(t, r, c);
			}
		}
		t.grid[1][1] = new Casual(t, 1, 1);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.CASUAL, result.who());
	}

	@Test
	public void testNextPositionAndTOwn() {

		Town tOld = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				tOld.grid[r][c] = new Empty(tOld, r, c);
			}
		}
		tOld.grid[1][1] = new Casual(tOld, 1, 1);
		Town tNew = new Town(3, 3);
		TownCell result = tOld.grid[1][1].next(tNew);
		assertEquals(1, result.row);
		assertEquals(1, result.col);
		assertTrue(result.plain == tNew);
	}
}
