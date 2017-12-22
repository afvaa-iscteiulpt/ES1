package antiSpamFilter.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ArrayUtils;

//API TESTER

public class ConsoleMenu {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private FileRule fileRules = null;
	private FileEmail fileHam = null;
	private FileEmail fileSpam = null;

	private int fileRulesLoaded = 0;
	private int fileHamLoaded = 0;
	private int fileSpamLoaded = 0;
	
	private boolean jmetalalreadyrunned = false;

	public ConsoleMenu() throws IOException, InterruptedException {
		showMainWindow();
	}

	public void showMainWindow() throws IOException, InterruptedException {
		int firstTime = 0;
		while (fileRulesLoaded == 0 || fileHamLoaded == 0 || fileSpamLoaded == 0) {

			if (firstTime == 0)
				System.out.println("#### Window 1 - SET UP FILES");

			System.out.println("1 - load rules file" + loaded(fileRulesLoaded) + "\n2 - load ham file"
					+ loaded(fileHamLoaded) + "\n3 - load spam file" + loaded(fileSpamLoaded) + "\n0 - exit");

			int[] validInputs = { 0, 1, 2, 3 };

			int inputOption = 0;
			while (inputOption == 0) {
				TimeUnit.MILLISECONDS.sleep(10);
				try {
					System.out.print("Choose one option: ");
					inputOption = Integer.parseInt(br.readLine());

					checkForExitOrInvalid(inputOption, validInputs, 1);

				} catch (NumberFormatException nfe) {
					System.err.println("Invalid Format!");
					continue;

				}
			}

			switch (inputOption) {
			case 1:
				fileRules = new FileRule();
				fileRulesLoaded = 1;
				break;
			case 2:
				fileHam = new FileEmail(TypeEmail.HAM);
				fileHamLoaded = 1;
				break;
			case 3:
				fileSpam = new FileEmail(TypeEmail.SPAM);
				fileSpamLoaded = 1;
				break;
			default:
				break;
			}

			firstTime++;
		}

		System.out.println("All files loaded");

		showMenuTypeOfConfig(0);
	}

	private String loaded(int fileLoaded) {
		if (fileLoaded == 1)
			return " (loaded)";

		return "";
	}

	private int checkForExitOrInvalid(int inputOption, int[] validInputs, int waitingForInputWindow) {

		if (inputOption == 0) {
			System.err.println("Terminating program!");
			System.exit(0);
		} else if (!ArrayUtils.contains(validInputs, inputOption)) {
			System.err.println("Invalid Number!");
			return 1;
		} else if (waitingForInputWindow == 1) {
			System.out.println("Waiting for input ...");
			return 0;
		}

		return 0;
	}

	public void showMenuTypeOfConfig(int firstTime) throws InterruptedException, IOException {

		if (firstTime == 0)
			System.out.println("\n#### Window 2 - CHOOSE TYPE OF CONFIGURATION");

		System.out.println(
				"1 - MANUAL (window)" + "\n2 - AUTO (jmetal) (window)" + "\n3 - CONFIG RULES (window)" + "\n0 - exit");

		int[] validInputs = { 0, 1, 2, 3 };

		int inputOption = 0;
		while (inputOption == 0) {
			TimeUnit.MILLISECONDS.sleep(10);
			try {
				System.out.print("Choose one option: ");
				inputOption = Integer.parseInt(br.readLine());

				if (checkForExitOrInvalid(inputOption, validInputs, 0) == 1) {
					showMenuTypeOfConfig(1);
				}

			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
				continue;

			}
		}

		switch (inputOption) {
		case 1:
			showManualWindow(0);
			break;
		case 2:
			showAutoWindow(0);
			break;
		case 3:
			showConfigRulesWindow(0);
			break;
		default:
			break;
		}

	}

	public void showManualWindow(int firstTime) throws InterruptedException, IOException {

		if (firstTime == 0)
			System.out.println("\n#### Window 2.1 - MANUAL CONFIGURATION");

		System.out.println("1 - config rules (window)" + "\n2 - generate random weights for each rule"
				+ "\n3 - print rules and weights" + "\n4 - calculate FP and FN" + "\n5 - save to file" + "\n9 - back" + "\n0 - exit");

		int[] validInputs = { 0, 1, 2, 3, 4, 5, 9 };

		int inputOption = 0;
		while (inputOption == 0) {
			TimeUnit.MILLISECONDS.sleep(10);
			try {
				System.out.print("Choose one option: ");
				inputOption = Integer.parseInt(br.readLine());

				if (checkForExitOrInvalid(inputOption, validInputs, 0) == 1) {
					showManualWindow(1);
				}

			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
				continue;

			}
		}

		switch (inputOption) {
		case 1:
			showConfigRulesWindow(0);
			break;
		case 2:
			generateRandomWeightsForEachRule();
			break;
		case 3:
			printRulesAndWeights();
			showManualWindow(1);
			break;
		case 4:
			calculateFPandFN();
			break;
		case 5:
			saveToFile();
			showManualWindow(0);
			break;
		case 9:
			showMenuTypeOfConfig(0);
			return;
		default:
			break;
		}
	}

	private void calculateFPandFN() throws InterruptedException, IOException {
		System.out.println("\nFP = " + fileHam.calculateFPorFN(fileRules.getHmapRules()) + "\nFN = "
				+ fileSpam.calculateFPorFN(fileRules.getHmapRules()) + "\n");

		showManualWindow(1);
	}

	private void generateRandomWeightsForEachRule() throws InterruptedException, IOException {
		fileRules.generateRandomWeightsForEachRule();

		System.out.println("Random weights generated!");
		
		showManualWindow(1);
	}

	private void printRulesAndWeights() throws InterruptedException, IOException {
		System.out.println("\nALL RULES = " + fileRules.getHmapRulesString() + "\n");
	}

	public void showAutoWindow(int firstTime) throws InterruptedException, IOException {

		if (firstTime == 0)
			System.out.println("\n#### Window 2.2 - AUTOMATIC CONFIGURATION");

		String extraFeatures = (jmetalalreadyrunned != false) ? "\n4 - compile HV file" + "\n5 - compile latex file" : "";
		System.out.println("1 - config rules (window)" + "\n2 - run jmetal and find best weights"
				+ "\n3 - print rules and weights" + extraFeatures 
				+ "\n6 - save rules to file" + "\n9 - back" + "\n0 - exit");

		int[] validInputs = { 0, 1, 2, 3, 4, 5, 6, 9 };

		int inputOption = 0;
		while (inputOption == 0) {
			TimeUnit.MILLISECONDS.sleep(10);
			try {
				System.out.print("Choose one option: ");
				inputOption = Integer.parseInt(br.readLine());

				if (checkForExitOrInvalid(inputOption, validInputs, 0) == 1) {
					showAutoWindow(1);
				}

			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
				continue;

			}
		}

		switch (inputOption) {
		case 1:
			showConfigRulesWindow(0);
			break;
		case 2:
			runJmetalAndReturnResults(); //falta acabar este
			showAutoWindow(1);
			break;
		case 3:
			printRulesAndWeights();
			showAutoWindow(1);
			break;
		case 4:
			compileHv(); 
			break;
		case 5:
			compileLatex(); 
			break;
		case 6:
			saveToFile();
			showAutoWindow(1);
			break;
		case 9:
			showMenuTypeOfConfig(0);
			return;
		default:
			break;
		}
	}

	private void runJmetalAndReturnResults() {

		AntiSpamFilterProblem problem = new AntiSpamFilterProblem(fileHam, fileSpam, fileRules);

		AntiSpamFilterAutomaticConfiguration antiSpamConfig = new AntiSpamFilterAutomaticConfiguration(problem);

		try {
			antiSpamConfig.runSolution();

			// results[FP, FN, WEIGTHS to put in hashmap]
			BestResult results = antiSpamConfig.checkBestSolution();
			
			fileRules.setWeights(results.getBestWeights());
			
			fileHam.setFpFn(results.getDoubleBestFn());
			fileSpam.setFpFn(results.getDoubleBestFp());
		
			System.out.println("\nFP = " + results.getDoubleBestFp() + "\nFN = "
					+ results.getDoubleBestFn() + "\n");

		} catch (IOException e) {
			System.out.println("Something wrong with current problem!");
			System.exit(0);
			e.printStackTrace();
		}
		
		jmetalalreadyrunned = true;
	}

	private void compileLatex() throws IOException, InterruptedException {

		LatexFile latexfile = new LatexFile();

		latexfile.compile();

		System.out.println("\n");
		showAutoWindow(1);

	}

	private void compileHv() throws IOException, InterruptedException {

		HVFile hvfile = new HVFile();

		hvfile.compile();

		System.out.println("\n");
		showAutoWindow(1);

	}

	public void showConfigRulesWindow(int firstTime) throws InterruptedException, IOException {

		if (firstTime == 0)
			System.out.println("\n#### Window 3 - CONFIG RULES");

		System.out.println("1 - save rules to file" + "\n2 - add new rule" + "\n3 - delete rule(s)"
				+ "\n4 - edit rule(s)" + "\n5 - show SPAM file" + "\n6 - show HAM file" + "\n7 - search rules"
				+ "\n8 - print rules and weights" + "\n9 - back" + "\n0 - exit");

		int[] validInputs = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		int inputOption = 0;
		while (inputOption == 0) {
			TimeUnit.MILLISECONDS.sleep(10);
			try {
				System.out.print("Choose one option: ");
				inputOption = Integer.parseInt(br.readLine());

				if (checkForExitOrInvalid(inputOption, validInputs, 0) == 1) {
					showManualWindow(1);
				}

			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
				continue;

			}
		}

		switch (inputOption) {
		case 1:
			saveToFile();
			showConfigRulesWindow(1);
			break;
		case 2:
			addNewRule();
			break;
		case 3:
			deleteRules();
			break;
		case 4:
			editRules();
			break;
		case 5:
			fileSpam.showTableEmail();
			System.out.println("Showing file!\n");
			showConfigRulesWindow(1);
			break;
		case 6:
			fileHam.showTableEmail();
			System.out.println("Showing file!\n");
			showConfigRulesWindow(1);
			break;
		case 7:
			searchRule();
			break;
		case 8:
			printRulesAndWeights();
			showConfigRulesWindow(1);
			break;
		case 9:
			showMenuTypeOfConfig(0);
			return;
		default:
			break;
		}
	}

	private void editRules() throws InterruptedException, IOException {
		System.out.println("Enter rule/s names separated by comma: ");

		String rules = br.readLine();

		System.out.println("Enter rule weight:");

		String ruleWeight = br.readLine();

		fileRules.applyToRules(rules.split(",", -1), ruleWeight);

		System.out.println("Rules edited!\n");

		showConfigRulesWindow(1);

	}

	private void addNewRule() throws InterruptedException, IOException {
		System.out.println("Enter new rule name: ");

		String rule = br.readLine();

		System.out.println("Enter rule weight:");

		String ruleWeight = br.readLine();

		fileRules.createNewRule(rule, ruleWeight);

		System.out.println("Rule created!\n");

		showConfigRulesWindow(1);
	}

	private void searchRule() throws InterruptedException, IOException {
		System.out.println("Enter rule names for search: ");

		String rule = br.readLine();

		System.out.println(fileRules.findRule(rule));

		showConfigRulesWindow(1);
	}

	private void deleteRules() throws IOException, InterruptedException {
		System.out.println("Enter rules names separated by comma: ");

		String rules = br.readLine();

		fileRules.deleteRules(rules.split(",", -1));

		System.out.println("Rules deleted!\n");

		showConfigRulesWindow(1);
	}

	private void saveToFile() throws InterruptedException, IOException {
		fileRules.replaceFileContent();
		
		System.out.println("Saved to file!\n");
	}

}
