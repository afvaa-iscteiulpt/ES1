package antiSpamFilter.gui.jUnit;

import org.junit.Test;

import antiSpamFilter.api.FileEmail;
import antiSpamFilter.api.FileRule;
import antiSpamFilter.api.TypeEmail;
import antiSpamFilter.gui.ConfGui;

public class ConfGuiTest {

	@Test
	public void test() {
		ConfGui gui = new ConfGui(new FileRule(), new FileEmail(TypeEmail.HAM), new FileEmail(TypeEmail.SPAM), 0);
		ConfGui gui1 = new ConfGui(new FileRule(), new FileEmail(TypeEmail.HAM), new FileEmail(TypeEmail.SPAM), 1);
		ConfGui gui2 = new ConfGui(new FileRule(), new FileEmail(TypeEmail.HAM), new FileEmail(TypeEmail.SPAM), 2);

		//fail("Not yet implemented");
	}

}
