package antiSpamFilter.api.jUnit;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import antiSpamFilter.api.BestResult;
import antiSpamFilter.api.FileRule;
import antiSpamFilter.api.Rule;
import antiSpamFilter.api.TypeFile;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BestResultTest {
	
	/**
	 * JUnit Tests For the antiSpamFilter.api.BestResult
	 */

	private static BestResult instance = null;
	private static String[] bestWeitghs = new String[3];
	
	/**
	 * Setting up for the tests
	 */
	@BeforeClass
	public static void setUpBestResult() {
		
		bestWeitghs[0] = "2.3";
		bestWeitghs[1] = "3.1";
		bestWeitghs[2] = "0.3";
		
		instance = new BestResult(2.0, 3.1, bestWeitghs);
	}

	
	/**
	 * Testing getBestWeights()
	 */
	@Test
	public void testGetBestWeights() {
		Assert.assertTrue(instance.getBestWeights() == bestWeitghs && instance.getBestWeights() instanceof String[]);
	}

	/**
	 * Testing getDoubleBestFp()
	 */
	@Test
	public void testGetDoubleBestFp() {
		Assert.assertTrue(instance.getDoubleBestFp() == 2.0);
	}

	/**
	 * Testing getDoubleBestFn()
	 */
	@Test
	public void testGetDoubleBestFn() {
		Assert.assertTrue(instance.getDoubleBestFn() == 3.1);
	}

}
