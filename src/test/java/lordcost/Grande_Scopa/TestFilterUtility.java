package lordcost.Grande_Scopa;

import java.util.ArrayList;

import junit.framework.Assert;
import lordcost.Grande_Scopa.engine.FilterUtility;

import org.junit.Test;

public class TestFilterUtility {

	@Test
	public void TestMadwomanRanksToString(){
		ArrayList<Integer> madWomanRanks = new ArrayList<Integer>();
		madWomanRanks.add(8);
		madWomanRanks.add(2);
		madWomanRanks.add(13);
		String stringMad = FilterUtility.MadWomanRanksToString(madWomanRanks);
		System.out.println(stringMad);
		String string2 = new String("Choose one of this values before playing the MadWoman: 2 8 13 ");
		Assert.assertTrue(string2.equals(stringMad));
		
	}
}
