package cmdfilehandler;

import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.InputMismatchException;

public class CLFileHandler {
	
	static String rootDir = "src/test/resources/root";
	static String resourcesDir = "src/main/resources";
	
	protected void printWelcomeView() {
		System.out.println("_______COMMAND_LINE_FILE_HANDLER_______");
		System.out.println("_________(BY_MAX_LUCAS_KIENAST)________");
		System.out.println();
		System.out.println(">> Welcome to the cmd line file handler.");
		System.out.println(">> This app lets LockedMe.com keep track");
		System.out.println(">> of their inventory of lockers to sell.");
	}
	
	protected void printHomeUserOptions() {
		System.out.println();
		System.out.println("Please choose an option");
		System.out.println("1) List all files");
		System.out.println("2) Create a new file");
		System.out.println("3) Move file into root");
		System.out.println("4) View a file");
		System.out.println("5) Edit a file");
		System.out.println("6) Delete ALL files");
		System.out.println("7) Close this application");
	}
	
	protected void printCrudUserOptions() {
		System.out.println();
		System.out.println("Please choose an option");
		System.out.println("1) View file");
		System.out.println("2) Edit file");
		System.out.println("3) Delete file");
		System.out.println("4) Go back");
	}
	
	protected void listAllFilesInRoot() {
		File dir = new File(rootDir);
		File[] allFiles = dir.listFiles();
		System.out.println();
		if (allFiles.length >= 1) {
			String[] fileNames = new String[allFiles.length];
			System.out.println(">> All files in root directory:");
			for (int i = 0; i < allFiles.length; i++) {
				if (allFiles[i].isFile()) {
					fileNames[i] = allFiles[i].getName();
				}
			}
			HeapSort sorter = new HeapSort(fileNames);
			sorter.sort(fileNames, fileNames.length);
		} else {
			System.out.println("[INFO] - There are no files in the root directory.");
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
			System.out.println("[WARNING] - You do not have permissions to edit files on your machine...");
		}
	}
	
	protected void deleteExistingFileWithName(String filename) {
		try {
			boolean result = Files.deleteIfExists(Paths.get(rootDir + "/" + filename));
			if (result) {
				System.out.println("[SUCCESS] - Your file was deleted.");
			} else {
				System.out.println("[WARNING] - Please enter an existing filename...");
			}
		} catch(NoSuchFileException e) {
			System.out.println("[WARNING] - Please enter an existing filename...");
		} catch(IOException e) {
			System.out.println("[WARNING] - You do not have permissions to edit files on your machine...");
		}
	}
	
	protected void deleteAllExistingFiles() {
		try {
			File dir = new File(rootDir);
			File[] allFiles = dir.listFiles();
			for (File file : allFiles) {
				Files.deleteIfExists(Paths.get(rootDir + "/" + file.getName()));
			}
		} catch(IOException e) {
			// log exception
			System.out.println("[WARNING] - You do not have permissions to edit files on your machine...");
		}
	}
	
	protected void printFileContent(String filename) {
		List<String> lines = Collections.emptyList();
		try {
			if (Files.exists(Paths.get(rootDir + "/" + filename))) {
				lines = Files.readAllLines(Paths.get(rootDir + "/" + filename));
				System.out.println();
				System.out.println(">> File content:");
				for (String line : lines) {
					System.out.println(line);
				}
			} else {
				System.out.println("[WARNING] - Please enter an existing filename...");
			}
		} catch(IOException e) {
			// log exception
			System.out.println("[WARNING] - You do not have permissions to edit files on your machine...");
		}
	}
	
	protected void printWelcomeLogo() {
		List<String> lines = Collections.emptyList();
		try {
			if (Files.exists(Paths.get(resourcesDir + "/" + "ascii-art.txt"))) {
				lines = Files.readAllLines(Paths.get(resourcesDir + "/" + "ascii-art.txt"));
				System.out.println();
				for (String line : lines) {
					System.out.println(line);
				}
			} else {
				System.out.println("[INFO] - Logo could not be loaded.");
			}
		} catch(IOException e) {
			// log exception
			System.out.println("[WARNING] - You do not have permissions to edit files on your machine...");
		}
	}
	
	protected void replaceTextInExistingFile(String filename, String oldString, String newString) {
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
			System.out.println("[WARNING] - Please enter an existing filename...");
		} 
	}
	
	protected void clearAndWriteToExistingFile(String filename, String newString) {
		try {
			FileOutputStream outputStream = new FileOutputStream(filename);
			byte[] strToBytes = newString.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();
		} catch(IOException e) {
			// log exception
			System.out.println("[WARNING] - Please enter an existing filename...");
		} 
	}
	
	protected void writeInventoryFile(String filename) {
		Scanner sc = new Scanner(System.in);
		try {
			File file = new File(rootDir + "/" + filename);
			FileOutputStream fos = new FileOutputStream(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			String[] productAttributes = new String[] {"Id", "Name", "Price", "Color", "Inventory"};
			System.out.println(">> Enter product info...");
			for (String attr: productAttributes) {
				System.out.println(">> " + attr + ": ");
				String newLine = sc.nextLine();
				bw.write(attr + ": " + newLine + "\n");
			}
			bw.close();
		} catch(IOException e) {
			// log exception
			System.out.println("[WARNING] - Machine cannot read input. Contact admin.");
			sc.close();
		} 
	}
	
	protected void crudExistingFile(String filename) {
		if (Files.exists(Paths.get(rootDir + "/" + filename))) {
			Scanner sc = new Scanner(System.in);
			boolean crudOpsLive = true;
			while (crudOpsLive) {
				try {
					printCrudUserOptions();
					int userProgramChoice = sc.nextInt();
					switch (userProgramChoice) {
					case 1:
						printFileContent(filename);
						break;
					case 2:
						writeInventoryFile(filename);
						break;
					case 3:
						deleteExistingFileWithName(filename);
						crudOpsLive = false;
						break;
					case 4:
						crudOpsLive = false;
						sc.nextLine();
						break;
					default:
						printInputErrorMessage();
					}
				} catch(InputMismatchException e) {
					// log exception
					printInputErrorMessage();
					sc.nextLine();
				}
			}
		} else {
			System.out.println("[WARNING] - Please enter an existing filename...");
		}
	}
	
	protected void copyExistingFileIntoRoot(String filePath, String filename) {
		try {
			if (Files.exists(Paths.get(filePath))) {
				Files.move(Paths.get(filePath), Paths.get(rootDir + "/" + filename + ".txt"));
			} else {
				System.out.println("[WARNING] - Please enter an existing filename...");
			}
		} catch(IOException e) {
			// log exception
			System.out.println("[WARNING] - You do not have permissions to edit files on your machine...");
		}
	}
	
	protected void printInputErrorMessage() {
		System.out.println("[WARNING] - Please enter a valid input...");
	}
	

}
