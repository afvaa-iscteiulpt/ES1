package antiSpamFilter.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Class com o ficheiro da rule que extends o FileAbstract
 * 
 */

public class FileRule extends FileAbstract {

	private static TypeFile typeFile = TypeFile.RULE;
	private HashMap<String, Rule> hmapRules = new HashMap<String, Rule>();
 
	/**
	 * Inicializador
	 * Se o ficheiro estiver bom carrega o ficheiro nas rules, se não faz reset ao hashmap das rules.
	 */
	public FileRule() {
		super(typeFile);

		if (super.getStatusFile() == StatusFile.APPROVED)
			insertToRules();
		else
			resetHashmap();
	}

	/**
	 * Insere as rules para o hashmap
	 * 
	 * @return void
	 */
	private void insertToRules() {

		String[] columnDetail = null;

		for (String line : super.getAllLines()) {
			columnDetail = line.split("\t");

			String ruleName = columnDetail[0];
			
			String ruleWeight = "0";
			if(columnDetail .length > 1) {
				if(columnDetail[1] != null)
					ruleWeight = columnDetail[1];
			}
			
			Rule rule = new Rule();

			rule.setRuleName(ruleName);
			rule.setRuleWeight(ruleWeight);

			this.hmapRules.put(ruleName, rule);
		}

	}

	/**
	 * Devolve todas as rules do hashmap
	 * 
	 * @return HashMap<String, Rule>
	 */
	public HashMap<String, Rule> getHmapRules() {
		return this.hmapRules;
	}

	/**
	 * Limpa o hashmap das rules
	 * 
	 * @return void
	 */
	public void resetHashmap() {
		this.hmapRules = new HashMap<String, Rule>();
	}

	/**
	 * Cria uma nova rule e adiciona ao hashmap
	 * 
	 * @param String - nome da rule
	 * @param String - peso da rule
	 * @return void
	 */
	public void createNewRule(String ruleName, String ruleWeight) {
		Rule rule = new Rule();

		rule.setRuleName(ruleName);
		rule.setRuleWeight(ruleWeight);

		this.hmapRules.put(ruleName, rule);
	}
	
	/**
	 * Elimina as rules 
	 * 
	 * @param String[] - rules
	 * @return void
	 */
	public void deleteRules(String[] rulesName) {
		for (String rule : rulesName) {
			this.hmapRules.remove(rule);
		}
	}

	/**
	 * Gera pesos aleatorios para cada rule
	 * 
	 * @return void
	 */
	public void generateRandomWeightsForEachRule() {
		for (Entry<String, Rule> e : this.hmapRules.entrySet()) {
			e.getValue().generateRandomWeight();
		}
	}

	/**
	 * Substitui as rules e seus pesos no ficheiro
	 * 
	 * @return void
	 */
	public void replaceFileContent() {
		String oldFileName = super.getPath();
		String tmpFileName = "tmp_" + super.getName();

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(tmpFileName));
			
			for(Entry<String, Rule> entry : this.hmapRules.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue().getRuleWeight();

			    bw.write(key + "\t" + value + "\n");
			}
		} catch (Exception e) {
			return;
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				return;
			}
		}
		
		File oldFile = new File(oldFileName);
		oldFile.delete();

		File newFile = new File(tmpFileName);
		newFile.renameTo(oldFile);
	}
	
	/**
	 * Aplica o novo peso as rules passadas no array String[]
	 * 
	 * @param String[] - nome das rules
	 * @param String - peso a aplicar nas rules
	 * @return void
	 */
	public void applyToRules(String[] rules, String weight) {
		for (String rule : rules) {
			
			Rule ruleObj = this.hmapRules.get(rule);
			
			if (ruleObj != null) {
				hmapRules.get(rule).setRuleWeight(weight);
			} 			
		}
	}

	/**
	 * Imprime na console cada rule e o seu peso
	 * Debugging
	 * 
	 * @return void
	 */
	public String getHmapRulesString() {
		String fullString = "";
		for(Entry<String, Rule> entry : this.getHmapRules().entrySet()) {
		    String subString = entry.getKey() + "(" + entry.getValue().getRuleWeight() + ")";
			fullString += (subString + " | ");
		}
		return fullString;	
	}

	/**
	 * Substitui o hashmap das rules pelo passado como parametro
	 * 
	 * @param HashMap<String, Rule>
	 * @return void
	 */
	public void setHashMap(HashMap<String, Rule> hmapRules2) {
		this.hmapRules = hmapRules2;		
	}

	/**
	 * Procura nas rules a rule passada 
	 * 
	 * @param String - string a procurar
	 * @return String - found, not found
	 */
	public String findRule(String rule) {
		if(this.hmapRules.containsKey(rule)) {
			return "Rule found!\n";
		} else {
			return "Rule not found!\n";
		}
	}

	/**
	 * Substitui cada rule com o peso. O index bate certo pelo que é só percorrer o hashmap e inserir o peso.
	 * 
	 * @param String[] - lista com todos os pesos
	 * @return void 
	 */
	public void setWeights(String[] bestWeights) {
		
		int i = 0;
		for (String key : hmapRules.keySet()) {
	        Rule r = hmapRules.get(key);
	        
	        r.setRuleWeight(String.valueOf(bestWeights[i]));
	        i++;
	    }
		
	}
}