package it.polimi.ingsw.cg31;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import players.Player;
import server.model.market.Market;

public class SortMarketListTest {

	@Test
	public void ifSellingListSortWork(){
		Player a =new Player("a");
		Player b =new Player("b");
		Player c =new Player("c");
		
		a.setPlayerNumber(1);
		b.setPlayerNumber(3);
		c.setPlayerNumber(2);
		
		a.setScore(1);
		b.setScore(3);
		c.setScore(4);
		
		Market market=new Market();
		market.getSellingPlayerList().addAll(Arrays.asList(b,c,a));
		market.sortSellingPlayerList();
		
		assertEquals(market.getSellingPlayerList().get(0), a);
		assertEquals(market.getSellingPlayerList().get(1), c);
		assertEquals(market.getSellingPlayerList().get(2), b);
		
	}

}
