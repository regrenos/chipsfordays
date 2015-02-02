package bjt.bias_range_current_approximations;

public class CutOffRange {

	/**
	 * Collector current in the cut-off bias range for NPN BJT.
	 * 
	 * @param iS
	 *            - saturation current (A)
	 * @param alphaR
	 *            - common-base reverse current gain
	 * @return - collector current (A)
	 */
	public static double collectorCurrent(double iS, double alphaR) {
		return iS * (1 / alphaR - 1);
	}

	/**
	 * Emitter current in the cut-off bias range for NPN BJT.
	 * 
	 * @param iS
	 *            - saturation current (A)
	 * @param alphaF
	 *            - common-base forward current gain
	 * @return - emitter current (A)
	 */
	public static double emitterCurrent(double iS, double alphaF) {
		return iS * (-1 / alphaF + 1);
	}

	/**
	 * Base current in the cut-off bias range for NPN BJT.
	 * 
	 * @param iS
	 *            - saturation current (A)
	 * @param betaF
	 *            - common-emitter forward current gain
	 * @param betaR
	 *            - common-emitter reverse current gain
	 * @return - base current (A)
	 */
	public static double baseCurrent(double iS, double betaF, double betaR) {
		return -iS * (1 / betaF + 1 / betaR);
	}

}
