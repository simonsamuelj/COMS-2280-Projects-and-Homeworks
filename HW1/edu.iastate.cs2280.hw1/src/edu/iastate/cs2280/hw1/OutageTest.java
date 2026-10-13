package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Simon Samuel
 *
 * Tests for the Outage class.
 */
public class OutageTest {

	@Test
	public void testWho() {
		Town t = new Town(1, 1);
		t.grid[0][0] = new Outage(t, 0, 0);
		assertEquals(State.OUTAGE, t.grid[0][0].who());
	}

	@Test
	public void testNextRule4() {

		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Casual(t, r, c);
			}
		}
		t.grid[1][1] = new Outage(t, 1, 1);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.EMPTY, result.who());
	}
}
