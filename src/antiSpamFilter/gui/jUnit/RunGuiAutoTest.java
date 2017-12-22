package antiSpamFilter.gui.jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import antiSpamFilter.FileEmail;
import antiSpamFilter.FileRule;
import antiSpamFilter.TypeEmail;
import antiSpamFilter.gui.RunGuiAuto;

public class RunGuiAutoTest {

	@Test
	public void test() {
		RunGuiAuto auto = new RunGuiAuto(new FileRule(), new FileEmail(TypeEmail.HAM), new FileEmail(TypeEmail.SPAM));
		//fail("Not yet implemented");
	}

}
