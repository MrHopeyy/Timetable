package Tests;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import Model.Main;

public class FilesShouldBeComparedAndMatched {

	@SuppressWarnings("deprecation")
	@Test
	public void testNoFileSelected() throws IOException {

		String[] actual = Main.importCohort(" ");
		String[] expected = null;
		assertEquals(actual, expected);

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testWrongFileSelected() throws IOException {

		String[] actual = Main.importCohort("TestFiles/Cohort2.txt");
		String[] expected = { " " };
		assertEquals(actual, expected);

	}

	@Test
	public void testWrongAmountElementsAdded() throws IOException {

		int actual = Main.importCohort("TestFiles/Cohort2.txt").length;
		int expected = 0;
		assertEquals(actual, expected);

	}

	@Test
	public void testCorrectAmountElementsShouldBeAdded() throws IOException {

		int actual = Main.importCohort("./TestFiles/Cohort.csv").length;
		int expected = 5;
		assertEquals(actual, expected);

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testContentsElementsShouldBeAdded() throws IOException {

		String[] actual = Main.importCohort("./TestFiles/Cohort.csv");
		String[] expected = { "CS1050", "CS1310", "CS2020", "CS2160", "CS3360" };
		assertEquals(actual, expected);

	}

}
