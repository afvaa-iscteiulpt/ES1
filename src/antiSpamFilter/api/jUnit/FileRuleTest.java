package antiSpamFilter.api.jUnit;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import antiSpamFilter.api.FileRule;
import antiSpamFilter.api.Rule;
import antiSpamFilter.api.TypeFile;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileRuleTest {

	/**
	 * JUnit Tests For the antiSpamFilter.api.FileRule
	 */
	
	private static FileRule instance = null;
	/**
	 * Setting Up Before Testing
	 */
	@BeforeClass
	public static void setUpRule() {
		instance = new FileRule();
	}
	
	/**
	 * Testing the getHmapRules()
	 */
	@Test
	public void test1GetHmapRules() {
		Assert.assertTrue(!instance.getHmapRules().isEmpty());
	}

	/**
	 * Testing the resetHashMap()
	 */
	@Test
	public void test2ResetHashmap() {
		instance.resetHashmap();
		
		Assert.assertTrue(instance.getHmapRules().isEmpty());
	}

	/**
	 * Testing the createNewRule()
	 */
	@Test
	public void test3CreateNewRule() {
		
		instance.createNewRule("NEWRULE", "3.44");

		Assert.assertTrue(instance.getHmapRules().containsKey("NEWRULE"));
		
		Rule rule = instance.getHmapRules().get("NEWRULE");
		
		Assert.assertTrue(rule != null && rule.getRuleWeight() == "3.44");
	}

	/**
	 * Testing the remove(String)
	 */
	@Test
	public void test4DeleteRule() {
		instance.getHmapRules().remove("NEWRULE");
		
		Rule rule = instance.getHmapRules().get("NEWRULE");
		
		Assert.assertTrue(!instance.getHmapRules().containsKey("NEWRULE") && rule == null);
	}
	
	/**
	 * Testing the generateRandomWeightsForEachRule()
	 */
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

	/**
	 * Testing the replaceFileContent()
	 */
	@Test
	public void test6ReplaceFileContent() {
	
		instance.replaceFileContent();
		
		FileRule newFileRule = new FileRule();
	
		Assert.assertTrue(instance.getHmapRulesString() != newFileRule.getHmapRulesString());	
	}

	/**
	 * Testing the applytoRules(String[],String)
	 */
	@Test
	public void test7ApplyToRules() {
		String[] rulesToModified = { "SUBJ_AS_SEEN", "DNS_FROM_RFC_BOGUSMX", "DRUGS_DIET" };
		instance.applyToRules(rulesToModified, "-10");
				
		Assert.assertTrue(instance.getHmapRules().get("SUBJ_AS_SEEN").getRuleWeight() == "-10" && instance.getHmapRules().get("DNS_FROM_RFC_BOGUSMX").getRuleWeight() == "-10" && instance.getHmapRules().get("DRUGS_DIET").getRuleWeight() == "-10");	
	}

	/**
	 * Testing the getHmapRulesString()
	 */
	@Test
	public void test8GetHmapRulesString() {
		Assert.assertTrue(!instance.getHmapRulesString().isEmpty() && instance.getHmapRulesString() instanceof String);	
	}

	/**
	 * Testing the getNumberOfLines()
	 */
	@Test
	public void test9getNumberOfLines() {
		Assert.assertTrue(instance.getNumberOfLines() != 0);	
	}
	
	/**
	 * Testing the getTypeFile()
	 */
	@Test
	public void test10getTypeFile() {
		Assert.assertTrue(instance.getTypeFile() instanceof TypeFile);	
	}
}
