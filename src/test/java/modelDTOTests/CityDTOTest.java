package modelDTOTests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import modelDTO.gameTableDTO.CityColourDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.GenericPlayerDTO;
import server.model.bonus.Bonus;

public class CityDTOTest {

	@Test
	public void testGettersAndSetters() {
		CityDTO city= new CityDTO();
		String name="città";
		city.setName(name);
		CityColourDTO colour= new CityColourDTO();
		city.setColour(colour);
		Set<GenericPlayerDTO> emporiums= new HashSet<>();
		city.setBuildedEmporiums(emporiums);
		Set<Bonus> rewardToken= new HashSet<>();
		city.setRewardToken(rewardToken);
		assertTrue(city.getBuildedEmporiums()==emporiums);
		assertTrue(city.getColour()==colour);
		assertTrue(city.getName()==name);
		assertTrue(city.getRewardToken()==rewardToken);
	}

}
