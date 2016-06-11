package modelDTO.marketDTO;

import java.util.ArrayList;
import java.util.List;

import modelDTO.ModelDTO;
import server.model.market.Market;

public class MarketDTO implements ModelDTO<Market>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6208915629908932963L;
	private List<OfferDTO> offersList;
	private List<String> sellingPlayerList;
	private List<String> buyingPlayerList;

	public MarketDTO() {
		this.offersList=new ArrayList<>();
		this.sellingPlayerList=new ArrayList<>();
		this.buyingPlayerList=new ArrayList<>();
	}
	

	public List<OfferDTO> getOffersList() {
		return this.offersList;
	}

	public void setOffersList(List<OfferDTO> offersList) {
		this.offersList = offersList;
	}

	public List<String> getSellingPlayerList() {
		return this.sellingPlayerList;
	}

	public void setSellingPlayerList(List<String> sellingPlayerList) {
		this.sellingPlayerList = sellingPlayerList;
	}

	public List<String> getBuyingPlayerList() {
		return this.buyingPlayerList;
	}

	public void setBuyingPlayerList(List<String> buyingPlayerList) {
		this.buyingPlayerList = buyingPlayerList;
	}


	@Override
	public String toString() {
		return "What is in the market: " + offersList + "]";
	}



}
