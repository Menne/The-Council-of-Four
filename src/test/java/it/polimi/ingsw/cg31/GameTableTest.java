package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.bonus.ScoreBonus;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.CouncillorsReserve;
import server.model.gameTable.Map;
import server.model.gameTable.NobilityTrack;
import server.model.gameTable.PoliticsDeck;
import server.model.gameTable.RegionBoard;


public class GameTableTest {

	@Test
	public void test() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		assertEquals(Map.class, game.getGameTable().getMap().getClass());
		List<RegionBoard> regions= new ArrayList<>();
		assertEquals(regions.getClass(), game.getGameTable().getRegionBoards().getClass());
		assertEquals(CouncilBalcony.class, game.getGameTable().getCouncilOfKing().getClass());
		assertEquals(CouncillorsReserve.class, game.getGameTable().getCouncilReserve().getClass());
		assertEquals(PoliticsDeck.class, game.getGameTable().getPoliticsDeck().getClass());
		assertEquals(NobilityTrack.class, game.getGameTable().getNobilityTrack().getClass());
		List<ScoreBonus> bonus= new ArrayList<>();
		assertEquals(bonus.getClass(), game.getGameTable().getKingRewardTiles().getClass());
	}

}
