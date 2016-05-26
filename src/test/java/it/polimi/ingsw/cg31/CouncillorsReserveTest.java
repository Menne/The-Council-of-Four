package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.gameTable.CardColour;
import server.model.gameTable.Councillor;
import server.model.gameTable.CouncillorsReserve;

public class CouncillorsReserveTest {

	@Test
	public void testGetter() {
		CardColour a=new CardColour("a");
		Councillor c=new Councillor(a);
		List<Councillor> lista= new ArrayList<Councillor>();
		lista.add(c);
		CouncillorsReserve reserve= new CouncillorsReserve(lista);
		assertTrue(reserve.getCouncillors().get(0)==c);
	}

	@Test
	public void testAddCouncillor() {
		CardColour a=new CardColour("a");
		Councillor c=new Councillor(a);
		Councillor d=new Councillor(a);
		List<Councillor> lista= new ArrayList<Councillor>();
		lista.add(c);
		CouncillorsReserve reserve= new CouncillorsReserve(lista);
		reserve.addConcullor(d);
		assertTrue(reserve.getCouncillors().size()==2);
		assertEquals(c, reserve.getCouncillors().get(0));
		assertEquals(d, reserve.getCouncillors().get(1));
		assertEquals(c, reserve.getCouncillors().get(1));
		assertEquals(d, reserve.getCouncillors().get(0));
	}
	
	@Test
	public void testRemoveCouncillor() {
		CardColour a=new CardColour("a");
		Councillor c=new Councillor(a);
		CardColour b=new CardColour("b");
		Councillor d=new Councillor(b);
		List<Councillor> lista= new ArrayList<Councillor>();
		lista.add(c);
		lista.add(d);
		CouncillorsReserve reserve= new CouncillorsReserve(lista);
		reserve.removeCouncillor(d);
		assertEquals(c, reserve.getCouncillors().get(0));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testExceptionOfRemoveIfTheDeckIsEmpty(){
		CardColour a=new CardColour("a");
		Councillor c=new Councillor(a);
		CardColour b=new CardColour("b");
		Councillor d=new Councillor(b);
		List<Councillor> lista= new ArrayList<Councillor>();
		lista.add(c);
		lista.add(d);
		CouncillorsReserve reserve= new CouncillorsReserve(lista);
		reserve.removeCouncillor(d);
		reserve.removeCouncillor(c);
		reserve.removeCouncillor(c);
	}
	
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testExceptionOfRemove(){
		CardColour a=new CardColour("a");
		Councillor c=new Councillor(a);
		CardColour b=new CardColour("b");
		Councillor d=new Councillor(b);
		List<Councillor> lista= new ArrayList<Councillor>();
		lista.add(c);
		CouncillorsReserve reserve= new CouncillorsReserve(lista);
		reserve.removeCouncillor(d);
	}
}
