package bonusTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.bonuses.interactiveBonus.PickPermitTileBonus;
import server.model.player.Player;
import server.model.stateMachine.State11;

public class PickPermitTileBonusTest {
	
	@Test
	public void testAssignBonus() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(game.getPlayers().get(0));
		State11 state= new State11();
		game.setState(state);
		game.getGameTable().getRegionBoards().get(0).uncoverPermitTiles();
		PickPermitTileBonus action= new PickPermitTileBonus();
		action.assignBonus(game);
		assertTrue(game.getState().getClass()==state.interactiveBonusTransition().getClass());
	}

	
}
