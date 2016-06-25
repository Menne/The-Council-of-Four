package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.clientNotifies.CityBonusDTONotify;
import server.model.player.Player;
import server.view.notifies.CityBonusNotify;

public class CityBonusNotifyTest {

	@Test
	public void test() {
		List<Player> interestedPlayers= new ArrayList<>();
		Player a= new Player("Andre");
		interestedPlayers.add(a);
		int numberOfCities= 3;
		CityBonusNotify notify= new CityBonusNotify(interestedPlayers, numberOfCities);
		assertEquals(CityBonusDTONotify.class, notify.toClientNotify().getClass());
		assertTrue(interestedPlayers==notify.sendTo());
	}

}
