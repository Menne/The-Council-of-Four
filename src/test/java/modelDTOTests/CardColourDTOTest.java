package modelDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.gameTableDTO.CardColourDTO;

public class CardColourDTOTest {

	@Test
	public void test() {
		String name= "Blu";
		CardColourDTO colour= new CardColourDTO();
		CardColourDTO colour1= new CardColourDTO(name);
		colour.setName(name);
		assertTrue(colour.getName()==name);
		assertEquals(name, colour.toString());
		assertEquals(colour, colour1);
		assertEquals(66922, colour.hashCode());
	}
	

}
