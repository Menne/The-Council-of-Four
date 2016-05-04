package gameStuff;

import java.util.HashSet;
import java.util.Set;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;

public class ConnectedBuiltCityDiscover {

	private final Set<City> connectedBonusCities;
	private final Set<City> checkedCities;
	
	public ConnectedBuiltCityDiscover(){
		this.connectedBonusCities=new HashSet<City>();
		this.checkedCities=new HashSet<City>();
	}
	
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
	
	private void resetAttributes(){
		this.checkedCities.clear();
		this.connectedBonusCities.clear();
	}
	
	public Set<City> getConnectedBuiltCities(UndirectedGraph<City,DefaultEdge> map, 
			City cityToCheck, Emporium builedEmporium){
		this.addCities(map, cityToCheck, builedEmporium);
		Set<City> temp= new HashSet<City>(this.connectedBonusCities);
		this.resetAttributes();
		return temp;
	}
}
