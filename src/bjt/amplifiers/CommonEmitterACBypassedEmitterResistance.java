package bjt.amplifiers;

import util.Constants;

public class CommonEmitterACBypassedEmitterResistance {
	
	/**
	 * Approximate base-to-emitter bias in the forward active range. (V)
	 */
	public static final double vBEfa = 0.7;
	
	private static double vBB(double r1, double r2, double vCC){
		return vCC*r1/(r1+r2);
	}
	
	private static double rB(double r1, double r2){
		return r1*r2/(r1+r2);
	}
	
	/**
	 * Quiescent base current using the approximation that the base-to-emitter bias is 0.7, and the BJT is in forward active range.
	 * 
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @param vCC - supply voltage
	 * @param betaF - common emitter forward current gain
	 * @param rE - emitter resistance
	 * @return - base current
	 */
	public static double baseCurrentApproximation(double r1, double r2, double vCC, double betaF, double rE){
		return (vBB(r1, r2, vCC)-vBEfa)/(rB(r1, r2)+(1+betaF)*rE);
	}
	
	/**
	 * Quiescent collector current using the approximation that the base-to-emitter bias is 0.7 and the BJT is in forward active range.
	 * 
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @param vCC - supply voltage
	 * @param betaF - common emitter forward current gain
	 * @param rE - emitter resistance
	 * @return - collector current
	 */
	public static double collectorCurrentApproximation(double r1, double r2, double vCC, double betaF, double rE){
		return betaF*baseCurrentApproximation(r1, r2, vCC, betaF, rE);
	}
	
	/**
	 * Quiescent collector to emitter bias using the approximation that the base-to-emitter bias is 0.7 and the BJT is in forward active range.
	 * 
	 * @param vCC - supply voltage
	 * @param betaF - common emitter forward current gain
	 * @param rC - collector resistance
	 * @param rE - emitter resistance
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @return - vCE approximation
	 */
	public static double collectorToEmitterBiasApproximation(double vCC, double betaF, double rC, double rE, double r1, double r2){
		return vCC - (vBB(r1, r2, vCC)-vBEfa)*(betaF*rC+(1+betaF)*rE)/(rB(r1, r2)+(1+betaF)*rE);
	}
	
	/**
	 * Quiescent base to collector bias using the approximation that the base-to-emitter bias is 0.7 and the BJT is in forward active range.
	 * 
	 * @param vCC - supply voltage
	 * @param betaF - common emitter forward current gain
	 * @param rC - collector resistance
	 * @param rE - emitter resistance
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @return - vBE approximation
	 */
	public static double baseToCollectorBiasApproximation(double vCC, double betaF, double rC, double rE, double r1, double r2){
		return vBEfa - collectorToEmitterBiasApproximation(vCC, betaF, rC, rE, r1, r2);
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
		return collectorCurrentApproximation(r1, r2, vCC, betaF, rE)/Constants.kbtq;
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
	
	private static double rP(double vA, double r1, double r2, double vCC, double betaF, double rE, double rC, double rL){
		return 1/(1/collectorToEmitterResistance(vA, r1, r2, vCC, betaF, rE)+1/rC+1/rL);
	}
	
	/**
	 * Terminal voltage gain for the BJT amplifier.
	 * 
	 * @param vA - Early voltage
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @param vCC - supply voltage
	 * @param betaF - common emitter forward current gain
	 * @param rE - emitter resistance
	 * @param rC - collector resistance
	 * @param rL - load resistance
	 * @return aVT - terminal voltage gain
	 */
	public static double terminalVoltageGain(double vA, double r1, double r2, double vCC, double betaF, double rE, double rC, double rL){
		return -forwardTransconductance(r1, r2, vCC, betaF, rE)*rP(vA, r1, r2, vCC, betaF, rE, rC, rL);
	}
	
	/**
	 * Voltage gain for the BJT amplifier.
	 * 
	 * @param vA - Early voltage
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @param vCC - supply voltage
	 * @param betaF - common emitter forward current gain
	 * @param rE - emitter resistance
	 * @param rC - collector resistance
	 * @param rL - load resistance
	 * @param rSig - signal source resistance
	 * @return aV - voltge gain
	 */
	public static double voltageGain(double vA, double r1, double r2, double vCC, double betaF, double rE, double rC, double rL, double rSig){
		return terminalVoltageGain(vA, r1, r2, vCC, betaF, rE, rC, rL)*
				     (1/(1/rB(r1, r2)+1/baseToEmitterResistance(r1, r2, vCC, betaF, rE))) / 
				(rSig+1/(1/rB(r1, r2)+1/baseToEmitterResistance(r1, r2, vCC, betaF, rE)));
	}
	
	/**
	 * Current gain for the BJT amplifier. 
	 * 
	 * @param vA - Early voltage
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @param vCC - supply voltage
	 * @param betaF - common emitter forward current gain
	 * @param rE - emitter resistance
	 * @param rC - collector resistance
	 * @param rL - load resistance
	 * @return aI - current gain
	 */
	public static double currentGain(double vA, double r1, double r2, double vCC, double betaF, double rE, double rC, double rL){
		return terminalVoltageGain(vA, r1, r2, vCC, betaF, rE, rC, rL) * 
					(1/(1/rB(r1, r2)+1/baseToEmitterResistance(r1, r2, vCC, betaF, rE))) / rL;
	}
	
	/**
	 * Small signal input resistance to the BJT amplifier.
	 * 
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @param vCC - supply voltage
	 * @param betaF - common emitter forward current gain
	 * @param rE - emitter resistance
	 * @return rin - input resistance
	 */
	public static double inputResistance(double r1, double r2, double vCC, double betaF, double rE){
		return 1/(1/rB(r1, r2)+1/baseToEmitterResistance(r1, r2, vCC, betaF, rE));
	}
	
	/**
	 * Small signal output resistance to the BJT amplifier.
	 * 
	 * @param vA - Early voltage
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @param vCC - supply voltage
	 * @param betaF - common emitter forward current gain
	 * @param rE - emitter resistance
	 * @param rC - collector resistance
	 * @return rOut - output resistance
	 */
	public static double outputResistance(double vA, double r1, double r2, double vCC, double betaF, double rE, double rC){
		return 1/(1/collectorToEmitterResistance(vA, r1, r2, vCC, betaF, rE)+1/rC);
	}
}
