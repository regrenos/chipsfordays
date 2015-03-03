package bjt.amplifiers;

import bjt.BipolarJunctionTransistor;

public class CommonEmitterACUnbypassedEmitterResistance {
	
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
		return CommonEmitterACBypassedEmitterResistance.baseCurrentApproximation(r1, r2, vCC, betaF, rE);
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
		return CommonEmitterACBypassedEmitterResistance.collectorCurrentApproximation(r1, r2, vCC, betaF, rE);
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
		return CommonEmitterACBypassedEmitterResistance.collectorToEmitterBiasApproximation(vCC, betaF, rC, rE, r1, r2);
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
		return CommonEmitterACBypassedEmitterResistance.baseToCollectorBiasApproximation(vCC, betaF, rC, rE, r1, r2);
	}
	
	/*
	 * Utility functions to truncate names.
	 */
	private static double rCrL(double rC, double rL){
		return 1/(1/rC+1/rL);
	}
	private static double gM(double r1, double r2, double vCC, double betaF, double rE){
		return BipolarJunctionTransistor.forwardTransconductance(r1, r2, vCC, betaF, rE);
	}
	private static double gCE(double vA, double r1, double r2, double vCC, double betaF, double rE){
		if (vA == Double.MAX_VALUE){
			return 0;
		}
		else {
			return 1/BipolarJunctionTransistor.collectorToEmitterResistance(vA, r1, r2, vCC, betaF, rE);
		}
	}
	private static double gBE(double r1, double r2, double vCC, double betaF, double rE){
		return 1/BipolarJunctionTransistor.baseToEmitterResistance(r1, r2, vCC, betaF, rE);
	}
	private static double vOUT(double rC, double rL, double r1, double r2, double betaF, double rE, double vA, double vCC){
		return -(rCrL(rC, rL)*(gM(r1, r2, vCC, betaF, rE)-gCE(vA, r1, r2, vCC, betaF, rE)*gBE(r1, r2, vCC, betaF, rE)*rE) / 
				(1 + gCE(vA, r1, r2, vCC, betaF, rE)*rCrL(rC, rL)+gCE(vA, r1, r2, vCC, betaF, rE)*rE));
	}
	private static double vIN(double r1, double r2, double vCC, double betaF, double rE, double vA, double rL, double rC){
		return ( 1 + (rE*(gBE(r1, r2, vCC, betaF, rE)*(1+gCE(vA, r1, r2, vCC, betaF, rE)*rCrL(rC, rL)))+gM(r1, r2, vCC, betaF, rE)) / 
				(1+gCE(vA, r1, r2, vCC, betaF, rE)*rCrL(rC, rL)+gCE(vA, r1, r2, vCC, betaF, rE)*rE));
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
		if (vA == Double.MAX_VALUE){
			return -rCrL(rC, rL)*gM(r1, r2, vCC, betaF, rE)/(1+rE*(gBE(r1, r2, vCC, betaF, rE)+gM(r1, r2, vCC, betaF, rE)));
		}
		else {
			return vOUT(rC, rL, r1, r2, betaF, rE, vA, vCC)/ vIN(r1, r2, vCC, betaF, rE, vA, rL, rC);
		}
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
		if (vA == Double.MAX_VALUE){
			return -rCrL(rC, rL)*gM(r1, r2, vCC, betaF, rE) / 
					((1+rSig/rB(r1, r2))*(1+rE*(gBE(r1, r2, vCC, betaF, rE)+gM(r1, r2, vCC, betaF, rE)))+rSig*gBE(r1, r2, vCC, betaF, rE));
		}
		else {
			return vOUT(rC, rL, r1, r2, betaF, rE, vA, vCC) /
				((1+rSig/rB(r1, r2))*(vIN(r1, r2, vCC, betaF, rE, vA, rL, rC))+rSig*gBE(r1, r2, vCC, betaF, rE));
		}
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
		if (vA == Double.MAX_VALUE){
			return -rCrL(rC, rL)*gM(r1, r2, vCC, betaF, rE) / 
					(rL*(1/rB(r1, r2)+rE/rB(r1, r2)*(gBE(r1, r2, vCC, betaF, rE)+gM(r1, r2, vCC, betaF, rE))+gBE(r1, r2, vCC, betaF, rE)));
		}
		else {
			return (vOUT(rC, rL, r1, r2, betaF, rE, vA, vCC)/rL) / 
				(vIN(r1, r2, vCC, betaF, rE, vA, rL, rC)/rB(r1, r2)+gBE(r1, r2, vCC, betaF, rE));
		}
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
	public static double inputResistance(double r1, double r2, double vCC, double betaF, double rE, double rC, double rL, double vA){
		if (vA == Double.MAX_VALUE){
			return (1+rE*(gBE(r1, r2, vCC, betaF, rE)+gM(r1, r2, vCC, betaF, rE))) /
					(1/rB(r1, r2)+rE/rB(r1, r2)*(gBE(r1, r2, vCC, betaF, rE)+gM(r1, r2, vCC, betaF, rE))+gBE(r1, r2, vCC, betaF, rE));
		}
		else {
			return (vIN(rC, rL, r1, r2, betaF, rE, vA, vCC)/rL) / 
					(vIN(r1, r2, vCC, betaF, rE, vA, rL, rC)/rB(r1, r2)+gBE(r1, r2, vCC, betaF, rE));
		}
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
	public static double outputResistance(double vA, double r1, double r2, double vCC, double betaF, double rE, double rC, double rSig){
		if (vA == Double.MAX_VALUE){
			return rC;
		}
		else {
			return 1/(1/rC + gCE(vA, r1, r2, vCC, betaF, rE)*(rE+1/gBE(r1, r2, vCC, betaF, rE)+1/(1/rSig+1/rB(r1, r2)))
					/(rE+1/gBE(r1, r2, vCC, betaF, rE)+(1+gCE(vA, r1, r2, vCC, betaF, rE)*rE)/(1/rSig+1/rB(r1, r2))+rE*(gM(r1, r2, vCC, betaF, rE)+gCE(vA, r1, r2, vCC, betaF, rE))/gBE(r1, r2, vCC, betaF, rE)));
		}
	}

}
