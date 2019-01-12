package ID3Cleaner.ID3Cleaner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
			} else {

				try {
					logger.info("Trying to clean file: " + file.getName());
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

		if (stringContainsRubbish(file.getName())) {

			final File fileOld = file;

			final File fileNew = new File(getCleanFileName(file.toString()));

			logger.info("Trying to rename file: " + file.getName());

			try {
				if (fileNew.exists()) {
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
			} catch (Exception e) {
				logger.info("Whoops. Something went horribly wrong during renaming. Abort mission.");
				e.getStackTrace();
			}

		}
	}

	public static void cleanAudioFile(File inputFile) throws CannotReadException, IOException, TagException,
			ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {

		logger.info("Starting cleaning ID3 Tag of file: " + inputFile.getName());

		AudioFile file = AudioFileIO.read(inputFile);

		Tag tag = file.getTag();

		FieldKey[] fieldKey = FieldKey.values();

		for (FieldKey fieldKeysInEnum : fieldKey) {

			String output = tag.getFirst(fieldKeysInEnum);

			if (!output.isEmpty()  && fieldKeysInEnum != FieldKey.ARTIST && fieldKeysInEnum != FieldKey.TITLE) {

				if (stringContainsRubbish(output)) {
					tag.deleteField(fieldKeysInEnum);					
					logger.info("This text: '" + tag.getFirst(fieldKeysInEnum) + "'" + "for ID3 Tag "
							+ fieldKeysInEnum.toString() + " was cleared");
				}

				else {

					logger.info(tag.getFirst(fieldKeysInEnum));

				}

			}
		}

		try {
		file.commit();
		logger.info("Successfully cleaned ID3 tags");
		}
		
		catch(Exception e) {
			logger.warning("An exception occured while committing ID3s tags to file.");
			e.printStackTrace();
		}
	}
	
	

	public static String getCleanFileName(String uncleanFileName) {

		String cleanFileName = null;

		if (stringContainsRubbish(uncleanFileName)) {

			String regex = "-[www]*.[a-zA-Z]*.[a-z]{3}.mp3";
			String regex2 = "\\s\\[[a-zA-Z.]*.[a-z]{2,3}\\]";
			String regex3 = "[a-zA-Z]*.[a-z]{3}.mp3";

			cleanFileName = uncleanFileName.replaceAll(regex, "");
			cleanFileName = cleanFileName.replaceAll(regex2, "");
			cleanFileName = cleanFileName.replaceAll(regex3, "");

			cleanFileName = cleanFileName.replace("[", "");
			cleanFileName = cleanFileName.replace("]", "");

		}

		cleanFileName = fixExtensions(cleanFileName);

		return cleanFileName;

	}

	public static boolean stringContainsRubbish(String inputKeyContent) {

		return inputKeyContent.startsWith("www") || inputKeyContent.contains(".") && inputKeyContent.contains("www")
				|| inputKeyContent.contains("[") || inputKeyContent.contains("]") || inputKeyContent.contains(".org")
				|| inputKeyContent.contains(".com") || inputKeyContent.contains(".me");

	}

	private static String fixExtensions(String fileName) {

		if (fileName.endsWith(".mp3") && !fileName.matches("[.mp3]{2,}")) {
			return fileName;
		}

		if (!fileName.endsWith(".mp3")) {
			return fileName.concat(".mp3");
		}

		return "Cheese";
	}

}
