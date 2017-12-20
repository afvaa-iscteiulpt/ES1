package antiSpamFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.omg.CORBA.portable.InputStream;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {

		ConsoleMenu consoleMenu = new ConsoleMenu();
		
		
		
		//HVFile hvfile = new HVFile();

		//LatexFile l = new LatexFile();
		//hvfile.compile();
		
		
		//ystem.out.println(hvfile.compile());
		
		//runH();
		
		  
		  //Rscript name
		  //pdflatex name
		
		//FileRule fileRules = new FileRule();
		//fileRules.generateRandomWeightsForEachRule();
		//fileRules.createNewRule("LALALA", 4);
		//fileRules.deleteRules("LALALA");
		//fileRules.replaceFileContent();

		//FileEmail fileHam = new FileEmail(TypeEmail.HAM);
		//System.out.println("FP = " + fileHam.calculateFPorFN(fileRules.getHmapRules()));
		//FileEmail fileSpam = new FileEmail(TypeEmail.SPAM);
		//System.out.println("FN = " + fileSpam.calculateFPorFN(fileRules.getHmapRules()));

		//runMultipleTests(10000);

		//String[] rulesToModified = { "SUBJ_AS_SEEN", "DNS_FROM_RFC_BOGUSMX", "DRUGS_DIET" };
		//fileRules.applyToRules(rulesToModified, -10);
		//fileRules.replaceFileContent();

		//fileRules.replaceFileContent();

		//fileHam.showTableEmail();
		//fileSpam.showTableEmail();
		/*
		AntiSpamFilterProblem problem = new AntiSpamFilterProblem(fileRules.getNumberOfLines(), fileHam, fileSpam, fileRules);
		
		AntiSpamFilterAutomaticConfiguration antiSpamConfig = new AntiSpamFilterAutomaticConfiguration(problem);

		//fileHam.showTableEmail();
		//fileSpam.showTableEmail();
		
		try {
			
			antiSpamConfig.runSolution();
			
		} catch (IOException e) {
			System.out.println("Something wrong with current problem!");
			e.printStackTrace();
		}
		*/
	}

	public static void runMultipleTests(int numberOfTests) {
		FileRule fileRules = new FileRule();
		FileEmail fileHam = new FileEmail(TypeEmail.HAM);
		FileEmail fileSpam = new FileEmail(TypeEmail.SPAM);

		boolean perfectMatch = false;
		for (int i = 0; i < numberOfTests; i++) {

			System.out.println("RUN: " + i);

			fileRules.generateRandomWeightsForEachRule();

			double FP = fileHam.calculateFPorFN(fileRules.getHmapRules());
			double FN = fileSpam.calculateFPorFN(fileRules.getHmapRules());

			if (FP == FN) {
				perfectMatch = true;
				fileRules.replaceFileContent();
				System.out.println("Perfect match found with FP: " + FP + " and FN: " + FN);
			}

			System.out.println("FP = " + FP);
			System.out.println("FN = " + FN + "\n");
		}

		if (perfectMatch)
			System.out.println("Perfect match found " + "\n");
		
		fileHam.showTableEmail();
		fileSpam.showTableEmail();
	}

	public static void runH() throws IOException {
		ProcessBuilder builder = new ProcessBuilder(
	            "cmd.exe", "/c", "pdflatex aa");
	        builder.redirectErrorStream(true);
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();
	            if (line == null) { break; }
	            System.out.println(line);
	        }
	}
}
