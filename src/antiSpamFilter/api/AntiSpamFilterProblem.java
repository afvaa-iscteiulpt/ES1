package antiSpamFilter.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	private FileEmail fileHam;
	private FileEmail fileSpam;
	private FileRule fileRules;

	public AntiSpamFilterProblem(FileEmail fileHam, FileEmail fileSpam, FileRule fileRules) {
		setNumberOfVariables(fileRules.getNumberOfLines());
		setNumberOfObjectives(2);
		setName("AntiSpamFilterProblem");

		this.fileHam = fileHam;
		this.fileSpam = fileSpam;
		this.fileRules = fileRules;
		
		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(-5.0);
			upperLimit.add(5.0);
		}

		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	public void evaluate(DoubleSolution solution) {
		
		//set weight for each rule
		int index = 0;
		for(Entry<String, Rule> entry : fileRules.getHmapRules().entrySet()) {
		    Rule rule = entry.getValue();
		    
		    rule.setRuleWeight(String.valueOf(solution.getVariableValue(index)));
		    
		    index++;
		}
		
		HashMap<String, Rule> hmapRules = this.fileRules.getHmapRules();
		int FP = this.fileHam.calculateFPorFN(hmapRules);
		int FN = this.fileSpam.calculateFPorFN(hmapRules);
		
		solution.setObjective(0, FP);
		solution.setObjective(1, FN);
	}
}
