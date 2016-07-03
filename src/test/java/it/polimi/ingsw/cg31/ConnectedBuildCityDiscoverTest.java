package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import server.model.Game;
import server.model.gameTable.City;
import server.model.gameTable.ConnectedBuiltCityDiscover;
import server.model.gameTable.Emporium;
import server.model.player.Player;

public class ConnectedBuildCityDiscoverTest {

	@Test
	public void test() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		Emporium e = new Emporium(a);
		Iterator<City> it1= game.getGameTable().getRegionBoards().get(0).getRegionCities().iterator();
		City city1= it1.next();
		for(int i=0; i<4; i++){
			if(city1.getName()!="Arkon")
				city1=it1.next();
		}
		Iterator<City> it2= game.getGameTable().getRegionBoards().get(0).getRegionCities().iterator();
		City city2= it2.next();
		for(int i=0; i<4; i++){
			if(city2.getName()!="Castrum")
				city2=it2.next();
		}
		Iterator<City> it3= game.getGameTable().getRegionBoards().get(0).getRegionCities().iterator();
		City city3= it3.next();
		for(int i=0; i<4; i++){
			if(city3.getName()!="Esti")
				city3=it3.next();
		}
		city1.addEmporium(e);
		city2.addEmporium(e);
		ConnectedBuiltCityDiscover discover= new ConnectedBuiltCityDiscover();
		Set<City> connectedBuiltCities= new HashSet<>();
		connectedBuiltCities=discover.getConnectedBuiltCities(game.getGameTable().getMap().getGameMap(), city1, e);
		assertTrue(connectedBuiltCities.contains(city2));
	}

}
