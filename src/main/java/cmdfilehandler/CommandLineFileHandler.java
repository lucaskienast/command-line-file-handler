package cmdfilehandler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandLineFileHandler {
	
	public static void main(String[] args) {
		// start program and show welcome screen
		Scanner sc = new Scanner(System.in);
		CLFileHandler fileHandler = new CLFileHandler();
		fileHandler.printWelcomeView();
		boolean programLive = true;
		
		while (programLive) {
			try {
				fileHandler.printHomeUserOptions();
				String filename;
				int userProgramChoice = sc.nextInt();
				switch(userProgramChoice) {
					case 1:
						fileHandler.listAllFilesInRoot();
						break;
					case 2:
						break;
					case 3:
						System.out.println(">> Enter the filename...");
						filename = sc.next();
						fileHandler.createNewFileWithName(filename);
						break;
					case 4:
						System.out.println(">> Enter the filepath of the file to be moved...");
						String filepath = sc.next();
						System.out.println(">> Enter the new filename...");
						filename = sc.next();
						fileHandler.copyExistingFileIntoRoot(filepath, filename);
						break;
					case 5:
						System.out.println(">> Enter the filename...");
						filename = sc.next();
						fileHandler.printFileContent(filename);
						break;
					case 6:
						System.out.println(">> Enter the filename...");
						filename = sc.next();
						fileHandler.crudExistingFile(filename);
						break;
					case 7:
						System.out.println(">> Enter the filename...");
						filename = sc.next();
						fileHandler.deleteExistingFileWithName(filename);
						break;
					case 8:
						fileHandler.deleteAllExistingFiles();
						break;
					case 9:
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
			}			
		}
	}

}