package lordcost.Grande_Scopa.engine;

import java.util.ArrayList;

public class EngineUtility {
	
	private EngineUtility() {
		
	}
	
	private static final int INVALIDINPUT=0;
	private static final int INVALIDRANK=0;
	
	public int parseMadWomanValue(String madWomanValue){
		
		if(Character.isDigit(madWomanValue.charAt(0)) && madWomanValue.length()==1 && !"0".equals(madWomanValue) && !"1".equals(madWomanValue) )
			return Integer.parseInt(madWomanValue);
		else if	("10".equals(madWomanValue) || "11".equals(madWomanValue) || "12".equals(madWomanValue) || "13".equals(madWomanValue))
			return Integer.parseInt(madWomanValue);
		else if ("j".equals(madWomanValue) || "J".equals(madWomanValue))
			return 11;
		else if ("q".equals(madWomanValue) || "Q".equals(madWomanValue))
			return 12;
		else if ("k".equals(madWomanValue) || "K".equals(madWomanValue))
			return 13;
		else
			return INVALIDINPUT;
		
	}

	public int verifyPresenceInMadWomanRanks(int chosenRank, ArrayList<Integer> madWomanRanks) {
		
		if(madWomanRanks.contains(chosenRank))
			return chosenRank;
		return INVALIDRANK;
	}

}
