package actionsDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.actionsDTO.actionsParametersSetters.BuildByKingParser;
import client.modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;

public class BuildByKingDTOTest {

	@Test
	public void testGettersAndSetters() {
		CityDTO selectedCity= new CityDTO();
		List<CardColourDTO> cardsToDescard= new ArrayList<>();
		BuildByKingDTO action= new BuildByKingDTO();
		action.setCardsToDescard(cardsToDescard);
		action.setSelectedCity(selectedCity);
		assertFalse(action.checkIfParametersSetted());
		assertEquals(BuildByKingParser.class, action.setParser().getClass());
		assertTrue(action.getCardsToDescard()==cardsToDescard);
		assertTrue(action.getSelectedCity()==selectedCity);
		action.parametersSetted();
		assertTrue(action.checkIfParametersSetted());
		assertEquals("m4: build an emporium with the help of the king", action.toString());
	}

}
