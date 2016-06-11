package DTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelDTO.gameTableDTO.CityColourDTO;
import server.model.gameTable.CityColour;

public class CityColourDTOtest {

	@Test
	public void test() {
		CityColourDTO colour= new CityColourDTO();
		String name= "blu";
		colour.setName(name);
		assertTrue(colour.getName()==name);
	}
	
	@Test
	public void testMapping(){
		CityColour realColour= new CityColour("blu", null);
		CityColourDTO colour= new CityColourDTO();
		colour.map(realColour);
		assertTrue(colour.getName()==realColour.getName());
	}

	@Test(expected=NullPointerException.class)
	public void testException(){
		CityColourDTO colour= new CityColourDTO();
		colour.map(null);
	}
}
