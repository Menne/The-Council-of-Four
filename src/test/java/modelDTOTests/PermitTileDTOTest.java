package modelDTOTests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import server.model.bonuses.Bonus;

public class PermitTileDTOTest {

	@Test
	public void testGettersAndSettersandHashCodeAndEqualsAndTostring() {
		Set<CityDTO> buildablecities= new HashSet<>();
		Set<Bonus> bonuses= new HashSet<>();
		PermitTileDTO permitTile= new PermitTileDTO();
		PermitTileDTO permitTile1= new PermitTileDTO();
		permitTile.setBonuses(bonuses);
		permitTile.setBuildablecities(buildablecities);
		permitTile1.setBonuses(bonuses);
		permitTile1.setBuildablecities(buildablecities);
		assertTrue(permitTile.getBonuses()==bonuses);
		assertTrue(permitTile.getBuildablecities()==buildablecities);
		Set<String> cities=new HashSet<>();
		for(CityDTO cityDTO : buildablecities)
			cities.add(cityDTO.getName());
		assertEquals(cities + "\t" + bonuses, permitTile.toString());
		assertEquals(961, permitTile.hashCode());
		assertEquals(permitTile, permitTile1);
	}

}
