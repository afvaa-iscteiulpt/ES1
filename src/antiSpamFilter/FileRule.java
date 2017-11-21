package antiSpamFilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class FileRule extends FileAbstract {

	public static TypeFile typeFile = TypeFile.RULE;
	public HashMap<String, Rule> hmapRules = new HashMap<String, Rule>();

	public FileRule() {
		super(typeFile);

		if (super.statusFile == StatusFile.APPROVED)
			insertToRules();
		else
			resetHashmap();
	}

	public void insertToRules() {

		String[] columnDetail = null;
		Rule rule = new Rule();
		for (String line : super.allLines) {
			columnDetail = line.split("\t");

			String ruleName = columnDetail[0];
			String ruleWeight;
			if (columnDetail.length > 1) {
				ruleWeight = columnDetail[1];
				rule.setRuleWeight(Integer.parseInt(ruleWeight));
			} else {
				ruleWeight = null;
			}


			rule.setRuleName(ruleName);

			this.hmapRules.put(ruleName, rule);
		}

	}

	public HashMap<String, Rule> getHmapRules() {
		return this.hmapRules;
	}

	public void resetHashmap() {
		this.hmapRules = new HashMap<String, Rule>();
	}

	public void createNewRule(String ruleName, int ruleWeight) {
		Rule rule = new Rule();

		rule.setRuleName(ruleName);
		rule.setRuleWeight(ruleWeight);

		this.hmapRules.put(ruleName, rule);
	}

	public void deleteRule(String ruleName) {
		this.hmapRules.remove(ruleName);
	}

	public void generateRandomWeightsForEachRule() {
		for (Entry<String, Rule> e : this.hmapRules.entrySet()) {
			e.getValue().generateRandomWeight();
		}
	}

	public void replaceFileContent() {
		String oldFileName = super.path;
		String tmpFileName = "tmp_" + super.getName();

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(tmpFileName));

			for (Entry<String, Rule> entry : this.hmapRules.entrySet()) {
				String key = entry.getKey();
				int value = entry.getValue().getRuleWeight();

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

	public void applyToRules(String[] rules, int weight) {
		for (String rule : rules) {

			Rule ruleObj = this.hmapRules.get(rule);

			if (ruleObj != null) {
				hmapRules.get(rule).setRuleWeight(weight);
			}
		}
	}
}
