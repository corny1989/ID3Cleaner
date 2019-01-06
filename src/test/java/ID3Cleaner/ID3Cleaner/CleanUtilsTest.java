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
    	String inputStringNegativeCase ="Calvin Harris & Sam Smith - Promises Sonny Fodera Extended Remix";
    	
    	String expectedStringHappyCase = "Calvin Harris & Sam Smith - Promises Sonny Fodera Extended Remix.mp3";
    	String expectedStringHappyCase2 = "Metodi Hristov - Out Of Control (Original Mix).mp3";
    	String expectedStringHappyCase3 = "Queemose - Promise (Dave Pad Remix).mp3";
    	String expectedStringNegativeCase ="Calvin Harris & Sam Smith - Promises Sonny Fodera Extended Remix.mp3";
    	
    	
    	String actualStringHappyCase = CleanUtils.getCleanFileName(inputStringHappyCase);
    	String actualStringHappyCase2 = CleanUtils.getCleanFileName(inputStringHappyCase2);
    	String actualStringHappyCase3 = CleanUtils.getCleanFileName(inputStringHappyCase3);
    	String actualStringNegativeCase = CleanUtils.getCleanFileName(inputStringNegativeCase);
    	
    	assertEquals("File name is still containing URL", expectedStringHappyCase, actualStringHappyCase);
    	assertEquals("File name is still containing URL", expectedStringHappyCase2, actualStringHappyCase2);
    	assertEquals("File name is still containing URL", expectedStringHappyCase3, actualStringHappyCase3);
    	assertEquals("File name is not the same as before", expectedStringNegativeCase, actualStringNegativeCase);
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
		
		assertEquals("Expected " + expectedHappyCase + "does not match actual", expectedHappyCase, actualHappyCase );
		assertEquals("Expected " + expectedHappyCase + "does not match actual", expectedNegCase1, actualNegCase1 );
		assertEquals("Expected " + expectedHappyCase + "does not match actual", expectedNegCase2, actualNegCase2 );
	}
	
}
