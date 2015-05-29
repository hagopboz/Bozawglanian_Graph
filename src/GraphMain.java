import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
public class GraphMain {
	static Digraph digraph;
	/*
	 * this main method stores the data  from city.dat and road.dat and then loops a menu-driven system for the user
	 * until they choose to quit the program
	 */
	public static void main(String[] args) throws IOException
	   {
	      	Scanner kb = new Scanner(System.in);
	      	boolean loop = true;
	      	storeData();
	      	while(loop){
	      		
	      		System.out.println("Choose an option (Enter H for help): ");
	      		String option = kb.nextLine();
		      	switch(option.toLowerCase()){
		      	case "q":
		      		query();
		      		break;
		      	case "d":
		      		minD();
		      		break;
		      	case "i":
		      		insert();
		      		break;
		      	case "r":
		      		remove();
		      		break;
		      	case "h": 
		      		displayMessage();
		      		break;
		      	case "e":
		      		System.out.println("Thank you come again!");
		      		loop = false;
		      		break;
		      	default:
		      		System.out.println("Invalid input");
		      		break;
		      	}
	      	}
	      	kb.close();
	   }
	/*
	 * This method displays the options for the user to input(I thought it'd be easier to put it in a method then to have
	 * it in the main)
	 */
	public static void displayMessage(){
		System.out.println("Q  Query the city information by entering the city code."); //check
		System.out.println("D  Find the minimum distance between two cities.");
		System.out.println("I  Insert a road by entering two city codes and distance.");
		System.out.println("R  Remove an existing road by entering two city codes.");
		System.out.println("H  Display this message.");									//check
		System.out.println("E  Exit");													//check
	}
	/*
	 * This method is in charge of Storing the data from City.dat and Roads.dat.
	 * deals with all the file I/O in the program
	 */
	public static void storeData() throws IOException{
		digraph = new Digraph();
		
		File file1 = new File("city.dat");
	    Scanner cities = new Scanner(file1);
	    File file2 = new File("road.dat");
	    Scanner roads = new Scanner(file2);
	    while(cities.hasNext()){
	    	String delims = "[ ]+";
	    	String[] tokens = cities.nextLine().split(delims);
	    	if(tokens.length > 5){
	    		int number = Integer.parseInt(tokens[0]);
	        	String code = tokens[1];
	        	String cityName = tokens[2] + " " + tokens[3];
	        	int pop = Integer.parseInt(tokens[4]);
	        	int elev = Integer.parseInt(tokens[5]);
	        	digraph.add(new City(number, code, cityName, pop, elev));
	    	}
	    	else{
		    	int number = Integer.parseInt(tokens[0]);
		    	String code = tokens[1];
		    	String cityName = tokens[2];
		    	int pop = Integer.parseInt(tokens[3]);
		    	int elev = Integer.parseInt(tokens[4]);
		    	digraph.add(new City(number, code, cityName, pop, elev));
	    	}
	    }
	    while(roads.hasNext()){
	    	digraph.insertRoad(roads.nextInt() -1, roads.nextInt() -1, roads.nextInt());
	    }
	    cities.close();
	    roads.close();
	}
	/*
	 * The first option for the user.
	 * Takes in a city code, searches for it in the digraph and prints out the entire
	 * City information
	 */
	public static void query(){
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter a city Code: ");
		String input = kb.nextLine();
		for(int i = 0; i< digraph.getCities().size(); i++){
			if(digraph.getCities().get(i).getCode().equalsIgnoreCase(input)){
				System.out.println("\n" + digraph.getCities().get(i).gogogadgetString() + "\n");
				break;
			}
			else if (i == digraph.getCities().size()-1){
				System.out.println("The code you entered was either not found or invalid.");
				break;
			}
			
		}

	}
	/*
	 * First off, this method will take in two city codes, then it finds
	 * the smallest distance using dijstrka's algorithm
	 */
	private static void minD()
	{
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter city codes: ");
		String delims = "[ ]+";
    	String[] tokens = kb.nextLine().split(delims);
    	if(tokens.length != 2){
			System.out.println("Invalid input");
			minD();
		}
    	String code1 = tokens[0];
    	String code2 = tokens[1];
    	int city1 = digraph.getCityNumber(code1)-1;
    	int city2 = digraph.getCityNumber(code2)-1;
    	System.out.println(city1 + "," + city2);
		
		// Dijkstra's algorithm
		int[] distanceArray = new int[digraph.getCities().size() + 1];
		int[] prev = new int[digraph.getCities().size() + 1];
		boolean[] setOfNodes = new boolean[digraph.getCities().size() + 1];
		ArrayList<String> path = new ArrayList<String>();
		int[] neighbors = new int[0];
		for(int i = 1; i < distanceArray.length; i++)
			distanceArray[i]= Integer.MAX_VALUE;
		
		distanceArray[city1] = 0;
		
		for(int j = 1; j < distanceArray.length; j++)
		{
			int smallestVertex = SmallestWeight(distanceArray, setOfNodes);
			setOfNodes[smallestVertex] = true;
			//path.add(0, d.getList().get(smallestVertex-1).getCityName());
			neighbors = neighbors(smallestVertex);
			for(int k = 0; k < neighbors.length; k++)
			{
				int temp = neighbors[k];
				int tempDist = distanceArray[smallestVertex] + digraph.getDigraph()[smallestVertex][temp];
				if(distanceArray[temp] > tempDist)
				{
					distanceArray[temp] = tempDist;
					prev[temp] = smallestVertex;
				}
			}
		}
	
		System.out.println("\nThe minimum distance between " + digraph.getCityName(code1).toUpperCase() 
			 + " and " + digraph.getCityName(code2).toUpperCase() + " is " + distanceArray[city2]
			 + " through the route: ");
		int var = city2;
		do
		{
		path.add(0, digraph.getCityName(var));
		var = prev[var];
		}
		while(prev[var] != city1);
		path.add(0, digraph.getCityName(var));
		if(var != city1)
		path.add(0, digraph.getCityName(code1));
		for(int x = 0; x < path.size()-1; x++)
		System.out.print(path.get(x) + ", ");
		System.out.print(path.get(path.size()-1) + ".\n");
		System.out.println();
	
	}
	/*
	 * supplement method to the minD method. Finds the smallest weight of an edge
	 * given the neighborArray
	 */
	public static int smallestWeight(int[] neighborArray){
		int counter = neighborArray[0];
		int smallestWeight = 0;
		for(int i = 0; i <neighborArray.length; i++){
			if(neighborArray[i] < counter){
				i = smallestWeight;
			}
		}
		return smallestWeight;
	}
	/*
	 * supplment method to the minD method. Finds the neighbors of an edge given 
	 * the edge
	 */
	public static int[] neighbors(int vertex)
	{
		int counter = 0;																			//set counter to 0
		//get neighbor size
		for(int i = 0; i < digraph.getDigraph()[vertex].length; i++)								//going through the smallest vertices neighbors.
		{																							//if the array index has a value >0, then it's a neighbor
			if(digraph.getDigraph()[vertex][i] > 0)
				counter++;																			//counter++
		}
		int[] temp = new int[counter];																//temp holds the number of neighbors for the vertex
		counter = 0;
		//fill height array
		for(int i = 0; i < digraph.getDigraph()[vertex].length; i++)
		{
			if(digraph.getDigraph()[vertex][i] > 0)
				temp[counter++] = i;
		}
		return temp;
	}
	/*
	 * supplemental method to the minD method. Finds the smallest weight of an edge
	 * given the distance array and the visited array
	 */
	public static int SmallestWeight(int[] dist, boolean[] visited) {
		int curmin = Integer.MAX_VALUE;																//current min = infinity
		int absmin = 0;																				//absolute min = 0;
		for(int i = 0; i< dist.length; i++){			
			if(!visited[i] && dist[i] < curmin)														//if this vertex hasn't been visited and distance is less than current min
			{
				absmin = i;																			//then set this vertex = to y
				curmin= dist[i];																	//and set current min to this index
			}
		}
		return absmin;																				//return the absolute minimun weight
	}
	/*
	 * Insert method. Get's two city codes and a distance, then it adds it into the digraph
	 * if it's not already there
	 */
	public static void insert(){
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter two city codes and a distance");
		String delims = "[ ]+";
    	String[] tokens = kb.nextLine().split(delims);
    	if(tokens.length != 3){
			System.out.println("Invalid input");
			insert();
		}
    	String code1 = tokens[0];
    	String code2 = tokens[1];
    	int distance = Integer.parseInt(tokens[2]);
    	int city1 = digraph.getCityNumber(code1) -1;
    	int city2 = digraph.getCityNumber(code2) -1;
    	{
    		if(city1 +1 == -1 || city2 +1 == -1){
    			System.out.println("Invalid city codes");
    			insert();
    		}
    		if(digraph.getDigraph()[(city1)][(city2)] != 0){
    			System.out.println("\nThe road from " + digraph.getCities().get(city1).getName().toUpperCase() 
    								+ " to " + digraph.getCities().get(city2).getName().toUpperCase() + " already exists!\n");
    		}
    		else if(digraph.getDigraph()[city1][city2] == 0){
    			digraph.insertRoad(city1, city2, distance);
    			System.out.println("\nYou have inserted a road from " + digraph.getCities().get(city1).getName().toUpperCase() 
    								+ " to " + digraph.getCities().get(city2).getName().toUpperCase() + " with a distance of " 
    								+ distance + ".\n");
    		}
    	}
	}
	/*
	 * remove method. Get's two city codes and removes it from the digraph if it exists
	 */
	public static void remove(){
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter two city codes");
		String delims = "[ ]+";
    	String[] tokens = kb.nextLine().split(delims);
    	if(tokens.length != 2){
			System.out.println("Invalid input");
			remove();
		}
    	String code1 = tokens[0];
    	String code2 = tokens[1];
    	int city1 = digraph.getCityNumber(code1);
    	int city2 = digraph.getCityNumber(code2);
    	{
    		if(city1 == -1 || city2 == -1){
    			System.out.println("Invalid city code(s)");
    			remove();
    		}
    		if(!digraph.remove(city1-1, city2-1)){
    			System.out.println("\nThe road from " + digraph.getCities().get(city1-1).getName().toUpperCase() 
   					 + " to " + digraph.getCities().get(city2-1).getName().toUpperCase() + " doesn't exist!\n");
    		}
    		else{
    			System.out.println("\nYou have removed the road from " + digraph.getCities().get(city1-1).getName().toUpperCase() 
      					 + " to " + digraph.getCities().get(city2-1).getName().toUpperCase() + ".\n");
    		}
    	}
	}
}