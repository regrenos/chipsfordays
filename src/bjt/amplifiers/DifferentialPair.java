package bjt.amplifiers;

import util.Constants;
import bjt.BipolarJunctionTransistor;

public class DifferentialPair {
	
	/**
	 * Emitter current for both BJTs in the differential pair.	
	 * 
	 * @param vIN - static input voltage to bases of BJTs
	 * @param vEE - supply voltage
	 * @param rEE - emitter resistance
	 * @return iE - emitter current
	 */
	public static double emitterCurrent(double vIN, double vEE, double rEE){
		return (vIN + vEE - BipolarJunctionTransistor.vBEfa)/(2*rEE);
	}
	
	/**
	 * Collector current for both BJTs in the differential pair.
	 * 
	 * @param vIN - static input voltage to bases of BJTs
	 * @param vEE - supply voltage
	 * @param rEE - emitter resistance
	 * @param aF - common-base forward current gain
	 * @return iC - collector current
	 */
	public static double collectorCurrent(double vIN, double vEE, double rEE, double aF){
		return aF*emitterCurrent(vIN, vEE, rEE);
	}	
	
	/**
	 * Base current for both BJTs in the differential pair
	 * 
	 * @param vIN - static input voltage to bases of BJTs
	 * @param vEE - supply voltage
	 * @param rEE - emitter resistance
	 * @param aF - common-base forward current gain
	 * @param bF - common-emitter forward current gain
	 * @return iB - base current
	 */
	public static double baseCurrent(double vIN, double vEE, double rEE, double aF, double bF){
		return collectorCurrent(vIN, vEE, rEE, aF)/bF;
	}
	
	/**
	 * Collector to emitter bias for both BJTs in the differntial pair.
	 * 
	 * @param vCC - supply voltage
	 * @param rC - collector resistance
	 * @param vIN - static input voltage to bases of BJTs
	 * @param vEE - supply voltage
	 * @param rEE - emitter resistance
	 * @param aF - common-base forward current gain
	 * @return vCE - collector to emitter bias
	 */
	public static double collectorToEmitterBias(double vCC, double rC, double vIN, double vEE, double rEE, double aF){
		return vCC - rC*collectorCurrent(vIN, vEE, rEE, aF)-2*rEE*emitterCurrent(vIN, vEE, rEE)+vEE;
	}
	
	private static double forwardTransconductance(double vIN, double vEE, double rEE, double aF){
		return collectorCurrent(vIN, vEE, rEE, aF)/Constants.kbtq;
	}
	
	/**
	 * Differential mode gain for the amplifier
	 * 
	 * @param vIN - static input voltage to bases of BJTs
	 * @param vEE - supply voltage
	 * @param rEE - emitter resistance
	 * @param aF - common-base forward current gain
	 * @param rC - collector load
	 * @return Add - differential mode gain
	 */
	public static double differentialModeGain(double vIN, double vEE, double rEE, double aF, double rC){
		return -rC*forwardTransconductance(vIN, vEE, rEE, aF);
	}
	
	private static double rBE(double vIN, double vEE, double rEE, double aF, double bF){
		return bF/forwardTransconductance(vIN, vEE, rEE, aF);
	}
	
	/**
	 * Differential mode input resistance (equal to twice rBE)
	 * 
	 * @param vIN - static input voltage to bases of BJTs
	 * @param vEE - supply voltage
	 * @param rEE - emitter resistance
	 * @param aF - common-base forward current gain
	 * @param bF - common-emitter forward current gain
	 * @return Rid - input resistance for the differential mode
	 */
	public static double differentialModeInputResistance(double vIN, double vEE, double rEE, double aF, double bF){
		return 2*rBE(vIN, vEE, rEE, aF, bF);
	}
	
	private static double rCE(double vIN, double vEE, double rEE, double aF, double vA){
		return vA/(Constants.kbtq*forwardTransconductance(vIN, vEE, rEE, aF));
	}
	
	/**
	 * Differential mode output resistance, equal to 2*rCE in parallel with 2*rC
	 * 
	 * @param rC - collector load
	 * @param vA - Early Voltage
	 * @param vIN - static input voltage to bases of BJTs
	 * @param vEE - supply voltage 
	 * @param rEE - emitter resistance
	 * @param aF - common-base forward current gain
	 * @return
	 */
	public static double differentialModeOutputResistance(double rC, double vA, double vIN, double vEE, double rEE, double aF){
		return 1/(1/(2*rC)+1/(2*rCE(vIN, vEE, rEE, aF, vA)));
	}
	
	/**
	 * Common-mode gain for the BJT differential amp without taking rCE into account
	 * 
	 * @param vIN - static input voltage to bases of BJTs
	 * @param vEE - supply voltage 
	 * @param rEE - emitter resistance
	 * @param aF - common-base forward current gain
	 * @param bF - common-emitter forward current gain
	 * @param rC - collector load
	 * @return Acc 0 common mode gain
	 */
	public static double commonModeGainNoRCE(double vIN, double vEE, double rEE, double aF, double bF, double rC){
		return bF*rC/(rBE(vIN, vEE, rEE, aF, bF)+2*(1+bF)*rEE);
	}
	
	/**
	 * Common-mode gain for the BJT differential amp taking rCE into account.
	 * 
	 * @param rC - collector load
	 * @param bF - common-emitter forward current gain
	 * @param vIN - static input voltage to bases of BJTs
	 * @param vEE - supply voltage 
	 * @param rEE - emitter resistance
	 * @param aF - common-base forward current gain
	 * @param vA - Early voltage
	 * @return
	 */
	public static double commonModeGain(double rC, double bF, double vIN, double vEE, double rEE, double aF, double vA){
		return rC*(1/(bF*rCE(vIN, vEE, rEE, aF, vA))-1/(2*rEE));
	}
	
	/**
	 * Common-mode input resistance
	 * 
	 * @param bF - common-emitter forward current gain
	 * @param vIN - static input voltage to bases of BJTs
	 * @param vEE - supply voltage 
	 * @param rEE - emitter resistance
	 * @param aF - common-base forward current gain
	 * @return Ric - common mode input resistance
	 */
	public static double commonModeInputResistance(double bF, double vIN, double vEE, double rEE, double aF){
		return (rBE(vIN, vEE, rEE, aF, bF)+2*(1+bF)*rEE)/2;
	}
	
	/**
	 * Common-mode rejection ratio
	 * 
	 * @param rC - collector load
	 * @param bF - common-emitter forward current gain
	 * @param vIN - static input voltage to bases of BJTs
	 * @param vEE - supply voltage 
	 * @param rEE - emitter resistance
	 * @param aF - common-base forward current gain
	 * @param vA - Early voltage
	 * @return CMMR - common mode rejection ratio
	 */
	public static double commonModeRejectionRatio(double rC, double bF, double vIN, double vEE, double rEE, double aF, double vA){
		return Math.abs(differentialModeGain(vIN, vEE, rEE, aF, rC)/(2*commonModeGain(rC, bF, vIN, vEE, rEE, aF, vA)));
	}
	
}
