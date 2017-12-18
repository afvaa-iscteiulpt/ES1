package antiSpamFilter.jUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import antiSpamFilter.StatusFile;

public class StatusFileTest {

	@Test
	public void test() {
		assertNotNull(StatusFile.valueOf("APPROVED"));
		assertNotNull(StatusFile.valueOf("NOTAPPROVED"));
	}

}
