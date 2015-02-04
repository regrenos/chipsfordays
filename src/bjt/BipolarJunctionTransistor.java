package bjt;

import util.Constants;

/**
 * Class containing utility function relating to bipolar junction transistors.
 * 
 * @author Steve
 *
 */
public class BipolarJunctionTransistor {

	/**
	 * Determies the bias range of the BJT depending on biases. Input PNP biases
	 * as if vBE were vEB, etc.
	 * 
	 * @param vBE
	 *            - bias between base and emitter
	 * @param vBC
	 *            - bias between base and collector
	 * @return - the bias range of the BJT
	 */
	public static String determineBiasRange(double vBE, double vBC) {
		if (vBE <= 0) {
			if (vBC <= 0) {
				return "Cut-off";
			} else {
				return "Reverse-active";
			}
		} else {
			if (vBC <= 0) {
				return "Forward-active";
			} else {
				return "Saturation";
			}
		}
	}

	/**
	 * Converts common-base forward and reverse current gains into
	 * common-emitter reverse and forward current gains.
	 * 
	 * @param alpha - common-base current gain
	 * @return - common-emitter current gain
	 */
	public static double commonBaseToCommonEmitterGain(double alpha) {
		return alpha / (1 - alpha);
	}

	/**
	 * Converts common-emitter forward and reverse current gains into
	 * common-base reverse and forward current gains.
	 * 
	 * @param beta - common-emitter current gain
	 * @return - common-base current gain
	 */
	public static double commonEmitterToCommonBaseGain(double beta) {
		return beta / (1 + beta);
	}

	/**
	 * Complete NPN BJT Ebers-Moll Model for collector current.
	 * 
	 * @param vBE
	 *            - base to emitter bias (V)
	 * @param vBC
	 *            - base to collector bias (V)
	 * @param alphaR
	 *            - common-base reverse current gain
	 * @param iS
	 *            - saturation current (A)
	 * @return - collector current (A)
	 */
	public static double collectorCurrent(double vBE, double vBC,
			double alphaR, double iS) {
		return iS * (Math.exp(vBE / Constants.kbtq) - 1) - (iS / alphaR)
				* (Math.exp(vBC / Constants.kbtq) - 1);
	}

	/**
	 * Complete NPN BJT Ebers-Moll Model for emitter current.
	 * 
	 * @param vBE
	 *            - base to emitter bias (V)
	 * @param vBC
	 *            - base to collector bias (V)
	 * @param alphaF
	 *            - common-base forward current gain
	 * @param iS
	 *            - saturation current (A)
	 * @return - emitter current (A)
	 */
	public static double emitterCurrent(double vBE, double vBC, double alphaF,
			double iS) {
		return (iS / alphaF) * (Math.exp(vBE / Constants.kbtq) - 1) - iS
				* (Math.exp(vBC / Constants.kbtq) - 1);
	}

	/**
	 * Complete NPN BJT Ebers-Moll Model for base current.
	 * 
	 * @param vBE
	 *            - base to emitter bias (V)
	 * @param vBC
	 *            - base to collector bias (V)
	 * @param betaF
	 *            - common-emitter forward current gain
	 * @param betaR
	 *            - common-emitter reverse current gain
	 * @param iS
	 *            - saturation current (A)
	 * @return - base current (A)
	 */
	public static double baseCurrent(double vBE, double vBC, double betaF,
			double betaR, double iS) {
		return (iS / betaF) * (Math.exp(vBE / Constants.kbtq) - 1) - (iS / betaR)
				* (Math.exp(vBC / Constants.kbtq) - 1);
	}
}
