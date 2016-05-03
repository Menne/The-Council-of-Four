package gameStuff;

import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.graph.*;

/**
 * The gameMap modelized as a simple graph
 * @author Luca
 *
 */
public class Map {

	private final UndirectedGraph<City, DefaultEdge> gameMap;
	
	/**
	 * Creates the game map as a simple graph starting from a set of cities.
	 * Each city has the reference to the set of its near cities.
	 * @param cities of the game map
	 */
	public Map(Set<City> cities){
		this.gameMap=new SimpleGraph<City, DefaultEdge>(DefaultEdge.class);
		for(City city : cities)
			this.gameMap.addVertex(city);
		for(City city1 : cities)
			for(City city2 : city1.getNearCities())
				this.gameMap.addEdge(city1, city2);
	}

	public UndirectedGraph<City, DefaultEdge> getGameMap() {
		return gameMap;
	}

	@Override
	public String toString() {
		return "Map [gameMap=" + gameMap + "]";
	}
	
	
	

}
