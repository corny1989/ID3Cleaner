package ID3Cleaner.ID3Cleaner;

import java.io.File;
import java.util.logging.Logger;

public class App 
{
	
	private static final Logger logger = Logger.getLogger(App.class.getName());
	
    public static void main( String[] args )
    {
    	String pathFolder = "files/";
    	logger.info("Cleaning of ID3 tags is started in folder: " + pathFolder);
    	File[] audiofile = new File(pathFolder).listFiles();
 	
    	CleanUtils.cleanFiles(audiofile);
    }	
     
}
