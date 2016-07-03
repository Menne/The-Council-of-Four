package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import server.model.bonuses.Bonus;
import server.model.bonuses.ScoreBonus;
import server.model.gameTable.NobilityTrack;

public class NobilityTrackTest {

	@Test
	public void testGetNobilityTrack(){
		NobilityTrack nobilityTrack=new NobilityTrack(1);
		List<Set<Bonus>> track=new ArrayList<>();
		for(int i=0; i<1; i++)
			track.add(new HashSet<Bonus>());
		assertEquals(track,nobilityTrack.getTrack());
	}
	
	@Test
	public void testAddBonus() throws IOException{
		NobilityTrack nobilityTrack=new NobilityTrack(1);
		List<Set<Bonus>> track= new ArrayList<Set<Bonus>>();
		Set<Bonus> casella=new HashSet<>();
		Bonus bonus=new ScoreBonus(2);
		casella.add(bonus);
		track.add(casella);
		nobilityTrack.addBonus(0, bonus);
		assertEquals(casella, nobilityTrack.getTrack().get(0));
	}

}
