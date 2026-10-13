package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

/**
 * @author Simon Samuel
 *
 * Tests for the Town class.
 */
public class TownTest {

	@Test
	public void testSizeConstructor() {
		Town t = new Town(3, 5);
		assertEquals(3, t.getLength());
		assertEquals(5, t.getWidth());
		assertNotNull(t.grid);
		assertEquals(3, t.grid.length);
		assertEquals(5, t.grid[0].length);
		assertNull(t.grid[0][0]);
	}

	@Test
	public void testFileConstructor() throws FileNotFoundException {
		Town t = new Town("ISP4x4.txt");
		assertEquals(4, t.getLength());
		assertEquals(4, t.getWidth());
		assertEquals(State.OUTAGE, t.grid[0][0].who());
		assertEquals(State.RESELLER, t.grid[0][1].who());
		assertEquals(State.EMPTY, t.grid[1][0].who());
		assertEquals(State.CASUAL, t.grid[1][2].who());
		assertEquals(State.STREAMER, t.grid[2][1].who());
	}

	@Test
	public void testFileMissingFile() {
		try {
			new Town("no_such_file.txt");
			fail("Expected FileNotFoundException");
		} catch (FileNotFoundException e) {
			// expected: the test passes
		}
	}

	@Test
	public void testGetLengthAndWidth() {
		// A non-square grid catches length and width being swapped
		Town t = new Town(2, 7);
		assertEquals(2, t.getLength());
		assertEquals(7, t.getWidth());
	}

	@Test
	public void testRandomInit() {
		Town t = new Town(4, 4);
		t.randomInit(10);
		String expected = "O R O R\nE E C O\nE S O S\nE O R R";
		assertEquals(expected, t.toString());
	}

	@Test
	public void testToString() throws FileNotFoundException {
		Town t = new Town("ISP4x4.txt");
		String expected = "O R O R\nE E C O\nE S O S\nE O R R";
		assertEquals(expected, t.toString());
	}
}
