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

	/**
	 * @return the list of offers DTO in the market
	 */
	public List<OfferDTO> getOffersList() {
		return this.offersList;
	}

	/**
	 * Sets the list of offers DTO to the market
	 * @param offersList is the list of offers DTO of the market
	 */
	public void setOffersList(List<OfferDTO> offersList) {
		this.offersList = offersList;
	}

	/**
	 * @return the list of selling players
	 */
	public List<String> getSellingPlayerList() {
		return this.sellingPlayerList;
	}

	/**
	 * Sets the list of selling players to the market
	 * @param sellingPlayerList is the list of selling players
	 */
	public void setSellingPlayerList(List<String> sellingPlayerList) {
		this.sellingPlayerList = sellingPlayerList;
	}

	/**
	 * @return the list of buying players
	 */
	public List<String> getBuyingPlayerList() {
		return this.buyingPlayerList;
	}

	/**
	 * Sets the list of buying players to the market
	 * @param buyingPlayerList is the list of buying players
	 */
	public void setBuyingPlayerList(List<String> buyingPlayerList) {
		this.buyingPlayerList = buyingPlayerList;
	}


	@Override
	public String toString() {
		return "What is in the market: " + offersList + "]";
	}


}
