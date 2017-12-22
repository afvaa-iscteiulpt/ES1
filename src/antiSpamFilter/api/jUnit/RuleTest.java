package antiSpamFilter.api.jUnit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import antiSpamFilter.api.Rule;

import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RuleTest {
	/**
	 * JUnit Tests For the antiSpamFilter.api.Rule
	 */
	
	
	private static Rule instance = null;
	/**
	 * Setting up for the tests
	 */
	@BeforeClass
	public static void setUpRule() {
		instance = new Rule();
	}
	
	/**
	 * Testing the setRuleName()
	 */
	@Test
	public void test1SetRuleName() {
		instance.setRuleName("RULENAME");
		
		test4GetRuleName();
	}

	/**
	 * Testing the setRuleWeight()
	 */
	@Test
	public void test2SetRuleWeight() {
		instance.setRuleWeight("3.33"); 
		
		test5GetRuleWeight();
	}

	/**
	 * Testing the generateRandomWeight()
	 */
	@Test
	public void test3GenerateRandomWeight() {
		instance.generateRandomWeight();
	
		test5GetRuleWeight();
	}
	
	
	/**
	 * Testinf the getRuleName()
	 */
	@Test 
	public void test4GetRuleName() {
		Assert.assertEquals("RULENAME", instance.getRuleName());
	}
	
	/**
	 * Testing the getRuleWeight()
	 */
	@Test
	public void test5GetRuleWeight() {
		Assert.assertTrue(Double.parseDouble(instance.getRuleWeight()) > -5 && Double.parseDouble(instance.getRuleWeight()) < 5);
		Assert.assertTrue(instance.getRuleWeight().matches("[-+]?\\d*\\.?\\d+"));
	}
}
