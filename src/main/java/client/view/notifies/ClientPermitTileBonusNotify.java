package client.view.notifies;

import java.util.List;
import java.util.Scanner;

import modelDTO.gameTableDTO.PermitTileDTO;

public class ClientPermitTileBonusNotify implements ClientViewNotify {

	private List<PermitTileDTO> availablePermitTiles;

	public ClientPermitTileBonusNotify(List<PermitTileDTO> availablePermitTiles) {
		this.availablePermitTiles=availablePermitTiles;
	}

	@Override
	public void stamp(Scanner scanner) {
		System.out.println(availablePermitTiles);
	}

}
