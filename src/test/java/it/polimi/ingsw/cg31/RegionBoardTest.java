
package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.bonus.ScoreBonus;
import server.model.gameTable.City;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.PermitDeck;
import server.model.gameTable.PermitTile;
import server.model.player.Player;

public class RegionBoardTest {


	@Test
	public void testGetName() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		assertEquals("Sea", game.getGameTable().getRegionBoards().get(0).getName());
	}

	@Test
	public void testIsBonusAailable() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		assertEquals(true, game.getGameTable().getRegionBoards().get(0).isBonusAvailable());
	}
	
	@Test
	public void testNotBonusAvailable() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.getGameTable().getRegionBoards().get(0).notBonusAvailable();
		assertEquals(false, game.getGameTable().getRegionBoards().get(0).isBonusAvailable());
	}
	
	@Test
	public void testGetRegionPermitDeck() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		assertEquals(PermitDeck.class, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().getClass());
	}
	
	@Test
	public void testGetRegionBalcony() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		assertEquals(CouncilBalcony.class, game.getGameTable().getRegionBoards().get(0).getRegionBalcony().getClass());
	}
	
	@Test
	public void testGetRegionBonus() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		assertEquals(ScoreBonus.class, game.getGameTable().getRegionBoards().get(0).getRegionBonus().getBonus().getClass());
	}
	
	@Test
	public void testGetRegionCities() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		int i=0;
		for(@SuppressWarnings("unused") City city : game.getGameTable().getRegionBoards().get(0).getRegionCities())
			i++;
		assertTrue(i==5);
	}
	
	@Test
	public void testGetUncoveredPermitTiles() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		assertEquals(PermitTile.class, game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0].getClass());
	}
	
	@Test
	public void testAddCityOfThisRegion() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		City c= null;
		game.getGameTable().getRegionBoards().get(0).addCityOfThisRegion(c);
		assertTrue(game.getGameTable().getRegionBoards().get(0).getRegionCities().contains(c));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPickUncoveredPermitTileException() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(4);
	}
	
	@Test
	public void testPickUncoveredPermitTile() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		assertEquals(PermitTile.class, game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(0).getClass());
	}
	
	@Test
	public void testPickUncoveredPermitTileReturnNullInThatPosition() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(0).getClass();
		assertEquals(null, game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]);
	}
	

	@Test
	public void testUncoverPermitTile() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		PermitTile t= game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().getPermitTiles().get(0);
		game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(0);
		game.getGameTable().getRegionBoards().get(0).uncoverPermitTiles();
		assertEquals(t, game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]);
	}
	
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testIfSubstitutePermitTilesThrowException() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().getPermitTiles().removeAll(
				game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().getPermitTiles());
		game.getGameTable().getRegionBoards().get(0).substitutePermitTiles();
	}
	
	@Test
	public void testSubstitutePermitTilesIfThePermitTileGoesToTheBottomOfTheDeck() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		int i=0;
		PermitTile t= game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0];
		game.getGameTable().getRegionBoards().get(0).substitutePermitTiles();
		i=game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().getPermitTiles().size();
		assertEquals(t, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().getPermitTiles().get(i-3));
	}
	
}