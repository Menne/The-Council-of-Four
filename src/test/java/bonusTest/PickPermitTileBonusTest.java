package bonusTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.bonus.PickPermitTileBonus;
import server.model.gameTable.PermitTile;
import server.model.stateMachine.State11;

public class PickPermitTileBonusTest {

	@Test(expected=IllegalArgumentException.class)
	public void testExceptionNumberTooBig() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		new PickPermitTileBonus(game.getGameTable().getRegionBoards().get(0), 3);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExceptionNumberTooLow() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		new PickPermitTileBonus(game.getGameTable().getRegionBoards().get(0), -1);
	}
	
	@Test
	public void testIfBonusGiveThePermitTileToPlayer() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(game.getPlayers().get(0));
		game.setState(new State11());
		game.getGameTable().getRegionBoards().get(0).uncoverPermitTiles();
		int temp= game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().size();
		PermitTile tile= game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[1];
		PickPermitTileBonus bonus= new PickPermitTileBonus(game.getGameTable().getRegionBoards().get(0), 1);
		bonus.assignBonus(game);
		assertEquals(temp+1, game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().size());
		assertEquals(tile, game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(0));
	}

	@Test
	public void testIfBonusGiveePermitTileToPlayer() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(game.getPlayers().get(0));
	}
	
	@Test
	public void testIfBeThePermitTileToPlayer() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(game.getPlayers().get(0));
	}
}
