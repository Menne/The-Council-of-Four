package modelDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.gameTableDTO.CityColourDTO;

public class CityColourDTOtest {

	@Test
	public void test() {
		CityColourDTO colour= new CityColourDTO();
		CityColourDTO colour1= new CityColourDTO();
		String name= "blu";
		colour.setName(name);
		colour1.setName(name);
		assertTrue(colour.getName()==name);
		assertEquals(name, colour.toString());
		assertEquals(97674, colour.hashCode());
		assertEquals(colour, colour1);
	}
	
}
