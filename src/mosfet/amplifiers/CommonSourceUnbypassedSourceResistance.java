package mosfet.amplifiers;



public class CommonSourceUnbypassedSourceResistance {
	
	
	/**
	 * Forward transconductance for a NMOSFET in the saturation range.
	 * 
	 * @param kN - mu * Cox * W / L
	 * @param id - drain current
	 * @param lambdaN - channel length modulation factor
	 * @param vDS - drain t0 source bias
	 * @param vDSat - vGS-vTN
	 * @return gmsat - forward transconductance
	 */
	public static double forwardTransconductance(double kN, double id, double lambdaN, double vDS, double vDSat){
		return Math.sqrt(2*kN*id*(1+lambdaN*(vDS-vDSat)));
	}
	
	/**
	 * Forward transconductance for a NMOSFET in the saturation range.
	 * 
	 * @param kN - mu * Cox * W / L
	 * @param id - drain current
	 * @param lambdaN - channel length modulation factor
	 * @param vDS - drain t0 source bias
	 * @param vGS - gate to source bias
	 * @param vTN - threshold voltage
	 * @return gmsat - forward transconductance
	 */
	public static double forwardTransconductance(double kN, double id, double lambdaN, double vDS, double vGS, double vTN){
		return Math.sqrt(2*kN*id*(1+lambdaN*(vDS-vGS+vTN)));
	}
	
	
	/**
	 * Output resistance (also drain-to-source resistance) of NMOSFET in saturation range.
	 * 
	 * @param lambdaN - channel length modulation factor
	 * @param vDS - drain to source bias
	 * @param vDSat - vGS-vTN
	 * @param idsat - drain current
	 * @return ro - output resistance
	 */
	public static double outputResistance(double lambdaN, double vDS, double vDSat, double idsat){
		if(lambdaN==0){
			return Double.MAX_VALUE;
		}
		else{
			return (1+lambdaN*(vDS-vDSat))/(lambdaN*idsat);
		}
	}
	
	/**
	 * Output resistance (also drain-to-source resistance) of NMOSFET in saturation range.
	 * 
	 * @param lambdaN - channel length modulation factor
	 * @param vDS - drain to source bias
	 * @param vGS - gate to source bias
	 * @param vTN - threshold voltage
	 * @param idsat - drain current
	 * @return ro - output resistance
	 */
	public static double outputResistance(double lambdaN, double vDS, double vGS, double vTN, double idsat){
		if(lambdaN==0){
			return Double.MAX_VALUE;
		}
		else{
			return (1+lambdaN*(vDS-vGS+vTN))/(lambdaN*idsat);
		}
	}
	
	/**
	 * Terminal voltage gain for the unbypassed souce resistance MOSFET amplifier.
	 * 
	 * @param gmsat - forward transconductance
	 * @param rdssat - output resistance
	 * @param rD - drain resistance
	 * @param rL - load resistance
	 * @param rS - source resistance
	 * @return avt - terminal voltage gain
	 */
	public static double terminalVoltageGain(double gmsat, double rdssat, double rD, double rL, double rS){
		if (rdssat == Double.MAX_VALUE){
			return -gmsat*(1/(1/rD+1/rL))/(1+gmsat*rS);
		}
		else {
			return -gmsat*rdssat*(1/(1/rD+1/rL))/(rdssat*(1+gmsat*rS)+rS+1/(1/rD+1/rL));
		}
	}
	
	/**
	 * Voltage gain Avt for a common source unbypassed source resistance NMOSFET amplifier.
	 * 
	 * @param gmsat - forward transconductance
	 * @param rdssat - drain to source resistance (output resistance)
	 * @param rD - drain resistance
	 * @param rL - load resistance
	 * @param rS - source resistance
	 * @param rG - gate resistance
	 * @param rSig - signal resistance
	 * @return Av - voltage gain
	 */
	public static double voltageGain(double gmsat, double rdssat, double rD, double rL, double rS, double r1, double r2, double rSig){
		if (rdssat == Double.MAX_VALUE){
			return -gmsat*(1/(1/rD+1/rL))/(1+gmsat*rS)*(rG(r1,r2)/(rSig+rG(r1,r2)));
		}
		else {
			return -(gmsat*rdssat*(1/(1/rD+1/rL))/(rdssat*(1+gmsat*rS)+rS+(1/(1/rD+1/rL))))*(rG(r1,r2)/(rSig+rG(r1,r2)));
		}
	}
	
	/**
	 * Current gain for the NMOSFET amplifier.
	 * 
	 * @param gmsat - forward transconductance
	 * @param rdssat - drain to source (output) resistance
	 * @param rD - drain resistance
	 * @param rL - load resistance
	 * @param rG - gate resistance
	 * @param rS - source resistance
	 * @return Ai - current gain
	 */
	public static double currentGain(double gmsat, double rdssat, double rD, double rL, double r1, double r2, double rS){
		if (rdssat == Double.MAX_VALUE){
			return -gmsat*(1/(1/rD+1/rL))/(1+gmsat*rS)*(rG(r1,r2)/rL);
		}
		else {
			return -(gmsat*rdssat*(1/(1/rD+1/rL))/(rdssat*(1+gmsat*rS)+rS+(1/(1/rD+1/rL))))*(rG(r1,r2)/rL);
		}
	}
	
	/**
	 * Small signal input resistance to the NMOSFET amplifier.
	 * 
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @return Rin - input resistance
	 */
	public static double smallSignalInputResistance(double r1, double r2){
		return r1*r2/(r1+r2);
	}
	
	/**
	 * Small signal output resistance of the NMOSFET amplifier.
	 * 
	 * @param rdssat - drain to source (output) resistance
	 * @param rD - drain resistance
	 * @param rS - source resistance
	 * @param gmsat - forward transconductance
	 * @return Rout - output resistance
	 */
	public static double smallSignalOutputResistance(double rdssat, double rD, double rS, double gmsat){
		if (rdssat == Double.MAX_VALUE){
			return rD;
		}
		else {
			return rD*(rS+rdssat*(1+gmsat*rS))/(rD+rS+rdssat*(1+gmsat*rS));
		}
	}
	
	public static double rG(double r1, double r2){
		return 1/(1/r1+1/r2);
	}
}
