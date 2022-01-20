package cmdfilehandler;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;

public class CLFileHandler {
	
	static String rootDir = "src/test/resources/root";
	
	protected void printWelcomeView() {
		System.out.println("_______COMMAND_LINE_FILE_HANDLER_______");
		System.out.println("_________(BY_MAX_LUCAS_KIENAST)________");
	}
	
	protected void printUserOptions() {
		System.out.println();
		System.out.println("Please choose an option");
		System.out.println("1) List all files");
		System.out.println("2) Search for files");
		System.out.println("3) Create a new file");
		System.out.println("4) Move file into root");
		System.out.println("5) View a file");
		System.out.println("6) Edit a file");
		System.out.println("7) Delete a file");
		System.out.println("8) Delete ALL files");
		System.out.println("9) Close this application");
	}
	
	protected void listAllFilesInRoot() {
		File dir = new File(rootDir);
		File[] allFiles = dir.listFiles();
		System.out.println();
		System.out.println("All files in root directory:");
		for (File f : allFiles) {
			if (f.isFile()) {
				System.out.println(">> " + f.getName());
			}
		}
	}
	
	protected void createNewFileWithName(String filename) {
		try {
			File file = new File(rootDir + "/" + filename + ".txt");
			if (file.createNewFile()) {
				System.out.println("[SUCCESS] - File was created.");
			} else {
				System.out.println("[WARNING] - File already exists.");
			}
		} catch(IOException e) {
			// log exception
		}
	}
	
	protected void deleteExistingFileWithName(String filename) {
		try {
			boolean result = Files.deleteIfExists(Paths.get(rootDir + "/" + filename));
			if (result) {
				System.out.println("[SUCCESS] - Your file was deleted.");
			} else {
				System.out.println("[WARNING] - Please give enter an existing filename...");
			}
		} catch(NoSuchFileException e) {
			System.out.println("[WARNING] - Please give enter an existing filename...");
		} catch(IOException e) {
			System.out.println("[WARNING] - You do not have permissions to edit files on your machine...");
		}
	}
	
	protected void printFileContent(String filename) {
		List<String> lines = Collections.emptyList();
		try {
			if (Files.exists(Paths.get(rootDir + "/" + filename))) {
				lines = Files.readAllLines(Paths.get(rootDir + "/" + filename));
				System.out.println();
				for (String line : lines) {
					System.out.println(line);
				}
			} else {
				System.out.println("[WARNING] - Please give enter an existing filename...");
			}
		} catch(IOException e) {
			// log exception
		}
	}
	
	protected void updateExistingFile(String filename, String oldString, String newString) {
		try {
			String oldContent = "";
			BufferedReader reader = null;
			FileWriter writer = null;
			File file = new File(rootDir + "/" + filename);
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while(line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(oldString,  newString);
			writer = new FileWriter(file);
			writer.write(newContent);
			reader.close();
			writer.close();
		} catch(IOException e) {
			// log exception
			System.out.println("[WARNING] - Please give enter an existing filename...");
		} 
	}
	
	protected void copyExistingFileIntoRoot(String filePath, String filename) {
		try {
			if (Files.exists(Paths.get(filePath))) {
				Files.move(Paths.get(filePath), Paths.get(rootDir + "/" + filename));
			} else {
				System.out.println("[WARNING] - Please give enter an existing filename...");
			}
		} catch(IOException e) {
			// log exception
		}
	}
	
	protected void printInputErrorMessage() {
		System.out.println("[WARNING] - Please enter a valid input...");
	}
	

}
