package client.view.notifies;

import java.util.List;

import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.view.ClientView;

public class ClientPermitTileBonusNotify implements ClientViewNotify {

	private List<PermitTileDTO> availablePermitTiles;

	public ClientPermitTileBonusNotify(List<PermitTileDTO> availablePermitTiles) {
		this.availablePermitTiles=availablePermitTiles;
	}

	@Override
	public void updateView(ClientView view) {
		view.PurchasedPermitTileBonus(this.availablePermitTiles);
	}

}
