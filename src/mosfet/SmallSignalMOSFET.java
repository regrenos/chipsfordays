package mosfet;
import util.Constants;
import moscap.MOSCAP;


public class SmallSignalMOSFET {
	
	/**Small Signal Drain Current */
	public static double id(double gm, double vgs, double gmb, double vbs, double go, double vds){
		double result = (gm*vgs) + (gmb*vbs) + (go*vds);
		System.out.print("The small signal drain current for the MOSFET is (A): ");
		return Constants.s(result);
	}
	
	/**Small Signal Transconductance */ 
	public static double gmsat(double Kn, double VGS, double lambda, double VDS, double VBS, double Na,double Xox, double QfQit){
		double vtn = MOSFET.Vtn(VBS, Na, Xox, QfQit);
		double vdsat = MOSFET.VDsat(VGS, VBS, Na, Xox, QfQit);
		double result = Kn*(VGS - vtn) * (1 + lambda*(VDS - vdsat));
		System.out.print("The transconductance gmsat (A/V^2) is: ");
		return Constants.s(result);
	}
	
	/**Small Signal Transconductance Through the Bulk */
	public static double gmbsat(double Kn, double VGS, double lambda, double VDS, double VBS, double Na, double Xox, double QfQit){
		double gmsat = gmsat(Kn, VGS, lambda, VDS, VBS, Na, Xox, QfQit);
		double gammbn = MOSFET.gammaBn(Na, Xox);
		double phifb = MOSCAP.phiFb(Na);
		double result = gmsat * (gammbn / (2*Math.sqrt(2*phifb - VBS)));
		System.out.print("The transconductance in the bulk gmbsat (A/V^2) is: ");
		return Constants.s(result);
	}
	
	/**Output Saturation Transconductance */
	public static double gosat(){
		double result = 0;
		System.out.print("ASSUMING this is 0 because lambda usually 0 and r_o_sat is infinity: ");
		return Constants.s(result);
	}
	
	/**Cut-off Frequency */
	public static double fT(double mu, double Lch, double VGS, double VBS, double Na,double Xox, double QfQit){
		double vtn = MOSFET.Vtn(VBS, Na, Xox, QfQit);
		double result = ((3*mu) / (4*Math.PI*Constants.p(Lch, 2))) * (VGS - vtn);
		System.out.print("This weird T frequency is: ");
		return Constants.s(result);
	}

}
