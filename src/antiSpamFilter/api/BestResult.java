package antiSpamFilter.api;

public class BestResult {
	
	private double doubleBestFp;
	private double doubleBestFn;
	private String[] bestWeights;

	public BestResult(double doubleBestFp, double doubleBestFn, String[] bestWeights) {
		this.doubleBestFp = doubleBestFp;
		this.doubleBestFn = doubleBestFn;
		this.bestWeights = bestWeights;
	}

	public String[] getBestWeights() {
		return bestWeights;
	}
	public double getDoubleBestFp() {
		return doubleBestFp;
	}

	public double getDoubleBestFn() {
		return doubleBestFn;
	}
}
