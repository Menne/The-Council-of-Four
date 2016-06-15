package it.polimi.ingsw.cg31;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.gameTable.PermitDeck;
import server.model.gameTable.PermitTile;
import server.model.player.Player;

public class PermitDeckTest {

	@Test
	public void testIfPickPermitTileReturnsTheFisrstTileFromTheDeck() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		PermitTile t= game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().getPermitTiles().get(0);
		assertTrue(t== game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().pickPermitTile());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testIfPickPermitTileThrowsException() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		while(!game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().getPermitTiles().isEmpty())
			game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().pickPermitTile();
		game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().pickPermitTile();
	}
	
	@Test
	public void testIfAddOnBottomAddsThePermitTileOnTheBottomOfTheDeck() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		PermitTile t=new PermitTile(null, null, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().addOnBottom(t);
		assertEquals(t, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().getPermitTiles().
				get(game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().getPermitTiles().size()-1));
	}
	
	@Test(expected=NullPointerException.class)
	public void testIfAddOnBottomThrowsException() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck().addOnBottom(null);
	}

	
	@Test
	public void testGetterOfPermitTiles(){
		PermitDeck deck=new PermitDeck();
		PermitTile tile1= new PermitTile(null, null, deck);
		PermitTile tile2= new PermitTile(null, null, deck);
		List<PermitTile> list = new ArrayList<>();
		list.add(tile1);
		list.add(tile2);
		assertEquals(list, deck.getPermitTiles());
	}
}
