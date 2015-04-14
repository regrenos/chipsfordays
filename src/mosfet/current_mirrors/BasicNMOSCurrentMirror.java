package mosfet.current_mirrors;

public class BasicNMOSCurrentMirror {

	/**
	 * Numerically solve for vDS and vGS for the driving MOSFET in a current mirror. Solved for using 10000 points.
	 * 
	 * @param vTN - threshold voltage
	 * @param iD - drain current (reference)
	 * @param kNprime - kN without the width/length term
	 * @param wl - width over lenght
	 * @param lambdaN - channel length modulation coefficient
	 * @param searchMin - minimum search bound
	 * @param searchMax - maximum search bound
	 * @return vGS - gate to source bias
	 */
	public static double gateToSourceBias(double vTN, double iD, double kNprime, double wl, double lambdaN, double searchMin, double searchMax){
		double[] possibleValues = new double[10000];
		for(int i=0; i<10000; i++){
			possibleValues[i] = searchMin + i*(searchMax-searchMin)/10000;
		}
		double[] error = new double[10000];
		for(int i=0;i<10000;i++){
			error[i] = Math.abs(possibleValues[i] - (vTN+Math.sqrt(2*iD/(kNprime*wl*(1+lambdaN*possibleValues[i])))));
		}
		
		return possibleValues[minIndex(error)];
	}
	
	private static int minIndex(double[] input){
		int index = 0;
		double value = input[0];
		for (int i=0;i<input.length;i++){
			if(input[i]<value){
				index = i;
				value = input[i];
			}
		}
		return index;
	}
	
	/**
	 * Calculate other currents in a current mirror knowing reference parameters.
	 * 
	 * @param iD_ref - reference drain current
	 * @param wl_ref - reference geometry parameter
	 * @param wl - other geometry parameter
	 * @param lambdaN - channel lenght modulation parameter
	 * @param vDS_ref - reference drain to source bias
	 * @param vDS - other drain to source bias 
	 * @return io - other current
	 */
	public static double otherCurrent(double iD_ref, double wl_ref, double wl, double lambdaN, double vDS_ref, double vDS){
		return iD_ref*((wl)/(wl_ref))*(1+lambdaN*vDS)/(1+lambdaN*vDS_ref); 
	}
	
	
}
