package client.modelDTO.marketDTO;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.ModelDTO;

/**
 * This class provides all the informations about the market, but without logic
 * @author cg31
 *
 */
public class MarketDTO implements ModelDTO{
	
	private static final long serialVersionUID = -6208915629908932963L;
	private List<OfferDTO> offersList;
	private List<String> sellingPlayerList;
	private List<String> buyingPlayerList;

	/**
	 * Constructor of MarketDTO
	 */
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
