package cmdfilehandler;

import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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
	
	protected void printGoodbyeMessage() {
		System.out.println(">> Command Line File Handler shutting down. Goodbye!");
	}
	
	protected void printInputErrorMessage() {
		System.out.println("[WARNING] - Please enter a valid input...");
	}
	
	protected void crudExistingFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println(">> Enter the filename...");
		String filename = sc.next();
		if (Files.exists(Paths.get(rootDir + "/" + filename))) {
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
						break;
					default:
						printInputErrorMessage();
					}
				} catch(InputMismatchException e) {
					printInputErrorMessage();
					sc.nextLine();
				}
			}
		} else {
			System.out.println("[WARNING] - Please enter an existing filename...");
		}
	}
	
	protected void listAllFilesInRoot() {
		File[] allFiles = getAllFilesInRoot();
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
	
	private File[] getAllFilesInRoot() {
		File dir = new File(rootDir);
		File[] allFiles = dir.listFiles();
		return allFiles;
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
		Scanner sc = new Scanner(System.in);
		System.out.println(">> Are you sure you want to delete all files in the root directory? (y/n)");
		String deleteYesNo = sc.next();
		if (deleteYesNo.equals("y")) {
			try {
				File dir = new File(rootDir);
				File[] allFiles = dir.listFiles();
				for (File file : allFiles) {
					Files.deleteIfExists(Paths.get(rootDir + "/" + file.getName()));
				}
				System.out.println("[INFO] - Deleted all files in root directory.");
			} catch(IOException e) {
				System.out.println("[WARNING] - You do not have permissions to edit files on your machine...");
			}
		} else {
			if (!deleteYesNo.equals("n")) {
				System.out.println("[INFO] - Invalid input.");
			}
			System.out.println("[INFO] - Did not delete any files.");
		}
	}
	
	protected void printFileContent() {
		Scanner sc = new Scanner(System.in);
		System.out.println(">> Enter the filename...");
		String filename = sc.next();
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
			System.out.println("[WARNING] - You do not have permissions to edit files on your machine...");
		}
	}
	
	protected void clearAndWriteToExistingFile(String filename, String newString) {
		try {
			FileOutputStream outputStream = new FileOutputStream(filename);
			byte[] strToBytes = newString.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();
		} catch(IOException e) {
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
			printInputErrorMessage();
		} 
	}
	
	protected void writeInventoryFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println(">> Enter the filename...");
		String filename = sc.nextLine();
		if (filename.isEmpty()) {
			System.out.println("[WARNING] - Filename cannot be empty string.");
		}
		filename = filename.replaceAll("\\s+","");
		if (!filename.endsWith(".txt")) {
			filename = filename + ".txt";
		}
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
			printInputErrorMessage();
		} 
	}
	
	protected void copyExistingFileIntoRoot() {
		Scanner sc = new Scanner(System.in);
		System.out.println(">> Enter the filepath of the file to be moved...");
		System.out.println(">> Help: drag your file to the terminal and copy the path it shows.");
		String filepath = sc.nextLine();
		System.out.println(">> Enter the new filename...");
		String filename = sc.nextLine();
		try {
			if (Files.exists(Paths.get(filepath))) {
				Files.move(Paths.get(filepath), Paths.get(rootDir + "/" + filename + ".txt"));
			} else {
				System.out.println("[WARNING] - Please enter an existing path");
			}
		} catch(IOException e) {
			System.out.println("[WARNING] - You do not have permissions to edit files on your machine...");
		}
	}
	
}
