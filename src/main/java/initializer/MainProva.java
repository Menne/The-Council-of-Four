package initializer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bonus.Bonus;
import bonus.ScoreBonus;
import gameStuff.City;
import gameStuff.CityColour;
import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import gameStuff.Map;
import gameStuff.PermitDeck;
import gameStuff.PermitTile;
import gameStuff.RegionBoard;

public class MainProva {

	public static void main(String[] args) {
		CityColour color=new CityColour("Oro", new ScoreBonus(2));
		
		CouncilBalcony balcony=new CouncilBalcony(new Councillor[4]);
		
		List<PermitTile> tiles=new ArrayList<PermitTile>();		
		PermitDeck deck=new PermitDeck(tiles);
		
		Set<Bonus> bonuses= new HashSet<Bonus>();
		bonuses.add(new ScoreBonus(4));
		
		Set<City> citiesOfTile= new HashSet<City>();
		
		
		RegionBoard region=new RegionBoard
				("Mare", deck , balcony, new ScoreBonus(4));
		City a=new City("A", region, color, 4);
		City b=new City("B", region, color, 4);
		City c=new City("C", region, color, 4);
		
		citiesOfTile.add(a);
		
		
		a.addNearCity(b);
		a.addNearCity(c);
		b.addNearCity(a);
		c.addNearCity(a);
		Set<City> cities= new HashSet<City>();
		cities.add(a);
		cities.add(b);
		cities.add(c);
		
		Map map = new Map(cities);
		
		System.out.println(map);
		
		System.out.print(map.getShortestPathLenght(a, c));
		
		

	}

}
