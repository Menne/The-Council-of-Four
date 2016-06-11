package DTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelDTO.gameTableDTO.CardColourDTO;
import server.model.gameTable.CardColour;

public class CardColourDTOTest {

	@Test
	public void test() {
		CardColourDTO colour= new CardColourDTO();
		String name= "Blu";
		colour.setName(name);
		assertTrue(colour.getName()==name);
	}
	
	@Test
	public void testMapping(){
		CardColour realColour= new CardColour("blu");
		CardColourDTO colour= new CardColourDTO();
		colour.map(realColour);
		assertTrue(colour.getName()==realColour.getColour());
	}

	@Test(expected=NullPointerException.class)
	public void testException(){
		CardColourDTO colour= new CardColourDTO();
		colour.map(null);
	}

}
