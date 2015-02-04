package bjt.bias_range_approximations;

import util.Constants;

public class ReverseActiveRange {

	/**
	 * Collector current in the reverse active bias range for an NPN BJT.
	 * 
	 * @param vBC
	 *            - base to collector bias (V)
	 * @param iS
	 *            - saturation current (A)
	 * @param alphaR
	 *            - common-base reverse current gain
	 * @return - collector current (A)
	 */
	public static double collectorCurrent(double vBC, double iS, double alphaR) {
		return -iS * (1 - Math.exp(vBC / Constants.kbtq) / alphaR);
	}

	/**
	 * Emitter current in the reverse active bias range for an NPN BJT.
	 * 
	 * @param vBC
	 *            - base to collector bias (V)
	 * @param iS
	 *            - saturation current (A)
	 * @param alphaF
	 *            - common-base reverse current gain
	 * @return - emitter current (A)
	 */
	public static double emitterCurrent(double vBC, double iS, double alphaF) {
		return -iS * (1 / alphaF - Math.exp(vBC / Constants.kbtq));
	}

	/**
	 * Base current in the reverse active bias range for an NPN BJT.
	 * 
	 * @param vBC
	 *            - base to collector bias (V)
	 * @param iS
	 *            - saturation current (A)
	 * @param betaF
	 *            - common-emitter forward current gain
	 * @param betaR
	 *            - common-emitter reverse current gain
	 * @return - base current (A)
	 */
	public static double baseCurrent(double vBC, double iS, double betaF,
			double betaR) {
		return iS * (-1 / betaF + Math.exp(vBC / Constants.kbtq / betaR));
	}

}
