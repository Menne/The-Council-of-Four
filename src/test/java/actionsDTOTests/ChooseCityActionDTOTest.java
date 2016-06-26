package actionsDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.actionsDTO.actionsParametersSetters.bonusActionsSetters.ChooseCityBonusParser;
import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import client.modelDTO.gameTableDTO.CityDTO;

public class ChooseCityActionDTOTest {

/*	@Test
	public void testSetter() {
		List<CityDTO> selectedCities= new ArrayList<CityDTO>();
		ChooseCityActionDTO action= new ChooseCityActionDTO(1);
		action.setSelectedCities(selectedCities);
		assertFalse(action.checkIfParametersSetted());
		assertEquals(ChooseCityBonusParser.class, action.setParser().getClass());
		action.parametersSetted();
		assertTrue(action.checkIfParametersSetted());
		assertTrue(action.getSelectedCities()==selectedCities);
	}*/
}
