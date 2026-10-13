package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Simon SAmuel
 *
 * Tests for the Reseller class.
 */
public class ResellerTest {

	@Test
	public void testWho() {
		Town t = new Town(1, 1);
		t.grid[0][0] = new Reseller(t, 0, 0);
		assertEquals(State.RESELLER, t.grid[0][0].who());
	}

	@Test
	public void testNextRule3aFewCasualLeaves() {
		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Empty(t, r, c);
			}
		}
		t.grid[1][1] = new Reseller(t, 1, 1);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[0][2] = new Casual(t, 0, 2);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.EMPTY, result.who());
	}

	@Test
	public void testNextRule3bManyEmptyLeaves() {

		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Empty(t, r, c);
			}
		}
		t.grid[1][1] = new Reseller(t, 1, 1);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[0][2] = new Casual(t, 0, 2);
		t.grid[1][0] = new Casual(t, 1, 0);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.EMPTY, result.who());
	}

	@Test
	public void testNextRule6bBecomesStreamer() {

		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Casual(t, r, c);
			}
		}
		t.grid[1][1] = new Reseller(t, 1, 1);
		t.grid[2][1] = new Outage(t, 2, 1);
		t.grid[2][2] = new Outage(t, 2, 2);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.STREAMER, result.who());
	}

	@Test
	public void testNextRule7UnchangedAndRule6aDoesNotApply() {

		Town t = new Town(3, 3);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				t.grid[r][c] = new Streamer(t, r, c);
			}
		}
		t.grid[1][1] = new Reseller(t, 1, 1);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[0][2] = new Casual(t, 0, 2);
		t.grid[1][0] = new Casual(t, 1, 0);
		TownCell result = t.grid[1][1].next(new Town(3, 3));
		assertEquals(State.RESELLER, result.who());
	}
}
