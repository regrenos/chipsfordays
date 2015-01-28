
public class Amplifiers {

	/** Drain Current Neglecting Channel Length Modulation (A) */ 
	public static double Idq(double Kn, double Vgs, double Vtn){
		double result = Kn/2*Math.pow((Vgs-Vtn),2);
		System.out.print("Drain Current at Quiescent Neglecting Channel Length Modulation (A): ");
		return C.s(result);
	}

	/** Drain Current With Channel Length Modulation (A) */
	public static double Idq2(double Kn, double Vgs, double Vtn, double lambda, double Vds, double Vdsat){
		double result = Kn/2*Math.pow((Vgs-Vtn),2)*(1+lambda*(Vds-Vdsat));
		System.out.print("Drain Current at Quiescent With Channel Length Modulation (A): ");
		return C.s(result);
	}

	/** Small Signal Forward Transconductance Neglecting Channel Length Modulation */
	public static double gmsat(double Kn, double Vgs, double Vtn){
		double result = Math.sqrt(2*Kn*Idq(Kn,Vgs,Vtn));
		System.out.print("Small Signal Forward Transconductance Neglecting Channel Length Modulation (A/V): ");
		return C.s(result);
	}

	/** Small Signal Forward Transconductance With Channel Length Modulation */
	public static double gmsat2(double Kn, double Vgs, double Vtn, double lambda, double Vds, double Vdsat){
		double result = Math.sqrt(2*Kn*Idq2(Kn,Vgs,Vtn,lambda,Vds,Vdsat));
		System.out.print("Small Signal Forward Transconductance With Channel Length Modulation (A/V): ");
		return C.s(result);
	}

	/** Inherent Resistor In Transistor Due to Channel Length Modulation */
	public static double rosat(double Kn, double Vgs, double Vtn, double lambda, double Vds, double Vdsat){
		double result = 1/(lambda*Idq2(Kn,Vgs,Vtn,lambda,Vds,Vdsat));
		System.out.print("Internal Resistance of Transistor Due to Channel Length Modulation (Ohms): ");
		return C.s(result);   
	}
}
