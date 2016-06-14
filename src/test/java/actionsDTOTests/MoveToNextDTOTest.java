package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelDTO.actionsDTO.MoveToNextDTO;
import server.model.actions.MoveToNext;

public class MoveToNextDTOTest {

	@Test
	public void testToString() {
		MoveToNextDTO action= new MoveToNextDTO();
		assertEquals("sk: skip this passage", action.toString());
		assertEquals(MoveToNext.class, action.startMapper(null).getClass());
	}

}
