package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.ChooseMapDTO;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class ChooseMapDTOTest {

	@Test
	public void test() {
		int mapNumber=1;
		ChooseMapDTO action= new ChooseMapDTO(mapNumber);
		assertTrue(action.getMapNumber()==mapNumber);
	}

	@Test(expected=IllegalStateException.class)
	public void testException() {
		ChooseMapDTO action= new ChooseMapDTO(1);
		ActionMapperVisitor mapper=null;
		action.startMapper(mapper);
		assertFalse(true);
	}
}
