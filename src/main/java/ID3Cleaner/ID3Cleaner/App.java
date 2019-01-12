package ID3Cleaner.ID3Cleaner;

import java.io.File;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class App {

	private static final Logger logger = Logger.getLogger(App.class.getName());
	
	
	public static void main(String[] args) {
		
		String pathFolder = "";

		final Scanner input = new Scanner(System.in);

		System.out.println("Please enter folder to clean:");
		
		try {
			pathFolder = input.nextLine();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();

		}

		input.close();

		if (!pathFolder.isEmpty()) {
			try {
				logger.info("Cleaning of ID3 tags is started in folder: " + pathFolder);
				File[] audiofile = new File(pathFolder).listFiles();

				CleanUtils.cleanFiles(audiofile);
			} catch (Exception e) {
				logger.warning(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
}
