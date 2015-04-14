package digital;

public class MOSInverterResistiveLoad {

	/**
	 * Maximum input low signal for an output high signal.
	 * 
	 * @param vTN - threshold voltage 
	 * @param kN - MOS parameter
	 * @param rL - load resistance
	 * @return vIL
	 */
	public static double inputLow(double vTN, double kN, double rL){
		return vTN + 1/(kN*rL);
	}
	
	/**
	 * Logical zero level output - must be less than vIL.
	 * 
	 * @param vDD - source bias
	 * @param vTN - threshold voltage
	 * @param kN - MOS parameter
	 * @param rL - load resistance
	 * @return vOL
	 */
	public static double outputLow(double vDD, double vTN, double kN, double rL){
		return (vDD-vTN+1/(kN*rL)) - Math.sqrt((vDD-vTN+1/(kN*rL))-2*vDD/(kN*rL));
	}
	
	/**
	 * Minimum high input to make a low output.
	 * 
	 * @param vTN - threshold voltage
	 * @param kN - MOS paramter
	 * @param rL - load resistance
	 * @param vDD - source bias
	 * @return vIH
	 */
	public static double inputHigh(double vTN, double kN, double rL, double vDD){
		return vTN - 1/(kN*rL) + Math.sqrt(8*vDD/(3*kN*rL));
	}
	
	/**
	 * Logical high level.	
	 * 
	 * @param vDD - source bias
	 * @return
	 */
	public static double outputHigh(double vDD){
		return vDD;
	}
	
	/**
	 * Static noise margin low - difference between vIL and vOL.
	 * 
	 * @param vTN - threshold voltage
	 * @param kN - MOS paramter
	 * @param rL - load resistance
	 * @param vDD - source bias
	 * @return SNML
	 */
	public static double staticNoiseMarginLow(double vTN, double kN, double rL, double vDD){
		return MOSInverterResistiveLoad.inputLow(vTN, kN, rL)-MOSInverterResistiveLoad.outputLow(vDD, vTN, kN, rL);
	}
	
	/**
	 * Static noise margin high - difference between vIH and vOH.
	 * 
	 * @param vTN - threshold voltage
	 * @param kN - MOS parameter 
	 * @param rL - load resistance
	 * @param vDD - source bias
	 * @return - SNMH
	 */
	public static double staticNoiseMarginHigh(double vTN, double kN, double rL, double vDD){
		return MOSInverterResistiveLoad.inputHigh(vTN, kN, rL, vDD)-MOSInverterResistiveLoad.outputHigh(vDD);
	}
	
	public static double instantaneousPower(double vIN, double vTN, double kN, double rL, double vDD){
		double vOL = MOSInverterResistiveLoad.outputLow(vDD, vTN, kN, rL);
		double vOH = MOSInverterResistiveLoad.outputHigh(vDD);
		
		if (vIN<=vOL){
			return 0;
		}
		else if (vIN>=vOH){
			return (vDD/rL)*(vTN-1/(kN*rL)+Math.sqrt(Math.pow(vDD-vTN+1/(kN*rL),2)-2*vDD/kN*rL));
		}
		else {
			return -1;
		}
	}
}
