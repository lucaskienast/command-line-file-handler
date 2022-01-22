package cmdfilehandler;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandLineFileHandler {
	
	public static void main(String[] args) {
		// start program and show welcome screen
		CLFileHandler fileHandler = new CLFileHandler();
		fileHandler.printWelcomeLogo();
		System.out.println();
		fileHandler.printWelcomeView();
		boolean programLive = true;
		
		while (programLive) {
			Scanner sc = new Scanner(System.in);
			try {
				fileHandler.printHomeUserOptions();
				String filename;
				int userProgramChoice = sc.nextInt();
				switch(userProgramChoice) {
					case 1:
						fileHandler.listAllFilesInRoot();
						break;
					case 2:
						System.out.println(">> Enter the filename...");
						filename = sc.next();
						fileHandler.writeInventoryFile(filename);
						break;
					case 3:
						System.out.println(">> Enter the filepath of the file to be moved...");
						String filepath = sc.next();
						System.out.println(">> Enter the new filename...");
						filename = sc.next();
						fileHandler.copyExistingFileIntoRoot(filepath, filename);
						break;
					case 4:
						System.out.println(">> Enter the filename...");
						filename = sc.next();
						fileHandler.printFileContent(filename);
						break;
					case 5:
						System.out.println(">> Enter the filename...");
						filename = sc.next();
						fileHandler.crudExistingFile(filename);
						break;
					case 6:
						fileHandler.deleteAllExistingFiles();
						break;
					case 7:
						sc.close();
						System.out.println(">> Command Line File Handler shutting down. Goodbye!");
						programLive = false;
						break;
					default:
						fileHandler.printInputErrorMessage();
				}
				
				System.out.println("_______________________________________");
				
			} catch(InputMismatchException e) {
				// log error to specific file via log4j
				fileHandler.printInputErrorMessage();
				sc.nextLine();
			} catch(NoSuchElementException e) {
				// log error to specific file via log4j
				fileHandler.printInputErrorMessage();
				sc.nextLine();
			}	
		}
	}

}