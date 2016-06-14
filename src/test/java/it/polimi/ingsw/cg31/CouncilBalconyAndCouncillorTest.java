package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.gameTable.CardColour;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.CouncillorsReserve;
import server.model.player.Player;


public class CouncilBalconyAndCouncillorTest {

	@Test
	public void testGetNumberOfCouncillors() {
		assertEquals(4, CouncilBalcony.getNumberofcouncillors());
	}
	

	@Test
	public void testGetColourOfCouncillor() {
		CardColour x=new CardColour("x");
		Councillor a=new Councillor(x);
		assertTrue(x== a.getColour());
	}
	
	@Test
	public void testSubstituteCouncillor() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		Councillor[] oldBalcony=game.getGameTable().getRegionBoards().get(0).getRegionBalcony().getCouncillors();
		CardColour x=new CardColour("x");
		Councillor councillor=new Councillor(x);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(councillor);
		Councillor[] newBalcony=game.getGameTable().getRegionBoards().get(0).getRegionBalcony().getCouncillors();
		assertTrue(newBalcony[0]==councillor);
		assertTrue(newBalcony[1]==oldBalcony[0]);
		assertTrue(newBalcony[2]==oldBalcony[1]);
		assertTrue(newBalcony[3]==oldBalcony[2]);
	}
	
	@Test(expected=NullPointerException.class)
	public void testIfSubstituteCouncillorThrowsException() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(null);
	}
	
	@Test
	public void testGetCouncillors() {
		CardColour x=new CardColour("x");
		Councillor a=new Councillor(x);	
		List<Councillor> listOfCouncillors= new ArrayList<>();
		listOfCouncillors.add(a);
		listOfCouncillors.add(a);
		listOfCouncillors.add(a);
		listOfCouncillors.add(a);
		listOfCouncillors.add(a);
		listOfCouncillors.add(a);
		listOfCouncillors.add(a);
		listOfCouncillors.add(a);
		CouncillorsReserve reserve= new CouncillorsReserve(listOfCouncillors);
		CouncilBalcony balcony=new CouncilBalcony(reserve);
		assertEquals(a, balcony.getCouncillors()[0]);
		assertEquals(a, balcony.getCouncillors()[1]);
		assertEquals(a, balcony.getCouncillors()[2]);
		assertEquals(a, balcony.getCouncillors()[3]);
	}
}