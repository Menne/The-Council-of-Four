package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.bonus.Bonus;
import server.model.bonus.ScoreBonus;
import server.model.gameTable.NobilityTrack;

public class NobilityTrackTest {

	@Test
	public void testGetNobilityTrack() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		NobilityTrack nobilityTrack=new NobilityTrack(20);
		assertEquals(nobilityTrack.getClass(), game.getGameTable().getNobilityTrack().getClass());
	}
	
	@Test
	public void test() throws IOException{
		NobilityTrack nobilityTrack=new NobilityTrack(1);
		List<Set<Bonus>> track= new ArrayList<Set<Bonus>>();
		Set<Bonus> casella=new HashSet<Bonus>();
		Bonus bonus=new ScoreBonus(2);
		casella.add(bonus);
		track.add(casella);
		nobilityTrack.addBonus(0, bonus);
		assertEquals(casella, nobilityTrack.getTrack().get(0));
	}

}
