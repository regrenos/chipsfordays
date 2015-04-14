package mosfet.amplifiers;


public class CommonSourceBypassedSourceResistance {
	
	
	/**
	 * Terminal voltage gain Avt for a common source bypassed source resistance NMOSFET amplifier.
	 * 
	 * @param gmsat - forward transconductance
	 * @param rdssat - drain to source resistance (output resistance)
	 * @param rD - drain resistance
	 * @param rL - load resistance
	 * @return Avt - terminal voltage gain
	 */
	public static double terminalVoltageGain(double gmsat, double rdssat, double rD, double rL){
		return -gmsat*rP(rdssat, rD, rL);
	}
	
	public static double rP(double rdssat, double rD, double rL){
		return 1/(1/rdssat+1/rD+1/rL);
	}
	
	public static double rG(double r1, double r2){
		return 1/(1/r1+1/r2);
	}
	
	/**
	 * Voltage gain for the NMOSFET amplifier.
	 * 
	 * @param gmsat - forward transconductance
	 * @param rdssat - drain to source (output) resistance
	 * @param rD - drain resistance
	 * @param rL - load resistance
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @param rSig - signal reistance
	 * @return Av - voltage gain
	 */
	public static double voltageGain(double gmsat, double rdssat, double rD, double rL, double r1, double r2, double rSig){
		return -gmsat*rP(rdssat, rD, rL)*rG(r1,r2)/(rSig+rG(r1,r2));
	}
	
	/**
	 * Current gain for the NMOSFET amplifier.
	 * 
	 * @param gmsat - forward transconductance
	 * @param rdssat - drain to source (output) resistance
	 * @param rD - drain resistance
	 * @param rL - load resistance
	 * @param r1 - resistance 1
	 * @param r2 - resistance 2
	 * @return Ai - current gain
	 */
	public static double currentGain(double gmsat, double rdssat, double rD, double rL, double r1, double r2){
		return -gmsat*rP(rdssat, rD, rL)*rG(r1,r2)/rL;
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
		if (rdssat == Double.MAX_VALUE){
			return rD;
		}
		else{
			return rdssat*rD/(rdssat+rD);
		}
	}
}
