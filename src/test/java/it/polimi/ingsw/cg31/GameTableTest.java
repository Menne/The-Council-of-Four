package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.Game;
import model.bonus.ScoreBonus;
import model.gameTable.CouncilBalcony;
import model.gameTable.CouncillorsReserve;
import model.gameTable.Map;
import model.gameTable.NobilityTrack;
import model.gameTable.PoliticsDeck;
import model.gameTable.RegionBoard;

public class GameTableTest {

	@Test
	public void test() throws IOException {
		Game game=new Game();
		assertEquals(Map.class, game.getGameTable().getMap().getClass());
		List<RegionBoard> regions= new ArrayList<RegionBoard>();
		assertEquals(regions.getClass(), game.getGameTable().getRegionBoards().getClass());
		assertEquals(CouncilBalcony.class, game.getGameTable().getCouncilOfKing().getClass());
		assertEquals(CouncillorsReserve.class, game.getGameTable().getCouncilReserve().getClass());
		assertEquals(PoliticsDeck.class, game.getGameTable().getPoliticsDeck().getClass());
		assertEquals(NobilityTrack.class, game.getGameTable().getNobilityTrack().getClass());
		List<ScoreBonus> bonus= new ArrayList<ScoreBonus>();
		assertEquals(bonus.getClass(), game.getGameTable().getKingRewardTiles().getClass());
	}

}
