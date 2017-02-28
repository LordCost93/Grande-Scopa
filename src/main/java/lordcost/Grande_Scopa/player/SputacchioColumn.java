package lordcost.Grande_Scopa.player;

import java.util.ArrayList;
import java.util.List;

import lordcost.Grande_Scopa.cards.SupportCard;

public class SputacchioColumn {

	private List<SupportCard> pile;

	public List<SupportCard> getPile() {
		return pile;
	}

	public SputacchioColumn() {
		this.pile = new ArrayList<SupportCard>();			
	}
	
	public void setPile(List<SupportCard> pile) {
		this.pile = pile;
	}
	
}
