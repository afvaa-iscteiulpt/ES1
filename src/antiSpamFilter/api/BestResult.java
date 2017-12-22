package antiSpamFilter.api;

/**
 * Esta class regista o melhor resultado saido do jmetal. Serve apenas de ponte de informação entre a configuração e a gui.
 */
public class BestResult {
	
	private double doubleBestFp;
	private double doubleBestFn;
	private String[] bestWeights;

	public BestResult(double doubleBestFp, double doubleBestFn, String[] bestWeights) {
		this.doubleBestFp = doubleBestFp;
		this.doubleBestFn = doubleBestFn;
		this.bestWeights = bestWeights;
	}

	/**
	 * Retorna os melhores pesos registados pelo jmetal
	 *
	 * @return      String[]
	 */
	public String[] getBestWeights() {
		return bestWeights;
	}
	
	/**
	 * Retorna o FP para os pesos registados
	 *
	 * @return      double
	 */
	public double getDoubleBestFp() {
		return doubleBestFp;
	}

	/**
	 * Retorna o FN para os pesos registados
	 *
	 * @return      double
	 */
	public double getDoubleBestFn() {
		return doubleBestFn;
	}
}
