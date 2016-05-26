package modelDTO.gameTableDTO;

import java.util.ArrayList;
import java.util.List;

import modelDTO.ModelDTO;
import server.model.market.Market;
import server.model.market.Offer;

public class MarketDTO implements ModelDTO<Market>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6208915629908932963L;
	private ArrayList<Offer> offersList;
	private List<PlayerDTO> sellingPlayerList;
	private ArrayList<PlayerDTO> buyingPlayerList;

	public MarketDTO(List<PlayerDTO> players){
		this.offersList=new ArrayList<Offer>();
		this.sellingPlayerList=players;
		this.buyingPlayerList=new ArrayList<PlayerDTO>();
		this.buyingPlayerList.addAll(players);
	}
	
	
	@Override
	public void map(Market realObject) {
		
	}
	
	

}
