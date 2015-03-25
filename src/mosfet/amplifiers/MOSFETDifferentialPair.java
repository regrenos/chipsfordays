package mosfet.amplifiers;

public class MOSFETDifferentialPair {
	
	/**
	 * Differential mode gain for the MOSFET differential pair.
	 * 
	 * @param gm - forward transconductance of the MOSFET
	 * @param rD - drain resistance
	 * @param rdssat - output resistance of the MOSFET
	 * @return Add - differential mode gain
	 */
	public static double differentialModeGain(double gm, double rD, double rdssat){
		return -gm*(1/(1/rD+1/rdssat));
	}
	
	/**
	 * Differential mode input resistance, assumed to be infinity as there is no gate current.
	 * 
	 * @return Rid - input resistance
	 */
	public static double differentialModeInputResistance(){
		return Double.MAX_VALUE;
	}
	
	/**
	 * Differential mode output resistance.
	 * 
	 * @param rD - drain resistance
	 * @param rdssat - output resistance of the MOSFET
	 * @return Rod - output resistance
	 */
	public static double differentialModeOutputResistance(double rD, double rdssat){
		return 1/(1/(2*rD)+1/(2*rdssat));
	}
	
	/**
	 * Common mode gain.
	 * 
	 * @param gm - forward transconductance of the MOSFET
	 * @param rD - drain resistance
	 * @param rSS - resistance at the source (of the current source)
	 * @return Acc - common mode gain
	 */
	public static double commonModeGain(double gm, double rD, double rSS){
		return -gm*rD/(1+2*gm*rSS);
	}
	
	/**
	 * Common mode input resistance, assumed to be infinity as there is no gate current.
	 * 
	 * @return Ric - input resistance
	 */
	public static double commonModeInputResitance(){
		return Double.MAX_VALUE;
	}
	
	/**
	 * CMMR of the MOSFET differential pair.
	 * 
	 * @param gm - forward transconductance of the MOSFET
	 * @param rdssat - output resistance of the MOSFET
	 * @param rD - drain resistance
	 * @param rSS - resistance at the source (of the current source)
	 * @return CMMR - common mode rejection raio
	 */
	public static double commonModeRejectionRatio(double gm, double rdssat, double rD, double rSS){
		return differentialModeGain(gm, rD, rdssat)/(2*commonModeGain(gm, rD, rSS));
	}
}
