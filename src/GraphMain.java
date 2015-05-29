import java.util.*;

public class GraphMain {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void displayMenu() {	
		System.out.println(" Q   Query the city information by entering the city code.");
		System.out.println(" D   Find the minimum distance between two cities.");
		System.out.println(" I   Insert a road by entering two city codes and distance.");
		System.out.println(" R   Remove an existing road by entering two city codes.");
		System.out.println(" H   Display this message.");
		System.out.println(" E   Exit.");
		command();
	}
	
	public static void command() {
		System.out.print("Command? ");
		char menuInput = sc.next().toLowerCase().charAt(0);
		if(menuInput == 'q' || menuInput == 'd' || menuInput == 'i'|| menuInput == 'r' || menuInput == 'h'){
			int valueInput = sc.nextInt();
			moveFromMenu(menuInput, valueInput);
		} else if(menuInput == 'e') {
			displayMenu();
			command();
		} else {
			System.out.println("Invalid command.");
			System.out.println("Please only enter one of the follow characters.");
			sc.nextLine();
			command();
		} 
	}
	
	public static void moveFromMenu(char option, int value) {		
		boolean run = true;
		while(run){
			switch(option){
			case 'q':
				System.out.println();
				command();
				break;
			case 'd':
				System.out.println();
				command();
				break;
			case 'i':
				System.out.println();
				command();
				break;
			case 'r':
				System.out.println();
				command();
			case 'h':
				System.out.println();
				command();
			case 'e':
				System.out.println("Thank you for using!");
				run = false;
				System.exit(0);
				break;
			}
		}
	}
}
