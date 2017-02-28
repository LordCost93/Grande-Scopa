package lordcost.Grande_Scopa.player;

import java.util.ArrayList;
import java.util.List;

import lordcost.Grande_Scopa.cards.SupportCard;

public class SputacchioPersonalBoard {

	private SupportCard[] spider;
	private List<SupportCard> jollyContainer;
	private SupportCard[] freecell;
	private SputacchioColumn[] columns;
	
	public SupportCard[] getSpider() {
		return spider;
	}
	public List<SupportCard> getJollyContainer() {
		return jollyContainer;
	}
	public SupportCard[] getFreecell() {
		return freecell;
	}
	public SputacchioColumn[] getColumns() {
		return columns;
	}
	public SputacchioPersonalBoard() {
		
		this.spider = new SupportCard[6];
		this.jollyContainer = new ArrayList<SupportCard>();
		this.freecell = new SupportCard[2];
		this.columns = new SputacchioColumn[6];		
	}
}
