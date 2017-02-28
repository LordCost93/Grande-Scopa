package lordcost.Grande_Scopa;

import java.util.ArrayList;

import junit.framework.Assert;
import lordcost.Grande_Scopa.board.MainBoard;
import lordcost.Grande_Scopa.cards.MainCard;
import lordcost.Grande_Scopa.cards.Suit;

import org.junit.Test;

public class TestMainBoard {
	
	
	@Test
	public void TestMadwomanRanksCalculation(){
		MainBoard mainBoard = new MainBoard();
		MainCard mainCard1 = new MainCard(Suit.PICCHE, 4);
		MainCard mainCard2 = new MainCard(Suit.CUORI, 5);
		
		mainBoard.getBoard().add(mainCard1);
		mainBoard.getBoard().add(mainCard2);
		
		ArrayList<Integer> madwomanRanks = mainBoard.calculateMadwomanRanksClassicGame();
		
		Assert.assertTrue(madwomanRanks.contains(4));
		Assert.assertTrue(madwomanRanks.contains(5));
		Assert.assertTrue(madwomanRanks.contains(9));
	}
	
	@Test
	public void TestMadwomanRanksCalculation2(){
		MainBoard mainBoard = new MainBoard();
		MainCard mainCard1 = new MainCard(Suit.PICCHE, 4);
		MainCard mainCard2 = new MainCard(Suit.CUORI, 5);
		MainCard mainCard3 = new MainCard(Suit.FIORI, 1);
		
		mainBoard.getBoard().add(mainCard1);
		mainBoard.getBoard().add(mainCard2);
		mainBoard.getBoard().add(mainCard3);
		
		ArrayList<Integer> madwomanRanks = mainBoard.calculateMadwomanRanksClassicGame();
		
		Assert.assertFalse(madwomanRanks.contains(8));
		Assert.assertTrue(madwomanRanks.contains(10));
		Assert.assertFalse(madwomanRanks.contains(1));
	}
	
	@Test
	public void TestMadwomanRanksCalculation3(){
		MainBoard mainBoard = new MainBoard();
		MainCard mainCard1 = new MainCard(Suit.PICCHE, 4);
		MainCard mainCard2 = new MainCard(Suit.CUORI, 5);
		MainCard mainCard3 = new MainCard(Suit.QUADRI, 13);
		
		mainBoard.getBoard().add(mainCard1);
		mainBoard.getBoard().add(mainCard2);
		mainBoard.getBoard().add(mainCard3);
		
		ArrayList<Integer> madwomanRanks = mainBoard.calculateMadwomanRanksClassicGame();
		
		Assert.assertTrue(madwomanRanks.contains(13));
		Assert.assertFalse(madwomanRanks.contains(17));
	}
	
	@Test
	public void TestMadwomanRanksCalculation4(){
		MainBoard mainBoard = new MainBoard();
		MainCard mainCard1 = new MainCard(Suit.PICCHE, 1);
		MainCard mainCard2 = new MainCard(Suit.CUORI, 1);
		MainCard mainCard3 = new MainCard(Suit.QUADRI, 1);
		MainCard mainCard4 = new MainCard(Suit.QUADRI, 1);
		MainCard mainCard5 = new MainCard(Suit.FIORI, 1);
		MainCard mainCard6 = new MainCard(Suit.FIORI, 1);
		MainCard mainCard7 = new MainCard(Suit.QUADRI, 2);
		MainCard mainCard8 = new MainCard(Suit.QUADRI, 2);
		MainCard mainCard9 = new MainCard(Suit.CUORI, 1);
		MainCard mainCard10 = new MainCard(Suit.PICCHE, 1);
		
		mainBoard.getBoard().add(mainCard1);
		mainBoard.getBoard().add(mainCard2);
		mainBoard.getBoard().add(mainCard3);
		mainBoard.getBoard().add(mainCard4);
		mainBoard.getBoard().add(mainCard5);
		mainBoard.getBoard().add(mainCard6);
		mainBoard.getBoard().add(mainCard7);
		mainBoard.getBoard().add(mainCard8);
		mainBoard.getBoard().add(mainCard9);
		mainBoard.getBoard().add(mainCard10);
		ArrayList<Integer> madwomanRanks = mainBoard.calculateMadwomanRanksClassicGame();
		
		Assert.assertTrue(madwomanRanks.contains(12));
		Assert.assertFalse(madwomanRanks.contains(13));
		Assert.assertTrue(madwomanRanks.contains(4));
		Assert.assertFalse(madwomanRanks.contains(1));
		
	}
	
	@Test
	public void TestMadwomanRanksCalculation5(){
		MainBoard mainBoard = new MainBoard();
				
		ArrayList<Integer> madwomanRanks = mainBoard.calculateMadwomanRanksClassicGame();
		
		Assert.assertFalse(madwomanRanks.contains(1));
		Assert.assertTrue(madwomanRanks.contains(5));
		Assert.assertTrue(madwomanRanks.contains(13));
	}
	
	/*@Test
	public void TestNumberOfSumsClassicGame(){
		MainBoard mainBoard = new MainBoard();
		MainCard mainCard1 = new MainCard(Suit.PICCHE, 1);
		MainCard mainCard2 = new MainCard(Suit.CUORI, 1);
		MainCard mainCard3 = new MainCard(Suit.QUADRI, 1);
		MainCard mainCard4 = new MainCard(Suit.QUADRI, 1);
		MainCard mainCard5 = new MainCard(Suit.FIORI, 1);
		MainCard mainCard6 = new MainCard(Suit.FIORI, 1);
		MainCard mainCard7 = new MainCard(Suit.QUADRI, 2);
		MainCard mainCard8 = new MainCard(Suit.QUADRI, 2);
		MainCard mainCard9 = new MainCard(Suit.CUORI, 1);
		MainCard mainCard10 = new MainCard(Suit.PICCHE, 1);
		
		mainBoard.getBoard().add(mainCard1);
		mainBoard.getBoard().add(mainCard2);
		mainBoard.getBoard().add(mainCard3);
		mainBoard.getBoard().add(mainCard4);
		mainBoard.getBoard().add(mainCard5);
		mainBoard.getBoard().add(mainCard6);
		mainBoard.getBoard().add(mainCard7);
		mainBoard.getBoard().add(mainCard8);
		mainBoard.getBoard().add(mainCard9);
		mainBoard.getBoard().add(mainCard10);
		int sums = mainBoard.numberOfSumsClassicGame();
		
		Assert.assertTrue(sums==10);
	}// il test dava il metodo corretto, lo cancello per mettere numberOfSumsClassicGame come metodo privato.*/
		
}
