package modelDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;

public class CouncillorDTOTest {

	@Test
	public void test() {
		CouncillorDTO councillor= new CouncillorDTO();
		CardColourDTO colour= new CardColourDTO();
		String a="blue";
		colour.setName(a);
		CouncillorDTO councillor1= new CouncillorDTO(colour);
		councillor.setColour(colour);
		councillor1.setColour(colour);
		assertTrue(councillor.getColour()==colour);
		assertTrue(councillor.equals(councillor1));
		assertTrue(councillor.toString()==a);
		assertEquals(3027096, councillor.hashCode());
	}

}
