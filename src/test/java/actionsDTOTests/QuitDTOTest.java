package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.QuitDTO;

public class QuitDTOTest {

	@Test(expected=IllegalArgumentException.class)
	public void testException() {
		QuitDTO action= new QuitDTO();
		action.startMapper(null);
		assertFalse(true);
	}
}
