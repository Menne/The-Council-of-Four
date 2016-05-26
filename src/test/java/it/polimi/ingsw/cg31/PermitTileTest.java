package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import server.model.Game;
import server.model.bonus.AssistantsBonus;
import server.model.bonus.Bonus;
import server.model.bonus.ScoreBonus;
import server.model.gameTable.City;
import server.model.gameTable.PermitDeck;
import server.model.gameTable.PermitTile;

public class PermitTileTest {
	
	@Test
	public void testGetterOfBuildableCities() throws IOException {
		Game game=new Game();
		Set<City> cities= new HashSet<City>();
		cities.addAll(game.getGameTable().getRegionBoards().get(0).getRegionCities());
		Bonus bonus1=new ScoreBonus(1);
		Bonus bonus2=new AssistantsBonus(2);
		Set<Bonus> bonuses=new HashSet<Bonus>();
		bonuses.add(bonus2);
		bonuses.add(bonus1);
		PermitDeck deck= new PermitDeck();
		PermitTile t=new PermitTile(cities, bonuses, deck);
		assertEquals(game.getGameTable().getRegionBoards().get(0).getRegionCities(), t.getBuildableCities());
	}

	@Test
	public void testGetterOfBonuses() throws IOException {
		Game game=new Game();
		Set<City> cities= new HashSet<City>();
		cities.addAll(game.getGameTable().getRegionBoards().get(0).getRegionCities());
		Bonus bonus1=new ScoreBonus(1);
		Bonus bonus2=new AssistantsBonus(2);
		Set<Bonus> bonuses=new HashSet<Bonus>();
		bonuses.add(bonus2);
		bonuses.add(bonus1);
		PermitDeck deck= new PermitDeck();
		PermitTile t=new PermitTile(cities, bonuses, deck);
		assertEquals(bonuses, t.getBonus());
	}
	
	@Test
	public void testAddObjectToPlayer() throws IOException {
		Game game=new Game();
		game.addPlayer("Luca");
		Set<City> cities= new HashSet<City>();
		cities.addAll(game.getGameTable().getRegionBoards().get(0).getRegionCities());
		Bonus bonus1=new ScoreBonus(1);
		Bonus bonus2=new AssistantsBonus(2);
		Set<Bonus> bonuses=new HashSet<Bonus>();
		bonuses.add(bonus2);
		bonuses.add(bonus1);
		PermitDeck deck= new PermitDeck();
		PermitTile t=new PermitTile(cities, bonuses, deck);
		t.addObjectToPlayer(game.getPlayers().get(0));
		assertTrue(game.getPlayers().get(0).getPlayersPermitTilesTurnedUp().contains(t));
	}
	
	@Test
	public void testRemoveObjectFromPlayer() throws IOException {
		Game game=new Game();
		game.addPlayer("Luca");
		Set<City> cities= new HashSet<City>();
		cities.addAll(game.getGameTable().getRegionBoards().get(0).getRegionCities());
		Bonus bonus1=new ScoreBonus(1);
		Bonus bonus2=new AssistantsBonus(2);
		Set<Bonus> bonuses=new HashSet<Bonus>();
		bonuses.add(bonus2);
		bonuses.add(bonus1);
		PermitDeck deck= new PermitDeck();
		PermitTile t=new PermitTile(cities, bonuses, deck);
		t.addObjectToPlayer(game.getPlayers().get(0));
		t.removeObjectFromPlayer(game.getPlayers().get(0));
		assertFalse(game.getPlayers().get(0).getPlayersPermitTilesTurnedUp().contains(t));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIfRemoveObjectFromPlayerThrowsException() throws IOException {
		Game game=new Game();
		game.addPlayer("Luca");
		Set<City> cities= new HashSet<City>();
		cities.addAll(game.getGameTable().getRegionBoards().get(0).getRegionCities());
		Bonus bonus1=new ScoreBonus(1);
		Bonus bonus2=new AssistantsBonus(2);
		Set<Bonus> bonuses=new HashSet<Bonus>();
		bonuses.add(bonus2);
		bonuses.add(bonus1);
		PermitDeck deck= new PermitDeck();
		PermitTile t=new PermitTile(cities, bonuses, deck);
		t.removeObjectFromPlayer(game.getPlayers().get(0));
	}
}
