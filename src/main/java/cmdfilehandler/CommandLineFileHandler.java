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
				int userProgramChoice = sc.nextInt();
				switch(userProgramChoice) {
					case 1:
						fileHandler.listAllFilesInRoot();
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						break;
					case 7:
						sc.close();
						System.out.println(">> Command Line File Handler shutting down. Goodbye!");
						programLive = false;
						break;
					default:
						fileHandler.printInputErrorMessage();
				}
			} catch(InputMismatchException e) {
				// log error to specific file via log4j
				fileHandler.printInputErrorMessage();
				sc.nextLine();
			}			
		}
	}

}