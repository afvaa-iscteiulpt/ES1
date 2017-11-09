package antiSpamFilter;

import java.awt.FileDialog;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Scanner;

import java.util.HashMap;

import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.Solution;

public class teste {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		AntiSpamFilterProblem problem = new AntiSpamFilterProblem(335);

		//Functions
		//(window 1)
		//HashMap<String, String[]> hmapHam = (HashMap<String, String[]>) readFileToHashMap((int) TYPES_FILES.HAM_SPAM.getValue());
		//HashMap<String, String[]> hmapSpam = (HashMap<String, String[]>) readFileToHashMap((int) TYPES_FILES.HAM_SPAM.getValue());
		HashMap<String, String> hmapRules = (HashMap<String, String>) readFileToHashMap((int) TYPES_FILES.RULES.getValue());

		//(window 2)
		HashMap<String, String> hmapNewWeights = generateRandomWeights(hmapRules);

		System.out.println(hmapNewWeights);
	}

	public static HashMap<?, ?> readFileToHashMap(int type) throws IOException {

		HashMap<String, String> hmapRules = new HashMap<String, String>();
		HashMap<String, String[]> hmapSpam = new HashMap<String, String[]>();

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filterToUse = null;
		if (type == TYPES_FILES.RULES.getValue()) {
			filterToUse = new FileNameExtensionFilter(".cf files", "cf");
		} else if (type == TYPES_FILES.HAM_SPAM.getValue()) {
			filterToUse = new FileNameExtensionFilter(".log files", "log");
		}

		chooser.setFileFilter(filterToUse);
		chooser.setAcceptAllFileFilterUsed(false);

		int returnValue = chooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();

			String path = chooser.getSelectedFile().getAbsolutePath();
			int numberOfLines = 0;

			BufferedReader reader = new java.io.BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {

				String[] columnDetail = line.split("\t");

				if (type == TYPES_FILES.RULES.getValue()) {

					String ruleName = columnDetail[0];
					String ruleWeight = columnDetail[1];

					hmapRules.put(ruleName, ruleWeight);

				} else if (type == TYPES_FILES.HAM_SPAM.getValue()) {

					String[] appliedRules = columnDetail;

					hmapSpam.put(
							columnDetail[0].replace("xval_initial/9/_spam_/", "").replace("xval_initial/9/_ham_/", ""),
							appliedRules);
				}

				numberOfLines++;

				line = reader.readLine();
			}

			reader.close();
		} else if (returnValue == JFileChooser.CANCEL_OPTION) {

			String alert = "No file selected";

			if (type == TYPES_FILES.RULES.getValue()) {
				return new HashMap<String, String>();
			} else if (type == TYPES_FILES.HAM_SPAM.getValue()) {
				return new HashMap<String, String[]>();
			}
		}

		if (type == TYPES_FILES.RULES.getValue()) {
			return hmapRules;
		} else if (type == TYPES_FILES.HAM_SPAM.getValue()) {
			return hmapSpam;
		}

		return null;
	}

	public static HashMap<String, String> generateRandomWeights(HashMap<String, String> hmap) {

		for (Entry<String, String> e : hmap.entrySet()) {

			int randomWeight = getRandomWeightValue();

			hmap.put(e.getKey(), String.valueOf(randomWeight));

		}

		return hmap;
	}

	public static int getRandomWeightValue() {
		int randomNum = ThreadLocalRandom.current().nextInt(-5, 5 + 1);
		return randomNum;
	}

}
