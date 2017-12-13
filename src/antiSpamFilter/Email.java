package antiSpamFilter;

public class Email {

	private String fullPath;
	private String id;
	private String[] appliedRules;
	private TypeEmail type = null;
	private Boolean isFPFN = null;
	private double currentSum;
	
	public Email(TypeEmail type) {
		this.type = type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAppliedRules(String[] appliedRules) {
		this.appliedRules = appliedRules;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
		
	}

	public String getFullPath() {
		return fullPath;
	}

	public String getId() {
		return id;
	}

	public String[] getAppliedRules() {
		return appliedRules;
	}

	public TypeEmail getType() {
		return type;
	}

	public void isFPFN(boolean b) {
		this.isFPFN = b;
	}
	
	public Boolean getIsFPFN() {
		return this.isFPFN;
	}

	public double getCurrentSum() {
		return this.currentSum;
	}
	
	public void setCurrentSum(double currentSum) {
		this.currentSum = currentSum;
	}

	public String getAppliedRulesString() {
		
		String allRules = "";
		for (String rule : this.appliedRules) {
			allRules = allRules + "," + rule;
		}
		
		return allRules;		
	}
	
}
