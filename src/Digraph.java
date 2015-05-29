/*
 * This Digraph class will be used to store city and road information.
 * any interaction that needs to be performed on roads and cities will be done
 * through this class. Includes method to add city, insert road(or edge), 
 * remove road, get full list of cities, and get the entire digraph.
 */

import java.util.ArrayList;
class Digraph
{
   private int[][] digraph = new int[20][20];
   private ArrayList<City> cities;

   public Digraph(){
	   cities = new ArrayList<City>();
   }
	public void add(City city){
		cities.add(city);
	}
	public void insertRoad(int i, int j, int d){
		digraph[i][j] = d;
	}
	public boolean remove(int i, int j){
		if(digraph[i][j] == 0){
			return false;
		}
		else
			digraph[i][j] = 0;
		return true;
	}
	public ArrayList<City> getCities(){
		return cities;
	}
	public City getCity(int cityNumber){
		return cities.get(cityNumber);
	}
	public int getCityNumber(String code){
		for(int i = 0; i < cities.size(); i++)
		{
			if(cities.get(i).getCode().equalsIgnoreCase(code))
				return cities.get(i).getNumber();
		}
		return -1;
	}
	public String getCityName(String cityCode)
	{
		for(int i = 0; i < cities.size(); i++)
		{
			if(cities.get(i).getCode().equalsIgnoreCase(cityCode))
				return cities.get(i).getName();
		}
		return null;
	}
	public String getCityName(int index)
	{
		for(int i = 0; i < cities.size(); i++)
		{
			if(cities.get(i).getNumber() == index)
				return cities.get(i).getName();
		}
		return null;
	}
	public int[][] getDigraph(){
		return digraph;
	}
	public int getDistance(int i, int j){
		return digraph[i][j];
	}
}