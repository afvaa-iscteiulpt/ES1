package antiSpamFilter.api.jUnit;

import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.junit.Test;

import antiSpamFilter.api.TypeFile;

import static org.junit.Assert.*;

public class TypeFileTest {
	/**
	 * JUnit Tests For the antiSpamFilter.api.TypeFile
	 */
	@Test
	public void test() {
		assertNotNull(TypeFile.valueOf("EMAIL"));
		assertNotNull(TypeFile.valueOf("RULE"));
	}

}
