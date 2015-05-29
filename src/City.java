/*
 * This class will create a city object. This will make 
 * the search and manipulation of cities and edges much easier.
 */
public class City {
		private int number;
		private String code;
		private String cityName;
		private int pop;
		private int elev;
		
		public City(int number, String code, String cityName, int pop, int elev){
			this.number = number;
			this.code = code;
			this.cityName = cityName;
			this.pop = pop;
			this.elev = elev;
		}
		
		public int getNumber(){
			return number;
		}
		
		public String getCode(){
			return code;
		}
		
		public String getName(){
			return cityName;
		}
		
		public int getPop(){
			return pop;
		}
		
		public int getElev(){
			return elev;
		}
		
		public String gogogadgetString(){
			return Integer.toString(number) + " " + code + " " + cityName + " " +
					Integer.toString(pop) + " " + Integer.toString(elev);
		}
}