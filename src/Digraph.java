/*
 * This Digraph class will be used to store city and road information.
 * any interaction that needs to be performed on roads and cities will be done
 * through this class. Includes method to add city, insert road(or edge), 
 * remove road, get full list of cities, and get the entire digraph.
 */
import java.io.*;
import java.util.*;

public class Digraph {

	public String[] cities;
	public HashTable table;
	public BinomialQueue queue;
	public AdjacencyList graph;
	public DijkstraTable dTable;

	public Digraph() 
	{

	}

	public void getNumCities(String[] args) {
		BufferedReader input;
		String nextLine;
		int count = 0;

		try {
			input = new BufferedReader(new FileReader(args[0]));
			nextLine = input.readLine();

			while (nextLine.compareTo(".") != 0) {
				count++;
				nextLine = input.readLine();
			}
		} catch (IOException e) {
			System.out.println("File Error");
		}

		// City List
		cities = new String[count];
	}


	public void printCities() {
		for (int vertex = 0; vertex < cities.length; vertex++)
			System.out.println(cities[vertex]);
	}


	public void readFile(String[] args) {
		BufferedReader input;
		String start, finish, nextLine;
		int cost;
		int vertex = 0;

		try {
			input = new BufferedReader(new FileReader("road.dat"));
			nextLine = input.readLine();
			while (nextLine.compareTo("road.dat") != 0) {
				cities[vertex++] = nextLine;
				nextLine = input.readLine();
			}

			// Creates Hash Table
			makeTable();

			// Make instance to AdjacencyList
			graph = new AdjacencyList(cities.length);

			nextLine = input.readLine(); // Reads '.'
			while (nextLine != null) {
				start    = nextLine;
				nextLine = input.readLine();
				finish   = nextLine;
				nextLine = input.readLine();
				cost     = Integer.valueOf(nextLine).intValue();
				nextLine = input.readLine();

				// Creates Adjacency List
				makeList(start, finish, cost);
			}
		} catch (IOException e) {
			System.out.println("File Error");
		}
	}


	private void makeTable() {
		// Make instance to HashTable
		table = new HashTable(cities.length);

		for (int vertex = 0; vertex < cities.length; vertex++) {
			table.Hash(cities[vertex], vertex, false);
		}
	}


	private void makeList(String start, String finish, int cost) {
		int vertex   = table.getVertex(start);
		int neighbor = table.getVertex(finish);

		graph.makeList(finish, vertex, neighbor, cost);
	}


	public void printList() {
		System.out.println("Original Graph");

		for (int vertex = 0; vertex < cities.length; vertex++) {
			graph.printList(cities[vertex], vertex);
		}
		System.out.println();
	}


	public void Dijkstra(int start) {
		// Make instance to DijkstraTable
		dTable = new DijkstraTable(cities.length, graph);

		dTable.Dijkstra(start);
	}


	public void printDTable() {
		dTable.printTable();
	}


	public void printPaths(int start) {

		System.out.println("Shortest Paths\n");
		dTable.printShortestPaths(start, cities);
	}


	public static void main(String[] args) {

		Digraph d = new Digraph();
		int start = 0;
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while(loop){

			System.out.println("Command? ");
			displayMessage();
			String option = sc.nextLine();
			switch(option.toLowerCase()){
			case "q":
				//query();
				break;
			case "d":
				//minD();
				break;
			case "i":
				//insert();
				break;
			case "r":
				//remove();
				break;
			case "h": 
				displayMessage();
				break;
			case "e":
				System.out.println("Thank you come again!");
				loop = false;
				System.exit(0);
				break;
			default:
				System.out.println("Invalid input");
				break;
			}
		}
		sc.close();
		d.getNumCities(args);
		d.readFile(args);
		d.printList();
		d.Dijkstra(start);
		d.printPaths(start);
	}

	public static void displayMessage(){
		System.out.println("Q  Query the city information by entering the city code."); //check
		System.out.println("D  Find the minimum distance between two cities.");
		System.out.println("I  Insert a road by entering two city codes and distance.");
		System.out.println("R  Remove an existing road by entering two city codes.");
		System.out.println("H  Display this message.");									//check
		System.out.println("E  Exit");													//check
	}

}