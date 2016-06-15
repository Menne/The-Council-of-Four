package modelDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.gameTableDTO.CityColourDTO;

public class CityColourDTOtest {

	@Test
	public void test() {
		CityColourDTO colour= new CityColourDTO();
		String name= "blu";
		colour.setName(name);
		assertTrue(colour.getName()==name);
		assertEquals(name, colour.toString());
	}
	
}
