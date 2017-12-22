package antiSpamFilter.gui.jUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ConfGuiTest.class, MainGuiTest.class, RunGuiAutoTest.class, RunGuiManualTest.class })
public class AllTests {
	
}
