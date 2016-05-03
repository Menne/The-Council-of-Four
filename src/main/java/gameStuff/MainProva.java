package gameStuff;

import java.util.HashSet;
import java.util.Set;


public class MainProva {

	public static void main(String[] args) {
		City a=new City("A", new CityColour("Oro"), 4);
		City b=new City("B", new CityColour("Azzurro"), 4);
		City c=new City("C", new CityColour("Argento"), 4);
		
		a.addNearCity(b);
		a.addNearCity(c);
		b.addNearCity(a);
		c.addNearCity(a);
		
		Set<City> cities=new HashSet<City>();
		cities.add(a);
		cities.add(b);
		cities.add(c);
		
		Map map=new Map(cities);
		
		System.out.println(map);
		
		

	}

}
