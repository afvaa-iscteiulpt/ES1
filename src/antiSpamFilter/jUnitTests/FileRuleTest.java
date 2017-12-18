package antiSpamFilter.jUnitTests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import antiSpamFilter.FileRule;
import antiSpamFilter.Rule;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileRuleTest {

	private static FileRule instance = null;
	
	@BeforeClass
	public static void setUpRule() {
		instance = new FileRule();
	}
	
	@Test
	public void test1GetHmapRules() {
		Assert.assertTrue(!instance.getHmapRules().isEmpty());
	}

	@Test
	public void test2ResetHashmap() {
		instance.resetHashmap();
		
		Assert.assertTrue(instance.getHmapRules().isEmpty());
	}

	@Test
	public void test3CreateNewRule() {
		
		instance.createNewRule("NEWRULE", "3.44");

		Assert.assertTrue(instance.getHmapRules().containsKey("NEWRULE"));
		
		Rule rule = instance.getHmapRules().get("NEWRULE");
		
		Assert.assertTrue(rule != null && rule.getRuleWeight() == "3.44");
	}

	@Test
	public void test4DeleteRule() {
		instance.getHmapRules().remove("NEWRULE");
		
		Rule rule = instance.getHmapRules().get("NEWRULE");
		
		Assert.assertTrue(!instance.getHmapRules().containsKey("NEWRULE") && rule == null);
	}

	@Test
	public void test5GenerateRandomWeightsForEachRule() {
		instance.resetHashmap();
		
		//clone original filerule
		FileRule fileRuleClone = new FileRule();
		instance.setHashMap(fileRuleClone.getHmapRules());
		fileRuleClone = instance;
		
		//generate random weight to the original FileRule
		instance.generateRandomWeightsForEachRule();
		
		//compare if is different
		Assert.assertTrue(instance.getHmapRulesString() != fileRuleClone.getHmapRulesString());		
	}

	@Test
	public void test6ReplaceFileContent() {
		instance.replaceFileContent();
		
		FileRule newFileRule = new FileRule();
		
		Assert.assertTrue(instance.getHmapRulesString() == newFileRule.getHmapRulesString());	
	}

	@Test
	public void test7ApplyToRules() {
		String[] rulesToModified = { "SUBJ_AS_SEEN", "DNS_FROM_RFC_BOGUSMX", "DRUGS_DIET" };
		instance.applyToRules(rulesToModified, "-10");
				
		Assert.assertTrue(instance.getHmapRules().get("SUBJ_AS_SEEN").getRuleWeight() == "-10" && instance.getHmapRules().get("DNS_FROM_RFC_BOGUSMX").getRuleWeight() == "-10" && instance.getHmapRules().get("DRUGS_DIET").getRuleWeight() == "-10");	
	}

	@Test
	public void test8GetHmapRulesString() {
		Assert.assertTrue(!instance.getHmapRulesString().isEmpty() && instance.getHmapRulesString() instanceof String);	
	}

}
