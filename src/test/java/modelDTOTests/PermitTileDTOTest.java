package modelDTOTests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import server.model.bonus.Bonus;

public class PermitTileDTOTest {

	@Test
	public void testGettersAndSetters() {
		Set<CityDTO> buildablecities= new HashSet<>();
		Set<Bonus> bonuses= new HashSet<>();
		PermitTileDTO permitTile= new PermitTileDTO();
		permitTile.setBonuses(bonuses);
		permitTile.setBuildablecities(buildablecities);
		assertTrue(permitTile.getBonuses()==bonuses);
		assertTrue(permitTile.getBuildablecities()==buildablecities);
		Set<String> cities=new HashSet<>();
		for(CityDTO cityDTO : buildablecities)
			cities.add(cityDTO.getName());
		assertEquals(cities + "\t" + bonuses, permitTile.toString());
	}

}
