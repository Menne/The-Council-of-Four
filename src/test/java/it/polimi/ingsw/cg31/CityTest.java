package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import server.model.Game;
import server.model.bonus.Bonus;
import server.model.bonus.ScoreBonus;
import server.model.gameTable.City;
import server.model.gameTable.CityColour;
import server.model.gameTable.Emporium;
import server.model.player.Player;

public class CityTest {

	@Test
	public void testIfIsKingPresentReturnFalse() throws IOException {
		
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		assertNotEquals(game.getGameTable().getKing().getCity(), city);
	}

	
	@Test
	public void testGetRewardToken() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		assertEquals(set,city.getRewardToken());
	}
	
	@Test
	public void testConstructorDoesnTGiveBonusToKingCity() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("KingColour", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		Set<Bonus> set1= new HashSet<>();
		assertEquals(set1, city.getRewardToken());
	}
	
	@Test
	public void testGetRegion() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		assertTrue(game.getGameTable().getRegionBoards().get(0)==city.getRegion());
	}
	
	@Test
	public void testGetName() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		assertTrue("città1"== city.getName());
	}
	
	@Test
	public void testGetColour() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		assertTrue(colour==city.getColour());
	}
	
	@Test
	public void testAddEmporiumsAndGetEmporiumsAndTostringAndEqualsAndHashcode() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		City city1=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		Emporium e=new Emporium(game.getPlayers().get(0));
		city.addEmporium(e);
		city1.addEmporium(e);
		Set<Emporium> emporiums= new HashSet<>();
		emporiums.add(e);
		assertEquals(emporiums, city.getCityEmporiums());
		assertTrue(city.equals(city1));
		assertEquals(-1360137386, city.hashCode());
		assertEquals(city.getName()+"\t"+city.getCityEmporiums()+"\t"+city.getRewardToken()+"\n", city.toString());
	}
	
	@Test
	public void testAddAndGetNearCity() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("KingColour", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		City nearCity=new City("città2",game.getGameTable().getRegionBoards().get(0), colour, list);
		city.addNearCity(nearCity);
		Set<City> cities=new HashSet<>();
		cities.add(nearCity);
		assertEquals(cities, city.getNearCities());
	}
}

