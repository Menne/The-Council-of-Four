package modelDTO.clientNotifies;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;

public class CityBonusDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2334036584265586488L;

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		ChooseCityActionDTO chooseCityActionDTO=new ChooseCityActionDTO();
	//	chooseCityActionDTO.setParser(gameDTOtoupdate).setParameters(gameDTOtoupdate.getParser());
	}

}
