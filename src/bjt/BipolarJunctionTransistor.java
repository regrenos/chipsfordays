package bjt;

import bjt.amplifiers.CommonEmitterACBypassedEmitterResistance;
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
	 * @param alpha
	 *            - common-base current gain
	 * @return - common-emitter current gain
	 */
	public static double commonBaseToCommonEmitterGain(double alpha) {
		return alpha / (1 - alpha);
	}

	/**
	 * Converts common-emitter forward and reverse current gains into
	 * common-base reverse and forward current gains.
	 * 
	 * @param beta
	 *            - common-emitter current gain
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
		return (iS / betaF) * (Math.exp(vBE / Constants.kbtq) - 1)
				- (iS / betaR) * (Math.exp(vBC / Constants.kbtq) - 1);
	}

	/**
	 * Forward transconductance in the forward active range.
	 * 
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @param vCC - supply voltage
	 * @param betaF - common emitter forward current gain
	 * @param rE - emitter resistance
	 * @return gmfa - forward transconductance in forward active range
	 */
	public static double forwardTransconductance(double r1, double r2, double vCC, double betaF, double rE){
		return CommonEmitterACBypassedEmitterResistance.collectorCurrentApproximation(r1, r2, vCC, betaF, rE)/Constants.kbtq;
	}

	/**
	 * Collector to emitter resistance in the forward active range. If base-width modulation is neglected, this is infinite.
	 * 
	 * @param vA - Early voltage
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @param vCC - supply voltage
	 * @param betaF - common emitter forward current gain
	 * @param rE - emitter resistance
	 * @return rCE - collector to emitter resistance
	 */
	public static double collectorToEmitterResistance(double vA, double r1, double r2, double vCC, double betaF, double rE){
		if (vA == Double.MAX_VALUE){
			return Double.MAX_VALUE; // neglecting base-width modulation
		}
		else{
			return vA/(forwardTransconductance(r1, r2, vCC, betaF, rE)*Constants.kbtq);
		}
	}

	/**
	 * Base to emitter resistance in the forward active range.
	 * 
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @param vCC - supply voltage
	 * @param betaF - common emitter forward current gain
	 * @param rE - emitter resistance
	 * @return rBE - base to emitter resistance
	 */
	public static double baseToEmitterResistance(double r1, double r2, double vCC, double betaF, double rE){
		return betaF/forwardTransconductance(r1, r2, vCC, betaF, rE);
	}

	/**
	 * Approximate base-to-emitter bias in the forward active range. (V)
	 */
	public static final double vBEfa = 0.7;
	
	/**
	 * Saturation current of a BJT from knowing the Early Voltage
	 * 
	 * @param iC - collector current
	 * @param vCE - collector to emitter bias
	 * @param vA - early voltage
	 * @param vBE - base to emitter bias
	 * @return iS - saturation current
	 */
	public static double saturationCurrent(double iC, double vCE, double vA, double vBE){
		return iC/((1+vCE/vA)*Math.exp(vBE/Constants.kbtq));
	}
}
