package antiSpamFilter.api;

/**
 * Class Email - cada email do ficheiro HAM e SPAM
 * 
 */

public class Email {

	private String fullPath;
	private String id;
	private String[] appliedRules = {};
	private TypeEmail type = null;
	private Boolean isFPFN = null;
	private double currentSum;
	
	/**
	 * Inicializador
	 * 
	 * @param TypeEmail
	 *            - HAM ou SPAM
	 */
	public Email(TypeEmail type) {
		this.type = type;
	}

	/**
	 * Insere o ID para o email
	 * 
	 * @param String
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Carrega para cada email as regras associadas a esse email
	 * 
	 * @param String[]
	 */
	public void setAppliedRules(String[] appliedRules) {
		this.appliedRules = appliedRules;
	}

	/**
	 * Define para cada email o full path apresentado no ficheiro 
	 * 
	 * @param String
	 */
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
		
	}

	/**
	 * Retorna o full path
	 * 
	 * @return String
	 */
	public String getFullPath() {
		return fullPath;
	}

	/**
	 * Retorna o id
	 * 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Retorna as regras aplicadas para este email
	 * 
	 * @return String[]
	 */
	public String[] getAppliedRules() {
		return appliedRules;
	}

	/**
	 * Retorna o tipo de email - HAM ou SPAM
	 * 
	 * @return TypeEmail
	 */
	public TypeEmail getType() {
		return type;
	}

	/**
	 * Define se este email foi considerado FP ou FN, consoante a soma das rules aplicadas.
	 * 
	 * @param boolean 
	 * @return void
	 */
	public void isFPFN(boolean b) {
		this.isFPFN = b;
	}
	
	/**
	 * Verifica se este email é considerado FP ou FN
	 * 
	 * @return boolean
	 */
	public Boolean getIsFPFN() {
		return this.isFPFN;
	}

	/**
	 * Retorna a soma corrente aplicada pelos pesos das rules aplicadas.
	 * 
	 * @return double
	 */
	public double getCurrentSum() {
		return this.currentSum;
	}
	
	/**
	 * Define a soma corrent aplicadas pelos pesos das rules aplicadas.
	 * 
	 * @param double
	 * @return void
	 */
	public void setCurrentSum(double currentSum) {
		this.currentSum = currentSum;
	}

	/**
	 * Retorna em string as rules aplicadas 
	 * Para debugg 
	 * 
	 * @return String
	 */
	public String getAppliedRulesString() {
		
		String allRules = "";
		for (String rule : this.appliedRules) {
			allRules = allRules + "," + rule;
		}
		
		return allRules;		
	}
	
}
