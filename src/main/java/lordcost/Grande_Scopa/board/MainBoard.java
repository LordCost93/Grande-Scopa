package lordcost.Grande_Scopa.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lordcost.Grande_Scopa.cards.MainCard;
import lordcost.Grande_Scopa.moveController.Move;

public class MainBoard {

	private List<MainCard> board;
	private Move prizeRibattuta;
	private static final int ACE=1;
	private ArrayList<Integer> madWomanRanks;
	
	public MainBoard(){
		this.board = new ArrayList<MainCard>();
		this.prizeRibattuta = new Move(null, null, 0, 0);
		this.madWomanRanks= new ArrayList<Integer>();
	}
	
	public List<MainCard> getBoard() {
		return board;
	}

	public Move getPrizeRibattuta() {
		return prizeRibattuta;
	}
	
	public ArrayList<Integer> getMadWomanRanks() {
		return madWomanRanks;
	}
	
	public void addToPrizeRibattuta (Move move){
		MainCard oldCardPlayed = this.prizeRibattuta.getCardPlayed();
				
		this.prizeRibattuta.setCardPlayed(move.getCardPlayed());
		this.prizeRibattuta.addCardTaken(oldCardPlayed);	
		this.prizeRibattuta.addThirdsAndScope(move.getThirds(), move.getScope());
	}

	public ArrayList<Integer> calculateMadwomanRanksClassicGame(){// fa da setter di madwomanranks
		int actualRank=0;
		boolean addRank= true;
		int i,j,k,l,m,o,p,q,r,s,t;
		
		madWomanRanks.clear();// cancello i vecchi rank quando li sto ricalcolando
		
		if(board.isEmpty()){//se il board è vuoto riempio l'array con tutti i valori delle carte possibili, dal 2 al 13
			for(i=2;i<=13;i++)
				madWomanRanks.add(i);
			return madWomanRanks;
		}
		
		
		
		
		for(i=0; i<board.size(); i++){// AAAAAAAAAA PROCEDURA PER LE PRESE SINGOLE  AAAAAAAAAAA
			actualRank=board.get(i).getRank();
			for(j=0; j<madWomanRanks.size();j++)//scorro tutto l'array e se il valore c'era già non lo aggiungo ancora
				if(madWomanRanks.get(j)==actualRank)
					addRank= false;
			if(addRank==true && actualRank!=ACE)//controllo sul fatto che l'A non può essere mai scelto
				madWomanRanks.add(actualRank);
			addRank= true;
		}
		if(numberOfSumsClassicGame()==1)// non valuto le somme successive se ho una carta sola sul board
			return madWomanRanks;
		
		
		
		
		
		for(i=0;i<board.size();i++)// AAAAAAAAA PROCEDURA PER LE SOMME DI 2 VALORI AAAAAAAAAA
			for(j=i+1;j<board.size();j++){
			actualRank=board.get(i).getRank() + board.get(j).getRank();
				if(actualRank>=2 && actualRank<=13){
					for(k=0; k<madWomanRanks.size();k++)//scorro tutto l'array e se il valore c'era già non lo aggiungo ancora
						if(madWomanRanks.get(k)==actualRank)
							addRank= false;
					if(addRank==true)
						madWomanRanks.add(actualRank);
					addRank= true;
				}	
			}
		if(numberOfSumsClassicGame()==2)// non valuto le somme successive se le due carte più basse del board sommate fanno oltre 13 come risultato
			return madWomanRanks;
		
		
		
		
		for(i=0;i<board.size();i++)// AAAAAAAAA PROCEDURA PER LE SOMME DI 3 VALORI AAAAAAAAAA
			for(j=i+1;j<board.size();j++)
				for(l=j+1;l<board.size();l++){
					actualRank=board.get(i).getRank() + board.get(j).getRank() + board.get(l).getRank();
					if(actualRank>=2 && actualRank<=13){
						for(k=0; k<madWomanRanks.size();k++)//scorro tutto l'array e se il valore c'era già non lo aggiungo ancora
							if(madWomanRanks.get(k)==actualRank)
								addRank= false;
						if(addRank==true)
							madWomanRanks.add(actualRank);
						addRank= true;
					}	
				}
		if(numberOfSumsClassicGame()==3)// non valuto le somme successive se le tre carte più basse del board sommate fanno oltre 13 come risultato
			return madWomanRanks;
		
		
		
		
		for(i=0;i<board.size();i++)// AAAAAAAAA PROCEDURA PER LE SOMME DI 4 VALORI AAAAAAAAAA
			for(j=i+1;j<board.size();j++)
				for(l=j+1;l<board.size();l++)
					for(m=l+1;m<board.size();m++){
						actualRank=board.get(i).getRank() + board.get(j).getRank() + board.get(l).getRank() + board.get(m).getRank();
						if(actualRank>=2 && actualRank<=13){
							for(k=0; k<madWomanRanks.size();k++)//scorro tutto l'array e se il valore c'era già non lo aggiungo ancora
								if(madWomanRanks.get(k)==actualRank)
									addRank= false;
							if(addRank==true)
								madWomanRanks.add(actualRank);
							addRank= true;
						}	
					}
		if(numberOfSumsClassicGame()==4)// non valuto le somme successive se le quattro carte più basse del board sommate fanno oltre 13 come risultato
			return madWomanRanks;
		
		
		
		
		for(i=0;i<board.size();i++)// AAAAAAAAA PROCEDURA PER LE SOMME DI 5 VALORI AAAAAAAAAA
			for(j=i+1;j<board.size();j++)
				for(l=j+1;l<board.size();l++)
					for(m=l+1;m<board.size();m++)
						for(o=m+1;o<board.size();o++){
							actualRank=board.get(i).getRank() + board.get(j).getRank() + board.get(l).getRank() + board.get(m).getRank()
									+board.get(o).getRank();
							if(actualRank>=2 && actualRank<=13){
								for(k=0; k<madWomanRanks.size();k++)//scorro tutto l'array e se il valore c'era già non lo aggiungo ancora
									if(madWomanRanks.get(k)==actualRank)
										addRank= false;
								if(addRank==true)
									madWomanRanks.add(actualRank);
								addRank= true;
							}	
						}
		if(numberOfSumsClassicGame()==5)// non valuto le somme successive se le cinque carte più basse del board sommate fanno oltre 13 come risultato
			return madWomanRanks;
		
		
		
		
		for(i=0;i<board.size();i++)// AAAAAAAAA PROCEDURA PER LE SOMME DI 6 VALORI AAAAAAAAAA
			for(j=i+1;j<board.size();j++)
				for(l=j+1;l<board.size();l++)
					for(m=l+1;m<board.size();m++)
						for(o=m+1;o<board.size();o++)
							for(p=o+1;p<board.size();p++){
								actualRank=board.get(i).getRank() + board.get(j).getRank() + board.get(l).getRank() + board.get(m).getRank()
										+board.get(o).getRank() + board.get(p).getRank();
								if(actualRank>=2 && actualRank<=13){
									for(k=0; k<madWomanRanks.size();k++)//scorro tutto l'array e se il valore c'era già non lo aggiungo ancora
										if(madWomanRanks.get(k)==actualRank)
											addRank= false;
									if(addRank==true)
										madWomanRanks.add(actualRank);
									addRank= true;
								}	
							}
		if(numberOfSumsClassicGame()==6)// non valuto le somme successive se le sei carte più basse del board sommate fanno oltre 13 come risultato
			return madWomanRanks;
		
		
		
		
		for(i=0;i<board.size();i++)// AAAAAAAAA PROCEDURA PER LE SOMME DI 7 VALORI AAAAAAAAAA
			for(j=i+1;j<board.size();j++)
				for(l=j+1;l<board.size();l++)
					for(m=l+1;m<board.size();m++)
						for(o=m+1;o<board.size();o++)
							for(p=o+1;p<board.size();p++)
								for(q=p+1;q<board.size();q++){
									actualRank=board.get(i).getRank() + board.get(j).getRank() + board.get(l).getRank() + board.get(m).getRank()
											+board.get(o).getRank() + board.get(p).getRank() + board.get(q).getRank();
									if(actualRank>=2 && actualRank<=13){
										for(k=0; k<madWomanRanks.size();k++)//scorro tutto l'array e se il valore c'era già non lo aggiungo ancora
											if(madWomanRanks.get(k)==actualRank)
												addRank= false;
										if(addRank==true)
											madWomanRanks.add(actualRank);
										addRank= true;
									}	
								}
		if(numberOfSumsClassicGame()==7)// non valuto le somme successive se le sette carte più basse del board sommate fanno oltre 13 come risultato
			return madWomanRanks;
		
		
		
		
		for(i=0;i<board.size();i++)// AAAAAAAAA PROCEDURA PER LE SOMME DI 8 VALORI AAAAAAAAAA
			for(j=i+1;j<board.size();j++)
				for(l=j+1;l<board.size();l++)
					for(m=l+1;m<board.size();m++)
						for(o=m+1;o<board.size();o++)
							for(p=o+1;p<board.size();p++)
								for(q=p+1;q<board.size();q++)
									for(r=q+1;r<board.size();r++){
										actualRank=board.get(i).getRank() + board.get(j).getRank() + board.get(l).getRank() + board.get(m).getRank()
												+board.get(o).getRank() + board.get(p).getRank() + board.get(q).getRank() + board.get(r).getRank();
										if(actualRank>=2 && actualRank<=13){
											for(k=0; k<madWomanRanks.size();k++)//scorro tutto l'array e se il valore c'era già non lo aggiungo ancora
												if(madWomanRanks.get(k)==actualRank)
													addRank= false;
											if(addRank==true)
												madWomanRanks.add(actualRank);
											addRank= true;
										}	
									}
		if(numberOfSumsClassicGame()==8)// non valuto le somme successive se le otto carte più basse del board sommate fanno oltre 13 come risultato
			return madWomanRanks;
		
		
		
		
		for(i=0;i<board.size();i++)// AAAAAAAAA PROCEDURA PER LE SOMME DI 9 VALORI AAAAAAAAAA
			for(j=i+1;j<board.size();j++)
				for(l=j+1;l<board.size();l++)
					for(m=l+1;m<board.size();m++)
						for(o=m+1;o<board.size();o++)
							for(p=o+1;p<board.size();p++)
								for(q=p+1;q<board.size();q++)
									for(r=q+1;r<board.size();r++)
										for(s=r+1;s<board.size();s++){
											actualRank=board.get(i).getRank() + board.get(j).getRank() + board.get(l).getRank() + board.get(m).getRank()
													+board.get(o).getRank() + board.get(p).getRank() + board.get(q).getRank() + board.get(r).getRank()
													+board.get(s).getRank();
											if(actualRank>=2 && actualRank<=13){
												for(k=0; k<madWomanRanks.size();k++)//scorro tutto l'array e se il valore c'era già non lo aggiungo ancora
													if(madWomanRanks.get(k)==actualRank)
														addRank= false;
												if(addRank==true)
													madWomanRanks.add(actualRank);
												addRank= true;
											}	
										}
		if(numberOfSumsClassicGame()==9)// non valuto le somme successive se le nove carte più basse del board sommate fanno oltre 13 come risultato
			return madWomanRanks;
		
		
		
		
		for(i=0;i<board.size();i++)// AAAAAAAAA PROCEDURA PER LE SOMME DI 10 VALORI AAAAAAAAAA
			for(j=i+1;j<board.size();j++)
				for(l=j+1;l<board.size();l++)
					for(m=l+1;m<board.size();m++)
						for(o=m+1;o<board.size();o++)
							for(p=o+1;p<board.size();p++)
								for(q=p+1;q<board.size();q++)
									for(r=q+1;r<board.size();r++)
										for(s=r+1;s<board.size();s++)
											for(t=s+1;t<board.size();t++){
												actualRank=board.get(i).getRank() + board.get(j).getRank() + board.get(l).getRank() + board.get(m).getRank()
														+board.get(o).getRank() + board.get(p).getRank() + board.get(q).getRank() + board.get(r).getRank()
														+board.get(s).getRank() + board.get(t).getRank();
												if(actualRank>=2 && actualRank<=13){
													for(k=0; k<madWomanRanks.size();k++)//scorro tutto l'array e se il valore c'era già non lo aggiungo ancora
														if(madWomanRanks.get(k)==actualRank)
															addRank= false;
													if(addRank==true)
														madWomanRanks.add(actualRank);
													addRank= true;
												}	
											}
		return madWomanRanks;
	}
	
	/** Method used in calculateMadwomanRanks() for not searching sums of many cards. It sort the ranks of the cards, then sum the rank of cards and discover the maximum possible sum of many cards.
	 * If the sums of the 4 lower ranked cards on the board is 14, at maximum there will be a sum of 3 cards (quadriglia semplice). It doesn't count sforate per briscola, there will be another method for it.
	 */
	
	private int numberOfSumsClassicGame(){
		int i;
		ArrayList<Integer> ranks= new ArrayList<Integer>();
		int numberOfSums=0,sum=0;
		
		for(i=0;i<board.size();i++)
			ranks.add(board.get(i).getRank());//copio i valori delle carte del board in un nuovo array ranks.
		if(ranks.isEmpty()==false){
			Collections.sort(ranks);//ordino in ordine crescente i valori delle carte
			for(i=0;i<ranks.size() && sum<=13;i++){
				numberOfSums++;
				sum=sum+ranks.get(i);
			}	
		}
		return numberOfSums;
	}
		
}
