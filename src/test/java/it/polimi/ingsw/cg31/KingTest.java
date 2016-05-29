package it.polimi.ingsw.cg31;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.gameTable.City;
import server.model.gameTable.King;


public class KingTest {

	@Test
	public void testIfJuvelarHasTheKing() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		City c=null;
		for(City city: game.getGameTable().getRegionBoards().get(1).getRegionCities()){
			if(city.getIsKingPresent())
				c=city;}
		assertEquals("Juvelar", c.getName());
	}
	
	
	@Test
	public void testIfKingChangesCity() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		City c=null;
		for(City city: game.getGameTable().getRegionBoards().get(1).getRegionCities()){
			if(city.getIsKingPresent())
				c=city;}
		King king=new King(c);
		City d=null;
		for(City city: game.getGameTable().getRegionBoards().get(1).getRegionCities()){
			if(city.getColour().getName()=="Gold")
				d=city;}
		king.moveKing(d);
		assertTrue(d.getIsKingPresent());
	}
}