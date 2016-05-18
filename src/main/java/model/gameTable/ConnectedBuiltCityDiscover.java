package model.gameTable;

import java.util.HashSet;
import java.util.Set;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;

/**
 * Allows to find all the cities connected (directly and indirectly)
 * to a given city where a player has built a given emporium
 * @author Luca
 *
 */
public class ConnectedBuiltCityDiscover {

	private final Set<City> connectedBonusCities;
	private final Set<City> checkedCities;
	
	public ConnectedBuiltCityDiscover(){
		this.connectedBonusCities=new HashSet<City>();
		this.checkedCities=new HashSet<City>();
	}
	/**
	 * Adds to the set "connectedBonusCities" all the city of the map 
	 * connected (directly and indirectly) to the cityToCheck and where 
	 * there is the buildedEmporium.
	 * It's used locally from only public method of the class
	 * @param map to consider in the search
	 * @param cityToCheck is the given city
	 * @param builedEmporium is the given emporium
	 */
	private void addCities(UndirectedGraph<City,DefaultEdge> map, 
			City cityToCheck, Emporium builedEmporium){
	
		this.checkedCities.add(cityToCheck);
		if(!cityToCheck.getCityEmporiums().contains(builedEmporium))
			return;
		else{
			this.connectedBonusCities.add(cityToCheck);
			ConnectivityInspector<City,DefaultEdge> inspector=
					new ConnectivityInspector<City,DefaultEdge>(map);
			for(City nearCity : inspector.connectedSetOf(cityToCheck))
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
	 * Returns the set of cities found out from the private method addCities
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
