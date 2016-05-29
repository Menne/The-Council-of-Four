package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.gameTable.CardColour;
import server.model.gameTable.PoliticsCard;

public class PoliticsCardsTest {

	@Test
	public void testIfCardsHaveTheAttributeCardColour() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		PoliticsCard card= game.getGameTable().getPoliticsDeck().pickCard();
		assertEquals(CardColour.class, card.getColour().getClass());
	}
	
	@Test
	public void testIfAddObjectToPlayerWorks() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		PoliticsCard card= game.getGameTable().getPoliticsDeck().pickCard();
		game.addPlayer("Luca");
		card.addObjectToPlayer(game.getPlayers().get(0));
		
		assertEquals(game.getPlayers().get(0).getHand().get(6), card);
	}
	
	@Test
	public void testRemoveCard() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		PoliticsCard card= game.getGameTable().getPoliticsDeck().pickCard();
		game.addPlayer("Luca");
		if(game.getPlayers().get(0).getHand().contains(card)){
			card.removeObjectFromPlayer(game.getPlayers().get(0));
			assertEquals(5, game.getPlayers().get(0).getHand().size());
		}
		else
			assertEquals(6, game.getPlayers().get(0).getHand().size());
	}
	
	@Test
	public void testToString() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		PoliticsCard card= game.getGameTable().getPoliticsDeck().pickCard();
		assertEquals(card.getColour().getColour(), card.toString());
	}
}
