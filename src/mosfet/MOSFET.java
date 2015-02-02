
public class MOSFET {

	/**MOSFET Threshold Voltage. QfQit = Qf+Qit */
	public static double Vtn(double VBS, double Na,double Xox, double QfQit) {
		
		double phifb = MOSCAP.phiFb(Na);
		double result = MOSCAP.Vfb(Na,Xox,QfQit) + 2*phifb + (1/MOSCAP.Cox(Xox)) * Math.sqrt(2*Constants.q*Constants.esi*Na*((2*phifb)-VBS));
		System.out.print("The MOSFET threshold voltage due to V_BS is: ");
		return Constants.s(result);
	}
	
	/**MOSFET Threshold Voltage at Vbs=0. QfQit = Qf+Qit */
	public static double vtn0(double Na,double Xox, double QfQit){
		double result = Vtn(0, Na, Xox, QfQit);
		System.out.print("The MOSFET threshold voltage at V_BS=0 is (V): ");
		return Constants.s(result);
	}
	
	public static double gammaBn(double Na, double Xox){
		double result = Math.sqrt(2*Constants.q*Constants.esi*Na) / MOSCAP.Cox(Xox);
		System.out.print("The bulk body effect coefficient for the MOSFET is: ");
		return Constants.s(result);
	}
	
	/**Vdsat. Drain to Source Saturation Voltage */
	public static double VDsat(double VGS, double VBS, double Na,double Xox, double QfQit){
		double vtn = Vtn(VBS, Na, Xox, QfQit);
		double result = VGS - vtn;
		if(VGS<=vtn){
			System.out.println("BTW, this NMOSFET is OFF and I_D_off = 0 A.");
		}
		else{
			System.out.println("BTW, this NMOSFET is ON, If V_DS < VDsat, linear range, else saturation range.");
		}
		System.out.print("The drain voltage at saturation VDsat is (V): ");
		return Constants.s(result);
	}
	
	/**Calculate Kn */
	public static double Kn(double mu, double W, double L, double Xox){
		double result = mu * MOSCAP.Cox(Xox) * (W/L);
		System.out.print("K_n for the MOSFET is: ");
		return Constants.s(result);
	}
	
	/**Calculating the Drain Current in the Linear Range */
	public static double IDlin(double Kn, double VDS, double VGS, double VBS, double Na,double Xox, double QfQit){
		double vtn = Vtn(VBS, Na, Xox, QfQit);
		double result = Kn*(((VGS-vtn)*VDS) - 0.5*(Constants.p(VDS, 2)));
		System.out.print("The drain current in the linear range for the MOSFET is (A): ");
		return Constants.s(result);
	}
	
	/**Calculating the Drain Current in the Saturation Range */
	public static double IDsat(double lambda, double Kn, double VDS, double VGS, double VBS, double Na,double Xox, double QfQit){
		double vtn = Vtn(VBS, Na, Xox, QfQit);
		double vdsat = VDsat(VGS, VBS, Na, Xox, QfQit);
		double result = (Kn/2)*Constants.p((VGS - vtn),2) * (1 + lambda*(VDS - vdsat));
		System.out.print("The drain current in the saturation range for the MOSFET is (A): ");
		return Constants.s(result);
	}
	
}
