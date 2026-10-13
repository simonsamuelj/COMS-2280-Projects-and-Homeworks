package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

/**
 * @author Simon Samuel
 *
 * Tests for the ISPBusiness class, checked against the 12-month example in the PDF.
 */
public class ISPBusinessTest {

	@Test
	public void testGetProfit() throws FileNotFoundException {
		Town t = new Town("ISP4x4.txt");
		assertEquals(1, ISPBusiness.getProfit(t));
	}

	@Test
	public void testUpdatePlain() throws FileNotFoundException {

		Town t = ISPBusiness.updatePlain(new Town("ISP4x4.txt"));
		String expected = "E E E E\nC C O E\nC O E O\nC E E E";
		assertEquals(expected, t.toString());
	}

	@Test
	public void testProfitEveryMonth() throws FileNotFoundException {

		int[] expected = {1, 4, 12, 3, 0, 8, 5, 1, 2, 8, 9, 0};
		Town t = new Town("ISP4x4.txt");
		for (int month = 0; month < 12; month++) {
			assertEquals(expected[month], ISPBusiness.getProfit(t));
			t = ISPBusiness.updatePlain(t);
		}
	}

	@Test
	public void testMainFromFile() throws FileNotFoundException {

			Town town = new Town("ISP4x4.txt");

			int totalProfit = 0;
			for (int month = 0; month < 12; month++) {
				totalProfit += ISPBusiness.getProfit(town);
				town = ISPBusiness.updatePlain(town);
			}

			double percent = 100.0 * totalProfit / (town.getLength() * town.getWidth() * 12);

			assertEquals(53, totalProfit);
			assertEquals(27.60, percent, 0.01);
		}

	@Test
	public void testMainRandom() {
		Town town = new Town(4, 4);
		town.randomInit(10);

		int totalProfit = 0;
		for (int month = 0; month < 12; month++) {
			totalProfit += ISPBusiness.getProfit(town);
			town = ISPBusiness.updatePlain(town);
		}

		double percent = 100.0 * totalProfit / (town.getLength() * town.getWidth() * 12);

		assertEquals(53, totalProfit);
		assertEquals(27.60, percent, 0.01);
	}
}
