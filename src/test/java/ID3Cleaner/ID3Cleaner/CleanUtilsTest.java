package ID3Cleaner.ID3Cleaner;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class CleanUtilsTest {

//	@Test
//	public void testCleanUtils() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCleanFiles() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCleanAudioFile() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetCleanFileName() {
		String inputStringHappyCase = "Calvin Harris & Sam Smith - Promises Sonny Fodera Extended Remix-www.groovytunes.org.mp3";
		String inputStringHappyCase2 = "Metodi Hristov - Out Of Control (Original Mix) [TraxCrate.com].mp3";
		String inputStringHappyCase3 = "Queemose - Promise (Dave Pad Remix) [ClapCrate.com].mp3";
		String inputStringHappyCase4 = "Cut Snake - Party Tutorial Original Mix-www.groovytunes.org.mp3";
		String inputStringHappyCase5 = "Eli Brown - Rave Weapon Original Mix Of Unsound Mind-www.groovytunes.org.mp3";
		String inputStringHappyCase7 = "Metodi Hristov - Out Of Control (Original Mix) [TraxCrate.com].mp3";
		String inputStringHappyCase8 = "Queemose - Promise (Dave Pad Remix) [ClapCrate.com].mp3";
		String inputStringHappyCase9 = "Cut Snake - Party Tutorial Original Mix-www.groovytunes.org.mp3";
		String inputStringHappyCase10 = "Eli Brown - Rave Weapon Original Mix Of Unsound Mind-www.groovytunes.org.mp3";
		String inputStringHappyCase11 = "Karretero - Back to Funk (Original Mix) [ClapCrate.me].mp3";

		

		
		String expectedStringHappyCase = "Calvin Harris & Sam Smith - Promises Sonny Fodera Extended Remix.mp3";
		String expectedStringHappyCase2 = "Metodi Hristov - Out Of Control (Original Mix).mp3";
		String expectedStringHappyCase3 = "Queemose - Promise (Dave Pad Remix).mp3";
		String expectedStringHappyCase4 = "Cut Snake - Party Tutorial Original Mix.mp3";
		String expectedStringHappyCase5 = "Eli Brown - Rave Weapon Original Mix Of Unsound Mind.mp3";
		String expectedStringHappyCase7 = "Metodi Hristov - Out Of Control (Original Mix).mp3";
		String expectedStringHappyCase8 = "Queemose - Promise (Dave Pad Remix).mp3";
		String expectedStringHappyCase9 = "Cut Snake - Party Tutorial Original Mix.mp3";
		String expectedStringHappyCase10 = "Eli Brown - Rave Weapon Original Mix Of Unsound Mind.mp3";
		String expectedStringHappyCase11 = "Karretero - Back to Funk (Original Mix).mp3";

		String actualStringHappyCase = CleanUtils.getCleanFileName(inputStringHappyCase);
		String actualStringHappyCase2 = CleanUtils.getCleanFileName(inputStringHappyCase2);
		String actualStringHappyCase3 = CleanUtils.getCleanFileName(inputStringHappyCase3);
		String actualStringHappyCase4 = CleanUtils.getCleanFileName(inputStringHappyCase4);
		String actualStringHappyCase5 = CleanUtils.getCleanFileName(inputStringHappyCase5);
		String actualStringHappyCase7 = CleanUtils.getCleanFileName(inputStringHappyCase7);
		String actualStringHappyCase8 = CleanUtils.getCleanFileName(inputStringHappyCase8);
		String actualStringHappyCase9 = CleanUtils.getCleanFileName(inputStringHappyCase9);
		String actualStringHappyCase10 = CleanUtils.getCleanFileName(inputStringHappyCase10);
		String actualStringHappyCase11 = CleanUtils.getCleanFileName(inputStringHappyCase11);


		final String errorMessage = "Filename does not match expectation";

		assertEquals(errorMessage, expectedStringHappyCase, actualStringHappyCase);
		assertEquals(errorMessage, expectedStringHappyCase2, actualStringHappyCase2);
		assertEquals(errorMessage, expectedStringHappyCase3, actualStringHappyCase3);
		assertEquals(errorMessage, expectedStringHappyCase4, actualStringHappyCase4);
		assertEquals(errorMessage, expectedStringHappyCase5, actualStringHappyCase5);
		assertEquals(errorMessage, expectedStringHappyCase7, actualStringHappyCase7);
		assertEquals(errorMessage, expectedStringHappyCase8, actualStringHappyCase8);
		assertEquals(errorMessage, expectedStringHappyCase9, actualStringHappyCase9);
		assertEquals(errorMessage, expectedStringHappyCase10, actualStringHappyCase10);
		assertEquals(errorMessage, expectedStringHappyCase11, actualStringHappyCase11);
	}

//
	@Test
	public void testStringContainsRubbish() {
		final String inputHappyCase = "Calvin Harris & Sam Smith - Promises Sonny Fodera Extended Remix-www.groovytunes.org.mp3";
		final String inputNegativeCase1 = "Calvin Harris feat. Sam Smith - Promises Sonny Fodera Extended Remix";
		final String inputNegativeCase2 = "Calvin Harris &www Sam Smith - Promises Sonny Fodera Extended Remix";

		final boolean expectedHappyCase = true;
		final boolean expectedNegCase1 = false;
		final boolean expectedNegCase2 = false;

		final boolean actualHappyCase = CleanUtils.stringContainsRubbish(inputHappyCase);
		final boolean actualNegCase1 = CleanUtils.stringContainsRubbish(inputNegativeCase1);
		final boolean actualNegCase2 = CleanUtils.stringContainsRubbish(inputNegativeCase2);

		assertEquals("Expected " + expectedHappyCase + "does not match actual", expectedHappyCase, actualHappyCase);
		assertEquals("Expected " + expectedHappyCase + "does not match actual", expectedNegCase1, actualNegCase1);
		assertEquals("Expected " + expectedHappyCase + "does not match actual", expectedNegCase2, actualNegCase2);
	}

}
