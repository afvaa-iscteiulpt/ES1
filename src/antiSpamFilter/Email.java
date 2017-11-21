package antiSpamFilter;

public class Email {

	public String fullPath;
	public String id;
	public String[] appliedRules;
	public TypeEmail type = null;
	public Boolean isFPFN = null;
	public double currentSum;
	
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
