package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelDTO.actionsDTO.PickPoliticsCardDTO;
import server.model.actions.PickPoliticsCard;

public class PickPoliticsCardDTOTest {

	@Test
	public void testToStringAndMapping() {
		PickPoliticsCardDTO action= new PickPoliticsCardDTO();
		assertEquals("pc: pick a politics card", action.toString());
		assertEquals(PickPoliticsCard.class, action.startMapper(null).getClass());
	}
}
