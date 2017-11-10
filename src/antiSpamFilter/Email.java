package antiSpamFilter;

import java.util.HashMap;

public class Email {

	public String fullPath;
	public String id;
	public String[] appliedRules;
	public TypeEmail type = null;
	
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
	
}
