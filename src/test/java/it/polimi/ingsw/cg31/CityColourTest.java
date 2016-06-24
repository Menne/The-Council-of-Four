package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import server.model.bonus.ScoreBonus;
import server.model.gameTable.City;
import server.model.gameTable.CityColour;
import server.model.gameTable.ColourBonusTile;
import server.model.gameTable.RegionBoard;

public class CityColourTest {

	@Test
	public void testBonusNotAvailable() {
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("Blue", new ColourBonusTile(bonus, "Blue"));
		colour.notBonusAvailable();
		assertFalse(colour.isBonusAvailable());
	}

	@Test
	public void testBonusAvailable() {
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("Blue", new ColourBonusTile(bonus, "Blue"));
		assertTrue(colour.isBonusAvailable());
	}
	
	@Test
	public void testGetName() {
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("Blue", new ColourBonusTile(bonus, "Blue"));
		assertTrue("Blue"== colour.getName());
	}
	
	@Test
	public void testGetColourBonus() {
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("Blue", new ColourBonusTile(bonus, "Blue"));
		assertTrue(bonus==colour.getColorBonus().getBonus());
	}
	
	@Test
	public void testAddCityOfThisColour() {
		ScoreBonus bonus= new ScoreBonus(1);
		CityColour colour= new CityColour("KingColour", new ColourBonusTile(bonus, "KingColour"));
		RegionBoard r=new RegionBoard(null, null, null, null);
		City city=new City("a", r , colour, null);
		colour.addCityOfThisColour(city);
		Set<City> colourCity= new HashSet<>();
		colourCity.add(city);
		assertEquals(colourCity, colour.getCitiesOfThisColour());
	} 
	
	@Test
	public void testGettersAndSettersHashCodeAndEquals(){
		String name="Blue";
		ScoreBonus colorBonus= new ScoreBonus(5);
		Set<City> citiesOfThisColour= new HashSet<>();
		City c= null;
		citiesOfThisColour.add(c);
		CityColour cityColour= new CityColour(name, new ColourBonusTile(colorBonus, name));
		cityColour.addCityOfThisColour(c);
		assertEquals(citiesOfThisColour, cityColour.getCitiesOfThisColour());
		assertTrue(cityColour.getName()==name);
		assertTrue(cityColour.getColorBonus().getBonus()==colorBonus);
		assertTrue(cityColour.isBonusAvailable());
		cityColour.notBonusAvailable();
		assertFalse(cityColour.isBonusAvailable());
		assertEquals(2073753, cityColour.hashCode());
		CityColour cityColour1= new CityColour(name, new ColourBonusTile(colorBonus, name));
		cityColour1.addCityOfThisColour(c);
		assertTrue(cityColour.equals(cityColour1));
	}
}

