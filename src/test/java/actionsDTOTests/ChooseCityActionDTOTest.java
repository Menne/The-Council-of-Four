package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.parser.ChooseCityBonusParser;

public class ChooseCityActionDTOTest {

	@Test
	public void testSetter() {
		GameDTO gameDTO=new GameDTO();
		CityDTO selectedCity= new CityDTO();
		ChooseCityActionDTO action= new ChooseCityActionDTO();
		action.setCity(selectedCity);
		assertFalse(action.checkIfParametersSetted());
		assertEquals(ChooseCityBonusParser.class, action.setParser(null, null).getClass());
		action.parametersSetted();
		assertTrue(action.checkIfParametersSetted());
		assertTrue(action.getSelectedCity()==selectedCity);
	}
}
