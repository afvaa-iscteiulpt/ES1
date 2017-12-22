package antiSpamFilter.api.jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import antiSpamFilter.api.TypeEmail;

public class TypeEmailTest {
	/**
	 * JUnit Tests For the antiSpamFilter.api.TypeEmail
	 */
	
	
	@Test
	public void test() {
		assertNotNull(TypeEmail.valueOf("HAM"));
		assertNotNull(TypeEmail.valueOf("SPAM"));
	}

}
