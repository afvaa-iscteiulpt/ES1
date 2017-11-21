package antiSpamFilter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class FileEmail extends FileAbstract {

	public static TypeFile typeFile = TypeFile.EMAIL;
	public TypeEmail typeEmail;
	public LinkedList<Email> linkedListEmails = new LinkedList<Email>();

	public FileEmail(TypeEmail typeEmail) {
		super(typeFile);

		this.typeEmail = typeEmail;

		if (super.getStatusFile() == StatusFile.APPROVED)
			insertToEmails();
		else
			resetList();

	}

	public void insertToEmails() {

		String[] columnDetail = null;

		for (String s : super.getAllLines()) {
			columnDetail = s.split("\t");

			String[] appliedRules = columnDetail;

			Email email = new Email(typeEmail);

			String fullPath = columnDetail[0];
			email.setFullPath(fullPath);

			String id = fullPath.replace("xval_initial/9/_spam_/", "").replace("xval_initial/9/_ham_/", "");
			email.setId(id);

			appliedRules = Arrays.copyOfRange(appliedRules, 1, appliedRules.length - 1);
			email.setAppliedRules(appliedRules);

			this.linkedListEmails.add(email);
		}

	}

	public TypeEmail getTypeEmail() {
		return this.typeEmail;
	}

	public LinkedList<Email> getLinkedListEmails() {
		return this.linkedListEmails;
	}

	public void resetList() {
		this.linkedListEmails = new LinkedList<Email>();
	}

	public int calculateFPorFN(HashMap<String, Rule> hmapRules) {
		int F = 0;

		for (Email email : this.linkedListEmails) {

			String[] appliedRules = email.getAppliedRules();

			double sum = 0;
			for (String rule : appliedRules) {

				Rule ruleObj = hmapRules.get(rule);

				double weight = 0;
				if (ruleObj != null) {
					weight = hmapRules.get(rule).getRuleWeight();
				} else {
					// System.out.println("The follow rule doesn't exist: " + rule);
				}
				
				sum += weight;
			}
			
			email.setCurrentSum(sum);

			if (this.typeEmail == TypeEmail.HAM) {

				// >5 - FP
				if (sum > 5) {
					// SPAM
					F++;
					email.isFPFN(true);
				} else {
					email.isFPFN(false);
				}

			} else if (this.typeEmail == TypeEmail.SPAM) {

				// <5 - FN
				if (sum <= 5) { 
					// NOT SPAM
					F++;
					email.isFPFN(true);
				} else {
					email.isFPFN(false);
				}

			}

		}
		return F;
	}
	
	public void showTableEmail() {
		
		Object[][] data = getDataTable();
		String[] columns = getColumnsTable();
		
		EmailTableGUI emailTableGui = new EmailTableGUI(data, columns);
		EmailTableGUI.createAndShowGUI(data, columns, typeEmail.toString());
	}

	private Object[][] getDataTable() {

		Object[][] data = new Object[linkedListEmails.size()][7];
		int i = 0;
		for (Email email : this.linkedListEmails) {
			
			data[i][0] = i+1;
			data[i][1] = typeEmail.toString();
			data[i][2] = email.getId();
			data[i][3] = ((email.getIsFPFN() == null) ? "N/A" : email.getIsFPFN());
			data[i][4] = ((email.getIsFPFN() == null) ? "N/A" : email.getCurrentSum());
			data[i][5] = email.getAppliedRulesString();
			data[i][6] = email.getFullPath();
			
			i++;
			
		}
		
		return data;
		
	}

	private String[] getColumnsTable() {
		
		String[] columnNames = { "#", "Type", "ID", "FP/FN", "Sum", "Rules", "Full Path" };

		return columnNames;
		
	}
}
