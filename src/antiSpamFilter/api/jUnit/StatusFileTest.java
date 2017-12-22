package antiSpamFilter.api.jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import antiSpamFilter.api.StatusFile;

public class StatusFileTest {
	/**
	 * JUnit Tests For the antiSpamFilter.api.StatusFile
	 */
	
	
	@Test
	public void test() {
		assertNotNull(StatusFile.valueOf("APPROVED"));
		assertNotNull(StatusFile.valueOf("NOTAPPROVED"));
	}

}
