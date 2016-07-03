package modelDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import client.modelDTO.gameTableDTO.CityColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.EmporiumDTO;
import client.modelDTO.gameTableDTO.RewardTokenDTO;
import server.model.bonuses.Bonus;

public class CityDTOTest {

	@Test
	public void testGettersAndSetters() {
		CityDTO city= new CityDTO();
		String name="città";
		city.setName(name);
		CityColourDTO colour= new CityColourDTO();
		city.setColour(colour);
		Set<EmporiumDTO> emporiums= new HashSet<>();
		city.setBuildedEmporiums(emporiums);
		Set<Bonus> rewardToken= new HashSet<>();
		city.setRewardToken(new RewardTokenDTO(rewardToken));
		assertTrue(city.getBuildedEmporiums()==emporiums);
		assertTrue(city.getColour()==colour);
		assertTrue(city.getName()==name);
		assertTrue(city.getRewardToken().getBonuses()==rewardToken);
		List<String> emporiumsPlayers=new ArrayList<>();
		for(EmporiumDTO player : emporiums)
			emporiumsPlayers.add(player.getPlayerName());
		assertEquals(name+"\t"+emporiums+"\t"+rewardToken+"\n", city.toString());
		assertEquals(94671961, city.hashCode());
		CityDTO city1= new CityDTO(name);
		assertEquals(city, city1);
	}
}
