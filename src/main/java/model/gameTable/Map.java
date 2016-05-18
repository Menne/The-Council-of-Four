package model.gameTable;

import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.DijkstraShortestPath;

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
				if(!this.gameMap.containsEdge(city1,city2))
					this.gameMap.addEdge(city1, city2);				
	}

	public UndirectedGraph<City, DefaultEdge> getGameMap() {
		return gameMap;
	}

	public int getShortestPathLenght(City c1, City c2){
		DijkstraShortestPath<City, DefaultEdge> dijkstraAlg=
				new DijkstraShortestPath<City, DefaultEdge>(this.gameMap, c1, c2);
		return (int)dijkstraAlg.getPathLength();
	}
	
	@Override
	public String toString() {
		return "Map [gameMap=" + gameMap + "]";
	}
	
	
	

}
