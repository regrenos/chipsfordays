package bjt.bias_range_current_approximations;

import util.Constants;
import bjt.BipolarJunctionTransistor;

public class SaturationRange {
	
	/**
	 * Collector current in the saturation range for an NPN BJT.
	 * 
	 * @param vBE - base to emitter bias (V)
	 * @param vBC - base to collector bias (V)
	 * @param alphaR - common-base reverse current gain
	 * @param iS - saturation current (A)
	 * @return - collector current (A)
	 */
	public static double collectorCurrent(double vBE, double vBC,
			double alphaR, double iS){
		return BipolarJunctionTransistor.collectorCurrent(vBE, vBC, alphaR, iS);
	}
	
	/**
	 * Emitter current in the saturation range for an NPN BJT.
	 * 
	 * @param vBE - base to emitter bias (V)
	 * @param vBC - base to collector bias (V)
	 * @param alphaF - common-base forward current gain
	 * @param iS - saturation current (A)
	 * @return - emitter current (A)
	 */
	public static double emitterCurrent(double vBE, double vBC, double alphaF,
			double iS){
		return BipolarJunctionTransistor.emitterCurrent(vBE, vBC, alphaF, iS);
	}
	
	/**
	 * Base current in the saturation range for an NPN BJT.
	 * 
	 * @param vBE - base to emitter bias (V)
	 * @param vBC - base to collector bias (V)
	 * @param betaF - common-emitter forward current gain 
	 * @param betaR - common-emitter reverse current gain
	 * @param iS - saturation current (A)
	 * @return - base current (A)
	 */
	public static double baseCurrent(double vBE, double vBC, double betaF,
			double betaR, double iS){
		return BipolarJunctionTransistor.baseCurrent(vBE, vBC, betaF, betaR, iS);
	}
	
	/**
	 * Base to emitter saturation voltage for an NPN BJT.
	 * 
	 * @param iS - saturation current (A)
	 * @param iB - base current (A)
	 * @param iC - collector current (A)
	 * @param alphaR - common-base reverse current gain
	 * @param betaF - common-emitter forward current gain
	 * @return - base to emitter saturation bias (V)
	 */
	public static double baseEmitterSaturationBias(double iS, double iB, double iC, double alphaR, double betaF){
		return Constants.kbtq*Math.log((iB+(1-alphaR)*iC)/(iS*(1/betaF+1-alphaR)));
	}
	
	/**
	 * Base to collector saturation voltage for an NPN BJT.
	 * 
	 * @param iS - saturation current (A)
	 * @param iB - base current (A)
	 * @param iC - collector current (A)
	 * @param alphaR - common-base reverse current gain
	 * @param betaF - common-emitter forward current gain
	 * @return - base to collector saturation bias (V)
	 */
	public static double baseCollectorSaturationBias(double iS, double iB, double iC, double alphaR, double betaF){
		return Constants.kbtq*Math.log((iB-iC/betaF)/((iS/alphaR)*(1/betaF+1-alphaR)));
	}
	
	/**
	 * Collector to emitter saturation bias for an NPN BJT.
	 * 
	 * @param iB - base current (A)
	 * @param iC - collector current (A)
	 * @param alphaR - common-base reverse current gain
	 * @param betaF - common-emitter forward current gain
	 * @return - collector to emitter saturation bias (V)
	 */
	public static double collectorEmitterSaturationBias(double iB, double iC, double alphaR, double betaF){
		return Constants.kbtq*Math.log((iB+(1-alphaR)*iC)/(alphaR*(iB-iC/betaF)));
	}

}
