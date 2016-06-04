package server.view.notifies;

import java.util.List;

import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.MarketDTONotify;
import modelDTO.marketDTO.MarketDTO;
import players.Player;
import server.model.market.Market;

public class MarketNotify implements ViewNotify {
	
	private Market market;
	private List<Player> interestedPlayers;

	public MarketNotify(Market market, List<Player> interestedPlayers) {
		this.market=market;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		MarketDTO marketDTO=new MarketDTO();
		marketDTO.map(this.market);
		return new MarketDTONotify(marketDTO);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
