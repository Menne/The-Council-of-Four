package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import model.bonus.ScoreBonus;
import model.gameTable.City;
import model.gameTable.CityColour;
import model.gameTable.RegionBoard;

public class CityColourTest {

	@Test
	public void testBonusNotAvailable() {
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		colour.notBonusAvailable();
		assertFalse(colour.isBonusAvailable());
	}

	@Test
	public void testBonusAvailable() {
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		assertTrue(colour.isBonusAvailable());
	}
	
	@Test
	public void testGetName() {
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		assertEquals("blu", colour.getName());
	}
	
	@Test
	public void testGetColourBonus() {
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("blu", bonus);
		assertEquals(bonus, colour.getColorBonus());
	}
	
	@Test
	public void test() {
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("KingColour", bonus);
		RegionBoard r=new RegionBoard(null, null, null, null);
		City city=new City("a", r , colour, null);
		colour.addCityOfThisColour(city);
		Set<City> colourCity= new HashSet<City>();
		colourCity.add(city);
		assertEquals(colourCity, colour.getCitiesOfThisColour());
	}
}
