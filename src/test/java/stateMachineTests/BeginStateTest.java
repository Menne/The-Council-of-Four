
package stateMachineTests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.model.stateMachine.BeginState;
import server.model.stateMachine.State11;

public class BeginStateTest {

	@Test
	public void test() {
		BeginState state= new BeginState();
		assertEquals(state.pickPoliticsCardTransition().getClass(), State11.class);
	}

}
