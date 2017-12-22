package antiSpamFilter.jUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import antiSpamFilter.api.TypeEmail;

public class TypeEmailTest {

	@Test
	public void test() {
		assertNotNull(TypeEmail.valueOf("HAM"));
		assertNotNull(TypeEmail.valueOf("SPAM"));
	}

}
