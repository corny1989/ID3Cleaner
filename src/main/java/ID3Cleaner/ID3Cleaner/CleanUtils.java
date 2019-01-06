package ID3Cleaner.ID3Cleaner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public final class CleanUtils {
	
	private static final Logger logger = Logger.getLogger(App.class.getName());
	
	public CleanUtils() {
		
	}

    public static void cleanFiles(File[] files) {
        
    	logger.info("Starting to clean files");
    	
    	
    	for (File file : files) {
    	
    		if (file.isDirectory()) {
    			System.out.println("Directory: " + file.getName());
    			cleanFiles(file.listFiles());
    	}
    		else {
    			
    			try {
    				logger.info("Trying to clean file: " + file.getName() );
					cleanAudioFile(file);
					renameFile(file);
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.info("Whoops. Something went wrong! ...");
					e.printStackTrace();
				} 
    			
    		}
    
    }
    	
    }
    
    private static void renameFile(final File file) {
    	
    	
    	
    	final File fileOld = file;

    	final File fileNew = new File(getCleanFileName(file.toString()) + ".mp3");
    	
    	logger.info("Trying to rename file: " + file.getName());
    	
    	try {
    	if (fileNew.exists()){
    		logger.info("Whoops. Something went wrong. Filename probably already exists.");
    		throw new java.io.IOException();
    	}
    	
    	final boolean success = fileOld.renameTo(fileNew);
    	if (success) {
    		logger.info("Renaming OK.");
    	}
    	
    	if (!success) {
    		System.out.println("Rename not OK");
    		logger.info("Whoops. Something went horribly wrong during renaming. Abort mission.");
    	}
    	}
    	catch (Exception e) {
    		logger.info("Whoops. Something went horribly wrong during renaming. Abort mission.");
    		e.getStackTrace();
    	}
    }
    
    
    public static void cleanAudioFile(File inputFile) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
    	
    	logger.info("Starting cleaning ID3 Tag of file: " + inputFile.getName());
    	
    	AudioFile file = AudioFileIO.read(inputFile);
    	
    	Tag tag = file.getTag();
   
    	FieldKey[] fieldKey = FieldKey.values();
   
    	for(FieldKey fieldKeysInEnum : fieldKey) {
    	
    		String output = tag.getFirst(fieldKeysInEnum);
    		
    		if (!output.isEmpty() && fieldKeysInEnum != FieldKey.COVER_ART) {
    		
    			if (stringContainsRubbish(output) ) {
    				tag.setField(fieldKeysInEnum, "");
    				logger.info("This text: '"+ tag.getFirst(fieldKeysInEnum) + "was changed to '");
    			}
    		
    		else {
    			
    			logger.info(tag.getFirst(fieldKeysInEnum));
    			
    			}
    		
    		}
    	}
    	
    	
    	
    	file.commit(); 
    	logger.info("Successfully cleaned ID3 tags");
    }
    
    public static String getCleanFileName(String uncleanFileName) {
    	
    	String cleanFileName = null;
    	
    	if (stringContainsRubbish(uncleanFileName)) {
    		
    		String regex = "-[www]*.[a-zA-Z]*.[a-z]{3}.mp3";
    		String regex2 = " \\[[a-zA-Z]*.[a-z]{3}\\]";
    		
    		cleanFileName = uncleanFileName.replaceAll(regex,"");
    		cleanFileName = cleanFileName.replaceAll(regex2,"");
    		
    		cleanFileName = cleanFileName.replace("[", "");
    		cleanFileName = cleanFileName.replace("]", "");
    		
    	}
    	
    	else {
    		
    		cleanFileName = uncleanFileName.toString();
    	}
    	
    	if (cleanFileName.endsWith(".mp3")) {
    		return cleanFileName;
    	}
    	else {
    		
    	return cleanFileName + ".mp3";
    	}
    }
    
    public static boolean stringContainsRubbish(String inputKeyContent) {
    	
    	return inputKeyContent.startsWith("www") 
    			|| inputKeyContent.contains(".") && inputKeyContent.contains("www") 
    			|| inputKeyContent.contains("[")
    			|| inputKeyContent.contains("]")
    			|| inputKeyContent.contains(".org")
    			|| inputKeyContent.contains(".com");
    	
    }
}
