package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.QuitDTORMI;
import client.view.rmi.ClientRMIViewRemote;

public class QuitDTORMITest {

	@Test
	public void testGetter() {
		ClientRMIViewRemote quittingView = null;
		QuitDTORMI action= new QuitDTORMI(quittingView);
		assertTrue(action.getQuittingView()==quittingView);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testException() {
		ClientRMIViewRemote quittingView = null;
		QuitDTORMI action= new QuitDTORMI(quittingView);
		action.startMapper(null);
		assertTrue(false);
	}
}
