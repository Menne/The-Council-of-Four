package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import model.Game;
import model.bonus.Bonus;
import model.bonus.ScoreBonus;
import model.gameTable.City;
import model.gameTable.CityColour;
import model.gameTable.Emporium;

public class CityTest {

	@Test
	public void testIfIsKingPresentReturnFalse() throws IOException {
		Game game=new Game();
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<Bonus>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		assertEquals(false, city.getIsKingPresent());
	}

	@Test
	public void testIfIsKingPresentReturnTrueAfterTheMethodSetIsKingPresent() throws IOException {
		Game game=new Game();
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("KingColour", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<Bonus>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		city.setIsKingPresent(true);
		assertEquals(true, city.getIsKingPresent());
	}
	
	@Test
	public void testGetRewardToken() throws IOException {
		Game game=new Game();
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<Bonus>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		assertEquals(set, city.getRewardToken());
	}
	
	@Test
	public void testConstructorDoesnTGiveBonusToKingCity() throws IOException {
		Game game=new Game();
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("KingColour", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<Bonus>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		Set<Bonus> set1= new HashSet<Bonus>();
		assertEquals(set1, city.getRewardToken());
	}
	
	@Test
	public void testGetRegion() throws IOException {
		Game game=new Game();
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<Bonus>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		assertEquals(game.getGameTable().getRegionBoards().get(0), city.getRegion());
	}
	
	@Test
	public void testGetName() throws IOException {
		Game game=new Game();
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<Bonus>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		assertEquals("città1", city.getName());
	}
	
	@Test
	public void testGetColour() throws IOException {
		Game game=new Game();
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<Bonus>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		assertEquals(colour, city.getColour());
	}
	
	@Test
	public void testAddEmporiumsAndGetEmporiums() throws IOException {
		Game game=new Game();
		game.addPlayer();
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		Set<Bonus> set= new HashSet<Bonus>();
		Bonus bonus1= new ScoreBonus(1);
		set.add(bonus1);
		list.add(set);
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		Emporium e=new Emporium(game.getPlayers().get(0));
		city.addEmporium(e);
		Set<Emporium> emporiums= new HashSet<Emporium>();
		emporiums.add(e);
		assertEquals(emporiums, city.getCityEmporiums());
	}
	
	@Test
	public void testAddAndGetNearCity() throws IOException {
		Game game=new Game();
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("KingColour", bonus);
		List<Set<Bonus>> list= new ArrayList<Set<Bonus>>();
		City city=new City("città1",game.getGameTable().getRegionBoards().get(0), colour, list);
		City nearCity=new City("città2",game.getGameTable().getRegionBoards().get(0), colour, list);
		city.addNearCity(nearCity);
		Set<City> cities=new HashSet<City>();
		cities.add(nearCity);
		assertEquals(cities, city.getNearCities());
	}
}

