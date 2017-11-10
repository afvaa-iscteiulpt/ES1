package antiSpamFilter;

import java.util.concurrent.ThreadLocalRandom;

public class Rule {

	public String ruleName;
	public int ruleWeight;
	
	public Rule() {
		
	}
	
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public void setRuleWeight(int ruleWeight) {
		this.ruleWeight = ruleWeight;
	}
	
	public void generateRandomWeight() {
		int randomNum = ThreadLocalRandom.current().nextInt(-5, 5 + 1);
		this.ruleWeight = randomNum;
	}

	public String getRuleName() {
		return ruleName;
	}

	public int getRuleWeight() {
		return ruleWeight;
	}

}
