package antiSpamFilter.jUnitTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import antiSpamFilter.Rule;

import org.junit.runners.MethodSorters;

import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RuleTest {

	private static Rule instance = null;
	
	@BeforeClass
	public static void setUpRule() {
		instance = new Rule();
	}
	
	@Test
	public void test1SetRuleName() {
		instance.setRuleName("RULENAME");
		
		test4GetRuleName();
	}

	@Test
	public void test2SetRuleWeight() {
		instance.setRuleWeight("3.33"); 
		
		test5GetRuleWeight();
	}

	@Test
	public void test3GenerateRandomWeight() {
		instance.generateRandomWeight();
	
		test5GetRuleWeight();
	}

	@Test 
	public void test4GetRuleName() {
		Assert.assertEquals("RULENAME", instance.getRuleName());
	}

	@Test
	public void test5GetRuleWeight() {
		Assert.assertTrue(Double.parseDouble(instance.getRuleWeight()) > -5 && Double.parseDouble(instance.getRuleWeight()) < 5);
		Assert.assertTrue(instance.getRuleWeight().matches("[-+]?\\d*\\.?\\d+"));
	}
}
