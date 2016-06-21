package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.bonus.ScoreBonus;
import server.model.market.Market;
import server.model.player.Player;
import server.model.stateMachine.BeginState;

public class GameTest {
/*
	@Test
	public void testStartMethod() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		Player b = new Player("Luca");
		players.add(b);
		game.start(players);
		for(Player player : game.getPlayers()){
			assertEquals(0, player.getNobility());
			assertEquals(0, player.getScore());
			assertEquals(player.getPlayerNumber(), player.getNumberOfAssistants());
			assertEquals(player.getPlayerNumber()+9, player.getCoins());
			assertEquals(10, player.getRemainigEmporiums().size());
			assertEquals(6, player.getHand().size());
		}
		assertTrue(game.getCurrentPlayer()==a);
		assertTrue(game.getState().getClass()==BeginState.class);
		assertTrue(game.isLastLap()==false);
		assertTrue(game.getMarket().getClass()== Market.class);
	}
	
	@Test
	public void testNormalNextPlayer() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		Player b = new Player("Luca");
		players.add(b);
		game.start(players);
		assertTrue(game.getCurrentPlayer()==a);
		game.nextPlayer();
		assertTrue(game.getCurrentPlayer()==b);
		assertTrue(game.getPlayers().get(game.getPlayers().size()-1)==a);
	}
	
	@Test
	public void testFinalNextPlayerAndEndGame() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		Player b = new Player("Luca");
		players.add(b);
		Player c = new Player("Menne");
		players.add(c);
		game.start(players);
		c.setNobility(1);
		b.setScore(0);
		game.setLastLap(true);
		assertTrue(game.getCurrentPlayer()==a);
		game.nextPlayer();
		assertTrue(game.getCurrentPlayer()==b);
		assertTrue(game.getPlayers().size()==2);
		ScoreBonus bonus= new ScoreBonus(3);
		c.getPlayersFinalBonus().add(bonus);
		game.nextPlayer();
		game.nextPlayer();
		assertEquals(11,c.getScore());
		assertEquals(5,b.getScore());
		assertEquals(5,a.getScore());
	}
	
	
	@Test
	public void testFinalNobilityScoreBonus() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		Player b = new Player("Luca");
		players.add(b);
		Player c = new Player("Menne");
		players.add(c);
		game.start(players);
		game.getQuittedPlayers().addAll(players);
		c.setNobility(6);
		b.setNobility(6);
		game.assignBonusNobilityEndGame();
		assertEquals(5,c.getScore());
		assertEquals(5,b.getScore());
		assertEquals(0,a.getScore());
	}*/
}
