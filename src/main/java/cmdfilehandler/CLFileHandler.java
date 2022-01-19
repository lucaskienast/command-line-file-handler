package cmdfilehandler;

import java.io.File;

public class CLFileHandler {
	
	protected void printWelcomeView() {
		System.out.println("_______COMMAND_LINE_FILE_HANDLER_______");
		System.out.println("_________(BY_MAX_LUCAS_KIENAST)________");
		System.out.println();
		System.out.println("Please choose an option");
		System.out.println("1) List all files");
		System.out.println("2) Search for files");
		System.out.println("3) View a file");
		System.out.println("4) Edit a file");
		System.out.println("5) Delete a file");
		System.out.println("6) Delete ALL files");
		System.out.println("7) Close this application");
	}
	
	protected void listAllFilesInRoot() {
		File dir = new File("/Users/maxkienast/Desktop/Education/Programming Courses/Java/Simplilearn/1 - OOPS using JAVA with Data Structures and Beyond/Command-Line-File-Handler/src/test/resources/root");
		File[] allFiles = dir.listFiles();
		System.out.println();
		System.out.println("All files in root directory:");
		for (File f : allFiles) {
			if (f.isFile()) {
				System.out.println(">> " + f.getName());
			}
		}
	}
	
	protected void printInputErrorMessage() {
		System.out.println("[WARNING] - Please enter a valid input");
	}
	

}
