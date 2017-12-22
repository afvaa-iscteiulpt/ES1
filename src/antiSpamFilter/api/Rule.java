package antiSpamFilter.api;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class Rule - cada rule do ficheiro rules
 * 
 */
public class Rule {

	private String ruleName;
	private String ruleWeight;
	
	/**
	 * Insere o nome da rule
	 * 
	 * @param String
	 * @return void
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * Insere o peso na rule
	 * 
	 * @param String
	 * @return void
	 */
	public void setRuleWeight(String ruleWeight) {
		this.ruleWeight = ruleWeight;
	}
	
	/**
	 * Gera um peso aleatorio para a rule
	 * 
	 * @return void
	 */
	public void generateRandomWeight() {
		double randomNum = ThreadLocalRandom.current().nextDouble(-5, 5 + 1);
		this.ruleWeight = String.valueOf(randomNum);
	}

	/**
	 * Retorna o nome da rule
	 * 
	 * @return String
	 */
	public String getRuleName() {
		return this.ruleName;
	}

	/**
	 * Retorna o peso da rule
	 * 
	 * @return String
	 */
	public String getRuleWeight() {
		return ruleWeight;
	}

}
