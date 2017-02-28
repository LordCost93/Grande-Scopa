package lordcost.Grande_Scopa.engine;

import java.util.ArrayList;
import java.util.Collections;

import lordcost.Grande_Scopa.cards.MainCard;
import lordcost.Grande_Scopa.cards.Suit;
import lordcost.Grande_Scopa.moveController.Move;

public class FilterUtility {
	
	private FilterUtility() {
	}

	public static String MadWomanRanksToString(ArrayList<Integer> madWomanRanks){
		String answer= "Choose one of this values before playing the MadWoman: ";
		Collections.sort(madWomanRanks);
		for(Integer num: madWomanRanks){
			answer = answer + String.valueOf(num) + " ";
		}
		return answer;	
	}

	public static ArrayList<MainCard> getCardList(Move prizeRibattuta) {
		ArrayList<MainCard> cards = new ArrayList<MainCard>();
		cards.add(prizeRibattuta.getCardPlayed());
		cards.addAll(prizeRibattuta.getCardTaken());
		return cards;
	}

	public static String printCards(ArrayList<MainCard> cards) {
		String string = new String();
		if(cards.size() > 1)
			for(MainCard card: cards){
				string = string + FilterUtility.printCard(card) + " -";
			}
		else if (cards.size() == 1)
			string = printCard(cards.get(0));
		else
			string = "You have no cards in hand";
		return string;
	}

	public static String printCard(MainCard card) {
		int rank = card.getRank();
		String rankStr = new String();
		String suitStr = new String();
		if(rank <=10 && rank >=2)
			rankStr = Integer.toString(rank);
		else if(rank == 1)
			rankStr = " A ";
		else if(rank == 11)
			rankStr = " J ";
		else if(rank == 12)
			rankStr = " Q ";
		else if(rank == 13)
			rankStr = " K ";
		else if(card.isMadwoman() == true)
			rankStr = " MdW ";
		else if(card.isJolly() == true)
			rankStr = " Jolly ";
		
		if(card.getSuit().equals(Suit.CUORI))
			suitStr = "Cuori";
		else if (card.getSuit().equals(Suit.PICCHE))
			suitStr = "Picche";
		else if (card.getSuit().equals(Suit.QUADRI))
			suitStr = "Quadri";
		else if (card.getSuit().equals(Suit.FIORI))
			suitStr = "Fiori";
		else if (card.isMadwoman() == true || card.isJolly() == true)
			suitStr = "";
		
		return rankStr + suitStr;
	}
}
