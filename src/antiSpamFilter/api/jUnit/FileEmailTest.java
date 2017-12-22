package antiSpamFilter.api.jUnit;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import antiSpamFilter.api.Email;
import antiSpamFilter.api.FileEmail;
import antiSpamFilter.api.FileRule;
import antiSpamFilter.api.Rule;
import antiSpamFilter.api.TypeEmail;
import antiSpamFilter.api.TypeFile;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileEmailTest {

	private static FileEmail instanceHam = null;
	private static FileEmail instanceSpam = null;
	
	@BeforeClass
	public static void setUpEmail() {
		instanceHam = new FileEmail(TypeEmail.HAM);
		instanceSpam = new FileEmail(TypeEmail.SPAM);
	}
	
	@Test
	public void test1GetTypeEmail() {
		Assert.assertTrue(instanceHam.getTypeEmail() == TypeEmail.HAM);
		Assert.assertTrue(instanceSpam.getTypeEmail() == TypeEmail.SPAM);
	}

	@Test
	public void test2GetLinkedListEmails() {
		Assert.assertTrue(instanceHam.getLinkedListEmails() instanceof LinkedList<?>);
	}
	
	@Test
	public void test3ResetList() {
		instanceHam.resetList();
		
		Assert.assertTrue(instanceHam.getLinkedListEmails().isEmpty());
	}

	@Test
	public void test5SetFpFn() {
		instanceHam.setFpFn(10);
		
		Assert.assertTrue(instanceHam.getFpFn() == 10);
	}

}
