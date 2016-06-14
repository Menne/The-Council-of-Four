package client.view.notifies;

import java.util.Scanner;

import client.view.ClientView;
import modelDTO.marketDTO.MarketDTO;

public class ClientMarketNotify implements ClientViewNotify {

	private MarketDTO marketUpdated;

	public ClientMarketNotify(MarketDTO marketDTO) {
		this.marketUpdated=marketDTO;
	}

	@Override
	public void stamp(Scanner scanner) {
		System.out.println(this.marketUpdated.toString());
	}

	@Override
	public void updateView(ClientView view) {
		view.displayMarket(this.marketUpdated);
	}

}
