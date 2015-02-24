package mosfet.amplifiers;

import sun.awt.image.PixelConverter.Rgba;

public class CommonSourceBypassedSourceResistance {
	
	
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
			return 0;
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
			return 0;
		}
		else{
			return (1+lambdaN*(vDS-vGS+vTN))/(lambdaN*idsat);
		}
	}
	
	/**
	 * Terminal voltage gain Avt for a common source bypassed source resistance NMOSFET amplifier.
	 * 
	 * @param gmsat - forward transconductance
	 * @param rdssat - drain to source resistance (output resistance)
	 * @param rD
	 * @param rL
	 * @return
	 */
	public static double terminalVoltageGain(double gmsat, double rdssat, double rD, double rL){
		return gmsat*rP(rdssat, rD, rL);
	}
	
	private static double rP(double rdssat, double rD, double rL){
		return 1/(1/rdssat+1/rD+1/rL);
	}
	
	/**
	 * Voltage gain for the NMOSFET amplifier.
	 * 
	 * @param gmsat - forward transconductance
	 * @param rdssat - drain to source (output) resistance
	 * @param rD - drain resistance
	 * @param rL - load resistance
	 * @param rG - gate resistance
	 * @param rSig - signal reistance
	 * @return Av - voltage gain
	 */
	public static double voltageGain(double gmsat, double rdssat, double rD, double rL, double rG, double rSig){
		return -gmsat*rP(rdssat, rD, rL)*rG/(rSig+rG);
	}
	
	/**
	 * Current gain for the NMOSFET amplifier.
	 * 
	 * @param gmsat - forward transconductance
	 * @param rdssat - drain to source (output) resistance
	 * @param rD - drain resistance
	 * @param rL - load resistance
	 * @param rG - gate resistance
	 * @return Ai - current gain
	 */
	public static double currentGain(double gmsat, double rdssat, double rD, double rL, double rG){
		return -gmsat*rP(rdssat, rD, rL)*rG/rL;
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
	 * @return Rout - output resistance
	 */
	public static double smallSignalOutputResistance(double rdssat, double rD){
		return rdssat*rD/(rdssat+rD);
	}
}
