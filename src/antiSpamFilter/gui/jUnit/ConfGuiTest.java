package antiSpamFilter.gui.jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import antiSpamFilter.FileEmail;
import antiSpamFilter.FileRule;
import antiSpamFilter.TypeEmail;
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
