package antiSpamFilter.api;

import java.util.concurrent.ThreadLocalRandom;

public class Rule {

	private String ruleName;
	private String ruleWeight;
	
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public void setRuleWeight(String ruleWeight) {
		this.ruleWeight = ruleWeight;
	}
	
	public void generateRandomWeight() {
		double randomNum = ThreadLocalRandom.current().nextDouble(-5, 5 + 1);
		this.ruleWeight = String.valueOf(randomNum);
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public String getRuleWeight() {
		return ruleWeight;
	}

}
