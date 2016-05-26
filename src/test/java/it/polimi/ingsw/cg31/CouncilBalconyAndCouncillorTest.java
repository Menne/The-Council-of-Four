package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.gameTable.CardColour;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.CouncillorsReserve;


public class CouncilBalconyAndCouncillorTest {

	@Test
	public void testGetNumberOfCouncillors() {
		assertEquals(4, CouncilBalcony.getNumberofcouncillors());
	}
	

	@Test
	public void testGetColourOfCouncillor() {
		CardColour x=new CardColour("x");
		Councillor a=new Councillor(x);

		assertEquals(x, a.getColour());
	}
	
	@Test
	public void test() {
		CardColour x=new CardColour("x");
		CardColour y=new CardColour("y");
		CardColour z=new CardColour("z");
		CardColour w=new CardColour("w");
		
		Councillor a=new Councillor(x);
		Councillor b=new Councillor(y);
		Councillor c=new Councillor(z);
		Councillor d=new Councillor(w);
		
		List<Councillor> councillors= new ArrayList<Councillor>();
		councillors.add(a);
		councillors.add(b);
		councillors.add(c);
		councillors.add(d);
		councillors.add(a);
		
		CouncillorsReserve reserve= new CouncillorsReserve(councillors);
		CouncilBalcony balcony= new CouncilBalcony(reserve);
		balcony.substituteCouncillor(a);
		assertTrue(true);
	}
}
