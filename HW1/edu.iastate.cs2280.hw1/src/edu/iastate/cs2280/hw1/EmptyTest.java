package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Simon Samuel
 * Tests for the Empty class. Each next() test uses a 3 x 3 town with
 * the Empty cell in the middle (1,1).
 **/
public class EmptyTest {

	@Test
	public void testWho() {
		Town t = new Town(1, 1);
		t.grid[0][0] = new Empty(t, 0, 0);
		assertEquals(State.EMPTY, t.grid[0][0].who());
	}

	@Test
	public void testNext6a() {

		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Casual(t, r, c);
			}
		}
		t.grid[1][1] = new Empty(t, 1, 1);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.RESELLER, result.who());
	}

	@Test
	public void testNextRule5() {

		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Empty(t, r, c);
			}
		}
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.CASUAL, result.who());
	}
}
