package actionsDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import client.modelDTO.actionsParametersSetters.bonusActionsParametersSetters.ChooseCityBonusParametersSetter;
import client.modelDTO.gameTableDTO.CityDTO;

public class ChooseCityActionDTOTest {

	@Test
	public void testSetter() {
		List<CityDTO> selectedCities= new ArrayList<CityDTO>();
		ChooseCityActionDTO action= new ChooseCityActionDTO(1);
		action.setSelectedCities(selectedCities);
		assertFalse(action.checkIfParametersSet());
		assertEquals(ChooseCityBonusParametersSetter.class, action.setParser().getClass());
		action.parametersSet();
		assertTrue(action.checkIfParametersSet());
		assertTrue(action.getSelectedCities()==selectedCities);
		assertEquals("b1: get bonus!", action.toString());
	}
}
