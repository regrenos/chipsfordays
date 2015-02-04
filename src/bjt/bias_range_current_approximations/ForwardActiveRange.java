package bjt.bias_range_current_approximations;

import util.Constants;

public class ForwardActiveRange {

	/**
	 * Collector current in the forward active bias range for an NPN BJT.
	 * 
	 * @param vBE
	 *            - base to emitter bias (V)
	 * @param iS
	 *            - saturation current (A)
	 * @param alphaR
	 *            - common-base reverse current gain
	 * @return - collector current (A)
	 */
	public static double collectorCurrent(double vBE, double iS, double alphaR) {
		return iS * (Math.exp(vBE / Constants.kbtq) + 1 / alphaR);
	}

	/**
	 * Calculates the saturation current if collector current is known for an
	 * NPN BJT in the forward active bias range.
	 * 
	 * @param iC
	 *            - collector current (A)
	 * @param vBE
	 *            - base to emitter bias (V)
	 * @param alphaR
	 *            - common-base reverse current gain
	 * @return - saturation current (A)
	 */
	public static double saturationCurrentFromCollectorCurrent(double iC,
			double vBE, double alphaR) {
		return iC / (Math.exp(vBE / Constants.kbtq) + 1 / alphaR);
	}

	/**
	 * Emitter current for the forward active bias range for an NPN BJT.
	 * 
	 * @param vBE
	 *            - bsae to emitter bias (V)
	 * @param iS
	 *            - saturation current (A)
	 * @param alphaF
	 *            - common-base forward current gain
	 * @return - emitter current (A)
	 */
	public static double emitterCurrent(double vBE, double iS, double alphaF) {
		return iS * (Math.exp(vBE / Constants.kbtq) / alphaF + 1);
	}

	/**
	 * Base current for the forward active bias range for an NPN BJT.
	 * 
	 * @param vBE
	 *            - base to emitter bias (V)
	 * @param iS
	 *            - saturation current (A)
	 * @param betaF
	 *            - common-emitter forward current gain
	 * @param betaR
	 *            - common-emitter reverse current gain
	 * @return - base current (A)
	 */
	public static double baseCurrent(double vBE, double iS, double betaF,
			double betaR) {
		return iS * (Math.exp(vBE / Constants.kbtq) / betaF - 1 / betaR);
	}

	/**
	 * Finds the common-emitter forward current gain from the saturation and base currents.
	 * 
	 * @param iS - saturation current (A)
	 * @param iB - base current (A)
	 * @param vBE - base to emitter bias (V)
	 * @return - common-emitter forward current gain
	 */
	public static double betaFFromBaseCurrent(double iS, double iB, double vBE){
		return (iS/iB)*(Math.exp(vBE/Constants.kbtq)-1);
	}
	
	/**
	 * Calculates the common-emitter forward current gain from its definition.
	 * 
	 * @param iC - collector current (A)
	 * @param iB - base current (A)
	 * @return - common-emitter forward current gain 
	 */
	public static double betaFFromDefinition(double iC, double iB){
		return iC / iB;
	}
	
}
