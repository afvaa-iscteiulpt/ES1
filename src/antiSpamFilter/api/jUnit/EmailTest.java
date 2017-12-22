package antiSpamFilter.api.jUnit;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import antiSpamFilter.api.Email;
import antiSpamFilter.api.FileRule;
import antiSpamFilter.api.Rule;
import antiSpamFilter.api.TypeEmail;
import antiSpamFilter.api.TypeFile;

import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmailTest {
	/**
	 * JUnit Tests For the antiSpamFilter.api.Email
	 */
	private static Email instanceHam = null;
	private static Email instanceSpam = null;
	
	@BeforeClass
	public static void setUpEmails() {
		instanceHam = new Email(TypeEmail.HAM);
		instanceSpam = new Email(TypeEmail.SPAM);
	}
	
	@Test
	public void test1SetId() {
		instanceHam.setId("44");
		
		Assert.assertTrue(instanceHam.getId() == "44");
	}

	@Test
	public void test2SetAppliedRules() {
		
		String[] a = {"a","b","c"};
		instanceHam.setAppliedRules(a);
		
		Assert.assertTrue(instanceHam.getAppliedRules() == a);
	}

	@Test
	public void test3SetFullPath() {
		
		instanceHam.setFullPath("teste");
		
		Assert.assertTrue(instanceHam.getFullPath() == "teste");
		
	}

	@Test
	public void test4GetFullPath() {
		Assert.assertTrue(instanceHam.getFullPath() instanceof String);
	}

	@Test
	public void test5GetId() {
		Assert.assertTrue(!instanceHam.getId().isEmpty());
	}

	@Test
	public void test6GetAppliedRules() {
		Assert.assertTrue(instanceHam.getAppliedRules() instanceof String[]);
	}

	@Test
	public void test7GetType() {
		Assert.assertTrue(instanceHam.getType() == TypeEmail.HAM && instanceSpam.getType() == TypeEmail.SPAM);
	}

	@Test
	public void test8IsFPFN() {
		
		instanceHam.isFPFN(true);
		
		Assert.assertTrue(instanceHam.getIsFPFN() == true);
	}
	
	@Test
	public void test9GetIsFPFN() {
		Assert.assertTrue(instanceHam.getIsFPFN() instanceof Boolean);
	}

	@Test
	public void test10SetCurrentSum() {
		
		instanceHam.setCurrentSum(10);
		
		Assert.assertTrue(instanceHam.getCurrentSum() == 10);

	}
	
	@Test
	public void test11GetCurrentSum() {
		Assert.assertTrue(instanceHam.getCurrentSum() == 10 && instanceHam.getCurrentSum() != 0);
	}

	@Test
	public void test12GetAppliedRulesString() {
		Assert.assertTrue(instanceHam.getAppliedRulesString() instanceof String);
	}

}
