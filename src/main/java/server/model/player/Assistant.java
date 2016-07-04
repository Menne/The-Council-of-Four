package server.model.player;

import server.model.market.Marketable;

/**
 * modelize assistants and it contains only the methods used for the market (add to player and remove from player)
 * @author cg31
 *
 */
public class Assistant implements Marketable {
	
	private final int id;
	
	/**
	 * Constructor of an assistant
	 */
	public Assistant() {
		this.id=0;
	}

	@Override
	public void addObjectToPlayer(Player player) {
		player.incrementAssistants(1);
	}
	
	@Override
	public void removeObjectFromPlayer(Player player){
		if (player.getNumberOfAssistants()<=0)
			throw new IllegalArgumentException("the selling player hasn't assistants to sell");
		player.decrementAssistants(1);
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assistant other = (Assistant) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
