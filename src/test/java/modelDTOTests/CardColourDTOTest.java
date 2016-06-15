package modelDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.gameTableDTO.CardColourDTO;

public class CardColourDTOTest {

	@Test
	public void test() {
		CardColourDTO colour= new CardColourDTO();
		String name= "Blu";
		colour.setName(name);
		assertTrue(colour.getName()==name);
		assertEquals(name, colour.toString());
	}
	

}
