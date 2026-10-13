package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Simon Samuel
 *
 * Tests for the Streamer class.
 */
public class StreamerTest {

	@Test
	public void testWho() {
		Town t = new Town(1, 1);
		t.grid[0][0] = new Streamer(t, 0, 0);
		assertEquals(State.STREAMER, t.grid[0][0].who());
	}

	@Test
	public void testNextRule6aBecomesReseller() {

		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Casual(t, r, c);
			}
		}
		t.grid[1][1] = new Streamer(t, 1, 1);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.RESELLER, result.who());
	}

	@Test
	public void testNextRule2aResellerCausesOutage() {

		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Empty(t, r, c);
			}
		}
		t.grid[1][1] = new Streamer(t, 1, 1);
		t.grid[0][0] = new Reseller(t, 0, 0);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.OUTAGE, result.who());
	}

	@Test
	public void testNextRule2bOutageNeighborLeaves() {

		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Empty(t, r, c);
			}
		}
		t.grid[1][1] = new Streamer(t, 1, 1);
		t.grid[0][0] = new Outage(t, 0, 0);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.EMPTY, result.who());
	}

	@Test
	public void testNextRule7Unchanged() {

		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Empty(t, r, c);
			}
		}
		t.grid[1][1] = new Streamer(t, 1, 1);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.STREAMER, result.who());
	}
}
