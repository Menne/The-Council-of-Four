package server.model.gameTable;

import java.util.HashSet;
import java.util.Set;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.UndirectedGraph;

/**
 * Allows to find all the cities connected (directly and indirectly)
 * to a given city where a player has built a given emporium
 * @author Luca
 *
 */
public class ConnectedBuiltCityDiscover {

	private final Set<City> connectedBonusCities;
	private final Set<City> checkedCities;
	
	/**
	 * constructor of ConnectedBuildCityDiscover thet initializes two empty Set of cities
	 */
	public ConnectedBuiltCityDiscover(){
		this.connectedBonusCities=new HashSet<>();
		this.checkedCities=new HashSet<>();
	}
	
	/**
	 *It's a recursive private method that adds all the connected cities
	 *with the same emporium to the private field connectedBonsCities
	 * @param map
	 * @param cityToCheck
	 * @param builedEmporium
	 */
	private void addCities(UndirectedGraph<City,DefaultEdge> map, 
			City cityToCheck, Emporium builedEmporium){
	
		this.checkedCities.add(cityToCheck);
		if(!cityToCheck.getCityEmporiums().contains(builedEmporium))
			return;
		else{
			this.connectedBonusCities.add(cityToCheck);
			for(City nearCity : cityToCheck.getNearCities())
				if(!this.checkedCities.contains(nearCity))
					this.addCities(map, nearCity, builedEmporium);			
		}			
	}
	
	
	/**
	 * resets the class attributes
	 */
	private void resetAttributes(){
		this.checkedCities.clear();
		this.connectedBonusCities.clear();
	}
	
	/**
	 * Returns the set of cities connected with the same builded emporium
	 * @param map is the map to pass to addCities
	 * @param cityToCheck is the city to pass to addCities
	 * @param builedEmporium is the emporium to pass to addCities
	 * @return the set of cities found out from the private method addCities
	 */
	public Set<City> getConnectedBuiltCities(UndirectedGraph<City,DefaultEdge> map, 
			City cityToCheck, Emporium builedEmporium){
		this.addCities(map, cityToCheck, builedEmporium);
		Set<City> temp= new HashSet<City>(this.connectedBonusCities);
		this.resetAttributes();
		return temp;
	}
}
