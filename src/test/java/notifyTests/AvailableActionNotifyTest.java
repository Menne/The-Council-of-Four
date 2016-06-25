package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.clientNotifies.AvailableActionsDTONotify;
import server.model.actions.Action;
import server.model.actions.standardActions.AdditionalMainAction;
import server.model.player.Player;
import server.view.notifies.AvailableActionsNotify;

public class AvailableActionNotifyTest {

	@Test
	public void test() {
		List<Action> availableActions= new ArrayList<>();
		List<Player> interestedPlayers= new ArrayList<>();
		String message= "message";
		AdditionalMainAction action= new AdditionalMainAction();
		availableActions.add(action);
		Player a= new Player("Andre");
		interestedPlayers.add(a);
		List<ActionDTO> actionsDTO= new ArrayList<>();
		actionsDTO.add(action.map());
		AvailableActionsNotify notify= new AvailableActionsNotify(availableActions, interestedPlayers, message);
		assertEquals(AvailableActionsDTONotify.class, notify.toClientNotify().getClass());
		assertTrue(notify.sendTo()==interestedPlayers);
	}

}
