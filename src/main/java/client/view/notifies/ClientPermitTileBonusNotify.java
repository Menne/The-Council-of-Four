package client.view.notifies;

import java.util.List;
import client.view.ClientView;
import modelDTO.gameTableDTO.PermitTileDTO;

public class ClientPermitTileBonusNotify implements ClientViewNotify {

	private List<PermitTileDTO> availablePermitTiles;

	public ClientPermitTileBonusNotify(List<PermitTileDTO> availablePermitTiles) {
		this.availablePermitTiles=availablePermitTiles;
	}

	@Override
	public void updateView(ClientView view) {
		// TODO Auto-generated method stub
		
	}

}
