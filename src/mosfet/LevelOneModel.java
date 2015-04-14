package mosfet;

import util.Constants;

public class LevelOneModel {
	
	/**
	 * Depletion charge density in the NMOSFET.
	 * 
	 * @param naB - concentration of acceptors in the bulk 
	 * @param phiFB - Fermi potential in substrate
	 * @param vCO - channel to origin voltage (at 0, 0, at l-channel, VDS) 
	 * @param vBS - bulk to source voltage
	 * @return - depletion charge density
	 */
	public static double depletionChargeDensity(double naB, double phiFB, double vCO, double vBS){
		return -Math.sqrt(2*Constants.q*Constants.esi*naB*(2*phiFB + vCO - vBS));
	}
	
	/**
	 * Threshold voltage of NMOSFET at VBS = 0.
	 * 
	 * @param vFB - flat band voltage
	 * @param phiFB - Fermi potential
	 * @param gammaBN - bulk body effect coefficient
	 * @return - threshold voltage
	 */
	public static double thresholdVoltage0(double vFB, double phiFB, double gammaBN){
		return vFB + 2*phiFB + gammaBN*Math.sqrt(2*phiFB);
	}
	
	/**
	 * Bulk body effect coefficient, for making other calculations simpler.
	 * 
	 * @param nAB - concentration of acceptors in bulk
	 * @param cOX - oxide capacitance
	 * @return - bulk body effect coefficient
	 */
	public static double bulkBodyEffectCoefficient(double nAB, double cOX){
		return Math.sqrt(2*Constants.q*Constants.esi*nAB)/cOX;
	}
	
	/**
	 * Fermi potential of substrate.pu
	 * 
	 * @param naB - concentration of acceptors in region
	 * @return - Fermi potential
	 */
	public static double fermiPotential(double naB){
		return Constants.kbtq*Math.log(naB/Constants.ni);
	}
	
	/**
	 * Threshold voltage of the NMOSFET
	 * 
	 * @param vFB - flat band voltage
	 * @param phiFB - fermi potential
	 * @param nAB - concentration of acceptors in bulk
	 * @param vBS - bulk to source voltage
	 * @param cOX - capacitance of oxide
	 * @return - threshold voltage
	 */
	public static double thresholdVoltage(double vFB, double phiFB, double nAB, double vBS, double cOX){
		return vFB + 2*phiFB + Math.sqrt(2*Constants.q*Constants.esi*nAB*(2*phiFB-vBS))/cOX;
	}
	
	/**
	 * Flat-band voltage of the NMOSFET.
	 * 
	 * @param phiPM - contact potential
	 * @param qF - charge on ???
	 * @param qIT - charge on interface trap
	 * @param cOX - oxide capacitance
	 * @return - flat band voltage
	 */
	public static double flatBandVoltage(double phiPM, double qF, double qIT, double cOX){
		return phiPM-(qF+qIT)/cOX;
	}
	
	/**
	 * Oxide capacitance knowing oxide thickness.
	 * 
	 * @param xOX - oxide thickness (cm)
	 * @return - oxide capacitance
	 */
	public static double oxideCapacitance(double xOX){
		return xOX/Constants.eox;
	}
	
	/**
	 * Drain current in the linear bias range for an NMOSFET.
	 * 
	 * @param kN - mobility of charge carriers * capacitance of oxide * width of channel / length of channel
	 * @param vGS - gate to source bias (V)
	 * @param vTN - threshold voltage (V)
	 * @param vDS - drain to source bias (V)
	 * @return - drain current (A)
	 */
	public static double linearRangeDrainCurrent(double kN, double vGS, double vTN, double vDS){
		return kN*((vGS-vTN)*vDS-Math.pow(vDS, 2)/2);
	}
	
	/**
	 * Drain current in the saturation bias range for an NMOSFET with channel length modulation.
	 * 
	 * @param kN - mobility of charge carriers * capacitance of oxide * width of channel / length of channel (A/V^2)
	 * @param vGS - gate to source bias (V)
	 * @param vTN - threshold voltage (V)
	 * @param vDS - drain to source bias (V)
	 * @param lambdaN - channel length modulation factor (1/V)
	 * @return - drain current (A)
	 */
	public static double saturationRangeDrainCurrent(double kN, double vGS, double vTN, double vDS, double lambdaN){
		return kN*Math.pow(vGS-vTN,2)*(1+lambdaN*(vDS-(vGS-vTN)));
	}

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
}
