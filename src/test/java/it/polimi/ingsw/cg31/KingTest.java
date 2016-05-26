package it.polimi.ingsw.cg31;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import server.model.Game;
import server.model.gameTable.City;


public class KingTest {

	@Test
	public void testIfJuvelarHasTheKing() throws IOException {
		Game game=new Game();
		City c=null;
		for(City city: game.getGameTable().getRegionBoards().get(1).getRegionCities()){
			if(city.getIsKingPresent())
				c=city;}
		assertEquals("Juvelar", c.getName());
	}
	
	
	@Test
	public void testIfKingChangesCity() throws IOException{
		Game game=new Game();
		
		City a=null;

		for(City city: game.getGameTable().getRegionBoards().get(1).getRegionCities()){
			if(city.getName()=="Hellar")
				a=city;}
		game.getGameTable().getMap().getKing().moveKing(a);
		assertEquals(a.getName(), game.getGameTable().getMap().getKing().getKingPresence().getName());
	}
}