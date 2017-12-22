package antiSpamFilter.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import antiSpamFilter.gui.EmailTableGUI;

/**
 * Class com os ficheiros do HAM e SPAM que extends o FileAbstract
 * 
 */

public class FileEmail extends FileAbstract {

	private static TypeFile typeFile = TypeFile.EMAIL;
	private TypeEmail typeEmail;
	private LinkedList<Email> linkedListEmails = new LinkedList<Email>();
	private double currentFpFn = 0;

	/**
	 * Inicializador
	 * Se o ficheiro estiver bom carrega o ficheiro nos email, se não faz reset à linked list de emails.
	 */
	public FileEmail(TypeEmail typeEmail) {
		super(typeFile);

		this.typeEmail = typeEmail;

		if (super.getStatusFile() == StatusFile.APPROVED)
			insertToEmails();
		else
			resetList();

	}

	/**
	 * Insere os emails para a linkedlist
	 * 
	 * @return void
	 */
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

			appliedRules = Arrays.copyOfRange(appliedRules, 0, appliedRules.length);
			email.setAppliedRules(appliedRules);

			this.linkedListEmails.add(email);
		}

	}

	/**
	 * Retorna o tipo de email - HAM ou SPAM
	 * 
	 * @return TypeEmail
	 */
	public TypeEmail getTypeEmail() {
		return this.typeEmail;
	}

	/**
	 * Retorna a lista de emails
	 * 
	 * @return LinkedList<Email>
	 */
	public LinkedList<Email> getLinkedListEmails() {
		return this.linkedListEmails;
	}

	/**
	 * Limpa a lista de emails
	 * 
	 * @return void
	 */
	public void resetList() {
		this.linkedListEmails = new LinkedList<Email>();
	}

	/**
	 * Calcula o FP ou FN para cada lista de emails, dependendo se é HAM ou SPAM
	 * 
	 * @param HashMap<String, Rule> - hashmap com as rules
	 * @return int
	 */
	public int calculateFPorFN(HashMap<String, Rule> hmapRules) {
		int F = 0;

		for (Email email : this.linkedListEmails) {

			String[] appliedRules = email.getAppliedRules();

			double sum = 0;
			for (String rule : appliedRules) {

				Rule ruleObj = hmapRules.get(rule);

				String weight = "0";
				if (ruleObj != null) {
					weight = hmapRules.get(rule).getRuleWeight();
				} else {
					// System.out.println("The follow rule doesn't exist: " + rule);
				}
				
				sum += Double.valueOf(weight);
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
		
		
		currentFpFn = F;
		return F;
	}
	
	/**
	 * Motra a gui com todos os emails e a sua soma
	 *
	 * @return void
	 */
	public void showTableEmail() {
		
		Object[][] data = getDataTable();
		String[] columns = getColumnsTable();
		
		EmailTableGUI.createAndShowGUI(data, columns, typeEmail.toString());
		
	}

	/**
	 * Processa a informação e cria as colunas da tabela
	 *
	 * @return Object[][]
	 */
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

	/**
	 * Retorna os nomes das colunas da tabela
	 *
	 * @return String[]
	 */
	private String[] getColumnsTable() {
		
		String[] columnNames = { "#", "Type", "ID", "FP/FN", "Sum", "Rules", "Full Path" };

		return columnNames;
		
	}

	/**
	 * Define o melhor FP ou FN saido do jmetal
	 *
	 * @param double
	 * @return void
	 */
	public void setFpFn(double doubleBestFp) {
		this.currentFpFn = doubleBestFp;
	}
	
	/**
	 * Retorna o FP ou FN guardado
	 *
	 * @return double
	 */
	public double getFpFn() {
		return currentFpFn;
	}
}
