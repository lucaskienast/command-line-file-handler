package cmdfilehandler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandLineFileHandler {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CLFileHandler fileHandler = new CLFileHandler();
		fileHandler.printWelcomeLogo();
		System.out.println();
		fileHandler.printWelcomeView();
		boolean programLive = true;
		
		while (programLive) {
			try {
				fileHandler.printHomeUserOptions();
				int userProgramChoice = sc.nextInt();
				switch(userProgramChoice) {
					case 1:
						fileHandler.listAllFilesInRoot();
						break;
					case 2:
						fileHandler.writeInventoryFile();
						break;
					case 3:
						fileHandler.copyExistingFileIntoRoot();
						break;
					case 4:
						fileHandler.printFileContent();
						break;
					case 5:
						fileHandler.crudExistingFile();
						break;
					case 6:
						fileHandler.deleteAllExistingFiles();
						break;
					case 7:
						fileHandler.printGoodbyeMessage();
						programLive = false;
						sc.close();
						break;
					default:
						fileHandler.printInputErrorMessage();
				}
				
				System.out.println("_______________________________________");
				
			} catch(InputMismatchException e) {
				fileHandler.printInputErrorMessage();
				sc.nextLine();
			}
		}
	}

}